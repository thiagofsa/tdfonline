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
    <link rel="stylesheet" href= "<c:url value='/resources/css/animate.min.css'/>" >    
  </head>  
<body>

<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" 
                                aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>Encaminhamento - Detalhes </h1>
	<br />

	<div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10">${encaminhamento.id}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Data</label>
		<div class="col-sm-10">${encaminhamento.dataviagem}</div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">Motorista</label>
		<div class="col-sm-10">${encaminhamento.distribuicao.motorista.nome}</div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">Veículo</label>
		<div class="col-sm-10">${encaminhamento.distribuicao.veiculo.descricao}</div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">Paciente</label>
		<div class="col-sm-10">${encaminhamento.marcacao.paciente.nome}</div>
	</div>
	
	<br>
	<br>
	
	<div class="row ">
		<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/cartaodeembaquereport/"><span class="esconder"> Gerar Cartão de Embarque</span></a>	
	</div>


<div  class="spaceabaixo"></div>	
</div>
	
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>	
</body>
</html>