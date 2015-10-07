<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<h1 class="page-header">Veiculo</h1>
	
	<c:url var="addAction" value="/veiculo/inserir"></c:url>
	<form:form action="${addAction}" commandName="veiculo" class="form-horizontal">
	
	<fieldset>
	
	<!-- Form Name -->
	<legend>Veiculo</legend>
	
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
	  <label class="col-md-4 control-label" for="marca">Marca</label>  
	  <div class="col-md-4">
	  <form:input path="marca" placeholder="Marca" required="true" class="form-control input-md"/>  
	  <form:errors path="marca" cssClass="label label-danger"/>
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="modelo">Modelo</label>  
	  <div class="col-md-4">
	  <form:input path="modelo" placeholder="Nome" required="true" class="form-control input-md"/>  
	  <form:errors path="modelo" cssClass="label label-danger"/>
	  </div>
	</div>
	
	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for=""></label>
	  <div class="col-md-4">
	    <input class="btn btn-primary" type="submit" value="Salvar" />
	    <a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}/veiculo/listar" role="button">Cancelar</a>
	  </div>
	</div>
	
	</fieldset>
	</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>