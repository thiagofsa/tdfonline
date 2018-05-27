package br.com.tfdonline.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import br.com.tfdonline.dao.PacienteDAOI;
import br.com.tfdonline.modelo.Paciente;

 
@Controller
public class PacienteController {
 

    Paciente paciente;
	
    @Autowired
    private PacienteDAOI pacienteDAO;
	
	 @RequestMapping(value = {"/cadastropaciente" })
	    public String cadastroPaciente(Model model) {
		 	
		 	model.addAttribute("paciente", new Paciente());
		 	return "cadastropacientepage";
	    }
	 
	 	 	 
	// @RequestMapping(value = {"/cadastrarpaciente" }, method = RequestMethod.POST)
	 @RequestMapping(value = "/cadastrarpaciente")
	    public ModelAndView cadastrarPaciente(
	    		  @ModelAttribute("paciente") Paciente paciente,BindingResult result, ModelMap model){
		 			  		   
		   pacienteDAO.addPaciente(paciente);
		   
		   ModelAndView modelView= new ModelAndView("pacientecadastradopage");
		   modelView.addObject("paciente", paciente);
			 
	       return modelView;
	    }
	 
	 
	 @RequestMapping(value = {"/listapacientes" })
	    public org.springframework.web.servlet.ModelAndView listaPacientePage(Model model) {
		 
		 List<Paciente> lista=  pacienteDAO.findAllPacientes();
		 
		 System.out.println("testando....tamanho da lista="+lista.size());
		 
		 ModelAndView modelView= new ModelAndView("listapacientespage");
		 modelView.addObject("listaPacientes", lista);
		 
		 	
        return modelView;
        
	    }
    
}
