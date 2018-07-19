package br.com.tfdonline.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.boot.model.source.spi.IdentifierSourceNonAggregatedComposite;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tfdonline.dao.AcompanhanteDAOI;
import br.com.tfdonline.dao.BeneficioAvulsoDAOI;
import br.com.tfdonline.dao.EncaminhamentoDAOI;
import br.com.tfdonline.dao.BeneficioAvulsoDAOI;
import br.com.tfdonline.dao.PacienteDAOI;
import br.com.tfdonline.dao.ProcedimentoDAOI;
import br.com.tfdonline.dao.UnidadeSaudeDAOI;
import br.com.tfdonline.modelo.Acompanhante;
import br.com.tfdonline.modelo.Beneficio;
import br.com.tfdonline.modelo.Encaminhamento;
import br.com.tfdonline.modelo.Beneficio;
import br.com.tfdonline.modelo.Paciente;
import br.com.tfdonline.modelo.Procedimento;
import br.com.tfdonline.modelo.UnidadeSaude;
import br.com.tfdonline.util.DateUtils;


	@Controller
	public class BeneficioAvulsoController {


		private final Logger logger = LoggerFactory.getLogger(BeneficioAvulsoController.class);

		@Autowired
		private BeneficioAvulsoDAOI beneficioavulsoDAO;
		
		@Autowired
		private PacienteDAOI pacienteDAO;
		
		@Autowired
		private UnidadeSaudeDAOI unidadesaudeDAO;

		@Autowired
		private ProcedimentoDAOI procedimentoDAO;
		
		@Autowired
		private AcompanhanteDAOI acompanhanteDAO;
	
		
		@InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        sdf.setLenient(true);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	    }
		

		// list page
		@RequestMapping(value = "/beneficioavulsos")
		public String showAllBeneficioAvulsos(Model model) {

			logger.debug("showAllBeneficioAvulsos()");
			model.addAttribute("beneficioavulsos", beneficioavulsoDAO.findLast());
			return "listabeneficioavulsospage";

		}
		// popula o form para a pesquisa de BeneficioAvulso
		@RequestMapping(value = {"/beneficioavulsos/find" })
		    public String findBeneficioAvulso(Model model) {
				
				Beneficio beneficioavulso = new Beneficio();
				beneficioavulso.setDataviagemida(new Date());
				beneficioavulso.setDataviagemvolta(new Date());
				beneficioavulso.setPaciente(new Paciente());
				beneficioavulso.setProcedimento(new Procedimento());
				beneficioavulso.setUnidadesaude(new UnidadeSaude());
				
			 	model.addAttribute("beneficioavulsoForm", beneficioavulso);
			 	
			 	
			 	return "findbeneficioavulso";
		    }
		 
		// find2 page
		@RequestMapping(value = "/beneficioavulsos/find2")
		public String showFindBeneficioAvulsoForm(@RequestParam("nome") String nome, @RequestParam("datafim") Date datafim, @RequestParam("dataini") Date dataini, Model model) {
			
			
			List<Beneficio> beneficioavulsoLista= new ArrayList<Beneficio>();
					
			System.out.println("chamando o beneficioavulsos/find/............");
			System.out.println("Nome="+nome);
			System.out.println("dataini="+dataini);
			System.out.println("datafim="+datafim);
				
				if ((dataini!=null) && (datafim!=null)) {
				
					//busca com datas e nome
					if (nome.trim().length()>0) {
						beneficioavulsoLista= beneficioavulsoDAO.findbyNomeData(dataini, datafim, nome);
					
					
					} else {
					//apenas as datas	
						beneficioavulsoLista= beneficioavulsoDAO.findbyData(dataini, datafim);
						
					}
				
				}
			
			model.addAttribute("beneficioavulsos", beneficioavulsoLista);
			
			return "listabeneficioavulsospage";

		}
		

		//populando um procedimento vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectprocedimento/beneficioavulsos/" })
		public String selectProcedimento(@ModelAttribute("beneficioavulsoForm")  Beneficio beneficioavulso,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				Procedimento procedimento = new Procedimento();
				model.addAttribute("procedimento", procedimento);	
			
				
			//colocando os dados da beneficioavulso na sessao..			
			if  (request.getSession().getAttribute("beneficioavulsoSession")==null) {
				request.getSession().setAttribute("beneficioavulsoSession", beneficioavulso);
			}
			
		 	return "selectprocedimentoform.beneficioavulso";
		 	
	    }
		
		@RequestMapping(value = {"/beneficioavulsos/selectprocedimento2" })
		public String selectProcedimento(@RequestParam("nome") String nome, Model model, @ModelAttribute("procedimento") Procedimento procedimento){
		 	
			System.out.println("chamando o beneficioavulsos/SelectProcedimento2/............Procedimento.nome="+nome);
			
			model.addAttribute("procedimentos", procedimentoDAO.findbyNome(nome));
			System.out.println("ProcedimentoDAO chamado...");
			
			model.addAttribute("procedimento", procedimento);	
			

			
		 	return "selectprocedimentoform.beneficioavulso";
		 	
	    }
		
		@RequestMapping(value = "/beneficioavulsos/selectprocedimento/{id}")
		public String selectProcedimento(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			Beneficio beneficioavulso =(Beneficio) request.getSession().getAttribute("beneficioavulsoSession");
			System.out.println("ID da beneficioavulso na sessao em Select Procedimento=" + beneficioavulso.getId());
			Procedimento procedimento  = procedimentoDAO.findByID(id);
			beneficioavulso.setProcedimento(procedimento);
			request.getSession().setAttribute("beneficioavulsoSession", beneficioavulso);
			model.addAttribute("beneficioavulsoForm", beneficioavulso);
			
			model.addAttribute("acompanhantespaciente",beneficioavulso.getAcompanhantespacientebeneficioavulso());
			
			if (beneficioavulso.isNew()) {
				return "beneficioavulsocadastro";
			}else {
				
				return "beneficioavulsoform";
			}
			
		
		}
		
		
		//populando um unidadesaude vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectunidadesaude/beneficioavulsos/" })
		public String selectUnidadeSaude(@ModelAttribute("beneficioavulsoForm")  Beneficio beneficioavulso,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				UnidadeSaude unidadesaude = new UnidadeSaude();
				model.addAttribute("unidadesaude", unidadesaude);	
				
			 
			//colocando os dados da beneficioavulso na sessao..			
			if  (request.getSession().getAttribute("beneficioavulsoSession")==null) {
				request.getSession().setAttribute("beneficioavulsoSession", beneficioavulso);
			}
		 	return "selectunidadesaudeform.beneficioavulso";
	    }
		
		
		
		
		@RequestMapping(value = {"/beneficioavulsos/selectunidadesaude2" })
		public String selectUnidadeSaude(@RequestParam("descricao") String descricao, Model model, @ModelAttribute("unidadesaude") UnidadeSaude unidadesaude){
		 	
			System.out.println("chamando o beneficioavulsos/SelectUnidadeSaude2/............UnidadeSaude.descricao="+descricao);
			
			model.addAttribute("unidadesaudes", unidadesaudeDAO.findbyDescricao(descricao));
			System.out.println("UnidadeSaudeDAO chamado...");
			
			model.addAttribute("unidadesaude", unidadesaude);	
			
			
		 	return "selectunidadesaudeform.beneficioavulso";
		 	
	    }
		
		@RequestMapping(value = "/beneficioavulsos/selectunidadesaude/{id}")
		public String selectUnidadeSaude(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			Beneficio beneficioavulso =(Beneficio) request.getSession().getAttribute("beneficioavulsoSession");
			System.out.println("ID da beneficioavulso na sessao em Select UnidadeSaude=" + beneficioavulso.getId());
			UnidadeSaude unidadesaude  = unidadesaudeDAO.findByID(id);
			beneficioavulso.setUnidadesaude(unidadesaude);
			request.getSession().setAttribute("beneficioavulsoSession", beneficioavulso);
			model.addAttribute("beneficioavulsoForm", beneficioavulso);
			
			model.addAttribute("acompanhantespaciente",beneficioavulso.getAcompanhantespacientebeneficioavulso());
			
			if (beneficioavulso.isNew()) {
				return "beneficioavulsocadastro";
			}else {
				
				return "beneficioavulsoform";
			}
			
		
		}
		
		
		
		//populando uma vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectpaciente/beneficioavulsos/" })
		public String selectPaciente(@ModelAttribute("beneficioavulsoForm")  Beneficio beneficioavulso,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				Paciente paciente = new Paciente();
				model.addAttribute("paciente", paciente);	
				
			 
			//colocando os dados da beneficioavulso na sessao..			
				if  (request.getSession().getAttribute("beneficioavulsoSession")==null) {
					request.getSession().setAttribute("beneficioavulsoSession", beneficioavulso);
				}
			
		 	return "selectpacienteform.beneficioavulso";
		 	
	    }
		
		@RequestMapping(value = {"/beneficioavulsos/selectpaciente2" })
		public String selectPaciente(@RequestParam("nome") String nome, Model model, @ModelAttribute("paciente") Paciente paciente){
		 	
			System.out.println("chamando o beneficioavulsos/Selectpaciente2/............Paciente.nome="+nome);
			
			model.addAttribute("pacientes", pacienteDAO.findbyName(nome));
			System.out.println("PacienteDAO chamado...");
			
			model.addAttribute("paciente", paciente);	
			
			
		 	return "selectpacienteform.beneficioavulso";
		 	
	    }
		
		
		@RequestMapping(value = "/beneficioavulsos/selectpaciente/{id}")
		public String selectPaciente(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			Beneficio beneficioavulso =(Beneficio) request.getSession().getAttribute("beneficioavulsoSession");
			System.out.println("ID da beneficioavulso na sessao em Select Paciente=" + beneficioavulso.getId());
			Paciente paciente  = pacienteDAO.findByID(id);
			beneficioavulso.setPaciente(paciente);
			request.getSession().setAttribute("beneficioavulsoSession", beneficioavulso);
			model.addAttribute("beneficioavulsoForm", beneficioavulso);
			
			List<Acompanhante> acompanhantespaciente = beneficioavulso.getAcompanhantespacientebeneficioavulso();
			
			
			//calculando se será necessário a vaga para o paciente..
			int totalVagas =  1;
			
			try {
				if (DateUtils.getAge(paciente.getDatanascimento())<6) {
					totalVagas = totalVagas - 1 ;
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Erro de conversao de data de nascimento do paciente");
				e.printStackTrace();
			}
			
			beneficioavulso.setVagas( totalVagas);
			System.out.println("BeneficioAvulsoController.selectPaciente() --> Vagas reservadas "+ totalVagas );
			model.addAttribute("acompanhantespaciente",acompanhantespaciente);
			
			if (beneficioavulso.isNew()) {
				return "beneficioavulsocadastro";
			}else {
				
				return "beneficioavulsoform";
			}
			
		
		}
		
		//populando um grid de Acompanhantes para serem escolhidos
		
		@RequestMapping(value = {"/selectacompanhante/beneficioavulsos/" })
		public String selectAcompanhante(@ModelAttribute("beneficioavulsoForm")  Beneficio beneficioavulso,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
			Beneficio beneficioavulsoSession = (Beneficio) request.getSession().getAttribute("beneficioavulsoSession");
			
			System.out.println("Paciente selecionado na BeneficioAvulso-->"+ beneficioavulsoSession.getPaciente().getNome());
			
			List<Acompanhante> acompanhatespaciente = acompanhanteDAO.findbyPacienteID(beneficioavulsoSession.getPaciente().getId());
			
			model.addAttribute("acompanhantespaciente", acompanhatespaciente );

			
				
			request.getSession().setAttribute("beneficioavulsoSession", beneficioavulsoSession);
				
			
		 	return "selectacompanhanteform.beneficioavulso";
		 	
	    }
		
		
		@RequestMapping(value = {"/selectacompanhante/beneficioavulso2/" })
		public String selectAcompanhante2(@RequestParam(value="idsacompanhantepacientebeneficioavulso", required = false)String[] idsacompanhante, Model model,HttpServletRequest request ){
		 	
			System.out.println("chamando o beneficioavulsos/selectacompanhante2/............");
			
			//Processar o checkbox itens marcados....
			
			Beneficio beneficioavulsoSession = (Beneficio) request.getSession().getAttribute("beneficioavulsoSession");
			System.out.println("Paciente selecionado..."+ beneficioavulsoSession.getPaciente().getNome());
			
			//array de IDs passados pelos checkboxes...
			String [] acompanhantes= idsacompanhante;
			
			List<Acompanhante> acompanhantespacientebeneficioavulso =  new ArrayList<Acompanhante>();
			
			
			//exite pelo menos um acompanhamento
			if (idsacompanhante!=null) {
				
			
				System.out.println("Acompanhantes selecionados..."+ acompanhantes.length);
				
				String acomp =null;
				
				//convertendo o array de IDs numa lista de Acompanhantes
				
	
				for (int i=0; i< acompanhantes.length; i++) {
					acomp = acompanhantes[i];
					System.out.println("id acomp="+acomp);
					acompanhantespacientebeneficioavulso.add(acompanhanteDAO.findByID(new Integer (acomp)));
					System.out.println(acompanhantespacientebeneficioavulso.get(i).getNome());
					
				}
				//colocando os Acompanhantes no objeto beneficioavulso do modelo e da sessao.
				beneficioavulsoSession.setAcompanhantespacientebeneficioavulso(acompanhantespacientebeneficioavulso);
				
				
				System.out.println("Acompanhantes inseridos na sessao="+ beneficioavulsoSession.getAcompanhantespacientebeneficioavulso().size());
				
				int totalVagas =  (int) beneficioavulsoSession.getVagas();
				
				try { //se for uma crianca menor de seis anos 
					if (DateUtils.getAge(beneficioavulsoSession.getPaciente().getDatanascimento())<6) {
						totalVagas = totalVagas - 1 ;
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Erro de conversao de data de nascimento do paciente");
					e.printStackTrace();
				}
	
				
				totalVagas = totalVagas + acompanhantespacientebeneficioavulso.size();
				beneficioavulsoSession.setVagas(totalVagas);
				
			}
			
			model.addAttribute("beneficioavulsoForm", beneficioavulsoSession);
			request.getSession().setAttribute("beneficioavulsoSession", beneficioavulsoSession);
			model.addAttribute("acompanhantespaciente", acompanhantespacientebeneficioavulso);
			
			if (beneficioavulsoSession.isNew()) {
				return "beneficioavulsocadastro";
			}else {
				
				return "beneficioavulsoform";
			}
		 	
		 	 
		 	
	    }
	    
		
		
		
		
		@RequestMapping(value = "/beneficioavulsos", method = RequestMethod.POST)
		//public String saveOrUpdateBeneficioAvulso(@ModelAttribute("beneficioavulsoForm") @Validated BeneficioAvulso beneficioavulso,
		public String saveOrUpdateBeneficioAvulso(@ModelAttribute("beneficioavulsoForm")  Beneficio beneficioavulso,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request) {

			
			
			System.out.println("Em saveOrUpdate, salvando ou atualizando beneficioavulso.............");
			
			Beneficio beneficioavulsoSession = (Beneficio) request.getSession().getAttribute("beneficioavulsoSession");
			System.out.println("ID de beneficioavulso da session="+ beneficioavulsoSession.getId());
			
			System.out.println("ID de beneficioavulso passada pelo form="+ beneficioavulso.getId());
			System.out.println("ID de Paciente passada pelo form="+ beneficioavulso.getPaciente().getId());
			System.out.println("ID de UnidadeSaude passada pelo form="+ beneficioavulso.getUnidadesaude().getId());
			System.out.println("ID de Procedimento passada pelo form="+ beneficioavulso.getProcedimento().getId());
			System.out.println("Num de Acompanhante passada pelo form="+ beneficioavulsoSession.getAcompanhantespacientebeneficioavulso().size());
			
			
			beneficioavulso.setAcompanhantespacientebeneficioavulso(beneficioavulsoSession.getAcompanhantespacientebeneficioavulso());
									
			
		/*	if (result.hasErrors()) {
				populateDefaultModel(model);
				return "beneficioavulsoform";
			} else*/ {

				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(beneficioavulso.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "BeneficioAvulso adicionado com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "BeneficioAvulso atualizado com sucesso!");
				}
				
				System.out.println("SaveOrUpdate: Paciente da beneficioavulso="+ beneficioavulso.getPaciente().getNome());
				System.out.println("SaveOrUpdate: ID Paciente da beneficioavulso="+ beneficioavulso.getPaciente().getId());
				
				beneficioavulsoDAO.saveOrUpdate(beneficioavulso);
				
				
				System.out.println(".....Salvo ou atualizado o beneficioavulso.....");
				System.out.println("redirecionando para... \"redirect:/beneficioavulsos/\" + beneficioavulso.getId();");
				// POST/REDIRECT/GET
				
				
				return "redirect:/beneficioavulsos/" + beneficioavulso.getId();
				//return "/beneficioavulsos/" + beneficioavulso.getId();

				// POST/FORWARD/GET
				// return "beneficioavulso/list";

			}

		}

		// show update form , chamado pela listagem, para preencher o form com o id passado e devolver o form pra edicao para o beneficioavulsoformpage
		@RequestMapping(value = "/beneficioavulsos/{id}/update")
		public String showUpdateBeneficioAvulsoForm(@PathVariable("id") int id, Model model,HttpServletRequest request) {
			
			System.out.println("showUpdateBeneficioAvulsoFOrm() -->> ID BeneficioAvulso passado pelo form = " + id);
			
			System.out.println("Pesquisando  ID beneficioavulso no DAO ---> ID = "+id);
			Beneficio beneficioavulso = beneficioavulsoDAO.findByID(id);
			
			
			if (beneficioavulso!=null) {
				 System.out.println("BeneficioAvulso Encontrada ------->Paciente da beneficioavulso="+ beneficioavulso.getPaciente().getNome());
				 beneficioavulso.setId(id);
			}else
				System.out.println("BeneficioAvulso nao localizado");
			
			
			beneficioavulso.setAcompanhantespacientebeneficioavulso(acompanhanteDAO.findbyBeneficioID(id));
			model.addAttribute("acompanhantespaciente",beneficioavulso.getAcompanhantespacientebeneficioavulso());
			model.addAttribute("beneficioavulsoForm", beneficioavulso);
					
			request.getSession().setAttribute("beneficioavulsoSession", beneficioavulso);
						
			return "beneficioavulsoform";

		}
		
		
		

		// show beneficioavulso (detalhes)
		@RequestMapping(value = "/beneficioavulsos/{id}")
		public String showBeneficioAvulso(@PathVariable("id") int id, Model model) {

			logger.debug("showBeneficioAvulso() id: {}", id);
			System.out.println("-----> procurando pelo beneficioavulso id="+id);

			Beneficio beneficioavulso = beneficioavulsoDAO.findByID(id);
			if (beneficioavulso == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "BeneficioAvulso não encontrado");
			}
			model.addAttribute("beneficioavulso", beneficioavulso);

			return "beneficioavulsoshow";

		}
		
		//  /add beneficioavulso form
		@RequestMapping(value = "/beneficioavulsos/add", method = RequestMethod.GET)
		public String showAddBeneficioAvulsoForm(Model model, HttpServletRequest request) {

			logger.debug("showAddBeneficioAvulsoForm()");
						// set default value
			Beneficio beneficioavulso = new Beneficio();
			beneficioavulso.setId(-1);
			beneficioavulso.setDataviagemida(new Date());
			
			beneficioavulso.setPaciente(new Paciente());
			beneficioavulso.setUnidadesaude(new UnidadeSaude());
			beneficioavulso.setProcedimento(new Procedimento());
						
			model.addAttribute("beneficioavulsoForm", beneficioavulso);
			
			request.getSession().setAttribute("beneficioavulsoSession", beneficioavulso);

			

			return "beneficioavulsocadastro";

		}


		// delete beneficioavulso
		@RequestMapping(value = "/beneficioavulsos/{id}/delete")
		public String deleteBeneficioAvulso(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) {

			
			logger.debug("deleteBeneficioAvulso() : {}", id);
			System.out.println("deletando a beneficioavulso ="+ id);
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "BeneficioAvulso deletado com sucesso!");

			beneficioavulsoDAO.deleteBeneficioAvulsoByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "BeneficioAvulso deletado!");
			
			
			return "redirect:/beneficioavulsos/";
			//return "listabeneficioavulsospage";

		}


		

	}
	

