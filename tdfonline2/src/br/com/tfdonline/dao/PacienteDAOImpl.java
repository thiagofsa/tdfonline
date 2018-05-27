package br.com.tfdonline.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.spi.LoggerFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tfdonline.modelo.Paciente;
import javassist.bytecode.Descriptor.Iterator;


@Repository
@Transactional
public class PacienteDAOImpl  implements PacienteDAOI, Serializable{

	//private static final Logger logger = LoggerFactory.getLogger(PacienteDAOImpl.class);

	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	public List<Paciente> findByNome(){
		
		//List<Paciente> lista = (List<Paciente>) hibernateTemplate.find("FROM Paciente WHERE nome=?", "Ana");
		
		return null;
		
	}


	@Override
	public void addPaciente(Paciente paciente) {
		sessionFactory.getCurrentSession().save(paciente);
		
	}


	@Override
	public List<Paciente> findAllPacientes() {
		// TODO Auto-generated method stub
		 
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from Paciente");
           List<Paciente> lista = (List<Paciente>) query.list(); 
           System.out.println("-------->>  Pacientes encontrados ="+ lista.size());
           return lista;
		    	
 	 }
		    
		
		   
		
	


	@Override
	public void deletePacienteByID(Integer id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Paciente findByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updatePaciente(Paciente paciente) {
		// TODO Auto-generated method stub
		
	}


	
	
    

	
}
