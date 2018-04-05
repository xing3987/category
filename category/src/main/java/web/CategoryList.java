package web;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import javaBean.Category;

/**
 * 生鲜列表
 */
public class CategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		CategoryDao categoryDao=new CategoryDao(); 
		int page1=1;
		if(request.getParameter("page1")!=null){
			page1=Integer.parseInt(request.getParameter("page1"));
			if(page1<=0){
				page1=1;
			}
		}
		List<Category> categorys=categoryDao.selectCategory(page1,10);//分页查询
		request.setAttribute("categorys", categorys);
		request.setAttribute("page1", page1);
		RequestDispatcher rd=request.getRequestDispatcher("category-list.jsp");
		rd.forward(request, response);
	}

}
