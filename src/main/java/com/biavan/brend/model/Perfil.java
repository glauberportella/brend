package com.biavan.brend.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "perfil")
public class Perfil implements Serializable {

	private static final long serialVersionUID = -8309675734361935711L;

	@Id
	@GeneratedValue
	private long id;

	private String nome;

	@ManyToMany
	@JoinTable(name = "recurso_perfil", 
		joinColumns = { @JoinColumn(name = "perfil_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "recurso_id") })
	private Set<Recurso> recursosPerfil;

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

	public Set<Recurso> getRecursosPerfil() {
		return recursosPerfil;
	}

	public void setRecursosPerfil(Set<Recurso> recursosPerfil) {
		this.recursosPerfil = recursosPerfil;
	}
	
	public void addRecurso(Recurso recurso) {
		if (recursosPerfil == null) {
			recursosPerfil = new HashSet<Recurso>();
		}
		this.recursosPerfil.add(recurso);
	}

	@Override
	public String toString() {
		return "Perfil [id=" + id + ", nome=" + nome + "]";
	}

}
