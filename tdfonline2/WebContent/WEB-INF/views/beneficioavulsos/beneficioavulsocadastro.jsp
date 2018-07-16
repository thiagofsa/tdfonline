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
		<h4>Informações da nova Beneficio</h4>
	</div>
	
	<spring:url value="/selectpaciente/beneficioavulsos/" var="selectpacienteUrl" />
	<spring:url value="/selectunidadesaude/beneficioavulsos/" var="selectunidadesaudeUrl" />
	<spring:url value="/selectprocedimento/beneficioavulsos/" var="selectprocedimentoUrl" />
	<spring:url value="/selectacompanhante/beneficioavulsos/" var="selectacompanhanteUrl" />
	
	<spring:url value="/beneficioavulsos" var="beneficioavulsoActionUrl" />

	<form:form method="post" modelAttribute="beneficioavulsoForm" action="${beneficioavulsoActionUrl}">

		<form:hidden path="id" />
	
	<div  class="form-row" >
		
		<spring:bind path="paciente.nome">
		  <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
				<label for="paciente.nome">Nome do Paciente</label>			
				<form:input path="paciente.nome" type="text" class="form-control mudacursor" id="paciente.nome" 
				placeholder="Clique aqui para selecionar paciente" onclick="location.href='${selectpacienteUrl}'" readonly="true" />
				<form:errors path="paciente.nome" class="control-label" />		
				<form:hidden path="paciente.id" />			  
		  </div>
		</spring:bind>
		
		<spring:bind path="unidadesaude.descricao">
		  <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
				<label for="unidadesaude.descricao">Unidade Saude</label>
				<form:input path="unidadesaude.descricao" type="text" class="form-control mudacursor" id="unidadesaude.descricao" 
				placeholder="Clique aqui para selecionar a Unidade de Saúde" onclick="location.href='${selectunidadesaudeUrl}'" readonly="true" />
				<form:errors path="unidadesaude.descricao" class="control-label" />
				<form:hidden path="unidadesaude.id" />
		  </div>		 
		</spring:bind>
		
	</div>
		
	<div  class="form-row" >			
				
		<spring:bind path="procedimento.nome">
		  <div class="form-group col-md-8 ${status.error ? 'has-error' : ''}">
				<label for="procedimento.nome">Procedimento</label>
				<form:input path="procedimento.nome" type="text" class="form-control mudacursor" id="procedimento.nome" 
				placeholder="Clique aqui para selecionar o Procedimento" onclick="location.href='${selectprocedimentoUrl}'" readonly="true" />
				<form:errors path="procedimento.nome" class="control-label" />
				<form:hidden path="procedimento.id" />
		  </div>
		</spring:bind>		
		
		
		
		<spring:bind path="dataviagemida">
		  <div class="form-group col-md-2 ${status.error ? 'has-error' : ''}">			 
        		<label  for="dataviagemida" >Data Viagem Ida</label>        		
            	<form:input path="dataviagemida" type="text" class="form-control" id="dataviagem" placeholder="" />
            	<form:errors path="dataviagemida" class="control-label" />	   	
		  </div>
		</spring:bind>
		
		<spring:bind path="dataviagemvolta">
		  <div class="form-group col-md-2 ${status.error ? 'has-error' : ''}">			 
        		<label  for="dataviagemvolta" >Data Viagem Volta</label>        		
            	<form:input path="dataviagemvolta" type="text" class="form-control" id="dataviagem" placeholder="" />
            	<form:errors path="dataviagemvolta" class="control-label" />	   	
		  </div>
		</spring:bind>
		
		</div>
		
	
	<div  class="form-row" >
	
		<spring:bind path="observacao">
		  <div class="form-group col-md-7 ${status.error ? 'has-error' : ''}">
			<label for="observacao">Observação</label>			
				<form:input path="observacao" type="text" class="form-control" id="observacao" placeholder="" />
				<form:errors path="observacao" class="control-label" />			
		  </div>
		</spring:bind>
	
		
		<spring:bind path="vagas">
		  <div class="form-group col-md-2 ${status.error ? 'has-error' : ''}">			 
        		<label  for="vagas" >Vagas</label>        		
            	<form:input path="vagas" type="text" class="form-control mudacursor" id="vagas" placeholder="" readonly="true" />
            	<form:errors path="vagas" class="control-label" />	   	
		  </div>
		</spring:bind>
		
		<spring:bind path="valor">
		  <div class="form-group col-md-2 ${status.error ? 'has-error' : ''}">			 
        		<label  for="valor" >Valor</label>        		
            	<form:input path="valor" type="text" class="form-control mudacursor" id="valor" placeholder=""  />
            	<form:errors path="valor" class="control-label" />	   	
		  </div>
		</spring:bind>			
	</div>
		

	<p  class="mudacursor" onclick="location.href='${selectacompanhanteUrl}'" >Selecione os Acompanhantes:</p>

		<c:if test="${not empty acompanhantespaciente}">
		
			<div class="table-responsive-md" >
				<table class="table table-striped table-hover">
		  			<thead>
					  <tr>
					   <td>Nome do Acompanhante</td>
					   <td>Telefone</td>
					  </tr>
					</thead>
		   
		  			<c:forEach items="${acompanhantespaciente}" var="acompanhante" varStatus="status">
		  			  <tr>      
		        		<td><c:out value="${acompanhante.nome}"/> </td>
		        		<td><c:out value="${acompanhante.telefone}"/> </td>
				      </tr>
				    </c:forEach>
				</table> 
			</div>
		</c:if>
	
		<div  class="spaceabaixo"></div>		
		
		<div  class="form-row justify-content-center" >				
			<div class="form-group col-xs-6 text-center">		
				<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-circle"></i> <span class="esconder"> Cadastrar</span></button>				
			</div>	
			
			<div class="form-group col-xs-6 text-center">		
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/beneficioavulsos/"><i class="fas fa-arrow-circle-left"></i> <span class="esconder"> Cancelar</span></a>				
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
