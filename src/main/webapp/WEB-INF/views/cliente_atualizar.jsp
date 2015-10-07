<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<h1 class="page-header">Cliente</h1>
	
	<c:url var="addAction" value="/cliente/inserir"></c:url>
	<form:form action="${addAction}" commandName="cliente" class="form-horizontal" method="post" modelAttribute="cliente" >
	
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
	  <label class="col-md-4 control-label" for="tipoPessoa">Tipo Pessoa</label>  
	  <div class="col-md-4">
	  <form:select path="tipoPessoa" class="form-control" >
	  	<form:option value="${Fisica}" selected="${fisicaSelecionada }">Fisica</form:option>
	  	<form:option value="${Juridica}" selected="${juridicaSelecionada }">Juridica</form:option>
	  </form:select>
	  </div>
	</div>
				
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="razao">Razão</label>  
	  <div class="col-md-4">
	  <form:input path="razao" placeholder="Razão" required="true" class="form-control input-md"/>
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
	
	<!-- Select Basic -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="tipoDocumento">Tipo de documento</label>
	  <div class="col-md-4">
	    <form:select path="tipoDocumento" class="form-control">
	    <c:forEach var="item" items="${listaTipoDocumentos}">
		    <c:choose>
		        <c:when test="${cliente.tipoDocumento.id eq item.key}">
		            <form:option selected="true" value="${item.key}" label="${item.value }"/>
		        </c:when>
		
		        <c:otherwise>
		            <form:option value="${item.key}" label="${item.value }"/>
		        </c:otherwise>
		    </c:choose>
		</c:forEach>
	    </form:select>
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="numeroDocumento">Numero do Documento</label>  
	  <div class="col-md-4">
	  <form:input path="numeroDocumento" placeholder="Numero do Documento" required="true" class="form-control input-md" />
	  <form:errors path="numeroDocumento" cssClass="label label-danger"/>
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="nascimento">Data de Nascimento</label>  
	  <div class="col-md-4">
	  <form:input path="nascimento" placeholder="Data de Nascimento" required="true" class="form-control input-md" value="${cliente.nascimentoFormatado}"/>
	  <form:errors path="nascimento" cssClass="label label-danger"/>
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="endereco">Endereço</label>  
	  <div class="col-md-4">
	  <form:input path="endereco" placeholder="Endereço" required="true" class="form-control input-md"/>
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
	  <form:input type="email" path="email" placeholder="E-mail" required="true" class="form-control input-md" />
	  <form:errors path="email" cssClass="label label-danger"/>
	  </div>
	</div>
	
	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for=""></label>
	  <div class="col-md-4">
	    <input class="btn btn-primary" type="submit" value="Salvar" />
	    <a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}/cliente/listar" role="button">Cancelar</a>
	  </div>
	</div>
	
	</fieldset>
	</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>