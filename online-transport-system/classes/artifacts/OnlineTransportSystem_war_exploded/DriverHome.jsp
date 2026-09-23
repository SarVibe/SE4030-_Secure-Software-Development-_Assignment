<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.Bookings.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Driver and Booking Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f5;
            margin: 0;
            padding: 2%;
        }

        h1 {
            color: red;
            font-size: 40px;
            text-align: center;
        }

        .driver-profile {
            position: absolute;
            top: 4%;
            right: 3%;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 1% 2% rgba(0, 0, 0, 0.1);
            padding: 1%;
            width: 20%;
        }

        .booking-info {
            margin-top: 3%;
            padding: 2%;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 1% 2% rgba(0, 0, 0, 0.1);
            width: 45%;
            max-width: 60%;
            margin-left: auto;
            margin-right: auto;
        }

        p {
            margin: 0.5% 0;
        }

        hr {
            margin: 10px 0;
            border: none;
            border-top: 1px solid #ccc;
        }

        .view-profile-btn {
            margin-top: 2%;
            padding: 4% 5%;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .view-profile-btn:hover {
            background-color: #45a049;
        }

        .driver-details {
            display: block; /* Change to block to show by default */
            margin-top: 1%;
        }

        .accept-btn, .deny-btn {
            padding: 1% 5%;
            margin-top: 1%;
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
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            display: none; /* Initially hidden */
            position: absolute; /* Use relative or absolute positioning */
            right: 50%;
            margin-top: 12%;
        }

        #finishTrip-btn:hover {
            background-color: #45a049;
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
			left 0px;
		}
		
    </style>
    <script>
        function finishTrip() {
            alert("Trip Finished!");
            // Hide the Finish Trip button after finishing the trip
            document.getElementById('finishTrip-btn').style.display = 'none';
        }
    </script>
</head>
<body>
	<div id="logout">
				<a href="logout.jsp"><button class="btn">LOG OUT</button></a>
	</div>
		
    <div class="driver-profile">
        <p>Driver Profile</p>

        <!-- Form to send driverName to the servlet -->
        <form action="DriverProfile.jsp" method="POST">
           <button type="submit" class="view-profile-btn" id="view-profile-button">View Profile</button>
        </form>
    </div>

    <h1>Booking Information</h1>
    <div class="booking-info" id="booking-info">
        <%
            List<User> AllBooking = (List<User>) request.getAttribute("bookings");
        	
            if (AllBooking != null) {
                for (User booking : AllBooking ) {
        %>
        	<p><strong>Passenger Name :</strong> <%= booking.getUserName() %></p>
            <p><strong>Pickup Address :</strong> <%= booking.getPickupAddress() %></p>
            <p><strong>Drop Address :</strong> <%= booking.getDropAddress() %></p>
            <p><strong>Pickup Time :</strong> <%= booking.getPickUpTime() %></p>
            <p><strong>Phone Number :</strong> <%= booking.getPhoneNumber() %></p>
            <form action="acceptORdenyservlet" method="post">
                <input type="hidden" name="NameOfBookingUser" value="<%= booking.getUserName() %>">
                <input type="submit" name="Accept" value="Accept" class="accept-btn">
            </form>
            <hr />
        <%
                }
            }
        %>
    </div>

    <!-- Finish Trip Button, initially hidden -->
    <form action="acceptORdenyservlet" method="post">
        <input type="submit" name="Finish-Trip" value="Finish Trip" id="finishTrip-btn" onclick="finishTrip(); return false;">
    </form> 
</body>
</html>