package br.com.tfdonline.dao;

import java.util.Date;
import java.util.List;

import br.com.tfdonline.modelo.Distribuicao;
import br.com.tfdonline.modelo.Marcacao;

public interface DistribuicaoDAOI {

	void addDistribuicao(Distribuicao distribuicao);
    
    List<Distribuicao> findAll();
     
    void deleteDistribuicaoByID(Integer id);
     
    Distribuicao findByID(Integer id);
     
    void updateDistribuicao(Distribuicao distribuicao);

	void saveOrUpdate(Distribuicao distribuicao);
	
	public List<Distribuicao> findbyDescricao(String descricao);
	
	public List<Distribuicao> findbyData(Date data);

	List<Distribuicao> findbyData(Date datainicial, Date datafinal);
	
}
