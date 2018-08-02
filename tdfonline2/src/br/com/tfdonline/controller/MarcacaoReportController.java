package br.com.tfdonline.controller;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.AbstractJasperReportsSingleFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsCsvView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsHtmlView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsXlsView;

import br.com.tfdonline.dao.AcompanhanteDAOI;
import br.com.tfdonline.dao.MarcacaoDAOI;
import br.com.tfdonline.modelo.Acompanhante;

import br.com.tfdonline.modelo.Marcacao;


import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

	 
	 
	/**
	 * Handles and retrieves download request
	 */
	@Controller

	public class MarcacaoReportController {
	 
	 protected static Logger logger = Logger.getLogger("ReportController");
	 
	 @Autowired	 
	 MarcacaoDAOI marcacaoDAO ;	   
	 @Autowired	 
	 AcompanhanteDAOI acompanhanteDAO ;
	 
		protected static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";

		public AbstractJasperReportsSingleFormatView getJasperReportsView(HttpServletRequest request,
			 String dataSource, String url, String format, String fileName){
		     String viewFormat = format==null?"pdf":format;
		 
		     // set possible content headers
		     Properties availableHeaders = new Properties();
		     availableHeaders.put("html", "inline; filename="+fileName+".html");
		     availableHeaders.put("csv", "inline; filename="+fileName+".csv");
		     availableHeaders.put("pdf", "inline; filename="+fileName+".pdf");
		     availableHeaders.put("xls", "inline; filename="+fileName+".xls");
		 
		     // get jasperView class based on the format supplied
		     // defaults to pdf
		     AbstractJasperReportsSingleFormatView jasperView = null;
		     if(viewFormat.equals("csv")) {
		         jasperView = new JasperReportsCsvView();
		     }else if(viewFormat.equals("html")){
		         jasperView = new JasperReportsHtmlView();
		     }else if(viewFormat.equals("xls")){
		         jasperView = new JasperReportsXlsView();
		     }else{
		         jasperView = new JasperReportsPdfView();
		     }
		 
		     // get appContext. required by the view
		     
		     
		     WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(
		    		 request.getSession().getServletContext());
		 
		     // set the appropriate content disposition header.
		     Properties headers = new Properties();
		     headers.put(HEADER_CONTENT_DISPOSITION, availableHeaders.get(viewFormat));
		 
		     // set the relevant jasperView properties
		     jasperView.setReportDataKey(dataSource);
		     //jasperView.setJdbcDataSource(dataSource);
		     jasperView.setUrl(url);
		     jasperView.setApplicationContext(ctx);
		     jasperView.setHeaders(headers);
		 
		     // return view
		     return jasperView;
		 }

	 
		
		@RequestMapping(value = "/marcacaos/comprovantemarcacaoreport")
		public String showComprovanteMarcacaoReportPage(Model model) {

			logger.debug("showreportMarcacaoPage");


			return "comprovantemarcacaoreportpage";

		}
			 
		// find2 page
		@RequestMapping(value = "/marcacaos/comprovantemarcacaoreport2")
		public String showFindMarcacaoForm(@RequestParam("nomepaciente") String pacientenome, Model model) {
			
			
			
			List<Marcacao> marcacaoLista= new ArrayList<Marcacao>();
					
			System.out.println("chamando o marcacaos/find/............");
			System.out.println("Nome="+pacientenome);
		
					
					marcacaoLista = marcacaoDAO.findbyNomePaciente(pacientenome);
		
			
			model.addAttribute("marcacaos", marcacaoLista);
			
			return "comprovantemarcacaoreportpage";

		}
		
		
		
		
			
			
			
	    /**
	     * Handles multi-format report requests
	     * 
	     * @param type the format of the report, i.e pdf
	     */
			
			
		@RequestMapping(value = "/reports/comprovantemarcacaoreport/{id}")
		public ModelAndView showCompronvateMarcacaoReport(@PathVariable("id") int id,  ModelAndView modelAndView, 
					ModelMap model, HttpServletRequest httpServletRequest) {
				

 
			logger.debug("Received request to download marcacao report");
	  //String format="pdf";
			String type="pdf";
	  
	   
	  // Retrieve our data from a custom data provider
	  // Our data comes from a DAO layer
	  // Assign the datasource to an instance of JRDataSource
	  // JRDataSource is the datasource that Jasper understands
	  // This is basically a wrapper to Java's collection classes
	  
	  
	  
			Marcacao marcacao = marcacaoDAO.findByID(id);
			List<Acompanhante> acompanhantespacientemarcacao = acompanhanteDAO.findbyMarcacaoID(marcacao.getId());
			
			ArrayList<Marcacao> marcacaos = new ArrayList<Marcacao>();
			
			
			marcacao.setAcompanhantespacientemarcacao(acompanhantespacientemarcacao);
			
			marcacaos.add(marcacao);
	  
			
			
			
			JRDataSource datasource  = new JRBeanCollectionDataSource(marcacaos);
	  		   
		  // Add our datasource parameter
			model.addAttribute("datasource", datasource);
			model.addAttribute("format", type);
		
		  
		  
		  // multiReport is the View of our application
		  // This is declared inside the /WEB-INF/jasper-views.xml
			modelAndView = new ModelAndView("comprovantemarcacaoReport", model);
		   
		  return modelAndView;
	  	 
	  }
 

 	
 
 
 
 
 }
