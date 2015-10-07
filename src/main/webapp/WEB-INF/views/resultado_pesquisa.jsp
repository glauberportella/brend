<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<c:out value="${renderOrcamento[\"renderBootgridJS\"]}" escapeXml="false"/>
	<c:out value="${renderOrdemServico[\"renderBootgridJS\"]}" escapeXml="false"/>
	<c:out value="${renderCSS}" escapeXml="false"/>
	
	<h1 class="page-header">Resultado pesquisa</h1>
	
	<h2>Orçamento</h2>
	<c:out value="${renderOrcamento[\"renderBootgridGrid\"]}" escapeXml="false"/>
	<hr/>

	<h2>Ordem de Servico</h2>
	<c:out value="${renderOrdemServico[\"renderBootgridGrid\"]}" escapeXml="false"/>
	<hr/>


	</tiles:putAttribute>
</tiles:insertDefinition>