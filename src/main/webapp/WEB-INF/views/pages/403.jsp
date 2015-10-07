<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="true"%>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
    
	<c:choose>
		<c:when test="${empty login}">
			<h3>Seu usuário não tem permissão para acessar esta págna!</h3>
		</c:when>
		<c:otherwise>
			<h3>Login : ${login} 
			<br/>
			<br/>
			Seu usuário não tem permissão para acessar esta página!</h3>
		</c:otherwise>
	</c:choose>
	
    </tiles:putAttribute>
</tiles:insertDefinition>