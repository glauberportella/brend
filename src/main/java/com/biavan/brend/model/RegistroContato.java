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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.biavan.brend.util.Formatos;

@Entity
@Table(name = "registro_contato")
public class RegistroContato implements Serializable {

	private static final long serialVersionUID = -2450997974775429362L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "ordem_servico_id", updatable = false)
	private OrdemServico ordemServico;
	
	private boolean vistoria;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}
	
	@Transient
	public String getDataFormatada() {
		return (data != null) ? Formatos.getFormatoDeData().format(data) : null;	
	}

	public void setData(Date data) {
		this.data = data;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public boolean isVistoria() {
		return vistoria;
	}
	
	public String getVistoriaFormatada() {
		return (vistoria) ? "Vistoria realizada." : "";
	}

	public void setVistoria(boolean vistoria) {
		this.vistoria = vistoria;
	}
	
}
