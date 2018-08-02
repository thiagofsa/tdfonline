package br.com.tfdonline.controller;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tfdonline.dao.BeneficioDAOI;
import br.com.tfdonline.dao.DistribuicaoDAOI;
import br.com.tfdonline.dao.EncaminhamentoDAOI;
import br.com.tfdonline.dao.LogTransacaoDAOI;
import br.com.tfdonline.dao.MarcacaoDAOI;
import br.com.tfdonline.dao.TransacaoDAOI;
import br.com.tfdonline.dao.UsuarioDAOI;
import br.com.tfdonline.modelo.LogTransacao;
import br.com.tfdonline.modelo.Usuario;


	@Controller

	public class LogtransacaoController {


		private final Logger logger = LoggerFactory.getLogger(LogtransacaoController.class);

		@Autowired
		private LogTransacaoDAOI logtransacaoDAO;
		
		@Autowired
		private UsuarioDAOI usuarioDAO;
		
		@Autowired
		private MarcacaoDAOI marcacaoDAO;
		
		@Autowired
		private EncaminhamentoDAOI encaminhamentoDAO;
		
		@Autowired
		private DistribuicaoDAOI distribuicaoDAO;
		
		@Autowired
		private BeneficioDAOI beneficioDAO;
		
		
	
		// list page
		@RequestMapping(value = "/logtransacaos")
		public String showAllLogTransacaos(Model model) {

			logger.debug("showAllLogTransacaos()");
			model.addAttribute("logtransacaos", logtransacaoDAO.findAll());
			return "listalogtransacaospage";

		}
		
		@RequestMapping(value = {"/logtransacaos/find" })
		public String showfindlogtransacaoform(HttpServletRequest request, Model model){
		 	
			System.out.println("chamando o logtransacaos/showform");
			
			model.addAttribute("logtransacaoForm", new LogTransacao());
			System.out.println("UsuarioDAO chamado...");
			
			
		 	return "findlogtransacaoform";
		 	
	    }
		
		
		//populando um usuario vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectusuario/logtransacaos/" })
		public String selectUsuario(@ModelAttribute("logtransacaoForm")  LogTransacao logtransacao,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				Usuario usuario = new Usuario();
				model.addAttribute("usuario", usuario);	
			
				
			//colocando os dados da logtransacao na sessao..			
			if  (request.getSession().getAttribute("logtransacaoSession")==null) {
				request.getSession().setAttribute("logtransacaoSession", logtransacao);
			}
			
		 	return "selectusuarioform.logtransacao";
		 	
	    }
		
		@RequestMapping(value = {"/logtransacaos/selectusuario2" })
		public String selectUsuario(@RequestParam("nome")String nomeusuario,HttpServletRequest request, Model model){
		 	
			System.out.println("chamando o logtransacaos/SelectUsuario2/............Usuario.nome="+nomeusuario);
			
			model.addAttribute("usuarios", usuarioDAO.findbyName(nomeusuario));
			System.out.println("UsuarioDAO chamado...");
			
			
		 	return "selectusuarioform.logtransacao";
		 	
	    }
		
		@RequestMapping(value = "/logtransacaos/selectusuario/{id}")
		public String selectUsuario(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			LogTransacao logtransacao =(LogTransacao) request.getSession().getAttribute("logtransacaoSession");
			System.out.println("ID da usuarioo na sessao em Select Usuario=" + logtransacao.getId());
			Usuario usuario  = usuarioDAO.findByID(id);
			logtransacao.setUsuario(usuario);
			request.getSession().setAttribute("logtransacaoSession", logtransacao);
			model.addAttribute("logtransacaoForm", logtransacao);
			
			return "findlogtransacaoform";
			
		
		}
				
		@RequestMapping(value = {"/logtransacaos/findLogTransacaosByUsuarioandtipotransacao" })
		public String findLogTransacaosByUsuarioandtipotransacao(@ModelAttribute("logtransacaoForm") LogTransacao logtransacao, HttpServletRequest request, Model model){
		 	
			System.out.println("chamando o logtransacaos/findLogTransacaoByUsuarioTipo transacao e operacao="+ logtransacao.getUsuario().getNome());
			model.addAttribute("logtransacaos", logtransacaoDAO.findbyUsuarioTipoTransacaoEntidade(logtransacao.getUsuario().getLogin(), logtransacao.getTransacao().getEntidade(), logtransacao.getTransacao().getOperacao()));
			System.out.println("logtransacaoDAO chamado...");
			
			
		 	return "listalogtransacaospage";
		 	
	    }
		

		// show logtransacao (detalhes)
		@RequestMapping(value = "/logtransacaos/detalhes/{idlogtransacao}/{identidade}")
		public String showLogTransacao(@PathVariable("idlogtransacao") int idlogtransacao, 
				@PathVariable("identidade") int identidade , Model model) {

			
			System.out.println("-----> procurando pelo logtransacao id="+idlogtransacao);

			LogTransacao logtransacao = logtransacaoDAO.findByID(idlogtransacao);
			
			if (logtransacao == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "LogTransacao não encontrado");
			}
			
			if (logtransacao.getTransacao().getEntidade()==TransacaoDAOI.ENTIDADE_MARCACAO) {
				model.addAttribute("marcacao", distribuicaoDAO.findByID(identidade));
			} 			
			if (logtransacao.getTransacao().getEntidade()==TransacaoDAOI.ENTIDADE_BENEFICIO) {
				model.addAttribute("beneficio", beneficioDAO.findByID(identidade));
			} 
			if (logtransacao.getTransacao().getEntidade()==TransacaoDAOI.ENTIDADE_ENCAMINHAMENTO) {
				model.addAttribute("encaminhamento", distribuicaoDAO.findByID(identidade));
			}
			if (logtransacao.getTransacao().getEntidade()==TransacaoDAOI.ENTIDADE_DISTRIBUICAO) {
				model.addAttribute("distribuicao", distribuicaoDAO.findByID(identidade));
			} 
			if (logtransacao.getTransacao().getEntidade()==TransacaoDAOI.ENTIDADE_USUARIO) {
				model.addAttribute("usuario", usuarioDAO.findByID(identidade));
			} 
			
						
			model.addAttribute("logtransacao", logtransacao);

			return "logtransacaoshow";

		}
		
	}
	

