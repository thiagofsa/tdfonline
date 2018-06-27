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
		
		
<!--  <div class="row">

<div class="col-md-6" >
	<div class="titulo">
	
	<spring:url value="/motoristas/find2" var="findUrl" />
		
		<!-- <form:form  action="${findUrl}"> 
		<form  method="POST"  action="${findUrl}" >
		<div class="form-row" >
    		<div class="form-group col-sm-10">    		
    			<input type="text" class="form-control" name="nome"  placeholder="Nome ou CPF" required="required">
  			</div>
  			
  			<div  class="form-group col-sm-2"  >
  				<button type="submit" class="btn btn-outline-info"><i class="fas fa-search"></i> Pesquisar</button>
  			</div>  		
  		</div>
		</form>
	</div>		
</div>


<div class="col-md-6" >
	<div class="titulo">	
		<div  class="col-sm-12"  >	
			<a class="btn btn-outline-primary"  href="${pageContext.request.contextPath}/motoristas/add"><i class="fas fa-plus-square"></i>  Adcionar Novo Motorista</a>
		</div>		
	</div>		
</div>
</div>-->		
		


		<div   class="titulo">
			<h4>Relação dos Motoristas</h4>
		</div>



		<div class="table-responsive-md" >
		
		<table id="Tabela" class="table table-striped table-hover display"  style="width:100%">
			<thead>
				<tr>
					<th>Nome</th>
					<th  class="text-center" >Telefone</th>
					<th  class="text-center">Detalhes</th>
				</tr>
			</thead>

			<c:forEach var="motorista" items="${motoristas}">
			    <tr>
				<td>${motorista.nome}</td>
				<td  class="text-center">${motorista.telefone}</td>				
				<td class="text-center">
				  <spring:url value="/motoristas/${motorista.id}/update" var="updateUrl" />
				  <button class="btn btn-sm" onclick="location.href='${updateUrl}'"><i class="fas fa-newspaper"></i></button>
				 </td>
			    </tr>
			    
			</c:forEach>
		</table>
		</div>
		
		
		<div class="col-sm-12" >
			<div class="titulo">	
				<div  class="col-sm-12"  >	
					<a class="btn btn-block  btn-outline-primary"  href="${pageContext.request.contextPath}/motoristas/add"><i class="fas fa-plus-square"></i>  Adcionar Novo Motorista</a>
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