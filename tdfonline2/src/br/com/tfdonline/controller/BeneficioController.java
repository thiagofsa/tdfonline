package br.com.tfdonline.controller;


	import java.util.ArrayList;
import java.util.Date;
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

import br.com.tfdonline.dao.BeneficioDAOI;
import br.com.tfdonline.modelo.Beneficio;

	@Controller
	public class BeneficioController {


		private final Logger logger = LoggerFactory.getLogger(BeneficioController.class);

		@Autowired
		private BeneficioDAOI beneficioDAO;

		/*@Autowired
		//BeneficioFormValidator beneficioFormValidator;
		
		//Set a form validator
		@InitBinder
		protected void initBinder(WebDataBinder binder) {
			binder.setValidator(beneficioFormValidator);
		}
		*/
		

		// list page
		@RequestMapping(value = "/beneficios")
		public String showAllBeneficios(Model model) {

			logger.debug("showAllBeneficios()");
			model.addAttribute("beneficios", beneficioDAO.findAll());
			return "listabeneficiospage";

		}
		
				
		@RequestMapping(value = {"/beneficios/find" })
		    public String findBeneficio(Model model) {
			 	
				
				Beneficio beneficio = new Beneficio();
				beneficio.setNome("Informe o nome da Beneficio");
			 	model.addAttribute("beneficioForm", beneficio);
			 	return "findbeneficio";
		    }
		 
		// pesquisar page
		@RequestMapping(value = "/beneficios/find2")
		public String showFindBeneficioForm(@RequestParam("nome") String nome, Model model) {
			System.out.println("chamando o beneficios/find/descricao............"+nome);
			logger.debug("Beneficios.FindByName()");
			model.addAttribute("beneficios", beneficioDAO.findbyName(nome));
			return "listabeneficiospage";

		}

		// save or update beneficio
		// 1. @ModelAttribute bind form value
		// 2. @Validated form validator
		// 3. RedirectAttributes for flash value
		@RequestMapping(value = "/beneficios", method = RequestMethod.POST)
		//public String saveOrUpdateBeneficio(@ModelAttribute("beneficioForm") @Validated Beneficio beneficio,
		public String saveOrUpdateBeneficio(@ModelAttribute("beneficioForm")  Beneficio beneficio,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes) {

			logger.debug("saveOrUpdateBeneficio() : {}", beneficio);
			System.out.println("Depois do formBeneficio, salvando ou atualizando beneficio.............");
			System.out.println("Nome do beneficio a ser atualizada="+ beneficio.getNome());
			
		/*	if (result.hasErrors()) {
				populateDefaultModel(model);
				return "beneficioform";
			} else*/ {

				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(beneficio.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Beneficio adicionado com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Beneficio atualizado com sucesso!");
				}
				
				System.out.println("----->Nome ====="+ beneficio.getNome());
				beneficioDAO.saveOrUpdate(beneficio);
				System.out.println(".....Salvo ou atualizado o beneficio.....");
				System.out.println("redirecionando para... \"redirect:/beneficios/\" + beneficio.getId();");
				// POST/REDIRECT/GET
				return "redirect:/beneficios/" + beneficio.getId();
				//return "/beneficios/" + beneficio.getId();

				// POST/FORWARD/GET
				// return "beneficio/list";

			}

		}

		// show update form
		@RequestMapping(value = "/beneficios/{id}/update")
		public String showUpdateBeneficioForm(@PathVariable("id") int id, Model model) {

			logger.debug("showUpdateBeneficioForm() : {}", id);
			System.out.println("showUpdateBeneficioForm() : {} " + id);

			Beneficio beneficio = beneficioDAO.findByID(id);
			if (beneficio!=null) {
				System.out.println("evocandoo showUpdateMOtoristaForm.......beneficio encontrado="+beneficio.getNome());
				
			}else
				System.out.println("Beneficio nao localizado");
			
			model.addAttribute("beneficioForm", beneficio);
			
			populateDefaultModel(model);
			
			return "beneficioform";

		}

		// show beneficio
		@RequestMapping(value = "/beneficios/{id}")
		public String showBeneficio(@PathVariable("id") int id, Model model) {

			logger.debug("showBeneficio() id: {}", id);
			System.out.println("-----> procurando pelo beneficio id="+id);

			Beneficio beneficio = beneficioDAO.findByID(id);
			if (beneficio == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Beneficio não encontrado");
			}
			model.addAttribute("beneficio", beneficio);

			return "beneficioshow";

		}
		
		// show add beneficio form
		@RequestMapping(value = "/beneficios/add", method = RequestMethod.GET)
		public String showAddBeneficioForm(Model model) {

			logger.debug("showAddBeneficioForm()");

			Beneficio beneficio = new Beneficio();

			// set default value
			beneficio.setId(-1);
			beneficio.setNome("Nome do Beneficio");
			beneficio.setDataautorizacao(new Date());
			beneficio.setDatavencimento(new Date());
									
			model.addAttribute("beneficioForm", beneficio);

			populateDefaultModel(model);

			return "beneficioform";

		}


		// delete beneficio
		@RequestMapping(value = "/beneficios/{id}/delete")
		public String deleteBeneficio(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) {

			
			logger.debug("deleteBeneficio() : {}", id);
			System.out.println("deletando o beneficio ="+ id);
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Beneficio deletado com sucesso!");

			beneficioDAO.deleteBeneficioByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Beneficio deletado!");
			
			
			return "redirect:/beneficios/";
			//return "listabeneficiospage";

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
	

