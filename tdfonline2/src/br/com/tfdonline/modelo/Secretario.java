package br.com.tfdonline.modelo;

import java.io.Serializable;


import java.util.Date;
import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.persistence.Table;


@Entity
//
@Table(name = "secretario")
public class Secretario implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	private String nome;
	private Date datainiciomandato;
	private Date datafimmandato;
	private Integer ativo;
	private String caminhoarquivo;
	public Integer getId() {
		return id;
	}
	
	public Secretario() {
		
		
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDatainiciomandato() {
		return datainiciomandato;
	}
	public void setDatainiciomandato(Date datainiciomandato) {
		this.datainiciomandato = datainiciomandato;
	}
	public Date getDatafimmandato() {
		return datafimmandato;
	}
	public void setDatafimmandato(Date datafimmandato) {
		this.datafimmandato = datafimmandato;
	}
	public Integer getAtivo() {
		return ativo;
	}
	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
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
	public String getCaminhoarquivo() {
		return caminhoarquivo;
	}
	public void setCaminhoarquivo(String caminhoarquivo) {
		this.caminhoarquivo = caminhoarquivo;
	}	
	

}
