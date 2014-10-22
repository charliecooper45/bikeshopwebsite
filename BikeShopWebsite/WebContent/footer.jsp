<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/footer.css">
    </head>
	<footer>
	    	<a id="left" href="/BikeShopWebsite"><img src="resources/images/homeicon.png"></a>
			<c:choose>
				<c:when test='${sessionScope["user"] == null}'>
					<a id="right" href="/BikeShopWebsite/HomeController?action=login">Log In</a>
				</c:when>
				<c:when test='${sessionScope["user"] != null}'>
					<a id="right" href="/BikeShopWebsite/HomeController?action=logout">Log Out</a>
				</c:when>
			</c:choose>
	</footer>
</html>