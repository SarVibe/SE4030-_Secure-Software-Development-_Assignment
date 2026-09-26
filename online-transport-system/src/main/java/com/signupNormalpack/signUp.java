package com.signupNormalpack;


import com.InvalidUserDefinedException.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.util.XSSUtils;

/**
 * Servlet implementation class signUp
 */
@WebServlet("/signUp")
public class signUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		// --- XSS Sanitization: sanitize all text inputs before use ---
		String userName = XSSUtils.sanitize(request.getParameter("userName"));
		String gender   = XSSUtils.sanitize(request.getParameter("gender"));
		String email    = XSSUtils.sanitize(request.getParameter("email"));
		// Password is NOT sanitized here - it is validated via strict regex.
		String password = request.getParameter("password");
		String phone    = XSSUtils.sanitize(request.getParameter("phone"));
		String role     = XSSUtils.sanitize(request.getParameter("role"));
		String address  = XSSUtils.sanitize(request.getParameter("address"));
		String comments = XSSUtils.sanitize(request.getParameter("comments"));		
		

	     String errorPassword = null;
	     String errorPhoneNumber = null;
	     String errorName = null;
	     String errorNameExist = null;
	     
	     signupDao registerDao = new signupDao();
	     
	    	 try {
		    		 if( registerDao.UserNameExist(userName) ) {
		    			 throw new UserNameExistException("User Name already exists. Please choose another one.");
		    			 
		    		 } else if( role.equals("Passenger") &&  !userName.startsWith("CT") ) {
		    			 throw new InvalidUserNameException("If you are a Passenger, Passenger UserName Should Start with 'CT'");
		    			 
		    		 } else if( role.equals("Admin") &&  !userName.startsWith("AD")  ) {
		    			 throw new InvalidUserNameException("If you are an Admin, Admin userName Should Start with 'AD'");
		    		 
		    		 } else if (!phone.matches("\\d{10}")) {
		                    throw new InvalidPhoneNumberException("Phone number must contain exactly 10 digits.");
		             }
		    		 else if (!phone.startsWith("0")) {
		                 throw new InvalidPhoneNumberException("Phone Number must start with 0.");
		             }
		    		 else if (!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%#*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
		    		      throw new InvalidPasswordException("Password must be atleast 8 characters long and include uppercase, lowercase, a digit, and a special character.");
		    		 }
	          }  catch (UserNameExistException e) {
	        	    errorNameExist = e.getMessage();
	        	    request.setAttribute("errorNameExist", errorNameExist);
	                forwardToSignupPage(request, response, userName, email, role, gender, phone, address, comments);
	                return;
			  }  catch ( InvalidUserNameException e ) {
	        	    errorName = e.getMessage();
	        	    request.setAttribute("errorName", errorName);
	                forwardToSignupPage(request, response, userName, email, role, gender, phone, address, comments);
	                return;
	          }	 catch (InvalidPhoneNumberException e) {
	        	    errorPhoneNumber = e.getMessage();
	        	    request.setAttribute("errorPhoneNumber", errorPhoneNumber);
	                forwardToSignupPage(request, response, userName, email, role, gender, phone, address, comments);
	                return;
	          } catch ( InvalidPasswordException e ) {
	        	    errorPassword = e.getMessage();
	        	    request.setAttribute("errorPassword", errorPassword);
	                forwardToSignupPage(request, response, userName, email, role, gender, phone, address, comments);
	                return;
	          }
	
	  if( role.equals("Admin")) {
		  role = "Pending";
	  }
		
	  User user = new User(userName, gender, email, password, phone, role, address, comments);
		
	  try {
	      if( registerDao.Insert(user) ) {
	    	  HttpSession session = request.getSession(); 
              // Store the user information in the session
              session.setAttribute("userName", userName);
              
	    	  Cookie userNameCookie = new Cookie("userName", userName);
	    	  userNameCookie.setMaxAge(86400 * 30);
	    	  	    	  
	    	  response.sendRedirect("RegisterSuccessCustomer.jsp?roleType="+role);
	    	  
		  } else {
			  request.setAttribute("errorMessage", "Failed to register. Please try again.");
			  forwardToSignupPage(request, response, userName, email, role, gender, phone, address, comments);
          }
	  } catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Database error occurred: " + e.getMessage());
			forwardToSignupPage(request, response, userName, email, role, gender, phone, address, comments);
	  }
	}

	private void forwardToSignupPage(HttpServletRequest request, HttpServletResponse response, String userName, String email, String role, String gender, String phone, String address, String comments) throws ServletException, IOException {
	    request.setAttribute("userName", userName);
	    request.setAttribute("email", email);
	    request.setAttribute("role", role);
	    request.setAttribute("gender", gender);
	    request.setAttribute("phone", phone);
	    request.setAttribute("address", address);
	    request.setAttribute("comments", comments);
	    request.getRequestDispatcher("signupNormal.jsp").forward(request, response);
	}

}
