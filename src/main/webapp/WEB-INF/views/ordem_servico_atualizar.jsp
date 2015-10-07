<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<h1 class="page-header">Ordem de Serviço</h1>

	<form:form action="${addAction}" commandName="ordemServico" class="form-horizontal">
	
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
	  <label class="col-md-4 control-label" for="data">Data</label>  
	  <div class="col-md-4">
	  <form:input path="data" placeholder="Data"  readonly="true" required="true" class="form-control input-md" value="${ordemServico.dataFormatado}"/>
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="validade">Prazo</label>  
	  <div class="col-md-4">
	  <form:input path="prazo" placeholder="prazo" required="true" class="form-control input-md" value="${ordemServico.prazoFormatado}"/>
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="cliente">Cliente</label>  
	  <div class="col-md-4">
	  <form:input  path="cliente.razao" readonly="true" size="8" disabled="true" class="form-control input-md" />
	  <form:hidden path="cliente.id" />
	  </div>
	</div>

	<c:if test="${!empty listaMecanicos }">
	<div class="form-group">
	  <label class="col-md-4 control-label" for="mecanico">Mecânico</label>
	  <div class="col-md-4">
		<c:choose>
		  <c:when test="${empty ordemServico.mecanico.id }">
		    <!-- Select Basic -->
		    <form:select path="mecanico" class="form-control">
		      <c:forEach var="item" items="${listaMecanicos}">
		        <c:choose>
		          <c:when test="${ordemServico.mecanico.id eq item.key}">
		            <form:option selected="true" value="${item.key}" label="${item.value }"/>
		          </c:when>
		          <c:otherwise>
		            <form:option value="${item.key}" label="${item.value }"/>
		          </c:otherwise>
		        </c:choose>
		      </c:forEach>
		    </form:select>
		    <input type="hidden" name="alteraStatus" value="S"/>
		  </c:when>
		  <c:otherwise>
		  	<c:choose>
		  		<c:when test="${statusOrdemServico.statusOS.text eq 'Reaberta'}">
			  		<form:select path="mecanico" class="form-control">
				      <c:forEach var="item" items="${listaMecanicos}">
				        <c:choose>
				          <c:when test="${ordemServico.mecanico.id eq item.key}">
				            <form:option selected="true" value="${item.key}" label="${item.value }"/>
				          </c:when>
				          <c:otherwise>
				            <form:option value="${item.key}" label="${item.value }"/>
				          </c:otherwise>
				        </c:choose>
				      </c:forEach>
				    </form:select>
				    <input type="hidden" name="alteraStatus" value="S"/>
			  	</c:when>
		  		<c:otherwise>
		 	        <form:input  path="mecanico.nome" readonly="true" size="8" disabled="true" class="form-control input-md" />
			        <form:hidden path="mecanico.id" />
			        <input type="hidden" name="alteraStatus" value="N"/>
		  		</c:otherwise>
		  	</c:choose>
		  </c:otherwise>
		</c:choose>
	  </div>
	</div>
	</c:if>

	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="ano">Ano</label>  
	  <div class="col-md-4">
	  <form:input path="ano" placeholder="Ano" readonly="true" required="true" class="form-control input-md"/>
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="veiculo">Veículo</label>  
	  <div class="col-md-4">
	  <form:input  path="veiculo.modelo" readonly="true" size="8" disabled="true" class="form-control input-md" />
	  <form:hidden path="veiculo.id" />
	  </div>
	</div>
	
		
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="tipoMotor">Tipo de Motor</label>  
	  <div class="col-md-4">
	  <form:input  path="tipoMotor.nome" readonly="true" size="8" disabled="true" class="form-control input-md" />
	  <form:hidden path="tipoMotor.id" />
	  </div>
	</div>

	<!-- Text input-->
	<c:if test="${ordemServico.id ne 0 and isPai eq true}">
		<div class="form-group">
		  <label class="col-md-4 control-label" for="status">Status Atual</label>  
		  <div class="col-md-4">
		  	<input type="text" readonly="readonly" class="form-control input-md" value="${statusOrdemServico.dataInicioFormatada } - ${statusOrdemServico.statusOS.text }" data-toggle="tooltip" data-placement="top" title="${statusOrdemServico.descricao }" />
		  </div>
		</div>
	</c:if>
	
	<c:if test="${ordemServico.id ne 0 and isPai eq true}">
		<div class="form-group">
		  <label class="col-md-4 control-label" for="">Alterar Status</label>
		  <div class="col-md-1">
		  	<button type="button" class="btn btn-info" data-toggle="modal" data-target="#historico">Histórico</button>
		  </div>

		  <c:if test="${statusOrdemServico.statusOS.text eq 'Em Andamento'}">		  
			  <div class="col-md-1">
			  	<a class="btn btn-finalizada" href="${requestScope['javax.servlet.forward.context_path']}/registroContato/telaFinalizar/${ordemServico.id }" role="button" >Finalizar</a>
			  </div>
			  <div class="col-md-1">
			  	<a class="btn btn-cancelada" href="${requestScope['javax.servlet.forward.context_path']}/registroContato/telaCancelar/${ordemServico.id }" role="button" >Cancelar</a>
			  </div>
		  </c:if>
		  <c:if test="${statusOrdemServico.statusOS.text eq 'Finalizada'}">
			  <div class="col-md-1">
			  	<a class="btn btn-reaberta" href="${requestScope['javax.servlet.forward.context_path']}/registroContato/telaReabrir/${ordemServico.id }" role="button" >Reabrir</a>
			  </div>
		  </c:if>
		</div>
	</c:if>
	
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
		        		<td>${servicoOrcamento.servico.id }<input type="hidden" value="${servicoOrcamento.servico.id }" name="idsServico"></td>
		        		<td>${servicoOrcamento.servico.nome }</td>
		        		<td>${servicoOrcamento.qtde }<input type="hidden" value="${servicoOrcamento.qtde }" name="qtdesServico"></td>
		        		<td><fmt:formatNumber type="currency" value="${servicoOrcamento.valor }" /><input type="hidden" value="${servicoOrcamento.valor }" name="unitsServico"></td>
		        		<td><span class="sub-total"><fmt:formatNumber type="currency" value="${servicoOrcamento.subTotal }" /></span></td>
		        	</tr>
		        </c:forEach>
		    </tbody>
		</table>
	</div>
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="razao">Total de Serviços</label>  
	  <div class="col-md-4">
	    <fmt:formatNumber type="currency" value="${totalServicosOrdemServico }" var="totalServicosOrdemServicoFormatado"/>
	    <input type="text" class="form-control input-md" readonly="readonly" value="${totalServicosOrdemServicoFormatado }"/>
	    <form:hidden path="valorServico" class="form-control input-md" readonly="true" value="${totalServicosOrdemServico }"/>
	    <input type="hidden" id="countLinesServico" value="${countLinesServico }"/>
	  </div>
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
		        		<td>${pecaOrcamento.peca.id }<input type="hidden" value="${pecaOrcamento.peca.id }" name="idsPeca"></td>
		        		<td>${pecaOrcamento.peca.nome }</td>
		        		<td>${pecaOrcamento.qtde }<input type="hidden" value="${pecaOrcamento.qtde }" name="qtdesPeca"></td>
		        		<td><fmt:formatNumber type="currency" value="${pecaOrcamento.valor }"/><input type="hidden" value="${pecaOrcamento.valor }" name="unitsPeca"></td>
		        		<td><span class="sub-total"><fmt:formatNumber type="currency" value="${pecaOrcamento.subTotal }" /></span></td>
		        	</tr>
		        </c:forEach>
		    </tbody>
		</table>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="razao">Total de peças</label>  
	  <div class="col-md-4">
	  	<fmt:formatNumber type="currency" value="${totalPecasOrdemServico }" var="totalPecasOrdemServicoFormatado"/>
	    <input type="text" class="form-control input-md" readonly="readonly" value="${totalPecasOrdemServicoFormatado }"/>
	    <form:hidden path="valorPeca" class="form-control input-md" readonly="true" value="${totalPecasOrdemServico }"/>
	    <input type="hidden" id="countLinesPeca" value="${countLinesPeca }"/>
	  </div>
	</div>
	
	<hr class="linha">

	<div class="form-group">
	  <label class="col-md-2 control-label" for="valorSubTotal">Sub Total</label>  
	  <div class="col-md-2">
	  <fmt:formatNumber type="currency" value="${ordemServico.valorSubTotal}" var="valorSubTotalFormatado"/>
	  <input type="text" placeholder="Sub Total" value="${valorSubTotalFormatado }" readonly="readonly" class="form-control"/>
	  <form:hidden path="valorSubTotal" placeholder="Sub Total" value="${ordemServico.valorSubTotal }" readonly="true" class="form-control"/>
	  </div>
	
	  <label class="col-md-2 control-label" for="valorTotal">Total OSs Adicionais</label>  
	  <div class="col-md-2">
	  <fmt:formatNumber type="currency" value="${ordemServico.valorTotalAdicionais}" var="valorTotalAdicionaisFormatado"/>
	  <input type="text" placeholder="Total OSs Adicionais" value="${valorTotalAdicionaisFormatado }" readonly="readonly" class="form-control"/>
	  <form:hidden path="valorTotalAdicionais" placeholder="Total OSs Adicionais" value="${ordemServico.valorTotalAdicionais }" readonly="true" class="form-control"/>
	  </div>

	  <label class="col-md-2 control-label" for="valorTotal">Valor Total</label>  
	  <div class="col-md-2">
	  <fmt:formatNumber type="currency" value="${ordemServico.valorTotal}" var="valorTotalFormatado" />
	  <input type="text" placeholder="Valor Total" value="${valorTotalFormatado }" readonly="readonly" class="form-control"/>
	  <form:hidden path="valorTotal" placeholder="Valor Total" value="${ordemServico.valorTotal }" readonly="true" class="form-control"/>
	  </div>
	</div>
	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for=""></label>
	  <div class="col-md-4">
	    <c:if test="${statusOrdemServico.access and (isPai or ordemServico.id eq 0)}">
	  		<input class="btn btn-primary" type="submit" value="Salvar" />
	  	</c:if>
	      <c:choose>
	    	<c:when test="${isPai eq true }">
	    		<a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}/ordemServico/listar" role="button">Cancelar</a>
	    	</c:when>
	    	<c:when test="${novaOS }">
	    		<a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}//orcamento/telaAtualizar/${orcamentoId }" role="button">Cancelar</a>
	    	</c:when>
	    	<c:otherwise>
	    		<a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}/ordemServico/telaAtualizar/${ordemServico.ordemServicoPai.id}" role="button">Cancelar</a>
	    	</c:otherwise>
	    </c:choose>
	    <c:if test="${ordemServico.id ne 0}">
	    	<a class="btn btn-success" target="_blank" href="${requestScope['javax.servlet.forward.context_path']}/ordemServico/imprimir/${ordemServico.id}" role="button">Imprimir</a>
	    </c:if>
	  </div>
	</div>
	
	<input type="hidden" name="orcamentoId" value="${not empty orcamentoId ? orcamentoId : 0}"/>
	<input type="hidden" name="ordemServicoIdPai" value="${not empty ordemServico.ordemServicoPai.id ? ordemServico.ordemServicoPai.id : 0 }"/>
	
	</fieldset>
	</form:form>

	<!-- Modal -->
	<div class="modal fade" id="historico" tabindex="-1" role="dialog" aria-labelledby="historicoLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="historicoLabel">Histórico de status</h4>
	      </div>
	      <div class="modal-body">
			
			<table class="table table-striped">
  				<tr>
  					<th>Data</th>
  					<th>Status</th>
  					<th>Obs.</th>
  				</tr>
  				<c:forEach items="${historico }" var="itemStatus">
		    		<tr>
	  					<td>${itemStatus.dataInicioFormatada }</td>
	  					<td>${itemStatus.statusOS.text }</td>
	  					<td>${itemStatus.descricao }</td>
	  				</tr>
		    	</c:forEach>
			</table>	
	    
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- fim do trecho do modal -->

	<script type="text/javascript">
	$(document).ready(function(){
		$('[data-toggle="tooltip"]').tooltip();
		
		$( "#ordemServico" ).submit(function(){
			var idsPeca = $( 'input[name=idsPeca]' ).val();
			
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
		});
	});
	</script>

	

	<c:if test="${isPai eq true }">
		<!-- bootgrid lista de OSs -->
		<c:out value="${renderBootgridJSOSAdicional}" escapeXml="false"/>
		
		<div class="alert alert-info" role="alert">OS's Adicionais</div>
		
		<c:if test="${statusOrdemServico.access }">
	   		<a class="btn btn-default" href="${requestScope['javax.servlet.forward.context_path']}/ordemServico/telaOSAdicional/${ordemServico.id }" role="button" >Adicionar Nova OS</a>
	   	</c:if>
		<c:out value="${renderBootgridGridOSAdicional}" escapeXml="false"/>
		
		<!-- bootgrid lista de follow-up -->
	
		<c:out value="${renderBootgridJSFollowup}" escapeXml="false"/>
		
		<div class="alert alert-info" role="alert">Registros de contato</div>
	
		<c:if test="${statusOrdemServico.access }">
			<button type="button" class="btn btn-default" data-toggle="modal" data-target="#followup">
			  Adicionar Follow-up
			</button>
		</c:if>

		<c:out value="${renderBootgridGridFollowup}" escapeXml="false"/>
		
		
		<!-- modal nova follow-up -->
		<script type="text/javascript">
		$(document).ready(function() {
			
			var salvaFollowup = function(descricaoId, form, url) {
				var descricao = $(descricaoId).val();
				
				if (!descricao) {		
					alert("Não pode adicionar um follow-up vazio.");
					return false;	
				} else {
					if(descricao.length > 10) {
						$('#loading').modal("show");
						$.post(url,	$(form).serialize()
						).done(function( data ) {
							$('#loading').modal("hide");
							alert("Mensagem enviada, atualize a lista de follow-ups!");
						});
					} else {
						alert("Não pode adicionar um follow-up com texto muito curto.");							
					}
				}
			}

			$( "#addFollowup" ).click(function(event){
				salvaFollowup('#descricao-followup', "#form-followup", "/omservicos/registroContato/inserir");
			});
						
		});
		</script>
	
		<form id="form-followup">
			
			<!-- Modal -->
			<div class="modal fade" id="followup" tabindex="-1" role="dialog" aria-labelledby="followupLabel" aria-hidden="true">
			  <div class="modal-dialog modal-sm">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="followupLabel">Follow-up</h4>
			      </div>
			      <div class="modal-body">
			      
					<textarea id="descricao-followup" cols="35" rows="5" name="descricao" class="form-control input-md" ></textarea>
			    	<label for="vistoria"><input type="checkbox" id="vistoria" name="vistoria"  value="1"> Vistoria realizada?</label>
			    	<input type="hidden" name="ordemServico" value="${ordemServico.id }"/>
			    	
			    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			    				    	
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
			        <button type="button" class="btn btn-primary" data-dismiss="modal" id="addFollowup">Adicionar</button>
			      </div>
			    </div>
			  </div>
			</div>
		
		</form>
		<!-- fim do modal -->

		<!-- Modal Load -->
		<div class="modal fade" id="loading" tabindex="-1" role="dialog" aria-labelledby="loadLabel" aria-hidden="true">
		  <div class="modal-dialog modal-sm">
		    <div class="modal-content">
		      <div class="modal-body">
		    	
		    	<p><img src="<c:url value="/resources/imagens/loading.gif"/>" /> Enviando...</p>  
				    
		      </div>
		    </div>
		  </div>
		</div>
		<!-- Fim modal load -->

	</c:if>
	</tiles:putAttribute>
</tiles:insertDefinition>