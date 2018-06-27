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
	
		<div  class="space" ></div>
		
		<div class="titulo"  >
			<h4>Dados do novo Motorista</h3>
		</div>
	

	<spring:url value="/motoristas" var="motoristaActionUrl" />

	<form:form method="post" modelAttribute="motoristaForm" action="${motoristaActionUrl}">

		<form:hidden path="id" />
		
		<div  class="form-row" >

		<spring:bind path="nome">
		  <div class="form-group col-md-9 ${status.error ? 'has-error' : ''}">
			<label for="nome">Nome</label>			
				<form:input path="nome" type="text" class="form-control" id="nome" required="required"/>                                
				<form:errors path="nome" class="control-label" />			
		  </div>
		</spring:bind>	
		
		
		<spring:bind path="cpf">
		  <div class="form-group col-md-3 ${status.error ? 'has-error' : ''}">
			<label for="cpf">Cpf</label>			
				<form:input path="cpf" type="text" class="form-control" id="cpf"  data-mask="000.000.000-00"  required="required"/>                                
				<form:errors path="cpf" class="control-label" />			
		  </div>
		</spring:bind>			
		
		</div>

		
		
		<div  class="form-row" >
		
		<spring:bind path="telefone">
		  <div class="form-group col-md-3${status.error ? 'has-error' : ''}">
			<label for="telefone">Telefone</label>			
				<form:input path="telefone" class="form-control" id="telefone" data-mask="(00) 00000-0000" required="required"/>                                
				<form:errors path="telefone" class="control-label" />
		   </div>
		</spring:bind>
		
		
		<spring:bind path="email">
		  <div class="form-group col-md-9${status.error ? 'has-error' : ''}">
			<label for="email" >Email</label>			
				<form:input path="email" class="form-control" id="email" required="required"/>                               
				<form:errors path="email" class="control-label" />			
		  </div>
		</spring:bind>
		
		</div>
		
		
		
		<div  class="form-row" >
		
		<spring:bind path="matricula">
		  <div class="form-group col-md-4${status.error ? 'has-error' : ''}">
			<label for="matricula">Matricula</label>			
				<form:input path="matricula" class="form-control" id="matricula" required="required"/>                                
				<form:errors path="matricula" class="control-label" />
		   </div>
		</spring:bind>
		
		
		<spring:bind path="categoriacnh">
		  <div class="form-group col-md-3${status.error ? 'has-error' : ''}">
			<label for="categoriacnh" >Categoria CNH</label>			
				<form:input path="categoriacnh" class="form-control" id="categoriacnh" required="required"/>                               
				<form:errors path="categoriacnh" class="control-label" />			
		  </div>
		</spring:bind>
		
		<spring:bind path="validadecnh">
		  <div class="form-group col-md-5${status.error ? 'has-error' : ''}">
			<label for="validadecnh" >Validade CNH</label>			
				<form:input path="validadecnh" class="form-control"  type="date" id="validadecnh" required="required"/>                               
				<form:errors path="validadecnh" class="control-label" />			
		  </div>
		</spring:bind>
		
		</div>
		
		
		<div  class="form-row" >				
			<div class="form-group col-sm-6 text-center">		
				<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-circle"></i>  Cadastrar</button>				
			</div>	
			
			<div class="form-group col-sm-6 text-center">		
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/motoristas/"><i class="fas fa-arrow-circle-left"></i> Cancelar</a>				
			</div>
		</div>  
		  
	</div>
		
	</form:form>

	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
	
	</body>
</html>