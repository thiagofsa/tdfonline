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
		<h4>Novo Procedimento</h3>
	</div>

	<spring:url value="/procedimentos" var="procedimentoActionUrl" />

	<form:form method="post" modelAttribute="procedimentoForm" action="${procedimentoActionUrl}">

		<form:hidden path="id" />

		<div  class="form-row" >
		
		<spring:bind path="area">
		  <div class="form-group col-md-4 ${status.error ? 'has-error' : ''}">
			<label for="area">Especialidade</label>				    			
    			<form:select path="area" class="form-control"  id="area" required="required" >
    				<form:option value="" label="Escolha uma especialidade"/>
    				<form:option value="Ortopedia" label="Ortopedia"/>
    				<form:option value="Cardiologia" label="Cardiologia"/>
    				<form:option value="Neurologia" label="Neurologia"/>
    				<form:option value="Renal" label="Renal"/>
    				<form:option value="Pulmonar" label="Pulmonar"/>
    				<form:option value="Oftalmologico" label="Oftalmologico"/>        				
				</form:select>    				                               
				<form:errors path="area" class="control-label" />			
		  </div>
		</spring:bind>

		<spring:bind path="nome">
		  <div class="form-group col-md-8 ${status.error ? 'has-error' : ''}">
			<label for="nome">Descrição</label>			
				<form:input path="nome" type="text" class="form-control" id="nome" required="required"/>                                
				<form:errors path="nome" class="control-label" />			
		  </div>
		</spring:bind>	
		
		</div>


		<div  class="form-row justify-content-center" >				
			<div class="form-group col-xs-6 text-center">		
				<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-circle"></i> <span class="esconder"> Cadastrar</span></button>				
			</div>	
			
			<div class="form-group col-xs-6 text-center">		
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/procedimentos/"><i class="fas fa-arrow-circle-left"></i> Cancelar</a>				
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