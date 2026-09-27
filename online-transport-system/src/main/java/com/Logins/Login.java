package com.Logins;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.util.XSSUtils;


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
        // --- XSS Sanitization: sanitize username before use ---
        String un = XSSUtils.sanitize(request.getParameter("Username"));
        // Password is NOT sanitized - it is never rendered in HTML output.
        String pw = request.getParameter("password");


        UserValidator userValidator = new UserValidator();
        
        // Validate username and password
        if (!userValidator.validate(un, pw)) {
            request.setAttribute("errorMessage", "Invalid input. Please check your username and password.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return; // Stop further processing
        }

        LoginDAO loginDAO = new LoginDAO();
        LoginDAO.AuthenticationResult authenticationResult = loginDAO.authenticateUser(un, pw);

        if (authenticationResult == LoginDAO.AuthenticationResult.SUCCESS) {
            request.getSession().setAttribute("userName", un);
            if( un.startsWith("CT") ) {
            	 response.sendRedirect("cusHome.jsp");
    		} else if( un.startsWith("DD") ) {
    			 response.sendRedirect("DriverHomeServlet");
    		} else if( un.startsWith("AD") ) {
    			 response.sendRedirect("AdminHome.jsp");
    		}
           
        } else {
            String errorMessage = authenticationResult == LoginDAO.AuthenticationResult.LOCKED
                    ? "Too many failed login attempts. Login is disabled for 5 minutes."
                    : "Invalid Login, Please try again.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}