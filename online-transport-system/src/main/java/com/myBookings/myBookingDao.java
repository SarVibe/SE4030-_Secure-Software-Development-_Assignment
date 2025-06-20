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
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TransportDB", "root", "it23440722@my.sliit.lk");
			
			String query = "select BD.pickupAddress, BD.dropAddress, BD.pickUpTime, BD.bookDate, BD.DriverName, PD.Amount "
					+ "from bookingDetails BD, PaymentDetails PD "
					+ "where BD.userName = PD.userName and PD.payDate = BD.bookDate and BD.userName= ? and BD.status = ?";
			
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, "Accepted");
		
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	        	int Amount = rs.getInt("Amount");
	            String pickupAddress = rs.getString("pickupAddress");
	            String dropAddress = rs.getString("dropAddress");
	            Time pickTime = rs.getTime("pickUpTime");
	            Date bookDate = rs.getDate("bookDate");
	            String DriverName = rs.getString("DriverName");
	            	            
	            LocalTime pickUpTime = pickTime.toLocalTime();
	            
	            bookingList.add( new User( pickupAddress, dropAddress, pickUpTime, bookDate, DriverName, Amount ) );

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