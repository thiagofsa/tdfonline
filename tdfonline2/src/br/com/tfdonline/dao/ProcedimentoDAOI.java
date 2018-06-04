package br.com.tfdonline.dao;

import java.util.List;

import br.com.tfdonline.modelo.Procedimento;

public interface ProcedimentoDAOI {

	void addProcedimento(Procedimento procedimento);
    
    List<Procedimento> findAll();
     
    void deleteProcedimentoByID(Integer id);
     
    Procedimento findByID(Integer id);
     
    void updateProcedimento(Procedimento procedimento);

	void saveOrUpdate(Procedimento procedimento);
	
	public List<Procedimento> findbyNome(String nome);

	public List<String> getAreasProcedimento();
	
}
