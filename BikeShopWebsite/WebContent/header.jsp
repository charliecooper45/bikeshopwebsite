<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" href="css/header.css">
    </head>
    <body>
		<header>
			<div id="currentStatus">
				<c:choose>
					<c:when test='${sessionScope["user"] == null}'>
						You are not currently logged in.
					</c:when>
					<c:when test='${sessionScope["user"] != null}'>
						Welcome, <c:out value="${sessionScope.user.firstName}"/> 
					</c:when>
				</c:choose>
			</div>
			<div id="heading">
		  		<h1><a href="/BikeShopWebsite/HomeController">The Bike Shop</a></h1>
			</div>
		   	<div id="shoppingBasket">
		   		Shopping basket
		   	</div>
		   	<br>
		</header>
    </body>
</html>