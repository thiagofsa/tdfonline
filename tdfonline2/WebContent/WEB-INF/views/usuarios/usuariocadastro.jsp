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
			<h4>Dados do novo Usuário</h3>
		</div>

	<spring:url value="/usuarios" var="usuarioActionUrl" />

	<form:form method="post"  modelAttribute="usuarioForm" action="${usuarioActionUrl}">

		<form:hidden path="id" />
		
		<div  class="form-row" >
		
			<spring:bind path="nome">
			  <div class="form-group col-md-8 ${status.error ? 'has-error' : ''}">
				<label for="nome">Nome</label>			
					<form:input path="nome" type="text" class="form-control" id="nome" required="required"/>                                
					<form:errors path="nome" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="telefone">
			  <div class="form-group col-md-4 ${status.error ? 'has-error' : ''}">
				<label for="telefone">Telefone</label>			
					<form:input path="telefone" type="text" class="form-control" id="telefone" required="required"/>                                
					<form:errors path="telefone" class="control-label" />			
			  </div>
			</spring:bind>
		
		</div>
		
		<div  class="form-row" >
		
			<spring:bind path="email">
			  <div class="form-group col-md-8 ${status.error ? 'has-error' : ''}">
				<label for="email">Email</label>			
					<form:input path="email" type="text" class="form-control" id="email" required="required"/>                                
					<form:errors path="email" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="admin">
			  <div class="form-group col-md-4 ${status.error ? 'has-error' : ''}">
				<label for="perfil">Perfil</label>				    			
	    			<form:select path="admin" class="form-control"  id="perfil" required="required" >
	    				<form:option value="" label="Escolha o perfil de usuário"/>
	    				<form:option value="1" label="Administrador"/>
	    				<form:option value="0" label="Operador"/>   				
					</form:select>    				                               
					<form:errors path="admin" class="control-label" />			
			  </div>
			</spring:bind>
		
		</div>
		
		<div  class="form-row" >
		
			<spring:bind path="login">
			  <div class="form-group col-md-4 ${status.error ? 'has-error' : ''}">
				<label for="login">Login</label>			
					<form:input path="login" type="text" class="form-control" id="login" required="required"/>                                
					<form:errors path="login" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="senha">
			  <div class="form-group col-md-4 ${status.error ? 'has-error' : ''}">
				<label for="senha">Senha</label>			
					<form:input path="senha" type="password" class="form-control" id="senha" required="required"/>                                
					<form:errors path="senha" class="control-label" />			
			  </div>
			</spring:bind>
			
			  <div class="form-group col-md-4">
				<label for="repeat">Repita a senha</label>			
					<input type="password" class="form-control" id="repeat" required="required" />                                	
			  </div>		
		</div>
		
		<div  class="form-row justify-content-center" >				
			<div class="form-group col-xs-6 text-center">		
				<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-circle"></i> <span class="esconder"> Cadastrar</span></button>				
			</div>	
			
			<div class="form-group col-xs-6 text-center">		
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/usuarios"><i class="fas fa-arrow-circle-left"></i> <span class="esconder"> Cancelar</span></a>				
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