<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.InvalidUserDefinedException.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>GetRide</title>
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
		}

		body {
		    margin: 0;
		    padding: 0;
		}
		
		#outer-container {
		    width: 40%;
		    height: 80%;
		    margin-left: 50px;
		    margin-top: 40px;
		    border-radius: 20px;
		    position: relative;
		    z-index: 1;
		    background-color: transparent;
		    box-shadow: 0 0 20px rgba(0, 0, 0, 10.5);
		}

		
		input[type="text"] {
			width: 85%;
            padding: 20px;
            margin: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            font-size: 15px;
		}
		
		label {
			width: 10%;
            padding: 20px;
            margin-left: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #FBF9F1;
            font-size: 17px;
            
		}
		
		input[type="time"] {
			width: 20%;
            padding: 15px;
            margin: 20px;
            margin-left: 10px;
            border-radius: 10px;
            background-color: #FBF9F1;
            border-color: 1px white;
		}
		
		#mode {
			width: 25%;
            padding: 20px;
            margin: 20px;
            margin-left: 50px;
            border: 1px solid #ccc;
            border-radius: 10px;
		}
		
		input[type="submit"] {
			width: 45%;
            padding: 10px;
            margin: 5px 0px 15px 180px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #F7E6FF;
            font-size: 20px;
		}
		
		input[type="submit"]:hover {
			width: 45%;
            padding: 10px;
            margin: 5px 0px 15px 180px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #C4D7FF;
            font-size: 20px;
		}
		
		#heading {
			text-align: center; 
			font-weight: bold;
			color: white;
			font-size: 40px;
			padding-top: 30px;
		}
		
		#map {
        	width: 40%;
        	height: 80%;
        	top: 5%;
        	right: 5%;
        	left: 45%;
        	position: absolute;
        	z-index: 0;
        	margin: 20px;
            margin-left: 100px;
            border-radius: 10px;
    	}
    	
    	p{
			width: 90%;
		    padding: 0px;
		    margin-left: 30px;
		    border: 1px solid #ccc;
		    background-color: red;
		    padding: 4px;
		}
	</style>
</head>
<body>
	<div id="outer-container">
		<h1 id="heading">Get a ride</h1>
		<form action="Booking" method ="post">			
			<input type="text" name="pickup" id="pickup" placeholder="Pickup Address" required value="<%= request.getAttribute("pickup") != null ? request.getAttribute("pickup") : "" %>">><br>
			
			<input type="text" name="drop" id="drop" placeholder="Destination Address" required value="<%= request.getAttribute("drop") != null ? request.getAttribute("drop") : "" %>">>
			
			<input type="text" name="phone" id="phone" placeholder="Phone Number">
			<% if (request.getAttribute("errorPhoneNumber") != null) { %>
           	<p style="color:white;"><%= request.getAttribute("errorPhoneNumber") %></p> <% } %>
           
			<label for="pickupTime">Pickup Time <input type="time" name="pickupTime"  required value="<%= request.getAttribute("pickupTime") != null ? request.getAttribute("pickupTime") : "" %>"></label><br>
			<% if (request.getAttribute("errorPickupTime") != null) { %>
           	<p style="color:white;"><%= request.getAttribute("errorPickupTime") %></p> <% } %>
           	
			<input type="submit" value="Book Now">
		</form>
	</div>
	
	
	<a href="https://www.maptiler.com/copyright/" target="_blank">&copy; MapTiler</a>
    <a href="https://www.openstreetmap.org/copyright" target="_blank">&copy; OpenStreetMap contributors</a>
	
	<div id="map">
		    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d31465.03767266556!2d80.00782719267717!3d9.66995693682505!2m3!1f0!2f0!3f0
		    !3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3afe53fd7be66aa5%3A0xc7da0d9203baf512!2sJaffna!5e0!3m2!1sen!2slk!4v1728219195921!5m2!1sen!2slk" 
		    width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
	</div>
</body>
</html>