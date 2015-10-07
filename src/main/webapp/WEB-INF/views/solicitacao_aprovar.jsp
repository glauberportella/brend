<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<h1 class="page-header">Solicitação de Retirada</h1>
	
	<c:url var="addAction" value="/solicitacao/inserirAprovacao"></c:url>
	<form:form action="${addAction}" commandName="solicitacao" class="form-horizontal">
	
	<fieldset>
	
	<!-- Form Name -->
	<legend>Visualizar</legend>

	<div class="alert alert-${tipoAlerta }" role="alert">${status }</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="id">ID</label>  
	  <div class="col-md-4">
	  <form:input  path="id" readonly="true" size="8" disabled="true" class="form-control input-md" />
	  <form:hidden path="id" />
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="solicitante">Solicitante</label>  
	  <div class="col-md-4">
	  <input type="text" readonly="readonly" class="form-control input-md" value="${solicitacao.solicitante.nome }"/>
	  </div>
	</div>

	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="data">Data da Solicitação</label>  
	  <div class="col-md-4">
	  <input type="text" readonly="readonly" class="form-control input-md" value="${solicitacao.dataSolicitacaoFormatada }"/>
	  </div>
	</div>

	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="solicitante">Autorizador</label>  
	  <div class="col-md-4">
	  <input type="text" readonly="readonly" class="form-control input-md" value="${solicitacao.autorizador.nome }"/>
	  </div>
	</div>

	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="data">Ordem de Servico</label>  
	  <div class="col-md-4">
	  <input type="text" readonly="readonly" class="form-control input-md" value="${solicitacao.ordemServicoIdData }"/>
	  </div>
	</div>
	
	<div>
		<table class="table table-condensed table-hover table-striped" id="table-pecas">
		    <thead>
		        <tr>
		            <th data-column-id="id" data-type="numeric">ID</th>
		            <th data-column-id="nome">Nome</th>
		            <th data-column-id="qtde">Qtde</th>
		        </tr>
		    </thead>
		    <tbody>
			<c:forEach items="${listaPecas}" var="item" >
				<tr>
		            <td>${item.peca.id }</td>
		            <td>${item.peca.nome }</td>
		            <td>${item.qtde }</td>
		        </tr>
			</c:forEach>
		    </tbody>
		</table>
	</div>
	
	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for=""></label>
	  <div class="col-md-4">
	  	<input class="btn btn-primary" type="submit" value="Salvar" />
	    <a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}/solicitacao/listarPendentes" role="button">Voltar</a>
	  </div>
	</div>
	
	</fieldset>
	</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>