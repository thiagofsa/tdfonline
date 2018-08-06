package br.com.tfdonline.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;

import br.com.tfdonline.dao.EncaminhamentoDAOI;
import br.com.tfdonline.dao.EncaminhamentoVoltaDAOI;
import br.com.tfdonline.dao.MarcacaoDAOI;
import br.com.tfdonline.dao.UsuarioDAOI;
import br.com.tfdonline.modelo.Usuario;

@Controller
public class LoginController {
	
	
	@Autowired
	private UsuarioDAOI usuarioDAO;
	
	@Autowired	 
	 EncaminhamentoDAOI encaminhamentoDAO ;
	
	@Autowired	 
	 EncaminhamentoVoltaDAOI encaminhamentovoltaDAO ;
	
	@Autowired	 
	 MarcacaoDAOI marcacaoDAO ;
	
	
	 @RequestMapping(value ="/login")
     public String login() {
         return "loginform";
     }
    
	 
	 @RequestMapping(value ="/loginform")
     public String loginForm() {
         return "loginform";
     }
	 
	 
	 @RequestMapping(value ="logout")
     public String logout(HttpSession session) {
         session.invalidate();
         return "redirect:loginform";
     }
	 
	 
	 @RequestMapping(value ="/efetualogin", method = RequestMethod.POST)
     public String efetuaLogin(@RequestParam("login") String login, @RequestParam("senha") String senha, HttpSession session, Model model,HttpServletRequest request) {
         
		 System.out.println("LoginController.efetuaLogin()");
		
		 
		 Usuario user = usuarioDAO.findbyLogin(login);
		 
		 if (user!=null) {
			 
			 String hashsenha =null;
			 StringBuilder hexString = null;
			 
			MessageDigest algorithm = null;;
			try {
				algorithm = MessageDigest.getInstance("SHA-256");
				byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8")); 
				 hexString= new StringBuilder();
				 
				 for (byte b : messageDigest) {
				   hexString.append(String.format("%02X", 0xFF & b));
				 }
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 			  			
			 hashsenha = hexString.toString();
			 			 
			 if (user.getSenha().equals(hashsenha)){
				 
				 System.out.println("Senha digititada corretamente...");
				 
				 session.setAttribute("usuarioLogado", user);
				 
				 if (user.getPerfilusuario()==UsuarioDAOI.PERFIL_TRANSPORTE) {
					 System.out.println("Redirecionand para a home do 	transporte...");
					 					 
				 }
				 				 
				 if (user.getPerfilusuario()== UsuarioDAOI.PERFIL_ADMIN) {
					 System.out.println("Redirecionand para a home do adm...");
					 				 
				 }
				 	
				 		
			 			System.out.println("passei no Logincontroller, anest homepage........");
			 			
			 			if ((user.getPerfilusuario()==UsuarioDAOI.PERFIL_ADMIN) || (user.getPerfilusuario()==UsuarioDAOI.PERFIL_USUARIO)) {
			 			
			 				Long contadorEncaminhamentos = encaminhamentoDAO.findbyContadorEncaminhamentosDaData(new Date());
			 				Long contadorEmbarquesEncaminhamentos = encaminhamentoDAO.findbyContadorEmbarquesEncaminhamentosDaData(new Date());
			 				
			 				request.setAttribute("contadorEncaminhamentos", contadorEncaminhamentos);
			 				request.setAttribute("contadorEmbarquesEncaminhamentos", contadorEmbarquesEncaminhamentos);
			 				
			 			}
			 			
			 			
			 		
				 
				 return "homepage";
			 }
			 else {
				 System.out.println("Senha incorreta...voltando para o form de login");
				 model.addAttribute("msg","Senha incorreta...voltando para o form de login");
				 request.setAttribute("msg","Senha incorreta...voltando para o form de login");
				 return "redirect:loginform";
			 }
         }
		 System.out.println("Usuário inexistente");
		 model.addAttribute("msg","Usuário inexistente. Tente novamente ou contacte o administrador");
		 
		 
		 
         return "redirect:loginform";
     }

	 

}
