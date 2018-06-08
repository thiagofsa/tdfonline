<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>


<div class="container">

	<c:choose>
		<c:when test="${distribuicaoForm['new']}">
			<h1>Cadastrar Distribuicao</h1>
		</c:when>
		<c:otherwise>
			<h1>Atualizar Distribuicao</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/selectmotorista/distribuicaos/" var="selectmotoristaUrl" />
					  <button class="btn btn-info" 
                                          onclick="location.href='${selectmotoristaUrl}'">Selecionar Motorista</button>
	
	<spring:url value="/selectveiculo/distribuicaos/" var="selectveiculoUrl" />
					  <button class="btn btn-info" 
                                          onclick="location.href='${selectveiculoUrl}'">Selecionar Veiculo</button>                                          
                                          
	<spring:url value="/selectpauta/distribuicaos/" var="selectpautaUrl" />
					  <button class="btn btn-info" 
                                          onclick="location.href='${selectpautaUrl}'">Selecionar Pauta</button>                                          

	<spring:url value="/distribuicaos" var="distribuicaoActionUrl" />

	<form:form class="form-horizontal" method="post" 
                modelAttribute="distribuicaoForm" action="${distribuicaoActionUrl}">

		<form:hidden path="id" />
		
	
		<spring:bind path="motorista.nome">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Nome do Motorista</label>
			<div class="col-sm-10">
				<form:input path="motorista.nome" type="text" class="form-control" 
                                id="motorista.nome" placeholder="Nome" />
				<form:errors path="motorista.nome" class="control-label" />
			</div>
		
		<form:hidden path="motorista.id" />	  
			  
		  </div>
		</spring:bind>
		
		<spring:bind path="veiculo.descricao">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Veiculo</label>
			<div class="col-sm-10">
				<form:input path="veiculo.descricao" type="text" class="form-control" 
                                id="veiculo.descricao" placeholder="ID US" />
				<form:errors path="veiculo.descricao" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<form:hidden path="veiculo.id" />
		
				
		<spring:bind path="pauta.descricao">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">	</label>
			<div class="col-sm-10">
				<form:input path="pauta.descricao" type="text" class="form-control" 
                                id="pauta.descricao" placeholder="ID Proc" />
				<form:errors path="pauta.descricao" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<form:hidden path="pauta.id" />
		
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
			  <c:when test="${distribuicaoForm['new']}">
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
