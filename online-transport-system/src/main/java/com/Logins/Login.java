package com.Logins;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String un = request.getParameter("Username");
        String pw = request.getParameter("password");

        UserValidator userValidator = new UserValidator();
        
        // Validate username and password
        if (!userValidator.validate(un, pw)) {
            request.setAttribute("errorMessage", "Invalid input. Please check your username and password.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return; // Stop further processing
        }

        LoginDAO loginDAO = new LoginDAO();
        boolean isValidUser = loginDAO.validateUser(un, pw);

        if (isValidUser) {
            request.getSession().setAttribute("userName", un);
            if( un.startsWith("CT") ) {
            	 response.sendRedirect("cusHome.jsp");
    		} else if( un.startsWith("DD") ) {
    			 response.sendRedirect("DriverHomeServlet");
    		} else if( un.startsWith("AD") ) {
    			 response.sendRedirect("AdminHome.jsp");
    		}
           
        } else {
            request.setAttribute("errorMessage", "Invalid Login, Please try again.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}