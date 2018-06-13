<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>


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
		
	
		<spring:bind path="distribuicao.data">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Data da Distribuicao</label>
			<div class="col-sm-10">
				<form:input path="distribuicao.data" type="text" class="form-control" 
                                id="distribuicao.data" placeholder="Data" />
				<form:errors path="distribuicao.data" class="control-label" />
			</div>
		
		<form:hidden path="distribuicao.id" />	  
			  
		  </div>
		</spring:bind>
		
			
		<spring:bind path="marcacao.data">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Marcacao</label>
			<div class="col-sm-10">
				<form:input path="marcacao.data" type="text" class="form-control" 
                                id="marcacao.data" placeholder="ID Proc" />
				<form:errors path="marcacao.data" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<form:hidden path="marcacao.id" />
		
			
				
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
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
		</div>
	</form:form>

</div>



