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

import br.com.tfdonline.dao.PautaDAOI;
import br.com.tfdonline.modelo.Pauta;

	@Controller
	public class PautaController {


		private final Logger logger = LoggerFactory.getLogger(PautaController.class);

		@Autowired
		private PautaDAOI pautaDAO;

		/*@Autowired
		//PautaFormValidator pautaFormValidator;
		
		//Set a form validator
		@InitBinder
		protected void initBinder(WebDataBinder binder) {
			binder.setValidator(pautaFormValidator);
		}
		*/
		

		// list page
		@RequestMapping(value = "/pautas")
		public String showAllPautas(Model model) {

			logger.debug("showAllPautas()");
			model.addAttribute("pautas", pautaDAO.findAll());
			return "listapautaspage";

		}
		
				
		@RequestMapping(value = {"/pautas/find" })
		    public String findPauta(Model model) {
			 	
				
				Pauta pauta = new Pauta();
				pauta.setDescricao("Informe o descricao da Pauta");
			 	model.addAttribute("pautaForm", pauta);
			 	return "findpauta";
		    }
		 
		// pesquisar page
		@RequestMapping(value = "/pautas/find2")
		public String showFindPautaForm(@RequestParam("descricao") String descricao, Model model) {
			System.out.println("chamando o pautas/find/descricao............"+descricao);
			logger.debug("Pautas.FindByName()");
			model.addAttribute("pautas", pautaDAO.findbyDescricao(descricao));
			return "listapautaspage";

		}

		// save or update pauta
		// 1. @ModelAttribute bind form value
		// 2. @Validated form validator
		// 3. RedirectAttributes for flash value
		@RequestMapping(value = "/pautas", method = RequestMethod.POST)
		//public String saveOrUpdatePauta(@ModelAttribute("pautaForm") @Validated Pauta pauta,
		public String saveOrUpdatePauta(@ModelAttribute("pautaForm")  Pauta pauta,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes) {

			logger.debug("saveOrUpdatePauta() : {}", pauta);
			System.out.println("Depois do formPauta, salvando ou atualizando pauta.............");
			System.out.println("Descricao do pauta a ser atualizada="+ pauta.getDescricao());
			
		/*	if (result.hasErrors()) {
				populateDefaultModel(model);
				return "pautaform";
			} else*/ {

				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(pauta.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Pauta adicionado com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Pauta atualizado com sucesso!");
				}
				
				System.out.println("----->Descricao ====="+ pauta.getDescricao());
				pautaDAO.saveOrUpdate(pauta);
				System.out.println(".....Salvo ou atualizado o pauta.....");
				System.out.println("redirecionando para... \"redirect:/pautas/\" + pauta.getId();");
				// POST/REDIRECT/GET
				return "redirect:/pautas/" + pauta.getId();
				//return "/pautas/" + pauta.getId();

				// POST/FORWARD/GET
				// return "pauta/list";

			}

		}

		// show update form
		@RequestMapping(value = "/pautas/{id}/update")
		public String showUpdatePautaForm(@PathVariable("id") int id, Model model) {

			logger.debug("showUpdatePautaForm() : {}", id);
			System.out.println("showUpdatePautaForm() : {} " + id);

			Pauta pauta = pautaDAO.findByID(id);
			if (pauta!=null) {
				System.out.println("evocandoo showUpdateMOtoristaForm.......pauta encontrado="+pauta.getDescricao());
				
			}else
				System.out.println("Pauta nao localizado");
			
			model.addAttribute("pautaForm", pauta);
			
						
			return "pautaform";

		}

		// show pauta
		@RequestMapping(value = "/pautas/{id}")
		public String showPauta(@PathVariable("id") int id, Model model) {

			logger.debug("showPauta() id: {}", id);
			System.out.println("-----> procurando pelo pauta id="+id);

			Pauta pauta = pautaDAO.findByID(id);
			if (pauta == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Pauta não encontrado");
			}
			model.addAttribute("pauta", pauta);

			return "pautashow";

		}
		
		// show add pauta form
		@RequestMapping(value = "/pautas/add", method = RequestMethod.GET)
		public String showAddPautaForm(Model model) {

			logger.debug("showAddPautaForm()");

			Pauta pauta = new Pauta();

			// set default value
			pauta.setId(-1);
			pauta.setDescricao("Descricao do Pauta");
			pauta.setData(new Date());
			pauta.setAberta(1);
		
			model.addAttribute("pautaForm", pauta);

			

			return "pautaform";

		}


		// delete pauta
		@RequestMapping(value = "/pautas/{id}/delete")
		public String deletePauta(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) {

			
			logger.debug("deletePauta() : {}", id);
			System.out.println("deletando o pauta ="+ id);
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Pauta deletado com sucesso!");

			pautaDAO.deletePautaByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Pauta deletado!");
			
			
			return "redirect:/pautas/";
			//return "listapautaspage";

		}

	
	}
	

