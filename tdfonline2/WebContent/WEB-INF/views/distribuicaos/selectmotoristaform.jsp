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
    <link rel="stylesheet" href= "<c:url value='/resources/css/animate.min.css'/>" >     
  </head>  
<body>

<div class="container">

		<div  class="spacesup"></div>		
		<div class="text-center text-uppercase">
			<h4>Selecione o Motorista</h4>
		</div>
		
			<!-- <spring:url value="/distribuicaos/selectmotorista2" var="selectUrl" />
			<form:form class="form-row my-5" modelAttribute="motorista" action="${selectUrl}">
				
				<div  class="form-group col-sm-1"></div>
					<spring:bind path="nome">
						  <div class="form-group col-sm-8  ${status.error ? 'has-error' : ''}">		
								<form:input path="nome" type="text" class="form-control" id="nome" required="required"/>                                
								<form:errors path="nome" class="control-label" />								
						  </div>
					</spring:bind>	
								
					<div class="form-group col-sm-3 d-flex justify-content-center justify-content-md-start">
						<button type="submit" class="btn btn-outline-secondary"><i class="fas fa-search"></i> <span class="esconder"> Pesquisar</span></button>
					</div>	
			</form:form> -->	
	
	<c:if test="${not empty motoristas}">

		<div class="table-responsive-md mt-4" >		
		<table class="table table-sm table-striped table-hover display" >
			<thead>
				<tr>
					<th class="text-center">Nome</th>
					<th class="text-center">Cpf</th>
					<th class="text-center">Selecionar</th>
				</tr>
			</thead>

			<c:forEach var="motorista" items="${motoristas}">
			    <tr>				
				<td>${motorista.nome}</td>
				<td class="text-center">${motorista.cpf}</td>				
				<td class="text-center">
				  <spring:url value="/distribuicaos/selectmotorista/${motorista.id}" var="distribuicaoUrl" />
				  <button class="btn btn-sm" data-toggle="tooltip" data-placement="botton" title="Selecione este Motorista" onclick="location.href='${distribuicaoUrl}'"><i class="fas fa-check-circle"></i></button>			  
                </td>
			    </tr>
			</c:forEach>
		</table>
		</div>
		</c:if>
		
<div  class="spaceabaixo"></div>	
</div>	
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
  <script>
	  $(document).ready(function() {
		   
		} );
  </script>   
</body>
</html>