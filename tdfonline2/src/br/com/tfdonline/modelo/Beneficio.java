package br.com.tfdonline.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
//@Table(name = "paciente" , schema = "tfdonline")
@Table(name = "beneficio")
public class Beneficio implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	private float valor;
	private Date dataviagemida;
	private Date dataviagemvolta;
	
	private String caminhoarquivo;
	
	@OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "idencaminhamento")
	private Encaminhamento encaminhamento;
	
	
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




	public float getValor() {
		return valor;
	}




	public void setValor(float valor) {
		this.valor = valor;
	}




	public Date getDataviagemida() {
		return dataviagemida;
	}




	public void setDataviagemida(Date dataviagemida) {
		this.dataviagemida = dataviagemida;
	}




	public Date getDataviagemvolta() {
		return dataviagemvolta;
	}




	public void setDataviagemvolta(Date dataviagemvolta) {
		this.dataviagemvolta = dataviagemvolta;
	}




	public String getCaminhoarquivo() {
		return caminhoarquivo;
	}




	public void setCaminhoarquivo(String caminhoarquivo) {
		this.caminhoarquivo = caminhoarquivo;
	}




	




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public Encaminhamento getEncaminhamento() {
		return encaminhamento;
	}




	public void setEncaminhamento(Encaminhamento encaminhamento) {
		this.encaminhamento = encaminhamento;
	}




	



		

}
