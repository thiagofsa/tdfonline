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

	<div  class="spacesup" ></div>	
	<div class="titulo"  >
		<h4>Detalhes Unidade de Sa�de</h4>
	</div>

	<spring:url value="/unidadesaudes" var="unidadesaudeActionUrl" />

	<form:form  method="post" modelAttribute="unidadesaudeForm" action="${unidadesaudeActionUrl}">

		<form:hidden path="id" />

		<div  class="form-row" >		
			<spring:bind path="descricao">
			  <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
				<label for="nome">Descri��o</label>			
					<form:input path="descricao" type="text" class="form-control" id="descricao" required="required"/>                                
					<form:errors path="descricao" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="telefone">
			  <div class="form-group col-md-3 ${status.error ? 'has-error' : ''}">
				<label for="telefone">Telefone</label>			
					<form:input path="telefone" type="text" class="form-control" id="telefone" required="required"/>                                
					<form:errors path="telefone" class="control-label" />			
			  </div>
			</spring:bind>		
			
			<spring:bind path="cidade.id">
			  <div class="form-group col-md-3 ${status.error ? 'has-error' : ''}">
				<label for="cidade">Cidade</label>				    			
	    			
	    			
	    			<form:select path="cidade.id" class="form-control"  id="cidade" required="required" >
	    				<form:option value="" label="Escolha a cidade"/>
	    				<form:option value="1" label="Recife"/>
	    				<form:option value="2" label="Caruaru"/>     				
					</form:select>    				                               
					<form:errors path="cidade.id" class="control-label" />			
			  </div>
			  <form:hidden path="cidade.id" />	
			</spring:bind>		
		</div>
		
		<div  class="form-row" >		
			<spring:bind path="logradouro">
			  <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
				<label for="logradouro">Endere�o</label>			
					<form:input path="logradouro" type="text" class="form-control" id="logradouro" required="required"/>                                
					<form:errors path="logradouro" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="numero">
			  <div class="form-group col-md-2 ${status.error ? 'has-error' : ''}">
				<label for="numero">Numero</label>			
					<form:input path="numero" type="text" class="form-control" id="numero" required="required"/>                                
					<form:errors path="numero" class="control-label" />			
			  </div>
			</spring:bind>	
			
			<spring:bind path="bairro">
			  <div class="form-group col-md-4 ${status.error ? 'has-error' : ''}">
				<label for="bairro">Bairro</label>			
					<form:input path="bairro" type="text" class="form-control" id="bairro" required="required"/>                                
					<form:errors path="bairro" class="control-label" />			
			  </div>
			</spring:bind>				
		</div>
		
		<div  class="form-row">
			<spring:bind path="complemento">
			  <div class="form-group col-sm-12 ${status.error ? 'has-error' : ''}">
				<label for="complemento">Complemento</label>			
					<form:input path="complemento" type="text" class="form-control" id="complemento" required="required"/>                                
					<form:errors path="complemento" class="control-label" />			
			  </div>
			</spring:bind>		
		</div>
		
		<div  class="form-row justify-content-center" >
			
			<div class="form-group col-xs-4 text-center">		
				<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-circle"></i> <span class="esconder"> Atualizar</span></button>				
			</div>
						
			<div class="form-group col-xs-4 text-center">	
				<spring:url value="/unidadesaudes/${id}/delete" var="deleteUrl" />		
				<button type="button" class="btn btn-outline-danger"  data-toggle="modal" data-target="#exclusao" ><i class="fas fa-trash-alt"></i> <span class="esconder"> Excluir</span></button>											
			</div>
						
			<div class="form-group col-xs-4 text-center">				
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/unidadesaudes/"><i class="fas fa-arrow-circle-left"></i> <span class="esconder"> Cancelar</span></a>				
			</div>						
		</div>	
		
	<!-- Modal -->
<div class="modal fade" id="exclusao" tabindex="-1" role="dialog" aria-labelledby="exclusao" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title " id="exampleModalLongTitle"><i class="fas fa-exclamation-triangle fa-2x text-warning"></i> <span class="font-italic" > Esta opera��o n�o pode ser revertida.</span></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body font-weight-bold">
        Deseja confirmar a exclus�o deste item?
      </div>
      <div class="modal-footer">        
        <button type="button" class="btn btn-sm btn-secondary"  data-toggle="modal" data-target="#exampleModalLong" onclick="location.href='${deleteUrl}'">Confirmar</button>
        <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">Cancelar</button>
      </div>
    </div>
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