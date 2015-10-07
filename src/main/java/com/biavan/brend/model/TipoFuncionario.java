package com.biavan.brend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tipo_funcionario")
public class TipoFuncionario implements Serializable {

	private static final long serialVersionUID = 943857190320244016L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
    
	@NotEmpty(message = "Informe o tipo do funcionário")
	@Size(min=2, message="O campo não permite o nome menor que 2 caracteres.")
	private String nome;
	
	private boolean mecanico;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isMecanico() {
		return mecanico;
	}

	public void setMecanico(boolean mecanico) {
		this.mecanico = mecanico;
	}

	@Override
	public String toString() {
		return "TipoFuncionario [id=" + id + ", nome=" + nome + "]";
	}
	
	
}
