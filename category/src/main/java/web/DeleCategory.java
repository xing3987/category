package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;

/**
 * 点击删除，根据id删除category数据
 */
public class DeleCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int id=Integer.parseInt(request.getParameter("id"));
		CategoryDao categoryDao=new CategoryDao();
		categoryDao.deleCategory(id);//根据id删除category数据
		response.sendRedirect(request.getContextPath()+"/CategoryList");
	}

}
