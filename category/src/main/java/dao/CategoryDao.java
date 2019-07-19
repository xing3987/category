package dao;

import javaBean.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Category> selectCategory(int i, int j) {
        List<Category> categorys = new ArrayList<Category>();
        try {
            conn = DBUtils.getConnection();
            String sql = "select * from category limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (i - 1) * j);
            ps.setInt(2, j);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setAddTime(rs.getString("addTime"));
                category.setCategoryType(rs.getString("categoryType"));
                category.setCategoryFrom(rs.getString("categoryFrom"));
                categorys.add(category);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtils.closeConn(conn);
        }


        return categorys;
    }

    public void deleCategory(int id) {
        try {
            conn = DBUtils.getConnection();
            String sql = "delete from category where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
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

    public void addCategory(String name, String addTime, String categoryType, String categoryFrom) {
        try {
            conn = DBUtils.getConnection();
            String sql = "insert into category values(null,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, addTime);
            ps.setString(3, categoryType);
            ps.setString(4, categoryFrom);
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

    public void updateCategory(int id, String name, String addTime, String categoryType, String categoryFrom) {
        try {
            conn = DBUtils.getConnection();
            String sql = "update category set name=?,addTime=?,categoryType=?,categoryFrom=? where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, addTime);
            ps.setString(3, categoryType);
            ps.setString(4, categoryFrom);
            ps.setInt(5, id);
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

}
