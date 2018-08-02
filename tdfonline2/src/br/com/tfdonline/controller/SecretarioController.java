package br.com.tfdonline.controller;
import java.io.File;
import java.io.IOException;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.tfdonline.dao.SecretarioDAOI;
import br.com.tfdonline.modelo.Requisicao;
import br.com.tfdonline.modelo.Secretario;

	@Controller
	public class SecretarioController {


		private final Logger logger = LoggerFactory.getLogger(SecretarioController.class);

		@Autowired
		private SecretarioDAOI secretarioDAO;


		// list page
		@RequestMapping(value = "/secretarios")
		public String showAllSecretarios(Model model) {

			logger.debug("showAllSecretarios()");
			model.addAttribute("secretarios", secretarioDAO.findAll());
			return "listasecretariospage";

		}
		
		//populando um form para upload
		@RequestMapping(value = {"/selectarquivo/secretarios/" })
		public String selectArquivo(Model model, HttpServletRequest request,@ModelAttribute("secretarioForm")  Secretario secretario){
		 	
				System.out.println("Teste do secretario depois de clicar em selecionar arquivo="+secretario.getNome());
			 	
				request.getSession().setAttribute("secretarioSession", secretario);
			
		 	return "uploadformpage.secretario";

	    }		

		@RequestMapping(value = "/upload/secretarioassinatura")
	    public String handleFormUpload( @RequestParam(value= "name", required=false) String name,
	        @RequestParam("file") MultipartFile multipartfile, HttpServletRequest request, Model model) {

			
			Secretario secretarioSession =null; 
			
	        if (!multipartfile.isEmpty()) {
	            try {
					byte[] bytes = multipartfile.getBytes();
					
					String ApplicationPath= request.getServletContext().getInitParameter("upload.directory");
					System.out.println("UploadDir"+ ApplicationPath);
					File f1 = new File(ApplicationPath+File.separator+multipartfile.getOriginalFilename());
					multipartfile.transferTo(f1);
					
					secretarioSession = (Secretario) request.getSession().getAttribute("secretarioSession");
					secretarioSession.setCaminhoarquivo(f1.getPath());
					
					request.getSession().setAttribute("secretarioSession", secretarioSession);
					model.addAttribute("secretarioForm", secretarioSession);
			
					System.out.println("Passei pelo upload...");
					
					
					System.out.println("Teste do secretarioForm apos envio de arquivo="+secretarioSession.getNome());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            // store the bytes somewhere
	        
	            if (secretarioSession.isNew()) {
					return "secretariocadastro";
				}else				
					return "secretarioform";	
	            
	       } else {
	           return "uploadformpage.secretario";
	       }
	    }


		
				
		@RequestMapping(value = {"/secretarios/find" })
		    public String findSecretario(Model model) {
			 	
				
				Secretario secretario = new Secretario();
				secretario.setNome("Informe o nome da Secretario");
			 	model.addAttribute("secretarioForm", secretario);
			 	return "findsecretario";
		    }
		 
		// pesquisar page
		@RequestMapping(value = "/secretarios/find2")
		public String showFindSecretarioForm(@RequestParam("nome") String nome, Model model) {
			System.out.println("chamando o secretarios/find/descricao............"+nome);
			logger.debug("Secretarios.FindByName()");
			model.addAttribute("secretarios", secretarioDAO.findbyName(nome));
			return "listasecretariospage";

		}

		// save or update secretario
		// 1. @ModelAttribute bind form value
		// 2. @Validated form validator
		// 3. RedirectAttributes for flash value
		@RequestMapping(value = "/secretarios", method = RequestMethod.POST)
		//public String saveOrUpdateSecretario(@ModelAttribute("secretarioForm") @Validated Secretario secretario,
		public String saveOrUpdateSecretario(@ModelAttribute("secretarioForm")  Secretario secretario,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpSession session) {
			
			
			logger.debug("saveOrUpdateSecretario() : {}", secretario);
			System.out.println("Depois do formSecretario, salvando ou atualizando secretario.............");
			System.out.println("Nome do secretario a ser atualizada="+ secretario.getNome());
			
		/*	if (result.hasErrors()) {
				populateDefaultModel(model);
				return "secretarioform";
			} else*/ {

				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(secretario.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Secretario adicionado com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Secretario atualizado com sucesso!");
				}
				
				System.out.println("----->Nome ====="+ secretario.getNome());
				secretarioDAO.saveOrUpdate(secretario);
				System.out.println(".....Salvo ou atualizado o secretario.....");
				System.out.println("redirecionando para... \"redirect:/secretarios/\" + secretario.getId();");
				// POST/REDIRECT/GET
				return "redirect:/secretarios/" + secretario.getId();
				//return "/secretarios/" + secretario.getId();

				// POST/FORWARD/GET
				// return "secretario/list";

			}

		}

		// show update form
		@RequestMapping(value = "/secretarios/{id}/update")
		public String showUpdateSecretarioForm(@PathVariable("id") int id, Model model) {

			logger.debug("showUpdateSecretarioForm() : {}", id);
			System.out.println("showUpdateSecretarioForm() : {} " + id);

			Secretario secretario = secretarioDAO.findByID(id);
			if (secretario!=null) {
				System.out.println("evocandoo showUpdateMOtoristaForm.......secretario encontrado="+secretario.getNome());
				
			}else
				System.out.println("Secretario nao localizado");
			
			model.addAttribute("secretarioForm", secretario);
			
			
			
			return "secretarioform";

		}

		// show secretario
		@RequestMapping(value = "/secretarios/{id}")
		public String showSecretario(@PathVariable("id") int id, Model model) {

			logger.debug("showSecretario() id: {}", id);
			System.out.println("-----> procurando pelo secretario id="+id);

			Secretario secretario = secretarioDAO.findByID(id);
			if (secretario == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Secretario não encontrado");
			}
			model.addAttribute("secretario", secretario);

			return "secretarioshow";

		}
		
		// show add secretario form
		@RequestMapping(value = "/secretarios/add", method = RequestMethod.GET)
		public String showAddSecretarioForm(Model model) {

			logger.debug("showAddSecretarioForm()");

			Secretario secretario = new Secretario();

			// set default value
			secretario.setId(-1);
			secretario.setNome("Nome do Secretario");
			
			model.addAttribute("secretarioForm", secretario);
						

			return "secretariocadastro";

		}
		// delete secretario
		@RequestMapping(value = "/secretarios/{id}/delete")
		public String deleteSecretario(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) {

			
			logger.debug("deleteSecretario() : {}", id);
			System.out.println("deletando o secretario ="+ id);
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Secretario deletado com sucesso!");

			secretarioDAO.deleteSecretarioByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Secretario deletado!");
			
			
			return "redirect:/secretarios/";
			//return "listasecretariospage";

		}
		
	}
	

