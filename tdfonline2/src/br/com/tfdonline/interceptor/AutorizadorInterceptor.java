package br.com.tfdonline.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.tfdonline.dao.EncaminhamentoDAOI;
import br.com.tfdonline.dao.EncaminhamentoVoltaDAOI;
import br.com.tfdonline.dao.MarcacaoDAOI;
import br.com.tfdonline.dao.UsuarioDAOI;
import br.com.tfdonline.modelo.Usuario;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter{

	
	
	
	@Autowired	 
	 EncaminhamentoDAOI encaminhamentoDAO ;
	
	@Autowired	 
	 EncaminhamentoVoltaDAOI encaminhamentovoltaDAO ;
	
	@Autowired	 
	 MarcacaoDAOI marcacaoDAO ;
	
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
		 	
		 		//MODULOS MOTORISTA E VEICULO NAO PODEM SER ACESSADOS POR MEDICOS
		 		
		 		if ((usuario.getPerfilusuario()==UsuarioDAOI.PERFIL_ADMIN) || (usuario.getPerfilusuario()==UsuarioDAOI.PERFIL_USUARIO) || 
		 		(usuario.getPerfilusuario()==UsuarioDAOI.PERFIL_MOTORISTA)|| (usuario.getPerfilusuario()==UsuarioDAOI.PERFIL_TRANSPORTE)) {
		 			if (uri.contains("veiculo")|| uri.contains("distribuicao") || (uri.contains("motorista"))) {
		 			return true;
		 			}
		 		}
		 		
		 		
		 		if (uri.contains("requisicao")) {
		 			
		 			System.out.println(" modulo Medicos.....");
		 			
		 			//MODULO MEDICO SO PODE SER ACESSADO POR ADMIN,USUARIO, MEDICO 
		 			if ((usuario.getPerfilusuario()==UsuarioDAOI.PERFIL_USUARIO) ||(usuario.getPerfilusuario()==UsuarioDAOI.PERFIL_MEDICO) || (usuario.getPerfilusuario()==UsuarioDAOI.PERFIL_ADMIN)) {
		 				System.out.println("Usuario MEDICO ou ADMIN acessando o modulo REQUISICAO...");
		 				return true;
		 			}
		 				
		 		}
		 		
		 		if (uri.contains("usuarios")) {
		 			
		 			System.out.println(" modulo Usuarios.....");
		 			
		 			//MODULO USUARIO SO PODE SER ACESSADO POR ADMIN
		 			if (usuario.getPerfilusuario()==UsuarioDAOI.PERFIL_ADMIN) {
		 				System.out.println("Usuario ADM acessando o modulo Usuarios...");
		 				return true;
		 			}
		 				
		 		}
		 		
		 		//PAINEL DE GERENCIAMENTO
		 		if (uri.contains("home")){
		 			
		 			System.out.println("passei no Logincontroller, anest homepage........");
		 			
		 			if ((usuario.getPerfilusuario()==UsuarioDAOI.PERFIL_ADMIN) || (usuario.getPerfilusuario()==UsuarioDAOI.PERFIL_USUARIO)) {
		 			
		 				Long contadorEncaminhamentos = encaminhamentoDAO.findbyContadorEncaminhamentosDaData(new Date());
		 				Long contadorEmbarquesEncaminhamentos = encaminhamentoDAO.findbyContadorEmbarquesEncaminhamentosDaData(new Date());
		 				
		 				request.setAttribute("contadorEncaminhamentos", contadorEncaminhamentos);
		 				request.setAttribute("contadorEmbarquesEncaminhamentos", contadorEmbarquesEncaminhamentos);
		 				
		 			}
		 			
		 		}
		 				

		 		 			 		
		 		//PARA OUTRAS OPERACOES, EXCETO MEDICOS
		 		if (uri.contains("acompanhante") || uri.contains("beneficio") 
		 				|| uri.contains("distribuicao")
		 				|| uri.contains("encaminhamento") 
		 				|| uri.contains("marcacao")
		 				|| uri.contains("paciente")
		 				|| uri.contains("pauta")
		 				|| uri.contains("requisicao")
		 				|| uri.contains("procedimento")
		 				|| uri.contains("unidadesaude")
		 				|| uri.contains("/")
		 				|| uri.contains("home")
		 				){
		 			
		 			System.out.println("Casos de acesso para ADMIN e USUARIO REGULAR, APENAS");

		 			if ((usuario.getPerfilusuario()==UsuarioDAOI.PERFIL_ADMIN) || (usuario.getPerfilusuario()==UsuarioDAOI.PERFIL_USUARIO)) {
			 								 						 				
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
