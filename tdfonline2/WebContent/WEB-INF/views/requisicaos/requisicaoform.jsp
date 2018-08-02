<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>


<div class="container">

	<c:choose>
		<c:when test="${requisicaoForm['new']}">
			<h1>Cadastrar Requisicao</h1>
		</c:when>
		<c:otherwise>
			<h1>Atualizar Requisicao</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/selectmedico/requisicaos/" var="selectmedicoUrl" />
					  <button class="btn btn-info" 
                                          onclick="location.href='${selectmedicoUrl}'">Selecionar Medico</button>
	
	<spring:url value="/selectpaciente/requisicaos/" var="selectpacienteUrl" />
					  <button class="btn btn-info" 
                                          onclick="location.href='${selectpacienteUrl}'">Selecionar Paciente</button>                                          
                                          
<spring:url value="/selectarquivo/requisicaos/" var="selectarquivoUrl" />
					  <button class="btn btn-info" 
                                          onclick="location.href='${selectarquivoUrl}'">Selecionar Arquivo</button>                                          
                                          
	                                          

	<spring:url value="/requisicaos" var="requisicaoActionUrl" />

	<form:form class="form-horizontal" method="post" 
                modelAttribute="requisicaoForm" action="${requisicaoActionUrl}">

		<form:hidden path="id" />
		
	
		<spring:bind path="medico.nome">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Nome do Medico</label>
			<div class="col-sm-10">
				<form:input path="medico.nome" type="text" class="form-control" 
                                id="medico.nome" placeholder="Nome" />
				<form:errors path="medico.nome" class="control-label" />
			</div>
		
		<form:hidden path="medico.id" />	  
			  
		  </div>
		</spring:bind>
		
		<spring:bind path="paciente.nome">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Nome do Paciente</label>
			<div class="col-sm-10">
				<form:input path="paciente.nome" type="text" class="form-control" 
                                id="paciente.nome" placeholder="ID US" />
				<form:errors path="paciente.nome" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<form:hidden path="paciente.id" />
		
				
		
		
		<spring:bind path="data">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			
			 <div class="control-group">
        		<form:label cssClass="control-label" path="data">Data:</form:label>
        		<div class="controls">
            	<form:input path="data" class="date" />
        		</div>
    		</div>
		  </div>
		</spring:bind>


	<spring:bind path="caminhoarquivo">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			
			 <div class="control-group">
        		<form:label cssClass="control-label" path="caminhoarquivo">Arquivo:</form:label>
        		<div class="controls">
            	<form:input path="caminhoarquivo" class="date" disabled="true"/>
        		</div>
    		</div>
		  </div>
		</spring:bind>
				
				
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			<c:choose>
			  <c:when test="${requisicaoForm['new']}">
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
	</form:form>

</div>


</body>
</html>
