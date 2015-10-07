<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:importAttribute name="javascripts"/>
<tiles:importAttribute name="stylesheets"/>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>OMServiços</title>
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
	<tiles:insertAttribute name="header" />
	<div class="container-fluid">
		<div class="row">
            <tiles:insertAttribute name="menu" />
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            	<tiles:insertAttribute name="body" />
            </div>
        </div>
    </div>
   	<tiles:insertAttribute name="footer" />    
</body>
</html>