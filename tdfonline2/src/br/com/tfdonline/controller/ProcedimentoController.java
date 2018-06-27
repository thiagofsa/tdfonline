package br.com.tfdonline.controller;




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

import br.com.tfdonline.dao.ProcedimentoDAOI;
import br.com.tfdonline.modelo.Procedimento;
import br.com.tfdonline.util.SelectFiller;

	@Controller
	public class ProcedimentoController {


		private final Logger logger = LoggerFactory.getLogger(ProcedimentoController.class);

		@Autowired
		private ProcedimentoDAOI procedimentoDAO;

		/*@Autowired
		//ProcedimentoFormValidator procedimentoFormValidator;
		
		//Set a form validator
		@InitBinder
		protected void initBinder(WebDataBinder binder) {
			binder.setValidator(procedimentoFormValidator);
		}
		*/
		

		// list page - Mostra todos os procedimentos
		@RequestMapping(value = "/procedimentos")
		public String showAllProcedimentos(Model model) {

			logger.debug("showAllProcedimentos()");
			model.addAttribute("procedimentos", procedimentoDAO.findAll());
			return "listaprocedimentospage";
		}
		
				
		@RequestMapping(value = {"/procedimentos/find" })
		    public String findProcedimento(Model model) {			 	
				
				Procedimento procedimento = new Procedimento();
				procedimento.setNome("Informe o nome da Procedimento");
			 	model.addAttribute("procedimentoForm", procedimento);
			 	return "findprocedimento";
		    }
		 
		// pesquisar page
		@RequestMapping(value = "/procedimentos/find2")
		public String showFindProcedimentoForm(@RequestParam("nome") String nome, Model model) {
			
			System.out.println("chamando o procedimentos/find/nome............"+nome);
			logger.debug("Procedimentos.FindByName()");
			model.addAttribute("procedimentos", procedimentoDAO.findbyNome(nome));
			return "listaprocedimentospage";
		}

		// save or update procedimento
		// 1. @ModelAttribute bind form value
		// 2. @Validated form validator
		// 3. RedirectAttributes for flash value
		
		@RequestMapping(value = "/procedimentos", method = RequestMethod.POST)
		//public String saveOrUpdateProcedimento(@ModelAttribute("procedimentoForm") @Validated Procedimento procedimento,
		
		public String saveOrUpdateProcedimento(@ModelAttribute("procedimentoForm")  Procedimento procedimento,
				BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

			logger.debug("saveOrUpdateProcedimento() : {}", procedimento);
			System.out.println("Depois do formProcedimento, salvando ou atualizando procedimento.............");
			
		/*	if (result.hasErrors()) {
				populateDefaultModel(model);
				return "procedimentoform";
			} else*/ 
				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(procedimento.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Procedimento adicionado com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Procedimento atualizado com sucesso!");
				}
				
				System.out.println("----->Nome ====="+ procedimento.getNome());
				procedimentoDAO.saveOrUpdate(procedimento);
				System.out.println(".....Salvo ou atualizado o procedimento.....");
				System.out.println("redirecionando para... \"redirect:/procedimentos/\" + procedimento.getId();");
				// POST/REDIRECT/GET
				return "redirect:/procedimentos/";
			}

	
		// show update form
		@RequestMapping(value = "/procedimentos/{id}/update")
		public String showUpdateProcedimentoForm(@PathVariable("id") int id, Model model) {

			logger.debug("showUpdateProcedimentoForm() : {}", id);
			System.out.println("showUpdateProcedimentoForm() : {} " + id);

			Procedimento procedimento = procedimentoDAO.findByID(id);
			if (procedimento!=null) {
				System.out.println("evocandoo showUpdateMOtoristaForm.......procedimento encontrado="+procedimento.getNome());
				
			}else
				System.out.println("Procedimento nao localizado");

			model.addAttribute("areas", procedimentoDAO.getAreasProcedimento());

			model.addAttribute("procedimentoForm", procedimento);		
			
			
			return "procedimentoform";
		}

		// show procedimento
		@RequestMapping(value = "/procedimentos/{id}")
		public String showProcedimento(@PathVariable("id") int id, Model model) {

			logger.debug("showProcedimento() id: {}", id);
			System.out.println("-----> procurando pelo procedimento id="+id);

			Procedimento procedimento = procedimentoDAO.findByID(id);
			if (procedimento == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Procedimento não encontrado");
			}
			model.addAttribute("procedimento", procedimento);

			return "procedimentoshow";

		}
		
		// show add procedimento form
		@RequestMapping(value = "/procedimentos/add", method = RequestMethod.GET)
		public String showAddProcedimentoForm(Model model) {

			logger.debug("showAddProcedimentoForm()");

			Procedimento procedimento = new Procedimento();

			// set default value
			procedimento.setId(-1);					
			
			model.addAttribute("areas", procedimentoDAO.getAreasProcedimento());
						
			model.addAttribute("procedimentoForm", procedimento);

			return "procedimentocadastro";
		}


		// delete procedimento
		@RequestMapping(value = "/procedimentos/{id}/delete")
		public String deleteProcedimento(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes, Model model) {
			
			logger.debug("deleteProcedimento() : {}", id);
			System.out.println("desativando o procedimento ="+ id);
			
			procedimentoDAO.deleteProcedimentoByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Procedimento deletado com sucesso!");
			
			model.addAttribute("procedimentos", procedimentoDAO.findAll());
			
			return "listaprocedimentospage";
		}

	

		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	