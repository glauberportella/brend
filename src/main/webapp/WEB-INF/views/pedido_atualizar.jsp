<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<c:choose>
		<c:when test="${estoque eq true }">
			<h1 class="page-header">Estoque</h1>

		</c:when>
		<c:otherwise>
			<h1 class="page-header">Pedido</h1>
		</c:otherwise>
	</c:choose>
	
	<c:url var="addAction" value="/pedido/inserir"></c:url>
	<form:form action="${addAction}" commandName="pedido" class="form-horizontal">
	
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
	  <label class="col-md-4 control-label" for="data">Data do Pedido</label>  
	  <div class="col-md-4">
	  <form:input path="data" placeholder="Data do pedido" required="true" class="form-control input-md" value="${pedido.dataFormatada }"/>
	  <form:errors path="data" cssClass="label label-danger"/>    
	  </div>
	</div>
	
	<c:choose>
		<c:when test="${estoque eq true or not empty pedido.notaFiscal}">	
			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="dataCompra">Data da Compra</label>  
			  <div class="col-md-4">
			  <form:input path="dataCompra" placeholder="Data da Compra" required="true" class="form-control input-md" value="${pedido.dataCompraFormatada }"/>
			  <form:errors path="dataCompra" cssClass="label label-danger"/>
			  </div>
			</div>
		
			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="razao">Nota Fiscal</label>  
			  <div class="col-md-4">
			  <form:input path="notaFiscal" placeholder="Nota Fiscal" required="true" class="form-control input-md"/>
			  </div>
			</div>
			
			<input type="hidden" name="estoque" id="estoque" value="estoque"/>
		</c:when>
		<c:otherwise>
			<input type="hidden" name="estoque" id="estoque" value=""/>
			<form:hidden path="dataCompra" value="01/01/1900"/>
		</c:otherwise>
	</c:choose>
	
	<!-- Select Basic -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="fornecedor">Fornecedor</label>
	  <div class="col-md-4">
	    <form:select path="fornecedor" class="form-control">
	    <c:forEach var="item" items="${listaFornecedores}">
		    <c:choose>
		        <c:when test="${pedido.fornecedor.id eq item.key}">
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
		        <c:forEach items="${listaPecasPedido }" var="pecaPedido" varStatus="loop">
		        	<tr class="line" id="ln${loop.index + 1 }">
		        		<td>${pecaPedido.peca.id }<input type="hidden" value="${pecaPedido.peca.id }" name="ids"></td>
		        		<td>${pecaPedido.peca.nome }</td>
		        		<td>${pecaPedido.qtde }<input type="hidden" value="${pecaPedido.qtde }" name="qtdes"></td>
		        		<td>${pecaPedido.valor }<input type="hidden" value="${pecaPedido.valor }" name="units"></td>
		        		<td><span class="sub-total">${pecaPedido.subTotal }</span></td>
		        		<td><button type="button" class="btn btn-link remove" id="rm${loop.index + 1}" lineId="ln${loop.index + 1}">[ X ]</button></td>
		        	</tr>
		        </c:forEach>
		    </tbody>
		</table>
	</div>
	
	<!-- Text input-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="razao">Total de peças</label>  
	  <div class="col-md-4">
	    <input type="text" id="totalPecasPedido" class="form-control input-md" readonly="readonly" value="${totalPecasPedido }"/>
	    <input type="hidden" id="countLines" value="${countLines }"/>
	  </div>
	</div>
	
	<!-- fim da tabela de subnivel dos itens -->
	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for=""></label>
	  <div class="col-md-5">
	  	<c:if test="${empty pedido.notaFiscal }">
	  		<input id="btn-salvar" class="btn btn-primary" type="submit" value="Salvar" />
	  	</c:if>
	    <a class="btn btn-danger" href="${requestScope['javax.servlet.forward.context_path']}/pedido/listar" role="button">Cancelar</a>
	    <c:if test="${empty pedido.notaFiscal and pedido.id gt 0 and empty estoque}">
	    	<a class="btn btn-default" href="${requestScope['javax.servlet.forward.context_path']}/pedido/telaAtualizar/${pedido.id}/estoque" role="button">Inserir Estoque</a>
	    	<input type="button" id="btnEnviarEmail" class="btn btn-default" role="button" value="Enviar E-mail"/>
	    </c:if>
	  </div>
	</div>
	
	</fieldset>
	</form:form>

	<!-- inicio do trecho do modal  -->

	<script>
	$(document).ready(function() {
		$( "#btnEnviarEmail" ).click(function(){
			$('#loading').modal("show");
			$.post("/omservicos/pedido/enviarEmail",	{
				pedidoId: $( "#id" ).val()
			}).done(function( data ) {
				$('#loading').modal("hide");				
				alert(data);
			});	
		});
		
		
		$( '#pedido' ).submit(function(event){
			var ids = $( 'input[name=ids]' ).val();
			var qtdes = $( 'input[name=qtdes]' ).val();
			var unit = $( 'input[name=units]' ).val();
			
			if (!ids || !qtdes || !unit) {		
				alert("A lista de peças não pode ser vazia.");
				return false;
			} else {
				return true;
			}
		});
		
		var countLines = parseInt($( '#countLines' ).val());

		var actionRemove = function(lineId) {
			var subTotal = parseFloat($( lineId + ' td span.sub-total').html());
			var totalPecasPedidoRemover = parseFloat($( '#totalPecasPedido' ).val());
			$( '#totalPecasPedido' ).val(totalPecasPedidoRemover - subTotal);
			$( lineId ).remove();
		}
		
		$( '.remove' ).click(function() {
			actionRemove('#' + $(this).attr( 'lineId' ));
		});
		
		$( '#addItem' ).click(function() {
			countLines++;
			$( '#countLines' ).val(countLines);
			
			var frmPeca = $( '#frmId' ).val().split(':');
			var id = frmPeca[0];
			var nome = frmPeca[1]; 
			var qtde = $( '#frmQtde' ).val();
			var unit = $( '#frmUnit' ).val();
			
			var erro = 0;
			var msg = "";
			if (!qtde) {
				msg += "Informe a quantidade de peças.\n";
				erro++;
			}
						
			if (!unit) {
				msg += "Informe o valor unitário.\n";
				erro++;
			}
			
			if (unit <= 0) {
				msg += "O valor unitário não pode ser zero ou negativo\n";
				erro++;
			}
			
			if (qtde <= 0) {
				msg += "A quantidade não pode ser zero ou negativo\n";
				erro++;
			}
			
			if (erro == 0) { 
				$('#table-pecas').append( '<tr id="ln' + countLines + '" class="line">'
			        + '<td>' + id + '<input type="hidden" name="ids" value="' + id + '"/></td>'
			        + '<td>' + nome	+ '</td>'
			        + '<td>' + qtde + '<input type="hidden" name="qtdes" value="' + qtde + '" /></td>'
			        + '<td>' + unit + '<input type="hidden" name="units" value="' + unit + '" /></td>'
			        + '<td><span class="sub-total">' + (qtde * unit) + '</span></td>'
			        + '<td><button type="button" class="btn btn-link" id="rm' + countLines + '" lineId="ln' + countLines + '">[ X ]</button></td>'
			        + '</tr>');
				
				var totalPecasPedido = parseFloat($( '#totalPecasPedido' ).val());
				$( '#totalPecasPedido' ).val(totalPecasPedido + (qtde * unit));
	
				$( '#rm' + countLines ).on('click', function() {
					actionRemove('#' + $(this).attr( 'lineId' ));
				});
			} else {
				alert(msg);
			}
		});
	});
	</script>
	
	<!-- Modal -->
	<form id="lista-pecas">
		
		<div class="modal fade" id="pecas" tabindex="-1" role="dialog" aria-labelledby="pecasLabel" aria-hidden="true">
		  <div class="modal-dialog modal-sm">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="pecasLabel">Lista de Peças</h4>
		      </div>
		      <div class="modal-body">
		      
			    <select id="frmId" class="form-control input-md">
			    <c:forEach items="${listaPecas }" var="peca">
			    	<option value="${peca.key }:${peca.value }">${peca.value }</option>
			    </c:forEach>
			    </select><br/>
				<input type="number" name="frmQtde" id="frmQtde" placeholder="Quantidade" class="form-control input-md"><br/>
				<input type="number" name="frmUnit" id="frmUnit" placeholder="Valor unitário" class="form-control input-md"><br/>
		    
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
		        <button type="button" class="btn btn-primary" id="addItem">Adicionar</button>
		      </div>
		    </div>
		  </div>
		</div>
	
	</form>
	
	<!-- fim do trecho do modal -->
	
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

	</tiles:putAttribute>
</tiles:insertDefinition>