<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin Home</title>
	<style>
		body {
			background-image: url("https://wallpapers.com/images/hd/1920-x-1080-car-o0rkgvylu81cdjhz.jpg");
			font-family: 'Arial', sans-serif;
		    margin: 0;
		    padding: 0;
		    display: flex;
		    flex-direction: column;
		    align-items: center;
		    justify-content: center;
		    height: 100vh;
		}
		
		/* Button styling */
		.btn1 {
		    display: inline-block;
		    font-size: 20px;
		    padding: 25px 30px;
		    border: 2px solid #3498db;
		    cursor: pointer;
		    border-radius: 5px;
		    text-decoration: none;
		    font-weight: bold;
		    text-align: center;
		    transition: all 0.3s ease;
		    background-color: #fff;
		    color: #3498db;
		    margin: 10px;
		}
		
		.btn1:hover {
		    transform: translateY(-3px);
		    box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
		    background-color: #3498db;
		    color: #fff;
		}
		
		#logout {
		    position: absolute;
		    top: 20px;
		    right: 20px;
		}
		
		#buttonbox{
			display:block;
			width:100%;
			position:absolute;
			top:45%;
		}
		
		.btn {
			display:inline-block;
			font-size:30px;
			padding:30px 70px 30px 70px;
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
		
		#b1{
			margin-left:12%;
		}
					  
		.btn:hover {
			transform: translateY(-3px);
			box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
			color:black;
		}
		
		h1 {
		    font-size: 70px;
		    color: red;
		    text-align: center;
		    text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.7);
		    font-weight: bold;
		    letter-spacing: 1.5px; 
		    z-index: 1; 
			position: absolute;
			top: 10%;
		}
	</style>
</head>
<body>
	<div id="logout">
		<a href="logout.jsp"><button class="btn1">LOG OUT</button></a>
	</div>
	
	<h1>Admin Panel</h1>
	<div id="buttonbox">
		<a href="AdminDetails"><button class="btn" id="b1">Admin Details</button></a>
	
		<a href="Permission"><button class="btn">Driver Details</button></a>
		
		<a href="provider.jsp"><button class="btn">Answer to FAQ</button></a>
	</div>
</body>
</html>