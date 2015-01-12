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
import org.hibernate.exception.JDBCConnectionException;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends AbstractController {
	private static final long serialVersionUID = 1L;

	// TODO: add logging

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
			case "viewbrands":
				boolean lookupSuccessful = doLookupBikeBrands(request, response);

				if (lookupSuccessful) {
					jspPage = "/brands.jsp";
				} else {
					//TODO: implement error.jsp
					jspPage = "/error.jsp";
				}
				break;
			}
		}

		getServletContext().getRequestDispatcher(jspPage).forward(request, response);
	}

	@SuppressWarnings("unchecked")
	private boolean doLookupBikeBrands(HttpServletRequest request, HttpServletResponse response) {
		try {
			Query namedQuery = session.getNamedQuery(Brand.QUERY_ALL);
			List<Brand> brands = namedQuery.list();
			LOG.info("Found " + brands.size() + " brands in database");
			Collections.sort(brands);
			request.setAttribute("brands", brands);
			return true;
		} catch (Exception e) {
			LOG.info("Connection to database lost");
			return false;
		}
	}
}
