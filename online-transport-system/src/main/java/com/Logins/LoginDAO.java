package com.Logins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.PasswordUtils;

public class LoginDAO {
    public boolean validateUser(String un, String pw) {
        boolean status = false;
        Connection con = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = com.util.DBConfig.getConnection();
            String query = null;

           
            if (un.startsWith("CT")) {
                query = "SELECT password FROM RegisterDetails WHERE userName = ?";
            } else if (un.startsWith("DD")) {
                query = "SELECT password FROM driver_Details WHERE name = ? AND status = 'Accepted'";
            } else if (un.startsWith("AD")) {
                query = "SELECT password FROM RegisterDetails WHERE userName = ? AND status = 'Accepted'";
            }

            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, un);

            rs = pst.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                status = PasswordUtils.verifyPassword(storedHash, pw);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }
}