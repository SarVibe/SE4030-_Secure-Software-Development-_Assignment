package com.DriverHome;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Bookings.User;

@WebServlet("/DriverServlet")
public class DriverServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	
	}

    // Handling POST request
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get the session, but do not create a new one if it doesn't exist
        HttpSession session = request.getSession(false);
        String driverName = null;

        if (session != null) {
            // Retrieve the session variable "driverName"
            driverName = (String) session.getAttribute("userName");
            request.setAttribute("driverName", driverName);
        }
        
        DriverDao driver = new DriverDao();

        // Get driver details using the name from the session
        HashMap<Integer, String> driverDetails = driver.takeDriverDetails(driverName);
        for (Map.Entry<Integer, String> entry : driverDetails.entrySet()) {
            // Set attributes for phone number and address
            request.setAttribute("phoneNumber", entry.getKey());
            request.setAttribute("address", entry.getValue());
        }
     

        // Forward the request to DriverHome.jsp
        request.getRequestDispatcher("DriverHome.jsp").forward(request, response);
    }
}