package br.com.tfdonline.dao;

import java.util.List;

import br.com.tfdonline.modelo.Transacao;

public interface TransacaoDAOI {
	
	
	public static int ADD=1;
	public static int UPDATE=2;
	public static int DELETE=3;
	
	public static int ENTIDADE_USUARIO=1;
	public static int ENTIDADE_MARCACAO=2;
	public static int ENTIDADE_ENCAMINHAMENTO=2;
	public static int ENTIDADE_DISTRIBUICAO = 4;
	public static int ENTIDADE_BENEFICIO =5;
	

	void addTransacao(Transacao transacao);
    
    List<Transacao> findAll();
     
    void deleteTransacaoByID(Integer id);
     
    Transacao findByID(Integer id);
     
    void updateTransacao(Transacao transacao);

	void saveOrUpdate(Transacao transacao);
	
	public Transacao isRegistravel(Integer entidade, Integer operacao);
	
	
		
	
}
