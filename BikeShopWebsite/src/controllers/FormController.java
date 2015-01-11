package controllers;

import hibernate.classes.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Transaction;

import security.BCrypt;

/**
 * Servlet implementation class FormController
 */
@WebServlet("/FormController")
public class FormController extends AbstractController {
	private static final long serialVersionUID = 1L;

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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String firstName = request.getParameter("firstName");
		String surname = request.getParameter("surname");

		// check passwords match
		if (!password.equals(confirmPassword)) {
			request.setAttribute("validationMessage", "Passwords do not match.");
		} else {
			// check an account does not already exist with the given email address
			Query namedQuery = session.getNamedQuery(User.QUERY_USER_BY_EMAIL);
			LOG.info("Checking for existing user with email: " + email);
			namedQuery.setParameter("email", email);
			User user = (User) namedQuery.uniqueResult();
			
			if (user != null) {
				LOG.info("Email is already in use");
				request.setAttribute("validationMessage", "Email: " + email + " already registered.");
			} else {
				//TODO: how is the situation where database connection is lost handled?
				LOG.info("Email not in use, register new user.");
				String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
				Transaction tx = session.beginTransaction();
				user = new User(email, hashedPassword, firstName, surname);
				session.save(user);
				tx.commit();
				jspPage = "/registered.jsp";
			}
		}

		getServletContext().getRequestDispatcher(jspPage).forward(request, response);
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = null;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// check if an account with the given email address exists
		Query namedQuery = session.getNamedQuery(User.QUERY_USER_BY_EMAIL);
		LOG.info("Checking for existing user with email: " + email);
		namedQuery.setParameter("email", email);
		User user = (User) namedQuery.uniqueResult();
		
		if(user == null) {
			LOG.info("The user with email address " + email + " does not exist.");
			request.setAttribute("email", email);
			request.setAttribute("validationMessage", "Email address not recognised");
			jspPage = "/login.jsp";
		} else {
			LOG.info("User with email address " + email + " exists. Check password...");
			if (!BCrypt.checkpw(password, user.getPassword())) {
				LOG.info("Password is incorrect");
				request.setAttribute("email", user.getEmail());
				request.setAttribute("validationMessage", "Incorrect password");
				jspPage = "/login.jsp";
			} else {
				LOG.info("Password is correct");
				jspPage = "/index.jsp";
				request.getSession().setAttribute("user", user);
			}
		}
		
		getServletContext().getRequestDispatcher(jspPage).forward(request, response);
//
//		Connection conn = null;
//
//		try {
//			conn = ds.getConnection();
//
//			User user = new User(email, password);
//			UserDao userDao = DaoFactory.getUserDao(conn);
//
//			boolean validated = false;
//
//			// validate the email
//			validated = userDao.validateEmail(user);
//
//			if (!validated) {
//				request.setAttribute("email", user.getEmail());
//				request.setAttribute("validationMessage", "Email address not recognised");
//				jspPage = "/login.jsp";
//			} else {
//				// email validated so validate the password
//				validated = userDao.validatePassword(user);
//				if (!validated) {
//					request.setAttribute("email", user.getEmail());
//					request.setAttribute("validationMessage", "Incorrect password");
//					jspPage = "/login.jsp";
//				} else {
//					// the user is now logged in
//					jspPage = "/index.jsp";
//					user = userDao.getUser(user.getEmail());
//					request.getSession().setAttribute("user", user);
//				}
//			}
//		} catch (SQLException e) {
//			request.setAttribute("validationMessage", "Cannot connect to database");
//			jspPage = "/login.jsp";
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		getServletContext().getRequestDispatcher(jspPage).forward(request, response);
	}
}
