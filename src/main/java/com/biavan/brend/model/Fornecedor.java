package com.biavan.brend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="fornecedor")
public class Fornecedor implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 943857190320244016L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message = "Informe a razão")
	@Size(min=5, message="O campo não permiti a razão menor que 5 caracteres.")
	private String razao;
	
	@NotEmpty(message = "Informe a fantasia")
	@Size(min=5, message="O campo não permiti o nome menor que 5 caracteres.")
	private String fantasia;
	
	@NotNull
	@Pattern(regexp="([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\//]?[0-9]{4}[-]?[0-9]{2})", message="CNPJ Inválido" )
	private String cnpj;
	
	@Size(min=9, message="o campo não permiti o endereco menor que 9 caracteres")
	private String endereco;

	@Pattern(regexp ="\\(?\\b([0-9]{2})\\)?[-. ]?([0-9]{5})[-. ]?([0-9]{4})\\b", message="Telefone em formato incorreto")
	private String telefone;
	
	@NotNull
	@Pattern(regexp = "^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$", message="E-mail com formato incorreto.")
	private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Fornecedor [id=" + id + ", razao=" + razao + ", fantasia="
				+ fantasia + ", cnpj=" + cnpj + ", endereco=" + endereco
				+ ", telefone=" + telefone + ", email=" + email + "]";
	}
}
