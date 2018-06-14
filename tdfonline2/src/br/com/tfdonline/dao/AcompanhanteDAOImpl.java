package br.com.tfdonline.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tfdonline.modelo.Acompanhante;

@Repository
@Transactional
public class AcompanhanteDAOImpl implements AcompanhanteDAOI, Serializable{

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
	public void addAcompanhante(Acompanhante acompanhante) {
		 sessionFactory.getCurrentSession().save(acompanhante);
		
	}

	@Override
	public void deleteAcompanhanteByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		Acompanhante acompanhante = sessionFactory.getCurrentSession().load(Acompanhante.class,id);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(acompanhante);
		System.out.println("dentro do DAO..deletei o Acompanhante"+ id);
		
	}

	@Override
	public Acompanhante findByID(Integer id) {
		// TODO Auto-generated method stub
		Acompanhante acompanhante = null;
		acompanhante= sessionFactory.getCurrentSession().get(Acompanhante.class, id);
		
		return acompanhante;
	}

	@Override
	public void updateAcompanhante(Acompanhante acompanhante) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(acompanhante);
				
		System.out.println("---> Acompanhante atualizado pelo DAO!!!");
		
	}
	
	@Override
	public List<Acompanhante> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from Acompanhante");
           List<Acompanhante> lista = (List<Acompanhante>) query.list(); 
           System.out.println("-------->>  Acompanhantes encontrados ="+ lista.size());
           return lista;
		    	
 	 }
	public List<Acompanhante> findbyName(String nome){
		
		
		String hql = "from Acompanhante where nome like :keyword";
		String keyword = nome;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		 
		List<Acompanhante> lista = query.list();
		return lista;
		
	}

	@Override
	public void saveOrUpdate(Acompanhante acompanhante) {
		// TODO Auto-generated method stub
		if (findByID(acompanhante.getId())==null) {
			this.addAcompanhante(acompanhante);
			System.out.println("estou no DAO, tentanto ADD a acompanhante...");
		} else {
			
			this.updateAcompanhante(acompanhante);
			System.out.println("estou no DAO, tentanto ATUALIZAR a acompanhante...");
		}
	}


	@Override
	public List<Acompanhante> findbyMarcacaoID(Integer marcacaoid) {
		// TODO Auto-generated method stub
		
		
		System.out.println("Entrando no AcompanhanteDAO.findbyMarcacaoID()");
		System.out.println("Valor do ID Marcacao="+ marcacaoid );
		
		
		String squery = "select apm.idacompanhante from Acompanhantepacientemarcacao apm, Marcacao m where m.id=apm.idmarcacao and m.id=:marcacaoid";
		List<Acompanhante> acompanhantes = new ArrayList<Acompanhante>();
		
		Query query = sessionFactory.getCurrentSession().createQuery(squery);
		query.setParameter("marcacaoid",marcacaoid);
		
		List result = query.list();
		Acompanhante acompanhante ;
		for (int i=0; i< result.size(); i++) {
			
			
			System.out.println("Linha"+i+ "=" +result.get(i));
			acompanhante = findByID((Integer)result.get(i));
			acompanhantes.add(acompanhante);
			
		}
		
		
		return acompanhantes;
	}

	
}
