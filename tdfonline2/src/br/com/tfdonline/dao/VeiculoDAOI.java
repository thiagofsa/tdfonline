package br.com.tfdonline.dao;

import java.util.List;

import br.com.tfdonline.modelo.Veiculo;

public interface VeiculoDAOI {

	void addVeiculo(Veiculo veiculo);
    
    List<Veiculo> findAll();
     
    void deleteVeiculoByID(Integer id);
     
    Veiculo findByID(Integer id);
     
    void updateVeiculo(Veiculo veiculo);

	void saveOrUpdate(Veiculo veiculo);
	
	public List<Veiculo> findbyDescricao(String descricao);
	
}
