package br.com.tfdonline.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;

import br.com.tfdonline.dao.UsuarioDAOI;
import br.com.tfdonline.modelo.Usuario;

@Controller
public class LoginController {
	
	
	@Autowired
	private UsuarioDAOI usuarioDAO;
	
	
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
     public String efetuaLogin(@RequestParam("login") String login, @RequestParam("senha") String senha, HttpSession session, Model model) {
         
		 System.out.println("LoginController.efetuaLogin()");
		
		 
		 Usuario user = usuarioDAO.findbyLogin(login);
		 
		 if (user!=null) {
			 
			 if (user.getSenha().equals(senha)){
				 session.setAttribute("usuarioLogado", user);
				 
				 if (user.getTransporte()>0) {
					 System.out.println("Redirecionand para a home do 	transporte...");
					 					 
				 }
				 				 
				 if (user.getAdmin()>0) {
					 System.out.println("Redirecionand para a home do adm...");
					 				 
				 }
				 
				 return "homepage";
			 }
			 else {
				 System.out.println("Senha incorreta...voltando para o form de login");
				 model.addAttribute("msg","Senha incorreta...voltando para o form de login");
				 return "redirect:loginform";
			 }
         }
		 System.out.println("Usuário inexistente");
		 model.addAttribute("msg","Usuário inexistente. Tente novamente ou contacte o administrador");
		 
		 
         return "redirect:loginform";
     }

	 

}
