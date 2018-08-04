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
    <link rel="stylesheet" href= "<c:url value='/resources/css/jquery.dataTables.css'/>">
  </head>  
<body>
	<div class="container">

		<div  class="spacesup"></div>		
		<div   class="text-center text-uppercase font-weight-bold">
			<h4  class="h4 font-weight-bold">Relação dos Pacientes</h4>
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
					<th class="text-center">Nome</th>
					<th class="text-center hidea">Telefone</th>
					<th class="text-center">Cpf</th>
					<th class="text-center esconder">Cartão SUS</th>
					<th class="text-center">Detalhes</th>
				</tr>
			</thead>

			<c:forEach var="paciente" items="${pacientes}">
			    <tr>
					<td>${paciente.nome}</td>				
					<td class="text-center telefone hidea">${paciente.telefone}</td>
					<td class="text-center cpf">${paciente.cpf}</td>
					<td class="text-center cartaosus hidea">${paciente.cartaosus}</td>
					<td class="text-center">
						<spring:url value="/pacientes/${paciente.id}/update" var="updateUrl" />			  
						<button class="btn btn-sm btn-dark" data-toggle="tooltip" data-placement="botton" title="Visualizar Detalhes" onclick="location.href='${updateUrl}'"><i class="fas fa-eye"></i></i></button>			  
	                </td>
			    </tr>
			</c:forEach>
		</table>
		</div>
		
		<div class="row justify-content-center text-center mt-3 animated fadein" >			
			<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/pacientes/add">
			<i class="fas fa-plus-square mx-3 "></i><span class="esconder"> Adicionar Paciente</span></a>
		</div>

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
		    	$('.cartaosus').mask('000 0000 0000 0000');
		} );
  </script>   
</body>
</html>