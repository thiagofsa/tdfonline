package br.com.tfdonline.controller;



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


import br.com.tfdonline.dao.TransacaoDAOI;


import br.com.tfdonline.modelo.Transacao;


	@Controller
	public class TransacaoController {


		private final Logger logger = LoggerFactory.getLogger(TransacaoController.class);

		@Autowired
		private TransacaoDAOI transacaoDAO;
		



		// list page
		@RequestMapping(value = "/transacaos")
		public String showAllTransacaos(Model model) {

			logger.debug("showAllTransacaos()");
			model.addAttribute("transacaos", transacaoDAO.findAll());
			return "listatransacaospage";

		}

		// save or update transacao
		// 1. @ModelAttribute bind form value
		// 2. @Validated form validator
		// 3. RedirectAttributes for flash value
		@RequestMapping(value = "/transacaos", method = RequestMethod.POST)
		//public String saveOrUpdateTransacao(@ModelAttribute("transacaoForm") @Validated Transacao transacao,
		public String saveOrUpdateTransacao(@ModelAttribute("transacaoForm")  Transacao transacao,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpSession session) {
			
			
			logger.debug("saveOrUpdateTransacao() : {}", transacao);
			System.out.println("Depois do formTransacao, salvando ou atualizando transacao.............");
			System.out.println("ID do transacao a ser atualizada="+ transacao.getId());
			
		/*	if (result.hasErrors()) {
				populateDefaultModel(model);
				return "transacaoform";
			} else*/ {

				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(transacao.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Transacao adicionado com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Transacao atualizado com sucesso!");
				}
				
				transacaoDAO.saveOrUpdate(transacao);
				System.out.println(".....Salvo ou atualizado o transacao.....");
				
				
				//registrando a transção no BD..
				
				
				
				// POST/REDIRECT/GET
				return "redirect:/transacaos";
				//return "/transacaos/" + transacao.getId();

				// POST/FORWARD/GET
				// return "transacao/list";

			}

		}

		// show update form
		@RequestMapping(value = "/transacaos/{id}/update")
		public String showUpdateTransacaoForm(@PathVariable("id") int id, Model model) {

			logger.debug("showUpdateTransacaoForm() : {}", id);
			System.out.println("showUpdateTransacaoForm() : {} " + id);

			Transacao transacao = transacaoDAO.findByID(id);
			if (transacao!=null) {
				System.out.println("evocandoo showUpdateMOtoristaForm.......transacao encontrado="+transacao.getId());
				
			}else
				System.out.println("Transacao nao localizado");
			
			model.addAttribute("transacaoForm", transacao);
			
			return "transacaoform";

		}

		// show transacao
		@RequestMapping(value = "/transacaos/{id}")
		public String showTransacao(@PathVariable("id") int id, Model model) {

			logger.debug("showTransacao() id: {}", id);
			System.out.println("-----> procurando pelo transacao id="+id);

			Transacao transacao = transacaoDAO.findByID(id);
			if (transacao == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Transacao não encontrado");
			}
			model.addAttribute("transacao", transacao);

			return "transacaoshow";

		}
		
		// show add transacao form
		@RequestMapping(value = "/transacaos/add", method = RequestMethod.GET)
		public String showAddTransacaoForm(Model model) {

			logger.debug("showAddTransacaoForm()");

			Transacao transacao = new Transacao();

			// set default value
			transacao.setId(-1);
			
					
						
			model.addAttribute("transacaoForm", transacao);

			
			return "transacaocadastro";

		}


		// delete transacao
		@RequestMapping(value = "/transacaos/{id}/delete")
		public String deleteTransacao(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes,HttpSession session) {

			
			logger.debug("deleteTransacao() : {}", id);
			System.out.println("deletando o transacao ="+ id);
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Transacao deletado com sucesso!");

			transacaoDAO.deleteTransacaoByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Transacao deletado!");

			return "redirect:/transacaos/";
			//return "listatransacaospage";

		}

	

		
	}
	

