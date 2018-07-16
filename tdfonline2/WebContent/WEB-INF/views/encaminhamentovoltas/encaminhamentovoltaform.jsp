<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link rel="stylesheet" href= "<c:url value='/resources/css/jquery.dataTables.css'/>" > 
    
  </head>  
<body>


<div class="container">

	<c:choose>
		<c:when test="${encaminhamentovoltaForm['new']}">
			<h1>Cadastrar Encaminhamento Volta</h1>
		</c:when>
		<c:otherwise>
			<h1>Atualizar Encaminhamento Volta</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/selectdistribuicao/encaminhamentovoltas/" var="selectdistribuicaoUrl" />
					  <button class="btn btn-info" 
                                          onclick="location.href='${selectdistribuicaoUrl}'">Selecionar Distribuicao</button>
                                          
	<spring:url value="/selectpaciente/encaminhamentovoltas/" var="selectpacienteUrl" />
					  <button class="btn btn-info" 
                                          onclick="location.href='${selectpacienteUrl}'">Selecionar Paciente</button>                                          
	
	
	<spring:url value="/encaminhamentovoltas" var="encaminhamentovoltaActionUrl" />

	<form:form class="form-horizontal" method="post" 
                modelAttribute="encaminhamentovoltaForm" action="${encaminhamentovoltaActionUrl}">

		<form:hidden path="id" />
		
	
		<spring:bind path="distribuicao.dataviagem">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Data da Distribuicao</label>
			<div class="col-sm-10">
				<form:input path="distribuicao.dataviagem" type="text" class="form-control" 
                                id="distribuicao.dataviagem" placeholder="Data" />
				<form:errors path="distribuicao.dataviagem" class="control-label" />
			</div>
		
		<form:hidden path="distribuicao.id" />	  
			  
		  </div>
		</spring:bind>
		
		<spring:bind path="distribuicao.vagas">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Distribuicao - Vagas</label>
			<div class="col-sm-10">
				<form:input path="distribuicao.vagas" type="text" class="form-control" 
                                id="distribuicao.vagas" placeholder="..." />
				<form:errors path="distribuicao.vagas" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
	
			
		<spring:bind path="paciente.nome">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Encaminhamento -Paciente</label>
			<div class="col-sm-10">
				<form:input path="paciente.nome" type="text" class="form-control" 
                                id="paciente.nome" placeholder="..." />
				<form:errors path="paciente.nome" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		<form:hidden path="paciente.id" />
				
		<spring:bind path="vagas">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Encaminhamento - Vagas solicitadas</label>
			<div class="col-sm-10">
				<form:input path="vagas" type="text" class="form-control" 
                                id="vagas" placeholder="..." />
				<form:errors path="vagas" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<spring:bind path="observacao">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Encaminhamento Volta - Observacao</label>
			<div class="col-sm-10">
				<form:input path="observacao" type="text" class="form-control" 
                                id="observacao" placeholder="..." />
				<form:errors path="observacao" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
						
		<div class="form-group">
		  
			<c:choose>
			  <c:when test="${encaminhamentovoltaForm['new']}">
			     <button type="submit" class="btn-lg btn-primary pull-right">Cadastrar
                             </button>
			  </c:when>
			  <c:otherwise>
			     <button type="submit" class="btn-lg btn-primary pull-right">Atualizar
                             </button>
			  </c:otherwise>
			</c:choose>
		  
		</div>
		
		
	</form:form>
	
<div  class="spaceabaixo"></div>	
</div>
	
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>	
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
  <script>
	  $(document).ready(
			  function() {
		    	$('#Tabela').DataTable();
		    	$('.telefone').mask("(00) 00000-0000");    	 
		    	$('.cpf').mask('000.000.000-00');		    	
		} );
  </script>   
</body>
</html>

