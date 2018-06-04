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
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tfdonline.dao.AcompanhanteDAOI;
import br.com.tfdonline.modelo.Acompanhante;

	@Controller
	public class AcompanhanteController {


		private final Logger logger = LoggerFactory.getLogger(AcompanhanteController.class);

		@Autowired
		private AcompanhanteDAOI acompanhanteDAO;

		/*@Autowired
		//AcompanhanteFormValidator acompanhanteFormValidator;
		
		//Set a form validator
		@InitBinder
		protected void initBinder(WebDataBinder binder) {
			binder.setValidator(acompanhanteFormValidator);
		}
		*/
		

		// list page
		@RequestMapping(value = "/acompanhantes")
		public String showAllAcompanhantes(Model model) {

			logger.debug("showAllAcompanhantes()");
			model.addAttribute("acompanhantes", acompanhanteDAO.findAll());
			return "listaacompanhantespage";

		}
		
				
		@RequestMapping(value = {"/acompanhantes/find" })
		    public String findAcompanhante(Model model) {
			 	
				
				Acompanhante acompanhante = new Acompanhante();
				acompanhante.setNome("Informe o nome da Acompanhante");
			 	model.addAttribute("acompanhanteForm", acompanhante);
			 	return "findacompanhante";
		    }
		 
		// pesquisar page
		@RequestMapping(value = "/acompanhantes/find2")
		public String showFindAcompanhanteForm(@RequestParam("nome") String nome, Model model) {
			System.out.println("chamando o acompanhantes/find/descricao............"+nome);
			logger.debug("Acompanhantes.FindByName()");
			model.addAttribute("acompanhantes", acompanhanteDAO.findbyName(nome));
			return "listaacompanhantespage";

		}

		// save or update acompanhante
		// 1. @ModelAttribute bind form value
		// 2. @Validated form validator
		// 3. RedirectAttributes for flash value
		@RequestMapping(value = "/acompanhantes", method = RequestMethod.POST)
		//public String saveOrUpdateAcompanhante(@ModelAttribute("acompanhanteForm") @Validated Acompanhante acompanhante,
		public String saveOrUpdateAcompanhante(@ModelAttribute("acompanhanteForm")  Acompanhante acompanhante,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes) {

			logger.debug("saveOrUpdateAcompanhante() : {}", acompanhante);
			System.out.println("Depois do formAcompanhante, salvando ou atualizando acompanhante.............");
			System.out.println("Nome do acompanhante a ser atualizada="+ acompanhante.getNome());
			
		/*	if (result.hasErrors()) {
				populateDefaultModel(model);
				return "acompanhanteform";
			} else*/ {

				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(acompanhante.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Acompanhante adicionado com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Acompanhante atualizado com sucesso!");
				}
				
				System.out.println("----->Nome ====="+ acompanhante.getNome());
				acompanhanteDAO.saveOrUpdate(acompanhante);
				System.out.println(".....Salvo ou atualizado o acompanhante.....");
				System.out.println("redirecionando para... \"redirect:/acompanhantes/\" + acompanhante.getId();");
				// POST/REDIRECT/GET
				return "redirect:/acompanhantes/" + acompanhante.getId();
				//return "/acompanhantes/" + acompanhante.getId();

				// POST/FORWARD/GET
				// return "acompanhante/list";

			}

		}

		// show update form
		@RequestMapping(value = "/acompanhantes/{id}/update")
		public String showUpdateAcompanhanteForm(@PathVariable("id") int id, Model model) {

			logger.debug("showUpdateAcompanhanteForm() : {}", id);
			System.out.println("showUpdateAcompanhanteForm() : {} " + id);

			Acompanhante acompanhante = acompanhanteDAO.findByID(id);
			if (acompanhante!=null) {
				System.out.println("evocandoo showUpdateMOtoristaForm.......acompanhante encontrado="+acompanhante.getNome());
				
			}else
				System.out.println("Acompanhante nao localizado");
			
			model.addAttribute("acompanhanteForm", acompanhante);
			
			populateDefaultModel(model);
			
			return "acompanhanteform";

		}

		// show acompanhante
		@RequestMapping(value = "/acompanhantes/{id}")
		public String showAcompanhante(@PathVariable("id") int id, Model model) {

			logger.debug("showAcompanhante() id: {}", id);
			System.out.println("-----> procurando pelo acompanhante id="+id);

			Acompanhante acompanhante = acompanhanteDAO.findByID(id);
			if (acompanhante == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Acompanhante não encontrado");
			}
			model.addAttribute("acompanhante", acompanhante);

			return "acompanhanteshow";

		}
		
		// show add acompanhante form
		@RequestMapping(value = "/acompanhantes/add", method = RequestMethod.GET)
		public String showAddAcompanhanteForm(Model model) {

			logger.debug("showAddAcompanhanteForm()");

			Acompanhante acompanhante = new Acompanhante();

			// set default value
			acompanhante.setId(-1);
			acompanhante.setNome("Nome do Acompanhante");
			acompanhante.setTelefone("8888-8888");
			
						
			model.addAttribute("acompanhanteForm", acompanhante);

			populateDefaultModel(model);

			return "acompanhanteform";

		}


		// delete acompanhante
		@RequestMapping(value = "/acompanhantes/{id}/delete")
		public String deleteAcompanhante(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) {

			
			logger.debug("deleteAcompanhante() : {}", id);
			System.out.println("deletando o acompanhante ="+ id);
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Acompanhante deletado com sucesso!");

			acompanhanteDAO.deleteAcompanhanteByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Acompanhante deletado!");
			
			
			return "redirect:/acompanhantes/";
			//return "listaacompanhantespage";

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
	

