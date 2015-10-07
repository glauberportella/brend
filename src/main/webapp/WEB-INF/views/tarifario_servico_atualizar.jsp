<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<h1 class="page-header">Preço do Serviço ${tarifarioServico.servico.nome }</h1>
	
	<c:url var="addAction" value="/tarifarioServico/inserir"></c:url>
	<form:form action="${addAction}" commandName="tarifarioServico" modelAttribute="tarifarioServico" class="form-horizontal">
	
	<fieldset>
	
	<!-- Form Name -->
	<legend>Cadastrar/Atualizar</legend>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="id">ID</label>  
	  <div class="col-md-4">
	  <form:input  path="id" readonly="true" size="8" disabled="true" class="form-control input-md" />
	  <form:hidden path="id" />
	  <form:hidden path="servico" value="${tarifarioServico.servico.id}"/>
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="data">Data</label>  
	  <div class="col-md-4">
	  <form:input path="data" placeholder="Data" required="true" class="form-control" value="${tarifarioServico.dataFormatada }" onblur="validaDat(this,this.value)"/>  
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="valor">Valor</label>  
	  <div class="col-md-4">
	  <form:input path="valor" placeholder="Valor de custo" class="form-control"/>
	  </div>
	</div>
	
	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for=""></label>
	  <div class="col-md-4">
	    <input class="btn btn-primary" type="submit" value="Salvar" />
	    <a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}/tarifarioServico/listar/${tarifarioServico.servico.id}" role="button">Cancelar</a>
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