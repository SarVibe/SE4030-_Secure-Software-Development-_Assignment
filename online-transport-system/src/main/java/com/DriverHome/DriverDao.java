package com.DriverHome;

import com.Bookings.User;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

public class DriverDao {
	public HashMap<Integer,String> takeDriverDetails(String driverName) 
	{
	    Connection con = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    HashMap<Integer,String> driverData = new HashMap<>();
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
	
	
	
	
	public List<User> takeBookingDetails()
	{
	    List<User> bookings = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement pst2 = null;
	    ResultSet rs2 = null;

	    try 
	    {
	        
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TransportDB", "root", "it23440722@my.sliit.lk");

	        
	        String cus = "SELECT userName, pickupAddress, dropAddress, phoneNumber, pickUpTime FROM bookingDetails WHERE status = 'Request'";
	        pst2 = con.prepareStatement(cus);
	        rs2 = pst2.executeQuery();

	        
	        while (rs2.next()) 
	        {
	        	String passenger = rs2.getString(1);
	            String pickupAddress = rs2.getString(2);
	            String dropAddress = rs2.getString(3);
	            String phoneNumber = rs2.getString(4);
	            Time pickupTime = rs2.getTime(5);
	            
	            LocalTime pickTime = pickupTime.toLocalTime();
		       	           
	            bookings.add(new User(passenger, pickupAddress, dropAddress, phoneNumber, pickTime ));
	        }
	    } 
	    catch (ClassNotFoundException e) 
	    {
	        System.err.println("JDBC Driver not found: " + e.getMessage());
	    } 
	    catch (SQLException e) 
	    {
	        System.err.println("SQL error occurred: " + e.getMessage());
	    } 
	    finally
	    {
	        
	        try 
	        {
	            if (rs2 != null) rs2.close();
	            if (pst2 != null) pst2.close();
	            if (con != null) con.close();
	        } 
	        catch (SQLException e) 
	        {
	            System.err.println("Error closing resources: " + e.getMessage());
	        }
	    }

	    return bookings;
	}

}