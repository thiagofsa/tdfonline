package br.com.tfdonline.controller;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
import br.com.tfdonline.dao.RequisicaoDAOI;
import br.com.tfdonline.modelo.Requisicao;
import br.com.tfdonline.util.DateUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

	/**
	 * Handles and retrieves download request
	 */
	@Controller

	public class RequisicaoReportController {
	 
	 protected static Logger logger = Logger.getLogger("RequisicaoReportController");
	 
	 @Autowired	 
	 RequisicaoDAOI requisicaoDAO ;	   
	 
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

	 
	 
	 
	 /**
	  * Handles and retrieves the download page
	  * 
	  * @return the name of the JSP page
	  */  //**@RequestMapping(method = RequestMethod.GET)
		
		  //**@RequestMapping(method = RequestMethod.GET)
		
				@RequestMapping(value = "/requisicaos/requisicaosporperiodoreportpage", method = RequestMethod.GET)
			    public String getDownloadPage() {
			     logger.debug("Received request to show download page");
			     
			     // Do your work here. Whatever you like
			     // i.e call a custom service to do your business
			     // Prepare a model to be used by the JSP page
			      
			     // This will resolve to /WEB-INF/views/downloadpage.jsp
			     
			     return "requisicaosporperiodoreportpage";
			 }
	     
			

	@RequestMapping(value = "/requisicaos/requisicaosporperiodoreport2", method = RequestMethod.POST)
	public String showRequisicaosPorPeriodoEPaciente(			
			@RequestParam(value="nomepaciente",  required=false) String nomepaciente,
			@RequestParam(value="datainicial",  required=true)@DateTimeFormat(pattern = "dd/MM/yyyy") Date datainicial,
			@RequestParam(value="datafinal",  required=true)@DateTimeFormat(pattern = "dd/MM/yyyy") Date datafinal,
			
			ModelAndView modelAndView, ModelMap model, HttpServletRequest httpServletRequest) 
	   {
	  logger.debug("Received request to download multi report from Requisicaos");
	  //String format="pdf";
	  String type="pdf";
	  
	  System.out.println("Valor do nomepaciente=*"+ nomepaciente+"*");
	  
	  if (nomepaciente.trim().length()==0) {
		  System.out.println("nome paciente em branco");
		  nomepaciente=null;
	  }
	  
	  DateFormat formatlink = new SimpleDateFormat("dd-MM-yyyy");
	  String datainicialFormatada = formatlink.format(datainicial);
	  String dataFinalFormatada = formatlink.format(datafinal);
	  
	  httpServletRequest.setAttribute("nomepaciente", nomepaciente);
	  httpServletRequest.setAttribute("datainicial", datainicialFormatada);
	  httpServletRequest.setAttribute("datafinal", dataFinalFormatada);
	  
	  List<Requisicao> requisicaos= requisicaoDAO.findbyNomePacienteDataInicialDataFinal(nomepaciente, datainicial, datafinal);
	  
	  model.addAttribute("requisicaos", requisicaos);
	  
	  return "requisicaosporperiodoreportpage";
	   
	   }
	

	//RELATORIO EM PDF
				
	@RequestMapping(value = "/reports/requisicaosporperiodoreport/{nomepaciente}/{datainicial}/{datafinal}" )
	public ModelAndView doRequisicaoPorPeriodoReport( 
			
			@PathVariable(value="nomepaciente",  required=false) String nomepaciente,
			@PathVariable(value="datainicial",  required=true)@DateTimeFormat(pattern = "dd-MM-yyyy")  Date datainicial,
			@PathVariable(value="datafinal",  required=true) @DateTimeFormat(pattern = "dd-MM-yyyy") Date datafinal,
			
			ModelAndView modelAndView, ModelMap model, HttpServletRequest httpServletRequest) 
	   {
	  logger.debug("Received request to download multi report from Requisicaos");
	  //String format="pdf";
	  String type="pdf";
	  
	  
  
	  System.out.println("GErando relatorio de requsiicoes com datas");
	  
	  //System.out.println(datainicial);
	  //System.out.println(datafinal);
	  
	  if ((nomepaciente.trim().length()==0) || nomepaciente.equals("null")) {
		  System.out.println("nome paciente em branco");
		  nomepaciente=null;
	  }
	  /*
	  
	  DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	  Date dateinicial =null;
	  Date datefinal = null;
	try {
		dateinicial = (Date)formatter.parse(datainicial);
		datefinal = (Date)formatter.parse(datafinal);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	*/
	  
	  List<Requisicao> requisicaos= requisicaoDAO.findbyNomePacienteDataInicialDataFinal(nomepaciente, datainicial, datafinal);
	  
	  
	  //Collection<RequisicaoVolta> requisicaosvolta= requisicaovoltaDAO.findbyMotoristaID(idmotorista);
	  
	  JRDataSource datasource  = new JRBeanCollectionDataSource(requisicaos);
	  // In order to use Spring's built-in Jasper support, 
	  // We are required to pass our datasource as a map parameter
	   
	  // Add our datasource parameter
	  model.addAttribute("datasource", datasource);
	  model.addAttribute("format", type);
	  	  
	  model.addAttribute("qrCode", "user=Daniel%url=www.tfd.com.br/valida?enc=234");
	  
	  model.addAttribute("SUBREPORT_DIR", ".");
	  
	  System.out.println("Finalmente gerei o report");

	  System.out.println("**************************************");
	  
	  
	  
	  
	  // multiReport is the View of our application
	  // This is declared inside the /WEB-INF/jasper-views.xml
	  modelAndView = new ModelAndView("requisicaosporperiodoReport", model);

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
 
	
	
 	
	@RequestMapping(value = "/requisicaos/getrequisicaofile/{idrequisicao}",method = RequestMethod.GET)
	public HttpEntity<byte[]>  getRequisicaoPDFFile( 
			
			@PathVariable(value="idrequisicao",  required=true) String idrequisicao,
			ModelAndView modelAndView, ModelMap model, HttpServletRequest request) 
	   {
	  
		
					Requisicao requisicao = requisicaoDAO.findByID(Integer.parseInt(idrequisicao));
					
					String ApplicationPath= request.getServletContext().getInitParameter("upload.directory");
					System.out.println("UploadDir"+ ApplicationPath);
					File f1 = new File(ApplicationPath+File.separator+requisicao.getCaminhoarquivo());
		
					System.out.println("Baixando..."+f1.getAbsolutePath());
		
		 			byte[] zipBytes = null;
					try {
						zipBytes = Files.readAllBytes(f1.toPath());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 					
		 					
				    HttpHeaders httpHeaders = new HttpHeaders();
				    httpHeaders.add("Content-Disposition", "attachment; filename=\""+f1.getName()+"\"");
				    HttpEntity<byte[]> entity = new HttpEntity<byte[]>(zipBytes,httpHeaders);
		
				    
				    return entity;
	   
	   }
 
 }
