package br.com.tfdonline.dao;

import java.util.List;

import br.com.tfdonline.modelo.Beneficio;

public interface BeneficioDAOI {

	void addBeneficio(Beneficio beneficio);
    
    List<Beneficio> findAll();
     
    void deleteBeneficioByID(Integer id);
     
    Beneficio findByID(Integer id);
     
    void updateBeneficio(Beneficio beneficio);

	void saveOrUpdate(Beneficio beneficio);
	
	public List<Beneficio> findbyPacienteID(Integer idpaciente);
	
	public List<Beneficio> findbyMarcacaoID(Integer idmarcacao);
	
}
