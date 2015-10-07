<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<sec:authentication property="principal.username" var="userLogin" />
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href='<c:url value="/home"/>'>Oficina de Manutenção e Serviços</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="<c:url value="/empresa/telaAtualizar"/>">Configurações</a></li>
        <li><a href="<c:url value="/about"/>">Sobre</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
          Olá ${userLogin }!
          <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
          	<c:if test="${userLogin ne 'Admin' }">
          		<li><a href="<c:url value="/funcionario/perfil/${userLogin }"/>">Perfil</a></li>
          	</c:if>
			<li><a href="javascript:formLogout()">Sair</a></li>
          </ul>
        </li>
      </ul>
      <form action="${requestScope['javax.servlet.forward.context_path']}/busca/pesquisar" class="navbar-form navbar-right" method="GET">
        <div class="input-group">
        	<span class="glyphicon glyphicon-search input-group-addon" aria-hidden="true"></span>
        	<input type="number" name="palavra" class="form-control" placeholder="Busca..." data-toggle="tooltipbusca" data-placement="bottom" title="Informe o número do Orçamento ou da OS."/>
        </div>
      </form>
      <c:url value="/j_spring_security_logout" var="logoutUrl" />
	  <form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	  </form>
	  <script>function formLogout() { document.getElementById("logoutForm").submit(); }</script>
    </div>
  </div>
</nav>
<script type="text/javascript">
	$(document).ready(function(){
		$('[data-toggle="tooltipbusca"]').tooltip();
	});
</script>