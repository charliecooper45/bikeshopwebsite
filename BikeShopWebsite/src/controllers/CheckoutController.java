package controllers;

import hibernate.classes.Basket;
import hibernate.classes.BikeShopOrder;
import hibernate.classes.Payment;
import hibernate.classes.User;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

	private void doPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = null;
		String errorMessage = null;

		// collect payment details
		try {
			String cardNumber = request.getParameter("cardNumber");
			String expiryDate = request.getParameter("expiryDate");
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Date date = format.parse(expiryDate);
			User user = (User) request.getSession().getAttribute("user");
			
			Payment payment = new Payment(cardNumber, date, user, true);
			boolean paymentValidated = validatePayment(payment);

			if (paymentValidated) {
				// TODO: validate payment and add order
				// TODO: add logging
				// TODO: only show bikes that are not in a current order
				
				Transaction tx = session.beginTransaction();
				Query namedQuery = session.getNamedQuery(Basket.QUERY_BY_USER);
				namedQuery.setParameter("user", user);
				Basket basket = (Basket) namedQuery.uniqueResult();
				session.save(payment);
				BikeShopOrder order = new BikeShopOrder(user, basket.getBikes(), payment);
				session.save(order);
				
				session.delete(basket);
				tx.commit();
				
				jspPage = "/index.jsp";
			} else {
				// TODO: show error page
			}
		} catch (ParseException e) {
			jspPage = "/pay.jsp";
			errorMessage = "Date is in an incorrect format";
			request.setAttribute("validateMessage", errorMessage);
		}
		forwardToPage(jspPage, request, response);
	}

	private boolean validatePayment(Payment payment) {
		LOG.info("Attempting to validate payment for user " + payment.getUser().getFirstName() + " " + payment.getUser().getSurname());
		
		LOG.info("Payment validated");
		return true;
	}

	private void doFinishAndPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = "/pay.jsp";
		request.setAttribute("validateMessage", "");
		forwardToPage(jspPage, request, response);
	}
}
