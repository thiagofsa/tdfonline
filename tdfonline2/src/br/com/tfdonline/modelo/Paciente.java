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
	private Date datanascimento;
	private String cartaosus;
	private String rg;
	private String complemento;
	private String bairro;
	private String numero;
	private String logradouro;
	private String observacoes;
	private String tiposanguineo;
	
	private String agencia;
	private String banco;
	private String conta;
	private Date datavalidadebeneficio;
	private Integer ativo;
	
	
	public Paciente () {
		
		
	}
	
	 	
	
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
	
	
	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}
	public Date getDatanascimento() {
		return datanascimento;
	}
	
	
	public boolean isNew() {
		if ((id==null)|| (id<0))
			return true;
		else
			return false;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public Date getDatavalidadebeneficio() {
		return datavalidadebeneficio;
	}
	public void setDatavalidadebeneficio(Date datavalidadebeneficio) {
		this.datavalidadebeneficio = datavalidadebeneficio;
	}
	public Integer getAtivo() {
		return ativo;
	}
	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}
	
	


}
