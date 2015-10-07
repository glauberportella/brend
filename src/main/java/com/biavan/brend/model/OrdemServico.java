package com.biavan.brend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.biavan.brend.util.Formatos;

@Entity
@Table(name="ordem_servico")
public class OrdemServico implements Serializable {

	private static final long serialVersionUID = -4811070156877936179L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private Date data;
	
	private Date prazo;
	
	@ManyToOne
	@JoinColumn(name = "veiculo_id")
	private Veiculo veiculo;
	
	@OneToOne
	@JoinColumn(name = "orcamento_id")
	private Orcamento orcamento;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "ordem_servico_id", updatable = false)
	private OrdemServico ordemServicoPai;
	
	@ManyToOne
	@JoinColumn(name = "mecanico_id")
	private Funcionario mecanico;
	
	@Transient
	private String modeloVeiculo;
	
	@Transient
	private String nomeCliente;

	private int ano;
	
	@ManyToOne
	@JoinColumn(name = "tipo_motor_id")
	private TipoMotor tipoMotor; 
	
	@Column(name = "valor_servico")
	private double valorServico;
	
	@Column(name = "valor_peca")
	private double valorPeca;

	@Column(name = "valor_total_adicionais")
	private double valorTotalAdicionais;
	
	@Column(name = "valor_sub_total")
	private double valorSubTotal;
	
	@Column(name = "valor_total")
	private double valorTotal;
	
	@OneToMany(mappedBy = "ordemServico")
	private List<StatusOrdemServico> historico = new ArrayList<StatusOrdemServico>();
	
	@Transient
	private StatusOrdemServico statusAtual;

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

	public Date getPrazo() {
		return prazo;
	}

	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public OrdemServico getOrdemServicoPai() {
		return ordemServicoPai;
	}

	public void setOrdemServicoPai(OrdemServico ordemServicoPai) {
		this.ordemServicoPai = ordemServicoPai;
	}

	public Funcionario getMecanico() {
		return mecanico;
	}

	public void setMecanico(Funcionario mecanico) {
		this.mecanico = mecanico;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public TipoMotor getTipoMotor() {
		return tipoMotor;
	}

	public void setTipoMotor(TipoMotor tipoMotor) {
		this.tipoMotor = tipoMotor;
	}

	public double getValorServico() {
		return valorServico;
	}
	
	@Transient
	public String getValorServicoFormatado() {
		return Formatos.currentFormat(valorServico);
	}

	public void setValorServico(double valorServico) {
		this.valorServico = valorServico;
	}

	public double getValorPeca() {
		return valorPeca;
	}

	@Transient
	public String getValorPecaFormatado() {
		return Formatos.currentFormat(valorPeca);
	}
	
	public void setValorPeca(double valorPeca) {
		this.valorPeca = valorPeca;
	}

	public double getValorTotalAdicionais() {
		return valorTotalAdicionais;
	}
	
	@Transient
	public String getValorTotalAdicionaisFormatado() {
		return Formatos.currentFormat(valorTotalAdicionais);
	}

	public void setValorTotalAdicionais(double valorTotalAdicionais) {
		this.valorTotalAdicionais = valorTotalAdicionais;
	}

	public double getValorSubTotal() {
		return valorSubTotal;
	}
	
	@Transient
	public String getValorSubTotalFormatado() {
		return Formatos.currentFormat(valorSubTotal);
	}

	public void setValorSubTotal(double valorSubTotal) {
		this.valorSubTotal = valorSubTotal;
	}

	public double getValorTotal() {
		return valorTotal;
	}
	
	@Transient
	public String getValorTotalFormatado() {
		return Formatos.currentFormat(valorTotal);
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public String getNomeVeiculo() {
		return modeloVeiculo;
	}

	public void setModeloVeiculo() {
		this.modeloVeiculo = veiculo.getModelo();
	}
	
	public String getVeiculoNome() {
		return (veiculo != null) ? veiculo.getModelo() : null;	
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente() {
		this.nomeCliente = cliente.getRazao();
	}
	
	public String getClienteNome() {
		return (cliente != null) ? cliente.getRazao() : null;	
	}
	
	@Transient
	public String getDataFormatado() {
		return (data != null) ? Formatos.getFormatoDeData().format(data) : null;	
	}
	
	@Transient
	public String getPrazoFormatado() {
		return (prazo != null) ? Formatos.getFormatoDeData().format(prazo) : null;	
	}
	
	@Override
	public String toString() {
		return "Ordem de Serviço [id=" + id + ", data=" + data + ", prazo="
				+ prazo + ", veiculo=" + veiculo 
				+ ", ano=" + ano + ", tipoMotor=" + tipoMotor
				+ ", valorServico=" + valorServico + ", valorPeca=" + valorPeca
				+ ", valorTotal=" + valorTotal
				+ ", orcamento=" + orcamento +"]";
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	
	public void setHistorico(List<StatusOrdemServico> historico) {
		this.historico = historico;
		for(StatusOrdemServico sos : historico) {
			sos.setOrdemServico(null);
		}
		statusAtual = Collections.max(historico);
	}
	
	public StatusOrdemServico getStatusAtual() {
		return statusAtual;
	}
	
	@Transient
	public String getStatusAtualDescricao() {
		if (statusAtual.getStatusOS() != null)
			return statusAtual.getStatusOS().toString();
		else
			return "";
	}
	
}	