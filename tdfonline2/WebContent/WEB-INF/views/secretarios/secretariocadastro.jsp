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
			<h4>Dados do novo Secretario 1</h4>
		</div>

	

	<spring:url value="/secretarios" var="secretarioActionUrl" />

	<form:form  method="post" modelAttribute="secretarioForm" action="${secretarioActionUrl}">
	
	<spring:url value="/selectarquivo/secretarios/" var="selectarquivoUrl" />

		<form:hidden path="id"/>

		<div  class="form-row" >
		
			<spring:bind path="nome">
			  <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
				<label for="nome">Nome</label>			
					<form:input path="nome" type="text" class="form-control" id="nome" required="required"/>                                
					<form:errors path="nome" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="datainiciomandato">
			  <div class="form-group col-md-3 ${status.error ? 'has-error' : ''}">
				<label for="datainiciomandato">Data Inicio Mandato</label>			
					<form:input path="datainiciomandato" type="text" class="form-control" id="datainiciomandato" required="required"/>                                
					<form:errors path="datainiciomandato" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="datafimmandato">
			  <div class="form-group col-md-3 ${status.error ? 'has-error' : ''}">
				<label for="datafimmandato">Data Fim Mandato</label>			
					<form:input path="datafimmandato" type="text" class="form-control" id="datafimmandato" required="required"/>                                
					<form:errors path="datafimmandato" class="control-label" />			
			  </div>
			</spring:bind>
			
			
			<spring:bind path="ativo">
			  <div class="form-group col-md-3 ${status.error ? 'has-error' : ''}">
				<label for="ativo">Ativo</label>			
					<form:input path="ativo" type="text" class="form-control" id="ativo" required="required"/>                                
					<form:errors path="ativo" class="control-label" />			
			  </div>
			</spring:bind>
		
		</div>
		
		<spring:bind path="caminhoarquivo">
		  <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
				<label for="caminhoarquivo">Arquivo de Assinatura</label>			
				<form:input path="caminhoarquivo" type="text" class="form-control mudacursor" id="caminhoarquivo" 
				placeholder="Clique aqui para selecionar arquivo" onclick="location.href='${selectarquivoUrl}'" readonly="true" />
				<form:errors path="caminhoarquivo" class="control-label" />		
							  
		  </div>
		</spring:bind>
		
		<div  class="form-row justify-content-center" >				
			<div class="form-group col-xs-6 text-center">		
				<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-circle"></i> <span class="esconder"> Cadastrar</span></button>				
			</div>	
			
			<div class="form-group col-xs-6 text-center">		
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/secretarios"><i class="fas fa-arrow-circle-left"></i> <span class="esconder"> Cancelar</span></a>				
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
