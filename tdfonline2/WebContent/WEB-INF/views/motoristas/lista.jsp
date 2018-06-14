<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html lang="pt-br">

  <head>    
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><tiles:getAsString name="title" /></title>
    
    <link href="../resources/css/bootstrap.css"  rel="stylesheet" >
    <link href="../resources/css/fontawesome-all.min.css"  rel="stylesheet" > 
    
  </head> 
 
  <body>

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

		<div   class="text-center">
		Relação dos Motoristas
		</div>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Email</th>
					<th>Endereco</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="motorista" items="${motoristas}">
			    <tr>
				<td>
					${motorista.id}
				</td>
				<td>${motorista.nome}</td>
				<td>${motorista.email}</td>
				<td>${motorista.endereco}</td>
				
				<td>
				  <spring:url value="/motoristas/${motorista.id}" var="motoristaUrl" />
				  <spring:url value="/motoristas/${motorista.id}/delete" var="deleteUrl" /> 
				  <spring:url value="/motoristas/${motorista.id}/update" var="updateUrl" />

				  <button class="btn btn-info" 
                                          onclick="location.href='${motoristaUrl}'">Detalhes</button>
				  <button class="btn btn-primary" 
                                          onclick="location.href='${updateUrl}'">Atualizar</button>
				  <button class="btn btn-danger" 
                                          onclick="location.href='${deleteUrl}'">Deletar</button>
                                </td>
			    </tr>
			</c:forEach>
		</table>
		
		<a class="btn btn-outline-primary"  href="${pageContext.request.contextPath}/motoristas/add">Novo</a>
	</div>
	
	
	<script src="../resources/js/jquery.min.js"  type="text/javascript"></script>
	<script src="../resources/js/popper.min.js"  type="text/javascript"></script>
	<script src="../resources/js/bootstrap.js"  type="text/javascript"></script>
	</body>
	</html>
