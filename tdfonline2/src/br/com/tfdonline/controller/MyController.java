package br.com.tfdonline.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.portlet.ModelAndView;



 
@Controller
public class MyController {
 
    @RequestMapping(value = { "/", "/home" })
    public String homePage(Model model) {
        return "homepage";
    }
        
    
    
    
    @RequestMapping(value = { "/contactuspage" })
    public String contactusPage(Model model) {
        model.addAttribute("address", "Vietnam");
        model.addAttribute("phone", "...");
        model.addAttribute("email", "...");
        return "contactuspage";
    }
     
    
}
