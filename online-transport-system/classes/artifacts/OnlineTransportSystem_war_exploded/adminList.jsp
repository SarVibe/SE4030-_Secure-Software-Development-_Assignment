<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.Admins.Admin" %> <!-- Import the Driver class -->
<html>
<head>
    <title>Pending Admin's List</title>
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
    <h2>Pending Admin's Data</h2>
    
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
        
            <th>userName</th>
            <th>gender</th>
            <th>email</th>
            <th>phone</th>
            <th>role</th>
            <th>address</th>
            <th>comments</th>
            <th>status</th>
        </tr>

        <%
            List<Admin> adminList = (List<Admin>) request.getAttribute("adminList");
            if (adminList != null) {
                for (Admin admin : adminList) {
        %>
                    <tr>
                        <td><%= admin.getUserName() %></td>
                        <td><%= admin.getGender() %></td>
                        <td><%= admin.getEmail() %></td>
                        <td><%= admin.getPhone() %></td>
                        <td><%= admin.getRole() %></td>
                        <td><%= admin.getAddress() %></td>
                        <td><%= admin.getComments() %></td>
                        <td><%= admin.getStatus() %></td>
                        
                        <td>
                            <form action="AcceptAdmin" method="post">
                                <input type="hidden" name="allow" value="<%= admin.getUserName() %>"/>
                                <input type="submit" value="Allow" />
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
