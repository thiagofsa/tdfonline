<%@page import="br.com.tfdonline.modelo.Usuario"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- <link rel="stylesheet" href= "<c:url value='/resources/css/menu.css'/>" >   -->


<div id="menutop" class="fixed-top"> <!-- CONTAINER -->

	
	
	<div class="row p-2 hidec" style="background-color: #fff"><!-- HEADER DA PAGINA -->
		<div class="col-sm-2 d-flex justify-content-start " >
			<a class="" href="${pageContext.request.contextPath}/home"><img src="<c:url value="/resources/images/logomarca.png"/>"></a>		
		</div>
		
		<div class="col-sm-8 d-flex align-items-center justify-content-center pt-1" >
			 <h3 class="text-uppercase font-weight-bold h3">Sistema para Gestão do T.F.D</h3>
		</div>
		
		<div class="col-sm-2 d-flex justify-content-end" >		
			<a class="" target="_blank"  href="http://www.garanhuns.pe.gov.br/"><img src="<c:url value="/resources/images/Guslogo.png" />" ></a>			
		</div>	
	</div><!-- FECHA HEADER DA PAGINA -->	
	
	
	
	
	
	<nav class="navbar navbar-dark bg-dark navbar-expand-md shadow">  
		  
		  <a class="navbar-brand ml-2 font-italic font-weight-bold hideb" href="${pageContext.request.contextPath}/home"><img src="<c:url value="/resources/images/logomobile.png"/>"></a>
		  
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
			          
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/acompanhantes/"><span class="iconajst"><i class='fas fa-users'></i></span> Acompanhantes</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/medicos/"><span class="iconajst"><i class="fas fa-user-md"></i></span> Médicos</a>			                   
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/motoristas/"><span class="iconajst"><i class="fas fa-shipping-fast"></i></span> Motoristas</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/pacientes/"><span class="iconajst"><i class="fas fa-procedures"></i></span> Pacientes</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/procedimentos/"><span class="iconajst"><i class="fas fa-briefcase-medical"></i></span> Procedimentos Médico</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/secretarios/"><span class="iconajst"><i class="fas fa-user-tie"></i></span> Secretário</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/unidadesaudes/"><span class="iconajst"><i class="fas fa-hospital"></i></span> Unidades de Saúde</a>
			          <%Usuario user = (Usuario) session.getAttribute("usuarioLogado");          
			          	if (user.getAdmin()>0){        	
			          	out.println("<a class=dropdown-item href="+request.getContextPath()          	
			          			+ "/usuarios/" + "><span class='iconajst'><i class='fas fa-users-cog'></i></span> Usuários</a>");}%>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/veiculos/"><span class="iconajst"><i class="fas fa-ambulance"></i></span> Veículos</a>				      			               
			        </div>
			      </li>  
					  
			      <li class="nav-item dropdown">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Serviços
			        </a>       
			        
			        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/requisicaos/"><span class="iconajst"><i class="fas fa-file-medical"></i></span> Requisições</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/marcacaos/"><span class="iconajst"><i class="far fa-clock"></i></span> Agendamentos</a>
			          
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentos/"><span class="iconajst"><i class="fas fa-share-square"></i></span> Encaminhamentos de Ida</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentovoltas/"><span class="iconajst"><i class="fas fa-share-square fa-flip-horizontal "></i></span> Encaminhamentos de Volta</a>
			          
			          
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/distribuicaos/"><span class="iconajst"><i class="fas fa-bus"></i></span> Viagens</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/beneficios/"><span class="iconajst"><i class="fas fa-donate"></i></span> Benefícios</a>

			          <!-- <a class="dropdown-item" href="${pageContext.request.contextPath}/beneficios/add">Novo Beneficio - De Encam.</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/beneficios/">Listar Beneficios</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/beneficioavulsos/add">Novo - Beneficio Avulso</a> 
			          <div class="dropdown-divider"></div>
			          
			          
			          
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentos/">Viagem de Ida - Listar</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentos/add">De ida - Novo</a>                    
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentos/find">De ida - Pesquisar</a>   
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentos/lote">De Ida - Em Lote</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentovoltas/">Retorno - Listar</a>      
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentovoltas/add">De volta - Avulso</a>          
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentovoltas/lote0">De volta - Em Lote</a>	-->		          
			        </div>
			      </li>   
			     
			      
			      <li class="nav-item dropdown">
				        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				          Sistema
				        </a>
				        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
				          <a class="dropdown-item" href="#"><span class="iconajst"><i class="fas fa-cog mr-2"></i></span> Configurações</a>
				          <%
				          if (user.getAdmin()>0){        	
				        	  out.println("<a class=dropdown-item href="+request.getContextPath()        	
					  			+ "/transacaos/" + "><span class='iconajst'><i class='fab fa-searchengin'></i></span> Auditoria</a>"
					  			);
				        	  out.println("<a class=dropdown-item href="+request.getContextPath()        	
					  			+ "/logtransacaos/" + "><span class='iconajst'><i class='fas fa-bolt'></i></span> Logs de Transações</a>"
					  			);
						      }%>
						   <a class="dropdown-item" href="${pageContext.request.contextPath}/usuarios/minhaconta"><span class="iconajst"><i class="fas fa-user-cog"></i></span> Minha Conta</a>   				                    
				        </div>
			      	</li>
	      
			      <li class="nav-item dropdown">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Relatórios
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/reports/encaminhamentopormotoristaedatareport"><span class="iconajst"><i class="far fa-id-card"></i></span> Pacientes</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/report/cartaopaciente/"><span class="iconajst"><i class="fas fa-id-card-alt"></i></span> Cartão do Paciente</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/encaminhamentos/cartaodeembarque"><span class="iconajst"><i class="fas fa-id-card-alt"></i></span> Cartão de Embarque</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/marcacaos/comprovantemarcacaoreport"><span class="iconajst"><i class="fas fa-calendar-check"></i></span> Comprovante de Agendamento</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/reports/encaminhamentopormotoristaedatareport"><span class="iconajst"><i class="fas fa-bus"></i></span> Pauta por Motorista</a>			        
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/reports/beneficiosporperiodoreport"><span class="iconajst"><i class="fas fa-donate"></i></span> Fatura de Benefícios</a>
          			  <a class="dropdown-item" href="${pageContext.request.contextPath}/reports/registrosmssreport"><span class="iconajst"><i class="fas fa-envelope-square"></i></span> SMSs Enviados</a>
			        </div>
			      </li>
	      
			      <li class="nav-item dropdown">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Ajuda
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
			          <a class="dropdown-item" href="#"><span class="iconajst"><i class="fas fa-info-circle"></i></span> Manual</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/contato/"><span class="iconajst"><i class="fas fa-phone-square"></i></span> Suporte</a>
			          <a class="dropdown-item" href="#"><span class="iconajst"><i class="fas fa-copyright"></i></span> Sobre</a>          
			        </div>
			      </li>      
			      			      
			      <li class="nav-item dropdown hideb">
			        <a class="nav-link"  href="${pageContext.request.contextPath}/logout">
			          Sair
			        </a>        
			      </li>			      
			  </ul>			  
			  
			  <nav class="navbar  borderbrink px-1">	  
				  <div class="collapse navbar-collapse" id="navbarNav">
				    <ul class="navbar-nav ">
				      <li class="nav-item">
				        <a class="nav-link" data-toggle="tooltip" data-placement="botton" title="Agendamento" href="${pageContext.request.contextPath}/marcacaos/"><i class="far fa-clock fa-2x"></i></a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" data-toggle="tooltip" data-placement="botton" title="Encaminhamentos" href="${pageContext.request.contextPath}/encaminhamentos/"><i class="fas fa-share-square fa-2x"></i></a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" data-toggle="tooltip" data-placement="botton" title="Configurações" href="#"><i class="fas fa-cog fa-2x"></i></a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" data-toggle="tooltip" data-placement="botton" title="Minha conta" href="${pageContext.request.contextPath}/usuarios/minhaconta"  ><i class="fas fa-user-cog fa-2x"></i></a>
				      </li>	   
					  <li class="nav-item">
				        <a class="nav-link"  data-toggle="tooltip" data-placement="botton" title="Sair"  href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt fa-2x"></i></a>
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
			          		<a class="dropdown-item" href="${pageContext.request.contextPath}/motoristas/"><span class="iconajst"><i class="fas fa-shipping-fast"></i></span> Motoristas</a>
			          		<a class="dropdown-item" href="${pageContext.request.contextPath}/veiculos/"><span class="iconajst"><i class="fas fa-ambulance"></i></span> Veículos</a>
			          		<a class="dropdown-item" href="${pageContext.request.contextPath}/distribuicaos/"><span class="iconajst"><i class="fas fa-bus mr-2"></i></span> Viagens</a>
			          		<a class="dropdown-item" href=""><span class="iconajst"><i class="fas fa-user-cog"></i></span> Minha Conta</a>
			        	</div> 
		    		</li>
		    	
		    		<li class="nav-item dropdown">
				        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				          Ajuda
				        </a>
				        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
				           <a class="dropdown-item" href="#"><span class="iconajst"><i class="fas fa-info-circle mr-2"></i></span> Manual</a>
			          <a class="dropdown-item" href="${pageContext.request.contextPath}/contato/"><span class="iconajst"><i class="fas fa-phone-square mr-2"></i></span> Suporte</a>
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
			      
		 
			      
			      
	   
