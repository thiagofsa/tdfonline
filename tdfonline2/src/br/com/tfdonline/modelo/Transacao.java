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
@Table(name = "transacao")
public class Transacao implements Serializable {

private static final long serialVersionUID = 1L;
	
	//USUARIO =1 
	//MARCACAO = 2
	//ENCAMINHAMENTO = 3
	//DISTRIBUICAO = 4
	//BENEFICIO =5

	//ADD =1
	//UPDATE = 2
	//DELETE =3


	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	private Integer entidade;
	private Integer operacao;
	private Integer valor;
	
	public Transacao () {
		this.setId(new Integer(-1));
	}
	
	public Integer getId() {
		return id;
		
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEntidade() {
		return entidade;
	}
	public void setEntidade(Integer entidade) {
		this.entidade = entidade;
	}
	public Integer getOperacao() {
		return operacao;
	}
	public void setOperacao(Integer operacao) {
		this.operacao = operacao;
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
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
