package com.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Date;


public class DeleteDriverDao {


public boolean deleteDriver(String name) throws SQLException {
	Connection con = null;
	boolean result = false;
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TransportDB", "root", "it23440722@my.sliit.lk");
		
		String query = "delete from driver_Details where name = ?";
		
		PreparedStatement pst = con.prepareStatement(query);
		
		pst.setString(1, name);
		
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

