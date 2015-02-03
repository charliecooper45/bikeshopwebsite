package controllers;

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
		String errorMessage = null;
		
		// collect payment details
		try {
			String cardNumber = request.getParameter("cardNumber");
			String expiryDate = request.getParameter("expiryDate");
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Date date = format.parse(expiryDate);
			User user = (User) request.getSession().getAttribute("user");
			new Payment(cardNumber, date, user, true);
			
			//TODO: validate payment and add order
		} catch (ParseException e) {
			errorMessage = "Date is in an incorrect format";
			request.setAttribute("validateMessage", errorMessage);
			forwardToPage("/pay.jsp", request, response);
		}
	}

	private void doFinishAndPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = "/pay.jsp";
		request.setAttribute("validateMessage", "");
		forwardToPage(jspPage, request, response);
	}
}
