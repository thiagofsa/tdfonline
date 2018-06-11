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
    
    <li>
      <a href="">Acompanhante</a>
      <ul class="sub">
        <li><a href="${pageContext.request.contextPath}/acompanhantes/add">Cadastrar Acompanhante</a></li>
        <li><a href="${pageContext.request.contextPath}/acompanhantes/find">Pesquisar Acompanhante</a></li>
        <li><a href="${pageContext.request.contextPath}/acompanhantes">Listar Acompanhantes</a></li>        
      </ul>  
    </li>
    
    <li>
      <a href="">Beneficio</a>
      <ul class="sub">
        <li><a href="${pageContext.request.contextPath}/beneficios/add">Cadastrar Beneficio</a></li>
        <li><a href="${pageContext.request.contextPath}/beneficios/find">Pesquisar Beneficio</a></li>
        <li><a href="${pageContext.request.contextPath}/beneficios">Listar Beneficios</a></li>        
      </ul>  
    </li>
    
    <li>
      <a href="">Procedimento</a>
      <ul class="sub">
        <li><a href="${pageContext.request.contextPath}/procedimentos/add">Cadastrar Procedimento</a></li>
        <li><a href="${pageContext.request.contextPath}/procedimentos/find">Pesquisar Procedimento</a></li>
        <li><a href="${pageContext.request.contextPath}/procedimentos">Listar Procedimentos</a></li>        
      </ul>  
    </li>
    
    <li>
      <a href="">Marcacao</a>
      <ul class="sub">
        <li><a href="${pageContext.request.contextPath}/marcacaos/add">Cadastrar Marcacao</a></li>
        <li><a href="${pageContext.request.contextPath}/marcacaos/find">Pesquisar Marcacao</a></li>
        <li><a href="${pageContext.request.contextPath}/marcacaos">Listar Marcacaos</a></li>        
      </ul>  
    </li>
    
    <li>
      <a href="">Distribuicao</a>
      <ul class="sub">
        <li><a href="${pageContext.request.contextPath}/distribuicaos/add">Cadastrar Distribuicao</a></li>
        <li><a href="${pageContext.request.contextPath}/distribuicaos/find">Pesquisar Distribuicao</a></li>
        <li><a href="${pageContext.request.contextPath}/distribuicaos">Listar Distribuicaos</a></li>        
      </ul>  
    </li>
    
    <li>
      <a href="">Veiculo</a>
      <ul class="sub">
        <li><a href="${pageContext.request.contextPath}/veiculos/add">Cadastrar Veiculo</a></li>
        <li><a href="${pageContext.request.contextPath}/veiculos/find">Pesquisar Veiculo</a></li>
        <li><a href="${pageContext.request.contextPath}/veiculos">Listar Veiculos</a></li>        
      </ul>  
    </li>
    
    
    
     <li>
      <a href="">Pauta</a>
      <ul class="sub">
        <li><a href="${pageContext.request.contextPath}/pautas/add">Cadastrar Pauta</a></li>
        <li><a href="${pageContext.request.contextPath}/pautas/find">Pesquisar Pauta</a></li>
        <li><a href="${pageContext.request.contextPath}/pautas">Listar Pautas</a></li>        
      </ul>  
    </li>
    
    <li>
      <a href="">Encaminhamento</a>
      <ul class="sub">
        <li><a href="${pageContext.request.contextPath}/encaminhamentos/add">Cadastrar Encaminhamento</a></li>
        <li><a href="${pageContext.request.contextPath}/encaminhamentos/find">Pesquisar Encaminhamento</a></li>
        <li><a href="${pageContext.request.contextPath}/encaminhamentos">Listar Encaminhamentos</a></li>        
      </ul>  
    </li>
    
    
    
  </ul>
</nav>
</div>