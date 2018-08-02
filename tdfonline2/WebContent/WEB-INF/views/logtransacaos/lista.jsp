<%@ page session="false"%>
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
    <link rel="stylesheet" href= "<c:url value='/resources/css/animate.min.css'/>" >
    <link rel="stylesheet" href= "<c:url value='/resources/css/jquery.dataTables.css'/>" > 
    
  </head>  
<body>
<br><Br><br><br><Br><br><br><Br><br>
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

		<h1>LogTransacao</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Data</th>
					<th>Login</th>
					<th>Entidade</th>
					<th>Operacao</th>
					<th>ID entidade</th>
					
					
				</tr>
			</thead>

			<c:forEach var="logtransacao" items="${logtransacaos}">
			    <tr>
				<td>
					${logtransacao.id}
				</td>
				<td>${logtransacao.data}</td>
				<td>${logtransacao.usuario.login}</td>
				<td>${logtransacao.transacao.entidade}</td>
				<td>${logtransacao.transacao.operacao}</td>
				<td>${logtransacao.identidade}</td>
				
				
				
				
				<td>
				  <spring:url value="/logtransacaos/detalhes/${logtransacao.id}/${logtransacao.identidade}" var="logtransacaoUrl" />
				  

				  <button class="btn btn-info" 
                                          onclick="location.href='${logtransacaoUrl}'">Detalhes</button>
				  
                </td>
			    </tr>
			</c:forEach>
		</table>

<div  class="spaceabaixo"></div>	
</div>
	
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>	
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
  <script>
	  $(document).ready(
			  function() {
		    	$('#Tabela').DataTable();
		    	$('.telefone').mask("(00) 00000-0000");    	 
		    	$('.cpf').mask('000.000.000-00');		    	
		} );
  </script>   
</body>
</html>
