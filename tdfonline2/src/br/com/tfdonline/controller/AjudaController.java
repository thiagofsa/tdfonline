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

	@Controller
	public class AjudaController {


		private final Logger logger = LoggerFactory.getLogger(AjudaController.class);
		

		// list page - Mostra todos os veiculos
		@RequestMapping(value = "/contato/")
		public String showAllVeiculos(Model model) {
			
			return "contatopage";
		}	
}