<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot Password</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('https://cdn.pixabay.com/photo/2017/08/07/05/11/architecture-2600144_1280.jpg');
            background-color: #f4f4f4;
            background-size: cover; /* Ensures the image covers the entire background */
            background-repeat: no-repeat; /* Prevents the image from repeating */
            background-position: center; /* Centers the image */
        }

        .container {
		    max-width: 500px; /* Sets a maximum width for the container */
		    margin: 50px auto; /* Centers the container with a margin at the top */
		    padding: 20px; /* Adds padding inside the container */
		    background-color: rgba(255, 255, 255, 0.9); /* Sets the background color to white */
		    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Adds a subtle shadow around the container */
		    border-radius: 8px; /* Rounds the corners of the container */
		}

        h1 {
            text-align: center;
            color: #333;
        }

        label {
            margin-bottom: 10px;
            display: block;
        }

        input[type="text"], input[type="email"], input[type="password"] {
            width: 90%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #5cb85c;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px; /* Ensures the button text is easily readable */
        }

        button:hover {
            background-color: #4cae4c;
        }

        .message {
            text-align: center;
            margin-top: 20px;
        }

        .message a {
            color: #007bff;
            text-decoration: none;
        }

        .message a:hover {
            text-decoration: underline;
        }

        .error-message {
            color: red;
            font-size: 16px;
            margin-top: 10px; /* Optional: Adds some space above the error message */
            font-weight: bold; /* Optional: Makes the error message stand out more */
        }
        
        .refresh-btn {
            background-color: #4CAF50; /* Green */
            color: white;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .refresh-btn:hover {
            background-color: #45a049; /* Darker green on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Forgot Password</h1>
        <form action="Forgotservlet" method="POST">
            <label for="Username">User name:</label>
            <input type="text" id="Username" name="Username" required>

            <label for="NewPassword">New Password:</label>
            <input type="password" id="NewPassword" name="NewPassword" required>

            <label for="ComPassword">Confirm Password:</label>
            <input type="password" id="ComPassword" name="ComPassword" required>
            

            <span id="message" style="color:red;"></span><br>

            <button type="submit" onclick ="dothis()">Submit</button>
            <button class="refresh-btn" onclick="location.reload()">Refresh</button>
        </form>

        <p class="message">Remembered your password? <a href="Login.jsp">Login here</a></p>
        
        <% 
            String errorMessage2 = (String) request.getAttribute("errorMessage2"); 
            if (errorMessage2 != null) { 
        %>
            <div class="error-message">
                <h2><%= errorMessage2 %></h2>
            </div>
        <% 
            } 
        %>
        
        <% 
                String errorMessage1 = (String) request.getAttribute("errorMessage1"); 
                if (errorMessage1 != null) { 
            %>
                <div class="error-message">
                    <h2><%= errorMessage1 %></h2>
                </div>
            <% 
                } 
            %>
            
            
            <% 
                String errorMessage3 = (String) request.getAttribute("errorMessage3"); 
                if (errorMessage3 != null) { 
            %>
                <div class="error-message">
                    <h2><%= errorMessage3 %></h2>
                </div>
            <% 
                } 
            %>
        
        </div>
</body>
</html>
