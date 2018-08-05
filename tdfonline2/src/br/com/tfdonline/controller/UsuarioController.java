package br.com.tfdonline.controller;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tfdonline.dao.LogTransacaoDAOI;
import br.com.tfdonline.dao.TransacaoDAOI;
import br.com.tfdonline.dao.UsuarioDAOI;

import br.com.tfdonline.modelo.Transacao;
import br.com.tfdonline.modelo.Usuario;

	@Controller
	public class UsuarioController {


		private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

		@Autowired
		private UsuarioDAOI usuarioDAO;
		
		@Autowired
		private LogTransacaoDAOI logtransacaoDAO;
		
		@Autowired
		private TransacaoDAOI transacaoDAO;

		
		
		
		public String getSenhaCriptografada(String senha) {
			
			//encripitando a senha
			byte messageDigest[] = null ;
			String senhaCriptografada =null;
			MessageDigest algorithm;
			try {
				algorithm = MessageDigest.getInstance("SHA-256");
				 messageDigest = algorithm.digest(senha.getBytes("UTF-8"));
				 StringBuilder hexString = new StringBuilder();
				 for (byte b : messageDigest) {
				   hexString.append(String.format("%02X", 0xFF & b));
				 }
				  senhaCriptografada = hexString.toString();
				  System.out.println("Hash senha="+ senhaCriptografada);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return senhaCriptografada;
		}

		// list page
		@RequestMapping(value = "/usuarios")
		public String showAllUsuarios(Model model) {

			logger.debug("showAllUsuarios()");
			model.addAttribute("usuarios", usuarioDAO.findAll());
			return "listausuariospage";

		}
		
		@RequestMapping(value = "/usuariobloqueado")
		public String showUsuarioBloqueado(Model model) {

			System.out.println("UsuarioController.showUsuarioBloqueado()");
			model.addAttribute("msg", "Usuário nao tem permissao para esta operacao");
			return "usuariobloqueado";

		}

		
				
		@RequestMapping(value = {"/usuarios/find" })
		    public String findUsuario(Model model) {
			 	
				
				Usuario usuario = new Usuario();
				usuario.setNome("Informe o nome da Usuario");
			 	model.addAttribute("usuarioForm", usuario);
			 	return "findusuario";
		    }
		 
		// pesquisar page
		@RequestMapping(value = "/usuarios/find2")
		public String showFindUsuarioForm(@RequestParam("nome") String nome, Model model) {
			System.out.println("chamando o usuarios/find/descricao............"+nome);
			logger.debug("Usuarios.FindByName()");
			model.addAttribute("usuarios", usuarioDAO.findbyName(nome));
			return "listausuariospage";

		}

		// save or update usuario
		// 1. @ModelAttribute bind form value
		// 2. @Validated form validator
		// 3. RedirectAttributes for flash value
		@RequestMapping(value = "/usuarios", method = RequestMethod.POST)
		//public String saveOrUpdateUsuario(@ModelAttribute("usuarioForm") @Validated Usuario usuario,
		public String saveOrUpdateUsuario(@ModelAttribute("usuarioForm")  Usuario usuario,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpSession session) {
			
			
			logger.debug("saveOrUpdateUsuario() : {}", usuario);
			System.out.println("Depois do formUsuario, salvando ou atualizando usuario.............");
			System.out.println("Nome do usuario a ser atualizada="+ usuario.getNome());
			
		/*	if (result.hasErrors()) {
				populateDefaultModel(model);
				return "usuarioform";
			} else*/ {

				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(usuario.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Usuario adicionado com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Usuario atualizado com sucesso!");
				}
				
				//encripitando a senha
				byte messageDigest[] = null ;
				String senha =null;
				MessageDigest algorithm;
				try {
					algorithm = MessageDigest.getInstance("SHA-256");
					 messageDigest = algorithm.digest(usuario.getSenha().getBytes("UTF-8"));
					 StringBuilder hexString = new StringBuilder();
					 for (byte b : messageDigest) {
					   hexString.append(String.format("%02X", 0xFF & b));
					 }
					  senha = hexString.toString();
					  System.out.println("Hash senha="+ senha);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				usuario.setSenha(senha);
				 
				
				
				
				System.out.println("----->Nome ====="+ usuario.getNome());
				usuarioDAO.saveOrUpdate(usuario);
				System.out.println(".....Salvo ou atualizado o usuario.....");
				
				
				//registrando a transção no BD..
				Transacao transacao =  transacaoDAO.isRegistravel(TransacaoDAOI.ENTIDADE_USUARIO, TransacaoDAOI.ADD);
				
				if (transacao!=null) {
					
					
					Usuario usuarioLogado =((Usuario) session.getAttribute("usuarioLogado"));
					logtransacaoDAO.saveOrUpdate(usuarioLogado, transacao, usuario.getId());	
					System.out.println("Salvando a transacao 1,1 no BD");
					
					
				}else {
					System.out.println("Transacao setada para não LOG");
				}
				
				
				
				
				// POST/REDIRECT/GET
				return "redirect:/usuarios";
				//return "/usuarios/" + usuario.getId();

				// POST/FORWARD/GET
				// return "usuario/list";

			}

		}
		
		@RequestMapping(value = "/usuarios/minhaconta")
		public String showMinhaContaForm(Model model, HttpSession session) {

			System.out.println("UsuarioController.ShowMinhaContaForm");
			Usuario usuariosession = ((Usuario) session.getAttribute("usuarioLogado"));
			
			Usuario usuario = new Usuario();
			usuario.setId(usuariosession.getId());
			usuario.setNome(usuariosession.getNome());
			usuario.setLogin(usuariosession.getLogin());
			usuario.setEmail(usuariosession.getEmail());
			usuario.setTelefone(usuariosession.getTelefone());
			usuario.setSenha("");
			usuario.setAdmin(usuariosession.getAdmin());
			usuario.setTransporte(usuariosession.getTransporte());
			
			
			model.addAttribute("usuarioForm", usuario);
			
			return "minhaconta";

		}
		
		@RequestMapping(value = "/usuarios/minhaconta2", method = RequestMethod.POST)
		//public String saveOrUpdateUsuario(@ModelAttribute("usuarioForm") @Validated Usuario usuario,
		public String updateMinhaConta(@ModelAttribute("usuarioForm")  Usuario usuario,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {
			
			
			logger.debug("updateMinhaconta : {}", usuario);
			System.out.println("Depois do formUsuario, salvando ou atualizando usuario.............");
			System.out.println("Nome do usuario a ser atualizada="+ usuario.getNome());
						// Add message to flash scope
				
			
				Usuario usuarioBD = usuarioDAO.findByID(usuario.getId());
				
				String senhaformcriptografada  = getSenhaCriptografada(usuario.getSenha());
				
				System.out.println("Senha informada pelo usuario:"+ usuario.getSenha());
				
				if (senhaformcriptografada.equals(usuarioBD.getSenha())){
					//senha do BD coincide com a digitada pelo usuario no form, vamos atualiza-lo
					
					String novasenhacriptografada = getSenhaCriptografada(usuario.getSenhanova1());
					usuario.setSenha(novasenhacriptografada);
					
					System.out.println(".....Salvo ou atualizado o usuario.....");
					
					usuarioDAO.saveOrUpdate(usuario);
					
					redirectAttributes.addFlashAttribute("css", "success");
					
					
					request.setAttribute("msg","Dados do usuário atualizados com sucesso!");
					
					//registrando a transção no BD..
					Transacao transacao =  transacaoDAO.isRegistravel(TransacaoDAOI.ENTIDADE_USUARIO, TransacaoDAOI.UPDATE);
					
					if (transacao!=null) {
						
						Usuario usuarioLogado =((Usuario) session.getAttribute("usuarioLogado"));
						logtransacaoDAO.saveOrUpdate(usuarioLogado, transacao, usuario.getId());	
						System.out.println("Salvando a transacao 1,2 no BD");
						
						
					}else {
						System.out.println("Transacao setada para não LOG");
					}
					
					//devovlendo o usuario atualizado para a sessao
					session.setAttribute("usuarioLogado", usuario);
					
					
				}else {
					
					redirectAttributes.addFlashAttribute("msg", "Senha informada não é a salva no banco de dados!");
					redirectAttributes.addFlashAttribute("css", "failure");
					redirectAttributes.addFlashAttribute("css", "error");
					request.setAttribute("msg","Senha informada não é a salva no banco de dados! <br> voltando para o form de login");
				}
				

				
				
				// POST/REDIRECT/GET
				return "minhaconta";
		
		

		}


		// show update form
		@RequestMapping(value = "/usuarios/{id}/update")
		public String showUpdateUsuarioForm(@PathVariable("id") int id, Model model) {

			logger.debug("showUpdateUsuarioForm() : {}", id);
			System.out.println("showUpdateUsuarioForm() : {} " + id);

			Usuario usuario = usuarioDAO.findByID(id);
			if (usuario!=null) {
				System.out.println("evocandoo showUpdateMOtoristaForm.......usuario encontrado="+usuario.getNome());
				
			}else
				System.out.println("Usuario nao localizado");
			
			model.addAttribute("usuarioForm", usuario);
			
			
			
			return "usuarioform";

		}

		// show usuario
		@RequestMapping(value = "/usuarios/{id}")
		public String showUsuario(@PathVariable("id") int id, Model model) {

			logger.debug("showUsuario() id: {}", id);
			System.out.println("-----> procurando pelo usuario id="+id);

			Usuario usuario = usuarioDAO.findByID(id);
			if (usuario == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Usuario não encontrado");
			}
			model.addAttribute("usuario", usuario);

			return "usuarioshow";

		}
		
		// show add usuario form
		@RequestMapping(value = "/usuarios/add", method = RequestMethod.GET)
		public String showAddUsuarioForm(Model model) {

			logger.debug("showAddUsuarioForm()");

			Usuario usuario = new Usuario();

			// set default value
			usuario.setId(-1);
			usuario.setNome("Nome do Usuario");
			usuario.setTelefone("8888-8888");
			usuario.setAdmin(0);
			usuario.setEmail("email");
			
			
						
			model.addAttribute("usuarioForm", usuario);

			

			return "usuariocadastro";

		}


		// delete usuario
		@RequestMapping(value = "/usuarios/{id}/delete")
		public String deleteUsuario(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes,HttpSession session) {

			
			logger.debug("deleteUsuario() : {}", id);
			System.out.println("deletando o usuario ="+ id);
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Usuario deletado com sucesso!");

			usuarioDAO.deleteUsuarioByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Usuario deletado!");
			
			
			//LOG DA TRANSACAO
			Transacao transacao =  transacaoDAO.isRegistravel(TransacaoDAOI.ENTIDADE_USUARIO, TransacaoDAOI.DELETE);
			
			if (transacao!=null) {
				
				Usuario usuarioLogado =((Usuario) session.getAttribute("usuarioLogado"));
				logtransacaoDAO.saveOrUpdate(usuarioLogado, transacao, id);	
				System.out.println("Salvando a transacao 1,3 no BD");
				
				
			}else {
				System.out.println("Transacao DELETE USER setada para não LOG");
			}
			
			
			return "redirect:/usuarios/";
			//return "listausuariospage";

		}

	

		

	}
	

