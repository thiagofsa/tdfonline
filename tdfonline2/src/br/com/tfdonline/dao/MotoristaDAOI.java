package br.com.tfdonline.dao;

import java.util.List;

import br.com.tfdonline.modelo.Motorista;

public interface MotoristaDAOI {

	void addMotorista(Motorista motorista);
    
    List<Motorista> findAll();
     
    void deleteMotoristaByID(Integer id);
     
    Motorista findByID(Integer id);
     
    void updateMotorista(Motorista motorista);

	void saveOrUpdate(Motorista motorista);
	
	public List<Motorista> findbyName(String nome);
	
}
