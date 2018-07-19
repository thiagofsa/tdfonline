package br.com.tfdonline.dao;

import java.util.Date;
import java.util.List;

import br.com.tfdonline.modelo.Beneficio;
import br.com.tfdonline.modelo.Marcacao;

public interface BeneficioAvulsoDAOI {

	void addBeneficioAvulso(Beneficio beneficio);
    
    List<Beneficio> findAll();
     
    void deleteBeneficioAvulsoByID(Integer id);
     
    Beneficio findByID(Integer id);
     
    void updateBeneficioAvulso(Beneficio beneficio);

	void saveOrUpdate(Beneficio beneficio);
	
	public List<Beneficio> findbyPacienteID(Integer idpaciente);
	
	public List<Beneficio> findbyMarcacaoID(Integer idmarcacao);
	
	public List<Beneficio> findLast();
	
	public List<Beneficio> findbyNomeData(Date datainicial,  Date datafinal, String nome) ;
	
	List<Beneficio> findbyData(Date datainicial, Date datafinal);
	
}
