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
		<h4>Dados do novo paciente</h4>
	</div>	

	<spring:url value="/pacientes" var="pacienteActionUrl"/>
	<form:form  class="mt-4" method="post" modelAttribute="pacienteForm" action="${pacienteActionUrl}">

		<form:hidden path="id" />

		<div  class="form-row" >
		
			<spring:bind path="nome">
			  <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
				<label for="nome">Nome</label>			
					<form:input path="nome" type="text" class="form-control" id="nome" placeholder="" required="required"/>                                
					<form:errors path="nome" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="datanascimento">
			  <div class="form-group col-md-3 col-sm-6 ${status.error ? 'has-error' : ''}">
				<label for="datanascimento">Data de Nascimento</label>			
					<form:input path="datanascimento" type="text" class="form-control" id="datanascimento"  placeholder="__/__/____" required="required"/>                                
					<form:errors path="datanascimento" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="telefone">
			  <div class="form-group col-md-3 col-sm-6 ${status.error ? 'has-error' : ''}">
				<label for="telefone">Telefone</label>			
					<form:input path="telefone" type="text" class="form-control" id="telefone"  placeholder="()_____-____" required="required"/>                                
					<form:errors path="telefone" class="control-label" />			
			  </div>
			</spring:bind>
		
		</div>
		
		<div  class="form-row" >
		
			<spring:bind path="cpf">
			  <div class="form-group col-md-3 col-sm-6 ${status.error ? 'has-error' : ''}">
				<label for="cpf">Cpf</label>			
					<form:input path="cpf" type="text" class="form-control" id="cpf"  placeholder="___.___.___-__"  required="required"/>                                
					<form:errors path="cpf" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="cartaosus">
			  <div class="form-group col-md-3 col-sm-6 ${status.error ? 'has-error' : ''}">
				<label for="cartaosus">Cartão SUS</label>			
					<form:input path="cartaosus" type="text" class="form-control cartaosus" id="cartaosus" placeholder="___ ____ ____ ____"  required="required"/>                                
					<form:errors path="cartaosus" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="rg">
			  <div class="form-group col-md-3 col-sm-6  ${status.error ? 'has-error' : ''}">
				<label for="rg">Identidade</label>			
					<form:input path="rg" type="text" class="form-control" id="rg"/>                                
					<form:errors path="rg" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="tiposanguineo">
			  <div class="form-group col-md-3  col-sm-6 ${status.error ? 'has-error' : ''}">
				<label for="nome">Tipo Sanguíneo</label>				    			
	    			<form:select path="tiposanguineo" class="form-control"  id="tiposanguineo" required="required" >
	    				<form:option value="" label="- Escolha -"/>
	    				<form:option value="O pos" label="O pos"/>
	    				<form:option value="O neg" label="O neg"/>
	    				<form:option value="A pos" label="A pos"/>
	    				<form:option value="A neg" label="A neg"/>
	    				<form:option value="B pos" label="B pos"/>
	    				<form:option value="B neg" label="B neg"/>
	    				<form:option value="AB pos" label="AB pos"/>
	    				<form:option value="AB neg" label="AB neg"/>    				    				
					</form:select>    				                               
					<form:errors path="tiposanguineo" class="control-label" />			
			  </div>
			</spring:bind>		
		
		</div>
		
		<div  class="form-row" >
		
			<spring:bind path="logradouro">
			  <div class="form-group col-md-5  col-sm-12  ${status.error ? 'has-error' : ''}">
				<label for="logradouro">Endereço</label>			
					<form:input path="logradouro" type="text" class="form-control" id="logradouro" required="required"/>                                
					<form:errors path="logradouro" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="numero">
			  <div class="form-group col-md-2  col-sm-3 ${status.error ? 'has-error' : ''}">
				<label for="numero">Numero</label>			
					<form:input path="numero" type="text" class="form-control" id="numero" required="required"/>                                
					<form:errors path="numero" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="bairro">
			  <div class="form-group col-md-3  col-sm-9 ${status.error ? 'has-error' : ''}">
				<label for="bairro">Bairro</label>			
					<form:input path="bairro" type="text" class="form-control" id="bairro" required="required"/>                                
					<form:errors path="bairro" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="complemento">
			  <div class="form-group col-md-2 ${status.error ? 'has-error' : ''}">
				<label for="complemento">Complemento</label>			
					<form:input path="complemento" type="text" class="form-control" id="complemento"/>                                
					<form:errors path="complemento" class="control-label" />			
			  </div>
			</spring:bind>
		
		</div>
				
		<div  class="form-row" >
		
			<spring:bind path="banco">
			  <div class="form-group col-md-6  col-sm-6  ${status.error ? 'has-error' : ''}">
				<label for="banco">Banco</label>			
					<form:input path="banco" type="text" class="form-control" id="banco" required="required"/>                                
					<form:errors path="banco" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="agencia">
			  <div class="form-group col-md-3  col-sm-3 ${status.error ? 'has-error' : ''}">
				<label for="agencia">Agência</label>			
					<form:input path="agencia" type="text" class="form-control" id="agencia" required="required"/>                                
					<form:errors path="agencia" class="control-label" />			
			  </div>
			</spring:bind>
			
			<spring:bind path="conta">
			  <div class="form-group col-md-3  col-sm-3 ${status.error ? 'has-error' : ''}">
				<label for="conta">Conta/Poupança</label>			
					<form:input path="conta" type="text" class="form-control" id="conta" required="required"/>                                
					<form:errors path="conta" class="control-label" />			
			  </div>
			</spring:bind>
		
		</div>
		
		<div  class="form-row" >
			<spring:bind path="observacoes">
			  <div class="form-group col-md-12 ${status.error ? 'has-error' : ''}">
				<label for="observacoes">Condições Especiais</label>			
					<form:textarea path="observacoes" class="form-control" rows="2" id="observacoes"/>                                
					<form:errors path="observacoes" class="control-label" />			
			  </div>
			</spring:bind>		
		</div>
		
		<div  class="form-row justify-content-center" >				
			<div class="form-group col-xs-6 text-center">		
				<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-circle"></i> <span class="esconder"> Cadastrar</span></button>				
			</div>	
			
			<div class="form-group col-xs-6 text-center">		
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/pacientes"><i class="fas fa-arrow-circle-left"></i> <span class="esconder"> Cancelar</span></a>				
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
	    	  $('.telefone').mask("(00) 00000-0000");    	 
	    	  $('.data').mask("00/00/0000");
	    	  $('.cpf').mask('000.000.000-00');
	    	  $('.cartaosus').mask('000 0000 0000 0000');  
	    	});    	     	
    </script>		
</body>
</html>