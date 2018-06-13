package br.com.tfdonline.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "marcacao")
public class Marcacao implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	private Integer status;
	private String hora;
	private String localacolhimento;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dataviagem")
	private Date data;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datamarcacao;
	

	@OneToOne
    @JoinColumn(name = "idpaciente")
	Paciente paciente;
	
	@OneToOne
    @JoinColumn(name = "idprocedimento")
	Procedimento procedimento;
	
	@OneToOne
    @JoinColumn(name = "idunidadesaude")
	UnidadeSaude unidadesaude;
	
	 @ManyToMany(cascade = { 
			 CascadeType.PERSIST,
				CascadeType.MERGE 
		    })
	 @JoinTable(name = "acompanhantepacientemarcacao",
	    		 joinColumns = { @JoinColumn(name = "idmarcacao") }, 
	    	     inverseJoinColumns = { @JoinColumn(name = "idacompanhante") }
		    )
	 private List<Acompanhante> acompanhantespacientemarcacao = new ArrayList<Acompanhante>();
	 
	@Transient
	 private List<String> acompanhantespacientesmarcacaostring = new ArrayList<String>();
	 
	 
	
	public Marcacao() {
		this.setId(-1);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getLocalacolhimento() {
		return localacolhimento;
	}

	public void setLocalacolhimento(String localacolhimento) {
		this.localacolhimento = localacolhimento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public UnidadeSaude getUnidadesaude() {
		return unidadesaude;
	}

	public void setUnidadesaude(UnidadeSaude unidadesaude) {
		this.unidadesaude = unidadesaude;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public boolean isNew() {
		if ((id==null)|| (id<0))
			return true;
		else
			return false;
	}

	public List<Acompanhante> getAcompanhantespacientemarcacao() {
		return acompanhantespacientemarcacao;
	}

	public void setAcompanhantespacientemarcacao(List<Acompanhante> acompanhantespacientemarcacao) {
		this.acompanhantespacientemarcacao = acompanhantespacientemarcacao;
	}

	public List<String> getAcompanhantespacientesmarcacaostring() {
		return acompanhantespacientesmarcacaostring;
	}

	public void setAcompanhantespacientesmarcacaostring(List<String> acompanhantespacientesmarcacaostring) {
		this.acompanhantespacientesmarcacaostring = acompanhantespacientesmarcacaostring;
	}

	public Date getDatamarcacao() {
		return datamarcacao;
	}

	public void setDatamarcacao(Date datamarcacao) {
		this.datamarcacao = datamarcacao;
	}
	

}
