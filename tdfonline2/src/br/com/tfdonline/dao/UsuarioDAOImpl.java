package br.com.tfdonline.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tfdonline.modelo.Usuario;

@Repository
@Transactional
public class UsuarioDAOImpl implements UsuarioDAOI, Serializable{

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
	public void addUsuario(Usuario usuario) {
		 sessionFactory.getCurrentSession().save(usuario);
		
	}

	@Override
	public void deleteUsuarioByID(Integer id) {
		System.out.println("recebi dentro do DAO o id="+  id);
		Usuario usuario = sessionFactory.getCurrentSession().load(Usuario.class,id);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(usuario);
		System.out.println("dentro do DAO..deletei o Usuario"+ id);
		
	}

	@Override
	public Usuario findByID(Integer id) {
		// TODO Auto-generated method stub
		Usuario usuario = null;
		usuario= sessionFactory.getCurrentSession().get(Usuario.class, id);
		
		return usuario;
	}

	@Override
	public void updateUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(usuario);
				
		System.out.println("---> Usuario atualizado pelo DAO!!!");
		
	}
	
	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		 
		   Query query  = sessionFactory.getCurrentSession().createQuery("from Usuario");
           List<Usuario> lista = (List<Usuario>) query.list(); 
           System.out.println("-------->>  Usuarios encontrados ="+ lista.size());
           return lista;
		    	
 	 }
	public List<Usuario> findbyName(String nome){
		
		
		String hql = "from Usuario where nome like :keyword";
		String keyword = nome;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		 
		List<Usuario> lista = query.list();
		return lista;
		
	}

	@Override
	public void saveOrUpdate(Usuario usuario) {
		// TODO Auto-generated method stub
		if (findByID(usuario.getId())==null) {
			this.addUsuario(usuario);
			System.out.println("estou no DAO, tentanto ADD a usuario...");
		} else {
			
			this.updateUsuario(usuario);
			System.out.println("estou no DAO, tentanto ATUALIZAR a usuario...");
		}
	}


	

	@Override
	public Usuario findbyLogin(String login){
		
		
		String hql = "from Usuario where login like '"+ login +"'";
		String keyword = login;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		 
		List<Usuario> lista = query.list();
		if (lista.size()>0) {
			return lista.get(0);
		}else
			return null;
		
		
	}
	
	
}
