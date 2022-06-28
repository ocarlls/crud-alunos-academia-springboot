package com.crudalunosacademia.models;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Endereco {
	private String cidade;
	private String estado; 
	private String bairro;
	@Id
	private String rua;
	private String numero;
	private String cep;
	private String complemento;
		
	public Endereco() {
		super(); 
	}
	
	public Endereco(String cidade, String estado, String bairro, String rua, String numero, String cep,
			String complemento) {
		super();
		this.cidade = cidade;
		this.estado = estado;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}