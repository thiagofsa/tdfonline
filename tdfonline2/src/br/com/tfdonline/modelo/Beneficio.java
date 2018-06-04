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
//@Table(name = "paciente" , schema = "tfdonline")
@Table(name = "beneficio")
public class Beneficio implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	private Date dataautorizacao;
	private Date datavencimento;
	private String nome;
	
	

	public boolean isNew() {
		if ((id==null)|| (id<0))
			return true;
		else
			return false;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public Date getDataautorizacao() {
		return dataautorizacao;
	}




	public void setDataautorizacao(Date dataautorizacao) {
		this.dataautorizacao = dataautorizacao;
	}




	public Date getDatavencimento() {
		return datavencimento;
	}




	public void setDatavencimento(Date datavencimento) {
		this.datavencimento = datavencimento;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public String getNome() {
		return nome;
	}




	public void setNome(String descricao) {
		this.nome = descricao;
	}
	

}
