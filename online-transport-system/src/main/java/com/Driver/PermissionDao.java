package com.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Date;

import java.util.*;

import com.signupDriver.Driver;

public class PermissionDao {

	
	
	 public static List<Driver> getDriver() {

	   List<Driver> driverList = new ArrayList<>();
	   Connection con = null;
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TransportDB", "root", "it23440722@my.sliit.lk");
		
		String query = "select * from driver_Details where status = 'pending'";
		
		PreparedStatement pst = con.prepareStatement(query);
	
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name");
            String address = rs.getString("address");
            int phonenumber = rs.getInt("phonenumber");
            String vechilenumber = rs.getString("vechilenumber");
            String licensenumber = rs.getString("licensenumber");
            Date expirydate = rs.getDate("expirydate");
            String password = rs.getString("password");
            String confirmpassword = rs.getString("confirmpassword");
            
            driverList.add(new Driver(name,address,phonenumber,vechilenumber,licensenumber,expirydate,password,confirmpassword));

    }
		
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	    return driverList;
	} catch (SQLException e) {
	    e.printStackTrace();  // To catch SQL-related errors
	    return driverList;
	}
	 
	return driverList;
}
}
