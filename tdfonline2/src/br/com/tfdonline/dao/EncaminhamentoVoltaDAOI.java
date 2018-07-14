package br.com.tfdonline.dao;

import java.util.Date;
import java.util.List;

import br.com.tfdonline.modelo.EncaminhamentoVolta;
import br.com.tfdonline.modelo.Motorista;

public interface EncaminhamentoVoltaDAOI {

	void addEncaminhamentoVolta(EncaminhamentoVolta encaminhamentovolta);
    
    List<EncaminhamentoVolta> findAll();
     
    void deleteEncaminhamentoVoltaByID(Integer id);
     
    EncaminhamentoVolta findByID(Integer id);
     
    void updateEncaminhamentoVolta(EncaminhamentoVolta encaminhamentovolta);

	void saveOrUpdate(EncaminhamentoVolta encaminhamentovolta);
	
	public List<EncaminhamentoVolta> dataviagem(Date data);

	List<EncaminhamentoVolta> findbyData(Date datainicial, Date datafinal);

	List<EncaminhamentoVolta> findbyData(Date datainicial);
	
}
