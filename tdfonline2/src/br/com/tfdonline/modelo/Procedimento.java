package br.com.tfdonline.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
//@Table(name = "paciente" , schema = "tfdonline")
@Table(name = "procedimento")
public class Procedimento implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	private String area ;
	private String nome;
	private Integer ativo;
	
		
	public Procedimento () {
		this.id = -1;
						
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public boolean isNew() {
		if ((id==null)|| (id<0))
			return true;
		else
			return false;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}

}
