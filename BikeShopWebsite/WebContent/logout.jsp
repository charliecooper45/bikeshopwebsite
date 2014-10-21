<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="css/logout.css">
	<title>Bike Shop</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<h2>Are you sure you wish to logout?</h2>
	
	<form action="/BikeShopWebsite/FormController" method="post">
		<input type="submit" value="Logout"/>
		<input type="hidden" name="formType" value="logout"/>
	</form>

	<jsp:include page="footer.jsp" />
</body>
</html>