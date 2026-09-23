<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>LogOut Page</title>
	<meta http-equiv="refresh" content="6;url=Index.jsp">
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
    </style>
</head>
<body>
	<div class="content">
		 <h1>You have been successfully logged out!</h1>
		 <p>You will be redirected to the home page in 6 seconds.</p>
	</div>
</body>
</html>