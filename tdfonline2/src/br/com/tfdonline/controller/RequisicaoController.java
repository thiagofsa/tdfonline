 package br.com.tfdonline.controller;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tfdonline.dao.RequisicaoDAOI;
import br.com.tfdonline.dao.LogTransacaoDAOI;
import br.com.tfdonline.dao.MedicoDAOI;
import br.com.tfdonline.dao.TransacaoDAOI;
import br.com.tfdonline.dao.PacienteDAOI;
import br.com.tfdonline.dao.ProcedimentoDAOI;
import br.com.tfdonline.modelo.Requisicao;
import br.com.tfdonline.modelo.Requisicao;
import br.com.tfdonline.modelo.Medico;
import br.com.tfdonline.modelo.Transacao;
import br.com.tfdonline.modelo.Usuario;
import br.com.tfdonline.modelo.Paciente;
import br.com.tfdonline.modelo.Procedimento;

	@Controller

	public class RequisicaoController {


		private final Logger logger = LoggerFactory.getLogger(RequisicaoController.class);

		@Autowired
		private RequisicaoDAOI requisicaoDAO;
		
		@Autowired
		private MedicoDAOI medicoDAO;
		
		@Autowired
		private PacienteDAOI pacienteDAO;	
		
		@Autowired
		private LogTransacaoDAOI logtransacaoDAO;
		
		@Autowired
		private TransacaoDAOI transacaoDAO;
		
		@Autowired
		private ProcedimentoDAOI procedimentoDAO;
	
	
			

		// list page
		@RequestMapping(value = "/requisicaos")
		public String showAllRequisicaos(Model model) {

			logger.debug("showAllRequisicaos()");
			model.addAttribute("requisicaos", requisicaoDAO.findAll());
			return "listarequisicaospage";
		}		
				
		// popula o form para a pesquisa de Requisicao
		@RequestMapping(value = {"/requisicaos/find" })
		    public String findRequisicao(Model model) {			 	
				
				Requisicao requisicao = new Requisicao();
				requisicao.setData(new Date());
			 	model.addAttribute("requisicaoForm", requisicao);
			 	return "findrequisicao";
		    }
		 
		// find2 page
		@RequestMapping(value = "/requisicaos/find2")
		public String showFindRequisicaoForm(@RequestParam("data") Date data, Model model) {
			System.out.println("chamando o requisicaos/find/descricao............"+data);
			logger.debug("Requisicaos.FindByData()");
			model.addAttribute("requisicaos", requisicaoDAO.findbyData(data));
			return "listarequisicaospage";
		}
		
		//populando um form para upload
				@RequestMapping(value = {"/selectarquivo/requisicaos/" })
				public String selectArquivo(@ModelAttribute("requisicaoForm")  Requisicao requisicao,
						 Model model, 
						final RedirectAttributes redirectAttributes, HttpServletRequest request){
				 	
					//primeira vez da exibicao...vamos popular o form
						
						model.addAttribute("requisicaoForm", requisicao);					
					 
					//colocando os dados da requisicao na sessao..			
					if  (request.getSession().getAttribute("requisicaoSession")==null) {
						request.getSession().setAttribute("requisicaoSession", requisicao);
					}
				 	return "uploadformpage";

			    }		

				@RequestMapping(value = "/upload")
			    public String handleFormUpload(@RequestParam(value= "name", required=false) String name,
			        @RequestParam("file") MultipartFile multipartfile, HttpServletRequest request, Model model) {

					Requisicao requisicao = null;
					
			        if (!multipartfile.isEmpty()) {
			            try {
							byte[] bytes = multipartfile.getBytes();
							
							String ApplicationPath= request.getServletContext().getInitParameter("upload.directory");
							System.out.println("UploadDir"+ ApplicationPath);
							File f1 = new File(ApplicationPath+File.separator+multipartfile.getOriginalFilename());
							multipartfile.transferTo(f1);
							
							requisicao = (Requisicao) request.getSession().getAttribute("requisicaoSession");
									
							if  (requisicao!=null) {
								requisicao.setCaminhoarquivo(multipartfile.getOriginalFilename());
								model.addAttribute("requisicaoForm", requisicao);		
								request.getSession().setAttribute("requisicaoSession", requisicao);
								
							}
							
							
							System.out.println("Passei pelo upload...");
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			            // store the bytes somewhere
			        
			            if (requisicao.isNew()) {
							return "requisicaocadastro";
						}else				
							return "requisicaoform";	
			            
			       } else {
			           return "uploadformpage";
			       }
			    }

		
		
				//populando um procedimento vazio e direcionando para a pagina de pesquisa
				@RequestMapping(value = {"/selectprocedimento/requisicaos/" })
				public String selectProcedimento(@ModelAttribute("requisicaoForm")  Requisicao requisicao,
						BindingResult result, Model model, 
						final RedirectAttributes redirectAttributes, HttpServletRequest request){
				 	
					//primeira vez da exibicao...vamos popular o form
						Procedimento procedimento = new Procedimento();
						model.addAttribute("procedimento", procedimento);	
					
						
					//colocando os dados da requisicao na sessao..			
					if  (request.getSession().getAttribute("requisicaoSession")==null) {
						request.getSession().setAttribute("requisicaoSession", requisicao);
					}
					
				 	return "selectprocedimentoform.requisicao";
				 	
			    }
				
				@RequestMapping(value = {"/requisicaos/selectprocedimento2" })
				public String selectProcedimento(@RequestParam("nome") String nome, Model model, @ModelAttribute("procedimento") Procedimento procedimento){
				 	
					System.out.println("chamando o requisicaos/SelectProcedimento2/............Procedimento.nome="+nome);
					
					model.addAttribute("procedimentos", procedimentoDAO.findbyNome(nome));
					System.out.println("ProcedimentoDAO chamado...");
					
					model.addAttribute("procedimento", procedimento);	
										
				 	return "selectprocedimentoform.requisicao";
				 	
			    }
				
				@RequestMapping(value = "/requisicaos/selectprocedimento/{id}")
				public String selectProcedimento(@PathVariable("id") int id, Model model, HttpServletRequest request) {
					
					Requisicao requisicao =(Requisicao) request.getSession().getAttribute("requisicaoSession");
					System.out.println("ID da requisicao na sessao em Select Procedimento=" + requisicao.getId());
					Procedimento procedimento  = procedimentoDAO.findByID(id);
					requisicao.setProcedimento(procedimento);
					request.getSession().setAttribute("requisicaoSession", requisicao);
					model.addAttribute("requisicaoForm", requisicao);
					
					if (requisicao.isNew()) {
						return "requisicaocadastro";
					}else
						return "requisicaoform";
					
				
				}
				

				
				//populando um paciente vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectpaciente/requisicaos/" })
		public String selectPaciente(@ModelAttribute("requisicaoForm")  Requisicao requisicao,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				Paciente paciente = new Paciente();
				model.addAttribute("paciente", paciente);					
			 
			//colocando os dados da requisicao na sessao..			
			if  (request.getSession().getAttribute("requisicaoSession")==null) {
				request.getSession().setAttribute("requisicaoSession", requisicao);
			}
		 	return "selectpacienteform";
		 	//return "redirect:/requisicaos/selectpaciente2";
	    }
				
		@RequestMapping(value = {"/requisicaos/selectpaciente2" })
		//public String selectPaciente(@RequestParam("descricao") String descricao, Model model, @ModelAttribute("paciente") Paciente paciente){
		public String selectPaciente(Model model, @ModelAttribute("paciente") Paciente paciente){
		 	
			System.out.println("chamando o requisicaos/SelectPaciente2/............Paciente.descricao=");
			
			model.addAttribute("pacientes", pacienteDAO.findbyName(paciente.getNome()));
			//model.addAttribute("pacientes", pacienteDAO.findAll());
			System.out.println("PacienteDAO chamado...");
			
			model.addAttribute("paciente", paciente);				
			
		 	return "selectpacienteform";		 	
	    }
		
		@RequestMapping(value = "/requisicaos/selectpaciente/{id}")
		public String selectPaciente(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			Requisicao requisicao =(Requisicao) request.getSession().getAttribute("requisicaoSession");
			System.out.println("ID da requisicao na sessao em Select Paciente=" + requisicao.getId());
			Paciente paciente  = pacienteDAO.findByID(id);
			requisicao.setPaciente(paciente);
			request.getSession().setAttribute("requisicaoSession", requisicao);
			model.addAttribute("requisicaoForm", requisicao);
			
			if (requisicao.isNew()) {
				return "requisicaocadastro";
			}else				
				return "requisicaoform";		
		}
		
		//populando uma vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectmedico/requisicaos/" })
		public String selectMedico(@ModelAttribute("requisicaoForm")  Requisicao requisicao,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				Medico medico = new Medico();
				model.addAttribute("medico", medico);	
				
			 
			//colocando os dados da requisicao na sessao..			
				if  (request.getSession().getAttribute("requisicaoSession")==null) {
					request.getSession().setAttribute("requisicaoSession", requisicao);
				}
			
		 	return "selectmedicoform";
		 	//return "redirect:/requisicaos/selectmedico";
	    }
		
		@RequestMapping(value = {"/requisicaos/selectmedico2" })
		//public String selectMedico(@RequestParam("nome") String nome, Model model, @ModelAttribute("medico") Medico medico){
		public String selectMedico(Model model, @ModelAttribute("medico") Medico medico){
		 	
			System.out.println("chamando o requisicaos/Selectmedico2/.....Medico.nome=");
			
			model.addAttribute("medicos", medicoDAO.findbyName(medico.getNome()));			
			//model.addAttribute("medicos", medicoDAO.findAll());
			System.out.println("MedicoDAO chamado...");
			
			model.addAttribute("medico", medico);				
			
		 	return "selectmedicoform";		 	
	    }
		
		
		@RequestMapping(value = "/requisicaos/selectmedico/{id}")
		public String selectMedico(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			Requisicao requisicao =(Requisicao) request.getSession().getAttribute("requisicaoSession");
			System.out.println("ID da requisicao na sessao em Select Medico=" + requisicao.getId());
			Medico medico  = medicoDAO.findByID(id);
			requisicao.setMedico(medico);
			request.getSession().setAttribute("requisicaoSession", requisicao);
			model.addAttribute("requisicaoForm", requisicao);
			
			if (requisicao.isNew()) {
				return "requisicaocadastro";
			}else				
				return "requisicaoform";			
		}
		
		//populando uma vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectmedicoautorizador/requisicaos/" })
		public String selectMedicoAutorizador(@ModelAttribute("requisicaoForm")  Requisicao requisicao,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				Medico medicoautorizador = new Medico();
				medicoautorizador.setAutorizador(1);
				model.addAttribute("medicoautorizador", medicoautorizador);	
				
			 
			//colocando os dados da requisicao na sessao..			
				if  (request.getSession().getAttribute("requisicaoSession")==null) {
					request.getSession().setAttribute("requisicaoSession", requisicao);
				}
			
		 	return "selectmedicoautorizadorform";
		 	//return "redirect:/requisicaos/selectmedicoautorizador";
	    }
		
		@RequestMapping(value = {"/requisicaos/selectmedicoautorizador2" })
		//public String selectMedico(@RequestParam("nome") String nome, Model model, @ModelAttribute("medicoautorizador") Medico medicoautorizador){
		public String selectMedicoAutorizador(Model model, @ModelAttribute("medicoautorizador") Medico medicoautorizador){
		 	
			System.out.println("chamando o requisicaos/Selectmedicoautorizador2/.....Medico.nome=");
			
			model.addAttribute("medicoautorizadors", medicoDAO.findbyAutorizadorNome(medicoautorizador.getNome()));			
			//model.addAttribute("medicoautorizadors", medicoautorizadorDAO.findAll());
			System.out.println("MedicoDAO chamado...");
			
			model.addAttribute("medicoautorizador", medicoautorizador);				
			
		 	return "selectmedicoautorizadorform";		 	
	    }
		
		
		@RequestMapping(value = "/requisicaos/selectmedicoautorizador/{id}")
		public String selectMedicoAutorizador(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			Requisicao requisicao =(Requisicao) request.getSession().getAttribute("requisicaoSession");
			System.out.println("ID da requisicao na sessao em Select Medico=" + requisicao.getId());
			Medico medicoautorizador  = medicoDAO.findByID(id);
			requisicao.setMedicoautorizador(medicoautorizador);
			request.getSession().setAttribute("requisicaoSession", requisicao);
			model.addAttribute("requisicaoForm", requisicao);
			
			if (requisicao.isNew()) {
				return "requisicaocadastro";
			}else				
				return "requisicaoform";			
		}
		
		
		@RequestMapping(value = "/requisicaos", method = RequestMethod.POST)
		public String saveOrUpdateRequisicao(@ModelAttribute("requisicaoForm")  Requisicao requisicao,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request) {
			
			System.out.println("Em saveOrUpdate, salvando ou atualizando requisicao.............");
			
			Requisicao requisicaoSession = (Requisicao) request.getSession().getAttribute("requisicaoSession");
			System.out.println("ID de requisicao da session="+ requisicaoSession.getId());
			
			System.out.println("ID de requisicao passada pelo form="+ requisicao.getId());
			System.out.println("ID de Medico passada pelo form="+ requisicao.getMedico().getId());
			System.out.println("ID de Paciente passada pelo form="+ requisicao.getPaciente().getId());		
			
						
			{

				// Add message to flash scope
				if(requisicao.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Viagem ADICIONADA com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Viagem ATUALIZADA com sucesso!");
				}
				
				System.out.println("SaveOrUpdate: Medico da requisicao="+ requisicao.getMedico().getNome());
				System.out.println("SaveOrUpdate: ID Medico da requisicao="+ requisicao.getMedico().getId());
				
				requisicaoDAO.saveOrUpdate(requisicao);
				System.out.println(".....Salvo ou atualizado o requisicao.....");
				System.out.println("redirecionando para listarequisicaospage");
				
//LOG DA TRANSACAO
				Transacao transacao =  transacaoDAO.isRegistravel(TransacaoDAOI.ENTIDADE_DISTRIBUICAO, TransacaoDAOI.ADD);
				
				if (transacao!=null) {
					
					Usuario usuarioLogado =((Usuario) request.getSession().getAttribute("usuarioLogado"));
					logtransacaoDAO.saveOrUpdate(usuarioLogado, transacao, requisicao.getId());	
					System.out.println("Salvando a transacao"+ TransacaoDAOI.ENTIDADE_DISTRIBUICAO + "-"+ TransacaoDAOI.ADD+ "no BD");
					
					
				}else {
					System.out.println("Transacao ADD Encaminhamento setada para não LOG");
				}
				
				//FIM LOG



				return "redirect:/requisicaos/";				
			}
		}

		// show update form , chamado pela listagem, para preencher o form com o id passado e devolvero form pra edicao para o requisicaoformpage
		@RequestMapping(value = "/requisicaos/{id}/update")
		public String showUpdateRequisicaoForm(@PathVariable("id") int id, Model model,HttpServletRequest request) {
			
			System.out.println("showUpdateRequisicaoFOrm() -->> ID Requisicao passado pelo form = " + id);
			
			System.out.println("Pesquisando  ID requisicao no DAO ---> ID = "+id);
			Requisicao requisicao = requisicaoDAO.findByID(id);			
			
			if (requisicao!=null) {
				 System.out.println("Requisicao Encontrada ------->Medico da requisicao="+ requisicao.getMedico().getNome());
				 requisicao.setId(id);
			}else
				System.out.println("Requisicao nao localizado");			
			
			model.addAttribute("requisicaoForm", requisicao);			
			request.getSession().setAttribute("requisicaoSession", requisicao);						
			
			return "requisicaoform";
		}

		// show requisicao (detalhes)
		@RequestMapping(value = "/requisicaos/{id}")
		public String showRequisicao(@PathVariable("id") int id, Model model) {

			logger.debug("showRequisicao() id: {}", id);
			System.out.println("-----> procurando pelo requisicao id="+id);

			Requisicao requisicao = requisicaoDAO.findByID(id);
			if (requisicao == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Requisicao não encontrado");
			}
			model.addAttribute("requisicao", requisicao);

			return "requisicaoshow";
		}
		
		//  /add requisicao form
		@RequestMapping(value = "/requisicaos/add", method = RequestMethod.GET)
		public String showAddRequisicaoForm(Model model, HttpServletRequest request) {

			logger.debug("showAddRequisicaoForm()");
						// set default value
			Requisicao requisicao = new Requisicao();
			requisicao.setId(-1);
			//requisicao.setDataviagem(new Date());			
			requisicao.setMedico(new Medico());
			requisicao.setPaciente(new Paciente());			
			
			
						
			model.addAttribute("requisicaoForm", requisicao);
			
			request.getSession().setAttribute("requisicaoSession", requisicao);			

			return "requisicaocadastro";
		}

		// delete requisicao
		@RequestMapping(value = "/requisicaos/{id}/delete")
		public String deleteRequisicao(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {
			
			logger.debug("deleteRequisicao() : {}", id);
			System.out.println("deletando a requisicao ="+ id);

			requisicaoDAO.deleteRequisicaoByID(new Integer(id));
				
			redirectAttributes.addFlashAttribute("msg", "Viagem DELETADA com sucesso!");
	
	//LOG DA TRANSACAO
			Transacao transacao =  transacaoDAO.isRegistravel(TransacaoDAOI.ENTIDADE_DISTRIBUICAO, TransacaoDAOI.DELETE);
			
			if (transacao!=null) {
				
				Usuario usuarioLogado =((Usuario) request.getSession().getAttribute("usuarioLogado"));
				logtransacaoDAO.saveOrUpdate(usuarioLogado, transacao, id);	
				System.out.println("Salvando a transacao"+ TransacaoDAOI.ENTIDADE_DISTRIBUICAO + "-"+ TransacaoDAOI.DELETE+ "no BD");
				
				
			}else {
				System.out.println("Transacao ADD Encaminhamento setada para não LOG");
			}
			
			//FIM LOG

			return "redirect:/requisicaos/";
		}
	}
	


