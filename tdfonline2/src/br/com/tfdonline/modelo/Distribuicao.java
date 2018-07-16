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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
//
@Table(name = "distribuicao")
public class Distribuicao implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataviagem;
	private Integer vagas;
	private Integer tipoviagem;//0=ida, 1= volta 
	
	@OneToOne
    @JoinColumn(name = "idmotorista")
	private Motorista motorista;
	
	@OneToOne
    @JoinColumn(name = "idveiculo")
	private Veiculo veiculo;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
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

	public Integer getVagas() {
		return vagas;
	}

	public void setVagas(Integer vagas) {
		this.vagas = vagas;
	}

	public Integer getTipoviagem() {
		return tipoviagem;
	}

	public void setTipoviagem(Integer tipoviagem) {
		this.tipoviagem = tipoviagem;
	}	


}
