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
		<h4  class="h4 font-weight-bold">Relação dos Últimos Agendamentos</h4>
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
					<th class="text-center hidea">Unidade de Saúde</th>
					<th class="text-center hidea">Procedimento</th>
					<th class="text-center">Data</th>
					<th class="text-center">Status</th>
					<th class="text-center">Ações</th>
				</tr>
			</thead>

			<c:forEach var="marcacao" items="${marcacaos}">
			    <tr>
				<td>${marcacao.paciente.nome}</td>
				<td class="hidea" >${marcacao.unidadesaude.descricao}</td>
				<td class="hidea" >${marcacao.procedimento.nome}</td>
				<td class="text-center">${marcacao.dataviagem}</td>
				<td class="text-center">${marcacao.confirmada}</td>				
				<td class="text-center">				  
				  <spring:url value="/marcacaos/${marcacao.id}/update" var="updateUrl" />
				  <spring:url value="/marcacaos/${marcacao.id}/replica" var="replicarUrl" />				  
				  <button class="btn btn-sm btn-dark" data-toggle="tooltip" data-placement="botton" title="Visualizar Detalhes" onclick="location.href='${updateUrl}'"><i class="fas fa-eye"></i></button>
                  <button class="btn btn-sm btn-dark" data-toggle="tooltip" data-placement="botton" title="Clonar Marcação" onclick="location.href='${replicarUrl}'"><i class="fas fa-clone"></i></button>
                </td>                                
			    </tr>
			</c:forEach>
		</table>
		</div>	
		
		<div  class="form-row justify-content-center mt-3" >				
			<div class="col-xs-6 text-center">		
				<a class="btn btn-outline-primary"  href="${pageContext.request.contextPath}/marcacaos/add">
				<i class="fas fa-plus-square mx-2"></i><span class="hidea"> Novo Agendamento</span></a>
			</div>	
			
			<div class=" col-xs-6 text-center">		
				<a class="btn btn-outline-secondary"  href="${pageContext.request.contextPath}/marcacaos/find">
				<i class="fas fa-search mx-3"></i><span class="hidea"> Busca Avançada</span></a>
			</div>
		</div>  
				
	<div  class="spaceabaixo"></div>	
</div>
	
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
  <script>
	  $(document).ready(function() {
		    $('#Tabela').DataTable();
		} );
  </script>
   
</body>
</html>