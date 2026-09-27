package com.myBookings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalTime;


import com.Bookings.User;

public class myBookingDao {
	 public static List<User> getMyBookings(String username) {

		   List<User> bookingList = new ArrayList<>();
		   Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = com.util.DBConfig.getConnection();
			
			String query = "SELECT BD.pickupAddress, BD.dropAddress, BD.pickUpTime, BD.bookDate, " +
					"BD.DriverName, BD.status, COALESCE(PD.Amount, 0) AS Amount " +
					"FROM bookingDetails BD " +
					"LEFT JOIN PaymentDetails PD ON BD.userName = PD.userName AND PD.payDate = BD.bookDate " +
					"WHERE BD.userName = ? " +
					"ORDER BY BD.bookDate DESC";
			
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);
		
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	        	int Amount = rs.getInt("Amount");
	            String pickupAddress = rs.getString("pickupAddress");
	            String dropAddress = rs.getString("dropAddress");
	            Time pickTime = rs.getTime("pickUpTime");
	            Date bookDate = rs.getDate("bookDate");
	            String DriverName = rs.getString("DriverName");
	            String status = rs.getString("status");
	            	            
	            LocalTime pickUpTime = pickTime.toLocalTime();
	            
	            bookingList.add( new User( pickupAddress, dropAddress, pickUpTime, bookDate, DriverName, Amount, status ) );

	    }
			
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		    return bookingList;
		} catch (SQLException e) {
		    e.printStackTrace();
		    return bookingList;
		}
		 
		return bookingList;
	}
}