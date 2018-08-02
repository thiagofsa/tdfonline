package br.com.tfdonline.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tfdonline.modelo.Medico;

@Repository
@Transactional
public class MedicoDAOImpl implements MedicoDAOI, Serializable{

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
	public void addMedico(Medico medico) {
		 sessionFactory.getCurrentSession().save(medico);
		
	}

	@Override
	public void deleteMedicoByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		Medico medico = sessionFactory.getCurrentSession().load(Medico.class,id);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(medico);
		System.out.println("dentro do DAO..deletei o Medico"+ id);
		
	}

	@Override
	public Medico findByID(Integer id) {
		// TODO Auto-generated method stub
		Medico medico = null;
		medico= sessionFactory.getCurrentSession().get(Medico.class, id);
		
		return medico;
	}

	@Override
	public void updateMedico(Medico medico) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(medico);
				
		System.out.println("---> Medico atualizado pelo DAO!!!");
		
	}
	
	@Override
	public List<Medico> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from Medico");
           List<Medico> lista = (List<Medico>) query.list(); 
           System.out.println("-------->>  Medicos encontrados ="+ lista.size());
           return lista;
		    	
 	 }
	public List<Medico> findbyName(String nome){
		
		
		String hql = "from Medico where nome like :keyword";
		String keyword = nome;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		 
		List<Medico> lista = query.list();
		return lista;
		
	}

	@Override
	public void saveOrUpdate(Medico medico) {
		// TODO Auto-generated method stub
		if (findByID(medico.getId())==null) {
			this.addMedico(medico);
			System.out.println("estou no DAO, tentanto ADD a medico...");
		} else {
			
			this.updateMedico(medico);
			System.out.println("estou no DAO, tentanto ATUALIZAR a medico...");
		}
	}


	@Override
	public List<Medico> findbyAutorizadorNome(String nome) {
		// TODO Auto-generated method stub
		String hql = "from Medico where nome like :keyword AND autorizador>0";
		String keyword = nome;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		 
		List<Medico> lista = query.list();
		return lista;
	}
	
	
	

	
}
