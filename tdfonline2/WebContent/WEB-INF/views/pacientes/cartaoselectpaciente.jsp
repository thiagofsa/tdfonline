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
		<div class="text-center text-uppercase">
			<h4>Informe o Paciente</h4>
		</div>

		<spring:url value="/report/selectdistribuicao/" var="findUrl" />
		<form action="${findUrl}" method="get">
	
			<div  class="form-group col-sm-1"></div>			
			
			  <div class="form-group col-sm-8">		
					<form:input  type="text" class="form-control" id="nomepaciente" name="nomepaciente" required="required"/>                                								
			  </div>			
						
			<div class="form-group col-sm-3 d-flex justify-content-center justify-content-md-start">
				<button type="submit" class="btn btn-outline-secondary"><i class="fas fa-search"></i> <span class="esconder"> Pesquisar</span></button>
			</div>	
			
		</form>

	
		
		<c:if test="${not empty distribuicaos}">
		
			<div class="text-center mt-5">
				<h5>Viagens Marcadas</h5>		
			</div>
			
			<spring:url value="/reports/encaminhamentospormotoristaedatareport" var="findUrl" />			
			<form class="my-4"  action="${findUrl}" method="get">		
			
				<div class="table-responsive-md mt-3" >		
					<table class="table table-sm table-striped table-hover display" >
						<thead>
							<tr>
								<th class="text-center">ID</th>
								<th class="text-center">Nome</th>
								<th class="text-center">Veiculo</th>
								<th class="text-center">Data</th>
								<th class="text-center">Imprimir</th>
							</tr>
						</thead>
			
						<c:forEach var="distribuicao1" items="${distribuicaos}">
							
							<input type="hidden" id="idmotorista"  name="idmotorista"  value="${distribuicao1.motorista.id}">
							<input type="hidden" id="dataviagem"  name="dataviagem"  value="${distribuicao1.dataviagem}">
							
						    <tr>
						    	<td class="text-center">${distribuicao1.id}</td>						    							    	
						    	<td>${distribuicao1.motorista.nome}</td>
						    	<td class="text-center">${distribuicao1.veiculo.descricao}</td>								
								<td class="text-center">${distribuicao1.dataviagem}</td>	
										
								<td  class="text-center">	
								  <button type="submit"  class="btn" data-toggle="tooltip" data-placement="botton" title="Imprimir Pauta"><i class="fas fa-print"></i></button>				  
				                </td>
						    </tr>
						</c:forEach>
					</table>
				</div>					   						
			</form>
		</c:if>	
		
</div>	
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>	
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
  <script>
	  $(document).ready();
  </script>   
</body>
</html>