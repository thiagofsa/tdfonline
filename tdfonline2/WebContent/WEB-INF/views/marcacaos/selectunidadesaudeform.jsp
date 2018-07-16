<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


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
		
		<div   class="text-center text-uppercase">
			<h4>Pesquise a Unidade de Sa�de</h4>
		</div>
		
		<div  class="row areanotify justify-content-end py-3">		
			<c:if test="${not empty msg}">			    
				<strong id="textonotify" class=" animated fadeout font-italic mr-2" ><i class="fas fa-check-circle fa-lg text-success mr-1"></i>${msg}</strong>
			</c:if>		
		</div>
		
	<spring:url value="/marcacaos/selectunidadesaude2" var="selectunidadesaudeUrl" />	
	<form:form  class="form-row"  modelAttribute="unidadesaude" action="${selectunidadesaudeUrl}">
		
		<div  class="form-group col-sm-1"></div>
		<spring:bind path="descricao">
			  <div class="form-group col-sm-8 ${status.error ? 'has-error' : ''}">		
					<form:input path="descricao" type="text" class="form-control" id="descricao" required="required"/>                                
					<form:errors path="descricao" class="control-label" />			
			  </div>
		</spring:bind>
				
		<div class="form-group col-sm-3 d-flex justify-content-center justify-content-md-start">				
			<button type="submit" class="btn btn-outline-secondary"><i class="fas fa-search"></i> <span class="esconder"> Pesquisar</span></button>				
		</div>
	
	</form:form>
	
	
	<c:if test="${not empty unidadesaudes}">

		<div class="text-center mt-5">
			<h5>Resultado da Pesquisa</h5>		
		</div>

		<div class="table-responsive-md mt-3" >		
		<table class="table table-striped table-hover display" >
			<thead>
				<tr>					
					<th class="text-center">Descricao</th>
					<th class="text-center">Cidade</th>
					<th class="text-center esconder">Telefone</th>
					<th class="text-center">Selecionar</th>
				</tr>
			</thead>

			<c:forEach var="unidadesaude" items="${unidadesaudes}">
			    <tr>
			    	<td>${unidadesaude.descricao}</td>
					<td class="text-center">${unidadesaude.cidade.descricao}</td>
					<td class="text-center esconder">${unidadesaude.telefone}</td>					
					<td class="text-center">
					  <spring:url value="/marcacaos/selectunidadesaude/${unidadesaude.id}" var="selectunidadesaudeUrl" />
					  <button class="btn btn-sm" data-toggle="tooltip" data-placement="botton" title="Selecione esta unidade" onclick="location.href='${selectunidadesaudeUrl}'"><i class="fas fa-check-circle"></i></button>					  
	                </td>
			    </tr>
			</c:forEach>
		</table>
		</div>
		</c:if>	
	
<div  class="spaceabaixo"></div>	
</div>	
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>	
</body>
</html>