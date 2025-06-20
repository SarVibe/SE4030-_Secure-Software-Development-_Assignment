package com.Admins;



import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDetailsDao {
	 public static List<Admin> getAdmin() {

		   List<Admin> adminList = new ArrayList<>();
		   Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TransportDB", "root", "it23440722@my.sliit.lk");
			
			
				String query = "select userName, gender, email, phone, role, address, comments, status from RegisterDetails where userName like 'AD%' and status = 'pending'";
			
			
			
			
			PreparedStatement pst = con.prepareStatement(query);
		
	        ResultSet rs = pst.executeQuery(query);

	        while (rs.next()) {
	            String userName = rs.getString("userName");
	            String gender = rs.getString("gender");
	            String email = rs.getString("email");
	            String phone = rs.getString("phone");
	            String role = rs.getString("role");
	            String address = rs.getString("address");
	            String comments = rs.getString("comments");
	            String status = rs.getString("status");
	            
	            adminList.add(new Admin(userName, gender, email, phone, role, address, comments, status));

	    }
			
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		    return adminList;
		} catch (SQLException e) {
		    e.printStackTrace();  // To catch SQL-related errors
		    return adminList;
		}
		 
		return adminList;
	}
	}


