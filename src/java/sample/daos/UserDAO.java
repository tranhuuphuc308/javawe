/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.dtos.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author ROG STRIX
 */
public class UserDAO {

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //ket noi db
            conn = DBUtils.getConnection();
            //viet lenh sql
            if (conn != null) {
                String sql = "select fullName, roleID from tblUsers where userID =? AND password =?";
                //dua lenh tu java vao DB
                stm = conn.prepareStatement(sql);
                //truyen userID va pass vao ?
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String roleID = rs.getString("roleID");
                    String fullName = rs.getString("fullName");
                    user = new UserDTO(userID, fullName, "", roleID);
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public List<UserDTO> getlistUser(String search) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;//result đại diện cho dữ liệu dạng bảng 
        // được các câu lệnh SQL trả về

        //ResultSet giữ data dạng bảng được
        // trả về bởi phương thức executeQuery
        try {
            conn = DBUtils.getConnection();
            String sql = "select userID, fullName, roleID from tblUsers where fullName like ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + search + "%");
            rs = stm.executeQuery();//thực hiện câu lệnh > trả về data dạng bảng
            while (rs.next()) {// rs.next() trả về true or false
                //lần đầu tiên con trỏ next trỏ tới hàng đâu tiên trong kp trả về 
                String userID = rs.getString("userID");
                String fullName = rs.getString("fullName");
                String roleID = rs.getString("roleID");
                String password = "****";
                list.add(new UserDTO(userID, fullName, password, roleID));

            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean deleteUser(String userID) throws SQLException {
        boolean res = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "delete tblUsers where userId=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, userID);
            int value = stm.executeUpdate();//thực thi đúng trả về giá trị >0
            res = value > 0 ? true : false;
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return res;

    }

    public boolean updateUser(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "update tblUsers set fullName=?, roleID=? where userID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, user.getFullName());
            stm.setString(2, user.getRoleID());
            stm.setString(3, user.getUserID());
            check = stm.executeUpdate() > 0;
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }

    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "select userID from tblUsers where userID =?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, userID);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public boolean insert(UserDTO user) throws SQLException{
        boolean check = false;
        Connection conn=null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            String sql="insert into tblUsers(fullName, roleID, userID, password) values(?,?,?,?)";
            stm=conn.prepareStatement(sql);
            stm.setString(1, user.getFullName());
            stm.setString(2, user.getRoleID());
            stm.setString(3, user.getUserID());
            stm.setString(4, user.getPassword());
            check = stm.executeUpdate()>0;
        } catch (Exception e) {
        }finally{
        if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        
        }
        return check;
    }
}
