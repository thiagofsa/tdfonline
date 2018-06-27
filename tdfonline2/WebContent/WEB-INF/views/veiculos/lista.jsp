<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>

<html lang="pt-br">

  <head>    
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Lista de Veiculos</title>
    
    <link rel="stylesheet" href= "<c:url value='/resources/css/bootstrap.css'/>" >  
    <link rel="stylesheet" href= "<c:url value='/resources/css/fontawesome.css'/>" >    
    <link rel="stylesheet" href= "<c:url value='/resources/css/jquery.dataTables.css'/>" >
    
   
  </head>  

<body>

<div class="container">

	<div  class="space"></div>

		<c:if test="${not empty msg}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		    </div>
		</c:if>



		<div   class="titulo">
			<h4>Relação dos Veículos</h4>
		</div>


	<div class="table-responsive-md" >
		<table id="Tabela" class="table table-striped table-hover display"  style="width:100%">
			<thead>
				<tr>
					<th  class="text-center">Descrição</th>
					<th  class="text-center">Placa</th>
					<th  class="text-center">Situação</th>
					<th  class="text-center">Detalhes</th>
				</tr>
			</thead>

			<c:forEach var="veiculo" items="${veiculos}">
			    <tr>
				<td  class="text-center">${veiculo.descricao}</td>
				<td  class="text-center">${veiculo.placa}</td>
				<td  class="text-center">${veiculo.situacao}</td>
						
				
				<td  class="text-center">
				  
				  <spring:url value="/veiculos/${veiculo.id}/update" var="updateUrl" />

				  <!--  <button class="btn btn-sm" onclick="location.href='${motoristaUrl}'"><i class="fas fa-newspaper"></i></button> -->
				  <button class="btn btn-sm" onclick="location.href='${updateUrl}'"><i class="fas fa-newspaper"></i></button>
				  <!-- <button class="btn btn-secondary btn-sm" onclick="location.href='${updateUrl}'">Atualizar.</button> -->
				  <!-- <button class="btn btn-danger btn-sm" onclick="location.href='${deleteUrl}'">Deletar.</button>  -->
                </td>
			    </tr>
			</c:forEach>
		</table>
	</div>
	
		<div class="col-sm-12" >
			<div class="titulo">	
				<div  class="col-sm-12"  >	
					<a class="btn btn-block  btn-outline-primary"  href="${pageContext.request.contextPath}/veiculos/add"><i class="fas fa-plus-square"></i>  Adcionar Novo Veículo</a>
				</div>		
			</div>		
		</div>
	
	
	

	</div>

<div  class="space"></div>	
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>
	
	
  <script>
  $(document).ready(function() {
	    $('#Tabela').DataTable();
	} );
  </script>
  
</body>
</html>