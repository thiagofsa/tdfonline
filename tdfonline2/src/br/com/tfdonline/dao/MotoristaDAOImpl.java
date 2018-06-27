package br.com.tfdonline.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tfdonline.modelo.Motorista;

@Repository
@Transactional
public class MotoristaDAOImpl implements MotoristaDAOI, Serializable{

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
		
	@Override
	public void addMotorista(Motorista motorista) {
		
		motorista.setAtivo(1);
		sessionFactory.getCurrentSession().save(motorista);		
	}

		
	@Override
	public void deleteMotoristaByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		
		Motorista motorista = sessionFactory.getCurrentSession().load(Motorista.class,id);
		
		motorista.setAtivo(0);
		
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(motorista);
		
		System.out.println("dentro do MOtoristaDAO..desativei o Motorista");		
	}

		
	@Override
	public Motorista findByID(Integer id) {
		// TODO Auto-generated method stub
		Motorista motorista = null;
		motorista= sessionFactory.getCurrentSession().get(Motorista.class, id);
		
		return motorista;
	}

		
	@Override
	public void updateMotorista(Motorista motorista) {
		// TODO Auto-generated method stub
		
		motorista.setAtivo(1);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(motorista);
				
		System.out.println("---> Motorista atualizado pelo DAO!!!");		
	}
		
	
	@Override
	public List<Motorista> findAll() {
		// TODO Auto-generated method stub
		 		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from Motorista m where m.ativo = 1  order by nome");
           List<Motorista> lista = (List<Motorista>) query.list(); 
           System.out.println("-------->>  Motoristas encontrados ="+ lista.size());
           return lista;		    	
 	 }
	
	public List<Motorista> findbyName(String nome){
			
		String hql = "from Motorista where nome like :keyword  and ativo = 1";
		String keyword = nome;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		 
		List<Motorista> lista = query.list();
		return lista;		
	}

		
	@Override
	public void saveOrUpdate(Motorista motorista) {
		// TODO Auto-generated method stub
		
		if (findByID(motorista.getId())==null) {
			this.addMotorista(motorista);
			System.out.println("estou no DAO, tentanto2 add o motorista...");
		} else {
			
			this.updateMotorista(motorista);
			System.out.println("estou no DAO, tentanto2 ATUALIZAR o motorista...");
		}
	}
	
}