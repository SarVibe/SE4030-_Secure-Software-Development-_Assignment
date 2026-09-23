<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.Bookings.BookingDao" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="refresh" content="20; URL=BookingSuccessServlet">
	<title>Booking Success</title>
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
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
        }

        h1 {
            color: red;
            margin: 0;
            font-size: 48px; /* Increased font size */
        }

        p {
            font-size: 24px; /* Increased font size */
            color: #4CAF50;
            margin-top: 20px;
        }

        .content {
            background-color: rgba(255, 255, 255, 0.9); /* Less transparent background */
            padding: 50px; /* Increased padding for larger content box */
            width: 80%; /* Adjust the width for larger box */
            max-width: 600px; /* Max width for responsiveness */
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            z-index: 1; /* Ensure the content is above the background */
        }
        
        a {
			text-decoration: none;   
		    color: inherit; 
		    font-size: 2rem;        
		    color: #674636;         
		    text-align: center;     
		    padding: 10px;          
		    margin: 0;              
		    transition: color 0.3s ease, transform 0.3s ease;
		}
		
		a:hover {
		    color: #ff5722;          /* Change color on hover */
		    transform: scale(2.4);    /* Slightly enlarge the text */
		}
		
		.btn {
			display:inline-block;
			font-size:30px;
			padding:15px 30px 15px 30px;
			border: none;
			cursor: pointer;
			border-radius: 5px;
			text-decoration: none;
			font-weight: bold;
			text-align: center;
			transition: all 0.3s ease;
			margin: 20px;
			background-color: white;
			color: #3498db;
		}
					  
		.btn:hover {
			transform: translateY(-3px);
			box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
			color:black;
		}
		
		#pay{
			display:block;
			width:35%;
			position:absolute;
			bottom:5%;
			right: auto;
			left: auto;
		}
	</style>
</head>
<body>
	<div class="content">
		<h1>Your vehicle request is sent successfully.</h1>
		
		<%
			String DriverDetail = (String) request.getAttribute("DriverDetail");
			if (DriverDetail != null && !DriverDetail.isEmpty()) {
		%>
			<p> One driver has accept your ride.</p>
			<p><%= DriverDetail %></p>
			
			<p>After finish your ride, Upload the payment Details.</p> 
			<div id="pay">
				<a href="Payment.jsp"><button class="btn">Payment</button></a>
			</div>
		<%
			} else {
		%>
			<p>Please wait until one driver accept your ride.</p>
		<%
			}
		%>
		
	</div>
</body>
</html>