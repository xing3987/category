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
 * ��֤���servlet
 */
public class Checkcode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * ��һ�󲽣���ͼ
		 */
			//step1:�����ڴ�ӳ����󣨻�����
			BufferedImage image=new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);
			//step2:��û���
			Graphics g=image.getGraphics();
			//step3:���û�����ɫ
			g.setColor(new Color(255,255,255));//���û�����ɫrgb
			//step4:���û���������ɫ
			g.fillRect(0, 0, 80, 30);
			//step5:����������������ɫ
			Random r=new Random();
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));//���ó������ɫ
			//step6:����һ�������
//			String number=r.nextInt(88888)+"";
			String number=getNumber(4);//����һ������ַ��ķ���
			HttpSession s=request.getSession();
			s.setAttribute("checkCode", number);
			//step7:����������������ʽ��С�����������ӵ�ͼƬ��
			g.setFont(new Font("����",Font.BOLD,24));
			g.drawString(number,5,22);//���½ǵ�����
			//step8:��������
			for(int i=0;i<10;i++){
				g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
				g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(30));
			}
		/**
		 * �ڶ��󲽣���ͼƬѹ�����͸������
		 */
			//step1:��������������������ص���һ��jpg��ʽ��ͼƬ��jpeg��ѹ���㷨��
			response.setContentType("image/jpeg");
			//step2:���һ���ֽ������
			OutputStream os=response.getOutputStream();
			//step3:��ͼƬѹ���������
			//write�����ὫԭʼͼƬ��image������ָ����ѹ���㷨����ѹ��
			//Ȼ��ѹ��֮��õ����ֽ�ͨ��os�������
			javax.imageio.ImageIO.write(image, "jpeg", os);
	}
	
	//����һ���ַ���
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
