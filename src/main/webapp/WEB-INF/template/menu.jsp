<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="col-sm-3 col-md-2 sidebar">
  <ul class="nav nav-pills nav-stacked">
    <li role="presentation"><a href="<c:url value="/orcamento/telaInserir" />">Novo or�amento</a></li>
    <li role="presentation"><a href="<c:url value="/orcamento/listar" />">Listar or�amentos</a></li>
    <li role="presentation"><a href="<c:url value="/ordemServico/listar" />">Listar OS</a></li>
    <li role="presentation" class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
        Almoxarifado <span class="caret"></span>
      </a>

	  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
	    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/solicitacao/listar" />">Solicita��es de Pe�as</a></li>
	    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/solicitacao/listarPendentes" />">Solicita��es Pendentes</a></li>
	    <li class="divider"></li>
	    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/pedido/listar" />">Pedidos de compra</a></li>
	    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/pedido/telaEstoque" />">Entrada em estoque</a></li>
	  </ul>
   </li>
   
   <li role="presentation" class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
        Cadastros <span class="caret"></span>
      </a>

	  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
	    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/cliente/listar" />">Cliente</a></li>
	    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/fornecedor/listar" />">Fornecedor</a></li>
	    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/funcionario/listar" />">Funcionarios</a></li>
	    <li class="divider"></li>
	    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/peca/listar" />">Pe�as</a></li>
	    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/servico/listar" />">Servi�os</a></li>
	    <li class="divider"></li>
	    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/marca/listar" />">Marca da Pe�a</a></li>
	    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/veiculo/listar" />">Ve�culos</a></li>
	    <li class="divider"></li>
	    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/tipoDocumento/listar" />">Tipo de Documento</a></li>
	    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/tipoFuncionario/listar" />">Tipo de Funcion�rio</a></li>
	    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/tipoMotor/listar" />">Tipo de Motor</a></li>
	    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/tipoServico/listar" />">Tipo de Servi�os</a></li>
	  </ul>
   </li>
   
   <li role="presentation" class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
        Relat�rios <span class="caret"></span>
      </a>
	  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
	    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/relatorio/estoque" />" target="_blank">Estoque pe�as</a></li>
	  </ul>
   </li>
 </ul> 

</div>