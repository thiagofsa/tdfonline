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

import br.com.tfdonline.modelo.Requisicao;
import br.com.tfdonline.modelo.Marcacao;
import br.com.tfdonline.modelo.Paciente;

@Repository
@Transactional
public class RequisicaoDAOImpl implements RequisicaoDAOI, Serializable{

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
	public void addRequisicao(Requisicao requisicao) {
		 sessionFactory.getCurrentSession().save(requisicao);
		
	}

	@Override
	public void deleteRequisicaoByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		Requisicao requisicao = sessionFactory.getCurrentSession().load(Requisicao.class,id);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(requisicao);
		System.out.println("dentro do DAO..deletei o Requisicao"+ id);
		
	}

	@Override
	public Requisicao findByID(Integer id) {
		// TODO Auto-generated method stub
		Requisicao requisicao = null;
		requisicao= sessionFactory.getCurrentSession().get(Requisicao.class, id);
		
		return requisicao;
	}

	@Override
	public void updateRequisicao(Requisicao requisicao) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(requisicao);
				
		System.out.println("---> Requisicao atualizado pelo DAO!!!");
		
	}
	
	@Override
	public List<Requisicao> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from Requisicao");
           List<Requisicao> lista = (List<Requisicao>) query.list(); 
           System.out.println("-------->>  Requisicaos encontrados ="+ lista.size());
           return lista;
		    	
 	 }
	
	@Override
	public void saveOrUpdate(Requisicao requisicao) {
		// TODO Auto-generated method stub
		if (findByID(requisicao.getId())==null) {
			this.addRequisicao(requisicao);
			System.out.println("estou no DAO, tentanto ADD a requisicao...");
		} else {
			
			this.updateRequisicao(requisicao);
			System.out.println("estou no DAO, tentanto ATUALIZAR a requisicao...");
		}
	}


	@Override
	public List<Requisicao> findbyData(@DateTimeFormat(pattern = "yyyy-MM-dd")Date datainicial) {
	
	return findbyData(datainicial, datainicial);
	
	}	
	@Override
	public List<Requisicao> findbyData(@DateTimeFormat(pattern = "yyyy-MM-dd")Date datainicial, @DateTimeFormat(pattern = "yyyy-MM-dd") Date datafinal) {

				
		String hql = "FROM Requisicao d WHERE d.data BETWEEN :start AND :end";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("start", datainicial);
		query.setParameter("end", datafinal);
		 
		List<Requisicao> lista = query.list();
		
		return lista;
	}

	public List<Requisicao> findbyNomePaciente(String nome){
		
		
		String hql = "from Requisicao r WHERE r.paciente.nome like :keyword ORDER BY r.id DESC";
		String keyword = nome;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		 
		List<Requisicao> lista = query.list();
		return lista;
		
	}


	@Override
	public List<Requisicao> findbyNomePacienteDataInicialDataFinal(String nomepaciente, Date datainicial,
			Date datafinal) {
		// TODO Auto-generated method stub
		String hql = "FROM Requisicao r WHERE r.data BETWEEN :start AND :end";
		
		if (nomepaciente!=null) {
			
			hql+= " AND r.paciente.nome like :keyword";
			
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("start", datainicial);
		query.setParameter("end", datafinal);
		if (nomepaciente!=null) {
			query.setParameter("keyword", nomepaciente);
			
			
		}
		
		 
		List<Requisicao> lista = query.list();
		
		return lista;
	}
	
}
