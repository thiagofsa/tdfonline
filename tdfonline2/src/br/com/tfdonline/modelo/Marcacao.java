package br.com.tfdonline.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	
	private String horaprocedimento;
	
	private String localacolhimento;
	
	private String observacao;
	
	private Integer encaminhada;
	
	private Integer confirmada;
	
	private Integer vagas;
	
	
	
	private int ida;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dataviagem")
	private Date dataviagem;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	
	@OneToOne(cascade = { 
	         CascadeType.MERGE
	    }) 
    @JoinColumn(name = "idpaciente")
	Paciente paciente;
	
	@OneToOne(cascade = { 
	         CascadeType.MERGE
	    }) 
    @JoinColumn(name = "idprocedimento")
	Procedimento procedimento;
	
	@OneToOne (cascade = { 
	         CascadeType.MERGE
	    }) 
    @JoinColumn(name = "idunidadesaude")
	UnidadeSaude unidadesaude;
	
	 @ManyToMany(cascade = { 
			 CascadeType.PERSIST,
				CascadeType.MERGE,  
		    })
	 @JoinTable(name = "acompanhantepacientemarcacao",
	    		 joinColumns = { @JoinColumn(name = "idmarcacao") }, 
	    	     inverseJoinColumns = { @JoinColumn(name = "idacompanhante") }
		    )
	 private List<Acompanhante> acompanhantespacientemarcacao = new ArrayList<Acompanhante>();
	 
	@Transient
	 private List<String> acompanhantespacientesmarcacaostring = new ArrayList<String>();
	 
	
	public Marcacao() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocalacolhimento() {
		return localacolhimento;
	}

	public void setLocalacolhimento(String localacolhimento) {
		this.localacolhimento = localacolhimento;
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

	

	public String getHoraprocedimento() {
		return horaprocedimento;
	}

	public void setHoraprocedimento(String horaprocedimento) {
		this.horaprocedimento = horaprocedimento;
	}

	public Date getDataviagem() {
		return dataviagem;
	}

	public void setDataviagem(Date dataviagem) {
		this.dataviagem = dataviagem;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getEncaminhada() {
		return encaminhada;
	}

	public void setEncaminhada(int encaminhada) {
		this.encaminhada = encaminhada;
	}

	public int getIda() {
		return ida;
	}

	public void setIda(int ida) {
		this.ida = ida;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getConfirmada() {
		return confirmada;
	}

	public void setConfirmada(Integer confirmada) {
		this.confirmada = confirmada;
	}

	public void setEncaminhada(Integer encaminhada) {
		this.encaminhada = encaminhada;
	}

	public Integer getVagas() {
		return vagas;
	}

	public void setVagas(Integer vagas) {
		this.vagas = vagas;
	}
	
	
}
