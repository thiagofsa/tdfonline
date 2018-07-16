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

import br.com.tfdonline.modelo.BeneficioAvulso;
import br.com.tfdonline.modelo.Marcacao;

@Repository
@Transactional
public class BeneficioAvulsoDAOImp implements BeneficioAvulsoDAOI, Serializable{

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
	public void addBeneficioAvulso(BeneficioAvulso beneficioavulso) {
		 sessionFactory.getCurrentSession().save(beneficioavulso);
		
	}

	@Override
	public void deleteBeneficioAvulsoByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		BeneficioAvulso beneficioavulso = sessionFactory.getCurrentSession().load(BeneficioAvulso.class,id);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(beneficioavulso);
		System.out.println("dentro do DAO..deletei o BeneficioAvulso"+ id);
		
	}

	@Override
	public BeneficioAvulso findByID(Integer id) {
		// TODO Auto-generated method stub
		BeneficioAvulso beneficioavulso = null;
		beneficioavulso= sessionFactory.getCurrentSession().get(BeneficioAvulso.class, id);
		
		return beneficioavulso;
	}

	@Override
	public void updateBeneficioAvulso(BeneficioAvulso beneficioavulso) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(beneficioavulso);
				
		System.out.println("---> BeneficioAvulso atualizado pelo DAO!!!");
		
	}
	
	@Override
	public List<BeneficioAvulso> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from BeneficioAvulso");
           List<BeneficioAvulso> lista = (List<BeneficioAvulso>) query.list(); 
           System.out.println("-------->>  BeneficioAvulsos encontrados ="+ lista.size());
           return lista;
		    	
 	 }
	public List<BeneficioAvulso> findbyPacienteID(Integer idpaciente){
		
		
		String hql = "from BeneficioAvulso b where b.paciente.id=:keyword";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keyword", idpaciente);
		 
		List<BeneficioAvulso> lista = query.list();
		return lista;
		
	}

	@Override
	public void saveOrUpdate(BeneficioAvulso beneficioavulso) {
		// TODO Auto-generated method stub
		if (findByID(beneficioavulso.getId())==null) {
			this.addBeneficioAvulso(beneficioavulso);
			System.out.println("estou no DAO, tentanto ADD a beneficioavulso...");
		} else {
			
			this.updateBeneficioAvulso(beneficioavulso);
			System.out.println("estou no DAO, tentanto ATUALIZAR a beneficioavulso...");
		}
	}


	@Override
	public List<BeneficioAvulso> findbyMarcacaoID(Integer idmarcacao) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<BeneficioAvulso> findLast() {
		// TODO Auto-generated method stub
		 Query query  = sessionFactory.getCurrentSession().createQuery("from BeneficioAvulso b ORDER BY m.id DESC");
		   query.setMaxResults(15);
		   List<BeneficioAvulso> lista = (List<BeneficioAvulso>) query.list(); 
         System.out.println("-------->>  Marcacaos encontrados ="+ lista.size());
         return lista;
	}


	@Override
	public List<BeneficioAvulso> findbyNomeData(Date datainicial, Date datafinal, String nome) {
		// TODO Auto-generated method stub
String hql = "FROM BeneficioAvulso ba WHERE ba.dataviagemida BETWEEN :start AND :end AND ba.paciente.nome like:keynome" ;
		
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keynome", "%" + nome + "%");
		query.setParameter("start", datainicial);
		query.setParameter("end", datafinal);
		 
		List<BeneficioAvulso> lista = query.list();
		
		return lista;
	}

	@Override
	public List<BeneficioAvulso> findbyData(@DateTimeFormat(pattern = "yyyy-MM-dd")Date datainicial, @DateTimeFormat(pattern = "yyyy-MM-dd") Date datafinal) {

				
		String hql = "FROM BeneficioAvulso b WHERE m.dataviagem BETWEEN :start AND :end";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("start", datainicial);
		query.setParameter("end", datafinal);
		 
		List<BeneficioAvulso> lista = query.list();
		
		return lista;
	}

	
}
