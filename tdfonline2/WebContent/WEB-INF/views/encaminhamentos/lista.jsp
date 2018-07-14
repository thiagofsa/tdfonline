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
    
    <link rel="stylesheet" href= "<c:url value='/resources/css/bootstrap.css'/>" >  
    <link rel="stylesheet" href= "<c:url value='/resources/css/fontawesome.css'/>" >    
    <link rel="stylesheet" href= "<c:url value='/resources/css/animate.min.css'/>" >
    <link rel="stylesheet" href= "<c:url value='/resources/css/jquery.dataTables.css'/>" > 
    
  </head>  
<body>

	<div class="container">

<div  class="spacesup"></div>
<div  class="spacesup"></div>
		<c:if test="${not empty msg}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" 
                                aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		    </div>
		</c:if>

		<h1>Todos os Encaminhamentos</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Data</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="encaminhamento" items="${encaminhamentos}">
			    <tr>
				<td>
					${encaminhamento.id}
				</td>
				<td>${encaminhamento.dataviagem}</td>
				
				
				
				<td>
				  <spring:url value="/encaminhamentos/${encaminhamento.id}" var="encaminhamentoUrl" />
				  <spring:url value="/encaminhamentos/${encaminhamento.id}/delete" var="deleteUrl" /> 
				  <spring:url value="/encaminhamentos/${encaminhamento.id}/update" var="updateUrl" />

				  <button class="btn btn-info" 
                                          onclick="location.href='${encaminhamentoUrl}'">Detalhes</button>
				  <button class="btn btn-primary" 
                                          onclick="location.href='${updateUrl}'">Atualizar</button>
				  <button class="btn btn-danger" 
                                          onclick="location.href='${deleteUrl}'">Deletar</button>
                                </td>
			    </tr>
			</c:forEach>
		</table>

	<div  class="spaceabaixo"></div>	
</div>
	
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>	
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js" integrity="sha256-u7MY6EG5ass8JhTuxBek18r5YG6pllB9zLqE4vZyTn4=" crossorigin="anonymous"></script>
  <script>
	  $(document).ready(function() {
		    $('#Tabela').DataTable();
		} );
  </script>   
</body>
</html>