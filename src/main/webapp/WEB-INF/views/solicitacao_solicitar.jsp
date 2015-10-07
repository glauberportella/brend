<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<h1 class="page-header">Solicitação de Retirada</h1>
	
	<c:url var="addAction" value="/solicitacao/inserirSolicitacao"></c:url>
	<form:form action="${addAction}" commandName="solicitacao" class="form-horizontal">
	
	<fieldset>
	
	<!-- Form Name -->
	<legend>Cadastrar</legend>
	
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
	  <form:hidden path="solicitante"  value="${solicitacao.solicitante.id }"/>  
	  </div>
	</div>

	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="ordemServico">Número da OS</label>  
	  <div class="col-md-3">
	  	<form:input type="number" path="ordemServico" placeholder="Número da OS" required="true" class="form-control input-md" />
	  </div>
	  <input type="button" id="btnBuscaOS" class="btn btn-info" value="Buscar">
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
		    <tbody class="lista-pecas-corpo">

		    </tbody>
		</table>
	</div>
	
	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for=""></label>
	  <div class="col-md-4">
	    <input class="btn btn-primary" type="submit" value="Salvar" />
	    <a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}/solicitacao/listar" role="button">Cancelar</a>
	  </div>
	</div>
	
	</fieldset>
	</form:form>

	<script type="text/javascript">
		$(document).ready(function() {
			$( "#btnBuscaOS" ).click(function(){
				$( ".lista-pecas-corpo" ).html("");
				$.post( "listarjson/pecasordemservico", {
					ordemServicoId: parseInt($( "#ordemServico" ).val() != "" ? $( "#ordemServico" ).val() : 0)   
				}, function( data ) {
					if (data.length > 0) {
						for(i = 0; i < data.length; i++) {
							var item = data[i];
							$( "#table-pecas" ).append( '<tr> '
						            + '<td>' + item.peca.id + '</td> '
						            + '<td>' + item.peca.nome + '</td> '
						            + '<td>' + item.qtde + '</td> '
						            + '</tr>' );
						}
					} else {
						if ($( "#ordemServico" ).val() == "") {
							alert("O campo Número da OS não pode ser vazio.");	
						} else {
							alert("Ordem de servido de número " + $( "#ordemServico" ).val() + " não encontrada.")
						}
					}
				});	
			});
		});
	</script>
	</tiles:putAttribute>
</tiles:insertDefinition>