<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Bike Shop</title>
	<link rel="stylesheet" type="text/css" href="css/brands.css">
</head>
<body>
<jsp:include page="header.jsp" />

	<div id="brands">
		<c:forEach items="${requestScope.brands}" var="brand" varStatus="i">
			<div class="brand">
				<a href="/BikeShopWebsite/BikeController?brand=${brand.id}"><c:out value="${brand}" /></a>
			</div>
		</c:forEach>
	</div>
	
<jsp:include page="footer.jsp" />
</body>
</html>