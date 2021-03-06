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
  </head>  
 
<body>

<div class="container">


<spring:url value="/beneficioavulsos/find2" var="findUrl" />

	<form action="${findUrl}" method="POST">

		
	
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			 <div class="control-group">
        		Nome Paciente:
        		<div class="controls">
            	 <input type="text" name="nome"><br>
        		</div>
    		</div>
		  </div>
	
	
	
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			
			 <div class="control-group">
        		Data ini:
        		<div class="controls">
            	<input type="text" name="dataini"><br>
        		</div>
    		</div>
		  </div>
	
	
	
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			
			 <div class="control-group">
        		Data fim:
        		<div class="controls">
            	<input type="text" name="datafim"><br>
        		</div>
    		</div>
		  </div>
	
	

				
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			
			     <button type="submit" class="btn-lg btn-primary pull-right" >Pesquisar
                             </button>
			
		  </div>
		</div>
	</form>
	
	
	<c:if test="${not empty beneficioavulsos}">
	
		<h1>Marca��es encontradas</h1>

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

			<c:forEach var="beneficioavulso" items="${beneficioavulsos}">
			    <tr>
				<td>
					${beneficioavulso.id}
				</td>
				<td>${beneficioavulso.paciente.nome}</td>
				<td>${beneficioavulso.procedimento.nome}</td>
				<td>${beneficioavulso.dataviagem}</td>
				
				
				<td>
				  <spring:url value="/beneficioavulsos/${beneficioavulso.id}" var="beneficioavulsoUrl" />
				  <spring:url value="/beneficioavulsos/${beneficioavulso.id}/delete" var="deleteUrl" /> 
				  <spring:url value="/beneficioavulsos/${beneficioavulso.id}/update" var="updateUrl" />
				  <spring:url value="/beneficioavulsos/${beneficioavulso.id}/update" var="replicarUrl" />

				  <button class="btn btn-info" 
                                          onclick="location.href='${beneficioavulsoUrl}'">Detalhes</button>
				  <button class="btn btn-primary" 
                                          onclick="location.href='${updateUrl}'">Atualizar</button>
				  <button class="btn btn-danger" 
                                          onclick="location.href='${deleteUrl}'">Deletar</button>
                  <button class="btn btn-primary" 
                                          onclick="location.href='${replicarUrl}'">Replicar</button>
                                
				  
                                </td>                                
			    </tr>
			</c:forEach>
		</table>
	
	</c:if>
	
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