package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;


public class DBUtils {
	private static String driver;
	private static String user;
	private static String url;
	private static String password;
	private static int init;
	private static int max;
	private static BasicDataSource bds;
	
	static{
		Properties prom=new Properties();
		InputStream is=DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			prom.load(is);
			driver=prom.getProperty("driver");
			url=prom.getProperty("url");
			password=prom.getProperty("password");
			user=prom.getProperty("user");
			init=Integer.parseInt(prom.getProperty("init"));
			max=Integer.parseInt(prom.getProperty("max"));
			bds=new BasicDataSource();
			bds.setDriverClassName(driver);
			bds.setUrl(url);
			bds.setUsername(user);
			bds.setPassword(password);
			bds.setInitialSize(init);
			bds.setMaxActive(max);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		Connection conn=bds.getConnection();	
		return conn;		
	}
	
	public static void closeConn(Connection conn){
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
