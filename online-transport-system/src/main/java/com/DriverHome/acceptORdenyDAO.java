package com.DriverHome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class acceptORdenyDAO {
	public boolean approveBooking(String UserNameBooking , String driverName) throws SQLException {
		
		Connection con = null;
		boolean result = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TransportDB", "root", "it23440722@my.sliit.lk");
			
			String query = "UPDATE bookingDetails SET status = ? , DriverName = ? where userName = ? and bookDate = current_date()";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, "Accepted");
			pst.setString(2, driverName);
			pst.setString(3, UserNameBooking);

			
			int rowsAffected = pst.executeUpdate();
		    
		    result = (rowsAffected > 0);
		    
		    System.out.println("Hi");
			
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
