package com.Bookings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;


public class BookingDao {
	public boolean Insert(User user) throws SQLException {
		Connection con = null;
		boolean result = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TransportDB", "root", "it23440722@my.sliit.lk");			
			String query = "Insert into bookingDetails( userName, pickupAddress, dropAddress, phoneNumber, pickUpTime) values(?,?,?,?,?)";
			
			PreparedStatement pst = con.prepareStatement(query);
								
			pst.setString(1, user.getUserName());
			pst.setString(2, user.getPickupAddress());
			pst.setString(3, user.getDropAddress());
			pst.setString(4, user.getPhoneNumber());
			
			LocalTime pickupTime = user.getPickUpTime();
	        Time sqlTime = Time.valueOf(pickupTime);
	        pst.setTime(5, sqlTime);
	        
	        int rowsAffected = pst.executeUpdate();
	        
	        result = (rowsAffected > 0);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return result;
		} finally {
			try {
				if (con != null && !con.isClosed() ) {
		            con.close();
		        }
			} catch( SQLException e ) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public String displayDriver(String userName) throws SQLException, ClassNotFoundException{
		Connection con = null;
		String displayDriverDetails = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TransportDB", "root", "it23440722@my.sliit.lk");			
			
	        String query =  "SELECT DD.name, DD.phoneNumber " +
		                    "FROM driver_Details DD JOIN bookingDetails BD " +
		                    "ON DD.name = BD.DriverName " +
		                    "WHERE BD.userName = ? " +
		                    "AND BD.bookDate = CURRENT_DATE " + 
		                    "AND BD.status = 'Accepted'";
	        
			PreparedStatement pst = con.prepareStatement(query);				
			pst.setString(1, userName);
			
			ResultSet rs = pst.executeQuery();
			
			while( rs.next() ) {
				String driverName = rs.getString("name");
			    String phoneNumber = rs.getString("phoneNumber");
			    
			    displayDriverDetails = "Driver Name : "+driverName+" and Phone Number : "+phoneNumber;
			}
		} catch (SQLException | ClassNotFoundException e ) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null && !con.isClosed() ) {
		            con.close();
		        }
			} catch( SQLException e ) {
				e.printStackTrace();
			}
		}
		return displayDriverDetails;
	}
}
