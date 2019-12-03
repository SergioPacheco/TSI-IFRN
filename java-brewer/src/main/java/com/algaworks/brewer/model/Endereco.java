package com.algaworks.brewer.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
//import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;

	//@NotBlank(message = "Logradouro é obrigatório")
	private String logradouro;
	
	@NotBlank(message = "Número é obrigatório")
	private String numero;
	
	private String complemento;
	
	@NotBlank(message = "CEP é obrigatório")
	private String cep;
	
	@ManyToOne //(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_cidade")
	private Cidade cidade;
	
	@Transient
	private Estado estado;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	//Refatorado getEstado para o caso de cidade estiver null
	public Estado getEstado() {
	    if (this.cidade != null) {
		return this.cidade.getEstado();
	    }
			
	    return null;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public String getNomeCidadeSiglaEstado(){
		if(this.cidade != null){
			return this.cidade.getNome()+"/"+this.cidade.getEstado().getSigla();
		}
		return null;
	}
	
}