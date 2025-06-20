package com.Admins;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class AcceptAdmin
 */
@WebServlet("/AcceptAdmin")
public class AcceptAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		 String name = request.getParameter("allow");
		 String errorMessages = "Something went wrong! Please try again later";
		 
		 AcceptAdminDao acceptadmindao = new AcceptAdminDao();
		 
		 try {
				if(acceptadmindao.approveAdmin(name)) {
					response.sendRedirect("UpdateAdmin");
				}
				else {
					 request.setAttribute("errorMessages", errorMessages);
			            request.getRequestDispatcher("adminList.jsp").forward(request, response);
			            return;
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


		
		
	}


