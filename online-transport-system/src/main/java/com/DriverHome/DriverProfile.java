package com.DriverHome;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class DriverProfile
 */
@WebServlet("/DriverProfile")
public class DriverProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 // Get the session, but do not create a new one if it doesn't exist
        HttpSession session = request.getSession(false);
        String driverName = null;

        if (session != null) {
            // Retrieve the session variable "driverName"
            driverName = (String) session.getAttribute("userName");
            System.out.println(driverName);  // For debugging purposes
            request.setAttribute("driverName", driverName);
        }

        DriverProfileDao driver = new DriverProfileDao();

        // Get driver details using the name from the session
        HashMap<Integer, String> driverDetails = driver.takeDriverDetails(driverName);
        for (Map.Entry<Integer, String> entry : driverDetails.entrySet()) {
            // Set attributes for phone number and address
            request.setAttribute("phoneNumber", entry.getKey());
            request.setAttribute("address", entry.getValue());
        }
        request.getRequestDispatcher("DriverProfile.jsp").forward(request, response);
	}

}