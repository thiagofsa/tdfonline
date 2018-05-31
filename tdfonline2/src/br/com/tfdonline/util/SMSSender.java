package br.com.tfdonline.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SMSSender {

	
	public  static String sendMessage(String mensagem, String numero) throws IOException {
		
		String urlParameters = "user=tfdcontrol" +   
                "&password=tfd1408" +   
                "&destinatario=" + numero +  
                "&msg=" + URLEncoder.encode(mensagem, "UTF-8");
		  
		            
        URL url = new URL("http://www.facilitamovel.com/api/simpleSend.ft?" + urlParameters);
        System.out.println("Enviado URL via "+ url.toString());
          
        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();             
        connection.setDoOutput(true);  
        connection.setDoInput(true);  
        connection.setInstanceFollowRedirects(false);   
        connection.setRequestMethod("POST");   
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");   
        connection.setRequestProperty("charset", "utf-8");  
        connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));  
        connection.setUseCaches (false);  
  
        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());  
        wr.write(urlParameters);  
        wr.flush();  
          
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));  
        StringBuffer strRet = new StringBuffer();  
        String line;  
        while ((line = rd.readLine()) != null) {  
            strRet.append(line);  
        }  
        wr.close();  
        rd.close();  
          
        System.out.println("Retorno da Chamada HTTP:"+ strRet);
		return "Retorno da Chamada HTTP:"+ strRet;  
  
    }  
		
		
}
	

