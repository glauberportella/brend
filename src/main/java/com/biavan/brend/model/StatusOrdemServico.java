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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.biavan.brend.enums.StatusOS;
import com.biavan.brend.util.Formatos;

@Entity
@Table(name="status_ordem_servico")
public class StatusOrdemServico implements Serializable, Comparable<StatusOrdemServico> {

	private static final long serialVersionUID = 199506045948702864L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "ordem_servico_id", updatable = false)
	private OrdemServico ordemServico;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicio")
	private Date dataInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fim")
	private Date dataFim;
	
	private String descricao;
	
	@Column(name = "status_os")
	private StatusOS statusOS;

	@Transient
	public String getDataInicioFormatada() {
		return (dataInicio != null) ? Formatos.getFormatoDeDataHora().format(dataInicio) : null;
	}
	
	@Transient
	public String getDataFimFormatada() {
		return (dataFim != null) ? Formatos.getFormatoDeDataHora().format(dataFim) : null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusOS getStatusOS() {
		return statusOS;
	}

	public void setStatusOS(StatusOS statusOS) {
		this.statusOS = statusOS;
	}

	@Override
	public int compareTo(StatusOrdemServico obj) {
		return dataFim.compareTo(obj.getDataFim());
	}

	@Override
	public String toString() {
		return statusOS + " - " + dataInicio;
	}

	@Transient
	public boolean getAccess() {
		
		if(StatusOS.PENDENTE == getStatusOS()) {
			return true;
		}
		
		if(StatusOS.EM_ANDAMENTO == getStatusOS()) {
			return true;
		}
		
		if(StatusOS.CANCELADA == getStatusOS()) {
			return false;
		}
		
		if(StatusOS.FINALIZADA == getStatusOS()) {
			return false;
		}
		
		if(StatusOS.REABERTA == getStatusOS()) {
			return true;
		}
		
		return false;
	}
}
