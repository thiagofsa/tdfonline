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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
//@Table(name = "paciente" , schema = "tfdonline")
@Table(name = "beneficioavulso")
public class BeneficioAvulso implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	private float valor;
	
	private float vagas;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataviagemida;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataviagemvolta;
	
	private String caminhoarquivo;
	
	private String observacao;
	
	@OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "idpaciente")
	private Paciente paciente;
	
	@OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "idunidadesaude")
	private UnidadeSaude unidadesaude;
	
	@OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "idprocedimento")
	private Procedimento procedimento;
	
	
	public BeneficioAvulso() {
		
		this.vagas=1;
	}
	
	
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


	public UnidadeSaude getUnidadesaude() {
		return unidadesaude;
	}


	public void setUnidadesaude(UnidadeSaude unidadesaude) {
		this.unidadesaude = unidadesaude;
	}


	public Procedimento getProcedimento() {
		return procedimento;
	}


	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}


	


	public float getVagas() {
		return vagas;
	}


	public void setVagas(float vagas) {
		this.vagas = vagas;
	}

	 @ManyToMany(cascade = { 
			 CascadeType.PERSIST,
				CascadeType.MERGE,  
		    })
	@JoinTable(name = "acompanhantepacientebeneficioavulso",
	   		 joinColumns = { @JoinColumn(name = "idbeneficioavulso") }, 
	   	     inverseJoinColumns = { @JoinColumn(name = "idacompanhante") }
		    )
		private List<Acompanhante> acompanhantespacientebeneficioavulso = new ArrayList<Acompanhante>();



	public List<Acompanhante> getAcompanhantespacientebeneficioavulso() {
			return acompanhantespacientebeneficioavulso;
		}


		public void setAcompanhantespacientebeneficioavulso(List<Acompanhante> acompanhantespacientebeneficioavulso) {
			this.acompanhantespacientebeneficioavulso = acompanhantespacientebeneficioavulso;
		}	





}


