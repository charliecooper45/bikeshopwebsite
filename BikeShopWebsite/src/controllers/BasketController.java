package controllers;

import hibernate.classes.Basket;
import hibernate.classes.BikeModel;
import hibernate.classes.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;

/**
 * Servlet implementation class FormController
 */
@WebServlet("/BasketController")
public class BasketController extends AbstractController {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addBikeToBasket(request);
	}
	
	private void addBikeToBasket(HttpServletRequest request) {
		//TODO: pass in the User here so it can be used in the query
		String bikeName = request.getParameter("bikeName");
		LOG.info("Adding bike " + bikeName + " to basket");
		
		//get updated version of bike from the database
		Query namedQuery = session.getNamedQuery(BikeModel.QUERY_BY_NAME);
		namedQuery.setParameter("name", bikeName);
		BikeModel bikeModel = (BikeModel) namedQuery.uniqueResult();
		LOG.info("Retrieved updated information for: " + bikeModel);
		
		// check if the user has an existing basket
		User user = (User) request.getSession().getAttribute("user");
		LOG.info("Check for existing basket for user: " + user);
		namedQuery = session.getNamedQuery(Basket.QUERY_BY_USER);
	}
}
