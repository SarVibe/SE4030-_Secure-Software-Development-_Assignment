package com.DriverHome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DriverProfileDao {
	public HashMap<Integer,String> takeDriverDetails(String driverName) 
	{
	    Connection con = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    HashMap<Integer,String> driverData = new HashMap<>();
	    System.out.println("Hi");
	    try
        {
	       
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TransportDB", "root", "it23440722@my.sliit.lk");

	        String query = "SELECT phonenumber, address FROM driver_Details WHERE name = ?";
	        pst = con.prepareStatement(query);
	        pst.setString(1, driverName);
	        rs = pst.executeQuery();

	        
	        while (rs.next()) 
	        {
	            int phoneNumber = rs.getInt(1);
	            String address = rs.getString(2);
	            
	            driverData.put(phoneNumber, address);
	        }
        } catch (SQLException | ClassNotFoundException e) {
		        System.err.println("Error occurred: " + e.getMessage());
		} finally { 
		        try 
		        {
		            if (rs != null) rs.close();
		            if (pst != null) pst.close();
		            if (con != null) con.close();
		        } 
		        catch (SQLException e) 
		        {
		            System.err.println("Error closing resources: " + e.getMessage());
		        }
		  }
	      return driverData;
	}
	
}