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
		
<spring:url value="/encaminhamentos/selectmarcacao2" var="selectmarcacaoUrl" />

	<form:form class="form-horizontal" 
                modelAttribute="marcacao" action="${selectmarcacaoUrl}">
		
		<spring:bind path="dataviagem">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Data Marcacao </label>
			<div class="col-sm-10">
				<form:input path="dataviagem" type="text" class="form-control" 
                                id="dataviagem" placeholder="Data" />
				<form:errors path="dataviagem" class="control-label" />
			</div>
		  </div>
		</spring:bind>

				
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			
			     <button type="submit" class="btn-lg btn-primary pull-right" >Pesquisar
                             </button>
			
		  </div>
		</div>
	</form:form>
	
	
	
	<c:if test="${not empty marcacaos}">

		<h1>Marcacaos</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Data</th>
					<th>Nome Paciente</th>
					<th>Procedimento</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="marcacao" items="${marcacaos}">
			    <tr>
				<td>
					${marcacao.id}
				</td>
				<td>
					${marcacao.dataviagem}
				</td>
				<td>${marcacao.paciente.nome}</td>
				
				<td>${marcacao.procedimento.nome}</td>
				
				<td>
				  <spring:url value="/encaminhamentos/selectmarcacao/${marcacao.id}" var="selectmarcacaoUrl" />
				  	  <button class="btn btn-info" 
                                          onclick="location.href='${selectmarcacaoUrl}'">Selecionar</button>
				  
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