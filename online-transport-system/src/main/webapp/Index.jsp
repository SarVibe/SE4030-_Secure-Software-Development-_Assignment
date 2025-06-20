<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Index Page</title>
	<style>
		body {
			background-image:linear-gradient(rgba(0,0,0,0.4),rgba(0,0,0,0.4)),url("images/backg.jpg");
		    background-attachment: fixed;
		    background-size: cover;
		    background-position: center
		}
		
		#headingbox{
			display:block;
			width:100%;
			text-align:center;
			position:absolute;
			top:4%;
			color:white;
		}
					
		#heading1{
			font-size:120px; 
			text-align:center;
			color:transparent;
			-webkit-text-stroke:1px #fff;
			background:url("images/back.png");
			-webkit-background-clip:text;
			background-position:0 0;
			animation:back 18s linear infinite;
		}
		
		@keyframes back{
			100%{
				background-position:2000px 0;
			}
		}
		
		#buttonbox{
			display:block;
			width:100%;
			position:absolute;
			top:55%;
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
	<div id="headingbox">
		<h1 id="heading1"><i>PEGAGUS ONLINE CAB</i></h1>
	</div>
	
	<div id="buttonbox">
		<a href="Login.jsp"><button class="btn" id="btn1">LOG IN</button></a>
		<a href="signupOption.jsp"><button class="btn">SIGN UP</button></a>
	</div>
</body>
</html>