<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Bike Shop</title>
	<link rel="stylesheet" type="text/css" href="css/basket.css">
	<script src="javascript/basket.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h3>Your basket:</h3>
	<div id="basket">
		<c:forEach items="${requestScope.basket.bikes}" var="bike" varStatus="i">
			<div class="bike">
				<div class="bikeInformation">
					<h4>${bike.bikeModel.brand.name} ${bike.bikeModel.name}</h4>
				 	Serial Number: ${bike.serialNumber}
				</div>
				<div class="removeFromBasket">
					<form action="/BikeShopWebsite/BasketController?formType=removeFromBasket" method="post">
						<input type="submit" id="${bike.serialNumber}" value="Remove" onclick="return removeFromBasket(this.id)" />
						<input type="hidden" name="serialNumber" value="${bike.serialNumber}"/>
					</form>
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