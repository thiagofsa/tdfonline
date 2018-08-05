
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
<Br><Br><Br><Br><Br><Br><Br><Br>
<div class="container">

	
			<h1>Minha conta</h1>
	
	<br />
	
	
	<div  class="row areanotify justify-content-end py-1">		
		<c:if test="${not empty msg}">			    
			<strong id="textonotify" class="animated fadeout font-italic" ><i class="fas fa-check-circle fa-lg text-success mr-1"></i>${msg}</strong>
		</c:if>		
	</div>
	
	

	<spring:url value="/usuarios/minhaconta2" var="usuarioActionUrl" />

		<form:form class="form-horizontal" method="post" 
                modelAttribute="usuarioForm" action="${usuarioActionUrl}">

		<form:hidden path="id" />
		
		<form:hidden path="login" />
		
		<form:hidden path="admin" />
		
		<form:hidden path="transporte" />
		
				
		<spring:bind path="nome">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Nome</label>
			<div class="col-sm-10">
				<form:input path="nome" type="text" class="form-control" 
                                id="nome" placeholder="Nome" />
				<form:errors path="nome" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<spring:bind path="email">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Email</label>
			<div class="col-sm-10">
				<form:input path="email" type="text" class="form-control" 
                                id="email" placeholder="Email" />
				<form:errors path="email" class="control-label" />
			</div>
		  </div>
		</spring:bind>

	<spring:bind path="senha">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Senha</label>
			<div class="col-sm-10">
				<form:password path="senha" class="form-control" 
                                id="senha" placeholder="senha" />
				<form:errors path="senha" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<spring:bind path="senhanova1">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Repita a Senha</label>
			<div class="col-sm-10">
				<form:password path="senhanova1" class="form-control" 
                                id="senhanova1" placeholder="senhanova1" />
				<form:errors path="senhanova1" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<spring:bind path="senhanova2">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Repita a Senha</label>
			<div class="col-sm-10">
				<form:password path="senhanova2" class="form-control" 
                                id="senhanova2" placeholder="senhanova2" />
				<form:errors path="senhanova2" class="control-label" />
			</div>
		  </div>
		</spring:bind>

		
	<spring:bind path="telefone">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Telefone</label>
			<div class="col-sm-10">
				<form:input path="telefone" class="form-control" 
                                id="telefone" placeholder="Telefone" />
				<form:errors path="telefone" class="control-label" />
			</div>
		  </div>
		</spring:bind>

	
			     <button type="submit" class="btn-lg btn-primary pull-right">Atualizar
                             </button>
	
	</form:form>

<div  class="spaceabaixo"></div>
</div>
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>	
</body>
</html>