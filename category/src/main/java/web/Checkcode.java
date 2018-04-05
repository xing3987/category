package web;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证码的servlet
 */
public class Checkcode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * 第一大步：绘图
		 */
			//step1:创建内存映像对象（画布）
			BufferedImage image=new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);
			//step2:获得画笔
			Graphics g=image.getGraphics();
			//step3:设置画笔颜色
			g.setColor(new Color(255,255,255));//设置画笔颜色rgb
			//step4:设置画布背景颜色
			g.fillRect(0, 0, 80, 30);
			//step5:给画笔重新设置颜色
			Random r=new Random();
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));//设置成随机颜色
			//step6:生成一个随机数
//			String number=r.nextInt(88888)+"";
			String number=getNumber(4);//创建一个获得字符的方法
			HttpSession s=request.getSession();
			s.setAttribute("checkCode", number);
			//step7:将画笔设置字体样式大小，将随机数添加到图片上
			g.setFont(new Font("宋体",Font.BOLD,24));
			g.drawString(number,5,22);//左下角的坐标
			//step8:画干扰线
			for(int i=0;i<10;i++){
				g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
				g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(30));
			}
		/**
		 * 第二大步：将图片压缩发送给浏览器
		 */
			//step1:告诉浏览器，服务器返回的是一张jpg格式的图片（jpeg的压缩算法）
			response.setContentType("image/jpeg");
			//step2:获得一个字节输出流
			OutputStream os=response.getOutputStream();
			//step3:将图片压缩，并输出
			//write方法会将原始图片（image）按照指定的压缩算法进行压缩
			//然后将压缩之后得到的字节通过os进行输出
			javax.imageio.ImageIO.write(image, "jpeg", os);
	}
	
	//生成一个字符串
	private String getNumber(int size){
		String number="";
		String base="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer sb=new StringBuffer();
		Random r=new Random();
		for(int i=0;i<size;i++){
			int index=r.nextInt(base.length());
			number=sb.append(base.charAt(index)).toString();
		}
		return number;
	}

}
