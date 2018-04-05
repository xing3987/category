package web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;

/**
 * 添加category数据
 */
public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name").trim();
		String categoryFrom=request.getParameter("categoryFrom").trim();
		String Type=request.getParameter("categoryType").trim();
		
		//根据选中的checked来判定categoryType值
		String categoryType="";
		if(Type.equals("0")){
			categoryType="水果";
		}else if(Type.equals("1")){
			categoryType="肉类";
		}else if(Type.equals("2")){
			categoryType="海鲜水产";
		}
		//生成时间
		Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("YYYYMMdd");
		String addTime=df.format(date);
		
		//使用categoryDao添加category
		CategoryDao categoryDao=new CategoryDao();
		categoryDao.addCategory(name,addTime,categoryType,categoryFrom);
		response.sendRedirect(request.getContextPath()+"/CategoryList");
	}

}
