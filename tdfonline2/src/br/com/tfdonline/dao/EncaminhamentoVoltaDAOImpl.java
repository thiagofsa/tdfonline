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
import br.com.tfdonline.modelo.EncaminhamentoVolta;

@Repository
@Transactional
public class EncaminhamentoVoltaDAOImpl implements EncaminhamentoVoltaDAOI, Serializable{

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
	public void addEncaminhamentoVolta(EncaminhamentoVolta encaminhamento) {
		 sessionFactory.getCurrentSession().save(encaminhamento);
		
	}

	@Override
	public void deleteEncaminhamentoVoltaByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		EncaminhamentoVolta encaminhamento = sessionFactory.getCurrentSession().load(EncaminhamentoVolta.class,id);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(encaminhamento);
		System.out.println("dentro do DAO..deletei o EncaminhamentoVolta"+ id);
		
	}

	@Override
	public EncaminhamentoVolta findByID(Integer id) {
		// TODO Auto-generated method stub
		EncaminhamentoVolta encaminhamento = null;
		encaminhamento= sessionFactory.getCurrentSession().get(EncaminhamentoVolta.class, id);
		
		return encaminhamento;
	}

	@Override
	public void updateEncaminhamentoVolta(EncaminhamentoVolta encaminhamento) {
		// TODO Auto-generated method stub
		//sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().merge(encaminhamento);
				
		System.out.println("---> EncaminhamentoVolta atualizado pelo DAO!!!");
		
	}
	
	@Override
	public List<EncaminhamentoVolta> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from EncaminhamentoVolta");
           List<EncaminhamentoVolta> lista = (List<EncaminhamentoVolta>) query.list(); 
           System.out.println("-------->>  EncaminhamentoVoltas encontrados ="+ lista.size());
           return lista;
		    	
 	 }
	

	@Override
	public void saveOrUpdate(EncaminhamentoVolta encaminhamento) {
		// TODO Auto-generated method stub
		if ((findByID(encaminhamento.getId())==null) ){
			this.addEncaminhamentoVolta(encaminhamento);
			System.out.println("estou no DAOSAVEUPDATE, tentanto ADD a encaminhamento...");
		} else {
			System.out.println("estou no DAOSAVEUPDATE, tentanto ATUALIZAR a encaminhamento...");
			this.updateEncaminhamentoVolta(encaminhamento);
			System.out.println("estou no DAOSAVEUDPATE, ATUALIZEI a encaminhamento...");
			
		}
	}


	@Override
	public List<EncaminhamentoVolta> findbyData(@DateTimeFormat(pattern = "yyyy-MM-dd")Date datainicial) {
	
	return findbyData(datainicial, new Date());
	
	}	
	
	
	@Override
	public List<EncaminhamentoVolta> findbyData(@DateTimeFormat(pattern = "yyyy-MM-dd")Date datainicial, @DateTimeFormat(pattern = "yyyy-MM-dd") Date datafinal) {

				
		String hql = "FROM EncaminhamentoVolta ev WHERE ev.dataviagem BETWEEN :start AND :end";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("start", datainicial);
		query.setParameter("end", datafinal);
		 
		List<EncaminhamentoVolta> lista = query.list();
		
		return lista;
	}


	@Override
	public List<EncaminhamentoVolta> dataviagem(Date data) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
