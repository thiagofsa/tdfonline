package br.com.tfdonline.dao;

import java.util.Date;
import java.util.List;

import br.com.tfdonline.modelo.Requisicao;
import br.com.tfdonline.modelo.Marcacao;

public interface RequisicaoDAOI {

	void addRequisicao(Requisicao requisicao);
    
    List<Requisicao> findAll();
     
    void deleteRequisicaoByID(Integer id);
     
    Requisicao findByID(Integer id);
     
    void updateRequisicao(Requisicao requisicao);

	void saveOrUpdate(Requisicao requisicao);
	
	
	public List<Requisicao> findbyData(Date data);

	List<Requisicao> findbyData(Date datainicial, Date datafinal);
	
	public List<Requisicao> findbyNomePaciente(String nome);
	
}
