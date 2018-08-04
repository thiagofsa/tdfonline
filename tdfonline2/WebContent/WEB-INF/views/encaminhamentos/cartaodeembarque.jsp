<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

<div class="container"  >

<div  class="spacesup"></div>
<div  class="spacesup"></div>

<spring:url value="/encaminhamentos/cartaodeembarque2" var="findUrl" />

	<form action="${findUrl}" method="post">

		
			<div class="controls">
    			Paciente <input type="text" id="nomepaciente" name="nomepaciente">
        	</div>
        	
	 		<div class="controls">
    			Data viagem Ida: <input type="text" id="dataviagemida" name="dataviagemida">
        	</div>
    		
		 	<div class="controls">
    			Data viagem Volta: <input type="text" id="dataviagemvolta" name="dataviagemvolta">
        	</div>

			
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			
			     <button type="submit" class="btn-lg btn-primary pull-right" >Pesquisar
                             </button>
			
		  </div>
		</div>
	</form>
	
	<br><br>
	
	<c:if test="${not empty encaminhamentosida}">
	<h2>Encaminhamentos IDA </h2>	
		<div class="table-responsive-md mt-4" >		
		<table class="table table-sm table-striped table-hover display" >
			<thead>
				<tr>
					<th class="text-center">ID</th>
					<th class="text-center">Nome Paciente</th>
					<th class="text-center">Cpf</th>
					<th class="text-center">Destino</th>
					<th class="text-center">Procedimento</th>
					<th class="text-center">Data</th>
					<th class="text-center">Selecionar</th>
				</tr>
			</thead>

			<c:forEach var="encaminhamento" items="${encaminhamentosida}">
			    <tr>				
				<td>${encaminhamento}</td>
				<td>${encaminhamento.marcacao.paciente.nome}</td>
				<td class="text-center">${encaminhamento.marcacao.paciente.cpf}</td>				
				<td class="text-center">${encaminhamento.marcacao.unidadesaude.descricao}</td>
				<td class="text-center">${encaminhamento.marcacao.procedimento.nome}</td>
				<td class="text-center">${encaminhamento.dataviagem}</td>
				<td class="text-center">
				  <spring:url value="/reports/cartaodeembarque/${1}/${encaminhamento.id}" var="encaminhamentoUrl" />
				  <button class="btn btn-sm" data-toggle="tooltip" data-placement="botton" title="Selecione este Encaminhamento" onclick="location.href='${encaminhamentoUrl}'"><i class="fas fa-check-circle"></i></button>			  
                </td>
			    </tr>
			</c:forEach>
		</table>
		</div>
		</c:if>
	
	<br><br><br><br><br>
	
				
		<c:if test="${not empty encaminhamentosvoltacomorigem}">
		
		<h2>Encaminhamentos VOLTA com Origem</h2>	

		<div class="table-responsive-md mt-4" >		
		<table class="table table-sm table-striped table-hover display" >
			
			<thead>
					<tr>
					<th class="text-center">ID</th>
					<th class="text-center">Nome Paciente</th>
					<th class="text-center">Cpf</th>
					<th class="text-center">Destino</th>
					<th class="text-center">Procedimento</th>
					<th class="text-center">Data</th>
					<th class="text-center">Selecionar</th>
				</tr>
				</thead>
				
			<c:forEach var="encaminhamentovolta" items="${encaminhamentosvoltacomorigem}">
				
				<tr>
				
				<td>${encaminhamentovolta.id}</td>
				<td>${encaminhamentovolta.encaminhamento.marcacao.paciente.nome}</td>
				<td>${encaminhamentovolta.encaminhamento.marcacao.paciente.cpf}</td>
				<td>${encaminhamentovolta.encaminhamento.marcacao.unidadesaude.descricao}</td>
				<td>${encaminhamentovolta.encaminhamento.marcacao.procedimento.nome}</td> 
				<td>${encaminhamentovolta.dataviagem}</td>
				
				<td class="text-center">
				  <spring:url value="/reports/cartaodeembarque/${2}/${encaminhamentovolta.id}" var="encaminhamentovoltaUrl" />
				  <button class="btn btn-sm" data-toggle="tooltip" data-placement="botton" title="Selecione este Encaminhamento" onclick="location.href='${encaminhamentovoltaUrl}'"><i class="fas fa-check-circle"></i></button>			  
                </td>
                </tr>

						    
			</c:forEach>
		</table>
		</div>
		</c:if>
		
		
<c:if test="${not empty encaminhamentosvoltaavulsos}">
		
		<h2>Encaminhamentos VOLTA avulsos</h2>	

		<div class="table-responsive-md mt-4" >		
		<table class="table table-sm table-striped table-hover display" >
			
			<thead>
					<tr>
					<th class="text-center">ID</th>
					<th class="text-center">Nome Paciente</th>
					<th class="text-center">Cpf</th>
					<th class="text-center">Destino</th>
					<th class="text-center">Procedimento</th>
					<th class="text-center">Data</th>
					<th class="text-center">Selecionar</th>
				</tr>
				</thead>
				
			<c:forEach var="encaminhamentovoltaavulso" items="${encaminhamentosvoltaavulsos}">
			    				
    			
				
				<!-- se exsistir um encaminhamentoida associado -->
				
									
				<tr>
				
				<td>${encaminhamentovoltaavulso.id}</td>
				<td>${encaminhamentovoltaavulso.paciente.nome}</td>
				<td>${encaminhamentovoltaavulso.paciente.cpf}</td>
				<td>Volta</td>
				<td>-</td>
				<td>${encaminhamentovolta.dataviagem}</td>
				
				<td class="text-center">
				  <spring:url value="/reports/cartaodeembarque/${2}/${encaminhamentovolta.id}" var="encaminhamentovoltaUrl" />
				  <button class="btn btn-sm" data-toggle="tooltip" data-placement="botton" title="Selecione este Encaminhamento" onclick="location.href='${encaminhamentovoltaUrl}'"><i class="fas fa-check-circle"></i></button>			  
                </td>
                </tr>
				
						    
			</c:forEach>
		</table>
		</div>
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
