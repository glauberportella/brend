<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="true"%>

<tiles:insertDefinition name="loginTemplate">
    <tiles:putAttribute name="body">
   	
	<c:if test="${not empty error}">
		<div class="alert alert-danger" role="alert">${error}</div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="alert alert-warning" role="alert">${msg}</div>
	</c:if>
   	
   	<form name="loginForm" action="<c:url value='/j_spring_security_check' />" method="POST" class="form-signin">
		<h2 class="form-signin-heading">Identificação</h2>
        <label for="login" class="sr-only">Login</label>
		<input type="text" id="login" name="login" class="form-control" placeholder="Login" required="required" />
        <label for="senha" class="sr-only">Senha</label>
		<input type="password" id="senha" name="senha" class="form-control" placeholder="Senha" required="required" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input name="submit" type="submit" class="btn btn-lg btn-primary btn-block" value="Entrar" />
	</form>
   	
    </tiles:putAttribute>
</tiles:insertDefinition>