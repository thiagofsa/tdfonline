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

import br.com.tfdonline.dao.DistribuicaoDAOI;
import br.com.tfdonline.dao.MotoristaDAOI;
import br.com.tfdonline.dao.PautaDAOI;
import br.com.tfdonline.dao.VeiculoDAOI;
import br.com.tfdonline.modelo.Distribuicao;
import br.com.tfdonline.modelo.Motorista;
import br.com.tfdonline.modelo.Pauta;
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
		private PautaDAOI pautaDAO;
	
	
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
				distribuicao.setData(new Date());
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
		
		
		
		//(1)
		
		//populando um pauta vazio e direcionando para a pagina de pesquisa
		@RequestMapping(value = {"/selectpauta/distribuicaos/" })
		public String selectPauta(@ModelAttribute("distribuicaoForm")  Distribuicao distribuicao,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request){
		 	
			//primeira vez da exibicao...vamos popular o form
				Pauta pauta = new Pauta();
				model.addAttribute("pauta", pauta);	
			
				
			//colocando os dados da distribuicao na sessao..			
			if  (request.getSession().getAttribute("distribuicaoSession")==null) {
				request.getSession().setAttribute("distribuicaoSession", distribuicao);
			}
			
		 	return "selectpautaform";
		 	
	    }
		
		@RequestMapping(value = {"/distribuicaos/selectpauta2" })
		public String selectPauta(@RequestParam("descricao") String descricao, Model model, @ModelAttribute("pauta") Pauta pauta){
		 	
			System.out.println("chamando o distribuicaos/SelectPauta2/............Pauta.descricao="+descricao);
			
			model.addAttribute("pautas", pautaDAO.findbyDescricao(descricao));
			System.out.println("PautaDAO chamado...");
			
			model.addAttribute("pauta", pauta);	
			
		 	return "selectpautaform";
		 	
	    }
		
		@RequestMapping(value = "/distribuicaos/selectpauta/{id}")
		public String selectPauta(@PathVariable("id") int id, Model model, HttpServletRequest request) {
			
			Distribuicao distribuicao =(Distribuicao) request.getSession().getAttribute("distribuicaoSession");
			System.out.println("ID da distribuicao na sessao em Select Pauta=" + distribuicao.getId());
			Pauta pauta  = pautaDAO.findByID(id);
			distribuicao.setPauta(pauta);
			request.getSession().setAttribute("distribuicaoSession", distribuicao);
			model.addAttribute("distribuicaoForm", distribuicao);
			
			return "distribuicaoform";
			
		
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
		 	return "selectveiculoform";
	    }
		
		
		
		
		@RequestMapping(value = {"/distribuicaos/selectveiculo2" })
		public String selectVeiculo(@RequestParam("descricao") String descricao, Model model, @ModelAttribute("veiculo") Veiculo veiculo){
		 	
			System.out.println("chamando o distribuicaos/SelectVeiculo2/............Veiculo.descricao="+descricao);
			
			model.addAttribute("veiculos", veiculoDAO.findbyDescricao(descricao));
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
			
		 	return "selectmotoristaform";
		 	
	    }
		
		@RequestMapping(value = {"/distribuicaos/selectmotorista2" })
		public String selectMotorista(@RequestParam("nome") String nome, Model model, @ModelAttribute("motorista") Motorista motorista){
		 	
			System.out.println("chamando o distribuicaos/Selectmotorista2/............Motorista.nome="+nome);
			
			model.addAttribute("motoristas", motoristaDAO.findbyName(nome));
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
		//public String saveOrUpdateDistribuicao(@ModelAttribute("distribuicaoForm") @Validated Distribuicao distribuicao,
		public String saveOrUpdateDistribuicao(@ModelAttribute("distribuicaoForm")  Distribuicao distribuicao,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request) {

			
			//distribuicao = request.getSession().getAttribute("distribuicaoSession");
			
			System.out.println("Em saveOrUpdate, salvando ou atualizando distribuicao.............");
			
			Distribuicao distribuicaoSession = (Distribuicao) request.getSession().getAttribute("distribuicaoSession");
			System.out.println("ID de distribuicao da session="+ distribuicaoSession.getId());
			
			System.out.println("ID de distribuicao passada pelo form="+ distribuicao.getId());
			System.out.println("ID de Motorista passada pelo form="+ distribuicao.getMotorista().getId());
			System.out.println("ID de Veiculo passada pelo form="+ distribuicao.getVeiculo().getId());
			System.out.println("ID de Pauta passada pelo form="+ distribuicao.getPauta().getId());
			
			distribuicao.setVagas(distribuicao.getVeiculo().getVagas());
			
			
			
			
		/*	if (result.hasErrors()) {
				populateDefaultModel(model);
				return "distribuicaoform";
			} else*/ {

				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(distribuicao.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Distribuicao adicionado com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Distribuicao atualizado com sucesso!");
				}
				
				System.out.println("SaveOrUpdate: Motorista da distribuicao="+ distribuicao.getMotorista().getNome());
				System.out.println("SaveOrUpdate: ID Motorista da distribuicao="+ distribuicao.getMotorista().getId());
				
				distribuicaoDAO.saveOrUpdate(distribuicao);
				System.out.println(".....Salvo ou atualizado o distribuicao.....");
				System.out.println("redirecionando para... \"redirect:/distribuicaos/\" + distribuicao.getId();");
				// POST/REDIRECT/GET
				
				
				return "redirect:/distribuicaos/" + distribuicao.getId();
				//return "/distribuicaos/" + distribuicao.getId();

				// POST/FORWARD/GET
				// return "distribuicao/list";

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
			distribuicao.setData(new Date());
			
			distribuicao.setMotorista(new Motorista());
			distribuicao.setVeiculo(new Veiculo());
			distribuicao.setPauta(new Pauta());
			
			distribuicao.setVagas(0);
						
			model.addAttribute("distribuicaoForm", distribuicao);
			
			request.getSession().setAttribute("distribuicaoSession", distribuicao);

			

			return "distribuicaoform";

		}


		// delete distribuicao
		@RequestMapping(value = "/distribuicaos/{id}/delete")
		public String deleteDistribuicao(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) {

			
			logger.debug("deleteDistribuicao() : {}", id);
			System.out.println("deletando a distribuicao ="+ id);
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Distribuicao deletado com sucesso!");

			distribuicaoDAO.deleteDistribuicaoByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Distribuicao deletado!");
			
			
			return "redirect:/distribuicaos/";
			//return "listadistribuicaospage";

		}

	

	}
	

