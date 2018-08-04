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
	<div   class="text-center text-uppercase font-weight-bold">
		<h4  class="h4 font-weight-bold">Relação dos Procedimentos</h4>
	</div>
	
	<div  class="row areanotify justify-content-end py-1">		
		<c:if test="${not empty msg}">			    
			<strong id="textonotify" class="animated fadeout font-italic" ><i class="fas fa-check-circle fa-lg text-success mr-1"></i>${msg}</strong>
		</c:if>		
	</div>

		<div class="table-responsive-md animated fadein" >	
		<table id="Tabela" class="table table-striped table-hover display"  style="width:100%">
			<thead>
				<tr>
					<th class="text-center">Descrição</th>
					<th class="text-center hidea">Especialidade</th>
					<th class="text-center">Detalhes</th>
				</tr>
			</thead>

			<c:forEach var="procedimento" items="${procedimentos}">
			    <tr>
				<td>${procedimento.nome}</td>
				<td class="text-center hidea">${procedimento.area}</td>				
				<td class="text-center">
				  <spring:url value="/procedimentos/${procedimento.id}/update" var="updateUrl" />
				  <button class="btn btn-sm btn-dark" data-toggle="tooltip" data-placement="botton" title="Visualizar Detalhes" onclick="location.href='${updateUrl}'"><i class="fas fa-eye"></i></button>
				 </td>
			    </tr>
			</c:forEach>
		</table>
		</div>
		
		<div class="row justify-content-center text-center mt-3 animated fadein" >			
			<a class="btn btn-outline-primary"  href="${pageContext.request.contextPath}/procedimentos/add">
			<i class="fas fa-plus-square mx-3"></i><span class="hidea"> Adicionar Procedimento</span></a>
		</div>	
				
<div  class="spaceabaixo"></div>	
</div>	
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>	
<script>
	  $(document).ready(
			  function() {
		    	$('#Tabela').DataTable();    	
		} );
  </script>   
</body>
</html>