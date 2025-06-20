<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.Bookings.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Bookings</title>
  <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
        }

        h1 {
            color: red;
            text-align: center;
            margin-bottom: 20px;
            font-size: 28px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #006BFF;
            color: white;
            font-weight: bold;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        td form {
            display: inline;
        }
        
        .no-data {
            text-align: center;
            font-style: italic;
            color: #666;
        }
        
        .btn-home {
		    background-color: #06D001;
		    color: white;
		    padding: 10px 20px;
		    border: none;
		    cursor: pointer;
		    font-size: 16px;
		    margin-top: 20px;
		    display: inline-block;
		    margin-left: 45%;
		}
		
		.btn-home:hover {
		    background-color: #059212;
		}
    </style>
</head>
<body>
	
	 <h1>Booking Details</h1>
	 <table>
        <tr>
            <th>Pickup Address</th>
            <th>Drop Address</th>
            <th>Pickup Time</th>
            <th>Booking Date</th>
            <th>Driver Name</th>
            <th>Pay Amount</th>
        </tr>

        <%
            List<User> myBookings = (List<User>) request.getAttribute("myBookingList");
            if (myBookings != null) {
                for (User user : myBookings) {
        %>
                    <tr>
                        <td><%= user.getPickupAddress() %></td>
                        <td><%= user.getDropAddress() %></td>
                        <td><%= user.getPickUpTime() %></td>
                        <td><%= user.getBookDate() %></td>
                        <td><%= user.getDriverName() %></td>
                        <td><%= user.getAmount() %></td>
                        <td>
                    </tr>
        <%
                }
            } else {
        %>
            <tr><td colspan="7" class="no-data">No Bookings happen yet.</td></tr>
        <%
            }
        %>
    </table>

	<form action="cusHome.jsp" method="get">
    	<button type="submit" class="btn-home">Go to Home</button>
	</form>    
</body>
</html>