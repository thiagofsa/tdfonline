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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
//
@Table(name = "encaminhamentovolta")
public class EncaminhamentoVolta implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataviagem;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	private Integer vagas;
	
	private String observacao;
	
		
	@OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "iddistribuicao")
	Distribuicao distribuicao;

	@OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "idencaminhamento")
	Encaminhamento encaminhamento;
	
	@OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "idpaciente")
	Paciente paciente;
		

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getVagas() {
		return vagas;
	}

	public void setVagas(Integer vagas) {
		this.vagas = vagas;
	}

	
	public Distribuicao getDistribuicao() {
		return distribuicao;
	}

	public void setDistribuicao(Distribuicao distribuicao) {
		this.distribuicao = distribuicao;
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

	public Encaminhamento getEncaminhamento() {
		return encaminhamento;
	}

	public void setEncaminhamento(Encaminhamento encaminhamento) {
		this.encaminhamento = encaminhamento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}