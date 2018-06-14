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


<spring:url value="/marcacaos/find2" var="findUrl" />

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
				  <spring:url value="/marcacaos/${marcacao.id}" var="marcacaoUrl" />
				  <spring:url value="/marcacaos/${marcacao.id}/delete" var="deleteUrl" /> 
				  <spring:url value="/marcacaos/${marcacao.id}/update" var="updateUrl" />
				  <spring:url value="/marcacaos/${marcacao.id}/update" var="replicarUrl" />

				  <button class="btn btn-info" 
                                          onclick="location.href='${marcacaoUrl}'">Detalhes</button>
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
	
	<script src="../resources/js/jquery.min.js"  type="text/javascript"></script>
	<script src="../resources/js/popper.min.js"  type="text/javascript"></script>
	<script src="../resources/js/bootstrap.js"  type="text/javascript"></script>
	</body>
	</html>
