package com.signupNormalpack;

import com.util.XSSUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/signupStart")
public class SignupStart extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Pattern EMAIL = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    private static final Pattern PASSWORD = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%#*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String role = XSSUtils.sanitize(request.getParameter("role"));
        String email = XSSUtils.sanitize(request.getParameter("email"));
        String password = request.getParameter("password");
        if (!("Passenger".equals(role) || "Admin".equals(role) || "Driver".equals(role))) {
            request.setAttribute("errorMessage", "Invalid signup role.");
        } else if (!EMAIL.matcher(email).matches()) {
            request.setAttribute("errorMessage", "Enter a valid email address.");
        } else if (password == null || !PASSWORD.matcher(password).matches()) {
            request.setAttribute("errorMessage", "Password must be at least 8 characters and include uppercase, lowercase, a digit, and a special character.");
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("pendingSignupRole", role);
            session.setAttribute("pendingSignupEmail", email);
            session.setAttribute("pendingSignupPassword", password);
            response.sendRedirect("Driver".equals(role) ? "SignupDriver.jsp" : "signupNormal.jsp");
            return;
        }
        request.getRequestDispatcher("signupStart.jsp?role=" + role).forward(request, response);
    }
}