package br.com.tfdonline.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import br.com.tfdonline.dao.DistribuicaoDAOI;
import br.com.tfdonline.dao.EncaminhamentoDAOI;
import br.com.tfdonline.dao.EncaminhamentoVoltaDAOI;
import br.com.tfdonline.dao.RegistroSMSDAOI;
import br.com.tfdonline.modelo.Acompanhante;
import br.com.tfdonline.modelo.Distribuicao;
import br.com.tfdonline.modelo.RegistroSMS;
import br.com.tfdonline.modelo.Marcacao;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

	/**
	 * Handles and retrieves download request
	 */
	@Controller
	public class RegistroSMSReportController {
	 
	 protected static Logger logger = Logger.getLogger("RegistroSMSReportController");
	 
	 @Autowired	 
	 RegistroSMSDAOI registrosmsDAO ;	   
	 
	 
	 @Autowired	 
	 EncaminhamentoDAOI encaminhamentoDAO ;
	 
	 @Autowired	 
	 EncaminhamentoVoltaDAOI encaminhamentovoltaDAO ;
	 
	 
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

	 /**
	  * Handles and retrieves the download page
	  * 
	  * @return the name of the JSP page
	  */  //**@RequestMapping(method = RequestMethod.GET)
		
		  //**@RequestMapping(method = RequestMethod.GET)
		
				@RequestMapping(value = "/reports/registrosmssreport", method = RequestMethod.GET)
			    public String getDownloadPage( Model model) {
			     logger.debug("Received request to show download page");
			     
			     // Do your work here. Whatever you like
			     // i.e call a custom service to do your business
			     // Prepare a model to be used by the JSP page
			      
			     // This will resolve to /WEB-INF/views/downloadpage.jsp
			     	
			     	Distribuicao distribuicao = new Distribuicao();
					model.addAttribute("distribuicao", distribuicao);			     
			     
			     return "registrosmsreportpage";
			 }    				
				
			
				
				
	    /**
	     * Handles multi-format report requests
	     * 
	     * @param type the format of the report, i.e pdf
	     * 
	     */
 @RequestMapping(value = "/reports/registrosmssreport2", method = RequestMethod.GET)
	public ModelAndView doRegistroSMSsPorMotoristaMultiReport(@RequestParam(value="type", required=false) String type, @RequestParam(value="datainicio",  required=false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date datainicio, @RequestParam(value="datafim",  required=false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date datafim,
	      ModelAndView modelAndView, ModelMap model, HttpServletRequest httpServletRequest) 
	   {
	  logger.debug("Received request to download multi report from RegistroSMSs");
	  //String format="pdf";
	  type="pdf";
	  
  
	  List<RegistroSMS> registrosmss= registrosmsDAO.findbyPeriodo(datainicio, datafim);
	  
	  System.out.println("Testando nome do paciente");
	  System.out.println(registrosmss.get(0).getEncaminhamento().getMarcacao().getPaciente().getNome());
	  System.out.println("**************************************");
	  
	  JRDataSource datasource  = new JRBeanCollectionDataSource(registrosmss);
	  // In order to use Spring's built-in Jasper support, 
	  // We are required to pass our datasource as a map parameter
	   
	  // Add our datasource parameter
	  model.addAttribute("datasource", datasource);
	  model.addAttribute("format", type);
	  	  
	  
	  System.out.println("Finalmente gerei o report");

	  

	  
	  // multiReport is the View of our application
	  // This is declared inside the /WEB-INF/jasper-views.xml
	  modelAndView = new ModelAndView("registrosmspormotoristaReport", model);

	  System.out.println("Testando o envio de SMS........");
	  
	  
	  /* try {
		SMSSender.sendMessage("Testando o SMS para o TFDControl", "87998093458");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("Problema no envio do SMS");
		e.printStackTrace();
	}
	*/
	  //fim
	  
	  return modelAndView;	  	 
	  } 
 }


