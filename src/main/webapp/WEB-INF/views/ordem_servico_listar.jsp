<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<c:out value="${renderBootgridJS}" escapeXml="false"/>
	
	<h1 class="page-header">Ordem de Serviço ${status }</h1>

	<c:out value="${renderBootgridGrid}" escapeXml="false"/>


	</tiles:putAttribute>
</tiles:insertDefinition>