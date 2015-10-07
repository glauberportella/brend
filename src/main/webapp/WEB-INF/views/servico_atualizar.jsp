<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<h1 class="page-header">Serviço</h1>
	
	<c:url var="addAction" value="/servico/inserir"></c:url>
	<form:form action="${addAction}" commandName="servico" modelAttribute="servico" class="form-horizontal">
	
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
	  <label class="col-md-4 control-label" for="descricao">Descrição</label>  
	  <div class="col-md-4">
	  <form:textarea path="descricao" placeholder="Descricao" class="form-control" rows="5" id="descricao"/>
	  <form:errors path="nome" cssClass="label label-danger"/>
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="nome">Tipo de Serviço</label>  
	  <div class="col-md-4">
	  <form:select path="tipoServico" class="form-control">
	    <c:forEach var="item" items="${listaTipoServico}">
		    <c:choose>
		        <c:when test="${servico.tipoServico.id eq item.key}">
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
	  <label class="col-md-4 control-label" for="nome">Preço</label>  
	  <div class="col-md-4">
	  <input type="text" value="<fmt:formatNumber value="${servico.preco}" type="currency"/>"  class="form-control" readonly="readonly"/>
	  </div>
	</div>
	
	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for=""></label>
	  <div class="col-md-4">
	    <input class="btn btn-primary" type="submit" value="Salvar" />
	    <a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}/servico/listar" role="button">Cancelar</a>
	    <c:if test="${servico.id gt 0 }">
	    	<a class="btn btn-default" href="${requestScope['javax.servlet.forward.context_path']}/tarifarioServico/listar/${servico.id}" role="button">Lista preços</a>
	    </c:if>
	  </div>
	</div>
	
	</fieldset>
	</form:form>
	<script type="text/javascript">
	function validaDat(campo,valor) {
		var date=valor;
		var ardt=new Array;
		var ExpReg=new RegExp("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}");
		ardt=date.split("/");
		erro=false;
		if ( date.search(ExpReg)==-1){
			erro = true;
			}
		else if (((ardt[1]==4)||(ardt[1]==6)||(ardt[1]==9)||(ardt[1]==11))&&(ardt[0]>30))
			erro = true;
		else if ( ardt[1]==2) {
			if ((ardt[0]>28)&&((ardt[2]%4)!=0))
				erro = true;
			if ((ardt[0]>29)&&((ardt[2]%4)==0))
				erro = true;
		}
		if (erro) {
			alert(" " + valor + " não é uma data válida!!!");
			campo.focus();
			campo.value = "";
			return false;
		}
		return true;
	}
	</script>

	</tiles:putAttribute>
</tiles:insertDefinition>