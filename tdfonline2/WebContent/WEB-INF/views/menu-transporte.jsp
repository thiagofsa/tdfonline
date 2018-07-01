<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- <link rel="stylesheet" href= "<c:url value='/resources/css/menu.css'/>" >   -->

<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top ">
  
  <a class="navbar-brand" href="${pageContext.request.contextPath}/home"><i class="fas fa-home fa-lg"></i></a>
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    
    <ul class="navbar-nav mr-auto">
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Cadastros
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="${pageContext.request.contextPath}/veiculos">Veiculos</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/motoristas">Motoristas</a>
    
          
          <!-- <div class="dropdown-divider"></div> -->
        </div>
      </li>
      
           
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Distribuição
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="${pageContext.request.contextPath}/distribuicaos/add">Novo</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/distribuicaos">Listar</a>          
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Relatórios
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="#">Distribuições</a>           
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
   
   <div class="dropdown">
  	
  	<a class="nav-link dropdown-toggle acertonav" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Olá, ${usuarioLogado.nome}
  	</a>

  	<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
    	<a class="dropdown-item" href="#"><i class="fas fa-user"></i> Minha Conta</a>
    	<a class="dropdown-item" href="${pageContext.request.contextPath}/logout"><i class="fas fa-window-close"></i> Sair</a>
    	
  	</div>
	</div>
  </div>
</nav>
