package controllers;

import hibernate.classes.Basket;
import hibernate.classes.Bike;
import hibernate.classes.BikeShopOrder;
import hibernate.classes.Payment;
import hibernate.classes.User;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 * Servlet implementation class CheckoutController
 */
@WebServlet("/CheckoutController")
public class CheckoutController extends AbstractController {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String formType = request.getParameter("formType");

		switch (formType) {
		case "finishAndPay":
			doFinishAndPay(request, response);
			break;
		case "pay":
			doPay(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = "/pay.jsp";
		request.setAttribute("validateMessage", "");
		forwardToPage(jspPage, request, response);
	}

	private void doPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = null;
		String errorMessage = null;

		// collect payment details
		try {
			String cardNumber = request.getParameter("cardNumber");
			String expiryDate = request.getParameter("expiryDate");
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Date date = format.parse(expiryDate);
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String county = request.getParameter("county");
			String postcode = request.getParameter("postcode");
			User user = (User) request.getSession().getAttribute("user");

			Payment payment = new Payment(cardNumber, date, user, true);
			boolean paymentValidated = validatePayment(payment);

			if (paymentValidated) {
				// TODO: add logging
				Transaction tx = session.beginTransaction();
				Query namedQuery = session.getNamedQuery(Basket.QUERY_BY_USER);
				namedQuery.setParameter("user", user);
				Basket basket = (Basket) namedQuery.uniqueResult();
				Set<Bike> bikes = basket.getBikes();
				basket.removeBikes();
				basket.removeUser();
				BikeShopOrder order = new BikeShopOrder(user, bikes, payment);
				
				session.delete(basket);
				session.save(payment);
				session.save(order);
				tx.commit();

				jspPage = "/index.jsp";
			} else {
				// set attributes
				request.getSession().setAttribute("address", address);
				request.getSession().setAttribute("city", city);
				request.getSession().setAttribute("county", county);
				request.getSession().setAttribute("postcode", postcode);
				
				jspPage = "/checkoutError.jsp";
			}
		} catch (ParseException e) {
			jspPage = "/pay.jsp";
			errorMessage = "Date is in an incorrect format";
			request.setAttribute("validateMessage", errorMessage);
		}
		forwardToPage(jspPage, request, response);
	}

	@SuppressWarnings("unused")
	private boolean validatePayment(Payment payment) {
		LOG.info("Attempting to validate payment for user " + payment.getUser().getFirstName() + " " + payment.getUser().getSurname());
		if (true) {
			LOG.info("Payment validation successful");
			return true;
		} else {
			LOG.info("Payment validation failed");
			return false;
		}
	}

	private void doFinishAndPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set attributes
		request.getSession().setAttribute("address", "");
		request.getSession().setAttribute("city", "");
		request.getSession().setAttribute("county", "");
		request.getSession().setAttribute("postcode", "");
		
		String jspPage = "/pay.jsp";
		request.setAttribute("validateMessage", "");
		forwardToPage(jspPage, request, response);
	}
}
