package br.com.tfdonline.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tfdonline.modelo.LogTransacao;
import br.com.tfdonline.modelo.Transacao;
import br.com.tfdonline.modelo.Usuario;

@Repository
@Transactional
public class LogTransacaoDAOImpl implements LogTransacaoDAOI, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public void addLogTransacao(LogTransacao logtransacao) {
		 sessionFactory.getCurrentSession().save(logtransacao);
		
	}

	@Override
	public void deleteLogTransacaoByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		LogTransacao logtransacao = sessionFactory.getCurrentSession().load(LogTransacao.class,id);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(logtransacao);
		System.out.println("dentro do DAO..deletei o LogTransacao"+ id);
		
	}

	@Override
	public LogTransacao findByID(Integer id) {
		// TODO Auto-generated method stub
		LogTransacao logtransacao = null;
		logtransacao= sessionFactory.getCurrentSession().get(LogTransacao.class, id);
		
		return logtransacao;
	}

	@Override
	public void updateLogTransacao(LogTransacao logtransacao) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(logtransacao);
				
		System.out.println("---> LogTransacao atualizado pelo DAO!!!");
		
	}
	
	@Override
	public List<LogTransacao> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from LogTransacao");
           List<LogTransacao> lista = (List<LogTransacao>) query.list(); 
           System.out.println("-------->>  LogTransacaos encontrados ="+ lista.size());
           return lista;
		    	
 	 }
	

	@Override
	public void saveOrUpdate(Usuario usuario, Transacao transacao, int identidade) {
		
		// TODO Auto-generated method stub
		
		LogTransacao logtransacao = new LogTransacao();
		logtransacao.setData(new Date());
		logtransacao.setUsuario(usuario);
		logtransacao.setTransacao(transacao);
		logtransacao.setIdentidade(identidade);
			
		System.out.println("Salvando a transacao " + transacao.getEntidade() + "-"+ transacao.getOperacao());
		
		if (findByID(logtransacao.getId())==null) {
			this.addLogTransacao(logtransacao);
			System.out.println("estou no DAO, tentanto ADD a logtransacao...");
		} else {
			
			this.updateLogTransacao(logtransacao);
			System.out.println("estou no DAO, tentanto ATUALIZAR a logtransacao...");
		}
	}


	

	@Override
	public LogTransacao findbyUsuario(String login){
		
		
		String hql = "from LogTransacao lt where lt.usuario.login like '"+ login +"'";
		String keyword = login;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		 
		List<LogTransacao> lista = query.list();
		if (lista.size()>0) {
			return lista.get(0);
		}else
			return null;
		
		
	}
	
	
}
