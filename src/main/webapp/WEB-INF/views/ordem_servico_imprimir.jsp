<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="reportTemplate">
	<tiles:putAttribute name="body">

	<h1 class="page-header">Ordem de Serviço</h1>

	<div class="form-horizontal">
	
	<fieldset>
	
	<!-- Form Name -->
	<c:if test="${not isPai }">
		<legend>OS Princial: ${ordemServico.ordemServicoPai.id }</legend>
	</c:if>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-2 control-label" for="id">ID</label>  
	  <div class="col-md-2"><p class="form-control-static">${ordemServico.id }</p></div>


	  <label class="col-md-2 control-label" for="data">Data</label>  
	  <div class="col-md-2"><p class="form-control-static">${ordemServico.dataFormatado}</p></div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-2 control-label" for="validade">Prazo</label>  
	  <div class="col-md-2"><p class="form-control-static">${ordemServico.prazoFormatado}</p></div>


	  <label class="col-md-2 control-label" for="cliente">Cliente</label>  
	  <div class="col-md-2"><p class="form-control-static">${ordemServico.cliente.razao}</p></div>
	</div>

	<div class="form-group">
	  <label class="col-md-2 control-label" for="mecanico">Mecânico</label>
	  <div class="col-md-2"><p class="form-control-static">${ordemServico.mecanico.nome }</p></div>


	  <label class="col-md-2 control-label" for="ano">Ano</label>  
	  <div class="col-md-2"><p class="form-control-static">${ordemServico.ano}</p></div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-2 control-label" for="veiculo">Veículo</label>  
	  <div class="col-md-2"><p class="form-control-static">${ordemServico.veiculo.modelo}</p></div>


	  <label class="col-md-2 control-label" for="tipoMotor">Tipo de Motor</label>  
	  <div class="col-md-2"><p class="form-control-static">${ordemServico.tipoMotor.nome}</p></div>
	</div>

	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-5 control-label" for="status">Status</label>  
	  <div class="col-md-3"><p class="form-control-static">${statusOrdemServico.dataInicioFormatada } - ${statusOrdemServico.statusOS.text }</p></div>
	</div>
	
	<hr class="linha">
	<div class="alert alert-info" role="alert">Serviços</div>
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
		        <c:forEach items="${listaServicosOrdemServico }" var="servicoOrcamento" varStatus="loop">
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
	  <div class="col-md-4"><p class="form-control-static"><fmt:formatNumber type="currency" value="${totalServicosOrdemServico }" /></p></div>
	</div>

	<hr class="linha">
	<div class="alert alert-info" role="alert">Peças</div>
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
		        <c:forEach items="${listaPecasOrdemServico }" var="pecaOrcamento" varStatus="loop">
		        	<tr class="line" id="lnp${loop.index + 1 }">
		        		<td>${pecaOrcamento.peca.id }</td>
		        		<td>${pecaOrcamento.peca.nome }</td>
		        		<td>${pecaOrcamento.qtde }</td>
		        		<td><fmt:formatNumber type="currency" value="${pecaOrcamento.valor }" /></td>
		        		<td><span class="sub-total"><fmt:formatNumber value="${pecaOrcamento.subTotal }" type="currency"/></span></td>
		        	</tr>
		        </c:forEach>
		    </tbody>
		</table>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="razao">Total de peças</label>  
	  <div class="col-md-4"><p class="form-control-static"><fmt:formatNumber type="currency" value="${totalPecasOrdemServico }" /></p></div>
	</div>
	
	<hr class="linha">
	
	<div class="form-group">
	  <c:if test="${isPai }">
		  <label class="col-md-3 control-label" for="valorSubTotal">Sub Total</label>  
		  <div class="col-md-3"><p class="form-control-static"><fmt:formatNumber type="currency" value="${ordemServico.valorSubTotal}" /></p></div>
		
		  <label class="col-md-3 control-label" for="valorTotal">Total OSs Adicionais</label>  
		  <div class="col-md-3"><p class="form-control-static"><fmt:formatNumber type="currency" value="${ordemServico.valorTotalAdicionais}" /></p></div>
	  </c:if>
	  <label class="col-md-5 control-label" for="valorTotal">Valor Total</label>  
	  <div class="col-md-5"><p class="form-control-static"><fmt:formatNumber type="currency" value="${ordemServico.valorTotal}" /></p></div>

	</div>
	
	</fieldset>
	</div>

	</tiles:putAttribute>
</tiles:insertDefinition>