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
@Table(name = "requisicao")
public class Requisicao implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@OneToOne
    @JoinColumn(name = "idpaciente")
	private Paciente paciente;
	
	@OneToOne
    @JoinColumn(name = "idprocedimento")
	private Procedimento procedimento;
	
	@OneToOne
    @JoinColumn(name = "idmedico")
	private Medico medico;
	
	
	@OneToOne
    @JoinColumn(name = "idmedicoautorizador")
	private Medico medicoautorizador;
	
	
	String caminhoarquivo;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date data;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public String getCaminhoarquivo() {
		return caminhoarquivo;
	}

	public void setCaminhoarquivo(String caminhoarquivo) {
		this.caminhoarquivo = caminhoarquivo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public Medico getMedicoautorizador() {
		return medicoautorizador;
	}

	public void setMedicoautorizador(Medico medicoautorizador) {
		this.medicoautorizador = medicoautorizador;
	}



}