package br.com.tfdonline.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tfdonline.modelo.UnidadeSaude;

@Repository
@Transactional
public class UnidadeSaudeDAOImpl implements UnidadeSaudeDAOI, Serializable{

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
	public void addUnidadeSaude(UnidadeSaude unidadesaude) {
		 sessionFactory.getCurrentSession().save(unidadesaude);
		
	}

	@Override
	public void deleteUnidadeSaudeByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		UnidadeSaude unidadesaude = sessionFactory.getCurrentSession().load(UnidadeSaude.class,id);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(unidadesaude);
		System.out.println("dentro do UnidadeSAUDEDAO..deletei o UnidadeSaude"+ id);
		
	}

	@Override
	public UnidadeSaude findByID(Integer id) {
		// TODO Auto-generated method stub
		UnidadeSaude unidadesaude = null;
		unidadesaude= sessionFactory.getCurrentSession().get(UnidadeSaude.class, id);
		
		return unidadesaude;
	}

	@Override
	public void updateUnidadeSaude(UnidadeSaude unidadesaude) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(unidadesaude);
				
		System.out.println("---> UnidadeSaude atualizado pelo DAO!!!");
		
	}
	
	@Override
	public List<UnidadeSaude> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from UnidadeSaude");
           List<UnidadeSaude> lista = (List<UnidadeSaude>) query.list(); 
           System.out.println("-------->>  UnidadeSaudes encontrados ="+ lista.size());
           return lista;
		    	
 	 }
	public List<UnidadeSaude> findbyName(String nome){
		
		
		String hql = "from UnidadeSaude where nome like :keyword";
		String keyword = nome;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		 
		List<UnidadeSaude> lista = query.list();
		return lista;
		
	}

	@Override
	public void saveOrUpdate(UnidadeSaude unidadesaude) {
		// TODO Auto-generated method stub
		if (findByID(unidadesaude.getId())==null) {
			this.addUnidadeSaude(unidadesaude);
			System.out.println("estou no DAO, tentanto ADD a unidadesaude...");
		} else {
			
			this.updateUnidadeSaude(unidadesaude);
			System.out.println("estou no DAO, tentanto ATUALIZAR a unidadesaude...");
		}
	}

	
}
