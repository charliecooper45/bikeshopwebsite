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

		if (action == null) {
			jspPage = "/index.jsp";
		} else {
			switch (action) {
			case "login":
				request.setAttribute("validationMessage", "");
				request.setAttribute("email", "");
				jspPage = "/login.jsp";
				break;
			case "logout":
				jspPage = "/logout.jsp";
				break;
			case "register":
				request.setAttribute("validationMessage", "");
				jspPage = "/register.jsp";
				break;
			}
		}

		getServletContext().getRequestDispatcher(jspPage).forward(request, response);
	}
}
