<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<html>
<head>
<title>User Management Application</title>
<style>
  body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #e9f4fb;
    margin: 0;
    padding: 0;
  }

  header {
    background-color: #0066cc;
    padding: 20px;
    text-align: center;
    color: white;
    font-size: 24px;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
  }

  .container {
    margin: 40px auto;
    max-width: 600px;
    padding: 20px;
    background-color: #ffffff;
    border-radius: 12px;
    box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
  }

  h2 {
    font-size: 28px;
    text-align: center;
    color: #333;
    margin-bottom: 25px;
  }

  .form-group {
    margin-bottom: 20px;
  }

  label {
    font-size: 16px;
    color: #333;
    display: block;
    margin-bottom: 8px;
    font-weight: bold;
  }

  input[type="text"], input[type="number"], input[type="password"], input[type="date"] {
    width: 100%;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 8px;
    font-size: 15px;
    color: #333;
    background-color: #f9f9f9;
  }

  input[type="text"]:focus, input[type="number"]:focus, input[type="password"]:focus, input[type="date"]:focus {
    border-color: #007bff;
    outline: none;
    box-shadow: 0px 0px 12px rgba(0, 123, 255, 0.5);
    background-color: #f3fbff;
  }

  button {
    width: 100%;
    padding: 12px;
    background-color: #28a745;
    color: white;
    border: none;
    border-radius: 8px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  button:hover {
    background-color: #218838;
  }

  .card {
    background-color: #f7f9fc;
    border-radius: 12px;
    padding: 30px;
  }

  .card-body {
    padding: 15px;
  }

  .btn {
    display: block;
    text-align: center;
    font-size: 16px;
  }

  ul {
    padding-left: 20px;
    color: #d9534f;
  }

  .error-message {
    color: #d9534f;
    background-color: #fcebea;
    padding: 10px;
    border-radius: 8px;
    margin-bottom: 20px;
  }

  @media only screen and (max-width: 600px) {
    .container {
      padding: 15px;
    }

    h2 {
      font-size: 24px;
    }

    input[type="text"], input[type="number"], input[type="password"], input[type="date"], button {
      font-size: 14px;
      padding: 10px;
    }
  }
</style>
</head>

<body>

	<header>
		<nav class="navbar">
			<div>
				<center><p>User Management Application</p></center>
			</div>
		</nav>
	</header>
	<br>

	<!-- Display error messages if available -->
    <%
        List<String> errorMessages = (List<String>) request.getAttribute("errorMessages");
        if (errorMessages != null && !errorMessages.isEmpty()) {
    %>
        <div class="error-message">
            <ul>
                <% for (String message : errorMessages) { %>
                    <li><%= message %></li>
                <% } %>
            </ul>
        </div>
    <%
        }
    %>
    
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="SignupDriver" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="CRUD" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${user != null}">
            			Edit User
            		</c:if>
						<c:if test="${user == null}">
            			Add New User
            		</c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>User Name</label> 
					<input type="text" class="form-control" name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Driver’s Address</label> 
					<input type="text" class="form-control" name="address">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Driver’s Phone Number</label> 
					<input type="number" class="form-control" name="phonenumber">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Driver’s Vehicle Number</label> 
					<input type="text" class="form-control" name="vechilenumber">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Driver’s License Number:</label> 
					<input type="text" class="form-control" name="licensenumber">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Driver’s License Expiry Date:</label> 
					<input type="date" class="form-control" name="expirydate">
				</fieldset>
			
				<fieldset class="form-group">
					<label>Password</label> 
					<input type="password" class="form-control" name="password" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Confirm Password</label> 
					<input type="password" class="form-control" name="confirmpassword" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
