package br.com.tfdonline.controller;


	import java.util.ArrayList;
	import java.util.LinkedHashMap;
	import java.util.List;
	import java.util.Map;



	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.beans.factory.annotation.Autowired;

	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.validation.BindingResult;
	import org.springframework.validation.annotation.Validated;
	import org.springframework.web.bind.WebDataBinder;
	import org.springframework.web.bind.annotation.InitBinder;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tfdonline.dao.UnidadeSaudeDAOI;
import br.com.tfdonline.modelo.UnidadeSaude;

	@Controller
	public class UnidadeSaudeController {


		private final Logger logger = LoggerFactory.getLogger(UnidadeSaudeController.class);

		@Autowired
		private UnidadeSaudeDAOI unidadesaudeDAO;

		/*@Autowired
		//UnidadeSaudeFormValidator unidadesaudeFormValidator;
		
		//Set a form validator
		@InitBinder
		protected void initBinder(WebDataBinder binder) {
			binder.setValidator(unidadesaudeFormValidator);
		}
		*/
		

		// list page
		@RequestMapping(value = "/unidadesaudes")
		public String showAllUnidadeSaudes(Model model) {

			logger.debug("showAllUnidadeSaudes()");
			model.addAttribute("unidadesaudes", unidadesaudeDAO.findAll());
			return "listaunidadesaudespage";

		}
		
				
		@RequestMapping(value = {"/unidadesaudes/find" })
		    public String findUnidadeSaude(Model model) {
			 	
				
				UnidadeSaude unidadesaude = new UnidadeSaude();
				unidadesaude.setDescricao("Informe o nome da Unidade de Saude");
			 	model.addAttribute("unidadesaudeForm", unidadesaude);
			 	return "findunidadesaude";
		    }
		 
		// pesquisar page
		@RequestMapping(value = "/unidadesaudes/find2")
		public String showFindUnidadeSaudeForm(@RequestParam("descricao") String descricao, Model model) {
			System.out.println("chamando o unidadesaudes/find/descricao............"+descricao);
			logger.debug("UnidadeSaudes.FindByName()");
			model.addAttribute("unidadesaudes", unidadesaudeDAO.findbyDescricao(descricao));
			return "listaunidadesaudespage";

		}



		// save or update unidadesaude
		// 1. @ModelAttribute bind form value
		// 2. @Validated form validator
		// 3. RedirectAttributes for flash value
		@RequestMapping(value = "/unidadesaudes", method = RequestMethod.POST)
		//public String saveOrUpdateUnidadeSaude(@ModelAttribute("unidadesaudeForm") @Validated UnidadeSaude unidadesaude,
		public String saveOrUpdateUnidadeSaude(@ModelAttribute("unidadesaudeForm")  UnidadeSaude unidadesaude,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes) {

			logger.debug("saveOrUpdateUnidadeSaude() : {}", unidadesaude);
			System.out.println("Depois do formUnidadeSaude, salvando ou atualizando unidadesaude.............");
			System.out.println("Rua do unidadesaude a ser atualizada="+ unidadesaude.getLogradouro());
			
		/*	if (result.hasErrors()) {
				populateDefaultModel(model);
				return "unidadesaudeform";
			} else*/ {

				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(unidadesaude.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "UnidadeSaude adicionado com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "UnidadeSaude atualizado com sucesso!");
				}
				
				System.out.println("----->nova rua ====="+ unidadesaude.getLogradouro());
				unidadesaudeDAO.saveOrUpdate(unidadesaude);
				System.out.println(".....Salvo ou atualizado o unidadesaude.....");
				System.out.println("redirecionando para... \"redirect:/unidadesaudes/\" + unidadesaude.getId();");
				// POST/REDIRECT/GET
				return "redirect:/unidadesaudes/" + unidadesaude.getId();
				//return "/unidadesaudes/" + unidadesaude.getId();

				// POST/FORWARD/GET
				// return "unidadesaude/list";

			}

		}

		// show update form
		@RequestMapping(value = "/unidadesaudes/{id}/update")
		public String showUpdateUnidadeSaudeForm(@PathVariable("id") int id, Model model) {

			logger.debug("showUpdateUnidadeSaudeForm() : {}", id);
			System.out.println("showUpdateUnidadeSaudeForm() : {} " + id);

			UnidadeSaude unidadesaude = unidadesaudeDAO.findByID(id);
			if (unidadesaude!=null) {
				System.out.println("evocandoo showUpdateMOtoristaForm.......unidadesaude encontrado="+unidadesaude.getDescricao());
				
			}else
				System.out.println("UnidadeSaude nao localizado");
			
			model.addAttribute("unidadesaudeForm", unidadesaude);
			
			populateDefaultModel(model);
			
			return "unidadesaudeform";

		}

		// show unidadesaude
		@RequestMapping(value = "/unidadesaudes/{id}")
		public String showUnidadeSaude(@PathVariable("id") int id, Model model) {

			logger.debug("showUnidadeSaude() id: {}", id);
			System.out.println("-----> procurando pelo unidadesaude id="+id);

			UnidadeSaude unidadesaude = unidadesaudeDAO.findByID(id);
			if (unidadesaude == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "UnidadeSaude não encontrado");
			}
			model.addAttribute("unidadesaude", unidadesaude);

			return "unidadesaudeshow";

		}
		
		// show add unidadesaude form
		@RequestMapping(value = "/unidadesaudes/add", method = RequestMethod.GET)
		public String showAddUnidadeSaudeForm(Model model) {

			logger.debug("showAddUnidadeSaudeForm()");

			UnidadeSaude unidadesaude = new UnidadeSaude();

			// set default value
			unidadesaude.setId(-1);
			unidadesaude.setDescricao("Nome da Unidade");
			unidadesaude.setTelefone("8888-8888");
			unidadesaude.setLogradouro("logradouro");
			unidadesaude.setNumero("xx");
			unidadesaude.setBairro("bairro");
			unidadesaude.setComplemento("complemento");
			unidadesaude.setIdcidade(-1);
			
			model.addAttribute("unidadesaudeForm", unidadesaude);

			populateDefaultModel(model);

			return "unidadesaudeform";

		}


		// delete unidadesaude
		@RequestMapping(value = "/unidadesaudes/{id}/delete")
		public String deleteUnidadeSaude(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) {

			logger.debug("deleteUnidadeSaude() : {}", id);
			System.out.println("deletando o unidadesaude ="+ id);
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "UnidadeSaude deletada com sucesso!");

			unidadesaudeDAO.deleteUnidadeSaudeByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "UnidadeSaude deletado!");
			
			
			return "redirect:/unidadesaudes/";
			//return "listaunidadesaudespage";

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
	

