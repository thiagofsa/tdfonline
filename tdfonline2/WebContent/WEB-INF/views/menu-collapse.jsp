<%@page import="br.com.tfdonline.modelo.Usuario"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
  
  <a class="navbar-brand ml-2" href="${pageContext.request.contextPath}/home">LOGOMARCA</a>
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    
      <ul class="navbar-nav mr-auto ">
       
      <li class="nav-item dropdown">    
    
    <c:if test = "${usuarioLogado.transporte < 1}">
    
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Cadastros
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">        
     
          <!-- <a class="dropdown-item" href="#"><i class="fas fa-male"></i> Beneficiários de Diárias</a> -->
          <a class="dropdown-item" href="${pageContext.request.contextPath}/pacientes/"><i class="fas fa-procedures mr-2"></i> Pacientes</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/acompanhantes/"> <i class="fas fa-user-friends mr-2"></i> Acompanhantes</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/procedimentos/"><i class="fas fa-stethoscope mr-2"></i> Procedimentos Médico</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/unidadesaudes/"><i class="fas fa-hospital mr-2"></i> Unidades de Saúde</a>         
          <a class="dropdown-item" href="${pageContext.request.contextPath}/motoristas/"><i class="fas fa-shipping-fast mr-2"></i> Motoristas</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/veiculos/"><i class="fas fa-ambulance mr-2"></i> Veiculos</a>
          
          <%
          Usuario user = (Usuario) session.getAttribute("usuarioLogado");
          
          if (user.getAdmin()>0){
        	
          	out.println("<a class=dropdown-item href="+request.getContextPath()
          	
          			+ "/usuarios/" + "><i class='fas fa-users mr-2'></i> Usuarios</a>"
          			); 
	      }
          
          %>         
         
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
          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentos/add">De ida - Novo</a>                    
          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentos/find">De ida - Pesquisar</a>   
          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentos/lote">De Ida - Em Lote</a>       
          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentovoltas/add">De volta - Avulso</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentovoltas/">De volta - Listar</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentovoltas/lote0">De volta - Em Lote</a>
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
      
	</c:if>      
      
      <c:if test = "${usuarioLogado.transporte > 0}">
    
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Cadastros
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
        
          <a class="dropdown-item" href="${pageContext.request.contextPath}/motoristas/">Motorista</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/veiculos/">Veiculo</a>
    
    	</div>
    	
    	<li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Distribuição
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="#">Novo</a>
          <a class="dropdown-item" href="#">Listar</a>          
        </div>
      </li>  
    
    </c:if>
      
      
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
   
   <div class="dropdown mr-4">  	
  	<a class="nav-link dropdown-toggle acertonav" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Olá, ${usuarioLogado.nome}
  	</a>
  	<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
    	<a class="dropdown-item" href="#"><i class="fas fa-user"></i> Minha Conta</a>
    	<a class="dropdown-item" href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt"></i> Sair</a>    	
  	</div>
	</div>
  </div>
</nav>


