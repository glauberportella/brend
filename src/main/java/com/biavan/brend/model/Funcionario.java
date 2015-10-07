package com.biavan.brend.model;

import java.io.Serializable;
import java.util.Date;

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

import org.hibernate.validator.constraints.Email;

import com.biavan.brend.util.Formatos;

@Entity
@Table(name="funcionario")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = -4811070156877936179L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "tipo_funcionario_id")
	private TipoFuncionario tipoFuncionario;
	
	@NotNull
	@Size(min=3, message="O campo não permiti o nome menor que 3 caracteres.")
	private String nome;
	
	@NotNull
	@Pattern(regexp="([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})", message="CPF Inválido" )
	private String cpf;
	
	@Past (message = "A data de nascimento deve ser anterior a data atual.") 
	@NotNull (message = "A data de nascimento deve ser preenchida.")
	private Date nascimento;
	
	@Size(min=9, message="o campo não permiti o endereco menor que 9 caracteres")
	private String endereco;
	
	@Pattern(regexp = "\\(?\\b([0-9]{2})\\)?[-. ]?([0-9]{5})[-. ]?([0-9]{4})\\b", message="Telefone em formato incorreto")
	private String telefone;
	
	@Email(message="Favor informar o e-mail correto.")
	private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TipoFuncionario getTipoFuncionario() {
		return tipoFuncionario;
	}
	
	public String getTipoFuncionarioNome() {
		return tipoFuncionario.getNome();
	}
	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
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

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	@Transient
	public String getNascimentoFormatado() {
		return (nascimento != null) ? Formatos.getFormatoDeData().format(nascimento) : null;	
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
		return "Funcionario [id=" + id + ", nome=" + nome + ", Tipo=" + tipoFuncionario.getNome() + "]";
	}
	
}
