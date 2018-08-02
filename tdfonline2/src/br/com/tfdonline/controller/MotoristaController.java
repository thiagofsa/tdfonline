package br.com.tfdonline.controller;


	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.beans.factory.annotation.Autowired;

	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.validation.BindingResult;
	
	import org.springframework.web.bind.WebDataBinder;
	import org.springframework.web.bind.annotation.InitBinder;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
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
		
		
		// list page - Mostra todos os motoristas
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
		public String saveOrUpdateMotorista(@ModelAttribute("motoristaForm")  Motorista motorista,
				BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

			logger.debug("saveOrUpdateMotorista() : {}", motorista);
			System.out.println("Depois do formMotorista, salvando ou atualizando motorista.............");
			
				redirectAttributes.addFlashAttribute("css", "success");
				if(motorista.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Motorista ADICIONADO com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Motorista ATUALIZADO com sucesso!");
				}				

				motoristaDAO.saveOrUpdate(motorista);
				System.out.println(".....Salvo ou atualizado o motorista.....");
				System.out.println("redirecionando para listapage");
				
				return "redirect:/motoristas/";
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
						
			model.addAttribute("motoristaForm", motorista);

			return "motoristacadastro";
		}

		// delete motorista
		@RequestMapping(value = "/motoristas/{id}/delete")		
		public String deleteMotorista(@PathVariable("id") int id, 
				final RedirectAttributes redirectAttributes, Model model) {

			logger.debug("deleteMotorista() : {}", id);
			System.out.println("desativando o motorista ="+ id);

			motoristaDAO.deleteMotoristaByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("msg", "Motorista DELETADO com sucesso!");

			return "redirect:/motoristas/";
		}	
}
	


