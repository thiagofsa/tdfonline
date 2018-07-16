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
    <link rel="stylesheet" href= "<c:url value='/resources/css/bootstrap.css'/>" >  
    <link rel="stylesheet" href= "<c:url value='/resources/css/fontawesome.css'/>" >    
  </head>  
<body>

	<div class="container">

		<c:if test="${not empty msg}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" 
                                aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		    </div>
		</c:if>
		
		<div  class="spacesup"></div>	
		<div class="titulo"  >
			<h4>Selecione o acompanhante</h4>
		</div>
		
		
	</div>
		
<spring:url value="/selectacompanhante/beneficioavulso2/" var="acompanhanteUrl" />

<form class="form-horizontal"  action="${acompanhanteUrl}">
                
<table>
  
   <c:forEach items="${acompanhantespaciente}" var="acompanhante" varStatus="status">
  <tr>
   
        <td>  <input type="checkbox" id="idsacompanhantepacientebeneficioavulso" name="idsacompanhantepacientebeneficioavulso" value="${acompanhante.id}"/></td> 
        <td><c:out value="${acompanhante.id}"/> </td>
        <td><c:out value="${acompanhante.nome}"/> </td>
         
     </tr>
   </c:forEach>
   
  
</table> 
  
  <div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			
			     <button type="submit" class="btn-lg btn-primary pull-right" >Enviar
                             </button>
			
		  </div>
	</div>
  
</form>



<div  class="spaceabaixo"></div>	

	
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>	
</body>
</html>