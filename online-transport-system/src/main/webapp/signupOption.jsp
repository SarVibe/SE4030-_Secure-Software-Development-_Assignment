<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Signup Option</title>
	<style>
		body {
			background-image:linear-gradient(rgba(0,0,0,0.4),rgba(0,0,0,0.4)),url("images/backg.jpg");
		    background-attachment: fixed;
		    background-size: cover;
		    background-position: center
		}
				
		#buttonbox{
			display:block;
			width:100%;
			position:absolute;
			top:25%;
			left: 25%;
		}
		
		.btn {
			display:inline-block;
			font-size:30px;
			padding:20px 60px 20px 60px;
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
				
		#btn1{
			margin-left:32%;
		}
					  
		.btn:hover {
			transform: translateY(-3px);
			box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
			color:black;
		}
	</style>
</head>
<body>
	<div id="buttonbox">
		<a href="signupNormal.jsp"><button class="btn">Create an account for Admin</button></a><br>
		<a href="signupNormal.jsp"><button class="btn">Create an account for Customer</button></a><br>
		<a href="SignupDriver.jsp"><button class="btn">Create an account for Driver</button></a>
	</div>
</body>
</html>