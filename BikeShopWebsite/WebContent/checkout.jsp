<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Bike Shop</title>
	<link rel="stylesheet" type="text/css" href="css/checkout.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<h3>Checkout</h3>
	<div id="checkout">
		${sessionScope.user.firstName}, the items in your basket:
		<ol>
			<c:forEach items="${requestScope.basket.bikes}" var="bike" varStatus="i">
				<li>${bike.bikeModel.brand.name} ${bike.bikeModel.name}: ${bike.bikeModel.price}</li>			
			</c:forEach>
		</ol>
		<form action="/BikeShopWebsite/CheckoutController?formType=finishAndPay" method="post">
			<input type="submit" value="Finish and pay"/>
		</form>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>