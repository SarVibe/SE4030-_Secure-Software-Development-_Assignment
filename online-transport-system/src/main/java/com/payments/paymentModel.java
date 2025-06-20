package com.payments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class paymentModel 
{
	public boolean insert(Payment p) throws SQLException
	{
		    Connection con = null;
	        boolean result = false;

	        try 
	        {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TransportDB", "root", "it23440722@my.sliit.lk");

	            String query = "INSERT INTO PaymentDetails(UserName, Amount, Method) VALUES(?, ?, ?)";

	            PreparedStatement pst = con.prepareStatement(query);
	            pst.setString(1, p.getUserName());
	            pst.setInt(2, p.getAmount());
	            pst.setString(3, p.getPaymentMethod());
	            

	            // Use executeUpdate() for INSERT statements
	            int rowsAffected = pst.executeUpdate();

	            // If rows were inserted, set result to true
	            if (rowsAffected > 0) {
	                result = true;
	              
	              //  System.out.println("Execute success fully");
	            }
	        } 
	        catch (ClassNotFoundException e) 
	        {
	            e.printStackTrace();
	           // System.out.println("ClassNotFoundException");
	        } 
	        finally 
	        {
	            if (con != null) 
	            {
	                try 
	                {
	                    con.close();
	                } 
	                catch (SQLException e) 
	                {
	                    e.printStackTrace();
	                }
	            }
	        }

		return result;
	}
}
