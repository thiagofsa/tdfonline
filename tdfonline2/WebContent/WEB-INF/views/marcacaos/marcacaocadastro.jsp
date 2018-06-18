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
    
   <link href="../resources/css/bootstrap.css"  rel="stylesheet" >
    <link href="../resources/css/fontawesome-all.min.css"  rel="stylesheet" > 
   
  </head> 

<body>

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

	<spring:url value="/selectacompanhante/marcacaos/" var="selectacompanhanteUrl" />
					  <button class="btn btn-info" 
                                          onclick="location.href='${selectacompanhanteUrl}'">Selecionar Acompanhante(s)</button>                                          
                                          

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
		
			
		
		<spring:bind path="unidadesaude.descricao">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Unidade Saude</label>
			<div class="col-sm-10">
				<form:input path="unidadesaude.descricao" type="text" class="form-control" 
                                id="unidadesaude.descricao" placeholder="ID Proc" />
				<form:errors path="unidadesaude.descricao" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		
		
		<spring:bind path="horaprocedimento">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Hora do procedimento</label>
			<div class="col-sm-10">
				<form:input path="horaprocedimento" type="text" class="form-control" 
                                id="horaprocedimento" placeholder="Hora" />
				<form:errors path="horaprocedimento" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<spring:bind path="localacolhimento">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Local acolhimento</label>
			<div class="col-sm-10">
				<form:input path="localacolhimento" type="text" class="form-control" 
                                id="localacolhimento" placeholder="Local Ac" />
				<form:errors path="localacolhimento" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<spring:bind path="observacao">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Observa��o</label>
			<div class="col-sm-10">
				<form:input path="observacao" type="text" class="form-control" 
                                id="observacao" placeholder="Nenhuma" />
				<form:errors path="observacao" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		
		<spring:bind path="dataviagem">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			
			 <div class="control-group">
        		<form:label cssClass="control-label" path="dataviagem">Data:</form:label>
        		<div class="controls">
            	<form:input path="dataviagem" class="date" />
        		</div>
    		</div>
		  </div>
		</spring:bind>
		

	<spring:bind path="vagas">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			
			 <div class="control-group">
        		<form:label cssClass="control-label" path="vagas">Vagas:</form:label>
        		<div class="controls">
            	<form:label path="vagas" class="date" />
        		</div>
    		</div>
		  </div>
		</spring:bind>		


<c:if test="${not empty acompanhantespaciente}">

	<table>
  
  <tr>
  <td> ID Acompanhante</td> <td> Nome</td>
  </tr>
   
  <c:forEach items="${acompanhantespaciente}" var="acompanhante" varStatus="status">
  <tr>
     
        <td>
    
        <td><c:out value="${acompanhante.id}"/> </td>
        <td><c:out value="${acompanhante.nome}"/> </td>
        
    
        </td>
     
     </tr>
   </c:forEach>


  
</table> 
</c:if>
	
	<Br>

				
				
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


	<script src="../resources/js/jquery.min.js"  type="text/javascript"></script>
	<script src="../resources/js/popper.min.js"  type="text/javascript"></script>
	<script src="../resources/js/bootstrap.js"  type="text/javascript"></script>
	</body>
	</html>