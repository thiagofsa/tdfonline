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
@Table(name = "acompanhantepacientemarcacao",schema = "tfdonline")
public class Acompanhantepacientemarcacao implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idacompanhante")
	private Integer idacompanhante;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idmarcacao")
	private Integer idmarcacao;

	public Integer getIdacompanhante() {
		return idacompanhante;
	}

	public void setIdacompanhante(Integer idacompanhante) {
		this.idacompanhante = idacompanhante;
	}

	public Integer getIdmarcacao() {
		return idmarcacao;
	}

	public void setIdmarcacao(Integer idmarcacao) {
		this.idmarcacao = idmarcacao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	


}
