package web;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * login
 */
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        /**
         * login
         */
        HttpSession s = request.getSession();
        String scheck = (String) s.getAttribute("checkCode");
        String usecheck = request.getParameter("checkcode");
        if (scheck == null ) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }else if(!scheck.equals(usecheck)){
            request.setAttribute("checkcode_fail", "check the code");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        s.removeAttribute("checkCode");

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        UserDao userDao = new UserDao();
        boolean flag = userDao.checkLogin(name, password);
        if (flag) {
            s.setAttribute("user", name);
            s.setMaxInactiveInterval(60 * 60 * 5);//set session time
            /*if (remember != null) {
                s.setAttribute("rightUser", name);
                s.setAttribute("rightPassword", password);
                s.setMaxInactiveInterval(60 * 60);
            }*/

            /**
             * save to cookies
             */
            if (remember != null) {
                Cookie c1 = new Cookie("rightUser", name);
                c1.setMaxAge(60 * 60 * 24);
                Cookie c2 = new Cookie("rightPassword", password);
                c2.setMaxAge(60 * 60 * 24);
                response.addCookie(c1);
                response.addCookie(c2);
            }
            response.sendRedirect(request.getContextPath() + "/CategoryList");
        } else {
            request.setAttribute("login_false", "login failed..");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
