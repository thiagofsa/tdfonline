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
                 uri.endsWith("efetualogin") || 
                         uri.contains("resources")){
             return true;
		 	}
		 
		 	Usuario usuario= (Usuario) request.getSession().getAttribute("usuarioLogado");
		 	
		 	if( usuario != null) {
		 	
		 		if (uri.contains("usuarios")) {
		 			
		 			if (usuario.getAdmin()>0) {
		 				System.out.println("Usuario adm acessando o modulo Usuarios...");
		 				return true;
		 			}
		 			else {
		 				System.out.println("Usuario nãoAdmin acessando o modulo Usuarios...acesso negado!");
		 				request.setAttribute("msg", "Usuário sem permissão para esta opepração (Módulo Usuário");
		 				response.sendRedirect("usuariobloqueado");
		 				return false;
		 			}
		 		}
		 		
		 		return true;		
		 	}
		 	
		 	
		 
	        response.sendRedirect("loginform");
	        return false;    
	    }
	 
	 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
