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

import br.com.tfdonline.dao.MotoristaDAOI;
import br.com.tfdonline.modelo.Motorista;
import br.com.tfdonline.validator.MotoristaFormValidator;

	@Controller
	public class MotoristaController {


		private final Logger logger = LoggerFactory.getLogger(MotoristaController.class);

		@Autowired
		private MotoristaDAOI motoristaDAO;

		@Autowired
		MotoristaFormValidator motoristaFormValidator;
		
		//Set a form validator
		@InitBinder
		protected void initBinder(WebDataBinder binder) {
			binder.setValidator(motoristaFormValidator);
		}
		
		

		// list page
		@RequestMapping(value = "/motoristas")
		public String showAllMotoristas(Model model) {

			logger.debug("showAllMotoristas()");
			model.addAttribute("motoristas", motoristaDAO.findAll());
			return "listamotoristaspage";

		}
		
				
		@RequestMapping(value = {"/motoristas/find" })
		    public String findMotorista(Model model) {
			 	
				
				Motorista motorista = new Motorista();
				motorista.setNome("Informe o nome");
			 	model.addAttribute("motoristaForm", motorista);
			 	return "findmotorista";
		    }
		 
		// pesquisar page
		@RequestMapping(value = "/motoristas/find2")
		public String showFindMotoristaForm(@RequestParam("nome") String nome, Model model) {
			System.out.println("chamando o motoristas/find/nome............"+nome);
			logger.debug("Motoristas.FindByName()");
			model.addAttribute("motoristas", motoristaDAO.findbyName(nome));
			return "listamotoristaspage";

		}



		// save or update motorista
		// 1. @ModelAttribute bind form value
		// 2. @Validated form validator
		// 3. RedirectAttributes for flash value
		@RequestMapping(value = "/motoristas", method = RequestMethod.POST)
		//public String saveOrUpdateMotorista(@ModelAttribute("motoristaForm") @Validated Motorista motorista,
		public String saveOrUpdateMotorista(@ModelAttribute("motoristaForm")  Motorista motorista,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes) {

			logger.debug("saveOrUpdateMotorista() : {}", motorista);
			System.out.println("Depois do formMotorista, salvando ou atualizando motorista.............");
			System.out.println("Rua do motorista a ser atualizada="+ motorista.getEndereco());
			
		/*	if (result.hasErrors()) {
				populateDefaultModel(model);
				return "motoristaform";
			} else*/ {

				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(motorista.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Motorista adicionado com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Motorista atualizado com sucesso!");
				}
				
				System.out.println("----->nova rua ====="+ motorista.getEndereco());
				motoristaDAO.saveOrUpdate(motorista);
				System.out.println(".....Salvo ou atualizado o motorista.....");
				System.out.println("redirecionando para... \"redirect:/motoristas/\" + motorista.getId();");
				// POST/REDIRECT/GET
				return "redirect:/motoristas/" + motorista.getId();
				//return "/motoristas/" + motorista.getId();

				// POST/FORWARD/GET
				// return "motorista/list";

			}

		}

		// show update form
		@RequestMapping(value = "/motoristas/{id}/update")
		public String showUpdateMotoristaForm(@PathVariable("id") int id, Model model) {

			logger.debug("showUpdateMotoristaForm() : {}", id);
			System.out.println("showUpdateMotoristaForm() : {} " + id);

			Motorista motorista = motoristaDAO.findByID(id);
			if (motorista!=null) {
				System.out.println("evocandoo showUpdateMOtoristaForm.......motorista encontrado="+motorista.getNome());
				
			}else
				System.out.println("Motorista nao localizado");
			
			model.addAttribute("motoristaForm", motorista);
			
			populateDefaultModel(model);
			
			return "motoristaform";

		}

		// show motorista
		@RequestMapping(value = "/motoristas/{id}")
		public String showMotorista(@PathVariable("id") int id, Model model) {

			logger.debug("showMotorista() id: {}", id);
			System.out.println("-----> procurando pelo motorista id="+id);

			Motorista motorista = motoristaDAO.findByID(id);
			if (motorista == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Motorista não encontrado");
			}
			model.addAttribute("motorista", motorista);

			return "motoristashow";

		}
		
		// show add motorista form
		@RequestMapping(value = "/motoristas/add", method = RequestMethod.GET)
		public String showAddMotoristaForm(Model model) {

			logger.debug("showAddMotoristaForm()");

			Motorista motorista = new Motorista();

			// set default value
			motorista.setId(-1);
			motorista.setNome("mkyong123");
			motorista.setEmail("test@gmail.com");
			motorista.setTelefone("8888-8888");
			model.addAttribute("motoristaForm", motorista);

			populateDefaultModel(model);

			return "motoristaform";

		}


		// delete motorista
		@RequestMapping(value = "/motoristas/{id}/delete")
		public String deleteMotorista(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) {

			logger.debug("deleteMotorista() : {}", id);
			System.out.println("deletando o motorista ="+ id);

			motoristaDAO.deleteMotoristaByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Motorista deletado!");
			
			
			return "redirect:/motoristas/";
			//return "listamotoristaspage";

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
	

