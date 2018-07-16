package br.com.tfdonline.dao;

import java.util.Date;
import java.util.List;

import br.com.tfdonline.modelo.BeneficioAvulso;
import br.com.tfdonline.modelo.Marcacao;

public interface BeneficioAvulsoDAOI {

	void addBeneficioAvulso(BeneficioAvulso beneficio);
    
    List<BeneficioAvulso> findAll();
     
    void deleteBeneficioAvulsoByID(Integer id);
     
    BeneficioAvulso findByID(Integer id);
     
    void updateBeneficioAvulso(BeneficioAvulso beneficio);

	void saveOrUpdate(BeneficioAvulso beneficio);
	
	public List<BeneficioAvulso> findbyPacienteID(Integer idpaciente);
	
	public List<BeneficioAvulso> findbyMarcacaoID(Integer idmarcacao);
	
	public List<BeneficioAvulso> findLast();
	
	public List<BeneficioAvulso> findbyNomeData(Date datainicial,  Date datafinal, String nome) ;
	
	List<BeneficioAvulso> findbyData(Date datainicial, Date datafinal);
	
}
