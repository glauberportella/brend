package com.biavan.brend.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.biavan.brend.enums.TipoPessoa;
import com.biavan.brend.util.Formatos;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -4811070156877936179L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "tipo_pessoa")
	private TipoPessoa tipoPessoa; 
	
	@NotEmpty(message = "Informe a razão")
	@Size(min=5, message="O campo não permiti o nome menor que 5 caracteres.")
	private String razao;
	
	@NotEmpty(message = "Informe a fantasia")
	@Size(min=5, message="O campo não permiti o nome menor que 5 caracteres.")
	private String fantasia;
	
	@ManyToOne
	@JoinColumn(name = "tipo_documento_id") 
	private TipoDocumento tipoDocumento;  
	
	@Column(name = "numero_documento")
	@NotNull
	@Size(min=9, message="Favor verificar o valor informado.")
	private String numeroDocumento;
	
	@Past (message = "A data de nascimento deve ser anterior a data atual.") 
	@NotNull (message = "A data de nascimento deve ser preenchida.")
	private Date nascimento;
	
	@Size(min=9, message="o campo não permiti o endereco menor que 9 caracteres")
	private String endereco;
	
	@Pattern(regexp = "\\(?\\b([0-9]{2})\\)?[-. ]?([0-9]{5})[-. ]?([0-9]{4})\\b", message="Telefone em formato incorreto")
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

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
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

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}


	public Date getNascimento() {
		return nascimento;
	}

	
	@Transient
	public String getNascimentoFormatado() {
		return (nascimento != null) ? Formatos.getFormatoDeData().format(nascimento) : null;	
	}
	
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
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
		return "Cliente [id=" + id + ", tipoPessoa=" + tipoPessoa
				+ ", razao=" + razao + ", fantasia=" + fantasia
				+ ", tipoDocumento=" + tipoDocumento + ", numeroDocumento="
				+ numeroDocumento + ", nascimento=" + nascimento
				+ ", endereco=" + endereco + ", telefone=" + telefone
				+ ", email=" + email + "]";
	}
	
}	