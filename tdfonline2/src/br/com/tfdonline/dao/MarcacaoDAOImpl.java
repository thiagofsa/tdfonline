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
import br.com.tfdonline.modelo.Requisicao;

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
		//
		sessionFactory.getCurrentSession().merge(marcacao);

		//**sessionFactory.getCurrentSession().clear();
		//**sessionFactory.getCurrentSession().update(marcacao);
				
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
	public List<Marcacao> findLast() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from Marcacao m ORDER BY m.id DESC");
		   query.setMaxResults(20);
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
	
	return findbyData(datainicial, datainicial);
	
	}	
	@Override
	public List<Marcacao> findbyData(@DateTimeFormat(pattern = "yyyy-MM-dd")Date datainicial, @DateTimeFormat(pattern = "yyyy-MM-dd") Date datafinal) {

				
		String hql = "FROM Marcacao m WHERE m.dataviagem BETWEEN :start AND :end";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("start", datainicial);
		query.setParameter("end", datafinal);
		 
		List<Marcacao> lista = query.list();
		
		return lista;
	}


	
	public List<Marcacao> findbyNomeData(@DateTimeFormat(pattern = "yyyy-MM-dd")Date datainicial, @DateTimeFormat(pattern = "yyyy-MM-dd") Date datafinal, String nome) {

				
		String hql = "FROM Marcacao m WHERE m.dataviagem BETWEEN :start AND :end AND m.paciente.nome like:keynome" ;
		
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keynome", "%" + nome + "%");
		query.setParameter("start", datainicial);
		query.setParameter("end", datafinal);
		 
		List<Marcacao> lista = query.list();
		
		return lista;
	}
	
	@Override
	public List<Marcacao> findbyNomePaciente(String nomepaciente) {
		// TODO Auto-generated method stub
		String hql = "FROM Marcacao m WHERE m.paciente.nome like :keyword";
		String keyword = nomepaciente;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		 
		List<Marcacao> lista = query.list();
		
		return lista;
	}


	@Override
	public List<Marcacao> findbyNaoEncaminhadas(Date datainicial, Date datafinal) {
		// TODO Auto-generated method stub
		String hql = "FROM Marcacao m WHERE m.dataviagem BETWEEN :start AND :end AND m.encaminhada<1" ;
		
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		query.setParameter("start", datainicial);
		query.setParameter("end", datafinal);
		 
		List<Marcacao> lista = query.list();
		
		return lista;
	}

	@Override
	public List<Marcacao> findbyNaoEncaminhadas() {
		// TODO Auto-generated method stub
		String hql = "FROM Marcacao m WHERE m.encaminhada<1" ;
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		 
		List<Marcacao> lista = query.list();
		
		return lista;
	}
	
	
	@Override
	public List<Marcacao> findbyNomeNaoEncaminhadasPeriodo(String nomepaciente, Date datainicio, Date datafim) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String hql = "FROM Marcacao m WHERE m.encaminhada<1";
				
				if ((nomepaciente!=null) && (nomepaciente.trim().length()>0)) {
					
					hql+= " AND m.paciente.nome like :keyword";
					
				}
				
				if (datainicio!=null) {
					hql+= " AND m.data BETWEEN :start AND :end";
					if (datafim==null) {
						datafim= new Date();
					}
				}
				
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				
				if (datainicio!=null) {
					query.setParameter("start", datainicio);
					query.setParameter("end", datafim);
				}
				
				if ((nomepaciente!=null) && (nomepaciente.trim().length()>0)) {
					query.setParameter("keyword", nomepaciente);
					
				}
				 
				List<Marcacao> lista = query.list();
				
				return lista;
	}


	@Override
	public int findbyContadorNaoConfirmadasNaoEncaminhadas(Date datainicial) {
		// TODO Auto-generated method stub

		System.out.println("Entrando no MarcacaoDAO.findbyContadorNaoConfirmadasNaoEncaminhadas");
				
		
		String squery = "select COUNT(*) from Marcacao m where m.confirmada=0 AND m.encaminhada=0 AND m.dataviagem=:data";
		
		
		Query query = sessionFactory.getCurrentSession().createQuery(squery);
		query.setParameter("data", datainicial);
		int count = (int)query.uniqueResult();
		
				
		return count;
		
	}
	
	@Override
	public Long findbyContadorConfirmadasNaoEncaminhadas(Date datainicial) {
		// TODO Auto-generated method stub

		System.out.println("Entrando no MarcacaoDAO.findbyContadorNaoConfirmadasNaoEncaminhadas");
				
		
		String squery = "select COUNT(*) from Marcacao m where m.confirmada=1 AND m.encaminhada=0 AND m.dataviagem=:data";
		
		
		Query query = sessionFactory.getCurrentSession().createQuery(squery);
		query.setParameter("data", datainicial);
		Long count = (Long)query.uniqueResult();
		
				
		return count;
		
	}

	@Override
	public Long findbyContadorEncaminhadas(Date datainicial) {
		// TODO Auto-generated method stub
		System.out.println("Entrando no MarcacaoDAO.findbyContadorNaoConfirmadasNaoEncaminhadas");
				
		
		String squery = "select COUNT(*) from Marcacao m where m.encaminhada=1 AND m.dataviagem=:data";
		
		
		Query query = sessionFactory.getCurrentSession().createQuery(squery);
		query.setParameter("data", datainicial);
		Long count = (Long)query.uniqueResult();
		
				
		return count;
	}

	
	@Override
	public Long findbyContadorMarcacoesData(Date datainicial) {
		// TODO Auto-generated method stub
		System.out.println("Entrando no MarcacaoDAO.findbyContadorMarcacoesData");
				
		
		String squery = "select COUNT(*) from Marcacao m where m.dataviagem=:data";
		
		
		Query query = sessionFactory.getCurrentSession().createQuery(squery);
		query.setParameter("data", datainicial);
		Long count = (Long)query.uniqueResult();
		
				
		return count;
	}
	

	
}
