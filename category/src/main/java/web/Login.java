package web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * 登陆
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		/**
		 * 判断验证码是否正确
		 */
		HttpSession s=request.getSession();
    	String scheck=(String)s.getAttribute("checkCode");
    	String usecheck=request.getParameter("checkcode");
    	if(!scheck.equals(usecheck)){
    		request.setAttribute("checkcode_fail", "验证码错误");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    		return;
    	}
    	
    	/**
    	 * 判断用户名密码是否正确
    	 */
    	String name=request.getParameter("name");
		String password=request.getParameter("password");
		String remember=request.getParameter("remember");
		UserDao userDao=new UserDao();
    	boolean flag=userDao.checkLogin(name,password);
		if(flag){
			s.setAttribute("user", name);//設置登陸后的頁面驗證
			s.setMaxInactiveInterval(60*60*5);//設置session保存時長
/*用session來保存用戶名和密碼
 * 			if(remember!=null){
				s.setAttribute("rightUser", name);
				s.setAttribute("rightPassword",password);
				s.setMaxInactiveInterval(60*60);
			}*/
			
/**用cookie來保存用戶名和密碼
 */	
			if(remember!=null){
				Cookie c1=new Cookie("rightUser",name);
				c1.setMaxAge(60*60*24);
				Cookie c2=new Cookie("rightPassword",password);
				c2.setMaxAge(60*60*24);
				response.addCookie(c1);
				response.addCookie(c2);
			}
			response.sendRedirect(request.getContextPath()+"/CategoryList");
		}else{
			request.setAttribute("login_false","用户名或密码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
