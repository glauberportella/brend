package com.biavan.brend.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "recurso")
public class Recurso implements Serializable{

	private static final long serialVersionUID = -1353156850342751256L;
	
	@Id
	@GeneratedValue
	private long id;
	
	private String nome;
	
	private String descricao;
	
	private String role;
	
	@ManyToOne
	@JoinColumn(name = "recurso_id")
	private Recurso recurso;

	@ManyToMany(mappedBy = "recursosPerfil")
	private Set<Perfil> perfis;
	
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	@Override
	public String toString() {
		return "Recurso [id=" + id + ", nome=" + nome + ", descricao="
				+ descricao + ", role=" + role + "]";
	}

}
