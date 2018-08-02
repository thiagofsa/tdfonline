<%@page import="br.com.tfdonline.modelo.Usuario"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- <link rel="stylesheet" href= "<c:url value='/resources/css/menu.css'/>" >   -->


<div class="fixed-top"> <!-- CONTAINER -->

	<div class="row p-2 hidec" style="background-color: #fff"><!-- HEADER DA PAGINA -->
		<div class="col-sm-2 d-flex justify-content-start " >
			<a class="" href="${pageContext.request.contextPath}/home"><img src="<c:url value="/resources/images/logomarca.png"/>"></a>		
		</div>
		
		<div class="col-sm-8 d-flex align-items-center justify-content-center pt-1" >
			 <p  class="text-uppercase font-weight-bold h4">Sistema para Gestão do T.F.D</p>
		</div>
		
		<div class="col-sm-2 d-flex justify-content-end" >		
			<a class="" target="_blank"  href="http://www.garanhuns.pe.gov.br/"><img src="<c:url value="/resources/images/Guslogo.png" />" ></a>			
		</div>	
	</div><!-- FECHA HEADER DA PAGINA -->	
	
	<nav class="navbar navbar-expand-md navbar-dark bg-dark shadow">  
		  
		  <a class="navbar-brand ml-2 font-italic font-weight-bold hideb" href="${pageContext.request.contextPath}/home">T.F.D Control</a>
		  
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  
		  <div class="collapse navbar-collapse" id="navbarNavDropdown">
		  
		      <c:if test = "${usuarioLogado.transporte < 1}">
	      	  <ul class="navbar-nav mr-auto ">      	  	
	       
			      <li class="nav-item dropdown"> 
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Cadastros
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">        
			          
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/acompanhantes/"><i class='fas fa-users mr-2'></i> Acompanhantes</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/medicos/"><i class="fas fa-user-md mr-2"></i> Médicos</a>			                   
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/motoristas/"><i class="fas fa-shipping-fast mr-2"></i> Motoristas</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/pacientes/"><i class="fas fa-procedures mr-2"></i> Pacientes</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/procedimentos/"><i class="fas fa-briefcase-medical mr-2"></i> Procedimentos Médico</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/secretarios/"><i class="fas fa-user-tie mr-2"></i> Secretário</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/unidadesaudes/"><i class="fas fa-hospital mr-2"></i> Unidades de Saúde</a>
			          <%Usuario user = (Usuario) session.getAttribute("usuarioLogado");          
			          	if (user.getAdmin()>0){        	
			          	out.println("<a class=dropdown-item href="+request.getContextPath()          	
			          			+ "/usuarios/" + "><i class='fas fa-users-cog mr-2'></i> Usuários</a>");}%>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/veiculos/"><i class="fas fa-ambulance mr-2"></i> Veículos</a>          
			          <div class="dropdown-divider"></div>
				      <a class="dropdown-item" href="#"><i class="fas fa-user-cog mr-2"></i> Minha Conta</a>			               
			        </div>
			      </li>  
					  
			      <li class="nav-item dropdown">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Transações
			        </a>       
			        
			        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/requisicaos/"><i class="fas fa-file-medical mr-2"></i> Requisições</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/marcacaos/"><i class="fas fa-notes-medical mr-2"></i> Marcações</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/distribuicaos/"><i class="fas fa-bus mr-2"></i> Viagens</a>
			          <div class="dropdown-divider"></div>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/beneficios/add">Novo Beneficio - De Encam.</a>
			          <a class="dropdown-item" href="#">Listar Beneficios</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/beneficioavulsos/add">Novo - Beneficio Avulso</a>
			          <div class="dropdown-divider"></div>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentos/">Viagem de Ida - Listar</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentos/add">De ida - Novo</a>                    
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentos/find">De ida - Pesquisar</a>   
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentos/lote">De Ida - Em Lote</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentovoltas/">Retorno - Listar</a>      
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentovoltas/add">De volta - Avulso</a>          
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentovoltas/lote0">De volta - Em Lote</a>
			          
			          
			          
			        </div>
			      </li>   
			     
			      
			      <li class="nav-item dropdown">
				        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				          Sistema
				        </a>
				        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
				        
				          <a class="dropdown-item" href="${pageContext.request.contextPath}/logtransacaos/find">Consulta Log </a>
				          <a class="dropdown-item" href="#"><i class="fas fa-cog mr-2"></i> Configurações</a>
				          <%
				          if (user.getAdmin()>0){        	
				        	  out.println("<a class=dropdown-item href="+request.getContextPath()        	
					  			+ "/transacaos/" + "><i class=''></i> Transações</a>"
					  			);
				        	  out.println("<a class=dropdown-item href="+request.getContextPath()        	
					  			+ "/####/" + "><i class=''></i> Logs</a>"
					  			);
						      }				          
				          %>				                    
				        </div>
			      	</li>
	      
			      <li class="nav-item dropdown">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Relatórios
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/reports/encaminhamentopormotoristaedatareport">Pacientes</a>
			          <a class="dropdown-item" href="#">Cartão de Identificação de Paciente</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/reports/encaminhamentopormotoristaedatareport">Pauta Motorista</a>			        
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/reports/beneficiosporperiodoreport">Fatura de Benefícios</a>
          			  <a class="dropdown-item" href="${pageContext.request.contextPath}/reports/registrosmssreport">SMSs enviados</a>
			        </div>
			      </li>
	      
			      <li class="nav-item dropdown">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Ajuda
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
			          <a class="dropdown-item" href="#"><i class="fas fa-info-circle mr-2"></i> Manual</a>
			          <a class="dropdown-item" href="#"><i class="fas fa-phone-square mr-2"></i> Fale Conosco</a>
			          <a class="dropdown-item" href="#">Sobre</a>          
			        </div>
			      </li>      
			      
			      <li class="nav-item dropdown hideb">
			        <a class="nav-link"  href="${pageContext.request.contextPath}/logout">
			          Sair
			        </a>        
			      </li>
			      
			  </ul>			  
			  
			  <nav class="navbar  borderbrink mx-1 px-2">	  
				  <div class="collapse navbar-collapse" id="navbarNav">
				    <ul class="navbar-nav ">
				      <li class="nav-item">
				        <a class="nav-link" href="#"><i class="fas fa-medkit fa-2x"></i></a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="#"><i class="fas fa-print fa-2x"></i></a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link " href="#"><i class="fas fa-cog fa-2x"></i></a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="#"><i class="fas fa-bus fa-2x"></i></a>
				      </li>	   
					  <li class="nav-item">
				        <a class="nav-link" href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt fa-2x"></i></a>
				      </li>
				    </ul>
				  </div>
				</nav>	  
			   	      	
	      	</c:if>
	      
	      	<c:if test = "${usuarioLogado.transporte > 0}"> <!-- ABRE MENU TRANSPORTE -->	 
				 <ul class="navbar-nav mr-auto ">
					<li class="nav-item dropdown">    
			       		 <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          		Cadastros
			        	</a>
			        	<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">	        
			          		<a class="dropdown-item" href="${pageContext.request.contextPath}/motoristas/">Motorista</a>
			          		<a class="dropdown-item" href="${pageContext.request.contextPath}/veiculos/">Veiculo</a>
			          		<a class="dropdown-item" href="${pageContext.request.contextPath}/distribuicaos/">Viagens</a>
			          		<div class="dropdown-divider"></div>
				      		<a class="dropdown-item" href="#"><i class="fas fa-user-tie mr-2"></i> Minha Conta</a>    
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
				      
				    <li class="nav-item dropdown">
				       <a class="nav-link"  href="${pageContext.request.contextPath}/logout">
				          Sair
				       </a>        
				    </li> 
			    </ul>  
	     	</c:if> <!-- FECHA MENU TRANSPORTE -->	
	     	      
	    </div>	<!-- FECHA COLAPSE -->		  		
	</nav>
</div><!-- FECHA CONTAINER -->


 	<!-- <div class="nav-item dropdown mr-4">  	
	  	<a class="nav-link dropdown-toggle acertonav" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Olá, ${usuarioLogado.nome}
	  	</a>
	  	<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
	    	<a class="dropdown-item" href="#"><i class="fas fa-user mr-2"></i> Minha Conta</a>
	    	<a class="dropdown-item" href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt mr-2"></i> Sair</a>    	
	  	</div>
	</div> -->
	
	
	 <!-- <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          Encaminhamentos
		        </a>
		        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
		        	<a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentos/">Viagem de Ida - Listar</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentos/add">De ida - Novo</a>                    
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentos/find">De ida - Pesquisar</a>   
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentos/lote">De Ida - Em Lote</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentovoltas/">Retorno - Listar</a>      
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentovoltas/add">De volta - Avulso</a>          
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentovoltas/lote0">De volta - Em Lote</a>
		        </div>        
		      </li> 
		      
		      <li class="nav-item dropdown">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Benefício
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/beneficios/add">Novo Beneficio - De Encam.</a>
			          <a class="dropdown-item" href="#">Listar Beneficios</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/beneficioavulsos/add">Novo - Beneficio Avulso</a>          
			        </div>
			      </li> --> 
			      
		 
			      
			      
	   
