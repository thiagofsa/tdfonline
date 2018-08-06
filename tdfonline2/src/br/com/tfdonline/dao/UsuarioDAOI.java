package br.com.tfdonline.dao;

import java.util.List;

import br.com.tfdonline.modelo.Usuario;

public interface UsuarioDAOI {

	public static Integer PERFIL_ADMIN=1;
	public static Integer PERFIL_USUARIO=2;
	public static Integer PERFIL_MOTORISTA=3;
	public static Integer PERFIL_TRANSPORTE=4;
	public static Integer PERFIL_MEDICO=5;
	
	
	void addUsuario(Usuario usuario);
    
    List<Usuario> findAll();
     
    void deleteUsuarioByID(Integer id);
     
    Usuario findByID(Integer id);
     
    void updateUsuario(Usuario usuario);

	void saveOrUpdate(Usuario usuario);
	
	public List<Usuario> findbyName(String nome);
	
	public Usuario findbyLogin(String login);
	

	
	
}
