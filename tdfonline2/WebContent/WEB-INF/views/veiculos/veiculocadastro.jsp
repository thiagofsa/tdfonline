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
			<h4>Dados do novo veículo</h4>
		</div>

	<spring:url value="/veiculos" var="veiculoActionUrl" />

	<form:form class="mt-5" method="post" modelAttribute="veiculoForm" action="${veiculoActionUrl}">

		<form:hidden path="id" />
		
		<div  class="form-row" >

		<spring:bind path="marca">
		  <div class="form-group col-md-3 ${status.error ? 'has-error' : ''}">
			<label for="nome">Marca</label>				    			
    			<form:select path="marca" class="form-control"  id="marca" required="required" >
    				<form:option value="" label="Escolha uma marca"/>
    				<form:option value="Chevrolet" label="Chevrolet"/>
    				<form:option value="Fiat" label="Fiat"/>
    				<form:option value="Ford" label="Ford"/>
    				<form:option value="Iveco" label="Iveco"/>
    				<form:option value="Volkswagen" label="Volkswagen"/>    				
				</form:select>    				                               
				<form:errors path="marca" class="control-label" />			
		  </div>
		</spring:bind>
		
		
		<spring:bind path="descricao">
		  <div class="form-group col-md-7 ${status.error ? 'has-error' : ''}">
			<label for="descricao">Descrição</label>			
				<form:input path="descricao" type="text" class="form-control" id="descricao" required="required"/>                                
				<form:errors path="descricao" class="control-label" />			
		  </div>
		</spring:bind>	
		
		<spring:bind path="placa">
		  <div class="form-group col-md-2 ${status.error ? 'has-error' : ''}">
			<label for="placa">Placa</label>			
				<form:input path="placa" type="text" class="form-control" id="placa" required="required" placeholder="___-____"/>                                
				<form:errors path="placa" class="control-label" />			
		  </div>
		</spring:bind>		
		</div>
		
		<div  class="form-row" >
		
			
		<spring:bind path="vagas">
		  <div class="form-group col-md-4 ${status.error ? 'has-error' : ''}">
			<label for="vagas">Vagas Disponíveis</label>			
				<form:input path="vagas" type="text" class="form-control" id="vagas" required="required"/>                                
				<form:errors path="vagas" class="control-label" />			
		  </div>
		</spring:bind>

		<spring:bind path="tipo">
		  <div class="form-group col-md-4 ${status.error ? 'has-error' : ''}">
			<label for="nome">Tipo</label>			
				    			
    			<form:select path="tipo" class="form-control"  id="tipo" required="required" >
    				<form:option value="" label="Escolha o tipo"/>
    				<form:option value="Automóvel" label="Automóvel"/>
    				<form:option value="Ambulância" label="Ambulância"/>
    				<form:option value="Coletivo" label="Coletivo"/>
				</form:select>    				                               
				<form:errors path="tipo" class="control-label" />			
		  </div>
		</spring:bind>
		
		<spring:bind path="situacao">
		  <div class="form-group col-md-4 ${status.error ? 'has-error' : ''}">
			<label for="nome">Situação</label>			
				    			
    			<form:select path="situacao" class="form-control"  id="situacao" required="required" >
    				<form:option value="" label="Escolha a situação"/>
    				<form:option value="Disponivel" label="Disponivel"/>
    				<form:option value="Manutenção" label="Manutenção"/>
    			</form:select>    			                              
				<form:errors path="situacao" class="control-label" />			
		  </div>
		</spring:bind>
				
		</div>
    
			
		<div  class="form-row justify-content-center mt-4" >				
			<div class="form-group col-xs-6 text-center">		
				<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-circle mx-2"></i> <span class="esconder"> Cadastrar</span></button>				
			</div>	
			
			<div class="form-group col-xs-6 text-center">		
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/veiculos"><i class="fas fa-arrow-circle-left mx-2"></i> <span class="esconder"> Cancelar</span></a>				
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
    	$(document).ready(
	    	function(){    	 
	    	  $('#placa').mask('AAA-0000');
	    	  $('#vagas').mask('00');  
	    	});    	 
    	
    	function myFunction() {	    	
    		var texto = $('#cpf').cleanVal();	
    		$("#cpf2").val(texto);
    		}  
    	
    </script>		
	
</body>
</html>
