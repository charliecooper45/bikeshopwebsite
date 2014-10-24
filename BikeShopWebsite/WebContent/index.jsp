<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Bike Shop</title>
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="javascript/slider.js"></script> 
</head>
<body>
<jsp:include page="header.jsp" />

	<body>
		<!-- Code adapted from http://codepen.io/zuraizm/pen/vGDHl/ -->
		<div id="slider">
		  <a href="#" class="control_next">&gt;</a>
		  <a href="#" class="control_prev">&lt;</a>
		  <ul>
		    <li>SLIDE 1</li>
		    <li style="background: #aaa;">SLIDE 2</li>
		    <li>SLIDE 3</li>
		    <li style="background: #aaa;"><a href="/BikeShopWebsite/HomeController?action=viewbrands">BIKES</a></li>
		  </ul>  
		</div>
	</body>

<jsp:include page="footer.jsp" />
</body>
</html>