package controllers;

import java.io.IOException;

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

	private void doPay(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Do payment here!");
	}

	private void doFinishAndPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = "/pay.jsp";
		request.setAttribute("validateMessage", "");
		forwardToPage(jspPage, request, response);
	}
}
