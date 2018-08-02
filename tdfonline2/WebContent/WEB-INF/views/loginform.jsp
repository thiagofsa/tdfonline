<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>

<html lang="pt-br">

  <head>    
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><tiles:getAsString name="title" /></title>
    
    <link rel="stylesheet" href= "<c:url value='/resources/css/bootstrap.css'/>" >  
    <link rel="stylesheet" href= "<c:url value='/resources/css/fontawesome.css'/>" >
    <link rel="stylesheet" href= "<c:url value='/resources/css/login.css'/>" >
   
  </head>  
 
<body>

<div  class="container">

	<div class="row justify-content-center align-items-center" style=" height: 100vh;">	
					
		<div class="shadow p-3" id="divlogin">
		
			<c:if test="${not empty msg}">
				<div  class="row areanotify justify-content-end py-3">							    
					<strong id="textonotify" class=" animated fadeout font-italic my-3" ><i class="fas fa-check-circle fa-lg text-success mr-1"></i>${msg}</strong>			
				</div>
			</c:if>		
				
              <div class="row justify-content-center my-3" >
					<img src="<c:url value="/resources/images/logodark.png"/>">	
			  </div>
              <form   class="mx-auto mt-5" action="/springtest/efetualogin" method="post">
              
                <div class="form-group icone">
				    <i class="fa fa-user" aria-hidden="true"></i>
				    <input type="text" class="form-control" id="login" name="login" aria-describedby="login" placeholder="Nome de usuário">
				</div>
				
				<div class="form-group icone">
				    <i class="fa fa-key" aria-hidden="true"></i>
				    <input type="password" class="form-control" id="senha" name="senha" placeholder="Password">
				</div>
				  
				  <div class="form-group text-center">
				  	<button type="submit" class="btn btn-primary btn-block btn-lg mt-4">Entrar</button>  
				  </div>						  
              </form>
              
              <div class="row justify-content-center rodape">
                <span class="copy" >Copyright ©</span> Todos os direitos reservados
              </div>		   
		</div>
	</div>
</div>

	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
</body>
</html>
