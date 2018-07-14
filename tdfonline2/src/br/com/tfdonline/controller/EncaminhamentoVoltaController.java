package br.com.tfdonline.controller;


import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
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

import br.com.tfdonline.dao.DistribuicaoDAOI;
import br.com.tfdonline.dao.EncaminhamentoDAOI;
import br.com.tfdonline.dao.EncaminhamentoVoltaDAOI;
import br.com.tfdonline.dao.PacienteDAOI;

import br.com.tfdonline.modelo.Distribuicao;
import br.com.tfdonline.modelo.Encaminhamento;
import br.com.tfdonline.modelo.EncaminhamentoVolta;

import br.com.tfdonline.modelo.Paciente;



	@Controller

	public class EncaminhamentoVoltaController {


		private final Logger logger = LoggerFactory.getLogger(EncaminhamentoVoltaController.class);

		@Autowired
		private EncaminhamentoVoltaDAOI encaminhamentovoltaDAO;
		
		@Autowired
		private DistribuicaoDAOI distribuicaoDAO;
		

		@Autowired
		private EncaminhamentoDAOI encaminhamentoDAO;
		
		@Autowired
		private PacienteDAOI pacienteDAO;
	
		
		@InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        sdf.setLenient(true);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	    }
		

		// list page
		@RequestMapping(value = "/encaminhamentovoltas")
		public String showAllEncaminhamentoVoltas(Model model) {

			logger.debug("showAllEncaminhamentoVoltas()");
			model.addAttribute("encaminhamentovoltas", encaminhamentovoltaDAO.findAll());
			return "listaencaminhamentovoltaspage";

		}
		
		//save or update
		
		@RequestMapping(value = "/encaminhamentovoltas", method = RequestMethod.POST)
		public String saveOrUpdateEncaminhamentoVolta(@ModelAttribute("encaminhamentovoltaForm")  EncaminhamentoVolta encaminhamentovolta,
						BindingResult result, Model model, 
						final RedirectAttributes redirectAttributes, HttpServletRequest request) {

				
					System.out.println("Em saveOrUpdate, salvando ou atualizando EncaminhamentoVolta.............");
					EncaminhamentoVolta encaminhamentovoltaSession = (EncaminhamentoVolta) request.getSession().getAttribute("encaminhamentovoltaSession");
					
					System.out.println("ID de encaminhamentovolta da session="+ encaminhamentovoltaSession.getId());
					System.out.println("ID de encaminhamentovolta passada pelo form="+ encaminhamentovolta.getId());
					System.out.println("ID de Distribuicao passada pelo form="+ encaminhamentovolta.getDistribuicao().getId());
					System.out.println("ID de paciente passada pelo form="+ encaminhamentovolta.getPaciente().getId());
					
				
					Distribuicao distribuicao = distribuicaoDAO.findByID(encaminhamentovolta.getDistribuicao().getId());
					Paciente paciente = encaminhamentovolta.getPaciente(); // = pacienteDAO.findByID(encaminhamentovolta.getPaciente().getId());
					
					//nao existe encamihamento de ida...
					//Encaminhamento encaminhamento = encaminhamentoDAO.findByID(encaminhamentovolta.getEncaminhamento().getId());
					
					
						// Add message to flash scope
						redirectAttributes.addFlashAttribute("css", "success");
						if(encaminhamentovolta.isNew()){
						  redirectAttributes.addFlashAttribute("msg", "EncaminhamentoVolta adicionado com sucesso!");
						}else{
						  redirectAttributes.addFlashAttribute("msg", "EncaminhamentoVolta atualizado com sucesso!");
						}
						
						System.out.println("SaveOrUpdate: Data da Distribuicao do encaminhamento="+ encaminhamentovolta.getDistribuicao().getDataviagem());
						System.out.println("SaveOrUpdate: ID Distribuicao da encaminhamento="+ encaminhamentovolta.getDistribuicao().getId());
						
						//calculo das vagas de acompanhantes e paciente
						//List<Acompanhante> acompanhantespacientemarcacao = acompanhanteDAO.findbyMarcacaoID(marcacao.getId());
						//marcacao.setAcompanhantespacientemarcacao(acompanhantespacientemarcacao);
						//int vagasMarcacao = marcacao.getAcompanhantespacientemarcacao().size();


						System.out.println("Vagas reservadas para o encaminhamento="+ encaminhamentovolta.getVagas());
						
						
											
						//**										
						//fim calculo vagas
						
						System.out.println(".....Salvo ou atualizado o encaminhamento.....");
						System.out.println("redirecionando para... \"redirect:/encaminhamentos/\" + encaminhamento.getId();");
						// POST/REDIRECT/GET
						
						System.out.println("Vagas reservadas :"+ encaminhamentovolta.getVagas());
						
						int vagas =  distribuicao.getVagas()- encaminhamentovolta.getVagas();
						System.out.println("Atualizando a  quantidade de vagas da distribuicao para: "+ vagas);
						distribuicao.setVagas(vagas);
						System.out.println("Setando a marcacao 	para encaminhada");
				
						
						distribuicaoDAO.saveOrUpdate(distribuicao);
						//pacienteDAO.saveOrUpdate(paciente);
						
						encaminhamentovoltaDAO.saveOrUpdate(encaminhamentovolta);
						
						
						
						return "redirect:/encaminhamentovoltas/" + encaminhamentovolta.getId();


				}
		

		@RequestMapping(value = {"/encaminhamentovoltas/lote0" })
	    public String encaminhamentovoltaLote0( Model model) {

			
		 	return "encaminhamentovoltalote";
	    }
		
		
				// 	Carrega as dsitribuicoes do dia e as marcacacoes em aberto a partir de uma data especifica
			@RequestMapping(value = {"/encaminhamentovoltas/lote" })
			    public String encaminhamentovoltaLote( @RequestParam("datadistribuicao")@DateTimeFormat(pattern = "yyyy-MM-dd") Date datadistribuicao, Model model) {
				
					
					if (datadistribuicao==null) {
						datadistribuicao =new Date();
					}
					
					model.addAttribute("distribuicaos", distribuicaoDAO.findbyData(datadistribuicao, datadistribuicao));
					//parei aqui..
					model.addAttribute("encaminhamentos", encaminhamentoDAO.findbyDataVoltaEncaminhamentoVoltaNaoGerado(datadistribuicao));
					model.addAttribute("datadistribuicao", new Date());
					
				 	return "encaminhamentovoltalote";
			    }
			 
			// 
			@RequestMapping(value = "/encaminhamentovoltas/lote2")
			public String encaminhamentovoltaLoteProcessa(@RequestParam("idsencaminhamento")String[] idsencaminhamento, @RequestParam("iddistribuicao")String iddistribuicao) {
				
				System.out.println("chamando o encaminhamentos/lote2............");
				System.out.println("ID Distribuicao recebido..." + iddistribuicao);
				System.out.println("IDs Encaminhamentos recebido.." );
				
				Encaminhamento encaminhamento= null;
				Distribuicao distribuicao=null;
				
				distribuicao= distribuicaoDAO.findByID(Integer.parseInt(iddistribuicao));
						
				for (int i=0; i<idsencaminhamento.length; i++) {
					
					EncaminhamentoVolta encaminhamentovolta = new EncaminhamentoVolta();
					
					System.out.println("ID Encaminhamento Volta" + idsencaminhamento[i]);
					encaminhamento = encaminhamentoDAO.findByID(Integer.parseInt(idsencaminhamento[i]));
					
					//calculo das vagas de acompanhantes e paciente
					encaminhamentovolta.setVagas(encaminhamento.getVagas());
						
					//**										
					//fim calculo vagas
					
					encaminhamentovolta.setId(-1);
					encaminhamentovolta.setDistribuicao(distribuicao);
					encaminhamentovolta.setDataviagem(distribuicao.getDataviagem());					
					encaminhamentovolta.setData(new Date());
					encaminhamentovolta.setEncaminhamento(encaminhamento);
					
					
					
					System.out.println("Salvando o encaminhamento volta" + i);

										
					int vagas =  distribuicao.getVagas() - encaminhamentovolta.getVagas();
					System.out.println("Atualizando a  quantidade de vagas da distribuicao para: "+ vagas);
					distribuicao.setVagas(vagas);
					System.out.println("Setando a encaminhamento para encaminhada");
					
					//sinalizando que já existe encaminhamento de volta gerado...
					encaminhamento.setEncaminhamentovoltagerado(1);
					
					encaminhamentoDAO.saveOrUpdate(encaminhamento);
					
					distribuicaoDAO.saveOrUpdate(distribuicao);
					
					encaminhamentovoltaDAO.saveOrUpdate(encaminhamentovolta);
					
					
					
				}
				System.out.println(idsencaminhamento.length + " encaminhamentos volta em lote salvos com sucesso!");
				
				
				return "encaminhamentovoltaloteprocessado";

			}		
				
		// popula o form para a pesquisa de EncaminhamentoVolta
		@RequestMapping(value = {"/encaminhamentovoltas/find" })
		    public String findEncaminhamentoVolta(Model model) {
			 	
				
				EncaminhamentoVolta encaminhamentovolta = new EncaminhamentoVolta();
				encaminhamentovolta.setDataviagem(new Date());
			 	model.addAttribute("encaminhamentoForm", encaminhamentovolta);
			 	return "findencaminhamentovolta";
		    }
		 
		// find2 page
		@RequestMapping(value = "/encaminhamentovoltas/find2")
		public String showFindEncaminhamentoVoltaForm(@RequestParam("dataviagemvolta")@DateTimeFormat(pattern = "yyyy-MM-dd") Date data, Model model) {
			System.out.println("chamando o encaminhamentovoltas/find/............"+data);
			logger.debug("EncaminhamentoVoltas.FindByData()");
			model.addAttribute("encaminhamentovoltas", encaminhamentovoltaDAO.findbyData(data, data));
			return "listaencaminhamentovoltaspage";

		}
		
			
		
		//populando uma vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectdistribuicao/encaminhamentovoltas/" })
		public String selectDistribuicaoEncaminhamentoVolta(@ModelAttribute("encaminhamentovoltaForm")  EncaminhamentoVolta encaminhamentovolta,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				Distribuicao distribuicao = new Distribuicao();
				model.addAttribute("distribuicao", distribuicao);	
				
			 
			//colocando os dados da encaminhamentovolta na sessao..			
				if  (request.getSession().getAttribute("encaminhamentovoltaSession")==null) {
					request.getSession().setAttribute("encaminhamentovoltaSession", encaminhamentovolta);
				}
			
		 	return "selectdistribuicaoencaminhamentovoltaform";
		 	
	    }
		
		@RequestMapping(value = {"/encaminhamentovoltas/selectdistribuicao2" })
		public String selectDistribuicaoEncaminhamentoVolta(@RequestParam("dataviagem")@DateTimeFormat(pattern = "yyyy-MM-dd") Date data, Model model, @ModelAttribute("distribuicao") Distribuicao distribuicao){
		 	
			System.out.println("chamando o encaminhamentovoltas/Selectdistribuicao2/............Distribuicao.data="+data);
			
			model.addAttribute("distribuicaos", distribuicaoDAO.findbyData(data));
			System.out.println("DistribuicaoDAO chamado...");
			
			model.addAttribute("distribuicao", distribuicao);	
			
			
		 	return "selectdistribuicaoencaminhamentovoltaform";
		 	
	    }
		
		
		@RequestMapping(value = "/encaminhamentovoltas/selectdistribuicao/{id}")
		public String selectDistribuicaoEncaminhamentoVolta(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			EncaminhamentoVolta encaminhamentovolta =(EncaminhamentoVolta) request.getSession().getAttribute("encaminhamentovoltaSession");
			System.out.println("ID da encaminhamentovolta na sessao em Select Distribuicao=" + encaminhamentovolta.getId());
			Distribuicao distribuicao  = distribuicaoDAO.findByID(id);
			encaminhamentovolta.setDistribuicao(distribuicao);
			request.getSession().setAttribute("encaminhamentovoltaSession", encaminhamentovolta);
			model.addAttribute("encaminhamentovoltaForm", encaminhamentovolta);
			
			return "encaminhamentovoltaform";
			
		
		}

		
		//populando uma vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectpaciente/encaminhamentovoltas/" })
		public String selectPacienteEncaminhamentoVolta(@ModelAttribute("encaminhamentovoltaForm")  EncaminhamentoVolta encaminhamentovolta,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				Paciente paciente = new Paciente();
				model.addAttribute("paciente", paciente);	
				
			 
			//colocando os dados da encaminhamentovolta na sessao..			
				if  (request.getSession().getAttribute("encaminhamentovoltaSession")==null) {
					request.getSession().setAttribute("encaminhamentovoltaSession", encaminhamentovolta);
				}
			
		 	return "selectpacienteencaminhamentovoltaform";
		 	
	    }
		
		@RequestMapping(value = {"/encaminhamentovoltas/selectpaciente2" })
		public String selectPacienteEncaminhamentoVolta(@RequestParam("nome") String nome, Model model, @ModelAttribute("paciente") Paciente paciente){
		 	
			System.out.println("chamando o encaminhamentovoltas/Selectpaciente2/............Paciente.nome="+nome);
			
			model.addAttribute("pacientes", pacienteDAO.findbyName(nome));
			System.out.println("PacienteDAO chamado...");
			
			model.addAttribute("paciente", paciente);	
			
			
		 	return "selectpacienteencaminhamentovoltaform";
		 	
	    }
		
		@RequestMapping(value = "/encaminhamentovoltas/selectpaciente/{id}")
		public String selectPacienteEncaminhamentoVolta(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			EncaminhamentoVolta encaminhamentovolta =(EncaminhamentoVolta) request.getSession().getAttribute("encaminhamentovoltaSession");
			System.out.println("ID da encaminhamentovolta na sessao em Select Paciente=" + encaminhamentovolta.getId());
			Paciente paciente  = pacienteDAO.findByID(id);
			encaminhamentovolta.setPaciente(paciente);
			request.getSession().setAttribute("encaminhamentovoltaSession", encaminhamentovolta);
			model.addAttribute("encaminhamentovoltaForm", encaminhamentovolta);			
			
			return "encaminhamentovoltaform";
			
		
		}
	
			

		// show update form , chamado pela listagem, para preencher o form com o id passado e devolvero form pra edicao para o encaminhamentovoltaformpage
		@RequestMapping(value = "/encaminhamentovoltas/{id}/update")
		public String showUpdateEncaminhamentoVoltaForm(@PathVariable("id") int id, Model model,HttpServletRequest request) {
			
			System.out.println("showUpdateEncaminhamentoVoltaFOrm() -->> ID EncaminhamentoVolta passado pelo form = " + id);
			
			System.out.println("Pesquisando  ID encaminhamentovolta no DAO ---> ID = "+id);
			EncaminhamentoVolta encaminhamentovolta = encaminhamentovoltaDAO.findByID(id);
			
			
			if (encaminhamentovolta!=null) {
				 System.out.println("EncaminhamentoVolta Encontrada ------->Data da Distribuicao da encaminhamentovolta="+ encaminhamentovolta.getDistribuicao().getDataviagem());
				 encaminhamentovolta.setId(id);
			}else
				System.out.println("EncaminhamentoVolta nao localizado");
			
			
			model.addAttribute("encaminhamentovoltaForm", encaminhamentovolta);
			
			request.getSession().setAttribute("encaminhamentovoltaSession", encaminhamentovolta);
						
			return "encaminhamentovoltaform";

		}

		// show encaminhamentovolta (detalhes)
		@RequestMapping(value = "/encaminhamentovoltas/{id}")
		public String showEncaminhamentoVolta(@PathVariable("id") int id, Model model) {

			logger.debug("showEncaminhamentoVolta() id: {}", id);
			System.out.println("-----> procurando pelo encaminhamentovolta id="+id);

			EncaminhamentoVolta encaminhamentovolta = encaminhamentovoltaDAO.findByID(id);
			if (encaminhamentovolta == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "EncaminhamentoVolta não encontrado");
			}
			model.addAttribute("encaminhamentovolta", encaminhamentovolta);

			return "encaminhamentovoltashow";

		}
		
		//  /add encaminhamentovolta form
		@RequestMapping(value = "/encaminhamentovoltas/add", method = RequestMethod.GET)
		public String showAddEncaminhamentoVoltaForm(Model model, HttpServletRequest request) {

			logger.debug("showAddEncaminhamentoVoltaForm()");
						// set default value
			EncaminhamentoVolta encaminhamentovolta = new EncaminhamentoVolta();
			encaminhamentovolta.setId(-1);
			encaminhamentovolta.setData(new Date());
			
			encaminhamentovolta.setDistribuicao(new Distribuicao());
			encaminhamentovolta.setEncaminhamento(new Encaminhamento());
			
						
			model.addAttribute("encaminhamentovoltaForm", encaminhamentovolta);
			
			request.getSession().setAttribute("encaminhamentovoltaSession", encaminhamentovolta);

			return "encaminhamentovoltaform";

		}

		// delete encaminhamentovolta
		@RequestMapping(value = "/encaminhamentovoltas/{id}/delete")
		public String deleteEncaminhamentoVolta(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) {

			
			logger.debug("deleteEncaminhamentoVolta() : {}", id);
			System.out.println("deletando a encaminhamentovolta ="+ id);
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "EncaminhamentoVolta deletado com sucesso!");

			//setando a flag de encaminhamentovolta para 0, na tabela encaminhamento (isso permite que se gere outro encaminhamentovolta posterioremente)
		
				EncaminhamentoVolta encaminhamentovolta = encaminhamentovoltaDAO.findByID(id);
				
				if (encaminhamentovolta.getEncaminhamento().getId()>0) {
				
					encaminhamentovolta.getEncaminhamento().setEncaminhamentovoltagerado(0);
					encaminhamentoDAO.saveOrUpdate(encaminhamentovolta.getEncaminhamento());
				
				}
				
			//fim set
				
			//devolvendo as vagas para a distribuicao...
			int vagasatuais = encaminhamentovolta.getDistribuicao().getVagas();
			vagasatuais = vagasatuais + encaminhamentovolta.getVagas();
			encaminhamentovolta.getDistribuicao().setVagas(vagasatuais);
			distribuicaoDAO.saveOrUpdate(encaminhamentovolta.getDistribuicao());
			
			//fim devolucao
			
			encaminhamentovoltaDAO.deleteEncaminhamentoVoltaByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "EncaminhamentoVolta deletado!");
			
			return "redirect:/encaminhamentovoltas/";
			//return "listaencaminhamentovoltaspage";

		}


		

	}
	

	