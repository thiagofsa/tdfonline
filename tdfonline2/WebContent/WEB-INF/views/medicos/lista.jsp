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
    <link rel="stylesheet" href= "<c:url value='/resources/css/jquery.dataTables.css'/>" >
  </head>  
<body>
	<div class="container">

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

		<div   class="titulo">
			<h4>Relação dos Medicos</h4>
		</div>

	<div class="table-responsive-md" >
		<table id="Tabela" class="table table-striped table-hover display"  style="width:100%">
			<thead>
				<tr>					
					<th class="text-center">Nome</th>
					<th class="text-center">CRM</th>
					<th class="text-center">Especialidade</th>
				</tr>
			</thead>

			<c:forEach var="medico" items="${medicos}">
			    <tr>
				<td>${medico.nome}</td>				
				<td class="text-center">${medico.crm}</td>
				<td class="text-center">${medico.especialidade}</td>
				<td class="text-center">
				  <spring:url value="/medicos/${medico.id}/update" var="updateUrl" />			  
				  <button class="btn btn-sm" data-toggle="tooltip" data-placement="botton" title="Visualizar Detalhes" onclick="location.href='${updateUrl}'"><i class="fas fa-newspaper"></i></button>			  
                 </td>
			    </tr>
			</c:forEach>
		</table>
		</div>
		
		<div  class="spacesup"></div>	
		<div class="row" >
			<div  class="col-md-4"></div>
			<div  class="col-md-4"  >	
				<a class="btn btn-block  btn-outline-primary"  href="${pageContext.request.contextPath}/medicos/add"><i class="fas fa-plus-square"></i>  Adcionar Medico</a>
			</div>
			<div  class="col-md-4"></div>		
		</div>		
	
	<div  class="spaceabaixo"></div>	
</div>
	
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>	
  <script>
	  $(document).ready(function() {
		    $('#Tabela').DataTable();
		} );
  </script>
   
</body>
</html>