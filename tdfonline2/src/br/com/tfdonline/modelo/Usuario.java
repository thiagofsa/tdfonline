package br.com.tfdonline.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
//@Table(name = "paciente" , schema = "tfdonline")
@Table(name = "usuario")
public class Usuario implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	
	private String nome;
	private String login;
	private String senha;
	
	@Transient
	private String senhanova1;
	
	@Transient
	private String senhanova2;
	
	private String telefone;
	private String email;
	
	
	private Integer perfilusuario;
	
		
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isNew() {
		if ((id==null)|| (id<0))
			return true;
		else
			return false;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenhanova1() {
		return senhanova1;
	}
	public void setSenhanova1(String senhanova1) {
		this.senhanova1 = senhanova1;
	}
	public String getSenhanova2() {
		return senhanova2;
	}
	public void setSenhanova2(String senhanova2) {
		this.senhanova2 = senhanova2;
	}
	public Integer getPerfilusuario() {
		return perfilusuario;
	}
	public void setPerfilusuario(Integer perfilusuario) {
		this.perfilusuario = perfilusuario;
	}	
	
}
