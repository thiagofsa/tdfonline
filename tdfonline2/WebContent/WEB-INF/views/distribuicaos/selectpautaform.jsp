<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html lang="en">


<body>

	<div class="container">

		<c:if test="${not empty msg}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" 
                                aria-label="Close">
				<span aria-hidden="true">�</span>
			</button>
			<strong>${msg}</strong>
		    </div>
		</c:if>
		
<spring:url value="/distribuicaos/selectpauta2" var="selectpautaUrl" />

	<form:form class="form-horizontal" 
                modelAttribute="pauta" action="${selectpautaUrl}">
		
		<spring:bind path="descricao">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Descricao </label>
			<div class="col-sm-10">
				<form:input path="descricao" type="text" class="form-control" 
                                id="descricao" placeholder="Descricao" />
				<form:errors path="descricao" class="control-label" />
			</div>
		  </div>
		</spring:bind>

				
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			
			     <button type="submit" class="btn-lg btn-primary pull-right" >Pesquisar
                             </button>
			
		  </div>
		</div>
	</form:form>
	
	</div>
	
	
	<c:if test="${not empty pautas}">

		<h1>Pautas</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Descricao</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="pauta" items="${pautas}">
			    <tr>
				<td>
					${pauta.id}
				</td>
				>
				<td>${pauta.descricao}</td>
				
				<td>
				  <spring:url value="/distribuicaos/selectpauta/${pauta.id}" var="selectpautaUrl" />
				  	  <button class="btn btn-info" 
                                          onclick="location.href='${selectpautaUrl}'">Selecionar</button>
				  
                </td>
			    </tr>
			</c:forEach>
		</table>
		</c:if>
	
	
	
		
	

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

</body>
</html>