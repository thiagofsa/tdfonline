<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href= "<c:url value='/resources/css/menu.css'/>" >



<nav class="navbar navbar-expand-md navbar-dark bg-dark">
  
  <a class="navbar-brand" href="${pageContext.request.contextPath}/home"><i class="fas fa-pallet fa-lg"></i></a>
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    
    <ul class="navbar-nav">
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Cadastros
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="${pageContext.request.contextPath}/paciente/">Pacientes</a>
          <a class="dropdown-item" href="#">Acompanhantes</a>
          <a class="dropdown-item" href="#">Procedimento Médico</a>
          <a class="dropdown-item" href="#">Unidades de Saúde</a>
          <a class="dropdown-item" href="#">Beneficiários de Diárias</a>
          <a class="dropdown-item" href="#">Veiculos</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/motoristas">Motoristas</a>
          
          <!-- <div class="dropdown-divider"></div> -->
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Marcações
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="${pageContext.request.contextPath}/marcacaos/add">Nova</a>                    
          <a class="dropdown-item" href="${pageContext.request.contextPath}/marcacaos/find">Pesquisar</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/marcacaos">Listar</a>
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Encaminhamentos
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="#">Novo</a>
          <a class="dropdown-item" href="#">Listar</a>          
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Distribuição
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="#">Novo</a>
          <a class="dropdown-item" href="#">Listar</a>          
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Relatórios
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="#">Pacientes</a>
          <a class="dropdown-item" href="#">Marcações</a>
          <a class="dropdown-item" href="#">Encaminhamentos</a>
          <a class="dropdown-item" href="#">Pautas</a>          
        </div>
      </li>
      
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Ajuda
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="#">Manual</a>
          <a class="dropdown-item" href="#">Fale Conosco</a>
          <a class="dropdown-item" href="#">Sobre</a>          
        </div>
      </li> 
      
      
    </ul>
  </div>
</nav>
