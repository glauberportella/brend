<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<h1 class="page-header">Empresa</h1>
	
	<c:url var="addAction" value="/empresa/atualizar"></c:url>
	<form:form action="${addAction}" commandName="configEmpresa" class="form-horizontal">
	
	<fieldset>
	
	<!-- Form Name -->
	<legend>Atualizar</legend>
	
	<c:if test="${not empty sucesso}">
		<div class="alert alert-success" role="alert">${sucesso}</div>
	</c:if>
	
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
	  <label class="col-md-4 control-label" for="nomeFantasia">Nome</label>  
	  <div class="col-md-4">
	  <form:input path="nomeFantasia" placeholder="Nome Fantasia" required="true" class="form-control input-md"/>  
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="razaoSocial">Razão Social</label>  
	  <div class="col-md-4">
	  <form:input path="razaoSocial" placeholder="Razão Social" required="true" class="form-control input-md"/>
	    
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="cnpj">CNPJ</label>  
	  <div class="col-md-4">
	  <form:input type="number" path="cnpj" placeholder="CNPJ" required="true" class="form-control input-md"/>
	    
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="endereco">Endereço</label>  
	  <div class="col-md-4">
	  <form:input path="endereco" placeholder="Endereço" required="true" class="form-control input-md"/>
	    
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="telefone">Telefone</label>  
	  <div class="col-md-4">
	  <form:input path="telefone" placeholder="Telefone" required="true" class="form-control input-md"/>
	    
	  </div>
	</div>

	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="markup">Markup</label>  
	  <div class="col-md-4">
	  <form:input path="markup" placeholder="MarkUp" required="true" class="form-control input-md"/>
	  </div>
	</div>

	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="email">E-mail</label>  
	  <div class="col-md-4">
	  <form:input path="email" placeholder="E-mail" required="true" class="form-control input-md"/>  
	  </div>
	</div>

	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="smtpHost">Servidor SMTP</label>  
	  <div class="col-md-4">
	  <form:input path="smtpHost" placeholder="Servidor SMTP" required="true" class="form-control input-md"/>  
	  </div>
	</div>

	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="smtpLogin">Login SMTP</label>  
	  <div class="col-md-4">
	  <form:input path="smtpLogin" placeholder="Login SMTP" required="true" class="form-control input-md"/>  
	  </div>
	</div>

	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="smtpSenha">Senha SMTP</label>  
	  <div class="col-md-4">
	  <form:password path="smtpSenha" placeholder="Senha SMTP" required="true" class="form-control input-md"/>  
	  </div>
	</div>


	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for=""></label>
	  <div class="col-md-4">
	    <input class="btn btn-primary" type="submit" value="Salvar" />
	    <a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}/home" role="button">Cancelar</a>
	  </div>
	</div>
	
	</fieldset>
	</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>