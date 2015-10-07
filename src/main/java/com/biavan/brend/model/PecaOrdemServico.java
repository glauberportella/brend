package com.biavan.brend.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "peca_ordem_servico")
public class PecaOrdemServico implements Serializable {

	private static final long serialVersionUID = -1307845201373710653L;

	@Id
	@ManyToOne
	@JoinColumn(name = "ordem_servico_id")
	private OrdemServico ordemServico;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "peca_id")
	private Peca peca;
	
	private double valor;
	
	private int qtde;

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
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
		return "PecaOrdemServico [ordemServico=" + ordemServico + ", peca=" + peca
				+ ", valor=" + valor + ", qtde=" + qtde + "]";
	}
	
	
}
