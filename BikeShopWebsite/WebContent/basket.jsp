<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Bike Shop</title>
	<link rel="stylesheet" type="text/css" href="css/basket.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<h3>Your basket:</h3>
	<div id="basket">
		<c:forEach items="${requestScope.basket.bikes}" var="bike" varStatus="i">
			<div class="bike">
				<div class="bikeName">
					<h4>${bike.bikeModel.brand.name} ${bike.bikeModel.name}</h4>
				</div>
				<div class="bikePrice">
				 	<h4>${bike.bikeModel.price}</h4>
				</div>
			</div>
		</c:forEach>
	</div>
	<div id="checkout">
		<input type="button" value="Checkout"/>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>