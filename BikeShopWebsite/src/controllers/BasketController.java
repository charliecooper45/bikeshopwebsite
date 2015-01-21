package controllers;

import hibernate.classes.Basket;
import hibernate.classes.Bike;
import hibernate.classes.BikeModel;
import hibernate.classes.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 * Servlet implementation class FormController
 */
@WebServlet("/BasketController")
public class BasketController extends AbstractController {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doShowBasket(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String formType = request.getParameter("formType");
		
		switch (formType) {
		case "addToBasket":
			doAddBikeToBasket(request, response);
			break;
		case "removeFromBasket":
			doRemoveBikeFromBasket(request, response);
			break;
		}
		LOG.info("Processing form type: " + formType);		
	}
	
	private void doShowBasket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		LOG.info("Show basket for user: " + user);
		Query namedQuery = session.getNamedQuery(Basket.QUERY_BY_USER);
		namedQuery.setParameter("user", user);
		Basket basket = (Basket) namedQuery.uniqueResult();
		request.setAttribute("basket", basket);
		String jspPage = "/basket.jsp";
		forwardToPage(jspPage, request, response);
	}
	
	private void doAddBikeToBasket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bikeName = request.getParameter("bikeName");
		LOG.info("Adding bike " + bikeName + " to basket");

		// check if a user is logged in (must be logged in to buy bikes)
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			LOG.info("No user logged in");
			String jspPage = "/loginOrRegister.jsp";
			forwardToPage(jspPage, request, response);
			//TODO: user should be returned to the basket page with the bike added after logging in or registering
		} else {
			LOG.info("Check for existing basket for user: " + user);
			Query namedQuery = session.getNamedQuery(Basket.QUERY_BY_USER);
			namedQuery.setParameter("user", user);
			Basket basket = (Basket) namedQuery.uniqueResult();
			if (basket == null) {
				LOG.info("Could not find basket for user: " + user);
				Transaction tx = session.beginTransaction();
				basket = new Basket(user);
				LOG.info("Basket: " + basket + " created for user: " + user);
				session.save(basket);
				tx.commit();
			} else {
				LOG.info("Found existing basket for user: " + user);
			}
			boolean bikeAdded = addBikeToBasket(bikeName, basket);
			
			String jspPage = null;
			if(bikeAdded) {
				jspPage = "/basket.jsp";
				request.setAttribute("basket", basket);
			} else {
				jspPage = "/basketError.jsp";
			}
			forwardToPage(jspPage, request, response);
		}
	}
	
	private boolean addBikeToBasket(String bikeName, Basket basket) {
		LOG.info("Looking up bike model: " + bikeName);
		Query namedQuery = session.getNamedQuery(BikeModel.QUERY_BY_NAME);
		namedQuery.setParameter("name", bikeName);
		BikeModel bikeModel = (BikeModel) namedQuery.uniqueResult();
		
		LOG.info("Checking for bike in stock for bike model: " + bikeName);
		namedQuery = session.getNamedQuery(Bike.QUERY_BY_BIKE_MODEL);
		namedQuery.setParameter("bikeModel", bikeModel);
		Bike bike = (Bike) namedQuery.uniqueResult();
		
		if(bike == null) {
			LOG.info("No bikes in stock for bike model: " + bikeName);
			return false;
		} else {
			Transaction tx = session.beginTransaction();
			LOG.info("Adding bike: " + bike + " to basket");
			basket.addBike(bike);
			tx.commit();
			return true;
		}
	}
	
	private void doRemoveBikeFromBasket(HttpServletRequest request, HttpServletResponse response) {
		String serialNumber = request.getParameter("serialNumber");
		LOG.info("Removing bike " + serialNumber + " from basket");
		
		User user = (User) request.getSession().getAttribute("user");
		System.out.println("User found: " + user);
		
		Basket basket = user.getBasket();
		System.out.println("Basket for user: " + basket);
		
	
	}
}
