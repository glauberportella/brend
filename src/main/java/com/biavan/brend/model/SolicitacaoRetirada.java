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

import com.biavan.brend.enums.StatusSolicitacaoRetirada;
import com.biavan.brend.util.Formatos;

@Entity
@Table(name = "solicitacao_retirada")
public class SolicitacaoRetirada implements Serializable {

	private static final long serialVersionUID = 8564512289369442349L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "data_solicitacao")
	private Date dataSolicitacao;
	
	@Column(name = "data_atendimento")
	private Date dataAtendimento;
	
	@ManyToOne
	@JoinColumn(name = "solicitante_id")
	private Funcionario solicitante;
	
	@ManyToOne
	@JoinColumn(name = "autorizador_id")
	private Funcionario autorizador;
	
	@ManyToOne
	@JoinColumn(name = "ordem_servico_id")
	private OrdemServico ordemServico;
	
	private StatusSolicitacaoRetirada status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}
	
	@Transient
	public String getDataSolicitacaoFormatada() {
		return (dataSolicitacao != null) ? Formatos.getFormatoDeData().format(dataSolicitacao) : "";	
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}
	
	@Transient
	public String getDataAtendimentoFormatada() {
		return (dataAtendimento != null) ? Formatos.getFormatoDeData().format(dataAtendimento) : "";	
	}
	
	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public Funcionario getSolicitante() {
		return solicitante;
	}
	
	@Transient
	public String getNomeSolicitante() {
		return (solicitante != null) ? solicitante.getNome() : "";
	}

	public void setSolicitante(Funcionario solicitante) {
		this.solicitante = solicitante;
	}

	public Funcionario getAutorizador() {
		return autorizador;
	}
	
	@Transient
	public String getNomeAutorizador() {
		return (autorizador != null) ? autorizador.getNome() : "";
	}

	public void setAutorizador(Funcionario autorizador) {
		this.autorizador = autorizador;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	
	@Transient
	public String getOrdemServicoIdData() {
		return ordemServico.getId() + ": " + ordemServico.getDataFormatado();
	}

	public StatusSolicitacaoRetirada getStatus() {
		return status;
	}

	public void setStatus(StatusSolicitacaoRetirada status) {
		this.status = status;
	}
	
	
}
