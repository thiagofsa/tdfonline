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
    <title>Cadastro de Veiculos</title>
    
    <link rel="stylesheet" href= "<c:url value='/resources/css/bootstrap.css'/>" >  
    <link rel="stylesheet" href= "<c:url value='/resources/css/fontawesome.css'/>" >    
   
  </head>  

<body>

<div class="container">

	<div  class="space" ></div>
		
	<div class="titulo"  >
		<h4>Dados do novo Veiculo</h3>
	</div>


	<spring:url value="/veiculos" var="veiculoActionUrl" />

	<form:form method="post" modelAttribute="veiculoForm" action="${veiculoActionUrl}">

		<form:hidden path="id" />
		
		<div  class="form-row" >

		<spring:bind path="marca">
		  <div class="form-group col-md-4 ${status.error ? 'has-error' : ''}">
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
		  <div class="form-group col-md-5 ${status.error ? 'has-error' : ''}">
			<label for="descricao">Descrição</label>			
				<form:input path="descricao" type="text" class="form-control" id="descricao" required="required"/>                                
				<form:errors path="descricao" class="control-label" />			
		  </div>
		</spring:bind>	
		
		<spring:bind path="placa">
		  <div class="form-group col-md-3 ${status.error ? 'has-error' : ''}">
			<label for="placa">Placa</label>			
				<form:input path="placa" type="text" class="form-control" id="placa" required="required"/>                                
				<form:errors path="placa" class="control-label" />			
		  </div>
		</spring:bind>		
		</div>
		
		<div  class="form-row" >
		
			
		<spring:bind path="vagas">
		  <div class="form-group col-md-4 ${status.error ? 'has-error' : ''}">
			<label for="vagas">Vagas Disponiveis</label>			
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
    
			
		<div  class="form-row" >				
			<div class="form-group col-sm-6 text-center">		
				<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-circle"></i>  Cadastrar</button>				
			</div>	
			
			<div class="form-group col-sm-6 text-center">		
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/veiculos"><i class="fas fa-arrow-circle-left"></i> Cancelar</a>				
			</div>
		</div>		
		
	</form:form>
</div>

	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
</body>
</html>
