package br.com.tfdonline.controller;


	import java.util.ArrayList;
	import java.util.LinkedHashMap;
	import java.util.List;
	import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.validation.BindingResult;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tfdonline.dao.UsuarioDAOI;
import br.com.tfdonline.modelo.Usuario;

	@Controller
	public class UsuarioController {


		private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

		@Autowired
		private UsuarioDAOI usuarioDAO;


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
				
				System.out.println("----->Nome ====="+ usuario.getNome());
				usuarioDAO.saveOrUpdate(usuario);
				System.out.println(".....Salvo ou atualizado o usuario.....");
				System.out.println("redirecionando para... \"redirect:/usuarios/\" + usuario.getId();");
				// POST/REDIRECT/GET
				return "redirect:/usuarios/" + usuario.getId();
				//return "/usuarios/" + usuario.getId();

				// POST/FORWARD/GET
				// return "usuario/list";

			}

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
			
			populateDefaultModel(model);
			
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

			populateDefaultModel(model);

			return "usuariocadastro";

		}


		// delete usuario
		@RequestMapping(value = "/usuarios/{id}/delete")
		public String deleteUsuario(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) {

			
			logger.debug("deleteUsuario() : {}", id);
			System.out.println("deletando o usuario ="+ id);
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Usuario deletado com sucesso!");

			usuarioDAO.deleteUsuarioByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Usuario deletado!");
			
			
			return "redirect:/usuarios/";
			//return "listausuariospage";

		}

	

		private void populateDefaultModel(Model model) {

			

			Map<String, String> skill = new LinkedHashMap<String, String>();
			skill.put("Ida", "Ida");
			skill.put("Volta", "Volta");
			skill.put("Ida e Volta", "Ida e Volta");
			model.addAttribute("tipoEncaminhamentoList", skill);

			List<Integer> numbers = new ArrayList<Integer>();
			numbers.add(1);
			numbers.add(2);
			numbers.add(3);
			numbers.add(4);
			numbers.add(5);
			model.addAttribute("numberList", numbers);

			Map<String, String> country = new LinkedHashMap<String, String>();
			country.put("US", "United Stated");
			country.put("CN", "China");
			country.put("SG", "Singapore");
			country.put("MY", "Malaysia");
			model.addAttribute("countryList", country);

		}

	}
	

