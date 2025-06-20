package com.Bookings;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;

import com.InvalidUserDefinedException.InvalidPhoneNumberException;
import com.InvalidUserDefinedException.InvalidPickUpTimeException;

/**
 * Servlet implementation class Booking
 */
@WebServlet("/Booking")
public class Booking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Booking() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalArgumentException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		// Get the session, but do not create a new one if it doesn't exist
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Retrieve a session variable (for example, "userName")
            String username = (String) session.getAttribute("userName");
        
		
		String pickupAddress = request.getParameter("pickup");
		String dropAddress = request.getParameter("drop");
		String phoneNumber = request.getParameter("phone");
		String pickupTimeString = request.getParameter("pickupTime");
		
		LocalTime pickupTime = LocalTime.parse(pickupTimeString);
		LocalTime curTime = LocalTime.now();
		
	     String errorPhoneNumber = null;
	     String errorPickupTime = null;
	    	 try {
	    		 if (!phoneNumber.matches("\\d{10}")) {
	                    throw new InvalidPhoneNumberException("Phone number must contain exactly 10 digits.");
	             }
	    		 else if (!phoneNumber.startsWith("0")) {
	                 throw new InvalidPhoneNumberException("Phone Number must start with 0.");
	             } 
	    		 else if ( !pickupTime.isAfter(curTime) ) {
	    			 throw new InvalidPickUpTimeException("Pickup time should be after the current time");
	    		 }
	          } catch (InvalidPhoneNumberException e) {
	        	    errorPhoneNumber = e.getMessage();
	        	    request.setAttribute("errorPhoneNumber", errorPhoneNumber);
		            request.setAttribute("pickup", pickupAddress);
		            request.setAttribute("drop", dropAddress);
		            request.setAttribute("pickupTime", pickupTimeString);
		            request.getRequestDispatcher("Booking.jsp").forward(request, response);
		            return;
	          } catch (InvalidPickUpTimeException e) {
	        	    errorPickupTime = e.getMessage();
	        	    request.setAttribute("errorPickupTime", errorPickupTime);
		            request.setAttribute("pickup", pickupAddress);
		            request.setAttribute("drop", dropAddress);
		            request.setAttribute("pickupTime", pickupTimeString);
		            request.getRequestDispatcher("Booking.jsp").forward(request, response);
		            return;
			  }
		
		User user = new User(username, pickupAddress, dropAddress, phoneNumber, pickupTime);
		
		BookingDao bookingDao = new BookingDao();
		
		try {
			if( bookingDao.Insert(user) ) {
				response.sendRedirect("BookingSuccess.jsp");
			} else {
				response.sendRedirect("Booking.jsp");
			}
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}
  }
}