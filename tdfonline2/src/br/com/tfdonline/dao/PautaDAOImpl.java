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

import br.com.tfdonline.modelo.Marcacao;
import br.com.tfdonline.modelo.Pauta;

@Repository
@Transactional
public class PautaDAOImpl implements PautaDAOI, Serializable{

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
	public void addPauta(Pauta pauta) {
		 sessionFactory.getCurrentSession().save(pauta);
		
	}

	@Override
	public void deletePautaByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		Pauta pauta = sessionFactory.getCurrentSession().load(Pauta.class,id);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(pauta);
		System.out.println("dentro do DAO..deletei o Pauta"+ id);
		
	}

	@Override
	public Pauta findByID(Integer id) {
		// TODO Auto-generated method stub
		Pauta pauta = null;
		pauta= sessionFactory.getCurrentSession().get(Pauta.class, id);
		
		return pauta;
	}

	@Override
	public void updatePauta(Pauta pauta) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(pauta);
				
		System.out.println("---> Pauta atualizado pelo DAO!!!");
		
	}
	
	@Override
	public List<Pauta> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from Pauta");
           List<Pauta> lista = (List<Pauta>) query.list(); 
           System.out.println("-------->>  Pautas encontrados ="+ lista.size());
           return lista;
		    	
 	 }
	public List<Pauta> findbyDescricao(String descricao){
		
		
		String hql = "from Pauta where descricao like :keyword";
		String keyword = descricao;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		 
		List<Pauta> lista = query.list();
		return lista;
		
	}

	@Override
	public void saveOrUpdate(Pauta pauta) {
		// TODO Auto-generated method stub
		if (findByID(pauta.getId())==null) {
			this.addPauta(pauta);
			System.out.println("estou no DAO, tentanto ADD a pauta...");
		} else {
			
			this.updatePauta(pauta);
			System.out.println("estou no DAO, tentanto ATUALIZAR a pauta...");
		}
	}


	@Override
	public List<Pauta> findbyData(@DateTimeFormat(pattern = "yyyy-MM-dd")Date datainicial) {
	
	return findbyData(datainicial, new Date());
	
	}	
	@Override
	public List<Pauta> findbyData(@DateTimeFormat(pattern = "yyyy-MM-dd")Date datainicial, @DateTimeFormat(pattern = "yyyy-MM-dd") Date datafinal) {

				
		String hql = "FROM Pauta m WHERE m.data BETWEEN :start AND :end";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("start", datainicial);
		query.setParameter("end", datafinal);
		 
		List<Pauta> lista = query.list();
		
		return lista;
	}

	
}
