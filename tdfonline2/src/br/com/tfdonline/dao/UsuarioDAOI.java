package br.com.tfdonline.dao;

import java.util.List;

import br.com.tfdonline.modelo.Usuario;

public interface UsuarioDAOI {

	void addUsuario(Usuario usuario);
    
    List<Usuario> findAll();
     
    void deleteUsuarioByID(Integer id);
     
    Usuario findByID(Integer id);
     
    void updateUsuario(Usuario usuario);

	void saveOrUpdate(Usuario usuario);
	
	public List<Usuario> findbyName(String nome);
	
	public Usuario findbyLogin(String login);
	

	
	
}
