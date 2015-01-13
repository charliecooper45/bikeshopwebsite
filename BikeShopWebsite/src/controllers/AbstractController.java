package controllers;

import hibernate.utils.HibernateUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.hibernate.Session;

public abstract class AbstractController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected static final Logger LOG = Logger.getLogger(AbstractController.class);
	
	protected Session session;
	
	@Override
	public void init() throws ServletException {
		session = HibernateUtilities.getSessionFactory().openSession();
	}
	
	@Override
	public void destroy() {
		HibernateUtilities.shutdown();
	}
}
