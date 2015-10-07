<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<h1 class="page-header">Ordem de Serviço Adicional</h1>
	
	<c:url var="addAction" value="/ordemServico/inserirOSAdicional"></c:url>
	<form:form action="${addAction}" commandName="ordemServicoAdicional" class="form-horizontal">
	
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
	  <label class="col-md-4 control-label" for="data">Data</label>  
	  <div class="col-md-4">
	  <form:input path="data" placeholder="Data"  readonly="true" class="form-control input-md" value="${ordemServicoAdicional.dataFormatado}"/>
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="prazo">Prazo</label>  
	  <div class="col-md-4">
	  <form:input path="prazo" placeholder="Prazo"  required="true" class="form-control input-md" value="${ordemServicoAdicional.prazoFormatado}"/>
	  </div>
	</div>
	
	<!-- Select Basic -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="cliente">Cliente</label>
	  <div class="col-md-4">
	  <form:input path="cliente.razao" readonly="readonly" class="form-control input-md" />
	  <form:hidden path="cliente.id" />
	  </div>
	</div>
		
	<!-- Select Basic -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="veiculo">Veículo</label>
	  <div class="col-md-4">
	  <form:input path="veiculo.modelo" readonly="readonly" class="form-control input-md" />
	  <form:hidden path="veiculo.id"/>
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="ano">Ano</label>  
	  <div class="col-md-4">
	  <form:input path="ano" placeholder="Ano" readonly="true" class="form-control input-md"/>
	  </div>
	</div>
	
	<!-- Select Basic -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="tipoMotor">Tipo de Motor</label>
	  <div class="col-md-4">
	  <form:input path="tipoMotor.nome" readonly="readonly" class="form-control input-md" />
	  <form:hidden path="tipoMotor.id"/>
	  </div>
	</div>
	<hr class="linha">
	<!-- inicio ta tabela de subnivel dos itens  -->

	<div class="form-group">
	  <label class="col-md-4 control-label" for=""></label>
	  <div class="col-md-4">
		<button type="button" class="btn btn-info" data-toggle="modal" data-target="#servicos">
		  Adicionar Servicos
		</button>
	  </div>
	</div>

	<div>
		<table class="table table-condensed table-hover table-striped" id="table-servicos">
		    <thead>
		        <tr>
		            <th data-column-id="id" data-type="numeric">ID</th>
		            <th data-column-id="nome">Nome</th>
		            <th data-column-id="qtde">Qtde</th>
					<th data-column-id="unitario">Unit.</th>
					<th data-column-id="total">Total</th>
		            <th data-column-id="remover">Remover</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach items="${listaServicosOrdemServico }" var="servicoOrdemServico" varStatus="loop">
		        	<tr class="line" id="lns${loop.index + 1 }">
		        		<td>${servicoOrdemServico.servico.id }<input type="hidden" value="${servicoOrdemServico.servico.id }" name="idsServico"></td>
		        		<td>${servicoOrdemServico.servico.nome }</td>
		        		<td>${servicoOrdemServico.qtde }<input type="hidden" value="${servicoOrdemServico.qtde }" name="qtdesServico"></td>
		        		<td>${servicoOrdemServico.valor }<input type="hidden" value="${servicoOrdemServico.valor }" name="unitsServico"></td>
		        		<td><span class="sub-total">${servicoOrdemServico.subTotal }</span></td>
		        		<td><button type="button" class="btn btn-link remove-servico" id="rms${loop.index + 1}" lineId="lns${loop.index + 1}">[ X ]</button></td>
		        	</tr>
		        </c:forEach>
		    </tbody>
		</table>
	</div>
	
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="razao">Total de Serviços</label>  
	  <div class="col-md-4">
	    <input type="text" id="totalServicosOrdemServico" class="form-control input-md" readonly="readonly" value="${totalServicosOrdemServico }"/>
	    <input type="hidden" id="countLinesServico" value="${countLinesServico }"/>
	  </div>
	</div>
	
	
	<!-- fim da tabela de subnivel dos itens -->
	<hr class="linha">
	<!-- inicio ta tabela de subnivel dos itens  -->

	<div class="form-group">
	  <label class="col-md-4 control-label" for=""></label>
	  <div class="col-md-4">
		<button type="button" class="btn btn-info" data-toggle="modal" data-target="#pecas">
		  Adicionar Pecas
		</button>
	  </div>
	</div>

	<div>
		<table class="table table-condensed table-hover table-striped" id="table-pecas">
		    <thead>
		        <tr>
		            <th data-column-id="id" data-type="numeric">ID</th>
		            <th data-column-id="nome">Nome</th>
		            <th data-column-id="qtde">Qtde</th>
					<th data-column-id="unitario">Unit.</th>
					<th data-column-id="total">Total</th>
		            <th data-column-id="remover">Remover</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach items="${listaPecasOrdemServico }" var="pecaOrdemServico" varStatus="loop">
		        	<tr class="line" id="ln${loop.index + 1 }">
		        		<td>${pecaOrdemServico.peca.id }<input type="hidden" value="${pecaOrdemServico.peca.id }" name="idsPeca"></td>
		        		<td>${pecaOrdemServico.peca.nome }</td>
		        		<td>${pecaOrdemServico.qtde }<input type="hidden" value="${pecaOrdemServico.qtde }" name="qtdesPeca"></td>
		        		<td>${pecaOrdemServico.valor }<input type="hidden" value="${pecaOrdemServico.valor }" name="unitsPeca"></td>
		        		<td><span class="sub-total">${pecaOrdemServico.subTotal }</span></td>
		        		<td><button type="button" class="btn btn-link remove-peca" id="rm${loop.index + 1}" lineId="ln${loop.index + 1}">[ X ]</button></td>
		        	</tr>
		        </c:forEach>
		    </tbody>
		</table>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="razao">Total de peças</label>  
	  <div class="col-md-4">
	    <input type="text" id="totalPecasOrdemServico" class="form-control input-md" readonly="readonly" value="${totalPecasOrdemServico }"/>
	    <input type="hidden" id="countLinesPeca" value="${countLinesPeca }"/>
	  </div>
	</div>
	
	<div class="form-group">
	  <label class="col-md-4 control-label" for="valorTotal">Valor Total</label>  
	  <div class="col-md-4">
	  <form:input path="valorTotal" placeholder="Valor Total" value="${ordemServico.valorTotal}" class="form-control"/>
	  </div>
	</div>
	 
	
	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for=""></label>
	  <div class="col-md-4">
	    <input class="btn btn-primary" type="submit" value="Salvar" />
	    <a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}/ordemServico/telaAtualizar/${ordemServicoAdicional.ordemServicoPai.id }" role="button">Cancelar</a>
	    <c:if test="${ordemServico.id gt 0 }">
	    	<c:if test="${empty temOrdemServico }">
	    		<a class="btn btn-default" href="${requestScope['javax.servlet.forward.context_path']}/ordemServico/telaNovaOS/${ordemServico.id}" role="button">Gerar OS</a>
	    	</c:if>
	    </c:if>
	  </div>
	</div>
	
	<input type="hidden" name="ordemServicoIdPai" id="ordemServicoIdPai" value="${ordemServicoAdicional.ordemServicoPai.id }"/>
	
	</fieldset>
	</form:form>
	
	<!-- inicio do trecho do modal  -->

	<script>
	$(document).ready(function() {
		$( '#ordemServicoAdicional' ).submit(function(event){
			var idsServico = $( 'input[name=idsServico]' ).val();
			var qtdesServico = $( 'input[name=qtdesServico]' ).val();
			var unitServico = $( 'input[name=unitsServico]' ).val();
			
			var idsPeca = $( 'input[name=idsPeca]' ).val();
			var qtdesPeca = $( 'input[name=qtdesPeca]' ).val();
			var unitPeca = $( 'input[name=unitsPeca]' ).val();
			
			
			if (!idsServico || !qtdesServico || !unitServico) {		
				alert("A lista de serviços não pode ser vazia.");
				return false;	
			} else {
				if (!idsPeca) {
					$('#table-pecas').append( '<tr id="ln0" class="line">'
					        + '<td><input type="hidden" name="idsPeca" value="0"/></td>'
					        + '<td></td>'
					        + '<td><input type="hidden" name="qtdesPeca" value="0" /></td>'
					        + '<td><input type="hidden" name="unitsPeca" value="0" /></td>'
					        + '<td><span class="sub-total"></span></td>'
					        + '<td></td>'
					        + '</tr>');
				}
				return true;
			}
		});
		
		var countLinesPeca = parseInt($( '#countLinesPeca' ).val());
		var countLinesServico = parseInt($( '#countLinesServico' ).val());
		
		var calculaTotal = function() {
			var valorTotal = parseFloat($( '#totalServicosOrdemServico' ).val()) + parseFloat($( '#totalPecasOrdemServico' ).val());
			$( '#valorTotal' ).val(valorTotal);
		}

		var actionRemovePeca = function(lineId) {
			var subTotal = parseFloat($( lineId + ' td span.sub-total').html());
			var totalPecasOrdemServicoRemover = parseFloat($( '#totalPecasOrdemServico' ).val());
			$( '#totalPecasOrdemServico ' ).val(totalPecasOrdemServicoRemover - subTotal);
			
			calculaTotal();
			
			$( lineId ).remove();
	
		}
		
		var actionRemoveServico = function(lineId) {
			//var subTotal = parseFloat($( lineId + ' td span.sub-total').html());
			var subTotal = parseFloat($( lineId + ' td span.sub-total').html());
			var totalServicoOrdemServicoRemover = parseFloat($( '#totalServicosOrdemServico' ).val());
			$( '#totalServicosOrdemServico ' ).val(totalServicoOrdemServicoRemover - subTotal);
				
			calculaTotal();
			
			$( lineId ).remove();
		}
		
		$( '.remove-peca' ).click(function() {
			actionRemovePeca('#' + $(this).attr( 'lineId' ));
		});
		
		$( '.remove-servico' ).click(function() {
			actionRemoveServico('#' + $(this).attr( 'lineId' ));
		});
		
			
		$( '#addPeca' ).click(function() {
			countLinesPeca++;
			$( '#countLinesPeca' ).val(countLinesPeca);
			
			var frmPeca = $( '#frmIdPeca' ).val().split(':');
			var id = frmPeca[0];
			var nome = frmPeca[1];
			var unit = parseFloat(frmPeca[2]);
			var qtde = parseInt($( '#frmQtdePeca' ).val());

			var erro = 0;
			var msg = "";
			
			if(!qtde) {
				erro++;
				msg = "O campo quantidade não pode ser vazio.\n";
			}

			if (qtde <= 0) {
				msg += "A quantidade não pode ser zero ou negativo.";
				erro++;
			}

			
			if (erro == 0) {
				$('#table-pecas').append( '<tr id="ln' + countLinesPeca + '" class="line">'
				        + '<td>' + id + '<input type="hidden" name="idsPeca" value="' + id + '"/></td>'
				        + '<td>' + nome	+ '</td>'
				        + '<td>' + qtde + '<input type="hidden" name="qtdesPeca" value="' + qtde + '" /></td>'
				        + '<td>' + unit + '<input type="hidden" name="unitsPeca" value="' + unit + '" /></td>'
				        + '<td><span class="sub-total">' + (qtde * unit) + '</span></td>'
				        + '<td><button type="button" class="btn btn-link" id="rm' + countLinesPeca + '" lineId="ln' + countLinesPeca + '">[ X ]</button></td>'
				        + '</tr>');
				
				var totalPecasOrdemServico = parseFloat($( '#totalPecasOrdemServico' ).val());
				totalPecasOrdemServico = totalPecasOrdemServico + (qtde * unit);
				$( '#totalPecasOrdemServico' ).val(totalPecasOrdemServico);
				
				calculaTotal();
										
				$( '#rm' + countLinesPeca ).on('click', function() {
					actionRemovePeca('#' + $(this).attr( 'lineId' ));
				});
			} else {
				alert(msg);
			}
		});
		
		$( '#addServico' ).click(function() {
			countLinesServico++;
			$( '#countLinesServico' ).val(countLinesServico);
			
			var frmServico = $( '#frmIdServico' ).val().split(':');
			var id = frmServico[0];
			var nome = frmServico[1];
			var unit = parseFloat(frmServico[2]);
			var qtde = parseInt($( '#frmQtdeServico' ).val());
			
			var erro = 0;
			var msg = "";
			
			if (!qtde) {
				msg = "O campo quantidade não pode ser vazio.\n";
				erro++;
			}
			
			if (qtde <= 0) {
				msg += "A quantidade não pode ser zero ou negativo.";
				erro++;
			}
			
			
			if(erro == 0) {
				$('#table-servicos').append( '<tr id="lns' + countLinesServico + '" class="line">'
			        + '<td>' + id + '<input type="hidden" name="idsServico" value="' + id + '"/></td>'
			        + '<td>' + nome	+ '</td>'
			        + '<td>' + qtde + '<input type="hidden" name="qtdesServico" value="' + qtde + '" /></td>'
			        + '<td>' + unit + '<input type="hidden" name="unitsServico" value="' + unit + '" /></td>'
			        + '<td><span class="sub-total">' + (qtde * unit) + '</span></td>'
			        + '<td><button type="button" class="btn btn-link" id="rms' + countLinesServico + '" lineId="lns' + countLinesServico + '">[ X ]</button></td>'
			        + '</tr>');
				
				var totalServicosOrdemServico = parseFloat($( '#totalServicosOrdemServico' ).val());
				totalServicosOrdemServico = totalServicosOrdemServico + (qtde * unit);
				$( '#totalServicosOrdemServico' ).val(totalServicosOrdemServico);
				
				calculaTotal();
				
				$( '#rms' + countLinesServico ).on('click', function() {
					actionRemoveServico('#' + $(this).attr( 'lineId' ));
				});
			} else {
				alert(msg);
			}
		});
		
		
	});
	</script>

	<form id="lista-pecas">
		
		<!-- Modal -->
		<div class="modal fade" id="pecas" tabindex="-1" role="dialog" aria-labelledby="pecasLabel" aria-hidden="true">
		  <div class="modal-dialog modal-sm">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="pecasLabel">Lista de Peças</h4>
		      </div>
		      <div class="modal-body">
		      
			    <select id="frmIdPeca" class="form-control input-md">
			    <c:forEach items="${listaPecas }" var="peca">
			    	<option value="${peca.id }:${peca.nome } - ${peca.marca.nome }:${peca.preco }">${peca.nome } - ${peca.marca.nome } - ${peca.preco }</option>
			    </c:forEach>
			    </select><br/>
				<input type="number" name="frmQtdePeca" id="frmQtdePeca" placeholder="Quantidade" class="form-control input-md"><br/>
		    
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
		        <button type="button" class="btn btn-primary" id="addPeca">Adicionar</button>
		      </div>
		    </div>
		  </div>
		</div>
	
	</form>
	
	<form id="lista-servicos">
		
		<!-- Modal -->
		<div class="modal fade" id="servicos" tabindex="-1" role="dialog" aria-labelledby="servicosLabel" aria-hidden="true">
		  <div class="modal-dialog modal-sm">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="servicosLabel">Lista de Serviços</h4>
		      </div>
		      <div class="modal-body">
		      
			    <select id="frmIdServico" class="form-control input-md">
			    <c:forEach items="${listaServicos }" var="servico">
			    	<option value="${servico.id }:${servico.nome }:${servico.preco }">${servico.nome } - ${servico.preco }</option>
			    </c:forEach>
			    </select><br/>
				<input type="number" name="frmQtdeServico" id="frmQtdeServico" placeholder="Quantidade" class="form-control input-md"><br/>
		    
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
		        <button type="button" class="btn btn-primary" id="addServico">Adicionar</button>
		      </div>
		    </div>
		  </div>
		</div>
	
	</form>
	
	<!-- fim do trecho do modal -->
	</tiles:putAttribute>
</tiles:insertDefinition>