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

import br.com.tfdonline.dao.EncaminhamentoDAOI;
import br.com.tfdonline.dao.DistribuicaoDAOI;
import br.com.tfdonline.dao.MarcacaoDAOI;

import br.com.tfdonline.modelo.Encaminhamento;
import br.com.tfdonline.modelo.Distribuicao;
import br.com.tfdonline.modelo.Marcacao;


	@Controller

	public class EncaminhamentoController {


		private final Logger logger = LoggerFactory.getLogger(EncaminhamentoController.class);

		@Autowired
		private EncaminhamentoDAOI encaminhamentoDAO;
		
		@Autowired
		private DistribuicaoDAOI distribuicaoDAO;
		
	
		@Autowired
		private MarcacaoDAOI marcacaoDAO;
	
		
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
		
				
		// popula o form para a pesquisa de Encaminhamento
		@RequestMapping(value = {"/encaminhamentos/find" })
		    public String findEncaminhamento(Model model) {
			 	
				
				Encaminhamento encaminhamento = new Encaminhamento();
				encaminhamento.setData(new Date());
			 	model.addAttribute("encaminhamentoForm", encaminhamento);
			 	return "findencaminhamento";
		    }
		 
		// find2 page
		@RequestMapping(value = "/encaminhamentos/find2")
		public String showFindEncaminhamentoForm(@RequestParam("data") Date data, Model model) {
			System.out.println("chamando o encaminhamentos/find/descricao............"+data);
			logger.debug("Encaminhamentos.FindByData()");
			model.addAttribute("encaminhamentos", encaminhamentoDAO.findbyData(data));
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
		public String selectMarcacao(@RequestParam("data") Date data, Model model, @ModelAttribute("marcacao") Marcacao marcacao){
		 	
			System.out.println("chamando o encaminhamentos/SelectMarcacao2/............Marcacao.nome="+data);
			
			model.addAttribute("marcacaos", marcacaoDAO.findbyData(data));
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
				Distribuicao distribuicao = new Distribuicao();
				model.addAttribute("distribuicao", distribuicao);	
				
			 
			//colocando os dados da encaminhamento na sessao..			
				if  (request.getSession().getAttribute("encaminhamentoSession")==null) {
					request.getSession().setAttribute("encaminhamentoSession", encaminhamento);
				}
			
		 	return "selectdistribuicaoform";
		 	
	    }
		
		@RequestMapping(value = {"/encaminhamentos/selectdistribuicao2" })
		public String selectDistribuicao(@RequestParam("data") Date data, Model model, @ModelAttribute("distribuicao") Distribuicao distribuicao){
		 	
			System.out.println("chamando o encaminhamentos/Selectdistribuicao2/............Distribuicao.data="+data);
			
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
		
	
		
		
		
		
		@RequestMapping(value = "/encaminhamentos", method = RequestMethod.POST)
		//public String saveOrUpdateEncaminhamento(@ModelAttribute("encaminhamentoForm") @Validated Encaminhamento encaminhamento,
		public String saveOrUpdateEncaminhamento(@ModelAttribute("encaminhamentoForm")  Encaminhamento encaminhamento,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request) {

			
			//encaminhamento = request.getSession().getAttribute("encaminhamentoSession");
			
			System.out.println("Em saveOrUpdate, salvando ou atualizando encaminhamento.............");
			
			Encaminhamento encaminhamentoSession = (Encaminhamento) request.getSession().getAttribute("encaminhamentoSession");
			System.out.println("ID de encaminhamento da session="+ encaminhamentoSession.getId());
			
			System.out.println("ID de encaminhamento passada pelo form="+ encaminhamento.getId());
			System.out.println("ID de Distribuicao passada pelo form="+ encaminhamento.getDistribuicao().getId());
		
			System.out.println("ID de Marcacao passada pelo form="+ encaminhamento.getMarcacao().getId());
			
			
			
		/*	if (result.hasErrors()) {
				populateDefaultModel(model);
				return "encaminhamentoform";
			} else*/ {

				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(encaminhamento.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Encaminhamento adicionado com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Encaminhamento atualizado com sucesso!");
				}
				
				System.out.println("SaveOrUpdate: Data da Distribuicao da encaminhamento="+ encaminhamento.getDistribuicao().getData());
				System.out.println("SaveOrUpdate: ID Distribuicao da encaminhamento="+ encaminhamento.getDistribuicao().getId());
				
				encaminhamentoDAO.saveOrUpdate(encaminhamento);
				System.out.println(".....Salvo ou atualizado o encaminhamento.....");
				System.out.println("redirecionando para... \"redirect:/encaminhamentos/\" + encaminhamento.getId();");
				// POST/REDIRECT/GET
				
				
				return "redirect:/encaminhamentos/" + encaminhamento.getId();
				//return "/encaminhamentos/" + encaminhamento.getId();

				// POST/FORWARD/GET
				// return "encaminhamento/list";

			}

		}

		// show update form , chamado pela listagem, para preencher o form com o id passado e devolvero form pra edicao para o encaminhamentoformpage
		@RequestMapping(value = "/encaminhamentos/{id}/update")
		public String showUpdateEncaminhamentoForm(@PathVariable("id") int id, Model model,HttpServletRequest request) {
			
			System.out.println("showUpdateEncaminhamentoFOrm() -->> ID Encaminhamento passado pelo form = " + id);
			
			System.out.println("Pesquisando  ID encaminhamento no DAO ---> ID = "+id);
			Encaminhamento encaminhamento = encaminhamentoDAO.findByID(id);
			
			
			if (encaminhamento!=null) {
				 System.out.println("Encaminhamento Encontrada ------->Data da Distribuicao da encaminhamento="+ encaminhamento.getDistribuicao().getData());
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
			final RedirectAttributes redirectAttributes) {

			
			logger.debug("deleteEncaminhamento() : {}", id);
			System.out.println("deletando a encaminhamento ="+ id);
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Encaminhamento deletado com sucesso!");

			encaminhamentoDAO.deleteEncaminhamentoByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Encaminhamento deletado!");
			
			
			return "redirect:/encaminhamentos/";
			//return "listaencaminhamentospage";

		}

	

	}
	

