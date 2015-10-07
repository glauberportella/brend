package com.biavan.brend.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "servico_orcamento")
public class ServicoOrcamento implements Serializable {

	private static final long serialVersionUID = -1307845201373710653L;

	@Id
	@ManyToOne
	@JoinColumn(name = "orcamento_id")
	private Orcamento orcamento;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "servico_id")
	private Servico servico;
	
	private double valor;
	
	private int qtde;

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getQtde() {
		return qtde;
	}

	public void setQtde(int qtde) {
		this.qtde = qtde;
	}
	
	@Transient
	public double getSubTotal() {
		return qtde * valor;
	}

	@Override
	public String toString() {
		return "ServicoOrcamento [orcamento=" + orcamento + ", servico="
				+ servico + ", valor=" + valor + ", qtde=" + qtde + "]";
	}
	
	
}
