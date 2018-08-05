
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
    <link rel="stylesheet" href= "<c:url value='/resources/css/jquery.dataTables.css'/>" >
    
    
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
	<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
	<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
	
	<script>
		$(function() {
			 $("#calendario").datepicker({
			        changeMonth: true,
			        changeYear: true,
			        dateFormat: 'dd/mm/yy'
			    });
	    
		});
		$(function() {
			 $("#calendario2").datepicker({
			        changeMonth: true,
			        changeYear: true,
			        dateFormat: 'dd/mm/yy'
			    });
	    
		});
	</script>
    
  </head>  
<body>

<div class="container"  >

<div  class="spacesup"></div>
<div  class="spacesup"></div>

<spring:url value="/requisicaos/requisicaosporperiodoreport2" var="findrequisicaosUrl" />

	<form action="${findrequisicaosUrl}" method="post">

		
			<div class="controls">
    			Paciente <input type="text" id="nomepaciente" name="nomepaciente" value="${nomepaciente}">
        	</div>
        	
        	<!-- 		<div class="controls">
    			Data Inicial (dd/mm/aaaa): <input type="text" id="datainicial" name="datainicial" value="${datainicial}">
        	</div>
        	 -->
    


		   <p>Data inicial: <input type="text" name="datainicial" id="calendario"  /></p>
		
    
    		
		 	<div class="controls">
    			  <p>Data Final: <input type="text" name="datafinal" id="calendario2"  /></p>
        	</div>

			
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			
			     <button type="submit" class="btn-lg btn-primary pull-right" >Pesquisar
                             </button>
			
		  </div>
		</div>
	</form>
	
	<br><br>
	
	<c:if test="${not empty requisicaos}">
	<h2>Requisicaos </h2>	
		<div class="table-responsive-md mt-4" >		
		<table class="table table-sm table-striped table-hover display" >
			<thead>
				<tr>
					<th class="text-center">ID</th>
					<th class="text-center">Nome Paciente</th>
					<th class="text-center">Procedimento</th>
					<th class="text-center">Médico Solicitante</th>
					<th class="text-center">Médico Autorizador</th>
					<th class="text-center">Data</th>
					<th class="text-center">Selecionar</th>
				</tr>
			</thead>

			<c:forEach var="requisicao" items="${requisicaos}">
			    <tr>				
				<td>${requisicao.id}</td>
				<td>${requisicao.paciente.nome}</td>
				<td class="text-center">${requisicao.procedimento.nome}</td>
				<td class="text-center">${requisicao.medico.nome}</td>
				<td class="text-center">${requisicao.medicoautorizador.nome}</td>
				<td class="text-center">${requisicao.data}</td>
				<td class="text-center">
				  <spring:url value="/requisicaos/getrequisicaofile/${requisicao.id}" var="requisicaoUrl" />
				  <button class="btn btn-sm" data-toggle="tooltip" data-placement="botton" title="Exibir imagem da requisicao"  onclick="location.href='${requisicaoUrl}'">Visualizar imagem</button>			  
                </td>
			    </tr>
			</c:forEach>
		</table>
		</div>
		
		 <% 
				String nomepaciente= request.getParameter("nomepaciente");
		
				if ((nomepaciente==null) || (nomepaciente.trim().length()==0)){
					request.setAttribute("nomepaciente", "null");
				}
			
		%>							
				
		<spring:url value="/reports/requisicaosporperiodoreport/${nomepaciente}/${datainicial}/${datafinal}"
			var="requisicaoreportUrl" />
		
				
		<button class="btn btn-sm" data-toggle="tooltip" data-placement="botton" title="Gerar relatório" onclick="location.href='${requisicaoreportUrl}'"> Converter em PDF </button>
		</c:if>
	
	<br><br><br><br><br>
	
				
			
	
	
	<div  class="spaceabaixo"></div>	
</div>
	
	
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>

  <script>
	  $(document).ready(function() {
		    $('#Tabela').DataTable();
		} );
  </script>   
  

  
</body>
</html>
