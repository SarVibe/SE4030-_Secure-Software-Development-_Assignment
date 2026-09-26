package com.Logins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

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
                query = "SELECT * FROM RegisterDetails WHERE userName = ? AND password = ?";
            } else if (un.startsWith("DD")) {
                query = "SELECT * FROM driver_Details WHERE name = ? AND password = ? AND status = 'Accepted'";
            } else if (un.startsWith("AD")) {
                query = "SELECT * FROM RegisterDetails WHERE userName = ? AND password = ? AND status = 'Accepted'";
            }

            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, un);
            pst.setString(2, hashPassword(pw));

            rs = pst.executeQuery();
            status = rs.next();

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

    private String hashPassword(String plainTextPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(plainTextPassword.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}