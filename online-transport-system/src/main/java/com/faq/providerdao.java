package com.faq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class providerdao {

    public boolean insertanswer(Faq faq) throws SQLException {
        boolean result = false;
        Connection con1 = null;
        PreparedStatement pst1 = null;
        System.out.println("---------------Hello --------------------");
        try {
        	System.out.println("---------------Start providerdao --------------------");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/TransportDB";
            String username = "root";
            String password = "it23440722@my.sliit.lk";

            con1 = DriverManager.getConnection(url, username, password);
            
           
            String Query = "UPDATE  faqs SET answer =(?) WHERE faq_id = (?)";
            pst1 = con1.prepareStatement(Query);
            
          
           
            pst1.setString(1, faq.getAnswer());
            pst1.setInt(2, faq.getId());
            
            System.out.println(faq.getAnswer());
            System.out.println(faq.getId());

            // Execute update
            int rows = pst1.executeUpdate();
            if (rows > 0) {
                result = true;
            }
            
           

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 

        System.out.println("--------------- providerdao --------------------");
        return result;
    }
}
