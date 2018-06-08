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

import br.com.tfdonline.modelo.Distribuicao;
import br.com.tfdonline.modelo.Marcacao;

@Repository
@Transactional
public class DistribuicaoDAOImpl implements DistribuicaoDAOI, Serializable{

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
	public void addDistribuicao(Distribuicao distribuicao) {
		 sessionFactory.getCurrentSession().save(distribuicao);
		
	}

	@Override
	public void deleteDistribuicaoByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		Distribuicao distribuicao = sessionFactory.getCurrentSession().load(Distribuicao.class,id);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(distribuicao);
		System.out.println("dentro do DAO..deletei o Distribuicao"+ id);
		
	}

	@Override
	public Distribuicao findByID(Integer id) {
		// TODO Auto-generated method stub
		Distribuicao distribuicao = null;
		distribuicao= sessionFactory.getCurrentSession().get(Distribuicao.class, id);
		
		return distribuicao;
	}

	@Override
	public void updateDistribuicao(Distribuicao distribuicao) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(distribuicao);
				
		System.out.println("---> Distribuicao atualizado pelo DAO!!!");
		
	}
	
	@Override
	public List<Distribuicao> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from Distribuicao");
           List<Distribuicao> lista = (List<Distribuicao>) query.list(); 
           System.out.println("-------->>  Distribuicaos encontrados ="+ lista.size());
           return lista;
		    	
 	 }
	public List<Distribuicao> findbyDescricao(String descricao){
		
		
		String hql = "from Distribuicao where descricao like :keyword";
		String keyword = descricao;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		 
		List<Distribuicao> lista = query.list();
		return lista;
		
	}

	@Override
	public void saveOrUpdate(Distribuicao distribuicao) {
		// TODO Auto-generated method stub
		if (findByID(distribuicao.getId())==null) {
			this.addDistribuicao(distribuicao);
			System.out.println("estou no DAO, tentanto ADD a distribuicao...");
		} else {
			
			this.updateDistribuicao(distribuicao);
			System.out.println("estou no DAO, tentanto ATUALIZAR a distribuicao...");
		}
	}


	@Override
	public List<Distribuicao> findbyData(@DateTimeFormat(pattern = "yyyy-MM-dd")Date datainicial) {
	
	return findbyData(datainicial, new Date());
	
	}	
	@Override
	public List<Distribuicao> findbyData(@DateTimeFormat(pattern = "yyyy-MM-dd")Date datainicial, @DateTimeFormat(pattern = "yyyy-MM-dd") Date datafinal) {

				
		String hql = "FROM Distribuicao m WHERE m.data BETWEEN :start AND :end";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("start", datainicial);
		query.setParameter("end", datafinal);
		 
		List<Distribuicao> lista = query.list();
		
		return lista;
	}


	
}
