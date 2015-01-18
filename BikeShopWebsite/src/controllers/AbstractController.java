package controllers;

import java.io.IOException;

import hibernate.utils.HibernateUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	public void forwardToPage(String jspPage, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("Forwarding to page: " + jspPage);
		getServletContext().getRequestDispatcher(jspPage).forward(request, response);
	}
}
