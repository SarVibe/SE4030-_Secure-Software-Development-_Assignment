<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Page</title>
	<style type="text/css">
	@charset "UTF-8";
	body 
	{
		background-image: url('https://cdn.pixabay.com/photo/2017/08/07/05/11/architecture-2600144_1280.jpg');
        background-repeat: no-repeat;
        background-size: cover;
	    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	    height: 100vh;
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    margin: 0;
	    color: #333;
	}
	
	form 
	{
	    background-color: rgba(255, 255, 255, 0.9);
	    border-radius: 15px;
	    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
	    padding: 3%;
	    width: 30%; 
	    transition: transform 0.2s ease-in-out;
	}
	
	form:hover 
	{
	    transform: scale(1.03); 
	}
	
	label 
	{
	    display: block;
	    margin: 10px 0 5px;
	    color: #555;
	    font-weight: bold;
	    font-size: 1em;
	}
	
	input[type="text"], select 
	{
	    width: 100%; 
	    padding: 10px;
	    margin-bottom: 15px;
	    border: 1px solid #ccc;
	    border-radius: 5px;
	    transition: border-color 0.3s ease;
	}
	
	input[type="text"]:focus, select:focus 
	{
	    border-color: #4CAF50;
	    outline: none; 
	}
	
	input[type="submit"] 
	{
	    width: 100%;
	    background-color: #4CAF50;
	    color: white;
	    padding: 10px;
	    border: none;
	    border-radius: 5px;
	    cursor: pointer;
	    font-size: 1em;
	    transition: background-color 0.3s ease;
	}
	
	input[type="submit"]:hover 
	{
	    background-color: #45a049;
	}
	
	#amount
	{
	width : 93%;
	}
	</style>
</head>
<body >
    
    <form action="Payment" method="post">
        <label for="amount">Amount:</label>
        <input type="text" id="amount" name="amount" ><br>

        <label for="paymentMethod">Payment Method:</label>
        <select id="paymentMethod" name="paymentMethod" >
            <option value="credit_card">Credit Card</option>
            <option value="debit_card">Debit Card</option>
            <option value="paypal">PayPal</option>
        </select><br>

        <input type="submit" value="Pay Now">
        
         <c:if test="${not empty errorMessage}">
            <p style="color:red;">${errorMessage}</p>
        </c:if>
 
    </form>
    
   
    
</body>
</html>