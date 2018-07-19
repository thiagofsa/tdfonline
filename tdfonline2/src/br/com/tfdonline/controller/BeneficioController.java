package br.com.tfdonline.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import br.com.tfdonline.dao.BeneficioDAOI;
import br.com.tfdonline.dao.EncaminhamentoDAOI;
import br.com.tfdonline.modelo.Acompanhante;
import br.com.tfdonline.modelo.Beneficio;
import br.com.tfdonline.modelo.Encaminhamento;


	@Controller
	public class BeneficioController {


		private final Logger logger = LoggerFactory.getLogger(BeneficioController.class);

		@Autowired
		private BeneficioDAOI beneficioDAO;
		@Autowired
		private EncaminhamentoDAOI encaminhamentoDAO;
		@Autowired
		private AcompanhanteDAOI acompanhanteDAO;

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
		
		
		//populando um encaminhamento vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectencaminhamento/beneficios/" })
		public String selectEncaminhamento(@ModelAttribute("beneficioForm")  Beneficio beneficio,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				Encaminhamento encaminhamento = new Encaminhamento();
				model.addAttribute("encaminhamento", encaminhamento);	
				
				;
			
				
			//colocando os dados da beneficio na sessao..			
			if  (request.getSession().getAttribute("beneficioSession")==null) {
				request.getSession().setAttribute("beneficioSession", beneficio);
			}
			
		 	return "selectbeneficioencaminhamentoform";
		 	
	    }
		
		@RequestMapping(value = {"/beneficios/selectencaminhamento2" })
		public String selectEncaminhamento(@RequestParam("nomepaciente") String nomepaciente, Model model, @ModelAttribute("encaminhamento") Encaminhamento encaminhamento){
		 	
			System.out.println("chamando o beneficios/SelectEncaminhamento2/............Pacietne.nome="+nomepaciente);
			
			model.addAttribute("encaminhamentos", encaminhamentoDAO.findbyNomePaciente(nomepaciente));
			System.out.println("EncaminhamentoDAO chamado...");
			
			model.addAttribute("encaminhamento", encaminhamento);	
			
		 	return "selectbeneficioencaminhamentoform";
		 	
	    }
		
		@RequestMapping(value = "/beneficios/selectencaminhamento/{id}")
		public String selectEncaminhamento(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			Beneficio beneficio =(Beneficio) request.getSession().getAttribute("beneficioSession");
			System.out.println("ID do encaminhamento selecionado em Select Encaminhamento=" + id);
			
			Encaminhamento encaminhamento  = encaminhamentoDAO.findByID(id);
			System.out.println("Encaminhamento.id form encaminhamentoDAO="+ encaminhamento.getId());
			
			
			System.out.println("*************Paciente da marcacao="+ encaminhamento.getMarcacao().getPaciente().getNome());		
			//beneficio.setEncaminhamento(encaminhamento);
			
			
			beneficio.setPaciente(encaminhamento.getMarcacao().getPaciente());
			beneficio.setUnidadesaude(encaminhamento.getMarcacao().getUnidadesaude());
			beneficio.setProcedimento(encaminhamento.getMarcacao().getProcedimento());
			
			beneficio.setVagas(encaminhamento.getVagas());
			
						
			beneficio.setDataviagemida(encaminhamento.getDataviagem());
			beneficio.setDataviagemvolta(encaminhamento.getDataviagemvolta());
			
			List<Acompanhante> acompanhantes = acompanhanteDAO.findbyMarcacaoID(encaminhamento.getMarcacao().getId());		
			beneficio.setAcompanhantespacientebeneficioavulso(acompanhantes);
			
			
			request.getSession().setAttribute("beneficioSession", beneficio);
			model.addAttribute("beneficioForm", beneficio);
			
			
			
			model.addAttribute("acompanhantespaciente", acompanhantes);
			
			
			return "beneficioform";
			
		
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
			
			
			//**18-07beneficio.setDataviagemida(beneficio.getEncaminhamento().getDataviagem());
			//**18-07beneficio.setDataviagemvolta(beneficio.getEncaminhamento().getDataviagemvolta());


				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(beneficio.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Beneficio adicionado com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Beneficio atualizado com sucesso!");
				}
				
				
				
				beneficioDAO.saveOrUpdate(beneficio);
				
				
				System.out.println(".....Salvo ou atualizado o beneficio.....");
				System.out.println("redirecionando para... \"redirect:/beneficios/\" + beneficio.getId();");
				// POST/REDIRECT/GET
				return "redirect:/beneficios/" + beneficio.getId();
				//return "/beneficios/" + beneficio.getId();

				// POST/FORWARD/GET
				// return "beneficio/list";



		}

		// show update form
		@RequestMapping(value = "/beneficios/{id}/update")
		public String showUpdateBeneficioForm(@PathVariable("id") int id, Model model) {

			logger.debug("showUpdateBeneficioForm() : {}", id);
			System.out.println("showUpdateBeneficioForm() : {} " + id);

			Beneficio beneficio = beneficioDAO.findByID(id);
			if (beneficio!=null) {
				
				
			}else
				System.out.println("Beneficio nao localizado");
			
			model.addAttribute("beneficioForm", beneficio);
			
			
			
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
			model.addAttribute("beneficioForm", beneficio);

			return "beneficioshow";

		}
		
		// show add beneficio form
		@RequestMapping(value = "/beneficios/add", method = RequestMethod.GET)
		public String showAddBeneficioForm(Model model, HttpServletRequest request) {

			logger.debug("showAddBeneficioForm()");

			Beneficio beneficio = new Beneficio();

			// set default value
			beneficio.setId(-1);
			
			model.addAttribute("beneficioForm", beneficio);
			request.getSession().setAttribute("beneficioSession", beneficio);
			

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

	

		

	}
	

