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
@Table(name = "acompanhantepacientebeneficioavulso",schema = "tfdonline")
public class Acompanhantepacientebeneficioavulso implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idacompanhante")
	private Integer idacompanhante;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idbeneficioavulso")
	private Integer idbeneficioavulso;

	public Integer getIdacompanhante() {
		return idacompanhante;
	}

	public void setIdacompanhante(Integer idacompanhante) {
		this.idacompanhante = idacompanhante;
	}

	public Integer getIdbeneficioavulso() {
		return idbeneficioavulso;
	}

	public void setIdbeneficioavulso(Integer idbeneficioavulso) {
		this.idbeneficioavulso = idbeneficioavulso;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
