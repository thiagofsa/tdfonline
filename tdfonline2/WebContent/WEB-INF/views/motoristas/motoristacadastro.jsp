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
			<h4>Dados do novo motorista</h4>
		</div>	

	<spring:url value="/motoristas" var="motoristaActionUrl" />

	<form:form class="mt-5"  method="post" modelAttribute="motoristaForm" action="${motoristaActionUrl}">

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
		  <div class="form-group col-md-3 col-sm-6 ${status.error ? 'has-error' : ''}">
			<label for="telefone">Telefone</label>			
				<form:input path="telefone" class="form-control" id="telefone" placeholder="(__) _____-____" required="required"/>                                
				<form:errors path="telefone" class="control-label" />
		   </div>
		</spring:bind>
		
		<spring:bind path="categoriacnh">
		  <div class="form-group col-md-3  col-sm-6${status.error ? 'has-error' : ''}">
			<label for="categoriacnh" >Categoria CNH</label>			
				<form:input path="categoriacnh" class="form-control" id="categoriacnh" required="required"/>                               
				<form:errors path="categoriacnh" class="control-label" />			
		  </div>
		</spring:bind>
		
		</div>
		
		
		<div  class="form-row justify-content-center mt-4" >				
			<div class="form-group col-xs-6 text-center">		
				<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-circle mx-2"></i> <span class="esconder"> Cadastrar</span></button>				
			</div>	
			
			<div class="form-group  col-xs-6 text-center">		
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/motoristas/"><i class="fas fa-arrow-circle-left mx-2"></i> <span class="esconder"> Cancelar</span></a>				
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
	    	 // $('#telefone').mask("(00) 00000-0000");    	 
	    	  $('#cpf').mask('000.000.000-00');
	    	  $('#categoriacnh').mask('SS');
	    	  $('#matricula').mask('#');  
	    	});    	 
    	
    	function myFunction() {	    	
    		var texto = $('#cpf').cleanVal();	
    		$("#cpf2").val(texto);
    		}  
    	
    	
    	var options =  {
    			  onblur: function(cep, e, field, options) {
    			    var masks = ['(00) 00000-0000', '(00) 0000-0000'];
    			    var mask = (cep.length=10) ? masks[1] : masks[0];
    			    $('.cpf').mask(mask, options);
    			}};

    			$('.cpf').mask('00000-000', options);   	
    	
    	
    </script>		
</body>
</html>