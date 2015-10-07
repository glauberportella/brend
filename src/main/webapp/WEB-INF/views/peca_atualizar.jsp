<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<h1 class="page-header">Peça</h1>
	
	<c:url var="addAction" value="/peca/inserir"></c:url>
	<form:form action="${addAction}" commandName="peca" class="form-horizontal" method="post" modelAttribute="peca">
	
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
	  <label class="col-md-4 control-label" for="nome">Nome</label>  
	  <div class="col-md-4">
	  <form:input path="nome" placeholder="Nome" required="true" class="form-control"/>  
	  <form:errors path="nome" cssClass="label label-danger"/>
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="descricao">Descricao</label>  
	  <div class="col-md-4">
	  <form:textarea path="descricao" placeholder="Descricao" class="form-control" rows="5" id="descricao"/>
	   <form:errors path="descricao" cssClass="label label-danger"/>
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="nome">Marca</label>  
	  <div class="col-md-4">
	  <form:select path="marca" class="form-control">
	    <c:forEach var="item" items="${listaMarca}">
		    <c:choose>
		        <c:when test="${peca.marca.id eq item.key}">
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

	<div class="form-group">
	  <label class="col-md-4 control-label" for="id">Preço de Custo</label>  
	  <div class="col-md-4">
	  <input type="text" value="<fmt:formatNumber value="${peca.preco}" type="currency"/>" readonly="readonly" class="form-control" />
	  </div>
	</div>

	<div class="form-group">
	  <label class="col-md-4 control-label" for="id">Preço de Venda</label>  
	  <div class="col-md-4">
	  <input value="<fmt:formatNumber value="${peca.precoVenda}" type="currency"/>" readonly="readonly" class="form-control"/>
	  </div>
	</div>

	
	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for=""></label>
	  <div class="col-md-4">
	    <input class="btn btn-primary" type="submit" value="Salvar" />
	    <a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}/peca/listar" role="button">Cancelar</a>
	    <c:if test="${peca.id gt 0 }">
	    	<a class="btn btn-default" href="${requestScope['javax.servlet.forward.context_path']}/tarifarioPeca/listar/${peca.id}" role="button">Lista preços</a>
	    </c:if>
	  </div>
	</div>
	
	</fieldset>
	</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>