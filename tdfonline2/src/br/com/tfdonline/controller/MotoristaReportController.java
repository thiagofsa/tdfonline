package br.com.tfdonline.controller;



import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import br.com.tfdonline.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
	 
	 
	/**
	 * Handles and retrieves download request
	 */
	@Controller

	public class MotoristaReportController {
	 
	 protected static Logger logger = Logger.getLogger("ReportController");
	 
	 @Autowired	 
	 MotoristaDAOI motoristaDAO ;	   
	 
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
	  */
		
	    //**@RequestMapping(method = RequestMethod.GET)
		
		@RequestMapping(value = "/reports", method = RequestMethod.GET)
	    public String getDownloadPage() {
	     logger.debug("Received request to show download page");
	     
	     // Do your work here. Whatever you like
	     // i.e call a custom service to do your business
	     // Prepare a model to be used by the JSP page
	      
	     // This will resolve to /WEB-INF/views/downloadpage.jsp
	     
	     return "downloadpage";
	 }
	     
	    /**
	     * Handles multi-format report requests
	     * 
	     * @param type the format of the report, i.e pdf
	     */
 @RequestMapping(value = "/reports/motoristasreport", method = RequestMethod.GET)
	public ModelAndView doMotoristaMultiReport(@RequestParam("type") String type, 
	      ModelAndView modelAndView, ModelMap model, HttpServletRequest httpServletRequest) 
	   {
	  logger.debug("Received request to download multi report");
	  //String format="pdf";
	  
	   
	  // Retrieve our data from a custom data provider
	  // Our data comes from a DAO layer
	  // Assign the datasource to an instance of JRDataSource
	  // JRDataSource is the datasource that Jasper understands
	  // This is basically a wrapper to Java's collection classes
	  
	  JRDataSource datasource  = new JRBeanCollectionDataSource(motoristaDAO.findAll());
	  // In order to use Spring's built-in Jasper support, 
	  // We are required to pass our datasource as a map parameter
	   
	  // Add our datasource parameter
	  model.addAttribute("datasource", datasource);
	  model.addAttribute("format", type);
	
	  model.addAttribute("qrCode", "user=Daniel%url=www.tfd.com.br/valida?enc=234");
	  
	  System.out.println("Finalmente gerei o report");
	  
	  
	  // multiReport is the View of our application
	  // This is declared inside the /WEB-INF/jasper-views.xml
	  modelAndView = new ModelAndView("motoristaReport", model);

	  System.out.println("Testando o envio de SMS........");
	  
	  
	   
	  return modelAndView;
	  	 
	  }
 

 	
 
 
 
 
 }
