package com.DriverHome;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class acceptORdenyservlet
 */
@WebServlet("/acceptORdenyservlet")
public class acceptORdenyservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public acceptORdenyservlet() {
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
		doGet(request, response);
		
		HttpSession session = request.getSession(false);
        String driverName = null;
        
        String UserNameBooking = request.getParameter("NameOfBookingUser");
        System.out.println(UserNameBooking);
        if (session != null) {
            // Retrieve the session variable "userName"
            driverName = (String) session.getAttribute("userName");
            System.out.println(driverName);  // For debugging purposes
            request.setAttribute("driverName", driverName);
        }
		
		String errorMessages = "Something went wrong! Please try again later";
		 
		acceptORdenyDAO accept = new acceptORdenyDAO();
		 try {
				if(accept.approveBooking(UserNameBooking,driverName)) {
					response.sendRedirect("DriverHome.jsp");
				}
				else {
					 request.setAttribute("errorMessages", errorMessages);
			            request.getRequestDispatcher("DriverHome.jsp").forward(request, response);
			            return;
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

}
