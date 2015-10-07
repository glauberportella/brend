<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<c:out value="${renderBootgridJS}" escapeXml="false"/>
	
	<h1 class="page-header">Tabela de pre�os do Servico: ${servico.nome }</h1>
	
	<a class="btn btn-default" href="../telaInserir/${servico.id }" role="button">Novo Pre�o</a>
	<a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}/servico/telaAtualizar/${servico.id }" role="button">Voltar para Servi�o</a>
	<c:out value="${renderBootgridGrid}" escapeXml="false"/>


	</tiles:putAttribute>
</tiles:insertDefinition>