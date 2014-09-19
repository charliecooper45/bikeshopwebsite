package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import beans.User;
import database.UserDao;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DataSource ds;

	@Override
	public void init() throws ServletException {
		try {
			InitialContext initContext = new InitialContext();

			Context env = (Context) initContext.lookup("java:comp/env");

			ds = (DataSource) env.lookup("jdbc/bikeshopdb");
		} catch (NamingException e) {
			throw new ServletException();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = null;
		String action = request.getParameter("action");

		if (action == null) {
			jspPage = "/index.jsp";
		} else {
			switch (action) {
			case "login":
				request.setAttribute("validationmessage", "");
				request.setAttribute("email", "");
				jspPage = "/login.jsp";
				break;
			}
		}

		getServletContext().getRequestDispatcher(jspPage).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = null;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Connection conn = null;
		
		try {
			conn = ds.getConnection();
			
			User user = new User(email, password);
			UserDao userDao = new UserDao(conn);
			
			boolean validated = false;
			
			// validate the email
			validated = userDao.validateEmail(email);
			
			if(!validated) {
				request.setAttribute("email", user.getEmail());
				request.setAttribute("validationmessage", "Email address not recognised");
				jspPage = "/login.jsp";
			} else {
				// email validated so validate the password
				validated = userDao.validatePassword(password);
				if(!validated) {
					request.setAttribute("email", user.getEmail());
					request.setAttribute("validationmessage", "Incorrect password");
					jspPage = "/login.jsp";
				} else {
					jspPage = "/index.jsp";
				}
			}
		} catch (SQLException e) {
			request.setAttribute("validationmessage", "Cannot connect to database");
			jspPage = "/login.jsp";
		}
		

		
		getServletContext().getRequestDispatcher(jspPage).forward(request, response);
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
