<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<h1 class="page-header">Funcionário</h1>
	
	<c:url var="addAction" value="/funcionario/inserir"></c:url>
	<form:form action="${addAction}" commandName="funcionario" class="form-horizontal" method="post" modelAttribute="funcionario">
	
	<fieldset>
	
	<!-- Form Name -->
	<legend>Cadastrar/Atualizar</legend>
	
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
	  <label class="col-md-4 control-label" for="nome">Nome</label>  
	  <div class="col-md-4">
	  <form:input path="nome" placeholder="Nome" required="true" class="form-control input-md"/>  
	  <form:errors path="nome" cssClass="label label-danger" />
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="cpf">CPF</label>  
	  <div class="col-md-4">
	  <form:input type="number" path="cpf" placeholder="CPF" required="true" class="form-control input-md"/>
	  <form:errors path="cpf" cssClass="label label-danger" />
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="nascimento">Data de Nascimento</label>  
	  <div class="col-md-4">
	  <form:input path="nascimento" placeholder="Data de Nascimento" required="true" class="form-control input-md" value="${funcionario.nascimentoFormatado}"/>
	  <form:errors path="nascimento" cssClass="label label-danger" />
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="endereco">Endereço</label>  
	  <div class="col-md-4">
	  <form:input path="endereco" placeholder="Endereço" required="true" class="form-control input-md"/>
	  <form:errors path="endereco" cssClass="label label-danger" />  
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="telefone">Telefone</label>  
	  <div class="col-md-4">
	  <form:input path="telefone" placeholder="Telefone" required="true" class="form-control input-md"/>
	  <form:errors path="telefone" cssClass="label label-danger" />  
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="email">E-mail</label>  
	  <div class="col-md-4">
	  <form:input type="email" path="email" placeholder="E-mail" required="true" class="form-control input-md" />
	  <form:errors path="email" cssClass="label label-danger" />
	  </div>
	</div>

	<!-- Select Basic -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="tipoFuncionario">Tipo</label>
	  <div class="col-md-4">
	    <form:select path="tipoFuncionario" class="form-control">
	    <c:forEach var="item" items="${listaTipoFuncionarios}">
		    <c:choose>
		        <c:when test="${funcionario.tipoFuncionario.id eq item.key}">
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
	
	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for=""></label>
	  <div class="col-md-6">
	    <input class="btn btn-primary" type="submit" value="Salvar" />
	    <a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}${destinoCancela }" role="button">Cancelar</a>
	    <button type="button" class="btn btn-info" data-toggle="modal" data-target="#usuario" id="btnAddDados">
	    	<c:out value="${empty usuario ? 'Inserir Dados de acesso' : 'Alterar Dados de acesso' }"/>
		</button>
	  </div>
	</div>

	<!-- Hidden -->
	<input type="hidden" name="destino" value="${destino }" />

	<!-- Inicio do trecho do modal -->
	
	<div class="modal fade" id="usuario" tabindex="-1" role="dialog" aria-labelledby="usuarioLabel" aria-hidden="true">
	  <div class="modal-dialog modal-sm">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="usuarioLabel">Dados de acesso ao sistema</h4>
	      </div>
	      <div class="modal-body">
	      
			<input type="text" id="login" name="login" placeholder="Login" class="form-control input-md" value="${usuario.login }" ${readOnly }/><br/>
			<input type="password" id="senha" name="senha" placeholder="Senha" class="form-control input-md" value="${usuario.senha }"/><br/>
			<c:set var="readOnlySelect" value="${destino == 1 ? 'readonly=\"readonly\"' : '' }"/>
			<select name="papeis" id="papeis" class="form-control" multiple="multiple" ${readOnlySelect }>
				<c:forEach var="item" items="${papeis }">
					<option value="${item.key }" ${item.value.selected }>${item.value.label }</option>
				</c:forEach>
			</select>
	    
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default modal-behavior" data-dismiss="modal" id="limparDadosAcesso">Cancelar</button>
	        <button type="button" class="btn btn-primary modal-behavior" data-dismiss="modal">Ok</button>
	      </div>
	    </div>
	  </div>
	</div>

	<script type="text/javascript">
		$(document).ready(function(){
			var loginDefault = "${usuario.login }";
			var senhaDefault = "${usuario.senha }";
			
			$("#limparDadosAcesso").click(function() {
				$("#login").val(loginDefault);
				$("#senha").val(senhaDefault);
			});
			
			$(".modal-behavior").click(function() {
				if ($("#login").val() == '') {
					$("#btnAddDados").html("Inserir Dados de acesso");	
				} else {
					$("#btnAddDados").html("Alterar Dados de acesso");
				}
			});
		});
	</script>
	
	<!-- Fim do trecho do modal -->	
	
	</fieldset>
	</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>