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
import br.com.tfdonline.modelo.Marcacao;

@Repository
@Transactional
public class MarcacaoDAOImpl implements MarcacaoDAOI, Serializable{

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
	public void addMarcacao(Marcacao marcacao) {
		 sessionFactory.getCurrentSession().save(marcacao);
		
	}

	@Override
	public void deleteMarcacaoByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		Marcacao marcacao = sessionFactory.getCurrentSession().load(Marcacao.class,id);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(marcacao);
		System.out.println("dentro do DAO..deletei o Marcacao"+ id);
		
	}

	@Override
	public Marcacao findByID(Integer id) {
		// TODO Auto-generated method stub
		Marcacao marcacao = null;
		marcacao= sessionFactory.getCurrentSession().get(Marcacao.class, id);
		
		return marcacao;
	}

	@Override
	public void updateMarcacao(Marcacao marcacao) {
		// TODO Auto-generated method stub
		//sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().merge(marcacao);
				
		System.out.println("---> Marcacao atualizado pelo DAO!!!");
		
	}
	
	@Override
	public List<Marcacao> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from Marcacao");
           List<Marcacao> lista = (List<Marcacao>) query.list(); 
           System.out.println("-------->>  Marcacaos encontrados ="+ lista.size());
           return lista;
		    	
 	 }
	

	@Override
	public void saveOrUpdate(Marcacao marcacao) {
		// TODO Auto-generated method stub
		if (findByID(marcacao.getId())==null) {
			this.addMarcacao(marcacao);
			System.out.println("estou no DAOSAVEUPDATE, tentanto ADD a marcacao...");
		} else {
			System.out.println("estou no DAOSAVEUPDATE, tentanto ATUALIZAR a marcacao...");
			this.updateMarcacao(marcacao);
			System.out.println("estou no DAOSAVEUDPATE, ATUALIZEI a marcacao...");
			
		}
	}


	@Override
	public List<Marcacao> findbyData(@DateTimeFormat(pattern = "yyyy-MM-dd")Date datainicial) {
	
	return findbyData(datainicial, new Date());
	
	}	
	@Override
	public List<Marcacao> findbyData(@DateTimeFormat(pattern = "yyyy-MM-dd")Date datainicial, @DateTimeFormat(pattern = "yyyy-MM-dd") Date datafinal) {

				
		String hql = "FROM Marcacao m WHERE m.data BETWEEN :start AND :end";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("start", datainicial);
		query.setParameter("end", datafinal);
		 
		List<Marcacao> lista = query.list();
		
		return lista;
	}

	
}