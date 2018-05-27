package br.com.tfdonline.dao;

import java.util.List;

import br.com.tfdonline.modelo.UnidadeSaude;

public interface UnidadeSaudeDAOI {

	void addUnidadeSaude(UnidadeSaude unidadesaude);
    
    List<UnidadeSaude> findAll();
     
    void deleteUnidadeSaudeByID(Integer id);
     
    UnidadeSaude findByID(Integer id);
     
    void updateUnidadeSaude(UnidadeSaude unidadesaude);

	void saveOrUpdate(UnidadeSaude unidadesaude);
	
	public List<UnidadeSaude> findbyName(String nome);
	
}
