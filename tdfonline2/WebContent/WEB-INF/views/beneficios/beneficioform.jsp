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

		<spring:bind path="encaminhamento.marcacao.paciente.nome">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Paciente Nome</label>
			<div class="col-sm-10">
				<form:input path="encaminhamento.marcacao.paciente.nome" type="text" class="form-control" 
                                id="encaminhamento.marcacao.paciente.nome" placeholder="Nome Paciente" />
				<form:errors path="encaminhamento.marcacao.paciente.nome" class="control-label" />
			</div>
		  </div>
		</spring:bind>


		
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Data viagem ida</label>
			<div class="col-sm-10">
				<form:input path="encaminhamento.dataviagem" type="text" class="form-control" 
                                name="dataviagem" id="dataviagem" placeholder="Data viagem" />
				
			</div>
		  </div>
		
		
		
		
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Data viagem Volta</label>
			<div class="col-sm-10">
				<form:input path="encaminhamento.dataviagemvolta" type="text" class="form-control" 
                                name="dataviagemvolta" id="dataviagemvolta" placeholder="Data viagem Volta" />
				
			</div>
		  </div>
		
		
		<spring:bind path="valor">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Valor beneficio</label>
			<div class="col-sm-10">
				<form:input path="valor" type="text" class="form-control" 
                                id="valor" placeholder="Valor" />
				<form:errors path="valor" class="control-label" />
			</div>
		  </div>
		</spring:bind>

		<form:hidden path="encaminhamento.id" />
						
				
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
