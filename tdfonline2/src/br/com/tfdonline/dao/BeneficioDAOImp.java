package br.com.tfdonline.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tfdonline.modelo.Beneficio;

@Repository
@Transactional
public class BeneficioDAOImp implements BeneficioDAOI, Serializable{

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
	public void addBeneficio(Beneficio beneficio) {
		 sessionFactory.getCurrentSession().save(beneficio);
		
	}

	@Override
	public void deleteBeneficioByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		Beneficio beneficio = sessionFactory.getCurrentSession().load(Beneficio.class,id);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(beneficio);
		System.out.println("dentro do DAO..deletei o Beneficio"+ id);
		
	}

	@Override
	public Beneficio findByID(Integer id) {
		// TODO Auto-generated method stub
		Beneficio beneficio = null;
		beneficio= sessionFactory.getCurrentSession().get(Beneficio.class, id);
		
		return beneficio;
	}

	@Override
	public void updateBeneficio(Beneficio beneficio) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(beneficio);
				
		System.out.println("---> Beneficio atualizado pelo DAO!!!");
		
	}
	
	@Override
	public List<Beneficio> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from Beneficio");
           List<Beneficio> lista = (List<Beneficio>) query.list(); 
           System.out.println("-------->>  Beneficios encontrados ="+ lista.size());
           return lista;
		    	
 	 }
	public List<Beneficio> findbyName(String nome){
		
		
		String hql = "from Beneficio where nome like :keyword";
		String keyword = nome;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		 
		List<Beneficio> lista = query.list();
		return lista;
		
	}

	@Override
	public void saveOrUpdate(Beneficio beneficio) {
		// TODO Auto-generated method stub
		if (findByID(beneficio.getId())==null) {
			this.addBeneficio(beneficio);
			System.out.println("estou no DAO, tentanto ADD a beneficio...");
		} else {
			
			this.updateBeneficio(beneficio);
			System.out.println("estou no DAO, tentanto ATUALIZAR a beneficio...");
		}
	}

	
}
