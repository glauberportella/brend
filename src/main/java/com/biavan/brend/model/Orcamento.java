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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.biavan.brend.util.Formatos;

@Entity
@Table(name="orcamento")
public class Orcamento implements Serializable {

	private static final long serialVersionUID = -4811070156877936179L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull (message = "Data invalida.")
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	private Date data;

	@NotNull (message = "Data invalida.")
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	private Date validade;
	
	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn(name = "veiculo_id")
	private Veiculo veiculo;
	
	@Transient
	private String nomeCliente;
	
	@Transient
	private String modeloVeiculo;

	private int ano;
	
	@ManyToOne
	@JoinColumn(name = "tipo_motor_id")
	private TipoMotor tipoMotor; 
	
	@Column(name = "valor_total")
	private double valorTotal;

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

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
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

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	@Transient
	public String getValorTotalFormatado() {
		return String.valueOf(Formatos.currentFormat(valorTotal));
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
	
	public String getModeloVeiculo() {
		return modeloVeiculo;
	}

	public void setModeloVeiculo() {
		this.modeloVeiculo = veiculo.getModelo();
	}
	
	public String getVeiculoNome() {
		return (veiculo != null) ? veiculo.getModelo() : null;	
	}
	
	@Transient
	public String getDataFormatado() {
		return (data != null) ? Formatos.getFormatoDeData().format(data) : null;	
	}
	
	@Transient
	public String getValidadeFormatado() {
		return (validade != null) ? Formatos.getFormatoDeData().format(validade) : null;	
	}

	@Override
	public String toString() {
		return "Orcamento [id=" + id + ", data=" + data + ", validade="
				+ validade + ", cliente=" + cliente +", veiculo=" + veiculo + ", ano=" + ano + ", tipoMotor=" + tipoMotor
				+ ", valorTotal=" + valorTotal + "]";
	}	
}	