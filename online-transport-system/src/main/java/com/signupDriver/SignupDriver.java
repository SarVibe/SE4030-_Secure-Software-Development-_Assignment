package com.signupDriver;


import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Date; 
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;


import com.InvalidUserDefinedException.*;

/**
 * Servlet implementation class SignupDriver
 */
@WebServlet("/SignupDriver")
public class SignupDriver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupDriver() {
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
		//doGet(request, response);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phonenumberString = request.getParameter("phonenumber");
		int phonenumber = Integer.parseInt(phonenumberString);
		String vechilenumber = request.getParameter("vechilenumber");
		String licensenumber = request.getParameter("licensenumber");
		String expiryDateString = request.getParameter("expirydate");
		String password = request.getParameter("password");
		String confirmpassword = request.getParameter("confirmpassword");
        
        java.sql.Date expiryDate = java.sql.Date.valueOf(expiryDateString);
        
        Date currentDate = new Date();
        
        java.sql.Date currentSqlDate = new java.sql.Date(currentDate.getTime());
        
        List<String> errorMessages = new ArrayList<>();
        
        try {
        	if (!name.startsWith("DD")) {
                throw new InvalidUserNameException("Username must start with DD");
            }
        	else if (!phonenumberString.startsWith("0")) {
                throw new InvalidPhoneNumberException("Phone Number must start with 0");
            }
        	else if (!vechilenumber.matches("^[A-Z]{2}\\s[A-Z]{3}\\s\\d{4}$")) {
  		      throw new invalidVechileNumberException("Invalid Vechile Number");
  		    }
        	else if (!licensenumber.matches("^[A-Z]{1}[0-9]{7}$")) {
    		      throw new invalidLicenceNumberException("Invalid Licence Number");
    		}
        	else if (expiryDate.before(currentSqlDate)) {
  		      throw new invalidLicenceNumberException("You're Licenece already expired");
  		    }
        	else if (!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
    		      throw new InvalidPasswordException("Password must be atleast 8 characters long and include uppercase, lowercase, a digit, and a special character");
    		}
        	else if (!confirmpassword.equals(password)) {
  		      throw new invalidConfirmPasswordException("Password not match");
  		    }
        }catch(InvalidUserNameException e) {
        	errorMessages.add(e.getMessage());
        }catch(InvalidPhoneNumberException e) {
        	errorMessages.add(e.getMessage());
        }catch(invalidVechileNumberException e) {
        	errorMessages.add(e.getMessage());
        }catch(invalidLicenceNumberException e) {
        	errorMessages.add(e.getMessage());
        }catch(InvalidPasswordException e) {
        	errorMessages.add(e.getMessage());
        }catch(invalidConfirmPasswordException e) {
        	errorMessages.add(e.getMessage());
        }
        
        if (!errorMessages.isEmpty()) {
            request.setAttribute("errorMessages", errorMessages);
            request.getRequestDispatcher("SignupDriver.jsp").forward(request, response);
            return;
        }
		
		Driver driver = new Driver(name , address , phonenumber , vechilenumber , licensenumber , expiryDate , password , confirmpassword);
		
		signupDriverDao cruddao = new signupDriverDao();
		
		try {
			if(cruddao.insert(driver)) {
				HttpSession session = request.getSession(); 
	            // Store the user information in the session
	            session.setAttribute("Name", name);
	              
		    	Cookie userNameCookie = new Cookie("Name", name);
		    	userNameCookie.setMaxAge(86400 * 30);
		    	  
				response.sendRedirect("RegisterSuccessDriver.jsp");
			}
			else {
				response.sendRedirect("SignupDriver.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(invalidUsername1Exception e) {
        	errorMessages.add("Username already exists");
        }
		
		if (!errorMessages.isEmpty()) {
            request.setAttribute("errorMessages", errorMessages);
            request.getRequestDispatcher("SignupDriver.jsp").forward(request, response);
            return;
        }
		
	}

}
