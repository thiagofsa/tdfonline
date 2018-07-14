<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    <link rel="stylesheet" href= "<c:url value='/resources/css/jquery.dataTables.css'/>" > 
    
  </head>  
<body>

	<div class="container">

<div  class="spacesup"></div>
<div  class="spacesup"></div>
		<c:if test="${not empty msg}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" 
                                aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		    </div>
		</c:if>
	
		
<spring:url value="/encaminhamentos/lote2" var="acompanhanteUrl" />

<form class="form-horizontal"  action="${acompanhanteUrl}">

<h1>Passo 1 - Selecione a distribuicão </h1>
                
<table border="1">
  
	 <tr>
        <td> Selecione</td>
        <td>ID </td>
        <td>Data Viagem </td>
        <td>Veículo </td>
        <td>Motorista </td>
        <td>Vagas </td>
     </tr>
     
   <c:forEach items="${distribuicaos}" var="distribuicao" varStatus="status">
  <tr>
      
        <td> <input type="radio" id="iddistribuicao" name="iddistribuicao" value="${distribuicao.id}"/></td>
        <td><c:out value="${distribuicao.id}"/> </td>
        <td><c:out value="${distribuicao.dataviagem}"/> </td>
        <td><c:out value="${distribuicao.veiculo.descricao}"/> </td>
        <td><c:out value="${distribuicao.motorista.nome}"/> </td>
        <td><c:out value="${distribuicao.vagas}"/> </td>

        
     
     </tr>
   </c:forEach>
  
</table> 
  

<br><Br>

<h1>Passo 2 - Selecione as marcações </h1>  
  
<table border="1">
  
  <tr>
        <td> Selecione</td>
        <td>ID </td>
        <td>Data Viagem </td>
        <td>Paciente</td>
        <td>Destino - Un. Saúde </td>
        <td>Confirmada</td>
        <td>Total de Vagas </td>
        
     </tr>
     
   <c:forEach items="${marcacaos}" var="marcacao" >
  <tr>
      
        <td>  <input type="checkbox" id="idsmarcacao" name="idsmarcacao" value="${marcacao.id}"/></td>
        <td><c:out value="${marcacao.id}"/> </td>
        <td><c:out value="${marcacao.dataviagem}"/> </td>
        <td><c:out value="${marcacao.paciente.nome}"/> </td>
        <td><c:out value="${marcacao.unidadesaude.descricao}"/> </td>
        <td><c:out value="${marcacao.confirmada}"/> </td>
        <td><c:out value="${marcacao.vagas}"/> </td>
     
     </tr>
   </c:forEach>
  
</table>
  
  <br><Br>
  
  <div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			
			     <button type="submit" class="btn-lg btn-primary pull-right" >Enviar
                             </button>
			
		  </div>
	</div>
  
</form>

<div  class="spaceabaixo"></div>	
</div>
	
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>	
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js" integrity="sha256-u7MY6EG5ass8JhTuxBek18r5YG6pllB9zLqE4vZyTn4=" crossorigin="anonymous"></script>
  <script>
	  $(document).ready(function() {
		    $('#Tabela').DataTable();
		} );
  </script>   
</body>
</html>