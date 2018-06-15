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

import br.com.tfdonline.modelo.Acompanhante;
import br.com.tfdonline.modelo.Encaminhamento;

@Repository
@Transactional
public class EncaminhamentoDAOImpl implements EncaminhamentoDAOI, Serializable{

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
	public void addEncaminhamento(Encaminhamento encaminhamento) {
		 sessionFactory.getCurrentSession().save(encaminhamento);
		
	}

	@Override
	public void deleteEncaminhamentoByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		Encaminhamento encaminhamento = sessionFactory.getCurrentSession().load(Encaminhamento.class,id);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(encaminhamento);
		System.out.println("dentro do DAO..deletei o Encaminhamento"+ id);
		
	}

	@Override
	public Encaminhamento findByID(Integer id) {
		// TODO Auto-generated method stub
		Encaminhamento encaminhamento = null;
		encaminhamento= sessionFactory.getCurrentSession().get(Encaminhamento.class, id);
		
		return encaminhamento;
	}

	@Override
	public void updateEncaminhamento(Encaminhamento encaminhamento) {
		// TODO Auto-generated method stub
		//sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().merge(encaminhamento);
				
		System.out.println("---> Encaminhamento atualizado pelo DAO!!!");
		
	}
	
	@Override
	public List<Encaminhamento> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from Encaminhamento");
           List<Encaminhamento> lista = (List<Encaminhamento>) query.list(); 
           System.out.println("-------->>  Encaminhamentos encontrados ="+ lista.size());
           return lista;
		    	
 	 }
	

	@Override
	public void saveOrUpdate(Encaminhamento encaminhamento) {
		// TODO Auto-generated method stub
		if ((findByID(encaminhamento.getId())==null) ){
			this.addEncaminhamento(encaminhamento);
			System.out.println("estou no DAOSAVEUPDATE, tentanto ADD a encaminhamento...");
		} else {
			System.out.println("estou no DAOSAVEUPDATE, tentanto ATUALIZAR a encaminhamento...");
			this.updateEncaminhamento(encaminhamento);
			System.out.println("estou no DAOSAVEUDPATE, ATUALIZEI a encaminhamento...");
			
		}
	}


	@Override
	public List<Encaminhamento> findbyData(@DateTimeFormat(pattern = "yyyy-MM-dd")Date datainicial) {
	
	return findbyData(datainicial, new Date());
	
	}	
	
	
	@Override
	public List<Encaminhamento> findbyData(@DateTimeFormat(pattern = "yyyy-MM-dd")Date datainicial, @DateTimeFormat(pattern = "yyyy-MM-dd") Date datafinal) {

				
		String hql = "FROM Encaminhamento m WHERE m.dataviagem BETWEEN :start AND :end";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("start", datainicial);
		query.setParameter("end", datafinal);
		 
		List<Encaminhamento> lista = query.list();
		
		return lista;
	}


	@Override
	public List<Encaminhamento> dataviagem(Date data) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
