<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="loginTemplate">
    <tiles:putAttribute name="body">

	<h1>${title}</h1>
	<h2>${message}</h2>
	
	<p><a href="login">Login</a>
	
    </tiles:putAttribute>
</tiles:insertDefinition>