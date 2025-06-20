package com.faq;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class providerservlet
 */
@WebServlet("/providerservlet")
public class providerservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public providerservlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String answer = request.getParameter("answer");
        int questionID =0;
        
         try {
            
             questionID = Integer.parseInt(request.getParameter("questionID"));
             
             
         }   catch (Exception e) {
        	 request.setAttribute("errorMessage1", "Input Number only");
             request.getRequestDispatcher("provider.jsp").forward(request, response);
             e.printStackTrace(); 
         }
     

        

        Faq faq = new Faq();
        faq.setAnswer(answer);
        faq.setId(questionID);

        providerdao providerDao = new providerdao();

        try {
            if (providerDao.insertanswer(faq)) {
            	request.setAttribute("successfullyMessage", "Answer inserted successfully");
            	request.getRequestDispatcher("provider.jsp").forward(request, response);
                System.out.println("Answer inserted successfully");
            } 
            else {
                System.out.println("Failed to insert answer into database");
                request.setAttribute("errorMessage", "No such records exist.");
            	request.getRequestDispatcher("provider.jsp").forward(request, response);
            }
        } catch (SQLException e) {
           
        	 request.setAttribute("errorMessage2", "There are no questions");
        	 request.getRequestDispatcher("provider.jsp").forward(request, response);
             System.out.println("Error: " + e.getMessage());
            
            e.printStackTrace();
        }
        
        }
    
    
    }



