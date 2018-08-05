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

    
    <link rel="stylesheet" href= "<c:url value='/resources/css/bootstrap.css'/>" >  
    <link rel="stylesheet" href= "<c:url value='/resources/css/fontawesome.css'/>" >    
    <link rel="stylesheet" href= "<c:url value='/resources/css/animate.min.css'/>" >
    <link rel="stylesheet" href= "<c:url value='/resources/css/jquery.dataTables.css'/>" > 
    
  </head>  
<body>



<div  class="spacesup"></div>
<div  class="spacesup"></div>

<spring:url value="/marcacaos/comprovantemarcacaoreport2" var="findUrl" />

	<form action="${findUrl}" method="POST">

		
	Nome do Paciente: <input type="text" name="nomepaciente" id="nomepaciente">
	
	Data inicio: <input type="text" name="datainicio" id="datainicio">
	
	Data fim: <input type="text" name="datafim" id="datafim">
	
	
			
			
   <button type="submit" class="btn-lg btn-primary pull-right" >Pesquisar
                             </button>
			
		
	</form>
	
	
	
	<c:if test="${not empty marcacaos}">
	
		<h1>Marcações encontradas</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Paciente</th>
					<th>Procedimento</th>
					<th>Data Viagem</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="marcacao" items="${marcacaos}">
			    <tr>
				<td>
					${marcacao.id}
				</td>
				<td>${marcacao.paciente.nome}</td>
				<td>${marcacao.procedimento.nome}</td>
				<td>${marcacao.dataviagem}</td>
				
				
				<td>
				  <spring:url value="/reports/comprovantemarcacaoreport/${marcacao.id}" var="marcacaoreportUrl" />
				  

				  
                  <button class="btn btn-primary" 
                                          onclick="location.href='${marcacaoreportUrl}'">Imprimir Comprovante</button>
                                
				  
                                </td>                                
			    </tr>
			</c:forEach>
		</table>
	
	</c:if>
	
	
	
	</body>
</html>
