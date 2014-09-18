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
		
		if(action == null) {
			jspPage = "/index.jsp";
		} else {
			switch(action) {
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
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		User user = new User(email, password);
		UserDao userDao = new UserDao(conn);
		
		boolean validated = userDao.checkLogin(email, password);
		
		if(validated) {
			jspPage = "/index.jsp";
		} else {
			request.setAttribute("email", user.getEmail());
			request.setAttribute("validationmessage", user.getErrorMessage());
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
