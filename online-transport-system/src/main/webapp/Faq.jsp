<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>FAQ Page</title>
    <style>
        /* Global Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /* Body Styling */
        body {
            background-image: url("https://media.gettyimages.com/id/1677459798/photo/question-mark-with-space-for-copy.jpg?s=2048x2048&w=gi&k=20&c=oAy3T_2JM4fY2K12CsmrTs-yAfsqG19dgKde1rP-eOo=");
            background-repeat: no-repeat;
            background-size: cover;
            font-family: 'Roboto', sans-serif;
            color: #333;
            line-height: 1.6;
        }

        /* Header Styles */
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: rgba(0, 0, 0, 0.8);
            color: white;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
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
            padding: 10px 20px;
            transition: background-color 0.3s;
        }

        nav ul li a:hover {
            background-color: #555;
            border-radius: 5px;
        }

        /* Container for FAQ and Contact Sections */
        .container {
            display: flex;
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
        }

        /* FAQ Section */
        .faq-section {
            width: 70%;
            padding: 20px;
        }

        .faq-section h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #333;
            font-size: 36px;
        }

        .faq-item {
            background-color: #f9f9f9;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            margin: 0 auto; /* Center the div */
            padding: 10px;  /* Add some padding */
            border: 1px solid #ccc; /* Optional: to visually separate each item */
            width: 63%; /* Adjust this value as needed (e.g., 50%, 400px) */
        
    }
        }

        .faq-item h3 {
            margin-bottom: 10px;
            font-size: 20px;
            color: #333;
        }

        .faq-item p {
            font-size: 16px;
            color: #666;
        }

        /* Button Styling */
        .edit-btn, .delete-btn, .submit-btn {
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

        .submit-btn {
            background-color: #ff4500;
            color: white;
            padding: 10px 20px;
            font-size: 18px;
            border-radius: 5px;
            display: block;
            margin-top: 20px;
        }

        .submit-btn:hover {
            background-color: #ff6347;
        }

        /* Add Question Section */
        .add-question input {
            width: 100%;
            padding: 12px;
            font-size: 16px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: inset 0 2px 5px rgba(0, 0, 0, 0.05);
        }

        /* Contact Section */
        .contact-section {
            width: 30%;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .contact-section h2, .contact-section p {
            margin-bottom: 20px;
            font-size: 18px;
            color: #333;
        }

        .social-icons a {
            display: inline-flex;
            align-items: center;
            margin-right: 15px;
            text-decoration: none;
            color: #000;
            font-size: 18px;
        }

        .social-icons img {
            width: 36px;
            height: 36px;
            margin-right: 10px;
        }

        /* Footer Styles */
        footer {
            background-color: #000;
            color: white;
            text-align: center;
            padding: 20px;
            margin-top: 20px;
        }

    </style>
</head>
<body>
    <!-- Header -->
    <header>
        <div class="logo">Wonder Transport</div>
        <nav>
            <ul>
                <li><a href="cusHome.jsp">Home</a></li>
                <li><a href="#">FAQs</a></li>
            </ul>
        </nav>
    </header>

    <!-- Main Container -->
    <div class="container">
        <!-- FAQ Section -->
        <div class="faq-section">
            <h1>Frequently Asked Questions</h1>

            <!-- Ask a Question Form -->
            <div class="add-question">
                <h3>Ask a Question:</h3>
                <form action="Faqservlet" method="Post">
                    <input type="text" id="question-input" name="input" placeholder="Your question here...">
                    <button class="submit-btn" type="submit" onclick="validateForm()">Submit</button>
                    <div id="error-message" class="message error-message" style="display:none;">Please enter a question.</div>
                </form>
            </div>
        </div>

        <!-- Contact Section -->
        <div class="contact-section">
            <h2>Contact Us</h2>
            <p>Email: <a href="mailto:support@transportsystem.com">support@transportsystem.com</a></p>

            <p>Phone: <a href="tel:+123456789">+123 456 789</a></p>

            <h2>Follow Us</h2>
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
                <a href="https://www.instagram.com" target="_blank">
                    <img src="https://img.icons8.com/?size=100&id=16713&format=png&color=000000" alt="Whatsapp">Whatsapp
                </a>
            </div>
        </div>
    </div>

    <!-- Display FAQ from Database -->
    <div class="faq-display">
       <%@ page import="java.sql.*" %>
       
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

        while (rs.next()) {
            int id = rs.getInt("faq_id");
            String qu = rs.getString("question");
            String as = rs.getString("answer");
            
             
            if (as != null) {
%>
                <div class="faq-item">
                    <h3><%= qu %></h3>
                    <p><%= as %></p>
                </div>
<%
            }
        }

        // Close resources
        rs.close();
        stmt.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

    </div>

    <!-- Footer -->
    <footer>
        <p>&copy; 2024 Wonder Transport System. All Rights Reserved.</p>
    </footer>

    <script>
        function validateForm() {
            var questionInput = document.getElementById("question-input").value;
            if (questionInput.trim() === "") {
                document.getElementById("error-message").style.display = "block";
                return false;
            }
            return true;
        }
    </script>
</body>
</html>
