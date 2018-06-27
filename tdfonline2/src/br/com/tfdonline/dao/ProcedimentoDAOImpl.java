package br.com.tfdonline.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tfdonline.modelo.Procedimento;

@Repository
@Transactional
public class ProcedimentoDAOImpl implements ProcedimentoDAOI, Serializable{

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
	public void addProcedimento(Procedimento procedimento) {
		
		procedimento.setAtivo(1);
		sessionFactory.getCurrentSession().save(procedimento);		
	}

	@Override
	public void deleteProcedimentoByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		
		Procedimento procedimento = sessionFactory.getCurrentSession().load(Procedimento.class,id);
		
		procedimento.setAtivo(0);
		
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(procedimento);
		
		System.out.println("dentro do DAO..dsativei o Procedimento"+ id);		
	}

	@Override
	public Procedimento findByID(Integer id) {
		// TODO Auto-generated method stub
		Procedimento procedimento = null;
		procedimento= sessionFactory.getCurrentSession().get(Procedimento.class, id);
		
		return procedimento;
	}

	@Override
	public void updateProcedimento(Procedimento procedimento) {
		// TODO Auto-generated method stub
		
		procedimento.setAtivo(1);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(procedimento);
				
		System.out.println("---> Procedimento atualizado pelo DAO!!!");		
	}
	
	@Override
	public List<Procedimento> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from Procedimento p where p.ativo = 1  order by nome");
           List<Procedimento> lista = (List<Procedimento>) query.list(); 
           System.out.println("-------->>  Procedimentos encontrados ="+ lista.size());
           return lista;		    	
 	 }
	
	public List<Procedimento> findbyNome(String nome){
		
		
		String hql = "from Procedimento where nome like :keyword and ativo = 1";
		String keyword = nome;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		 
		List<Procedimento> lista = query.list();
		return lista;		
	}

	@Override
	public void saveOrUpdate(Procedimento procedimento) {
		// TODO Auto-generated method stub
		
		if (findByID(procedimento.getId())==null) {
			this.addProcedimento(procedimento);
			System.out.println("estou no DAO, tentanto ADD a procedimento...");
		} else {
			
			this.updateProcedimento(procedimento);
			System.out.println("estou no DAO, tentanto ATUALIZAR a procedimento...");
		}
	}

	public  List<String>  getAreasProcedimento(){
		
		ArrayList <String> areas = new ArrayList<>();
		areas = new ArrayList<String> ();
		areas.add("Area 1");
		areas.add("Area 2");
		areas.add("Area 3");
		areas.add("Area 4");
		
		return areas;		
	}

}