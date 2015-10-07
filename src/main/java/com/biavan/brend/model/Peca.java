package com.biavan.brend.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name="peca")
public class Peca implements Serializable {

	@Transient
	private static final long serialVersionUID = -7856068195691644980L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message = "Informe o nome da peça")
	@Size(min=4, message="O campo não permiti o nome menor que 4 caracteres.")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "marca_id")
	private Marca marca;
	
	@NotEmpty(message = "Informe a descrição")
	@Size(min=4, message="O campo não permiti o nome menor que 4 caracteres.")
	private String descricao;
	
	@NotNull
	private int quantidade;
	
	@Transient
	@NumberFormat(style = Style.CURRENCY)
	private double preco; 
	
	@Transient
	@NumberFormat(style = Style.CURRENCY)
	private double precoVenda;
	
	@OneToMany(mappedBy = "peca", fetch = FetchType.EAGER)
	private List<TarifarioPeca> precos;
	
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
	
	public Marca getMarca() {
		return marca;
	}
	
	public String getNomeMarca() {
		return marca.getNome();
	}
	
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public void setPreco() {
		if (precos != null && precos.size() > 0) {
			TarifarioPeca tarifarioPeca = Collections.max(precos);
			preco = tarifarioPeca.getValor();
			precoVenda = tarifarioPeca.getValorVenda();
		} else  {
			precoVenda = preco = 0.0;
		}
	}
	
	public double getPrecoVenda() {
		return precoVenda;
	}
	
	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}
	
	public void setPrecoVenda() {
		setPreco();
	}
	
	public List<TarifarioPeca> getPrecos() {
		return precos;
	}

	public void setPrecos(List<TarifarioPeca> precos) {
		this.precos = precos;
	}
	
	@Override
	public String toString() {
		return "Peça [id=" + id + ", nome=" + nome + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Peca other = (Peca) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
