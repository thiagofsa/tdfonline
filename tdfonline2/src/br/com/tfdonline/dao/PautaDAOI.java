package br.com.tfdonline.dao;

import java.util.Date;
import java.util.List;

import br.com.tfdonline.modelo.Marcacao;
import br.com.tfdonline.modelo.Pauta;

public interface PautaDAOI {

	void addPauta(Pauta pauta);
    
    List<Pauta> findAll();
     
    void deletePautaByID(Integer id);
     
    Pauta findByID(Integer id);
    
        
    void updatePauta(Pauta pauta);

	void saveOrUpdate(Pauta pauta);
	
	public List<Pauta> findbyDescricao(String descricao);
	
	public List<Pauta> findbyData(Date data);

	List<Pauta> findbyData(Date datainicial, Date datafinal);

	
	
}
