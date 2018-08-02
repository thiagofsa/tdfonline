<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>

<html lang="pt-br">

  <head>    
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><tiles:getAsString name="title" /></title>
    
    <link rel="stylesheet" href= "<c:url value='/resources/css/bootstrap.css'/>" >  
    <link rel="stylesheet" href= "<c:url value='/resources/css/fontawesome.css'/>" >       
  </head>  
 
<body>

	
	<div class="container">
	
	<div  class="spacesup"></div>
	<div  class="spacesup"></div>

		<c:if test="${not empty msg}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" dataviagem-dismiss="alert" 
                                aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		    </div>
		</c:if>


 		
<spring:url value="/logtransacaos/selectusuario2" var="selectUrl" />

	<form class="form-horizontal" 
                action="${selectUrl}">
		
		<input id="nome" name="nome" type="text" class="form-control" 
                                />
				
			
				
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			
			     <button type="submit" class="btn-lg btn-primary pull-right" >Pesquisar
                             </button>
			
		  </div>
		</div>
	
	
	</form>
		
	<c:if test="${not empty usuarios}">

		<h1>Usuarios </h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Nome</th>
					<th>Login</th>
					<th>Telefone</th>
					<th>Action</th>
					
					
	
				</tr>
			</thead>

			<c:forEach var="usuario" items="${usuarios}">
			    <tr>
				<td>
					${usuario.id}
				</td>
				<td>${usuario.nome}</td>
				<td>${usuario.login}</td>
				<td>${usuario.telefone}</td>
				
				
				<td>
				  <spring:url value="/logtransacaos/selectusuario/${usuario.id}" var="logtransacaoUrl" />
				  

				  <button class="btn btn-info" 
                                          onclick="location.href='${logtransacaoUrl}'">Selecionar</button>
				  
                </td>
			    </tr>
			</c:forEach>
		</table>
		</c:if>
	
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