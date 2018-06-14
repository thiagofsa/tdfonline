package br.com.tfdonline.dao;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.tfdonline.modelo.Marcacao;
import br.com.tfdonline.modelo.Motorista;

public interface MarcacaoDAOI {

	void addMarcacao(Marcacao marcacao);
    
    List<Marcacao> findAll();
     
    void deleteMarcacaoByID(Integer id);
     
    Marcacao findByID(Integer id);
     
    void updateMarcacao(Marcacao marcacao);

	void saveOrUpdate(Marcacao marcacao);
	
	public List<Marcacao> findbyNomePaciente(String nomepaciente);
	
	public List<Marcacao> findbyData(Date data);
	
	public List<Marcacao> findLast();

	List<Marcacao> findbyData(Date datainicial, Date datafinal);
	
	public List<Marcacao> findbyNomeData(Date datainicial,  Date datafinal, String nome) ;
	
}
