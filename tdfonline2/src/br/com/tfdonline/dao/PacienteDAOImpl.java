package br.com.tfdonline.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tfdonline.modelo.Paciente;

@Repository
@Transactional
public class PacienteDAOImpl implements PacienteDAOI, Serializable{

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
	public void addPaciente(Paciente paciente) {
		 sessionFactory.getCurrentSession().save(paciente);
		
	}

	@Override
	public void deletePacienteByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		Paciente paciente = sessionFactory.getCurrentSession().load(Paciente.class,id);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(paciente);
		System.out.println("dentro do DAO..deletei o Paciente"+ id);
		
	}

	@Override
	public Paciente findByID(Integer id) {
		// TODO Auto-generated method stub
		Paciente paciente = null;
		paciente= sessionFactory.getCurrentSession().get(Paciente.class, id);
		
		return paciente;
	}

	@Override
	public void updatePaciente(Paciente paciente) {
		// TODO Auto-generated method stub
		//**sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(paciente);
				
		System.out.println("---> Paciente atualizado pelo DAO!!!");
		
	}
	
	@Override
	public List<Paciente> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from Paciente");
           List<Paciente> lista = (List<Paciente>) query.list(); 
           System.out.println("-------->>  Pacientes encontrados ="+ lista.size());
           return lista;
		    	
 	 }
	public List<Paciente> findbyName(String nome){
		
		
		String hql = "from Paciente where nome like :keyword";
		String keyword = nome;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		 
		List<Paciente> lista = query.list();
		return lista;
		
	}

	@Override
	public void saveOrUpdate(Paciente paciente) {
		// TODO Auto-generated method stub
		if (findByID(paciente.getId())==null) {
			this.addPaciente(paciente);
			System.out.println("estou no DAO, tentanto ADD a paciente...");
		} else {
			
			this.updatePaciente(paciente);
			System.out.println("estou no DAO, tentanto ATUALIZAR a paciente...");
		}
	}
	
	
	

	
}
