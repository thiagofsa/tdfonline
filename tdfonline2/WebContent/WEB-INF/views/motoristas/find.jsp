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
 
  <body>


<spring:url value="/motoristas/find2" var="findUrl" />

	<form:form class="form-horizontal" 
                modelAttribute="motoristaForm" action="${findUrl}">

		
		
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Nome</label>
			<div class="col-sm-10">
				<form:input path="nome" type="text" class="form-control" 
                                id="nome" placeholder="Nome" />
				<form:errors path="nome" class="control-label" />
			</div>
		  </div>
		

				
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			
			     <button type="submit" class="btn-lg btn-primary pull-right" >Pesquisar
                             </button>
			
		  </div>
		</div>
	</form:form>
	
	<script src="../resources/js/jquery.min.js"  type="text/javascript"></script>
	<script src="../resources/js/popper.min.js"  type="text/javascript"></script>
	<script src="../resources/js/bootstrap.js"  type="text/javascript"></script>
	</body>
	</html>