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
			<h4>Pesquise o Paciente</h4>
		</div>
		
<spring:url value="/beneficioavulsos/selectpaciente2" var="selectUrl" />

	<form:form  class="form-row align-items-center"  modelAttribute="paciente" action="${selectUrl}">	
		
		<div  class="col-md-1"></div>
		<spring:bind path="nome">
			  <div class="col-md-8 ${status.error ? 'has-error' : ''}">		
					<form:input path="nome" type="text" class="form-control" id="nome" required="required"/>                                
					<form:errors path="nome" class="control-label" />			
			  </div>
			</spring:bind>
				
		<div class="3">				
			<button type="submit" class="btn btn-outline-secondary"><i class="fas fa-search"></i> <span class="esconder"> Pesquisar</span></button>				
		</div>
	
	</form:form>
	
	
	
	
	<c:if test="${not empty pacientes}">

		<h1>Pacientes</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Nome</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="paciente" items="${pacientes}">
			    <tr>
				<td>
					${paciente.id}
				</td>
				<td>${paciente.nome}</td>
				
				<td>
				  <spring:url value="/beneficioavulsos/selectpaciente/${paciente.id}" var="beneficioavulsoUrl" />
				  

				  <button class="btn btn-info" 
                                          onclick="location.href='${beneficioavulsoUrl}'">Selecionar</button>
				  
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