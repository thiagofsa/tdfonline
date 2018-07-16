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

		<c:if test="${not empty msg}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" 
                                aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		    </div>
		</c:if>

		<h1>Todos os EncaminhamentoVoltas</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Data</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="encaminhamentovolta" items="${encaminhamentovoltas}">
			    <tr>
				<td>
					${encaminhamentovolta.id}
				</td>
				<td>${encaminhamentovolta.dataviagem}</td>
				
				
				
				<td>
				  <spring:url value="/encaminhamentovoltas/${encaminhamentovolta.id}" var="encaminhamentovoltaUrl" />
				  <spring:url value="/encaminhamentovoltas/${encaminhamentovolta.id}/delete" var="deleteUrl" /> 
				  <spring:url value="/encaminhamentovoltas/${encaminhamentovolta.id}/update" var="updateUrl" />

				  <button class="btn btn-info" 
                                          onclick="location.href='${encaminhamentovoltaUrl}'">Detalhes</button>
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
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
  <script>
	  $(document).ready(
			  function() {
		    	$('#Tabela').DataTable();
		    	$('.telefone').mask("(00) 00000-0000");    	 
		    	$('.cpf').mask('000.000.000-00');		    	
		} );
  </script>   
</body>
</html>
