<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<h1 class="page-header">Tipo de Funcionário</h1>
	
	<c:url var="addAction" value="/tipoFuncionario/inserir"></c:url>
	<form:form action="${addAction}" commandName="tipoFuncionario" class="form-horizontal">
	
	<fieldset>
	
	<!-- Form Name -->
	<legend>Tipo de Funcionário</legend>
	
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
	  <label class="col-md-4 control-label" for="nome">Nome</label>  
	  <div class="col-md-4">
	  <form:input path="nome" placeholder="Nome" required="true" class="form-control input-md"/>  
	  <form:errors path="nome" cssClass="label label-danger" />
	  </div>
	</div>
	
	<div class="form-group">
	  <label class="col-md-4 control-label" for="">É mecânico?</label>
	  <div class="col-md-4">
		<div class="input-group">
	      <span class="input-group-addon">     
	          <form:checkbox path="mecanico" value="1"/>     
	      </span>
	      <input id="descmecanico" type="text" class="form-control input-md" readonly="readonly" value="${isMecanico }" >
	    </div>
	  	
	  </div>
	</div>
	
	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for=""></label>
	  <div class="col-md-4">
	    <input class="btn btn-primary" type="submit" value="Salvar" />
	    <a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}/tipoFuncionario/listar" role="button">Cancelar</a>
	  </div>
	</div>
	
	</fieldset>
	</form:form>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$( "#mecanico1" ).click(function(){
				if ($(this).prop("checked")) {
					$( "#descmecanico" ).val("Sim");
				} else {
					$( "#descmecanico" ).val("Não");
				}
			});
		})
	</script>

	</tiles:putAttribute>
</tiles:insertDefinition>