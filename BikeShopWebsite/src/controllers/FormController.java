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
 * Servlet implementation class FormController
 */
@WebServlet("/FormController")
public class FormController extends HttpServlet {
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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String form = request.getParameter("formType");

		switch (form) {
		case ("login"):
			doLogin(request, response);
			break;
		case ("logout"):
			doLogout(request, response);
			break;
		case ("register"):
			doRegister(request, response);
			break;
		}
	}

	private void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = "/register.jsp";
		Connection conn = null;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		// check passwords match
		if (!password.equals(confirmPassword)) {
			request.setAttribute("validationMessage", "Passwords do not match.");
		} else {
			try {
				conn = ds.getConnection();
				User user = new User(email, password);
				UserDao userDao = new UserDao(conn);

				// check account does not exist with email address 
				boolean emailInUse = userDao.validateEmail(user);

				if (emailInUse) {
					request.setAttribute("validationMessage", "Email: " + email + " already registered.");
				} else {
					boolean added = userDao.registerUser(user);
					if (!added) {
						request.setAttribute("validationMessage", "Cannot connect to database");
					}
				}
			} catch (SQLException e) {
				request.setAttribute("validationMessage", "Cannot connect to database");
			}
		}

		getServletContext().getRequestDispatcher(jspPage).forward(request, response);
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			validated = userDao.validateEmail(user);

			if (!validated) {
				request.setAttribute("email", user.getEmail());
				request.setAttribute("validationMessage", "Email address not recognised");
				jspPage = "/login.jsp";
			} else {
				// email validated so validate the password
				validated = userDao.validatePassword(user);
				if (!validated) {
					request.setAttribute("email", user.getEmail());
					request.setAttribute("validationMessage", "Incorrect password");
					jspPage = "/login.jsp";
				} else {
					// the user is now logged in
					jspPage = "/index.jsp";
					request.getSession().setAttribute("username", user.getEmail());
				}
			}
		} catch (SQLException e) {
			request.setAttribute("validationMessage", "Cannot connect to database");
			jspPage = "/login.jsp";
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		getServletContext().getRequestDispatcher(jspPage).forward(request, response);
	}
}
