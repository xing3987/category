package web;

import helper.PropertiesHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Random;


public class Checkcode extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //step1:设置图片大小格式
        BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
        //step2:获取画笔
        Graphics g = image.getGraphics();
        //step3:设置画板背景色
        g.setColor(new Color(255, 255, 255));
        //step4:画出画板
        g.fillRect(0, 0, 80, 30);
        //step5:设置画笔
        Random r = new Random();
        g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        //step6:获取验证码
        String number = getNumber(4);
        request.getSession().setAttribute("checkCode", number);
        //step7:写出验证码
        g.setFont(new Font("宋体", Font.BOLD, 24));
        g.drawString(number, 5, 22);
        //step8:画十条模糊线
        for (int i = 0; i < 10; i++) {
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(30));
        }
        //step1:
        response.setContentType("image/jpeg");
        //step2:
        OutputStream os = response.getOutputStream();
        //step3:
        javax.imageio.ImageIO.write(image, "jpeg", os);
    }

    private String getNumber(int size) throws IOException {
        String number = "";
        String base = PropertiesHelper.getPros().getProperty("check.code.base.word");
        StringBuffer sb = new StringBuffer();
        SecureRandom r = new SecureRandom();
        for (int i = 0; i < size; i++) {
            int index = r.nextInt(base.length());
            number = sb.append(base.charAt(index)).toString();
        }
        return number;
    }

}
