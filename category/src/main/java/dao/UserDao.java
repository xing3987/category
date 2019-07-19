package dao;

import javaBean.User;

import java.sql.*;

public class UserDao {
    Connection conn = null;
    PreparedStatement ps = null;
    Statement st = null;
    ResultSet rs = null;

    /**
     * @param user
     */
    public void addUser(User user) {
        try {
            conn = DBUtils.getConnection();
            String sql = "insert into user_category values(null,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtils.closeConn(conn);
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


    /**
     * @param name
     * @param password
     */
    public boolean checkLogin(String name, String password) {
        boolean flag = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "select * from user_category where username=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtils.closeConn(conn);
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return flag;
    }
}
