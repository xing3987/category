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
 * ��½
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		/**
		 * �ж���֤���Ƿ���ȷ
		 */
		HttpSession s=request.getSession();
    	String scheck=(String)s.getAttribute("checkCode");
    	String usecheck=request.getParameter("checkcode");
    	if(!scheck.equals(usecheck)){
    		request.setAttribute("checkcode_fail", "��֤�����");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    		return;
    	}
    	
    	/**
    	 * �ж��û��������Ƿ���ȷ
    	 */
    	String name=request.getParameter("name");
		String password=request.getParameter("password");
		String remember=request.getParameter("remember");
		UserDao userDao=new UserDao();
    	boolean flag=userDao.checkLogin(name,password);
		if(flag){
			s.setAttribute("user", name);//�O�õ�ꑺ�������C
			s.setMaxInactiveInterval(60*60*5);//�O��session����r�L
/*��session�����Ñ������ܴa
 * 			if(remember!=null){
				s.setAttribute("rightUser", name);
				s.setAttribute("rightPassword",password);
				s.setMaxInactiveInterval(60*60);
			}*/
			
/**��cookie�����Ñ������ܴa
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
			request.setAttribute("login_false","�û������������");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
