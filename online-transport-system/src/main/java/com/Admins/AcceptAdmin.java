package com.Admins;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class AcceptAdmin
 */
@WebServlet("/AcceptAdmin")
public class AcceptAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AcceptAdmin.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AcceptAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		String name = request.getParameter("allow");
		String errorMessages = "Something went wrong! Please try again later";

		AcceptAdminDao acceptadmindao = new AcceptAdminDao();

		try {
			if (acceptadmindao.approveAdmin(name)) {
				response.sendRedirect("UpdateAdmin");
			} else {
				request.setAttribute("errorMessages", errorMessages);
				request.getRequestDispatcher("adminList.jsp").forward(request, response);
				return;

			}
		} catch (SQLException e) {
			// 2. Log the error securely (this goes to a log file, not the user)
			logger.log(Level.SEVERE, "A database error occurred while accepting the admin.", e);

			// 3. Handle the error appropriately.
			// Option A: Re-throw a generic custom exception
			throw new RuntimeException("An internal error occurred. Please try again later.");

		}

	}
}