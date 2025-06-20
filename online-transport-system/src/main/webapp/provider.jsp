<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Question Management</title>
    <style>
        /* Global Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            background-image: url("https://media.gettyimages.com/id/1677459798/photo/question-mark-with-space-for-copy.jpg?s=2048x2048&w=gi&k=20&c=oAy3T_2JM4fY2K12CsmrTs-yAfsqG19dgKde1rP-eOo=");
            background-repeat: no-repeat;
            background-size: cover;
            font-family: Arial, sans-serif;
            color: #333;
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: #000;
            color: white;
        }

        header .logo {
            font-size: 24px;
            font-weight: bold;
        }

        nav ul {
            list-style: none;
            display: flex;
        }

        nav ul li a {
            color: white;
            text-decoration: none;
            padding: 10px;
        }

        /* Social Icons Styling */
        .social-icons {
            display: flex;
            align-items: center;
        }

        .social-icons a {
            margin-left: 10px;
            color: white;
            text-decoration: none;
            font-size: 16px;
            display: flex;
            align-items: center;
        }

        .social-icons img {
            width: 30px;
            height: 30px;
            margin-right: 5px;
        }

        /* Container Styles */
        .container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.8);
            border-radius: 10px;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
        }

        /* FAQ Section */
        .faq-section h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        .faq-item {
            background-color: #fff;
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        /* Button Styling */
        .edit-btn, .delete-btn, .answer-btn {
            padding: 10px 15px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .edit-btn {
            background-color: #4caf50;
            color: white;
        }

        .edit-btn:hover {
            background-color: #45a049;
        }

        .delete-btn {
            background-color: #f44336;
            color: white;
        }

        .delete-btn:hover {
            background-color: #e53935;
        }

        .answer-btn {
            background-color: #1e90ff;
            color: white;
        }

        .answer-btn:hover {
            background-color: #1c86ee;
        }

        /* Answer Form Styling */
        .answer-form textarea {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        /* Footer Styling */
        footer {
            background-color: #000;
            color: white;
            text-align: center;
            padding: 20px;
            margin-top: 20px;
        }
        
        .question-input {
    width: 100px;  /* Adjust the width as needed */
    font-size: 18px;  /* Increase the font size */
    padding: 5px;  /* Add some padding for better appearance */
    border: 2px solid #ccc;  /* Add border */
    border-radius: 5px;  /* Rounded corners */
    }
    
   .error-message {
    text-align: center;
     color: green;
    font-size: 14px;
    margin-top: 10px; /* Optional: Adds some space above the error message */
    font-weight: bold; /* Optional: Makes the error message stand out more */


}

.error1-message {
    text-align: center;
     color: red;
    font-size: 14px;
    margin-top: 10px; /* Optional: Adds some space above the error message */
    font-weight: bold; /* Optional: Makes the error message stand out more */


}

 .error {
            color: red;
            display: none; /* Initially hide the error message */
        }
        
       
        
    </style>
</head>
<body>
    <!-- Header Section -->
    <header>
        <div class="logo">Admin</div>
        <nav>
            <ul>
                <li><a href="AdminHome.jsp">Home</a></li>
                <li><a href="#">Manage FAQs</a></li>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>
        </nav>
        <!-- Social Icons -->
        <div class="social-icons">
            <a href="https://www.facebook.com" target="_blank">
                <img src="https://img.icons8.com/?size=100&id=118495&format=png&color=000000" alt="Facebook">Facebook
            </a>
            <a href="https://twitter.com" target="_blank">
                <img src="https://img.icons8.com/?size=100&id=5MQ0gPAYYx7a&format=png&color=000000" alt="Twitter">Twitter
            </a>
            <a href="https://www.instagram.com" target="_blank">
                <img src="https://img.icons8.com/?size=100&id=nj0Uj45LGUYh&format=png&color=000000" alt="Instagram">Instagram
            </a>
            <a href="https://www.whatsapp.com" target="_blank">
                <img src="https://img.icons8.com/?size=100&id=16713&format=png&color=000000" alt="Whatsapp">Whatsapp
            </a>
        </div>
    </header>

    <!-- Main Container -->
    <div class="container">
        <div class="faq-section">
            <h1>Customer Questions</h1>
            
            <!-- FAQ List from Database -->
           <table border="1">
    <%
        String url = "jdbc:mysql://localhost:3306/TransportDB";
        String username = "root";
        String password = "it23440722@my.sliit.lk";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM faqs";
            ResultSet rs = stmt.executeQuery(query);

            // Display table headers
    %>
            <tr>
                <th>ID</th>
                <th>Question</th>
                <th>Answer</th>
            </tr>
    <%
            // Loop through the result set and display data
            while (rs.next()) {
                int id = rs.getInt("faq_id");
                String question = rs.getString("question");
                String answer = rs.getString("answer");

                if (question != null && !question.trim().isEmpty()) {
    %>
                <tr>
                    <td><%= id %></td>
                    <td><%= question %></td>
                    <td><%= answer %></td>
                </tr>
    <%
                }
            }
        } catch (Exception e) {
        	 out.println("There are no questions");
        }
    %>
</table>

            <br>
            <!-- Form for Answering Questions -->
            <div class="faq-item">
                <form class="answer-form" action="providerservlet" method="Post">
                <h2>Question ID: <input type="text" name="questionID" class="question-input" required></h2>

                <p>Answer:</p>
               
                    <textarea name="answer" placeholder="Type your answer here..." required></textarea>
                     <button type="submit" class="answer-btn" >Submit Answer</button>
                </form>
                     
                     
                      <% 
                String errorMessage3 = (String) request.getAttribute("errorMessage"); 
                if (errorMessage3 != null) { 
            %>
                <div class="error1-message">
                    <h2><%= errorMessage3 %></h2>
                </div>
            <% 
                } 
            %>
                   
                  <% 
                String Message1 = (String) request.getAttribute("successfullyMessage"); 
                if (Message1 != null) { 
            %>
                <div class="error-message">
                    <h2><%= Message1 %></h2>
                </div>
            <% 
                } 
            %>
            
             <% 
                String Message2 = (String) request.getAttribute("errorMessage1"); 
                if (Message2 != null) { 
            %>
                <div class="error1-message">
                    <h2><%= Message2 %></h2>
                </div>
            <% 
                } 
            %>
            
             
                      <% 
                String errorMessage2 = (String) request.getAttribute("errorMessage2"); 
                if (errorMessage2 != null) { 
            %>
                <div class="error1-message">
                    <h2><%= errorMessage2 %></h2>
                </div>
            <% 
                } 
            %>
             
            </div>
        </div>
    </div>
    <div>
    
    </div>

    
    <footer>
        <p>&copy; 2024 Wonder Transport. All rights reserved.</p>
    </footer>
</body>
</html>
