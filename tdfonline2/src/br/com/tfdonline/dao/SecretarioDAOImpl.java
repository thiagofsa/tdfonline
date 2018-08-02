package br.com.tfdonline.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tfdonline.modelo.Secretario;

@Repository
@Transactional
public class SecretarioDAOImpl implements SecretarioDAOI, Serializable{

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
	public void addSecretario(Secretario secretario) {
		 sessionFactory.getCurrentSession().save(secretario);
		
	}

	@Override
	public void deleteSecretarioByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		Secretario secretario = sessionFactory.getCurrentSession().load(Secretario.class,id);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(secretario);
		System.out.println("dentro do DAO..deletei o Secretario"+ id);
		
	}

	@Override
	public Secretario findByID(Integer id) {
		// TODO Auto-generated method stub
		Secretario secretario = null;
		secretario= sessionFactory.getCurrentSession().get(Secretario.class, id);
		
		return secretario;
	}

	@Override
	public void updateSecretario(Secretario secretario) {
		// TODO Auto-generated method stub
		//**sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(secretario);
				
		System.out.println("---> Secretario atualizado pelo DAO!!!");
		
	}
	
	@Override
	public List<Secretario> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from Secretario");
           List<Secretario> lista = (List<Secretario>) query.list(); 
           System.out.println("-------->>  Secretarios encontrados ="+ lista.size());
           return lista;
		    	
 	 }
	public List<Secretario> findbyName(String nome){
		
		
		String hql = "from Secretario where nome like :keyword";
		String keyword = nome;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		 
		List<Secretario> lista = query.list();
		return lista;
		
	}

	@Override
	public void saveOrUpdate(Secretario secretario) {
		// TODO Auto-generated method stub
		if (findByID(secretario.getId())==null) {
			this.addSecretario(secretario);
			System.out.println("estou no DAO, tentanto ADD a secretario...");
		} else {
			
			this.updateSecretario(secretario);
			System.out.println("estou no DAO, tentanto ATUALIZAR a secretario...");
		}
	}
	
	
	

	
}
