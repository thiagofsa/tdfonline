<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


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

		<c:if test="${not empty msg}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" 
                                aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		    </div>
		</c:if>
		
		<div  class="spacesup"></div>	
		<div class="titulo"  >
			<h4>Pesquise a Unidade deSaúde</h4>
		</div>
		
<spring:url value="/marcacaos/selectunidadesaude2" var="selectunidadesaudeUrl" />

	
	
	<form:form  class="form-row align-items-center"  modelAttribute="unidadesaude" action="${selectunidadesaudeUrl}">
		
		<div  class="col-md-1"></div>
		<spring:bind path="descricao">
			  <div class="col-md-8 ${status.error ? 'has-error' : ''}">		
					<form:input path="descricao" type="text" class="form-control" id="descricao" required="required"/>                                
					<form:errors path="descricao" class="control-label" />			
			  </div>
		</spring:bind>
				
		<div class="3">				
			<button type="submit" class="btn btn-outline-secondary"><i class="fas fa-search"></i> <span class="esconder"> Pesquisar</span></button>				
		</div>
	
	</form:form>
	
	
	<c:if test="${not empty unidadesaudes}">

		<h1>Unidade Saudes</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Cidade</th>
					<th>Descricao</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="unidadesaude" items="${unidadesaudes}">
			    <tr>
				<td>
					${unidadesaude.id}
				</td>
				<td>${unidadesaude.cidade.descricao}</td>
				<td>${unidadesaude.descricao}</td>
				
				<td>
				  <spring:url value="/marcacaos/selectunidadesaude/${unidadesaude.id}" var="selectunidadesaudeUrl" />
				  	  <button class="btn btn-info" 
                                          onclick="location.href='${selectunidadesaudeUrl}'">Selecionar</button>
				  
                </td>
			    </tr>
			</c:forEach>
		</table>
		</c:if>
	
	
<div  class="spaceabaixo"></div>	
</div>
	
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>	
</body>
</html>