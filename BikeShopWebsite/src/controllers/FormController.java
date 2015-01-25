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

		//TODO: should always return to previous page after doing a login/register
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

		String jspPage = "/index.jsp";
		forwardToPage(jspPage, request, response);
	}

	private void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("Attempt to register user");
		String jspPage = "/register.jsp";
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String firstName = request.getParameter("firstName");
		String surname = request.getParameter("surname");

		// check passwords match
		if (!password.equals(confirmPassword)) {
			LOG.info("Passwords do not match");
			request.setAttribute("validationMessage", "Passwords do not match.");
		} else {
			// check an account does not already exist with the given email
			// address
			Query namedQuery = session.getNamedQuery(User.QUERY_USER_BY_EMAIL);
			LOG.info("Checking for existing user with email: " + email);
			namedQuery.setParameter("email", email);
			User user = (User) namedQuery.uniqueResult();

			if (user != null) {
				LOG.info("Email is already in use");
				request.setAttribute("validationMessage", "Email: " + email + " already registered.");
			} else {
				LOG.info("Email not in use, register new user.");
				String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
				Transaction tx = session.beginTransaction();
				user = new User(email, hashedPassword, firstName, surname);
				session.save(user);
				tx.commit();
				jspPage = "/registered.jsp";
			}
		}

		forwardToPage(jspPage, request, response);
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

		if (user == null) {
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

		forwardToPage(jspPage, request, response);
	}
}
