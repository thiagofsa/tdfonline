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
    <link rel="stylesheet" href= "<c:url value='/resources/css/animate.min.css'/>" >     
  </head>  
<body>

	<div class="container">

		<div  class="spacesup"></div>		
		<div class="text-center text-uppercase">
			<h4>Selecione o veículo</h4>
		</div>
		
				<!-- <spring:url value="/distribuicaos/selectveiculo2" var="selectveiculoUrl" />
				<form:form   modelAttribute="veiculo" action="${selectveiculoUrl}">		
					<spring:bind path="descricao">
					  <div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="col-sm-2 control-label">Descricao</label>
						<div class="col-sm-10">
							<form:input path="descricao" type="text" class="form-control" 
			                                id="descricao" placeholder="Descricao" />
							<form:errors path="descricao" class="control-label" />
						</div>
					  </div>
					</spring:bind>
							
					<div class="form-group">
					  <div class="col-sm-offset-2 col-sm-10">			
						     <button type="submit" class="btn-lg btn-primary pull-right" >Pesquisar
			                             </button>			
					  </div>
					</div>
				</form:form> -->	
	
	<c:if test="${not empty veiculos}">

		<div class="table-responsive-md mt-4" >		
		<table class="table table-sm table-striped table-hover display" >
			<thead>
				<tr>					
					<th class="text-center">Descrição</th>
					<th class="text-center">Placa</th>
					<th class="text-center">Vagas</th>					
					<th class="text-center">Selecionar</th>
				</tr>
			</thead>

			<c:forEach var="veiculo" items="${veiculos}">
			    <tr>
				<td>${veiculo.descricao}</td>
				<td class="text-center">${veiculo.placa}</td>
				<td class="text-center">${veiculo.vagas}</td>
				
				<td class="text-center">
				  <spring:url value="/distribuicaos/selectveiculo/${veiculo.id}" var="selectveiculoUrl" />
				  	  <button class="btn btn-sm" data-toggle="tooltip" data-placement="botton" title="Selecione este Veículo" onclick="location.href='${selectveiculoUrl}'"><i class="fas fa-check-circle"></i></button>			  
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
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
  <script>
	  $(document).ready(function() {
		   
		} );
  </script>   
</body>
</html>