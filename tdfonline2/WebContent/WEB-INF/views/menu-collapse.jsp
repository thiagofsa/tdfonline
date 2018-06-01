<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
  <meta name="viewport" content="width=device-width">
   <link href="<c:url value="/resources/css/menu-collapse.css" />" rel="stylesheet">
   
</head>
<div class="wrap">
<span class="decor"></span>
<nav>
  <ul class="primary">
    <li>
     <a href="${pageContext.request.contextPath}/home">Início</a>
    </li>
    
    
    <li>
      <a href="">Motorista</a>
      <ul class="sub">
        <li><a href="${pageContext.request.contextPath}/motoristas/add">Cadastrar Motorista</a></li>
        <li><a href="${pageContext.request.contextPath}/motoristas/find">Pesquisar Motorista</a></li>
        <li><a href="${pageContext.request.contextPath}/motoristas">Listar Motoristas</a></li>        
      </ul>  
    </li>
    
    <li>
      <a href="">Un Saude</a>
      <ul class="sub">
        <li><a href="${pageContext.request.contextPath}/unidadesaudes/add">Cadastrar Unid Saude</a></li>
        <li><a href="${pageContext.request.contextPath}/unidadesaudes/find">Pesquisar Unid Saude</a></li>
        <li><a href="${pageContext.request.contextPath}/unidadesaudes">Listar Unid Saude</a></li>        
      </ul>  
    </li>
    
    
  </ul>
</nav>
</div>