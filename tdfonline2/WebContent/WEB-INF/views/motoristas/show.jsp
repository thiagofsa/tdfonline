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

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">×</span>
			</button>
		    <strong>${msg}</strong>
		</div>
		
	</c:if>
	<div   class="text-center">Motorista Detalhes</div>
	<br/>
	
	<div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10">${motorista.id}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Nome</label>
		<div class="col-sm-10">${motorista.nome}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Email</label>
		<div class="col-sm-10">${motorista.email}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Endereço</label>
		<div class="col-sm-10">${motorista.endereco}</div>
	</div>	
</div>


	<script src="../resources/js/jquery.min.js"  type="text/javascript"></script>
	<script src="../resources/js/popper.min.js"  type="text/javascript"></script>
	<script src="../resources/js/bootstrap.js"  type="text/javascript"></script>
	</body>
	</html>