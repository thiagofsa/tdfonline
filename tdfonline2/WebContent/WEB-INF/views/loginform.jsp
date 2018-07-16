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
    <style type="text/css">
    	.icone{
    		position: relative;
    	}    	
    	.icone i{
		    position: absolute;
		    right: 0.7rem;
		    top: 0.7rem; 
		    color: #22313F;	    
		}    
    </style>
           
  </head>  
 
<body>

<div  class="container">
	
	<div class="row justify-content-center align-items-center" style=" height: 100vh;">	
					
		<div class="shadow p-3" >
					<c:if test="${not empty msg}">
					    <div class="alert alert-${css} alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert" 
			                                aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<strong>${msg}</strong>
					    </div>
					</c:if>
		
		
				<div  id="div">
		              <h3 class="mb-3 text-center" >TFDControll </h3>
		              <form   class="mx-auto" action="/springtest/efetualogin" method="post">
		              
		                <div class="form-group icone">
						    <i class="fa fa-user" aria-hidden="true"></i>
						    <input type="text" class="form-control" id="login" name="login" aria-describedby="login" placeholder="Nome de usuário">
						</div>
						
						<div class="form-group icone">
						    <i class="fa fa-key" aria-hidden="true"></i>
						    <input type="password" class="form-control" id="senha" name="senha" placeholder="Password">
						</div>
						  
						  <div class="form-group text-center">
						  	<button type="submit" class="btn btn-block btn-outline-primary mt-4">Entrar</button>  
						  </div>
						  
		              </form>
		         </div>
		</div>
	</div>
</div>

	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
</body>
</html>
