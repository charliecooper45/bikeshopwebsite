<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/footer.css">
    </head>
	<footer>
	    <!-- Login/Logout Button -->
	    <ul>
			<c:choose>
				<c:when test='${sessionScope["username"] == null}'>
					<li><a id="right" href="/BikeShopWebsite/HomeController?action=login">Log In</a></li>
				</c:when>
				<c:when test='${sessionScope["username"] != null}'>
					<li><a id="right" href="/BikeShopWebsite/HomeController?action=logout">Log Out</a></li>
				</c:when>
			</c:choose>
	    </ul>
	</footer>
</html>