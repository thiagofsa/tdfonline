package br.com.tfdonline.controller;





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

import br.com.tfdonline.dao.VeiculoDAOI;
import br.com.tfdonline.modelo.Veiculo;

	@Controller
	public class VeiculoController {


		private final Logger logger = LoggerFactory.getLogger(VeiculoController.class);

		@Autowired
		private VeiculoDAOI veiculoDAO;

		/*@Autowired
		//VeiculoFormValidator veiculoFormValidator;
		
		//Set a form validator
		@InitBinder
		protected void initBinder(WebDataBinder binder) {
			binder.setValidator(veiculoFormValidator);
		}
		*/
		

		// list page - Mostra todos os veiculos
		@RequestMapping(value = "/veiculos")
		public String showAllVeiculos(Model model) {

			logger.debug("showAllVeiculos()");
			model.addAttribute("veiculos", veiculoDAO.findAll());
			return "listaveiculospage";
		}
		
						
		@RequestMapping(value = {"/veiculos/find" })
		    public String findVeiculo(Model model) {			 	
				
				Veiculo veiculo = new Veiculo();
				veiculo.setDescricao("Informe a descricao do Veiculo");
			 	model.addAttribute("veiculoForm", veiculo);
			 	return "findveiculo";
		    }
		 
		// pesquisar page
		@RequestMapping(value = "/veiculos/find2")
		public String showFindVeiculoForm(@RequestParam("descricao") String descricao, Model model) {
			
			System.out.println("chamando o veiculos/find/modelo............"+descricao);
			logger.debug("Veiculos.FindByName()");
			model.addAttribute("veiculos", veiculoDAO.findbyDescricao(descricao));
			return "listaveiculospage";
		}
		
		// save or update veiculo
		// 1. @ModelAttribute bind form value
		// 2. @Validated form validator
		// 3. RedirectAttributes for flash value
		
		@RequestMapping(value = "/veiculos", method = RequestMethod.POST)
		//public String saveOrUpdateVeiculo(@ModelAttribute("veiculoForm") @Validated Veiculo veiculo,		
		
		public String saveOrUpdateVeiculo(@ModelAttribute("veiculoForm")  Veiculo veiculo,
				BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

			logger.debug("saveOrUpdateVeiculo() : {}", veiculo);
			System.out.println("Depois do formVeiculo, salvando ou atualizando veiculo.............");			
			
		/*	if (result.hasErrors()) {
				populateDefaultModel(model);
				return "veiculoform";
			} else*/ 
				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(veiculo.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Veiculo adicionado com sucesso!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Veiculo atualizado com sucesso!");
				}
				
				System.out.println("----->Modelo ====="+ veiculo.getDescricao());
				veiculoDAO.saveOrUpdate(veiculo);
				System.out.println(".....Salvo ou atualizado o veiculo.....");
				System.out.println("redirecionando para... \"redirect:/veiculos/\" + veiculo.getId();");
				// POST/REDIRECT/GET
				
				return "redirect:/veiculos/";				
			}		

		// show update form
		@RequestMapping(value = "/veiculos/{id}/update")
		public String showUpdateVeiculoForm(@PathVariable("id") int id, Model model) {

			logger.debug("showUpdateVeiculoForm() : {}", id);
			System.out.println("showUpdateVeiculoForm() : {} " + id);

			Veiculo veiculo = veiculoDAO.findByID(id);
			if (veiculo!=null) {
				System.out.println("evocando showUpdateMOtoristaForm.......veiculo encontrado="+veiculo.getDescricao());
				
			}else
				System.out.println("Veiculo nao localizado");
			
			model.addAttribute("veiculoForm", veiculo);			
						
			return "veiculoform";
		}
		

		// show veiculo
		@RequestMapping(value = "/veiculos/{id}")
		public String showVeiculo(@PathVariable("id") int id, Model model) {

			logger.debug("showVeiculo() id: {}", id);
			System.out.println("-----> procurando pelo veiculo id="+id);

			Veiculo veiculo = veiculoDAO.findByID(id);
			if (veiculo == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Veiculo não encontrado");
			}
			model.addAttribute("veiculo", veiculo);

			return "veiculoshow";
		}
		
		// show add veiculo form
		@RequestMapping(value = "/veiculos/add", method = RequestMethod.GET)
		public String showAddVeiculoForm(Model model) {
			
			logger.debug("showAddVeiculoForm()");

			Veiculo veiculo = new Veiculo();

			// set default value
			veiculo.setId(-1);
			
			veiculo.setVagas(null);		
			model.addAttribute("veiculoForm", veiculo);

			return "veiculocadastro";
		}

		// delete veiculo
		@RequestMapping(value = "/veiculos/{id}/delete")
		public String deleteVeiculo(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes, Model model) {
			
			logger.debug("deleteVeiculo() : {}", id);
			System.out.println("desativando o veiculo ="+ id);				

			veiculoDAO.deleteVeiculoByID(new Integer(id));
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Veiculo deletado com sucesso!");
			
			model.addAttribute("veiculos", veiculoDAO.findAll());
			
			return "listaveiculospage";			
		}	
		
		
		
		
		
		
		
		
		
		
		
		
		
	
}