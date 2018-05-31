package br.com.tfdonline.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import br.com.facilitamovel.bean.Retorno;
import br.com.facilitamovel.bean.SmsSimples;
import br.com.facilitamovel.service.SendMessage;

public class SMSSender {

	
	public  static String sendMessage(String mensagem, String numero) throws IOException {
		
		String urlParameters = "user=tfdcontrol" +   
                "&password=tfd1408" +   
                "&destinatario=" + numero +  
                "&msg=" + URLEncoder.encode(mensagem, "UTF-8");
		  
		            
        URL url = new URL("http://www.facilitamovel.com/api/simpleSend.ft?" + urlParameters);
        System.out.println("Enviado SMS via "+ url.toString());
          
        
        SmsSimples sms = new SmsSimples();
		sms.setUser("tfdcontrol");
		sms.setPassword("tfd1408");
		sms.setDestinatario(numero);
		sms.setMessage(mensagem);
		Retorno retorno = null;
		try {
			retorno = SendMessage.simpleSend(sms);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Codigo:" + retorno.getCodigo());
		System.out.println("Descricao:" + retorno.getMensagem());
	    
		return retorno.getMensagem();  
  
    }  
		
		
}
	

