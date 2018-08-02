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
		<div class="titulo"  >
			<h4>Dados do novo Medico</h4>
		</div>

	<spring:url value="/medicos" var="medicoActionUrl" />

	<form:form  method="post" modelAttribute="medicoForm" action="${medicoActionUrl}">

		<form:hidden path="id" />

		<div  class="form-row" >
		
			<spring:bind path="nome">
			  <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
				<label for="nome">Nome</label>			
					<form:input path="nome" type="text" class="form-control" id="nome" required="required"/>                                
					<form:errors path="nome" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="crm">
			  <div class="form-group col-md-3 ${status.error ? 'has-error' : ''}">
				<label for="crm">CRM</label>			
					<form:input path="crm" type="text" class="form-control" id="crm" required="required"/>                                
					<form:errors path="crm" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="especialidade">
			  <div class="form-group col-md-3 ${status.error ? 'has-error' : ''}">
				<label for="especialidade">Especialidade</label>			
					<form:input path="especialidade" type="text" class="form-control" id="especialidade" required="required"/>                                
					<form:errors path="especialidade" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="telefone">
			  <div class="form-group col-md-3 ${status.error ? 'has-error' : ''}">
				<label for="telefone">Telefone</label>			
					<form:input path="telefone" type="text" class="form-control" id="telefone" required="required"/>                                
					<form:errors path="telefone" class="control-label" />			
			  </div>
			</spring:bind>
		
		</div>
		
		
		
		<div  class="form-row justify-content-center" >				
			<div class="form-group col-xs-6 text-center">		
				<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-circle"></i> <span class="esconder"> Cadastrar</span></button>				
			</div>	
			
			<div class="form-group col-xs-6 text-center">		
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/medicos"><i class="fas fa-arrow-circle-left"></i> <span class="esconder"> Cancelar</span></a>				
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
