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
				<span aria-hidden="true">�</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>Beneficio - Detalhes </h1>
	<br />

	<div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10">${id}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Nome Paciente</label>
		<div class="col-sm-10">${paciente.nome}</div>
	</div>
	
	
	<div class="row">
		<label class="col-sm-2">Valor</label>
		<div class="col-sm-10">${valor}</div>
	</div>

		
	
</div>


</body>
</html>