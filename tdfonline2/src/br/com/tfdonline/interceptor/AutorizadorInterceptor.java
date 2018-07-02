package br.com.tfdonline.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.tfdonline.modelo.Usuario;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter{

	 @Override
	    public boolean preHandle(HttpServletRequest request,
	            HttpServletResponse response,
	            Object controller) throws Exception {

		 	String uri = request.getRequestURI();
		 	//	 	garantir acesso as paginas de login e resources para todos os usuarios
		 	if(uri.endsWith("loginform") || uri.endsWith("login") ||  
                 uri.endsWith("efetualogin") || uri.contains("logout") ||
                         uri.contains("resources")){
             return true;
		 	}
		 
		 	Usuario usuario= (Usuario) request.getSession().getAttribute("usuarioLogado");
		 	
		 	if( usuario != null) {
		 	
		 		if (uri.contains("usuarios")) {
		 			
		 			System.out.println("Tentando acessar o modulo Usuarios.....");
		 			if (usuario.getAdmin()>0) {
		 				System.out.println("Usuario adm acessando o modulo Usuarios...");
		 				return true;
		 			}
		 			else {
		 				System.out.println("Usuario acessando o modulo Usuarios...acesso negado!");
		 				request.setAttribute("msg", "Usuário sem permissão para esta opepração (Módulo Usuário");
		 				
		 				//se nao for nem transporte nem admin, ele é usuario basico
		 				if ((usuario.getTransporte()<1)) {
		 					request.setAttribute("msg", "Usuário sem permissão para esta operação (Módulo Usuario");
		 					response.sendRedirect("usuariobloqueado");
		 					return true;    
		 				
		 				//ou é do transporte
		 				}else  {
		 					request.setAttribute("msg", "Usuário sem permissão para esta opepração (Módulo Usuario");
		 					response.sendRedirect("usuariobloqueado-transporte");
		 					return true;    
		 				
		 				}
		 				

		 			}
		 			//se estou acessando o modulo transporte
		 		} 
		 			
		 		if ((uri.contains("motoristas")) || (uri.contains("veiculos"))){

		 			System.out.println("tentando acessar o modulo de transportes.....");
		 			
		 			//checar se o usuario logado é de transportes ou admin		 			
		 			if ((usuario.getTransporte()>0) || (usuario.getAdmin()>0)){		 				
		 				return true;

		 			//caso seja um usuario  user comum		
		 			} else {
		 				request.setAttribute("msg", "Usuário sem permissão para esta opepração (Módulo Transporte");
		 				response.sendRedirect("usuariobloqueado");
		 				return false;
		 				
		 			}

			 	}
		 		
		 		if (uri.contains("acompanhante") || uri.contains("beneficio") 
		 				|| uri.contains("distribuicao")
		 				|| uri.contains("encaminhamento") 
		 				|| uri.contains("marcacao")
		 				|| uri.contains("paciente")
		 				|| uri.contains("pauta")
		 				|| uri.contains("procedimento")
		 				|| uri.contains("unidadesaude")
		 				){
		 			
		 			System.out.println("Caso geral.....");

		 			if ((usuario.getAdmin()>0) || (usuario.getTransporte()<1)){		 				
		 				return true;
		 		
		 			}else {
		 				request.setAttribute("msg", "Usuário sem permissão para esta operação");
		 				response.sendRedirect("usuariobloqueado-transporte");
		 				return false;
		 			}
		 		}
		 		
		 	}//usuario logado
		 	
		 	else {
		 	
		 	
		 	response.sendRedirect("loginform");
	        return false;
		 	
		 	}
	 return false;   
	 }
	 
	 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
