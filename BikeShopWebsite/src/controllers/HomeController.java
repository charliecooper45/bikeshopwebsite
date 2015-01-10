package controllers;

import hibernate.classes.Brand;
import hibernate.utils.HibernateUtilities;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(HomeController.class);
	
	private DataSource ds;
	private Session session;

	@Override
	public void init() throws ServletException {
		//TODO: c3p0 connection pool?
		session = HibernateUtilities.getSessionFactory().openSession();
	}
	
	@Override
	public void destroy() {
		HibernateUtilities.shutdown();
	}

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
				doLookupBikeBrands(request, response);
				jspPage = "/brands.jsp";
				break;
			}
		}

		getServletContext().getRequestDispatcher(jspPage).forward(request, response);
	}

	@SuppressWarnings("unchecked")
	private void doLookupBikeBrands(HttpServletRequest request, HttpServletResponse response) {
		Query namedQuery = session.getNamedQuery(Brand.QUERY_ALL);
		List<Brand> brands = namedQuery.list();
		LOG.info("Found " + brands.size() + " brands in database");
		Collections.sort(brands);
		request.setAttribute("brands", brands);
	}
}
