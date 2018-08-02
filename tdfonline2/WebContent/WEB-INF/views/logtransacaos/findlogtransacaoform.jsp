<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html lang="pt-br">
  <head>    
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><tiles:getAsString name="title" /></title>
    
    <link rel="stylesheet" href= "<c:url value='/resources/css/bootstrap.css'/>" >  
    <link rel="stylesheet" href= "<c:url value='/resources/css/fontawesome.css'/>" >    
    <link rel="stylesheet" href= "<c:url value='/resources/css/animate.min.css'/>" >    
  </head>  
<body>



<div class="container">

	<div  class="spacesup"></div>
	<div  class="spacesup"></div>



	<spring:url value="/selectusuario/logtransacaos/" var="selectusuarioUrl" />
					  <button class="btn btn-info" 
                                          onclick="location.href='${selectusuarioUrl}'">Selecionar Usuario</button>
	
	
	<spring:url value="/logtransacaos/findLogTransacaosByUsuarioandtipotransacao" var="logtransacaoActionUrl" />

	<form:form  method="post" modelAttribute="logtransacaoForm" action="${logtransacaoActionUrl}">

		<form:hidden path="id" />
	
		<spring:bind path="usuario.login">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Login</label>
			<div class="col-sm-10">
				<form:input path="usuario.login" type="text" class="form-control" readonly="true" 
                                id="usuario.login" placeholder="login" />
				<form:errors path="usuario.login" class="control-label" />
			</div>
		
		<form:hidden path="usuario.id" />	  
			  
		  </div>
		  
		</spring:bind>
		
		<spring:bind path="transacao.entidade">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Entidade</label>
			<div class="col-sm-10">
			
				<form:select path="transacao.entidade">
				    <form:option value="-1" label="--- ESCOLHA ---"/>
				  <form:option value="1" label="--- USUARIO ---"/>
				  <form:option value="2" label="--- MARCACAO ---"/>
				  <form:option value="3" label="--- ENCAMINHAMENTO ---"/>
				  <form:option value="4" label="--- DISTRIBUIÇÃO ---"/>
				  <form:option value="5" label="--- BENEFÍCIO ---"/>
				</form:select>
				
				<form:errors path="transacao.entidade" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
			
		<spring:bind path="transacao.operacao">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Operacao</label>
			<div class="col-sm-10">
			
			<form:select path="transacao.operacao">
				    <form:option value="-1" label="--- ESCOLHA ---"/>
				  <form:option value="1" label="--- ADICIONAR ---"/>
				  <form:option value="2" label="--- ALTERAR ---"/>
				  <form:option value="3" label="--- EXCLUIR ---"/>
				  
			</form:select>
				
				<form:errors path="transacao.operacao" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		     <button type="submit" class="btn-lg btn-primary pull-right">Pesquisar
                             </button>
			  
			
		
			
		
	</form:form>
	
	<div  class="spaceabaixo"></div>	
</div>
	
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>	
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
</body>
</html>