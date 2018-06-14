package br.com.tfdonline.dao;

import java.util.List;

import br.com.tfdonline.modelo.Acompanhante;

public interface AcompanhanteDAOI {

	void addAcompanhante(Acompanhante acompanhante);
    
    List<Acompanhante> findAll();
     
    void deleteAcompanhanteByID(Integer id);
     
    Acompanhante findByID(Integer id);
     
    void updateAcompanhante(Acompanhante acompanhante);

	void saveOrUpdate(Acompanhante acompanhante);
	
	public List<Acompanhante> findbyName(String nome);
	
	public List<Acompanhante> findbyMarcacaoID(Integer marcacaoid) ;
	
	
	
}
