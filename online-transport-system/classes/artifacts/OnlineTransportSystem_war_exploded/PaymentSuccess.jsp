<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment Successful</title>
    
    <style>
        body 
        {
		    font-family: 'Arial', sans-serif; 
		    background: linear-gradient(135deg, #f0f4f8, #c3e0e5); 
		    color: #333; 
		    text-align: center;
		    padding: 50px;
		    margin: 0;
		}
		
        h1 
        {
            color: #4CAF50;
        }
        
        p 
        {
            font-size: 1.2em;
            margin: 20px 0;
        }
        
        a 
        {
            text-decoration: none;
            color: #007BFF;
            font-size: 1.1em;
        }
        
        a:hover 
        {
            text-decoration: underline;
        }
        
       .success-message 
       {               
          font-weight: bold;          
          font-size: 16px;             
          border: 1px solid green;    
          padding: 10px;              
          border-radius: 5px;         
          background-color: #e0ffe0;  
          text-align: center;          
          margin: 10px 0;             
          width: 40%;             
        }
        
      .message-container 
      {
		  display: flex;                    
		  justify-content: center;        
		  align-items: center;                                   
      }
        
    </style>
    
</head>
<body>

    <h1>Payment Successful!</h1>
    
    <div class="message-container">
    <p class="success-message">${successMessage}</p>
    </div>

    <a href="cusHome.jsp">Go Back</a>
</body>
</html>
