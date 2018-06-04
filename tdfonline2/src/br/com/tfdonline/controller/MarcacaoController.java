package br.com.tfdonline.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tfdonline.dao.MarcacaoDAOI;
import br.com.tfdonline.dao.PacienteDAOI;
import br.com.tfdonline.dao.ProcedimentoDAOI;
import br.com.tfdonline.dao.UnidadeSaudeDAOI;
import br.com.tfdonline.modelo.Marcacao;
import br.com.tfdonline.modelo.Paciente;
import br.com.tfdonline.modelo.Procedimento;
import br.com.tfdonline.modelo.UnidadeSaude;

	@Controller

	public class MarcacaoController {


		private final Logger logger = LoggerFactory.getLogger(MarcacaoController.class);

		@Autowired
		private MarcacaoDAOI marcacaoDAO;
		
		@Autowired
		private PacienteDAOI pacienteDAO;
		
		@Autowired
		private UnidadeSaudeDAOI unidadesaudeDAO;

		@Autowired
		private ProcedimentoDAOI procedimentoDAO;
	
		
		@InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        sdf.setLenient(true);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	    }
		

		// list page
		@RequestMapping(value = "/marcacaos")
		public String showAllMarcacaos(Model model) {

			logger.debug("showAllMarcacaos()");
			model.addAttribute("marcacaos", marcacaoDAO.findAll());
			return "listamarcacaospage";

		}
		
				
		// popula o form para a pesquisa de Marcacao
		@RequestMapping(value = {"/marcacaos/find" })
		    public String findMarcacao(Model model) {
			 	
				
				Marcacao marcacao = new Marcacao();
				marcacao.setData(new Date());
			 	model.addAttribute("marcacaoForm", marcacao);
			 	return "findmarcacao";
		    }
		 
		// find2 page
		@RequestMapping(value = "/marcacaos/find2")
		public String showFindMarcacaoForm(@RequestParam("data") Date data, Model model) {
			System.out.println("chamando o marcacaos/find/descricao............"+data);
			logger.debug("Marcacaos.FindByData()");
			model.addAttribute("marcacaos", marcacaoDAO.findbyData(data));
			return "listamarcacaospage";

		}
		

		//populando um procedimento vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectprocedimento/marcacaos/" })
		public String selectProcedimento(@ModelAttribute("marcacaoForm")  Marcacao marcacao,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				Procedimento procedimento = new Procedimento();
				model.addAttribute("procedimento", procedimento);	
			
				
			//colocando os dados da marcacao na sessao..			
			if  (request.getSession().getAttribute("marcacaoSession")==null) {
				request.getSession().setAttribute("marcacaoSession", marcacao);
			}
			
		 	return "selectprocedimentoform";
		 	
	    }
		
		@RequestMapping(value = {"/marcacaos/selectprocedimento2" })
		public String selectProcedimento(@RequestParam("nome") String nome, Model model, @ModelAttribute("procedimento") Procedimento procedimento){
		 	
			System.out.println("chamando o marcacaos/SelectProcedimento2/............Procedimento.nome="+nome);
			
			model.addAttribute("procedimentos", procedimentoDAO.findbyNome(nome));
			System.out.println("ProcedimentoDAO chamado...");
			
			model.addAttribute("procedimento", procedimento);	
			
		 	return "selectprocedimentoform";
		 	
	    }
		
		@RequestMapping(value = "/marcacaos/selectprocedimento/{id}")
		public String selectProcedimento(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			Marcacao marcacao =(Marcacao) request.getSession().getAttribute("marcacaoSession");
			System.out.println("ID da marcacao na sessao em Select Procedimento=" + marcacao.getId());
			Procedimento procedimento  = procedimentoDAO.findByID(id);
			marcacao.setProcedimento(procedimento);
			request.getSession().setAttribute("marcacaoSession", marcacao);
			model.addAttribute("marcacaoForm", marcacao);
			
			return "marcacaoform";
			
		
		}
		
		
		//populando um unidadesaude vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectunidadesaude/marcacaos/" })
		public String selectUnidadeSaude(@ModelAttribute("marcacaoForm")  Marcacao marcacao,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				UnidadeSaude unidadesaude = new UnidadeSaude();
				model.addAttribute("unidadesaude", unidadesaude);	
				
			 
			//colocando os dados da marcacao na sessao..			
			if  (request.getSession().getAttribute("marcacaoSession")==null) {
				request.getSession().setAttribute("marcacaoSession", marcacao);
			}
		 	return "selectunidadesaudeform";
	    }
		
		
		
		
		@RequestMapping(value = {"/marcacaos/selectunidadesaude2" })
		public String selectUnidadeSaude(@RequestParam("descricao") String descricao, Model model, @ModelAttribute("unidadesaude") UnidadeSaude unidadesaude){
		 	
			System.out.println("chamando o marcacaos/SelectUnidadeSaude2/............UnidadeSaude.descricao="+descricao);
			
			model.addAttribute("unidadesaudes", unidadesaudeDAO.findbyDescricao(descricao));
			System.out.println("UnidadeSaudeDAO chamado...");
			
			model.addAttribute("unidadesaude", unidadesaude);	
			
			
		 	return "selectunidadesaudeform";
		 	
	    }
		
		@RequestMapping(value = "/marcacaos/selectunidadesaude/{id}")
		public String selectUnidadeSaude(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			Marcacao marcacao =(Marcacao) request.getSession().getAttribute("marcacaoSession");
			System.out.println("ID da marcacao na sessao em Select UnidadeSaude=" + marcacao.getId());
			UnidadeSaude unidadesaude  = unidadesaudeDAO.findByID(id);
			marcacao.setUnidadesaude(unidadesaude);
			request.getSession().setAttribute("marcacaoSession", marcacao);
			model.addAttribute("marcacaoForm", marcacao);
			
			return "marcacaoform";
			
		
		}
		
		
		
		
		
		//populando uma vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectpaciente/marcacaos/" })
		public String selectPaciente(@ModelAttribute("marcacaoForm")  Marcacao marcacao,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				Paciente paciente = new Paciente();
				model.addAttribute("paciente", paciente);	
				
			 
			//colocando os dados da marcacao na sessao..			
				if  (request.getSession().getAttribute("marcacaoSession")==null) {
					request.getSession().setAttribute("marcacaoSession", marcacao);
				}
			
		 	return "selectpacienteform";
		 	
	    }
		
		@RequestMapping(value = {"/marcacaos/selectpaciente2" })
		public String selectPaciente(@RequestParam("nome") String nome, Model model, @ModelAttribute("paciente") Paciente paciente){
		 	
			System.out.println("chamando o marcacaos/Selectpaciente2/............Paciente.nome="+nome);
			
			model.addAttribute("pacientes", pacienteDAO.findbyName(nome));
			System.out.println("PacienteDAO chamado...");
			
			model.addAttribute("paciente", paciente);	
			
			
		 	return "selectpacienteform";
		 	
	    }
		
		
		@RequestMapping(value = "/marcacaos/selectpaciente/{id}")
		public String selectPaciente(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			Marcacao marcacao =(Marcacao) request.getSession().getAttribute("marcacaoSession");
			System.out.println("ID da marcacao na sessao em Select Paciente=" + marcacao.getId());
			Paciente paciente  = pacienteDAO.findByID(id);
			marcacao.setPaciente(paciente);
			request.getSession().setAttribute("marcacaoSession", marcacao);
			model.addAttribute("marcacaoForm", marcacao);
			
			return "marcacaoform";
			
		
		}
		
		

		
		@RequestMapping(value = "/marcacaos", method = RequestMethod.POST)
		//public String saveOrUpdateMarcacao(@ModelAttribute("marcacaoForm") @Validated Marcacao marcacao,
		public String saveOrUpdateMarcacao(@ModelAttribute("marcacaoForm")  Marcacao marcacao,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request) {

			
			//marcacao = request.getSession().getAttribute("marcacaoSession");
			
			System.out.println("Em saveOrUpdate, salvando ou atualizando marcacao.............");
			
			Marcacao marcacaoSession = (Marcacao) request.getSession().getAttribute("marcacaoSession");
			System.out.println("ID de marcacao da session="+ marcacaoSession.getId());
			
			System.out.println("ID de marcacao passada pelo form="+ marcacao.getId());
			System.out.println("ID de Paciente passada pelo form="+ marcacao.getPaciente().getId());
			System.out.println("ID de UnidadeSaude passada pelo form="+ marcacao.getUnidadesaude().getId());
			System.out.println("ID de Procedimento passada pelo form="+ marcacao.getProcedimento().getId());
			
			
			
		/*	if (result.hasErrors()) {
				populateDefaultModel(model);
				return "marcacaoform";
			} else*/ {

				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(marcacao.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Marcacao adicionado com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Marcacao atualizado com sucesso!");
				}
				
				System.out.println("SaveOrUpdate: Paciente da marcacao="+ marcacao.getPaciente().getNome());
				System.out.println("SaveOrUpdate: ID Paciente da marcacao="+ marcacao.getPaciente().getId());
				
				marcacaoDAO.saveOrUpdate(marcacao);
				System.out.println(".....Salvo ou atualizado o marcacao.....");
				System.out.println("redirecionando para... \"redirect:/marcacaos/\" + marcacao.getId();");
				// POST/REDIRECT/GET
				
				
				return "redirect:/marcacaos/" + marcacao.getId();
				//return "/marcacaos/" + marcacao.getId();

				// POST/FORWARD/GET
				// return "marcacao/list";

			}

		}

		// show update form , chamado pela listagem, para preencher o form com o id passado e devolvero form pra edicao para o marcacaoformpage
		@RequestMapping(value = "/marcacaos/{id}/update")
		public String showUpdateMarcacaoForm(@PathVariable("id") int id, Model model,HttpServletRequest request) {
			
			System.out.println("showUpdateMarcacaoFOrm() -->> ID Marcacao passado pelo form = " + id);
			
			System.out.println("Pesquisando  ID marcacao no DAO ---> ID = "+id);
			Marcacao marcacao = marcacaoDAO.findByID(id);
			
			
			if (marcacao!=null) {
				 System.out.println("Marcacao Encontrada ------->Paciente da marcacao="+ marcacao.getPaciente().getNome());
				 marcacao.setId(id);
			}else
				System.out.println("Marcacao nao localizado");
			
			
			model.addAttribute("marcacaoForm", marcacao);
			
			request.getSession().setAttribute("marcacaoSession", marcacao);
						
			return "marcacaoform";

		}

		// show marcacao (detalhes)
		@RequestMapping(value = "/marcacaos/{id}")
		public String showMarcacao(@PathVariable("id") int id, Model model) {

			logger.debug("showMarcacao() id: {}", id);
			System.out.println("-----> procurando pelo marcacao id="+id);

			Marcacao marcacao = marcacaoDAO.findByID(id);
			if (marcacao == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Marcacao não encontrado");
			}
			model.addAttribute("marcacao", marcacao);

			return "marcacaoshow";

		}
		
		//  /add marcacao form
		@RequestMapping(value = "/marcacaos/add", method = RequestMethod.GET)
		public String showAddMarcacaoForm(Model model, HttpServletRequest request) {

			logger.debug("showAddMarcacaoForm()");
						// set default value
			Marcacao marcacao = new Marcacao();
			marcacao.setId(-1);
			marcacao.setData(new Date());
			
			marcacao.setPaciente(new Paciente());
			marcacao.setUnidadesaude(new UnidadeSaude());
			marcacao.setProcedimento(new Procedimento());
						
			model.addAttribute("marcacaoForm", marcacao);
			
			request.getSession().setAttribute("marcacaoSession", marcacao);

			

			return "marcacaoform";

		}


		// delete marcacao
		@RequestMapping(value = "/marcacaos/{id}/delete")
		public String deleteMarcacao(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) {

			
			logger.debug("deleteMarcacao() : {}", id);
			System.out.println("deletando a marcacao ="+ id);
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Marcacao deletado com sucesso!");

			marcacaoDAO.deleteMarcacaoByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Marcacao deletado!");
			
			
			return "redirect:/marcacaos/";
			//return "listamarcacaospage";

		}

	

	}
	

