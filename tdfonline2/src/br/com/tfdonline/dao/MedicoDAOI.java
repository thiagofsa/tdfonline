package br.com.tfdonline.dao;

import java.util.List;

import br.com.tfdonline.modelo.Medico;

public interface MedicoDAOI {

	void addMedico(Medico medico);
    
    List<Medico> findAll();
     
    void deleteMedicoByID(Integer id);
     
    Medico findByID(Integer id);
     
    void updateMedico(Medico medico);

	void saveOrUpdate(Medico medico);
	
	public List<Medico> findbyName(String nome);
	
	public List<Medico> findbyAutorizadorNome(String nome);
	
}
