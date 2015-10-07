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

import org.springframework.format.annotation.DateTimeFormat;

import com.biavan.brend.util.Formatos;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable{

	private static final long serialVersionUID = 4702865908308270332L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull (message = "A data inválida.")
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	private Date data;
	
	@NotNull (message = "A data inválida.")
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	@Column(name = "data_compra")
	private Date dataCompra;
	
	@Column(name = "nota_fiscal")
	private String notaFiscal;
	
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	@Transient
	public String getDataFormatada() {
		return (data != null) ? Formatos.getFormatoDeData().format(data) : "";	
	}

	public Date getDataCompra() {
		return dataCompra;
	}
	
	@Transient
	public String getDataCompraFormatada() {
		return (dataCompra != null) ? Formatos.getFormatoDeData().format(dataCompra) : "";	
	}	

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public String getNotaFiscal() {
		return (notaFiscal != null) ? notaFiscal : "";
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	@Transient
	public String getRazaoFornecedor() {
		return fornecedor.getRazao();
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", data=" + data + ", dataCompra="
				+ dataCompra + "]";
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
		Pedido other = (Pedido) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
