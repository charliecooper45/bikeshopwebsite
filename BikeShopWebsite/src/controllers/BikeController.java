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

import beans.BikeModel;
import beans.Brand;
import database.DaoFactory;

/**
 * Servlet implementation class BikeController
 */
@WebServlet("/BikeController")
public class BikeController extends HttpServlet {
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
		int brand = Integer.parseInt(request.getParameter("brand"));
		List<BikeModel> models = doLookupBikes(new Brand(brand, ""));
		
	}
	
	private List<BikeModel> doLookupBikes(Brand brand) {
		List<BikeModel> models = null;
		Connection conn =  null;
		
		try {
			conn = ds.getConnection();
			models = DaoFactory.getBikeModelDao(conn).getBikeModels(brand);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return models;
	}

}
