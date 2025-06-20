package com.payments;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.InvalidUserDefinedException.InvalidPaymentAmountException;

@WebServlet("/Payment")
public class PaymentServlet extends HttpServlet {
 
    
    // check amount is grater than or less than 99;
    public static void ValidatePaymentAmount(int amount) throws InvalidPaymentAmountException
    {
    	 if (amount < 100) {
             throw new InvalidPaymentAmountException("Amount must be greater than 99.");
         }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentMethod = request.getParameter("paymentMethod");
        int amount = 0; 
        
        // Get the session, but do not create a new one if it doesn't exist
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Retrieve a session variable (for example, "userName")
            String userName = (String) session.getAttribute("userName");
        
        try 
        {
            // get amount from Payment.jsp
            amount = Integer.parseInt(request.getParameter("amount"));
            
            // Validate the amount
            ValidatePaymentAmount(amount);

            // Create a Payment object
            Payment p = new Payment(userName, amount, paymentMethod);
            paymentModel pm = new paymentModel();

           
            if (pm.insert(p)) //if insert method is successful if block will be execute
            {
                // Set the success message and forward to Payment success page
                request.setAttribute("successMessage", "Your payment of " + amount + " was successful.");
                request.getRequestDispatcher("PaymentSuccess.jsp").forward(request, response);
            } 
            else  // If insert method fail, stay on the payment page
            {
                response.sendRedirect("Payment.jsp");
            }

        } 
        catch (NumberFormatException e)  // Handle invalid amount format
        {
        	//System.out.println("Payment successful: " + amount);
            request.setAttribute("errorMessage", "Invalid amount entered. Please enter a valid number.");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
        } 
        catch (InvalidPaymentAmountException e)  // Handle invalid payment amount
        {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
        } 
        catch (SQLException e)    // Handle database exceptions
        {
            e.printStackTrace();
            response.sendRedirect("Payment.jsp");
        }

    }
  }
}