<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>


<div class="container">

	<c:choose>
		<c:when test="${marcacaoForm['new']}">
			<h1>Cadastrar Marcacao</h1>
		</c:when>
		<c:otherwise>
			<h1>Atualizar Marcacao</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/selectpaciente/marcacaos/" var="selectpacienteUrl" />
					  <button class="btn btn-info" 
                                          onclick="location.href='${selectpacienteUrl}'">Selecionar Paciente</button>
	
	<spring:url value="/selectunidadesaude/marcacaos/" var="selectunidadesaudeUrl" />
					  <button class="btn btn-info" 
                                          onclick="location.href='${selectunidadesaudeUrl}'">Selecionar Unidade Saude</button>                                          
                                          
	<spring:url value="/selectprocedimento/marcacaos/" var="selectprocedimentoUrl" />
					  <button class="btn btn-info" 
                                          onclick="location.href='${selectprocedimentoUrl}'">Selecionar Procedimento</button>                                          

	<spring:url value="/marcacaos" var="marcacaoActionUrl" />

	<form:form class="form-horizontal" method="post" 
                modelAttribute="marcacaoForm" action="${marcacaoActionUrl}">

		<form:hidden path="id" />
		
	
		<spring:bind path="paciente.nome">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Nome do Paciente</label>
			<div class="col-sm-10">
				<form:input path="paciente.nome" type="text" class="form-control" 
                                id="paciente.nome" placeholder="Nome" />
				<form:errors path="paciente.nome" class="control-label" />
			</div>
		
		<form:hidden path="paciente.id" />	  
			  
		  </div>
		</spring:bind>
		
		<spring:bind path="unidadesaude.descricao">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Unidade Saude</label>
			<div class="col-sm-10">
				<form:input path="unidadesaude.descricao" type="text" class="form-control" 
                                id="unidadesaude.descricao" placeholder="ID US" />
				<form:errors path="unidadesaude.descricao" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<form:hidden path="unidadesaude.id" />
		
				
		<spring:bind path="procedimento.nome">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Procedimento</label>
			<div class="col-sm-10">
				<form:input path="procedimento.nome" type="text" class="form-control" 
                                id="procedimento.nome" placeholder="ID Proc" />
				<form:errors path="procedimento.nome" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<form:hidden path="procedimento.id" />
		
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

				
				
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			<c:choose>
			  <c:when test="${marcacaoForm['new']}">
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
