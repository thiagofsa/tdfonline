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
  </head>  
<body>

<div class="container">

	<div  class="spacesup"></div>		
	<div   class="text-center text-uppercase">
		<h4>informações da nova viagem</h4>
	</div>

	<spring:url value="/selectmotorista/distribuicaos/" var="selectmotoristaUrl" />	
	<spring:url value="/selectveiculo/distribuicaos/" var="selectveiculoUrl" />					                                      

	<spring:url value="/distribuicaos" var="distribuicaoActionUrl" />
	<form:form class="mt-4" method="post" modelAttribute="distribuicaoForm" action="${distribuicaoActionUrl}">

		<form:hidden path="id" />
		<form:hidden path="vagas" />
		<form:hidden path="tipoviagem" />
		
		<div  class="form-row" >
			
			<spring:bind path="dataviagem">
			  <div class="form-group col-md-2 ${status.error ? 'has-error' : ''}">			 
	        		<label  for="dataviagem" >Data</label>        		
	            	<form:input path="dataviagem" type="text" class="form-control" id="dataviagem" placeholder="" />
	            	<form:errors path="dataviagem" class="control-label" />	   	
			  </div>
			</spring:bind>
		
	
			<spring:bind path="motorista.nome">
			  <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
				<label for="motorista" >Nome do Motorista</label>			
					<form:input path="motorista.nome" type="text" id="motorista" class="form-control mudacursor" onclick="location.href='${selectmotoristaUrl}'" placeholder="Clique aqui para selecionar o Motorista." readonly="true" />
					<form:errors path="motorista.nome" class="control-label" />
					<form:hidden path="motorista.id" />	 		 
			  </div>		 
			</spring:bind>
		
			<spring:bind path="veiculo.descricao">
			  <div class="form-group col-md-4 ${status.error ? 'has-error' : ''}">
				<label  for="veiculo" class="control-label">Veículo</label>			
					<form:input path="veiculo.descricao" type="text" class="form-control mudacursor" id="veiculo" onclick="location.href='${selectveiculoUrl}'" placeholder="Clique aqui para selecionar o Veículo." readonly="true" />
					<form:errors path="veiculo.descricao" class="control-label" />
					<form:hidden path="veiculo.id" />		
			  </div>
			</spring:bind>
		
		</div>
		
		<div  class="form-row justify-content-center mt-4" >				
			<div class="form-group col-xs-6 text-center">		
				<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-circle"></i> <span class="esconder"> Cadastrar</span></button>				
			</div>	
			
			<div class="form-group col-xs-6 text-center">		
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/distribuicaos/"><i class="fas fa-arrow-circle-left"></i> <span class="esconder"> Cancelar</span></a>				
			</div>
		</div>	
		
	</form:form>

<div  class="spaceabaixo"></div>	
</div>
	
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js" integrity="sha256-u7MY6EG5ass8JhTuxBek18r5YG6pllB9zLqE4vZyTn4=" crossorigin="anonymous"></script>
  <script>
	  $(document).ready(function() {
		   
		} );
  </script>   
</body>
</html>
