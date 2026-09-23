<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home Page</title>
	<style>
		body {
			margin: 0;
		    padding: 0;
		    font-family: Arial, sans-serif;
		    background: url(images/backg.jpg) no-repeat;
		    background-size: cover;

		}
		
		#img1 {
			width: 21%;
			height: 20%;
			left: 30%;
			bottom: 16%;
			position: fixed;
		}
		
		#img1:hover {
			width: 17%;
			height: 22%;
			filter: Blur(2px);
		}
		
		#img2 {
			width: 18%;
			height: 20%;
			left: 60%;
			bottom: 16%;
			position: fixed;
		}
		
		#img2:hover {
			width: 21%;
			height: 22%;
			filter: Blur(2px);
		}
		
		#heading1{
			margin-top: 17%;
			font-size:100px; 
			text-align:center;
			color:transparent;
			-webkit-text-stroke:1px #fff;
			background:url("images/back.png");
			-webkit-background-clip:text;
			animation:back 20s linear infinite;
		}
		
		@keyframes back{
			100%{
				background-position:2000px 0;
			}
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
		
		#logout{
			display:block;
			width:35%;
			position:absolute;
			top:2%;
			right: 0px;
		}
		
		#mybook {
			position: absolute;
			top: 2%;
			right: 4px;
		}
	</style>
</head>
	<body>
		<div id="logout">
				<a href="logout.jsp"><button class="btn">LOG OUT</button></a>
		</div>
		
		<div id="mybook">
			<a href="myBooking"><button class="btn">My Bookings</button></a>
		</div>
		
		<h1 id="heading1"><i>PEGAGUS ONLINE CAB</i></h1>
		
		<a href="Booking.jsp"><img src="images/rideNow.png" alt="Book Now" id="img1"></a>
		<a href="Faq.jsp"><img src="images/faq.webp" alt="FAQ" id="img2"></a>
	</body>
</html>