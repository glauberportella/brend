<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:importAttribute name="loginStylesheets"/>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>OMServiços - Login</title>
	<!-- stylesheets -->
	<c:forEach var="css" items="${loginStylesheets}">
	    <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
	</c:forEach>
	<!-- end stylesheets -->	
	
</head>
<body>
    <div class="container">
		<tiles:insertAttribute name="body" />
	</div> <!-- /container -->
</body>
</html>