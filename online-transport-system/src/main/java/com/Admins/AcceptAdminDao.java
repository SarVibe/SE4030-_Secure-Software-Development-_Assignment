package com.Admins;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AcceptAdminDao {
	public boolean approveAdmin(String name) throws SQLException {
		
		Connection con = null;
		boolean result = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TransportDB", "root", "it23440722@my.sliit.lk");
			
			String query = "UPDATE RegisterDetails SET status = ?, role = ? WHERE userName = ?";

			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, "Accepted");
			pst.setString(2, "Admin");
			pst.setString(3, name);
			
			int rowsAffected = pst.executeUpdate();
		    
		    result = (rowsAffected > 0);
			
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		    return result;
		} catch (SQLException e) {
		    e.printStackTrace();  // To catch SQL-related errors
		    return result;
		}
		
		return result;
	}
	}

