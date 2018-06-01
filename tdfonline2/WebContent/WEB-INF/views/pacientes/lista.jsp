<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

<!DOCTYPE html>
<html lang="en">


<body>

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

		<h1>Todos as Unidades de Saude</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Descricao</th>
					<th>Logradouro</th>
					<th>Telefone</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="unidadesaude" items="${unidadesaudes}">
			    <tr>
				<td>
					${unidadesaude.id}
				</td>
				<td>${unidadesaude.descricao}</td>
				<td>${unidadesaude.logradouro}</td>
				<td>${unidadesaude.telefone}</td>
				
				<td>
				  <spring:url value="/unidadesaudes/${unidadesaude.id}" var="unidadesaudeUrl" />
				  <spring:url value="/unidadesaudes/${unidadesaude.id}/delete" var="deleteUrl" /> 
				  <spring:url value="/unidadesaudes/${unidadesaude.id}/update" var="updateUrl" />

				  <button class="btn btn-info" 
                                          onclick="location.href='${unidadesaudeUrl}'">Detalhes</button>
				  <button class="btn btn-primary" 
                                          onclick="location.href='${updateUrl}'">Atualizar</button>
				  <button class="btn btn-danger" 
                                          onclick="location.href='${deleteUrl}'">Deletar</button>
                                </td>
			    </tr>
			</c:forEach>
		</table>

	</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

</body>
</html>