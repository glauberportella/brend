package com.biavan.brend.model;

import com.biavan.brend.enums.StatusOS;

public class InformacaoStatusOrdemServico {

	private StatusOS statusOS;
	private Float totalOrdemServicos;
	private Float qtdeByStatus;
	private String cor;
	private String destaque;
	
	public StatusOS getStatusOS() {
		return statusOS;
	}
	
	public void setStatusOS(StatusOS statusOS) {
		this.statusOS = statusOS;
	}
	
	public Float getTotalOrdemServicos() {
		return totalOrdemServicos;
	}
	
	public void setTotalOrdemServicos(Float totalOrdemServicos) {
		this.totalOrdemServicos = totalOrdemServicos;
	}
	
	public Float getQtdeByStatus() {
		return qtdeByStatus;
	}
	
	public void setQtdeByStatus(Float qtdeByStatus) {
		this.qtdeByStatus = qtdeByStatus;
	}
	
	public Float getPercentual() {
		return (qtdeByStatus * 100) / totalOrdemServicos;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getDestaque() {
		return destaque;
	}

	public void setDestaque(String destaque) {
		this.destaque = destaque;
	}
	
}
