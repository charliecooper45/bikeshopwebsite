<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Bike Shop</title>
	<link rel="stylesheet" type="text/css" href="css/error.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="errorMessage">
		<p class="error">Unable to checkout, payment error</p>
		<p class="error">Return to <a href="/BikeShopWebsite/CheckoutController" >checkout</a></p>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>