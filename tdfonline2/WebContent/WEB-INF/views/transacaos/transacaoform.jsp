




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

	<c:choose>
		<c:when test="${transacaoForm['new']}">
			<h1>Cadastrar Usuario</h1>
		</c:when>
		<c:otherwise>
			<h1>Atualizar Usuario</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/transacaos" var="transacaoActionUrl" />


	<h1> Habilitar Log de Transação </h1>
	
	Entidades:<Br>
	//USUARIO =1 
	//MARCACAO = 2
	//ENCAMINHAMENTO = 3
	//DISTRIBUICAO = 4
	//BENEFICIO =5
	<br>

	Operaçao <br>
	//ADD =1
	//UPDATE = 2
	//DELETE =3
	
	
	<form:form class="form-horizontal" method="post" 
                modelAttribute="transacaoForm" action="${transacaoActionUrl}">

		<form:hidden path="id" />

		
		<spring:bind path="entidade">
		  <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
				<label for="entidade">Entidade</label>			
				<form:input path="entidade" type="text" class="form-control mudacursor" id="entidade" 
				 readonly="true" />
				<form:errors path="entidade" class="control-label" />		
						  
		  </div>
		</spring:bind>
		
		<spring:bind path="operacao">
		  <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
				<label for="operacao">Operacao</label>
				<form:input path="operacao" type="text" class="form-control mudacursor" id="operacao" 
				 readonly="true" />
				<form:errors path="operacao" class="control-label" />
				
		  </div>		 
		</spring:bind>
		
		<spring:bind path="valor">
		  <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
				<label for="valor">Ativar (0-Não;1-Sim)</label>
				<form:input path="valor" type="text" class="form-control mudacursor" id="valor" 
				 />
				<form:errors path="valor" class="control-label" />
				
		  </div>		
		   
		</spring:bind>
			   			    
		<div class="form-group col-xs-4 text-center">		
				<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-circle"></i> <span class="esconder"> Atualizar</span></button>				
			</div>		

	</form:form>

<div  class="spaceabaixo"></div>
</div>
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>	
</body>
</html>