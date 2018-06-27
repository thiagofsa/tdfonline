<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">


<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" 
                                aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>Usuario - Detalhes </h1>
	<br />

	<div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10">${usuario.id}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Login</label>
		<div class="col-sm-10">${usuario.login}</div>
	</div>
	
	
	<div class="row">
		<label class="col-sm-2">Nome</label>
		<div class="col-sm-10">${usuario.nome}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Telefone</label>
		<div class="col-sm-10">${usuario.telefone}</div>
	</div>

	
	
</div>


</body>
</html>