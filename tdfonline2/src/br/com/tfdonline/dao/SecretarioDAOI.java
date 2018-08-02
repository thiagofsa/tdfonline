package br.com.tfdonline.dao;

import java.util.List;

import br.com.tfdonline.modelo.Secretario;

public interface SecretarioDAOI {

	void addSecretario(Secretario secretario);
    
    List<Secretario> findAll();
     
    void deleteSecretarioByID(Integer id);
     
    Secretario findByID(Integer id);
     
    void updateSecretario(Secretario secretario);

	void saveOrUpdate(Secretario secretario);
	
	public List<Secretario> findbyName(String nome);
	
}
