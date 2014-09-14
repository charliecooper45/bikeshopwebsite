package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = null;
		String action = request.getParameter("action");
		
		if(action == null) {
			jspPage = "/index.jsp";
		} else {
			switch(action) {
			case "login":
				jspPage = "/login.jsp";
				break;
			}
		}
		
		getServletContext().getRequestDispatcher(jspPage).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println("Login attempt with: " + email + " " + password);
		
		//TODO NEXT: Implement login
	}
}
