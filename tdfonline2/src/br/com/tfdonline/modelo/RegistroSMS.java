package br.com.tfdonline.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;




@Entity
//
@Table(name = "registrosms")
public class RegistroSMS implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	
	@OneToOne
    @JoinColumn(name = "idencaminhamento")
	private Encaminhamento encaminhamento;
	
	@OneToOne
    @JoinColumn(name = "idencaminhamentovolta")
	private EncaminhamentoVolta encaminhamentovolta;
	
	private Date dataenvio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Encaminhamento getEncaminhamento() {
		return encaminhamento;
	}

	public void setEncaminhamento(Encaminhamento encaminhamento) {
		this.encaminhamento = encaminhamento;
	}

	public EncaminhamentoVolta getEncaminhamentovolta() {
		return encaminhamentovolta;
	}

	public void setEncaminhamentovolta(EncaminhamentoVolta encaminhamentovolta) {
		this.encaminhamentovolta = encaminhamentovolta;
	}

	public Date getDataenvio() {
		return dataenvio;
	}

	public void setDataenvio(Date dataenvio) {
		this.dataenvio = dataenvio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	


}
