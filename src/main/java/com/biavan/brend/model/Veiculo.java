package com.biavan.brend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="veiculo")
public class Veiculo implements Serializable {
	
	@Transient
	private static final long serialVersionUID = -3122150974028195218L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message = "Informe o modelo do carro")
	@Size(min=3, message="O campo não permiti o nome menor que 3 caracteres.")
	private String modelo;
	
	@NotEmpty(message = "Informe a marca do carro")
	@Size(min=3, message="O campo não permiti o nome menor que 3 caracteres.")
	private String marca;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", modelo=" + modelo + ", marca=" + marca
				+ "]";
	}
	
}
	
	