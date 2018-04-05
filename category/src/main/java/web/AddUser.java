package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import javaBean.User;
/**
 * ÃÌº””√ªß
 */
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name").trim();
		String password=request.getParameter("password").trim();
		String email=request.getParameter("email").trim();
		User user=new User();
		user.setUsername(name);
		user.setPassword(password);
		user.setEmail(email);
		UserDao userDao=new UserDao();
		userDao.addUser(user);
		
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		
	}

}
