package br.com.tfdonline.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
//@Table(name = "paciente" , schema = "tfdonline")
@Table(name = "pauta")
public class Pauta implements Serializable {

private static final long serialVersionUID = 1L;

	public Pauta() {
		
		this.id=-1;
		this.aberta=1;
		this.data = new Date();
		
	} 
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;
	private String descricao;
	private Integer aberta;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getAberta() {
		return aberta;
	}
	public void setAberta(Integer aberta) {
		this.aberta = aberta;
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

	
		

}
