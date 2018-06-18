<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="container">

	<c:choose>
		<c:when test="${encaminhamentoForm['new']}">
			<h1>Cadastrar Encaminhamento</h1>
		</c:when>
		<c:otherwise>
			<h1>Atualizar Encaminhamento</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/selectdistribuicao/encaminhamentos/" var="selectdistribuicaoUrl" />
					  <button class="btn btn-info" 
                                          onclick="location.href='${selectdistribuicaoUrl}'">Selecionar Distribuicao</button>
	
	                                          
                                          
	<spring:url value="/selectmarcacao/encaminhamentos/" var="selectmarcacaoUrl" />
					  <button class="btn btn-info" 
                                          onclick="location.href='${selectmarcacaoUrl}'">Selecionar Marcacao</button>                                          
                                          
	
	<spring:url value="/encaminhamentos" var="encaminhamentoActionUrl" />

	<form:form class="form-horizontal" method="post" 
                modelAttribute="encaminhamentoForm" action="${encaminhamentoActionUrl}">

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
		
			
		<spring:bind path="marcacao.dataviagem">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Marcacao -Data Viagem</label>
			<div class="col-sm-10">
				<form:input path="marcacao.dataviagem" type="text" class="form-control" 
                                id="marcacao.dataviagem" placeholder="..." />
				<form:errors path="marcacao.dataviagem" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		
		<spring:bind path="marcacao.paciente.nome">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Marcacao -Paciente</label>
			<div class="col-sm-10">
				<form:input path="marcacao.paciente.nome" type="text" class="form-control" 
                                id="marcacao.paciente.nome" placeholder="..." />
				<form:errors path="marcacao.paciente.nome" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<spring:bind path="marcacao.procedimento.nome">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Marcacao -Procedimento</label>
			<div class="col-sm-10">
				<form:input path="marcacao.procedimento.nome" type="text" class="form-control" 
                                id="marcacao.procedimento.nome" placeholder="..." />
				<form:errors path="marcacao.procedimento.nome" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<spring:bind path="marcacao.unidadesaude.descricao">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Marcacao -US</label>
			<div class="col-sm-10">
				<form:input path="marcacao.unidadesaude.descricao" type="text" class="form-control" 
                                id="marcacao.unidadesaude.descricao" placeholder="..." />
				<form:errors path="marcacao.unidadesaude.descricao" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		
		<spring:bind path="marcacao.vagas">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Marcacao - Vagas</label>
			<div class="col-sm-10">
				<form:input path="marcacao.vagas" type="text" class="form-control" 
                                id="marcacao.vagas" placeholder="..." />
				<form:errors path="marcacao.vagas" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<form:hidden path="marcacao.id" />
		
		
		<spring:bind path="dataviagem">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Data da Viagem</label>
			<div class="col-sm-10">
				<form:input path="dataviagem" type="text" class="form-control" 
                                id="dataviagem" placeholder="ID Proc" />
				<form:errors path="dataviagem" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
						
		<div class="form-group">
		  
			<c:choose>
			  <c:when test="${encaminhamentoForm['new']}">
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
	
	<br><br><br><br><br><br>

</div>



