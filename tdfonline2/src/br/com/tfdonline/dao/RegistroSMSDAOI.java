package br.com.tfdonline.dao;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.tfdonline.modelo.RegistroSMS;

public interface RegistroSMSDAOI {

	void addRegistroSMS(RegistroSMS registrosms);
    
    List<RegistroSMS> find50();
     
    void deleteRegistroSMSByID(Integer id);
     
    RegistroSMS findByID(Integer id);
     
    void updateRegistroSMS(RegistroSMS registrosms);

	void saveOrUpdate(RegistroSMS registrosms);
	
	public List<RegistroSMS> findbyPeriodo(@DateTimeFormat(pattern = "yyyy-MM-dd")Date datainicio, @DateTimeFormat(pattern = "yyyy-MM-dd")Date datafim);
	
	
	
	

	
	
}
