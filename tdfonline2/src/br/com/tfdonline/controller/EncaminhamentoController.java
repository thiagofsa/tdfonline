package br.com.tfdonline.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import br.com.tfdonline.dao.AcompanhanteDAOI;
import br.com.tfdonline.dao.DistribuicaoDAOI;
import br.com.tfdonline.dao.EncaminhamentoDAOI;
import br.com.tfdonline.dao.LogTransacaoDAOI;
import br.com.tfdonline.dao.MarcacaoDAOI;
import br.com.tfdonline.dao.TransacaoDAOI;
import br.com.tfdonline.modelo.Acompanhante;
import br.com.tfdonline.modelo.Distribuicao;
import br.com.tfdonline.modelo.Encaminhamento;
import br.com.tfdonline.modelo.Marcacao;
import br.com.tfdonline.modelo.Transacao;
import br.com.tfdonline.modelo.Usuario;
import br.com.tfdonline.util.DateUtils;


	@Controller

	public class EncaminhamentoController {


		private final Logger logger = LoggerFactory.getLogger(EncaminhamentoController.class);

		@Autowired
		private EncaminhamentoDAOI encaminhamentoDAO;
		
		@Autowired
		private DistribuicaoDAOI distribuicaoDAO;
		
		@Autowired
		private MarcacaoDAOI marcacaoDAO;
		
		@Autowired
		private AcompanhanteDAOI acompanhanteDAO;
	
		@Autowired
		private LogTransacaoDAOI logtransacaoDAO;
		
		@Autowired
		private TransacaoDAOI transacaoDAO;
		
		
		@InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        sdf.setLenient(true);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	    }
		

		// list page
		@RequestMapping(value = "/encaminhamentos")
		public String showAllEncaminhamentos(Model model) {

			logger.debug("showAllEncaminhamentos()");
			model.addAttribute("encaminhamentos", encaminhamentoDAO.findAll());
			return "listaencaminhamentospage";

		}
		
		//save or update
		
				@RequestMapping(value = "/encaminhamentos", method = RequestMethod.POST)
				public String saveOrUpdateEncaminhamento(@ModelAttribute("encaminhamentoForm")  Encaminhamento encaminhamento,
						BindingResult result, Model model, 
						final RedirectAttributes redirectAttributes, HttpServletRequest request) {

				
					System.out.println("Em saveOrUpdate, salvando ou atualizando Encaminhamento.............");
					Encaminhamento encaminhamentoSession = (Encaminhamento) request.getSession().getAttribute("encaminhamentoSession");
					
					System.out.println("ID de encaminhamento da session="+ encaminhamentoSession.getId());
					System.out.println("ID de encaminhamento passada pelo form="+ encaminhamento.getId());
					System.out.println("ID de Distribuicao passada pelo form="+ encaminhamento.getDistribuicao().getId());
					System.out.println("ID de Marcacao passada pelo form="+ encaminhamento.getMarcacao().getId());
				
					Distribuicao distribuicao = distribuicaoDAO.findByID(encaminhamento.getDistribuicao().getId());
					Marcacao marcacao = marcacaoDAO.findByID(encaminhamento.getMarcacao().getId());
					
					
						// Add message to flash scope
						redirectAttributes.addFlashAttribute("css", "success");
						if(encaminhamento.isNew()){
						  redirectAttributes.addFlashAttribute("msg", "Encaminhamento adicionado com sucesso!");
						}else{
						  redirectAttributes.addFlashAttribute("msg", "Encaminhamento atualizado com sucesso!");
						}
						
						System.out.println("SaveOrUpdate: Data da Distribuicao do encaminhamento="+ encaminhamento.getDistribuicao().getDataviagem());
						System.out.println("SaveOrUpdate: ID Distribuicao da encaminhamento="+ encaminhamento.getDistribuicao().getId());
						
						//calculo das vagas de acompanhantes e paciente
						//List<Acompanhante> acompanhantespacientemarcacao = acompanhanteDAO.findbyMarcacaoID(marcacao.getId());
						//marcacao.setAcompanhantespacientemarcacao(acompanhantespacientemarcacao);
						//int vagasMarcacao = marcacao.getAcompanhantespacientemarcacao().size();
						System.out.println("************quantidade de acompanhantes da marcacao="+ marcacao.getVagas());
						
						encaminhamento.setVagas(marcacao.getVagas());
						System.out.println("Vagas reservadas para a marcacao="+ encaminhamento.getVagas());
						
						
											
						//**										
						//fim calculo vagas
						
						System.out.println(".....Salvo ou atualizado o encaminhamento.....");
						System.out.println("redirecionando para... \"redirect:/encaminhamentos/\" + encaminhamento.getId();");
						// POST/REDIRECT/GET
						
						System.out.println("Vagas reservadas :"+ encaminhamento.getVagas());
						
						int vagas =  distribuicao.getVagas()- marcacao.getVagas();
						System.out.println("Atualizando a  quantidade de vagas da distribuicao para: "+ vagas);
						distribuicao.setVagas(vagas);
						System.out.println("Setando a marcacao 	para encaminhada");
						marcacao.setEncaminhada(1);
						
						
						distribuicaoDAO.saveOrUpdate(distribuicao);
						marcacaoDAO.saveOrUpdate(marcacao);
						encaminhamentoDAO.saveOrUpdate(encaminhamento);
						
						
						//LOG DA TRANSACAO
						Transacao transacao =  transacaoDAO.isRegistravel(TransacaoDAOI.ENTIDADE_ENCAMINHAMENTO, TransacaoDAOI.ADD);
						
						if (transacao!=null) {
							
							Usuario usuarioLogado =((Usuario) request.getSession().getAttribute("usuarioLogado"));
							logtransacaoDAO.saveOrUpdate(usuarioLogado, transacao, encaminhamento.getId());	
							System.out.println("Salvando a transacao"+ TransacaoDAOI.ENTIDADE_ENCAMINHAMENTO + "-"+ TransacaoDAOI.ADD+ "no BD");
							
							
						}else {
							System.out.println("Transacao ADD Encaminhamento setada para não LOG");
						}
						
						
						System.out.println("Enviarei SMS para o numero "+marcacao.getPaciente().getTelefone());
						/* try {
						SMSSender.sendMessage("Testando o SMS para o TFDControl", marcacao.getPaciente().getTelefone());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Problema no envio do SMS");
						e.printStackTrace();
					}
					*/
					  //fim sms
						
						return "redirect:/encaminhamentos/" + encaminhamento.getId();


				}
		
	// 	Carrega as dsitribuicoes do dia e as marcacacoes em aberto a partir de uma data especifica
			@RequestMapping(value = {"/encaminhamentos/lote" })
			    public String encaminhamentoLoteForm( Model model) {
				 	 Date dataini = null, datafim = null;
					
					if (dataini==null) {
						dataini =new Date();
					}
					if (datafim==null) {
						datafim =new Date();
					}
				
					model.addAttribute("distribuicaos", distribuicaoDAO.findbyData(dataini, datafim));
					//parei aqui..
					model.addAttribute("marcacaos", marcacaoDAO.findbyNaoEncaminhadas(dataini, datafim));
					
				 	return "encaminhamentolote";
			    }
			 
			// 
			@RequestMapping(value = "/encaminhamentos/lote2")
			public String encaminhamentoLoteProcessa(@RequestParam("idsmarcacao")String[] idsmarcacao, @RequestParam("iddistribuicao")String iddistribuicao,HttpServletRequest request) {
				
				System.out.println("chamando o encaminhamentos/lote2............");
				System.out.println("ID Distribuicao recebido..." + iddistribuicao);
				System.out.println("IDs Marcacao recebido.." );
				
				Marcacao marcacao= null;
				Distribuicao distribuicao=null;
				
				distribuicao= distribuicaoDAO.findByID(Integer.parseInt(iddistribuicao));
						
				for (int i=0; i<idsmarcacao.length; i++) {
					
					Encaminhamento encaminhamento = new Encaminhamento();
					
					System.out.println("ID Marcacao " + idsmarcacao[i]);
					marcacao = marcacaoDAO.findByID(Integer.parseInt(idsmarcacao[i]));
					
					//calculo das vagas de acompanhantes e paciente
					List<Acompanhante> acompanhantespacientemarcacao = acompanhanteDAO.findbyMarcacaoID(marcacao.getId());
					marcacao.setAcompanhantespacientemarcacao(acompanhantespacientemarcacao);
					int vagasMarcacao = marcacao.getAcompanhantespacientemarcacao().size();
					System.out.println("************quantidade de acompanhantes da marcacao="+ vagasMarcacao);
					
					//somando acompanhantes e paciente
					
					vagasMarcacao = vagasMarcacao + 1;
					
					try {
						//se paciente for criança de colo...
						if (DateUtils.getAge(marcacao.getPaciente().getDatanascimento())<6) {
							vagasMarcacao = vagasMarcacao - 1;
							System.out.println("Paciente de colo, diminuindo uma vaga da reserva.....");
							
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("Erro de conversão de data");
						e.printStackTrace();
					}
						
					//**										
					//fim calculo vagas
					
					encaminhamento.setId(-1);
					encaminhamento.setDistribuicao(distribuicao);
					encaminhamento.setMarcacao(marcacao);
					encaminhamento.setDataviagem(marcacao.getDataviagem());
					encaminhamento.setEmbarcado(0);
					encaminhamento.setVagas(vagasMarcacao);
					encaminhamento.setData(new Date());
					
					
					System.out.println("Salvando o encaminhamento " + i);

					System.out.println("Vagas reservadas :"+ vagasMarcacao);
					
					int vagas =  distribuicao.getVagas() - vagasMarcacao;
					System.out.println("Atualizando a  quantidade de vagas da distribuicao para: "+ vagas);
					distribuicao.setVagas(vagas);
					System.out.println("Setando a marcacao para encaminhada");
					marcacao.setEncaminhada(1);
					marcacaoDAO.saveOrUpdate(marcacao);
					distribuicaoDAO.saveOrUpdate(distribuicao);
					encaminhamentoDAO.saveOrUpdate(encaminhamento);
					
					
					//LOG DA TRANSACAO
					Transacao transacao =  transacaoDAO.isRegistravel(TransacaoDAOI.ENTIDADE_ENCAMINHAMENTO, TransacaoDAOI.ADD);
					
					if (transacao!=null) {
						
						Usuario usuarioLogado =((Usuario) request.getSession().getAttribute("usuarioLogado"));
						logtransacaoDAO.saveOrUpdate(usuarioLogado, transacao, encaminhamento.getId());	
						System.out.println("Salvando a transacao"+ TransacaoDAOI.ENTIDADE_ENCAMINHAMENTO + "-"+ TransacaoDAOI.ADD+ "no BD");
						
						
					}else {
						System.out.println("Transacao ADD ENC LOTE setada para não LOG");
					}
					
				
					System.out.println("Enviarei SMS para o numero "+marcacao.getPaciente().getTelefone());
					/* try {
					SMSSender.sendMessage("Testando o SMS para o TFDControl", marcacao.getPaciente().getTelefone());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Problema no envio do SMS");
					e.printStackTrace();
				}
				*/
				  //fim sms
				 
					
					
					
				}//end FOR
				
				System.out.println(idsmarcacao.length + " encaminhamentos em lote salvos com sucesso!");
				
				
				return "encaminhamentoloteprocessado";

			}		
				
		// popula o form para a pesquisa de Encaminhamento
		@RequestMapping(value = {"/encaminhamentos/find" })
		    public String findEncaminhamento(Model model) {
			 	
				
				Encaminhamento encaminhamento = new Encaminhamento();
				encaminhamento.setDataviagem(new Date());
			 	model.addAttribute("encaminhamentoForm", encaminhamento);
			 	return "findencaminhamento";
		    }
		 
		// find2 page
		@RequestMapping(value = "/encaminhamentos/find2")
		public String showFindEncaminhamentoForm(@RequestParam("dataviagem")@DateTimeFormat(pattern = "yyyy-MM-dd") Date data, Model model) {
			System.out.println("chamando o encaminhamentos/find/descricao............"+data);
			logger.debug("Encaminhamentos.FindByData()");
			model.addAttribute("encaminhamentos", encaminhamentoDAO.findbyData(data, data));
			return "listaencaminhamentospage";

		}
		

		//populando um marcacao vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectmarcacao/encaminhamentos/" })
		public String selectMarcacao(@ModelAttribute("encaminhamentoForm")  Encaminhamento encaminhamento,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				Marcacao marcacao = new Marcacao();
				model.addAttribute("marcacao", marcacao);	
			
				
			//colocando os dados da encaminhamento na sessao..			
			if  (request.getSession().getAttribute("encaminhamentoSession")==null) {
				request.getSession().setAttribute("encaminhamentoSession", encaminhamento);
			}
			
		 	return "selectmarcacaoform";
		 	
	    }
		
		@RequestMapping(value = {"/encaminhamentos/selectmarcacao2" })
		public String selectMarcacao(@RequestParam("dataviagem") Date data, Model model, @ModelAttribute("marcacao") Marcacao marcacao){
		 	
			System.out.println("chamando o encaminhamentos/SelectMarcacao2/............Marcacao.nome="+data);
			
			model.addAttribute("marcacaos", marcacaoDAO.findbyNaoEncaminhadas(data, data));
			System.out.println("MarcacaoDAO chamado...");
			
			model.addAttribute("marcacao", marcacao);	
			
		 	return "selectmarcacaoform";
		 	
	    }
		
		@RequestMapping(value = "/encaminhamentos/selectmarcacao/{id}")
		public String selectMarcacao(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			Encaminhamento encaminhamento =(Encaminhamento) request.getSession().getAttribute("encaminhamentoSession");
			System.out.println("ID da encaminhamento na sessao em Select Marcacao=" + encaminhamento.getId());
			Marcacao marcacao  = marcacaoDAO.findByID(id);
			encaminhamento.setMarcacao(marcacao);
			request.getSession().setAttribute("encaminhamentoSession", encaminhamento);
			model.addAttribute("encaminhamentoForm", encaminhamento);
			
			return "encaminhamentoform";
			
		
		}
				
		
		//populando uma vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectdistribuicao/encaminhamentos/" })
		public String selectDistribuicao(@ModelAttribute("encaminhamentoForm")  Encaminhamento encaminhamento,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				
			Encaminhamento encaminhamentoSession = (Encaminhamento) request.getSession().getAttribute("encaminhamentoSession");
			
				Distribuicao distribuicao = new Distribuicao();
				distribuicao.setDataviagem(encaminhamentoSession.getMarcacao().getDataviagem());
				model.addAttribute("distribuicao", distribuicao);	
				model.addAttribute("distribuicaos", distribuicaoDAO.findbyData(distribuicao.getDataviagem()));
			 
			//colocando os dados da encaminhamento na sessao..			
				
					request.getSession().setAttribute("encaminhamentoSession", encaminhamentoSession);
				
			
		 	return "selectdistribuicaoform";
		 	
	    }
		
		@RequestMapping(value = {"/encaminhamentos/selectdistribuicao2" })
		public String selectDistribuicao(@RequestParam("dataviagem")@DateTimeFormat(pattern = "yyyy-MM-dd") Date data, Model model, @ModelAttribute("distribuicao") Distribuicao distribuicao){
		 	
			
			
			model.addAttribute("distribuicaos", distribuicaoDAO.findbyData(data));
			System.out.println("DistribuicaoDAO chamado...");
			
			model.addAttribute("distribuicao", distribuicao);	
			
			
		 	return "selectdistribuicaoform";
		 	
	    }
		
		
		@RequestMapping(value = "/encaminhamentos/selectdistribuicao/{id}")
		public String selectDistribuicao(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			Encaminhamento encaminhamento =(Encaminhamento) request.getSession().getAttribute("encaminhamentoSession");
			System.out.println("ID da encaminhamento na sessao em Select Distribuicao=" + encaminhamento.getId());
			Distribuicao distribuicao  = distribuicaoDAO.findByID(id);
			encaminhamento.setDistribuicao(distribuicao);
			request.getSession().setAttribute("encaminhamentoSession", encaminhamento);
			model.addAttribute("encaminhamentoForm", encaminhamento);
			
			return "encaminhamentoform";
			
		
		}
			
		

		// show update form , chamado pela listagem, para preencher o form com o id passado e devolvero form pra edicao para o encaminhamentoformpage
		@RequestMapping(value = "/encaminhamentos/{id}/update")
		public String showUpdateEncaminhamentoForm(@PathVariable("id") int id, Model model,HttpServletRequest request) {
			
			System.out.println("showUpdateEncaminhamentoFOrm() -->> ID Encaminhamento passado pelo form = " + id);
			
			System.out.println("Pesquisando  ID encaminhamento no DAO ---> ID = "+id);
			Encaminhamento encaminhamento = encaminhamentoDAO.findByID(id);
			
			
			if (encaminhamento!=null) {
				 System.out.println("Encaminhamento Encontrada ------->Data da Distribuicao da encaminhamento="+ encaminhamento.getDistribuicao().getDataviagem());
				 encaminhamento.setId(id);
			}else
				System.out.println("Encaminhamento nao localizado");
			
			
			model.addAttribute("encaminhamentoForm", encaminhamento);
			
			request.getSession().setAttribute("encaminhamentoSession", encaminhamento);
						
			return "encaminhamentoform";

		}

		// show encaminhamento (detalhes)
		@RequestMapping(value = "/encaminhamentos/{id}")
		public String showEncaminhamento(@PathVariable("id") int id, Model model) {

			logger.debug("showEncaminhamento() id: {}", id);
			System.out.println("-----> procurando pelo encaminhamento id="+id);

			Encaminhamento encaminhamento = encaminhamentoDAO.findByID(id);
			if (encaminhamento == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Encaminhamento não encontrado");
			}
			model.addAttribute("encaminhamento", encaminhamento);

			return "encaminhamentoshow";

		}
		
		//  /add encaminhamento form
		@RequestMapping(value = "/encaminhamentos/add", method = RequestMethod.GET)
		public String showAddEncaminhamentoForm(Model model, HttpServletRequest request) {

			logger.debug("showAddEncaminhamentoForm()");
						// set default value
			Encaminhamento encaminhamento = new Encaminhamento();
			encaminhamento.setId(-1);
			encaminhamento.setData(new Date());
			
			encaminhamento.setDistribuicao(new Distribuicao());
			encaminhamento.setMarcacao(new Marcacao());
						
			model.addAttribute("encaminhamentoForm", encaminhamento);
			
			request.getSession().setAttribute("encaminhamentoSession", encaminhamento);

			return "encaminhamentoform";

		}


		// delete encaminhamento
		@RequestMapping(value = "/encaminhamentos/{id}/delete")
		public String deleteEncaminhamento(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes,HttpServletRequest request) {

			
			logger.debug("deleteEncaminhamento() : {}", id);
			System.out.println("deletando a encaminhamento ="+ id);
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Encaminhamento deletado com sucesso!");

			encaminhamentoDAO.deleteEncaminhamentoByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Encaminhamento deletado!");
			
			//LOG DA TRANSACAO
			Transacao transacao =  transacaoDAO.isRegistravel(TransacaoDAOI.ENTIDADE_ENCAMINHAMENTO, TransacaoDAOI.DELETE);
			
			if (transacao!=null) {
				
				Usuario usuarioLogado =((Usuario) request.getSession().getAttribute("usuarioLogado"));
				logtransacaoDAO.saveOrUpdate(usuarioLogado, transacao, id);	
				System.out.println("Salvando a transacao"+ TransacaoDAOI.ENTIDADE_ENCAMINHAMENTO + "-"+ TransacaoDAOI.DELETE+ "no BD");
				
				
			}else {
				System.out.println("Transacao DELETE Encaminhamento setada para não LOG");
			}
			
			//FIM LOG
			
			return "redirect:/encaminhamentos/";
			//return "listaencaminhamentospage";

		}


		

	}
	

