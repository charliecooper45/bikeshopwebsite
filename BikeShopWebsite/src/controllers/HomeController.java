package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import database.DaoFactory;
import beans.Brand;

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
			case "viewbikes":
				doLookupBikeBrands(request, response);
				jspPage = "/bikes.jsp";
				break;
			}
		}

		getServletContext().getRequestDispatcher(jspPage).forward(request, response);
	}

	private void doLookupBikeBrands(HttpServletRequest request, HttpServletResponse response) {
		Connection conn =  null;
		try {
			conn = ds.getConnection();
			List<Brand> brands = DaoFactory.getBrandDao(conn).getBrands();
			request.setAttribute("brands", brands);
		} catch (SQLException e) {
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
