package br.com.tfdonline.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tfdonline.modelo.Veiculo;

@Repository
@Transactional
public class VeiculoDAOImpl implements VeiculoDAOI, Serializable{

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void addVeiculo(Veiculo veiculo) {
		
		veiculo.setAtivo(1);
		sessionFactory.getCurrentSession().save(veiculo);		
	}

	@Override
	public void deleteVeiculoByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		
		Veiculo veiculo = sessionFactory.getCurrentSession().load(Veiculo.class,id);
		
		veiculo.setAtivo(0);
		
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(veiculo);
		
		System.out.println("dentro do DAO.. Desativei o Veiculo"+ id);		
	}

	@Override
	public Veiculo findByID(Integer id) {
		// TODO Auto-generated method stub
		Veiculo veiculo = null;
		veiculo= sessionFactory.getCurrentSession().get(Veiculo.class, id);
		
		return veiculo;
	}

	@Override
	public void updateVeiculo(Veiculo veiculo) {
		// TODO Auto-generated method stub
		
		veiculo.setAtivo(1);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(veiculo);
				
		System.out.println("---> Veiculo atualizado pelo DAO!!!");		
	}
	
	@Override
	public List<Veiculo> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from Veiculo v where v.ativo = 1  order by descricao");
           List<Veiculo> lista = (List<Veiculo>) query.list(); 
           System.out.println("-------->>  Veiculos encontrados ="+ lista.size());
           return lista;		    	
 	 }
	
	public List<Veiculo> findbyDescricao(String descricao){
			
		String hql = "from Veiculo where descricao like :keyword and ativo = 1";
		String keyword = descricao;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		 
		List<Veiculo> lista = query.list();
		return lista;		
	}

	
	@Override
	public void saveOrUpdate(Veiculo veiculo) {
		// TODO Auto-generated method stub
		
		if (findByID(veiculo.getId())==null) {
			this.addVeiculo(veiculo);
			System.out.println("estou no DAO, tentanto ADD a veiculo...");
		} else {
			
			this.updateVeiculo(veiculo);
			System.out.println("estou no DAO, tentanto ATUALIZAR a veiculo...");
		}
	}
}