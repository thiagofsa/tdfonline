package br.com.tfdonline.dao;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.tfdonline.modelo.Encaminhamento;
import br.com.tfdonline.modelo.Marcacao;
import br.com.tfdonline.modelo.Motorista;

public interface EncaminhamentoDAOI {

	void addEncaminhamento(Encaminhamento encaminhamento);
    
    List<Encaminhamento> findAll();
    
    public List<Encaminhamento> findbyNomePaciente(String nomepaciente);
     
    void deleteEncaminhamentoByID(Integer id);
     
    Encaminhamento findByID(Integer id);
     
    void updateEncaminhamento(Encaminhamento encaminhamento);

	void saveOrUpdate(Encaminhamento encaminhamento);
	
	public List<Encaminhamento> dataviagem(Date data);

	List<Encaminhamento> findbyData(Date datainicial, Date datafinal);

	List<Encaminhamento> findbyData(Date datainicial);
	
	public List<Encaminhamento> findbyDataVoltaEncaminhamentoVoltaNaoGerado(Date datavolta);
	
	public List<Encaminhamento> findbyDistribuicaoID(Integer distribuicaoid);
}