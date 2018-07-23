package br.com.tfdonline.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tfdonline.modelo.RegistroSMS;

@Repository
@Transactional
public class RegistroSMSDAOImpl implements RegistroSMSDAOI, Serializable{

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
	public void addRegistroSMS(RegistroSMS registrosms) {
		 sessionFactory.getCurrentSession().save(registrosms);
		
	}

	@Override
	public void deleteRegistroSMSByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		RegistroSMS registrosms = sessionFactory.getCurrentSession().load(RegistroSMS.class,id);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(registrosms);
		System.out.println("dentro do DAO..deletei o RegistroSMS"+ id);
		
	}

	@Override
	public RegistroSMS findByID(Integer id) {
		// TODO Auto-generated method stub
		RegistroSMS registrosms = null;
		registrosms= sessionFactory.getCurrentSession().get(RegistroSMS.class, id);
		
		return registrosms;
	}

	@Override
	public void updateRegistroSMS(RegistroSMS registrosms) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(registrosms);
				
		System.out.println("---> RegistroSMS atualizado pelo DAO!!!");
		
	}
	
	@Override
	public List<RegistroSMS> find50() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from RegistroSMS r order by r.id desc");
		   query.setMaxResults(50);
		   
           List<RegistroSMS> lista = (List<RegistroSMS>) query.list(); 
           System.out.println("-------->>  RegistroSMSs encontrados ="+ lista.size());
           return lista;
		    	
 	 }
	@Override
	public List<RegistroSMS> findbyPeriodo(@DateTimeFormat(pattern = "yyyy-MM-dd")Date datainicio, @DateTimeFormat(pattern = "yyyy-MM-dd")Date datafim){
		
		
		String hql = "FROM RegistroSMS r WHERE r.dataenvio BETWEEN :start AND :end";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("start", datainicio);
		query.setParameter("end", datafim);
		 
		List<RegistroSMS> lista = query.list();
		return lista;
		
	}

	@Override
	public void saveOrUpdate(RegistroSMS registrosms) {
		// TODO Auto-generated method stub
		if (findByID(registrosms.getId())==null) {
			this.addRegistroSMS(registrosms);
			System.out.println("estou no DAO, tentanto ADD a registrosms...");
		} else {
			
			this.updateRegistroSMS(registrosms);
			System.out.println("estou no DAO, tentanto ATUALIZAR a registrosms...");
		}
	}


	

	
	
	
}
