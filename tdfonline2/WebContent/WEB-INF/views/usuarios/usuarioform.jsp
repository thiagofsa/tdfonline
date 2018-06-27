<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">


<div class="container">

	<c:choose>
		<c:when test="${usuarioForm['new']}">
			<h1>Cadastrar Usuario</h1>
		</c:when>
		<c:otherwise>
			<h1>Atualizar Usuario</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/usuarios" var="usuarioActionUrl" />

	<form:form class="form-horizontal" method="post" 
                modelAttribute="usuarioForm" action="${usuarioActionUrl}">

		<form:hidden path="id" />

		<spring:bind path="login">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Login</label>
			<div class="col-sm-10">
				<form:input path="login" type="text" class="form-control" 
                                id="login" placeholder="Login" />
				<form:errors path="login" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<spring:bind path="nome">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Nome</label>
			<div class="col-sm-10">
				<form:input path="nome" type="text" class="form-control" 
                                id="nome" placeholder="Nome" />
				<form:errors path="nome" class="control-label" />
			</div>
		  </div>
		</spring:bind>

		<spring:bind path="telefone">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Telefone</label>
			<div class="col-sm-10">
				<form:input path="telefone" class="form-control" 
                                id="telefone" placeholder="Telefone" />
				<form:errors path="telefone" class="control-label" />
			</div>
		  </div>
		</spring:bind>

		
		<spring:bind path="email">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Email</label>
			<div class="col-sm-10">
				<form:input path="email" class="form-control" 
                                id="email" placeholder="Email" />
				<form:errors path="email" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<spring:bind path="admin">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Admin</label>
			<div class="col-sm-10">
				<form:input path="admin" class="form-control" 
                                id="admin" placeholder="Admin" />
				<form:errors path="admin" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
				
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			<c:choose>
			  <c:when test="${usuarioForm['new']}">
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
