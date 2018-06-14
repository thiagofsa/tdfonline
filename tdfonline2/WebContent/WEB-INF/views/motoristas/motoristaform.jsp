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
    
   <link href="../../resources/css/bootstrap.css"  rel="stylesheet" >
    <link href="../../resources/css/fontawesome-all.min.css"  rel="stylesheet" > 
   
  </head> 
 
  <body>

<div class="container">

	
	<spring:url value="/motoristas" var="motoristaActionUrl" />

	<form:form class="form-horizontal" method="post" 
                modelAttribute="motoristaForm" action="${motoristaActionUrl}">

		<form:hidden path="id" />

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
				<form:input path="email" class="form-control" 
                                id="email" placeholder="Email" />
				<form:errors path="email" class="control-label" />
			</div>
		  </div>
		</spring:bind>

		
		
		<spring:bind path="endereco">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Address</label>
			<div class="col-sm-10">
				<form:textarea path="endereco" rows="5" class="form-control" 
                                id="endereco" placeholder="endereco" />
				<form:errors path="endereco" class="control-label" />
			</div>
		  </div>
		</spring:bind>

		
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			
			<c:choose>
			  <c:when test="${motoristaForm['new']}">
			     <button type="submit" class="btn-lg btn-primary pull-right">Cadastrar</button>                             
			  </c:when>
			  
			  <c:otherwise>
			     <button type="submit" class="btn-lg btn-primary pull-right">Atualizar</button>                     
			  </c:otherwise>
			  
			</c:choose>
			
		  </div>
		  
		</div>
		
	</form:form>

</div>

	<script src="../../resources/js/jquery.min.js"  type="text/javascript"></script>
	<script src="../../resources/js/popper.min.js"  type="text/javascript"></script>
	<script src="../../resources/js/bootstrap.js"  type="text/javascript"></script>
	</body>
	</html>