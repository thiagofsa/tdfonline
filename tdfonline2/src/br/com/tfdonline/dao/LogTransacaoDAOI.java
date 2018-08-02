package br.com.tfdonline.dao;

import java.util.List;

import br.com.tfdonline.modelo.LogTransacao;
import br.com.tfdonline.modelo.Transacao;
import br.com.tfdonline.modelo.Usuario;

public interface LogTransacaoDAOI {

	void addLogTransacao(LogTransacao logtransacao);
    
    List<LogTransacao> findAll();
     
    void deleteLogTransacaoByID(Integer id);
     
    LogTransacao findByID(Integer id);
     
    void updateLogTransacao(LogTransacao logtransacao);

    public void saveOrUpdate(Usuario usuario, Transacao transacao, int identidade);
			
	public LogTransacao findbyUsuario(String usuario);
	
	public List <LogTransacao> findbyUsuarioTipoTransacaoEntidade(String usuario, Integer tipoentidade, Integer tipotransacao);
	
		
	
}
