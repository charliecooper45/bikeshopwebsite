<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Bike Shop</title>
	<link rel="stylesheet" type="text/css" href="css/bikes.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<div id="brand">
		<h2>${requestScope.brand}</h2>
	</div>
	<div id="bikes">
		<c:forEach items="${requestScope.bikeModels}" var="bikeModel" varStatus="i">
			<div class="bike">
				<div class="bikeImage">
				</div>
				<div class="bikeData">
					<div class="bikeName">
						<h3>${bikeModel.name}</h3>
						<div class="brand">
							Brand: ${bikeModel.brand.name}
						</div>
						<div class="price">
							Price: ${bikeModel.price}
						</div>
						<div class="addToBasket">
							 <button type="button">Add to basket</button> 
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<jsp:include page="footer.jsp" />
</body>
</html>