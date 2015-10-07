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

import com.biavan.brend.util.Formatos;

@Entity
@Table(name="tarifario_peca")
public class TarifarioPeca implements Serializable, Comparable<TarifarioPeca> {

	private static final long serialVersionUID = -7388014286929793891L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "peca_id")
	private Peca peca;
	
	private Date data;

	private double valor;
	
	private double markup;
	
	@Column(name = "valor_venda")
	private double valorVenda;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Transient
	public String getDataFormatada() {
		return (data != null) ? Formatos.getFormatoDeData().format(data) : null;
	}
	
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getMarkup() {
		return markup;
	}

	public void setMarkup(double markup) {
		this.markup = markup;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	@Override
	public String toString() {
		return "TarifarioPeça [id=" + id + ", peca=" + peca.getNome() + ", data=" + data
				+ ", valorVenda=" + valorVenda + "]";
	}

	@Override
	public int compareTo(TarifarioPeca tarifarioPeca) {
		return data.compareTo(tarifarioPeca.getData());
	}
	

}
