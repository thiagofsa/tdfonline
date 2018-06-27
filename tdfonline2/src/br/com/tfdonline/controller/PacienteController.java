package br.com.tfdonline.controller;


	import java.util.ArrayList;
	import java.util.LinkedHashMap;
	import java.util.List;
	import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.validation.BindingResult;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tfdonline.dao.PacienteDAOI;
import br.com.tfdonline.modelo.Paciente;

	@Controller
	public class PacienteController {


		private final Logger logger = LoggerFactory.getLogger(PacienteController.class);

		@Autowired
		private PacienteDAOI pacienteDAO;


		// list page
		@RequestMapping(value = "/pacientes")
		public String showAllPacientes(Model model) {

			logger.debug("showAllPacientes()");
			model.addAttribute("pacientes", pacienteDAO.findAll());
			return "listapacientespage";

		}
		
		@RequestMapping(value = "/pacientebloqueado")
		public String showPacienteBloqueado(Model model) {

			System.out.println("PacienteController.showPacienteBloqueado()");
			model.addAttribute("msg", "Usuário nao tem permissao para esta operacao");
			return "pacientebloqueado";

		}

		
				
		@RequestMapping(value = {"/pacientes/find" })
		    public String findPaciente(Model model) {
			 	
				
				Paciente paciente = new Paciente();
				paciente.setNome("Informe o nome da Paciente");
			 	model.addAttribute("pacienteForm", paciente);
			 	return "findpaciente";
		    }
		 
		// pesquisar page
		@RequestMapping(value = "/pacientes/find2")
		public String showFindPacienteForm(@RequestParam("nome") String nome, Model model) {
			System.out.println("chamando o pacientes/find/descricao............"+nome);
			logger.debug("Pacientes.FindByName()");
			model.addAttribute("pacientes", pacienteDAO.findbyName(nome));
			return "listapacientespage";

		}

		// save or update paciente
		// 1. @ModelAttribute bind form value
		// 2. @Validated form validator
		// 3. RedirectAttributes for flash value
		@RequestMapping(value = "/pacientes", method = RequestMethod.POST)
		//public String saveOrUpdatePaciente(@ModelAttribute("pacienteForm") @Validated Paciente paciente,
		public String saveOrUpdatePaciente(@ModelAttribute("pacienteForm")  Paciente paciente,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpSession session) {
			
			
			logger.debug("saveOrUpdatePaciente() : {}", paciente);
			System.out.println("Depois do formPaciente, salvando ou atualizando paciente.............");
			System.out.println("Nome do paciente a ser atualizada="+ paciente.getNome());
			
		/*	if (result.hasErrors()) {
				populateDefaultModel(model);
				return "pacienteform";
			} else*/ {

				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(paciente.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Paciente adicionado com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Paciente atualizado com sucesso!");
				}
				
				System.out.println("----->Nome ====="+ paciente.getNome());
				pacienteDAO.saveOrUpdate(paciente);
				System.out.println(".....Salvo ou atualizado o paciente.....");
				System.out.println("redirecionando para... \"redirect:/pacientes/\" + paciente.getId();");
				// POST/REDIRECT/GET
				return "redirect:/pacientes/" + paciente.getId();
				//return "/pacientes/" + paciente.getId();

				// POST/FORWARD/GET
				// return "paciente/list";

			}

		}

		// show update form
		@RequestMapping(value = "/pacientes/{id}/update")
		public String showUpdatePacienteForm(@PathVariable("id") int id, Model model) {

			logger.debug("showUpdatePacienteForm() : {}", id);
			System.out.println("showUpdatePacienteForm() : {} " + id);

			Paciente paciente = pacienteDAO.findByID(id);
			if (paciente!=null) {
				System.out.println("evocandoo showUpdateMOtoristaForm.......paciente encontrado="+paciente.getNome());
				
			}else
				System.out.println("Paciente nao localizado");
			
			model.addAttribute("pacienteForm", paciente);
			
			
			
			return "pacienteform";

		}

		// show paciente
		@RequestMapping(value = "/pacientes/{id}")
		public String showPaciente(@PathVariable("id") int id, Model model) {

			logger.debug("showPaciente() id: {}", id);
			System.out.println("-----> procurando pelo paciente id="+id);

			Paciente paciente = pacienteDAO.findByID(id);
			if (paciente == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Paciente não encontrado");
			}
			model.addAttribute("paciente", paciente);

			return "pacienteshow";

		}
		
		// show add paciente form
		@RequestMapping(value = "/pacientes/add", method = RequestMethod.GET)
		public String showAddPacienteForm(Model model) {

			logger.debug("showAddPacienteForm()");

			Paciente paciente = new Paciente();

			// set default value
			paciente.setId(-1);
			paciente.setNome("Nome do Paciente");
			paciente.setTelefone("8888-8888");
			paciente.setEmail("email");
			
			
						
			model.addAttribute("pacienteForm", paciente);

			

			return "pacientecadastro";

		}


		// delete paciente
		@RequestMapping(value = "/pacientes/{id}/delete")
		public String deletePaciente(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) {

			
			logger.debug("deletePaciente() : {}", id);
			System.out.println("deletando o paciente ="+ id);
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Paciente deletado com sucesso!");

			pacienteDAO.deletePacienteByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Paciente deletado!");
			
			
			return "redirect:/pacientes/";
			//return "listapacientespage";

		}

	

		
	}
	

