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
	<div   class="text-center text-uppercase">
		<h4  class="h4 font-weight-bold">Relação dos Encaminhamentos</h4>
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
					<th class="text-center">Paciente</th>
					<th class="text-center">Data</th>
					<th class="text-center">Veículo</th>
					<th class="text-center">Detalhes</th>
				</tr>
			</thead>
			<c:forEach var="encaminhamento" items="${encaminhamentos}">
			    <tr>
			    	<td >${encaminhamento.marcacao.paciente.nome}</td>
					<td class="text-center">${encaminhamento.dataviagem}</td>
					<td class="text-center">${encaminhamento.distribuicao.veiculo.descricao}</td>
					<td class="text-center">
					  <spring:url value="/encaminhamentos/${encaminhamento.id}" var="encaminhamentoUrl" />
					  <spring:url value="/encaminhamentos/${encaminhamento.id}/delete" var="deleteUrl" /> 
					  <spring:url value="/encaminhamentos/${encaminhamento.id}/update" var="updateUrl" />					 
					  <button class="btn btn-sm btn-dark" data-toggle="tooltip" data-placement="botton" title="Visualizar Detalhes" onclick="location.href='${updateUrl}'"><i class="fas fa-eye"></i></button>			 
	                </td>
			    </tr>
			</c:forEach>
		</table>
		</div>

		<div  class="form-row justify-content-center mt-3" >				
			<div class="col-xs-4 text-center">		
				<a class="btn btn-outline-primary"  href="${pageContext.request.contextPath}/encaminhamentos/add">
				<i class="fas fa-plus-square mx-3"></i><span class="hidea"> Novo Encaminhamento</span></a>
			</div>
			
			<div class="col-xs-4 text-center">		
				<a class="btn btn-outline-primary"  href="${pageContext.request.contextPath}/encaminhamentos/lote">
				<i class="fas fa-plus-square mx-3"></i><span class="hidea"> Encaminhar em Lote</span></a>
			</div>	
			
			<div class=" col-xs-4 text-center">		
				<a class="btn btn-outline-secondary"  href="${pageContext.request.contextPath}/marcacaos/find">
				<i class="fas 	fa-search mx-3"></i><span class="hidea"> Busca Avançada</span></a>
			</div>
		</div> 
	
	
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