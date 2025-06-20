package com.signupDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.InvalidUserDefinedException.*;

public class signupDriverDao {
	public boolean insert(Driver driver) throws SQLException , invalidUsername1Exception{
		
		Connection con = null;
		boolean result = false;
		
		ArrayList<String> names = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TransportDB", "root", "it23440722@my.sliit.lk");
			
			String query1 = "select name from driver_Details";
			
			PreparedStatement pst1 = con.prepareStatement(query1);
		
	        ResultSet rs = pst1.executeQuery(query1);

	        while (rs.next()) {
	        	names.add(rs.getString("name"));
	        }
	        	
	        	try {
	        	for (String nameCheck : names) {
	        	    if (driver.getName().equals(nameCheck)) {
	        	    	throw new invalidUsername1Exception("Username already exists");
	        	    }
	        	}
	        	}finally {
	        }
	        

			
			String query = "insert into driver_Details (name,address,phonenumber,vechilenumber,licensenumber,expirydate,password,confirmpassword) values(?,?,?,?,?,?,?,?)";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, driver.getName());
			pst.setString(2, driver.getAddress());
			pst.setInt(3, driver.getPhonenumber());
			pst.setString(4, driver.getVechilenumber());
			pst.setString(5, driver.getLicensenumber());
			pst.setDate(6, driver.getExpirydate());
			pst.setString(7, driver.getPassword());
			pst.setString(8, driver.getConfirmpassword());
			
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
