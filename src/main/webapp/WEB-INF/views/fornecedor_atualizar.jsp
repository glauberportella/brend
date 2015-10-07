<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<h1 class="page-header">Fornecedor</h1>
	
	<c:url var="addAction" value="/fornecedor/inserir"></c:url>
	<form:form action="${addAction}" commandName="fornecedor" class="form-horizontal">
	<fieldset>
	
	<!-- Form Name -->
	<legend>Cadastrar/Atualizar</legend>
	
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
	  <label class="col-md-4 control-label" for="razao">Razão</label>  
	  <div class="col-md-4">
	  <form:input path="razao" placeholder="Razao" required="true" class="form-control input-md"/>  
	  <form:errors path="razao" cssClass="label label-danger"/>
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="fantasia">Fantasia</label>  
	  <div class="col-md-4">
	  <form:input path="fantasia" placeholder="Fantasia" required="true" class="form-control input-md"/>  
	  <form:errors path="fantasia" cssClass="label label-danger"/>
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="cnpj">CNPJ</label>  
	  <div class="col-md-4">
	  <form:input path="cnpj" placeholder="CNPJ" required="true" class="form-control input-md"/>  
	  <form:errors path="cnpj" cssClass="label label-danger"/>
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="endereco">Endereço</label>  
	  <div class="col-md-4">
	  <form:input path="endereco" placeholder="Endereco" required="true" class="form-control input-md"/>  
	  <form:errors path="endereco" cssClass="label label-danger"/>
	  </div>
	</div>	
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="telefone">Telefone</label>  
	  <div class="col-md-4">
	  <form:input path="telefone" placeholder="Telefone" required="true" class="form-control input-md"/>  
	  <form:errors path="telefone" cssClass="label label-danger"/>
	  </div>
	</div>	
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="email">E-mail</label>  
	  <div class="col-md-4">
	  <form:input path="email" placeholder="E-mail" required="true" class="form-control input-md"/>  
	  <form:errors path="email" cssClass="label label-danger"/>
	  </div>
	</div>
	
	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for=""></label>
	  <div class="col-md-4">
	    <input class="btn btn-primary" type="submit" value="Salvar" />
	    <a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}/fornecedor/listar" role="button">Cancelar</a>
	  </div>
	</div>
	
	</fieldset>
	</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>