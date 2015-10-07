package com.biavan.brend.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "peca_orcamento")
public class PecaOrcamento implements Serializable {

	private static final long serialVersionUID = -1307845201373710653L;

	@Id
	@ManyToOne
	@JoinColumn(name = "orcamento_id")
	private Orcamento orcamento;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "peca_id")
	private Peca peca;
	
	private double valor;
	
	private int qtde;

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
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
		return "PecaOrcamento [orcamento=" + orcamento + ", peca=" + peca
				+ ", valor=" + valor + ", qtde=" + qtde + "]";
	}
	
	
}
