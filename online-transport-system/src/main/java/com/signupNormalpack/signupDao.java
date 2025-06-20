package com.signupNormalpack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class signupDao {
	public boolean UserNameExist(String username) {
			Connection con = null;
			boolean result = false;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TransportDB", "root", "it23440722@my.sliit.lk");			
				String query = "Select count(*) from RegisterDetails where userName=?";
				
				ps = con.prepareStatement(query);
				ps.setString(1, username);
				rs = ps.executeQuery();
				
				if( rs.next() ) {
					// it return the first column value
					result = rs.getInt(1) > 0 ;
				}
			} catch (ClassNotFoundException | SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // Close resources in the finally block to ensure they are closed even in case of an exception
		        try {
		            if (rs != null) rs.close();
		            if (ps != null) ps.close();
		            if (con != null) con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
			return result;
		}
		
		
		
		public boolean Insert(User user) throws SQLException {
			Connection con = null;
			boolean result = false;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TransportDB", "root", "it23440722@my.sliit.lk");			
				String query = "Insert into RegisterDetails( userName, gender, email, password, phone, role, address, comments) values(?,?,?,?,?,?,?,?)";
				
				PreparedStatement pst = con.prepareStatement(query);
										
				pst.setString(1, user.getUserName());
				pst.setString(2, user.getGender());
				pst.setString(3, user.getEmail());
				pst.setString(4, user.getPassword());
				pst.setString(5, user.getPhone());
				pst.setString(6, user.getRole());
				pst.setString(7, user.getAddress());
				pst.setString(8, user.getComments());
					        
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
	}
