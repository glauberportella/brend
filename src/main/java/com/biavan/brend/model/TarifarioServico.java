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

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.biavan.brend.util.Formatos;

@Entity
@Table(name="tarifario_servico")
public class TarifarioServico implements Serializable, Comparable<TarifarioServico>  {

	private static final long serialVersionUID = -7388014286929793891L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "servico_id")
	private Servico servico;
	
	private Date data;
	
	private double valor;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
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

	@Override
	public String toString() {
		return "TarifarioPeça [id=" + id + ", servico=" + servico.getNome() + ", data=" + data
				+ ", valor=" + valor + "]";
	}
	
	@Override
	public int compareTo(TarifarioServico tarifarioServico) {
		return data.compareTo(tarifarioServico.getData());
	}
	
}
