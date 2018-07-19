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

<spring:url value="/reports/beneficiosporperiodoreport2" var="findUrl" />
<form action="${findUrl}" method="get">

		
	Data inicial <input type="text" name="datainicial" id="datainicial">
	Data final <input type="text" name="datafinal" id="datafinal">
	
			
		
			
   <button type="submit" class="btn-lg btn-primary pull-right" >Pesquisar
                             </button>
			
		
	</form>
	
	</body>
</html>
