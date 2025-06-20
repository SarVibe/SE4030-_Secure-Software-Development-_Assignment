<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.signupDriver.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Driver and Booking Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-image : url(images/background.jpg);
        }

        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }

        .container {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 50%;
            max-width: 600px;
            text-align: center;
        }

        .driver-profile, .driver-details {
            background-color: #f0f0f5;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
        }

        .driver-profile h2, .driver-details h2 {
            margin-bottom: 15px;
            color: #333;
        }

        .driver-profile p, .driver-details p {
            margin: 10px 0;
            color: #555;
            font-size: 16px;
        }

        .view-profile-btn {
            margin-top: 15px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .view-profile-btn:hover {
            background-color: #45a049;
        }

        .accept-btn, .deny-btn {
            padding: 10px 20px;
            margin: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .accept-btn {
            background-color: #4CAF50;
            color: white;
        }

        .deny-btn {
            background-color: #f44336;
            color: white;
        }

        #finishTrip-btn {
            background-color: white;
            color: blue;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
        }

        #finishTrip-btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Driver Details</h1>
        
        <div class="driver-profile">
            <h2>Driver Profile</h2>

            <!-- Form to send driverName to the servlet -->
            <form action="DriverProfile" method="POST">
                <!-- Hidden input to send the driverName -->
                <input type="hidden" name="driverName" value="<%= request.getAttribute("driverName") != null ? request.getAttribute("driverName") : "" %>">

                <!-- Submit button to send the form -->
                <button type="submit" class="view-profile-btn">View Profile</button>
            </form>
        </div>

        <div id="driver-details" class="driver-details" style="display: <%=(request.getAttribute("driverName") != null) ? "block" : "none" %>;">
            <h2>Driver Details</h2>
            
            <p><strong>Driver Name :</strong> <%= request.getAttribute("driverName") != null ? request.getAttribute("driverName") : "N/A" %></p>
            <p><strong>Driver Phonenumber :</strong> <%= request.getAttribute("phoneNumber") != null ? request.getAttribute("phoneNumber") : "N/A" %></p>
            <p><strong>Driver Address :</strong> <%= request.getAttribute("address") != null ? request.getAttribute("address") : "N/A" %></p>
        </div>
        
        <a href="DriverHomeServlet" id="finishTrip-btn">Go Back</a>
    </div>
</body>
</html>