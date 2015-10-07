<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:importAttribute name="javascripts"/>
<tiles:importAttribute name="stylesheets"/>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>OMServiços - Relatório</title>
	<!-- stylesheets -->
	<c:forEach var="css" items="${stylesheets}">
	    <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
	</c:forEach>
	<!-- end stylesheets -->
	
	<!-- scripts -->
	<c:forEach var="script" items="${javascripts}">
	    <script src="<c:url value="${script}"/>"></script>
	</c:forEach>
	<!-- end scripts -->
</head>
<body>
    <div class="container">
		<tiles:insertAttribute name="body" />
	</div> <!-- /container -->
</body>
</html>