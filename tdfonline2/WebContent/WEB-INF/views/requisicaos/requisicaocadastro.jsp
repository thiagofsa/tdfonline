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
			<h4>Informações da nova Requisicao</h4>
		</div>	
	 
	<spring:url value="/selectpaciente/requisicaos/" var="selectpacienteUrl" />
	<spring:url value="/selectmedico/requisicaos/" var="selectmedicoUrl" />
	<spring:url value="/selectarquivo/requisicaos/" var="selectarquivoUrl" />
	
	
	<spring:url value="/requisicaos" var="requisicaoActionUrl" />
	
	<form:form method="post" class="mt-5" modelAttribute="requisicaoForm" action="${requisicaoActionUrl}">

		<form:hidden path="id"/>
	
	
	<div  class="form-row" >
		
		<spring:bind path="paciente.nome">
		  <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
				<label for="paciente.nome">Nome do Paciente</label>			
				<form:input path="paciente.nome" type="text" class="form-control mudacursor" id="paciente.nome" 
				placeholder="Clique aqui para selecionar paciente" onclick="location.href='${selectpacienteUrl}'" readonly="true" />
				<form:errors path="paciente.nome" class="control-label" />		
				<form:hidden path="paciente.id" />			  
		  </div>
		</spring:bind>
		
	</div>
	
	
	<div  class="form-row" >
		
		<spring:bind path="caminhoarquivo">
		  <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
				<label for="caminhoarquivo">Arquivo de requisicao</label>			
				<form:input path="caminhoarquivo" type="text" class="form-control mudacursor" id="caminhoarquivo" 
				placeholder="Clique aqui para selecionar arquivo" onclick="location.href='${selectarquivoUrl}'" readonly="true" />
				<form:errors path="caminhoarquivo" class="control-label" />		
							  
		  </div>
		</spring:bind>
		
	</div>
	
		
	<div  class="form-row" >			
				
		<spring:bind path="medico.nome">
		  <div class="form-group col-md-8 ${status.error ? 'has-error' : ''}">
				<label for="medico.nome">Medico</label>
				<form:input path="medico.nome" type="text" class="form-control mudacursor" id="medico.nome" 
				placeholder="Clique aqui para selecionar o Medico" onclick="location.href='${selectmedicoUrl}'" readonly="true"/>
				<form:errors path="medico.nome" class="control-label" />
				<form:hidden path="medico.id" />
		  </div>
		</spring:bind>		
		
		<spring:bind path="data">
		  <div class="form-group col-md-2 ${status.error ? 'has-error' : ''}">			 
        		<label  for="data" >Data</label>        		
            	<form:input path="data" type="text" class="form-control" id="data" placeholder="" />
            	<form:errors path="data" class="control-label" />	   	
		  </div>
		</spring:bind>
		
				
	</div>		
	
			

		
		<div  class="spaceabaixo"></div>		
		
		<div  class="form-row justify-content-center" >				
			<div class="form-group col-xs-6 text-center">		
				<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-circle"></i> <span class="esconder"> Cadastrar</span></button>				
			</div>	
			
			<div class="form-group col-xs-6 text-center">		
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/requisicaos/"><i class="fas fa-arrow-circle-left"></i> <span class="esconder"> Cancelar</span></a>				
			</div>
		</div>			
		
	</form:form>
	
	
	
<div  class="spaceabaixo"></div>	
</div>
	
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>	
</body>
</html>
