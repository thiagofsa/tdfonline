<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html lang="pt-br">
  <head>    
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    
    <link rel="stylesheet" href= "<c:url value='/resources/css/bootstrap.css'/>" >  
    <link rel="stylesheet" href= "<c:url value='/resources/css/fontawesome.css'/>" >    
    <link rel="stylesheet" href= "<c:url value='/resources/css/jquery.dataTables.css'/>" >
  </head>  
<body>

	<div class="container">

		<div  class="spacesup"></div>
		
		<div   class="text-center text-uppercase">
			<h4>Rela��o das transa��es com LOG</h4>
		</div>
		
		Entidades:<Br>
	//USUARIO =1 
	//MARCACAO = 2
	//ENCAMINHAMENTO = 3
	//DISTRIBUICAO = 4
	//BENEFICIO =5
	<br>

	Opera�ao <br>
	//ADD =1
	//UPDATE = 2
	//DELETE =3
	
		
		<div  class="row areanotify justify-content-end py-3">		
			<c:if test="${not empty msg}">			    
				<strong id="textonotify" class=" animated fadeout font-italic mr-2" ><i class="fas fa-check-circle fa-lg text-success mr-1"></i>${msg}</strong>
			</c:if>		
		</div>


		<div class="table-responsive-md animated fadein" >
		<table id="Tabela" class="table table-striped table-hover display"  style="width:100%">
			<thead>
				<tr>
					<th class="text-center">ID</th>
					<th class="text-center esconder">Entidade</th>
					<th class="text-center esconder">Operacao</th>
					<th class="text-center">Ativo</th>
					
				</tr>
			</thead>

			<c:forEach var="transacao" items="${transacaos}">
			    <tr>				
				
				<td  class="text-center esconder">${transacao.id}</td>
				<td  class="text-center esconder">${transacao.entidade}</td>
				<td class="text-center" >${transacao.operacao}</td>				
				<td class="text-center" >${transacao.valor}</td>
				<td  class="text-center">					
				  <spring:url value="/transacaos/${transacao.id}/update" var="updateUrl" />
				  <button class="btn btn-sm" data-toggle="tooltip" data-placement="botton" title="Visualizar Detalhes" onclick="location.href='${updateUrl}'"><i class="fas fa-newspaper"></i></button>	
				</td>
			    </tr>
			</c:forEach>
		</table>
		</div>
		
		

	<div  class="spaceabaixo"></div>	
</div>	
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