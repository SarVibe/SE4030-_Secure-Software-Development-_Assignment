<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.InvalidUserDefinedException.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>SignUp Page</title>
	<style>
		body::before {
		    content: "";
		    position: fixed;
		    top: 0;
		    left: 0;
		    width: 100%;
		    height: 100%;
		    background-image: url("images/background.jpg");
		    filter: blur(3px);
		    z-index: -1; /* Ensure the background is behind the content */
		}
		
		body {
			margin: 0;
		    padding: 0;
		    font-family: Arial, sans-serif;
		    background: url(images/background.jpg) fixed no-repeat;
		    background-size: cover;
		    background-color: #b0c4de;
		    color: #333;
		}
		
		#container {
		    width: 55%;
		    margin: 50px auto;
		    padding: 10px;
		    background-color: transparent;
		    border-radius: 50px;
		    box-shadow: 0 0 20px rgba(0, 0, 0, 1.5);
		    border-width: 4px solid #fff;
		    z-index: 1;
		}
		
		h1 {
		    text-align: center;
		    margin-bottom: 20px;
		}
		
		form {
		    width: 100%;
		    margin-bottom: 20px;
		    
		}
		
		input[type="text"],
		input[type="email"],
		input[type="password"],
		textarea {
		    width: 80%;
		    padding: 20px;
		    margin: 10px;
		    border: 1px solid #ccc;
		    border-radius: 10px;
		}
		
		input[type="email"] {
		    width: 80%;
		    padding: 20px;
		    margin: 10px;
		    margin-top : 0px;
		    border: 1px solid #ccc;
		    border-radius: 10px;
		}
		
		input[type="password"] {
		    width: 80%;
		    padding: 10px 20px 10px 20px;
		    margin: 10px;
		    border: 1px solid #ccc;
		    border-radius: 10px;
		}
		
		label {
		    width: 90%;
		    padding: 10px;
		    margin: 10px;
		    border: 1px solid #ccc;
		    background-color: #fff;
		    border-radius: 10px;
		}
		
		input[type="radio"] {
		    padding: 2px;
		    margin-left: 25px;
		    border: 1px solid #ccc;
		    border-radius: 10px;
		}
		
		input[type="submit"] {
		    padding: 10px 40px 10px 40px;
		    background-color: green;
		    color: #fff;
		    border: none;
		    border-radius: 6px;
		    cursor: pointer;
		    display: block;
		    margin: 30px auto 20px auto;
		    font-size: 20px;
		}
		
		p{
			width: 80%;
		    padding: 0px;
		    margin-left: 30px;
		    border: 1px solid #ccc;
		    background-color: red;
		    padding: 4px;
		}
	</style>
</head>
<body>
	<div id="container">
		<h1 style="color: red;">SignUp Here</h1>        
        
	 	<form action="signUp" method="post">		
           <input type="text" id="userName" name="userName" placeholder="User Name" required value="<%= request.getAttribute("userName") != null ? request.getAttribute("userName") : "" %>">
           <% if (request.getAttribute("errorName") != null) { %>
           <p style="color:white;"><%= request.getAttribute("errorName") %></p> <% } %>
           <% if (request.getAttribute("errorNameExist") != null) { %>
           <p style="color:white;"><%= request.getAttribute("errorNameExist") %></p> <% } %>
           
           <br><div style="margin-top: 12px; margin-bottom: 0px">
           <label for="male"><input type="radio" style="margin-left: 10px;" id="male" name="gender" value="male" required <%= "male".equals(request.getParameter("gender")) ? "checked" : "" %>>Male</label>
           <label for="female"><input type="radio" style="margin-left: 10px;" id="female" name="gender" value="female" required <%= "female".equals(request.getParameter("gender")) ? "checked" : "" %>>Female</label>
           </div><br><br>

           <input type="email" id="email" name="email" placeholder="Enter your email" required value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>">
          	
           <input type="password" id="password" name="password" placeholder="Enter your password" required style="height: 40px;">
           <% if (request.getAttribute("errorPassword") != null) { %>
           <p style="color:white;"><%= request.getAttribute("errorPassword") %></p> <% } %>
          
           <input type="text" id="phone" name="phone" placeholder="Enter your phone number" required value="<%= request.getAttribute("phone") != null ? request.getAttribute("phone") : "" %>">
           <% if (request.getAttribute("errorPhoneNumber") != null) { %>
           <p style="color:white;"><%= request.getAttribute("errorPhoneNumber") %></p> <% } %>
           
           <br><br>
           <label>Role
           <input type="radio" id="Passenger" style="margin-left: 60px;" name="role" value="Passenger" required <%= "Passenger".equals(request.getParameter("role")) ? "checked" : "" %>>Passenger  
           <input type="radio" id="Admin" name="role" value="Admin" required <%= "Admin".equals(request.getParameter("role")) ? "checked" : "" %>>Admin  
           </label>
           <br><br>
           
           <input type="text" id="address" name="address" placeholder="Enter your address" required value="<%= request.getAttribute("address") != null ? request.getAttribute("address") : "" %>">
           
           <textarea id="comments" name="comments" rows="3" cols="50" placeholder="Enter any additional comments or preferences" required><%= request.getAttribute("comments") != null ? request.getAttribute("comments") : "" %></textarea>
           
           <br><label for="terms">
                <input type="checkbox" id="terms" name="terms" required>
                I agree to the <a href="termsAndConditions.html" target="_blank">Terms and Conditions</a>
           </label><br>
                
           <input type="submit" value="Submit" name="btn">
        
        </form>
	</div>
</body>
</html>