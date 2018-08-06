<%@page import="br.com.tfdonline.dao.UsuarioDAOI"%>
<%@page import="br.com.tfdonline.modelo.Usuario"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<div  class="spacesup"></div>
	<div class="row justify-content-center" >
		 <h4 class="h4 text-uppercase">Informações Gerenciais</h4>
	</div>	
	
	<%


	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

	if ((usuario.getPerfilusuario()==UsuarioDAOI.PERFIL_ADMIN) || (usuario.getPerfilusuario()==UsuarioDAOI.PERFIL_USUARIO)) {

		out.print("<p>Encaminhamentos do dia "+ request.getAttribute("contadorEncaminhamentos"));
		out.print("<p>Embarques confirmados do dia "+ request.getAttribute("contadorEmbarquesEncaminhamentos"));
		
	}
	//......outros....
	
%>

</div>        
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
</body>
</html>
 