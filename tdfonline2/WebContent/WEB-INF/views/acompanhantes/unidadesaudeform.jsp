<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">


<div class="container">

	<c:choose>
		<c:when test="${unidadesaudeForm['new']}">
			<h1>Cadastrar Motorista</h1>
		</c:when>
		<c:otherwise>
			<h1>Atualizar Motorista</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/unidadesaudes" var="unidadesaudeActionUrl" />

	<form:form class="form-horizontal" method="post" 
                modelAttribute="unidadesaudeForm" action="${unidadesaudeActionUrl}">

		<form:hidden path="id" />

		<spring:bind path="descricao">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Nome</label>
			<div class="col-sm-10">
				<form:input path="descricao" type="text" class="form-control" 
                                id="descricao" placeholder="Descricao" />
				<form:errors path="descricao" class="control-label" />
			</div>
		  </div>
		</spring:bind>

		<spring:bind path="logradouro">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Email</label>
			<div class="col-sm-10">
				<form:input path="logradouro" class="form-control" 
                                id="logradouro" placeholder="Logradouro" />
				<form:errors path="logradouro" class="control-label" />
			</div>
		  </div>
		</spring:bind>

		
				
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			<c:choose>
			  <c:when test="${unidadesaudeForm['new']}">
			     <button type="submit" class="btn-lg btn-primary pull-right">Cadastrar
                             </button>
			  </c:when>
			  <c:otherwise>
			     <button type="submit" class="btn-lg btn-primary pull-right">Atualizar
                             </button>
			  </c:otherwise>
			</c:choose>
		  </div>
		</div>
	</form:form>

</div>


</body>
</html>
