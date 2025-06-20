package com.faq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Faqdao {
	
	public boolean insertdata(Faq f1) throws SQLException {
		boolean result = false;
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded successfully.");
		    
			String url = "jdbc:mysql://localhost:3306/TransportDB";
			String username = "root";
			String password = "it23440722@my.sliit.lk";
	        
			con = DriverManager.getConnection(url, username, password);
	        
			String query = "INSERT INTO faqs(question) VALUES(?)";
			pst = con.prepareStatement(query);  // Changed to prepareStatement()
			pst.setString(1, f1.getQuestion());
	        
			int rowsAffected = pst.executeUpdate();
	        
			if (rowsAffected > 0) {
				result = true;  // Insert successful
				System.out.println("FAQ inserted successfully.");
				System.out.println("Total rows affected: " + rowsAffected);
			}
	        
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Closing resources
			if (pst != null) {
				pst.close();
			}
			if (con != null) {
				con.close();
			}
		}
		
		return result;
	}
}
