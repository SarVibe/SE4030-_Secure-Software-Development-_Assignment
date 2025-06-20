package com.Logins;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class Forgotservlet
 */
@WebServlet("/Forgotservlet")
public class Forgotservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String url = "jdbc:mysql://localhost:3306/TransportDB";
    String username = "root";
    String password = "it23440722@my.sliit.lk";
    Connection con = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forgotservlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String un = request.getParameter("Username").trim();
        String npw = request.getParameter("NewPassword").trim();
        String opw = request.getParameter("ComPassword").trim();

       
        try {
            isvalidpassword(npw);
            checkpassword(npw, opw);
        } catch (InvalidPasswordException e) {
            request.setAttribute("errorMessage3", e.getmsg());
            request.getRequestDispatcher("ForgotLogin.jsp").forward(request, response);
            return; 
        } catch (Matchpassword m) {
            request.setAttribute("errorMessage1", m.getmsg());
            request.getRequestDispatcher("ForgotLogin.jsp").forward(request, response);
            return; 
        }

        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);

            String query = "UPDATE RegisterDetails SET password = ? WHERE userName = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, npw);
            pst.setString(2, un);

            int row = pst.executeUpdate(); // Execute the update

            if (row > 0) {
                request.getSession().setAttribute("userName", un);
                response.sendRedirect("Login.jsp"); // Redirect after success
            } else {
                request.setAttribute("errorMessage2", "Invalid Username, Please try again.");
                request.getRequestDispatcher("ForgotLogin.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.getRequestDispatcher("ForgotLogin.jsp").forward(request, response);
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

   
    public static void checkpassword(String password, String che_password) throws Matchpassword {
        if (!password.equals(che_password)) { 
            throw new Matchpassword("The passwords not match.");
        }
    }

    
    public static void isvalidpassword(String password) throws InvalidPasswordException {
        if (!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"))
            throw new InvalidPasswordException("Password must be 8+ chars, uppercase, lowercase, digit, special char");
    }
}

