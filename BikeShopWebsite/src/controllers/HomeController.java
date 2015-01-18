package controllers;

import hibernate.classes.Brand;
import hibernate.utils.HibernateUtilities;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends AbstractController {
	private static final long serialVersionUID = 1L;

	static {
		HibernateUtilities.createTestData();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = null;
		String action = request.getParameter("action");

		if (action == null) {
			LOG.info("Forwarding to page: " + jspPage);
			getServletContext().getRequestDispatcher(jspPage).forward(request, response);
		} else {
			switch (action) {
			case "login":
				goToLogin(request, response);
				break;
			case "logout":
				goToLogout(request, response);
				break;
			case "register":
				goToRegister(request, response);
				break;
			case "viewbrands":
				doLookupBikeBrands(request, response);
				break;
			}
		}
	}
	
	private void goToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("validationMessage", "");
		request.setAttribute("email", "");
		String jspPage = "/login.jsp";
		forwardToPage(jspPage, request, response);
	}
	
	private void goToLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = "/logout.jsp";
		forwardToPage(jspPage, request, response);
	}
	
	private void goToRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("validationMessage", "");
		String jspPage = "/register.jsp";
		forwardToPage(jspPage, request, response);
	}

	@SuppressWarnings("unchecked")
	private void doLookupBikeBrands(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Query namedQuery = session.getNamedQuery(Brand.QUERY_ALL);
		List<Brand> brands = namedQuery.list();
		LOG.info("Found " + brands.size() + " brands in database");
		Collections.sort(brands);
		request.setAttribute("brands", brands);
		
		String jspPage = "/brands.jsp";
		forwardToPage(jspPage, request, response);
	}
}
