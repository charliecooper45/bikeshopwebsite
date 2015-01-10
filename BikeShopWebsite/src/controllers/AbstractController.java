package controllers;

import hibernate.utils.HibernateUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.hibernate.Session;

public abstract class AbstractController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected static final Logger LOG = Logger.getLogger(HomeController.class);
	
	protected Session session;
	
	@Override
	public void init() throws ServletException {
		//TODO: c3p0 connection pool?
		session = HibernateUtilities.getSessionFactory().openSession();
	}
	
	@Override
	public void destroy() {
		HibernateUtilities.shutdown();
	}
}