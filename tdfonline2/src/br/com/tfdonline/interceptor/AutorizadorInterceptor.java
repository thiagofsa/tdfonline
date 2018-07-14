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
		 	
		 		
		 		if (uri.contains("veiculo") || (uri.contains("motorista"))) {
		 			return true;
		 		}
		 		
		 		
		 		if (uri.contains("usuarios")) {
		 			
		 			System.out.println(" modulo Usuarios.....");
		 			
		 			if (usuario.getAdmin()>0) {
		 				System.out.println("Usuario adm acessando o modulo Usuarios...");
		 				return true;
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
		 				|| uri.contains("home")
		 				|| uri.contains("/")
		 				){
		 			
		 			System.out.println("Caso geral, exceto transporte.....");

			 			if ((usuario.getAdmin()>0) || (usuario.getTransporte()<1)){		 				
			 				return true;
			 		
			 			}else {
			 				request.setAttribute("msg", "Usu�rio sem permiss�o para esta opera��o");
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
