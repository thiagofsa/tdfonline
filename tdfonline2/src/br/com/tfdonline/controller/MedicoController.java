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

import br.com.tfdonline.dao.MedicoDAOI;
import br.com.tfdonline.modelo.Medico;

	@Controller
	public class MedicoController {


		private final Logger logger = LoggerFactory.getLogger(MedicoController.class);

		@Autowired
		private MedicoDAOI medicoDAO;


		// list page
		@RequestMapping(value = "/medicos")
		public String showAllMedicos(Model model) {

			logger.debug("showAllMedicos()");
			model.addAttribute("medicos", medicoDAO.findAll());
			return "listamedicospage";

		}
		
		@RequestMapping(value = "/medicobloqueado")
		public String showMedicoBloqueado(Model model) {

			System.out.println("MedicoController.showMedicoBloqueado()");
			model.addAttribute("msg", "Usuário nao tem permissao para esta operacao");
			return "medicobloqueado";

		}

		
				
		@RequestMapping(value = {"/medicos/find" })
		    public String findMedico(Model model) {
			 	
				
				Medico medico = new Medico();
				medico.setNome("Informe o nome da Medico");
			 	model.addAttribute("medicoForm", medico);
			 	return "findmedico";
		    }
		 
		// pesquisar page
		@RequestMapping(value = "/medicos/find2")
		public String showFindMedicoForm(@RequestParam("nome") String nome, Model model) {
			System.out.println("chamando o medicos/find/descricao............"+nome);
			logger.debug("Medicos.FindByName()");
			model.addAttribute("medicos", medicoDAO.findbyName(nome));
			return "listamedicospage";

		}

		// save or update medico
		// 1. @ModelAttribute bind form value
		// 2. @Validated form validator
		// 3. RedirectAttributes for flash value
		@RequestMapping(value = "/medicos", method = RequestMethod.POST)
		//public String saveOrUpdateMedico(@ModelAttribute("medicoForm") @Validated Medico medico,
		public String saveOrUpdateMedico(@ModelAttribute("medicoForm")  Medico medico,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpSession session) {
			
			
			logger.debug("saveOrUpdateMedico() : {}", medico);
			System.out.println("Depois do formMedico, salvando ou atualizando medico.............");
			System.out.println("Nome do medico a ser atualizada="+ medico.getNome());
			
		/*	if (result.hasErrors()) {
				populateDefaultModel(model);
				return "medicoform";
			} else*/ {

				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(medico.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Medico adicionado com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Medico atualizado com sucesso!");
				}
				
				System.out.println("----->Nome ====="+ medico.getNome());
				medicoDAO.saveOrUpdate(medico);
				System.out.println(".....Salvo ou atualizado o medico.....");
				System.out.println("redirecionando para... \"redirect:/medicos/\" + medico.getId();");
				// POST/REDIRECT/GET
				return "redirect:/medicos/" + medico.getId();
				//return "/medicos/" + medico.getId();

				// POST/FORWARD/GET
				// return "medico/list";

			}

		}

		// show update form
		@RequestMapping(value = "/medicos/{id}/update")
		public String showUpdateMedicoForm(@PathVariable("id") int id, Model model) {

			logger.debug("showUpdateMedicoForm() : {}", id);
			System.out.println("showUpdateMedicoForm() : {} " + id);

			Medico medico = medicoDAO.findByID(id);
			if (medico!=null) {
				System.out.println("evocandoo showUpdateMOtoristaForm.......medico encontrado="+medico.getNome());
				
			}else
				System.out.println("Medico nao localizado");
			
			model.addAttribute("medicoForm", medico);
			
			
			
			return "medicoform";

		}

		// show medico
		@RequestMapping(value = "/medicos/{id}")
		public String showMedico(@PathVariable("id") int id, Model model) {

			logger.debug("showMedico() id: {}", id);
			System.out.println("-----> procurando pelo medico id="+id);

			Medico medico = medicoDAO.findByID(id);
			if (medico == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Medico não encontrado");
			}
			model.addAttribute("medico", medico);

			return "medicoshow";

		}
		
		// show add medico form
		@RequestMapping(value = "/medicos/add", method = RequestMethod.GET)
		public String showAddMedicoForm(Model model) {

			logger.debug("showAddMedicoForm()");

			Medico medico = new Medico();

			// set default value
			medico.setId(-1);
			medico.setNome("Nome do Medico");
			medico.setTelefone("8888-8888");
			
						
			model.addAttribute("medicoForm", medico);

			

			return "medicocadastro";

		}


		// delete medico
		@RequestMapping(value = "/medicos/{id}/delete")
		public String deleteMedico(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) {

			
			logger.debug("deleteMedico() : {}", id);
			System.out.println("deletando o medico ="+ id);
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Medico deletado com sucesso!");

			medicoDAO.deleteMedicoByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Medico deletado!");
			
			
			return "redirect:/medicos/";
			//return "listamedicospage";

		}

	

		
	}
	

