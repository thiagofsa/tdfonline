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
	
	<div  class="spacesup"></div>		
		
		<div   class="text-center text-uppercase">
			<h4>Detalhes do motorista selecionado</h4>
		</div>	
	
	
	<spring:url value="/motoristas" var="motoristaActionUrl" />

	<form:form class="mt-5" method="post" modelAttribute="motoristaForm" action="${motoristaActionUrl}" >

		<form:hidden path="id" />

		<div  class="form-row" >
		
		<spring:bind path="nome">
		  <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
			<label for="nome">Nome</label>			
				<form:input path="nome" type="text" class="form-control" id="nome"  required="required"/>                                
				<form:errors path="nome" class="control-label" />			
		  </div>
		</spring:bind>	
		
		
		<spring:bind path="cpf">
		  <div class="form-group col-md-3 col-sm-6 ${status.error ? 'has-error' : ''}">
			<label for="cpf">Cpf</label>			
				<form:input path="cpf" type="text" class="form-control" id="cpf" placeholder="___.___.___-__" required="required"/>                                
				<form:errors path="cpf" class="control-label" />			
		  </div>
		</spring:bind>	
		
		<spring:bind path="matricula">
		  <div class="form-group col-md-3 col-sm-6 ${status.error ? 'has-error' : ''}">
			<label for="matricula">Matrícula</label>			
				<form:input path="matricula" type="text" class="form-control" id="matricula" required="required"/>                                
				<form:errors path="matricula" class="control-label" />
		   </div>
		</spring:bind>		
		</div>

		<div  class="form-row" >
		
		<spring:bind path="email">
		  <div class="form-group col-md-6${status.error ? 'has-error' : ''}">
			<label for="email" >Email</label>			
				<form:input path="email" type="email" class="form-control" id="email" placeholder="seuemail@provedormail.com" required="required"/>                               
				<form:errors path="email" class="control-label" />			
		  </div>
		</spring:bind>
		
		<spring:bind path="telefone">
		  <div class="form-group col-md-3  col-sm-6 ${status.error ? 'has-error' : ''}">
			<label for="telefone">Telefone</label>			
				<form:input path="telefone" class="form-control" id="telefone" placeholder="(__) _____-____" required="required"    />                                
				<form:errors path="telefone" class="control-label" />
		   </div>
		</spring:bind>
		
		<spring:bind path="categoriacnh">
		  <div class="form-group col-md-3 col-sm-6 ${status.error ? 'has-error' : ''}">
			<label for="categoriacnh" >Categoria CNH</label>			
				<form:input path="categoriacnh" class="form-control" id="categoriacnh" required="required"/>                               
				<form:errors path="categoriacnh" class="control-label" />			
		  </div>
		</spring:bind>
		
		</div>
		
		<div  class="form-row justify-content-center mt-4" >			
			
				<div class="form-group col-xs-4  text-center">		
					<button type="submit" class="btn btn-outline-success" ><i class="fas fa-check-circle mx-2"></i> <span class="esconder"> Atualizar</span></button>				
				</div>
				
				<div class="form-group col-xs-4  text-center">	
					<spring:url value="/motoristas/${id}/delete" var="deleteUrl" />		
					<button type="button" class="btn btn-outline-danger"  data-toggle="modal" data-target="#exclusao"><i class="fas fa-trash-alt mx-2"></i> <span class="esconder"> Excluir</span></button>											
				</div>
			
				<div class="form-group col-xs-4  text-center">				
					<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/motoristas/"><i class="fas fa-arrow-circle-left mx-2"></i> <span class="esconder"> Cancelar</span></a>				
				</div>	
				
		</div> 	
		
		<!-- Modal -->
<div class="modal fade" id="exclusao" tabindex="-1" role="dialog" aria-labelledby="exclusao" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title " id="exampleModalLongTitle"><i class="fas fa-exclamation-triangle fa-2x text-warning"></i> <span class="font-italic" > Esta operação não pode ser revertida.</span></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body font-weight-bold">
        Deseja confirmar a exclusão deste item?
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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
	
	<script>
    	$(document).ready(
	    	function(){
	    	  $('#telefone').mask("(00) 00000-0000");    	 
	    	  $('#cpf').mask('000.000.000-00');
	    	  $('#categoriacnh').mask('SS');
	    	  $('#matricula').mask('#');  
	    	});    	 
    	
    	function myFunction() {	    	
    		var texto = $('#cpf').cleanVal();	
    		$("#cpf2").val(texto);
    		}
    	
    </script>		
</body>
</html>