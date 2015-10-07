<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="true"%>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
    
	<c:choose>
		<c:when test="${empty login}">
			<h3>Seu usu�rio n�o tem permiss�o para acessar esta p�gna!</h3>
		</c:when>
		<c:otherwise>
			<h3>Login : ${login} 
			<br/>
			<br/>
			Seu usu�rio n�o tem permiss�o para acessar esta p�gina!</h3>
		</c:otherwise>
	</c:choose>
	
    </tiles:putAttribute>
</tiles:insertDefinition>