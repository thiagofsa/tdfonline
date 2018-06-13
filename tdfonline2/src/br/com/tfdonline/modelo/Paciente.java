package br.com.tfdonline.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
//@Table(name = "paciente" , schema = "tfdonline")
@Table(name = "paciente")
public class Paciente implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	private String datanascimento;
	private String cartaosus;
	private String rg;
	private String complemento;
	private String bairro;
	private String numero;
	private String logradouro;
	private String observacoes;
	private String tiposanguineo;
	private String diabetico;
	
	public Paciente () {
		
		
	}
	
	 @ManyToMany(cascade = { 
		        CascadeType.PERSIST, 
		        CascadeType.MERGE
		    })
	 
	 @JoinTable(name = "beneficiopaciente",
	    		 joinColumns = { @JoinColumn(name = "idpaciente") }, 
	    	     inverseJoinColumns = { @JoinColumn(name = "idbeneficio") }
		    )
	 
	private List<Beneficio> beneficios = new ArrayList<Beneficio>();
	
	
	 @ManyToMany(cascade = { 
		        CascadeType.PERSIST, 
		        CascadeType.MERGE
		    }, fetch = FetchType.EAGER)
	 @JoinTable(name = "acompanhantepaciente",
	    		 joinColumns = { @JoinColumn(name = "idpaciente") }, 
	    	     inverseJoinColumns = { @JoinColumn(name = "idacompanhante") }
		    )
	 
	private List<Acompanhante> acompanhantes = new ArrayList<Acompanhante>();
	 
	 
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getDatanascimento() {
		return datanascimento;
	}
	public void setDatanascimento(String datanascimento) {
		this.datanascimento = datanascimento;
	}
	public String getCartaosus() {
		return cartaosus;
	}
	public void setCartaosus(String cartaosus) {
		this.cartaosus = cartaosus;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTiposanguineo() {
		return tiposanguineo;
	}
	public void setTiposanguineo(String tiposanguineo) {
		this.tiposanguineo = tiposanguineo;
	}
	
	
	public List<Acompanhante> getAcompanhantes() {
		return acompanhantes;
	}
	public void setAcompanhantes(List<Acompanhante> acompanhantes) {
		this.acompanhantes = acompanhantes;
	}
	public List<Beneficio> getBeneficios() {
		return beneficios;
	}
	public void setBeneficios(List<Beneficio> beneficios) {
		this.beneficios = beneficios;
	}
	public String getDiabetico() {
		return diabetico;
	}
	public void setDiabetico(String diabetico) {
		this.diabetico = diabetico;
	}
	
	
	
	


}
