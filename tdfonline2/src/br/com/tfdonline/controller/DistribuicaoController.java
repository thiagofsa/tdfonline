 package br.com.tfdonline.controller;


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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tfdonline.dao.DistribuicaoDAOI;
import br.com.tfdonline.dao.LogTransacaoDAOI;
import br.com.tfdonline.dao.MotoristaDAOI;
import br.com.tfdonline.dao.TransacaoDAOI;
import br.com.tfdonline.dao.VeiculoDAOI;
import br.com.tfdonline.modelo.Distribuicao;
import br.com.tfdonline.modelo.Motorista;
import br.com.tfdonline.modelo.Transacao;
import br.com.tfdonline.modelo.Usuario;
import br.com.tfdonline.modelo.Veiculo;

	@Controller

	public class DistribuicaoController {


		private final Logger logger = LoggerFactory.getLogger(DistribuicaoController.class);

		@Autowired
		private DistribuicaoDAOI distribuicaoDAO;
		
		@Autowired
		private MotoristaDAOI motoristaDAO;
		
		@Autowired
		private VeiculoDAOI veiculoDAO;	
		
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
		@RequestMapping(value = "/distribuicaos")
		public String showAllDistribuicaos(Model model) {

			logger.debug("showAllDistribuicaos()");
			model.addAttribute("distribuicaos", distribuicaoDAO.findAll());
			return "listadistribuicaospage";
		}		
				
		// popula o form para a pesquisa de Distribuicao
		@RequestMapping(value = {"/distribuicaos/find" })
		    public String findDistribuicao(Model model) {			 	
				
				Distribuicao distribuicao = new Distribuicao();
				distribuicao.setDataviagem(new Date());
			 	model.addAttribute("distribuicaoForm", distribuicao);
			 	return "finddistribuicao";
		    }
		 
		// find2 page
		@RequestMapping(value = "/distribuicaos/find2")
		public String showFindDistribuicaoForm(@RequestParam("data") Date data, Model model) {
			System.out.println("chamando o distribuicaos/find/descricao............"+data);
			logger.debug("Distribuicaos.FindByData()");
			model.addAttribute("distribuicaos", distribuicaoDAO.findbyData(data));
			return "listadistribuicaospage";
		}
		
		//populando um veiculo vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectveiculo/distribuicaos/" })
		public String selectVeiculo(@ModelAttribute("distribuicaoForm")  Distribuicao distribuicao,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				Veiculo veiculo = new Veiculo();
				model.addAttribute("veiculo", veiculo);					
			 
			//colocando os dados da distribuicao na sessao..			
			if  (request.getSession().getAttribute("distribuicaoSession")==null) {
				request.getSession().setAttribute("distribuicaoSession", distribuicao);
			}
		 	//return "selectveiculoform";
		 	return "redirect:/distribuicaos/selectveiculo2";
	    }
				
		@RequestMapping(value = {"/distribuicaos/selectveiculo2" })
		//public String selectVeiculo(@RequestParam("descricao") String descricao, Model model, @ModelAttribute("veiculo") Veiculo veiculo){
		public String selectVeiculo(Model model, @ModelAttribute("veiculo") Veiculo veiculo){
		 	
			System.out.println("chamando o distribuicaos/SelectVeiculo2/............Veiculo.descricao=");
			
			//model.addAttribute("veiculos", veiculoDAO.findbyDescricao(descricao));
			model.addAttribute("veiculos", veiculoDAO.findAll());
			System.out.println("VeiculoDAO chamado...");
			
			model.addAttribute("veiculo", veiculo);				
			
		 	return "selectveiculoform";		 	
	    }
		
		@RequestMapping(value = "/distribuicaos/selectveiculo/{id}")
		public String selectVeiculo(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			Distribuicao distribuicao =(Distribuicao) request.getSession().getAttribute("distribuicaoSession");
			System.out.println("ID da distribuicao na sessao em Select Veiculo=" + distribuicao.getId());
			Veiculo veiculo  = veiculoDAO.findByID(id);
			distribuicao.setVeiculo(veiculo);
			request.getSession().setAttribute("distribuicaoSession", distribuicao);
			model.addAttribute("distribuicaoForm", distribuicao);
			
			return "distribuicaoform";		
		}
		
		//populando uma vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectmotorista/distribuicaos/" })
		public String selectMotorista(@ModelAttribute("distribuicaoForm")  Distribuicao distribuicao,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				Motorista motorista = new Motorista();
				model.addAttribute("motorista", motorista);	
				
			 
			//colocando os dados da distribuicao na sessao..			
				if  (request.getSession().getAttribute("distribuicaoSession")==null) {
					request.getSession().setAttribute("distribuicaoSession", distribuicao);
				}
			
		 	//return "selectmotoristaform";
		 	return "redirect:/distribuicaos/selectmotorista2";
	    }
		
		@RequestMapping(value = {"/distribuicaos/selectmotorista2" })
		//public String selectMotorista(@RequestParam("nome") String nome, Model model, @ModelAttribute("motorista") Motorista motorista){
		public String selectMotorista(Model model, @ModelAttribute("motorista") Motorista motorista){
		 	
			System.out.println("chamando o distribuicaos/Selectmotorista2/.....Motorista.nome=");
			
			//model.addAttribute("motoristas", motoristaDAO.findbyName(nome));			
			model.addAttribute("motoristas", motoristaDAO.findAll());
			System.out.println("MotoristaDAO chamado...");
			
			model.addAttribute("motorista", motorista);				
			
		 	return "selectmotoristaform";		 	
	    }
		
		
		@RequestMapping(value = "/distribuicaos/selectmotorista/{id}")
		public String selectMotorista(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			Distribuicao distribuicao =(Distribuicao) request.getSession().getAttribute("distribuicaoSession");
			System.out.println("ID da distribuicao na sessao em Select Motorista=" + distribuicao.getId());
			Motorista motorista  = motoristaDAO.findByID(id);
			distribuicao.setMotorista(motorista);
			request.getSession().setAttribute("distribuicaoSession", distribuicao);
			model.addAttribute("distribuicaoForm", distribuicao);
			
			return "distribuicaoform";		
		}
		
		@RequestMapping(value = "/distribuicaos", method = RequestMethod.POST)
		public String saveOrUpdateDistribuicao(@ModelAttribute("distribuicaoForm")  Distribuicao distribuicao,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request) {
			
			System.out.println("Em saveOrUpdate, salvando ou atualizando distribuicao.............");
			
			Distribuicao distribuicaoSession = (Distribuicao) request.getSession().getAttribute("distribuicaoSession");
			System.out.println("ID de distribuicao da session="+ distribuicaoSession.getId());
			
			System.out.println("ID de distribuicao passada pelo form="+ distribuicao.getId());
			System.out.println("ID de Motorista passada pelo form="+ distribuicao.getMotorista().getId());
			System.out.println("ID de Veiculo passada pelo form="+ distribuicao.getVeiculo().getId());		
			
			distribuicao.setVagas(distribuicao.getVeiculo().getVagas());			
			{

				// Add message to flash scope
				if(distribuicao.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Viagem ADICIONADA com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Viagem ATUALIZADA com sucesso!");
				}
				
				System.out.println("SaveOrUpdate: Motorista da distribuicao="+ distribuicao.getMotorista().getNome());
				System.out.println("SaveOrUpdate: ID Motorista da distribuicao="+ distribuicao.getMotorista().getId());
				
				distribuicaoDAO.saveOrUpdate(distribuicao);
				System.out.println(".....Salvo ou atualizado o distribuicao.....");
				System.out.println("redirecionando para listadistribuicaospage");
				
//LOG DA TRANSACAO
				Transacao transacao =  transacaoDAO.isRegistravel(TransacaoDAOI.ENTIDADE_DISTRIBUICAO, TransacaoDAOI.ADD);
				
				if (transacao!=null) {
					
					Usuario usuarioLogado =((Usuario) request.getSession().getAttribute("usuarioLogado"));
					logtransacaoDAO.saveOrUpdate(usuarioLogado, transacao, distribuicao.getId());	
					System.out.println("Salvando a transacao"+ TransacaoDAOI.ENTIDADE_DISTRIBUICAO + "-"+ TransacaoDAOI.ADD+ "no BD");
					
					
				}else {
					System.out.println("Transacao ADD Encaminhamento setada para não LOG");
				}
				
				//FIM LOG



				return "redirect:/distribuicaos/";				
			}
		}

		// show update form , chamado pela listagem, para preencher o form com o id passado e devolvero form pra edicao para o distribuicaoformpage
		@RequestMapping(value = "/distribuicaos/{id}/update")
		public String showUpdateDistribuicaoForm(@PathVariable("id") int id, Model model,HttpServletRequest request) {
			
			System.out.println("showUpdateDistribuicaoFOrm() -->> ID Distribuicao passado pelo form = " + id);
			
			System.out.println("Pesquisando  ID distribuicao no DAO ---> ID = "+id);
			Distribuicao distribuicao = distribuicaoDAO.findByID(id);			
			
			if (distribuicao!=null) {
				 System.out.println("Distribuicao Encontrada ------->Motorista da distribuicao="+ distribuicao.getMotorista().getNome());
				 distribuicao.setId(id);
			}else
				System.out.println("Distribuicao nao localizado");			
			
			model.addAttribute("distribuicaoForm", distribuicao);			
			request.getSession().setAttribute("distribuicaoSession", distribuicao);						
			
			return "distribuicaoform";
		}

		// show distribuicao (detalhes)
		@RequestMapping(value = "/distribuicaos/{id}")
		public String showDistribuicao(@PathVariable("id") int id, Model model) {

			logger.debug("showDistribuicao() id: {}", id);
			System.out.println("-----> procurando pelo distribuicao id="+id);

			Distribuicao distribuicao = distribuicaoDAO.findByID(id);
			if (distribuicao == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Distribuicao não encontrado");
			}
			model.addAttribute("distribuicao", distribuicao);

			return "distribuicaoshow";
		}
		
		//  /add distribuicao form
		@RequestMapping(value = "/distribuicaos/add", method = RequestMethod.GET)
		public String showAddDistribuicaoForm(Model model, HttpServletRequest request) {

			logger.debug("showAddDistribuicaoForm()");
						// set default value
			Distribuicao distribuicao = new Distribuicao();
			distribuicao.setId(-1);
			//distribuicao.setDataviagem(new Date());			
			distribuicao.setMotorista(new Motorista());
			distribuicao.setVeiculo(new Veiculo());			
			
			distribuicao.setVagas(0);
						
			model.addAttribute("distribuicaoForm", distribuicao);
			
			request.getSession().setAttribute("distribuicaoSession", distribuicao);			

			return "distribuicaocadastro";
		}

		// delete distribuicao
		@RequestMapping(value = "/distribuicaos/{id}/delete")
		public String deleteDistribuicao(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {
			
			logger.debug("deleteDistribuicao() : {}", id);
			System.out.println("deletando a distribuicao ="+ id);

			distribuicaoDAO.deleteDistribuicaoByID(new Integer(id));
				
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

			return "redirect:/distribuicaos/";
		}
	}
	


