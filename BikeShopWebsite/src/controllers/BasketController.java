package controllers;

import hibernate.classes.Basket;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAddBikeToBasket(request, response);
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
			addBikeToBasket(bikeName, basket);
		}
	}
	
	private void addBikeToBasket(String bikeName, Basket basket) {
		LOG.info("Checking for bike in stock for bike model: " + bikeName);
	}
}
