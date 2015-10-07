<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<h1 class="page-header">Status da Ordem de Servico ${ordemServicoId }</h1>
	
	<c:url var="addAction" value="/registroContato/alterarStatus"></c:url>
	<form action="${addAction}" method="post" class="form-horizontal" id="frmAlterarStatus">
	
	<fieldset>
	
	<!-- Form Name -->
	<legend>Alterar</legend>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="motivo">Motivo</label>  
	  <div class="col-md-4">
	  <textarea id="motivo" name="motivo" required="required" class="form-control input-md"></textarea>  
	  </div>
	</div>
	
	<div class="form-group">
	  <label class="col-md-4 control-label" for="motivo">Status</label>  
	  <div class="col-md-4">
	  	<input type="text" id="status" name="status" class="form-control" value="${status }" readonly="readonly">
	  </div>
	</div>
	
	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for=""></label>
	  <div class="col-md-4">
	    <input class="btn btn-primary" type="submit" value="Salvar" />
	    <a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}/ordemServico/telaAtualizar/${ordemServicoId }" role="button">Cancelar</a>
	  </div>
	</div>
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" name="ordemServicoId" value="${ordemServicoId }"/>
	
	</fieldset>
	</form>

	<!-- Modal Load -->
	<div class="modal fade" id="loading" tabindex="-1" role="dialog" aria-labelledby="loadLabel" aria-hidden="true">
	  <div class="modal-dialog modal-sm">
	    <div class="modal-content">
	      <div class="modal-body">
	    	
	    	<p><img src="<c:url value="/resources/imagens/loading.gif"/>" /> Enviando...</p>  
			    
	      </div>
	    </div>
	  </div>
	</div>
	<!-- Fim modal load -->

	<script type="text/javascript">
		$(document).ready(function(){
			$( "#frmAlterarStatus" ).submit(function(){
				$('#loading').modal("show");
			});
		});
	</script>

	</tiles:putAttribute>
</tiles:insertDefinition>