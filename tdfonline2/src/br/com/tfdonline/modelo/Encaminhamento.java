package br.com.tfdonline.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
//
@Table(name = "encaminhamento")
public class Encaminhamento implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataviagem;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataviagemvolta;
	
	private Integer vagas;
	
	private Integer embarcado;
	
	private Integer encaminhamentovoltagerado;
	
	@OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "iddistribuicao")
	Distribuicao distribuicao;
	
	@OneToOne 
    @JoinColumn(name = "idmarcacao")
	Marcacao marcacao;


	public Distribuicao getDistribuicao() {
		return distribuicao;
	}

	public void setDistribuicao(Distribuicao distribuicao) {
		this.distribuicao = distribuicao;
	}

	public Marcacao getMarcacao() {
		return marcacao;
	}

	public void setMarcacao(Marcacao marcacao) {
		this.marcacao = marcacao;
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

	public Date getDataviagem() {
		return dataviagem;
	}

	public void setDataviagem(Date dataviagem) {
		this.dataviagem = dataviagem;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getEmbarcado() {
		return embarcado;
	}

	public void setEmbarcado(Integer embarcado) {
		this.embarcado = embarcado;
	}

	public void setVagas(Integer vagas) {
		this.vagas = vagas;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public int getVagas() {
		return vagas;
	}

	public Date getDataviagemvolta() {
		return dataviagemvolta;
	}

	public void setDataviagemvolta(Date dataviagemvolta) {
		this.dataviagemvolta = dataviagemvolta;
	}

	public Integer getEncaminhamentovoltagerado() {
		return encaminhamentovoltagerado;
	}

	public void setEncaminhamentovoltagerado(Integer encaminhamentovoltagerado) {
		this.encaminhamentovoltagerado = encaminhamentovoltagerado;
	}


}
