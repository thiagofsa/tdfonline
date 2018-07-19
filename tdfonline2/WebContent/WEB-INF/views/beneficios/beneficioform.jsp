<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">


<div class="container">

	<c:choose>
		<c:when test="${beneficioForm['new']}">
			<h1>Cadastrar Beneficio</h1>
		</c:when>
		<c:otherwise>
			<h1>Atualizar Beneficio</h1>
		</c:otherwise>
	</c:choose>
	<br />
	
	<spring:url value="/selectencaminhamento/beneficios/" var="selectencaminhamentoUrl" />
					  <button class="btn btn-info" 
                                          onclick="location.href='${selectencaminhamentoUrl}'">Selecionar Encaminhamento</button>
                                          

	<spring:url value="/beneficios" var="beneficioActionUrl" />

	<form:form class="form-horizontal" method="post" 
                modelAttribute="beneficioForm" action="${beneficioActionUrl}">

		<form:hidden path="id" />

		<spring:bind path="paciente.nome">
		  <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
				<label for="paciente.nome">Nome do Paciente</label>			
				<form:input path="paciente.nome" type="text" class="form-control mudacursor" id="paciente.nome" 
				placeholder="Clique para selecionar paciente" onclick="location.href='${selectpacienteUrl}'" readonly="true" />
				<form:errors path="paciente.nome" class="control-label" />		
				<form:hidden path="paciente.id" />			  
		  </div>
		</spring:bind>
		
		<spring:bind path="unidadesaude.descricao">
		  <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
				<label for="unidadesaude.descricao">Unidade Saude</label>
				<form:input path="unidadesaude.descricao" type="text" class="form-control mudacursor" id="unidadesaude.descricao" 
				placeholder="Clique para selecionar a Unidade de Saúde" onclick="location.href='${selectunidadesaudeUrl}'" readonly="true" />
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
				placeholder="Clique para selecionar o Procedimento" onclick="location.href='${selectprocedimentoUrl}'" readonly="true" />
				<form:errors path="procedimento.nome" class="control-label" />
				<form:hidden path="procedimento.id" />
		  </div>
		</spring:bind>		
		
		
		
		
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Data viagem ida</label>
			<div class="col-sm-10">
				<form:input path="dataviagemida" type="text" class="form-control" 
                                name="dataviagemida" id="dataviagemida" placeholder="Data viagem Ida" />
				
			</div>
		  </div>
		
		
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Data viagem Volta</label>
			<div class="col-sm-10">
				<form:input path="dataviagemvolta" type="text" class="form-control" 
                                name="dataviagemvolta" id="dataviagemvolta" placeholder="Data viagem Volta" />
				
			</div>
		  </div>
		  
		  
		    <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Valor</label>
			<div class="col-sm-10">
				<form:input path="valor" type="text" class="form-control" 
                                name="valor" id="valor" placeholder="Valor" />
				
			</div>
		  </div>
		
				
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			<c:choose>
			  <c:when test="${beneficioForm['new']}">
			     <button type="submit" class="btn-lg btn-primary pull-right">Cadastrar
                             </button>
			  </c:when>
			  <c:otherwise>
			     <button type="submit" class="btn-lg btn-primary pull-right">Atualizar
                             </button>
			  </c:otherwise>
			</c:choose>
		  </div>
		</div>
		
		
		<c:if test="${not empty acompanhantespaciente}">
		
			<div class="table-responsive-md" >
				<table class="table table-striped table-hover">
		  			<thead>
					  <tr>
					   <td>Nome do Acompanhante</td>
					   
					  </tr>
					</thead>
		   
		  			<c:forEach items="${acompanhantespaciente}" var="acompanhante" varStatus="status">
		  			  <tr>      
		        		<td><c:out value="${acompanhante.nome}"/> </td>
		        		
				      </tr>
				    </c:forEach>
				</table> 
			</div>
		</c:if>
		
		
		
	</form:form>

</div>


</body>
</html>
