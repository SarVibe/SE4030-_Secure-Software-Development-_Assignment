package com.Logins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private String url = "jdbc:mysql://localhost:3306/TransportDB";
    private String username = "root";
    private String password = "it23440722@my.sliit.lk";

    public boolean validateUser(String un, String pw) {
        boolean status = false;
        Connection con = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String query = null;
    		
    		if( un.startsWith("CT") ) {
    			query = "SELECT * FROM RegisterDetails WHERE userName = ? AND password = ?";
    		} else if( un.startsWith("DD") ) {
    			query = "SELECT * FROM driver_Details WHERE name = ? AND password = ? AND status = 'Accepted'";
    		} else if( un.startsWith("AD") ) {
    			query = "SELECT * FROM RegisterDetails WHERE userName = ? AND password = ? AND status = 'Accepted'";
    		}
    		
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, un);
            pst.setString(2, pw);

            rs = pst.executeQuery();
            status = rs.next();

            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }
}