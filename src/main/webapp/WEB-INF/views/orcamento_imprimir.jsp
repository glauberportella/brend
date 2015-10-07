<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="reportTemplate">
	<tiles:putAttribute name="body">

	<h1 class="page-header">Orçamento</h1>
	<div class="form-horizontal">
	<fieldset>
	
	<!-- Form Name -->
	<legend>Imprimir</legend>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-2 control-label" for="id">ID</label>  
	  <div class="col-md-2"><p class="form-control-static">${orcamento.id}</p></div>
	
	  <label class="col-md-2 control-label" for="data">Data</label>  
	  <div class="col-md-2"><p class="form-control-static">${orcamento.dataFormatado}</p></div>
	</div>
	
	<div class="form-group">
	  <label class="col-md-2 control-label" for="validade">Data de Validade</label>  
	  <div class="col-md-2"><p class="form-control-static">${orcamento.validadeFormatado}</p></div>

	  <label class="col-md-2 control-label" for="cliente">Cliente</label>
	  <div class="col-md-2"><p class="form-control-static">${orcamento.cliente.razao}</p></div>
	</div>

	<div class="form-group">
	  <label class="col-md-2 control-label" for="veiculo">Veículo</label>
	  <div class="col-md-2"><p class="form-control-static">${orcamento.veiculo.modelo}</p></div>

	  <label class="col-md-2 control-label" for="ano">Ano</label>  
	  <div class="col-md-2"><p class="form-control-static">${orcamento.ano }</p></div>
	</div>
	
	<!-- Select Basic -->
	<div class="form-group">
	  <label class="col-md-2 control-label" for="tipoMotor">Tipo de Motor</label>
	  <div class="col-md-2"><p class="form-control-static">${orcamento.tipoMotor.nome}</p></div>
	</div>
	<hr class="linha">
	<!-- inicio ta tabela de subnivel dos itens  -->

	<div>
		<table class="table table-condensed table-hover table-striped" id="table-servicos">
		    <thead>
		        <tr>
		            <th data-column-id="id" data-type="numeric">ID</th>
		            <th data-column-id="nome">Nome</th>
		            <th data-column-id="qtde">Qtde</th>
					<th data-column-id="unitario">Unit.</th>
					<th data-column-id="total">Total</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach items="${listaServicosOrcamento }" var="servicoOrcamento" varStatus="loop">
		        	<tr class="line" id="lns${loop.index + 1 }">
		        		<td>${servicoOrcamento.servico.id }</td>
		        		<td>${servicoOrcamento.servico.nome }</td>
		        		<td>${servicoOrcamento.qtde }</td>
		        		<td><fmt:formatNumber type="currency" value="${servicoOrcamento.valor }" /></td>
		        		<td><span class="sub-total"><fmt:formatNumber type="currency" value="${servicoOrcamento.subTotal }" /></span></td>
		        	</tr>
		        </c:forEach>
		    </tbody>
		</table>
	</div>
	
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="razao">Total de Serviços</label>  
	  <div class="col-md-4"><p class="form-control-static"><fmt:formatNumber type="currency" value="${totalServicosOrcamento }" /></p></div>
	</div>
	
	
	<!-- fim da tabela de subnivel dos itens -->
	<hr class="linha">
	<!-- inicio ta tabela de subnivel dos itens  -->

	<div>
		<table class="table table-condensed table-hover table-striped" id="table-pecas">
		    <thead>
		        <tr>
		            <th data-column-id="id" data-type="numeric">ID</th>
		            <th data-column-id="nome">Nome</th>
		            <th data-column-id="qtde">Qtde</th>
					<th data-column-id="unitario">Unit.</th>
					<th data-column-id="total">Total</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach items="${listaPecasOrcamento }" var="pecaOrcamento" varStatus="loop">
		        	<tr class="line" id="ln${loop.index + 1 }">
		        		<td>${pecaOrcamento.peca.id }</td>
		        		<td>${pecaOrcamento.peca.nome }</td>
		        		<td>${pecaOrcamento.qtde }</td>
		        		<td><fmt:formatNumber type="currency" value="${pecaOrcamento.valor }" /></td>
		        		<td><span class="sub-total"><fmt:formatNumber type="currency" value="${pecaOrcamento.subTotal }" /></span></td>
		        	</tr>
		        </c:forEach>
		    </tbody>
		</table>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="razao">Total de peças</label>  
	  <div class="col-md-4"><p class="form-control-static"><fmt:formatNumber type="currency" value="${totalPecasOrcamento }" /></p></div>
	</div>
	
	<div class="form-group">
	  <label class="col-md-4 control-label" for="valorTotal">Valor Total</label>  
	  <div class="col-md-4"><p class="form-control-static"><fmt:formatNumber type="currency" value="${orcamento.valorTotal}" /></p></div>
	</div>
	 
	</fieldset>
	</div>
	
	</tiles:putAttribute>
</tiles:insertDefinition>