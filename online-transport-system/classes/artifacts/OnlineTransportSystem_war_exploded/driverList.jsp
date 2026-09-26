<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.signupDriver.Driver" %> <!-- Import the Driver class -->
<%@ page import="com.util.XSSUtils" %>

<%
    if (session.getAttribute("userName") == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Pending Driver's List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
        }

        h2 {
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
            background-color: blue;
            color: white;
            font-weight: bold;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        td form {
            display: inline;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 15px;
            text-align: center;
            text-decoration: none;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
        
        #deny {
            background-color: red;
            color: white;
            border: none;
            padding: 10px 15px;
            text-align: center;
            text-decoration: none;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .error {
            color: red;
            text-align: center;
            font-weight: bold;
        }

        .no-data {
            text-align: center;
            font-style: italic;
            color: #666;
        }
    </style>
</head>
<body>
    <h2>Pending Driver's Data</h2>
    
    <%
    String errorMessages = (String) request.getAttribute("errorMessages");
        if (errorMessages != null && !errorMessages.isEmpty()) {
    %>
        <div class="error">
            <ul>
                <li><%= errorMessages %></li>
            </ul>
        </div>
    <%
        }
    %>

    <table>
        <tr>
            <th>User name</th>
            <th>Address</th>
            <th>Phone Number</th>
            <th>Vehicle Number</th>
            <th>License Number</th>
            <th>Expiry Date</th>
            <th>Actions</th>
        </tr>

        <%
            List<Driver> driverList = (List<Driver>) request.getAttribute("driverList");
            if (driverList != null) {
                for (Driver driver : driverList) {
        %>
                    <tr>
                        <td><%= XSSUtils.sanitize(driver.getName()) %></td>
                        <td><%= XSSUtils.sanitize(driver.getAddress()) %></td>
                        <td><%= XSSUtils.sanitize(String.valueOf(driver.getPhonenumber())) %></td>
                        <td><%= XSSUtils.sanitize(driver.getVechilenumber()) %></td>
                        <td><%= XSSUtils.sanitize(driver.getLicensenumber()) %></td>
                        <td><%= XSSUtils.sanitize(String.valueOf(driver.getExpirydate())) %></td>
                        <td>
                            <form action="AllowDriver" method="post">
                                <input type="hidden" name="allow" value="<%= XSSUtils.sanitizeForHtmlAttribute(driver.getName()) %>"/>
                                <input type="submit" value="Allow" />
                            </form>
                            
                            <form action="DeleteDriver" method="post">
                                <input type="hidden" name="delete" value="<%= XSSUtils.sanitizeForHtmlAttribute(driver.getName()) %>"/>
                                <input type="submit" value="Deny" id="deny" />
                            </form>
                        </td>
                    </tr>
        <%
                }
            } else {
        %>
            <tr><td colspan="7" class="no-data">No driver data available</td></tr>
        <%
            }
        %>
    </table>
</body>
</html>
