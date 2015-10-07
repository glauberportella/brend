package com.biavan.brend.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name="servico")
public class Servico implements Serializable {

	private static final long serialVersionUID = 7482921059975917958L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message = "Informe o nome da peça")
	@Size(min=5, message="O campo não permiti o nome menor que 5 caracteres.")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "tipo_servico_id")
	private TipoServico tipoServico;
	
	@NotEmpty(message = "Informe a descrição")
	@Size(min=5, message="O campo não permiti o nome menor que 5 caracteres.")
	private String descricao;
	
	@Transient
	@NumberFormat(style = Style.CURRENCY)
	private double preco;
	
	@OneToMany(mappedBy = "servico", fetch = FetchType.EAGER)
	private List<TarifarioServico> precos;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public TipoServico getTipoServico() {
		return tipoServico;
	}
	
	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public void setPreco() {
		if (precos != null && precos.size() > 0) {
			TarifarioServico tarifarioServico = Collections.max(precos);
			preco = tarifarioServico.getValor();
		} else  {
			preco = 0.0;
		}
	}
	
	public List<TarifarioServico> getPrecos() {
		return precos;
	}

	public void setPrecos(List<TarifarioServico> precos) {
		this.precos = precos;
	}
	
	@Override
	public String toString() {
		return "Servico [id=" + id + ", nome=" + nome + "]";
	}
}
