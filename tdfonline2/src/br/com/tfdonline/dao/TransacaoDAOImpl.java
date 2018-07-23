package br.com.tfdonline.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tfdonline.modelo.Transacao;

@Repository
@Transactional
public class TransacaoDAOImpl implements TransacaoDAOI, Serializable{

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
	public void addTransacao(Transacao transacao) {
		 sessionFactory.getCurrentSession().save(transacao);
		
	}

	@Override
	public void deleteTransacaoByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		Transacao transacao = sessionFactory.getCurrentSession().load(Transacao.class,id);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(transacao);
		System.out.println("dentro do DAO..deletei o Transacao"+ id);
		
	}

	@Override
	public Transacao findByID(Integer id) {
		// TODO Auto-generated method stub
		Transacao transacao = null;
		transacao= sessionFactory.getCurrentSession().get(Transacao.class, id);
		
		return transacao;
	}

	@Override
	public void updateTransacao(Transacao transacao) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(transacao);
				
		System.out.println("---> Transacao atualizado pelo DAO!!!");
		
	}
	
	@Override
	public List<Transacao> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from Transacao");
           List<Transacao> lista = (List<Transacao>) query.list(); 
           System.out.println("-------->>  Transacaos encontrados ="+ lista.size());
           return lista;
		    	
 	 }


	@Override
	public void saveOrUpdate(Transacao transacao) {
		// TODO Auto-generated method stub
		if (findByID(transacao.getId())==null) {
			this.addTransacao(transacao);
			System.out.println("estou no DAO, tentanto ADD a transacao...");
		} else {
			
			this.updateTransacao(transacao);
			System.out.println("estou no DAO, tentanto ATUALIZAR a transacao...");
		}
	}


	@Override
	public Transacao isRegistravel(Integer entidade, Integer operacao) {
		// TODO Auto-generated method stub
		
		
		   String hql = "from Transacao t WHERE t.entidade=:entidade AND t.operacao=:operacao";
           

		   	Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("entidade", entidade);
			query.setParameter("operacao", operacao);
		   
		   List<Transacao> lista = query.list();
		   
           System.out.println("-------->>  Transacaos encontrados ="+ lista.size());
           
           
           if (lista.get(0).getValor()==1) {
        	   return (Transacao) lista.get(0);
           }
           else
        	   
           return null;
		
		
	}


	


	
	
}
