package com.biavan.brend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.biavan.brend.plugin.mail.Mailable;

@Entity
@Table(name="config_empresa")
public class ConfigEmpresa implements Mailable {

	@Id
	private long id;
	
	@Column(name = "nome_fantasia")
	private String nomeFantasia;
	
	@Column(name = "razao_social")
	private String razaoSocial;
	
	private String cnpj;
	
	private String endereco;
	
	private String telefone;
	
	private float markup;
	
	private String email;
	
	@Column(name = "smtp_host")
	private String smtpHost;
	
	@Column(name = "smtp_port")
	private String smtpPort;
	
	@Column(name = "smtp_login")
	private String smtpLogin;
	
	@Column(name = "smtp_senha")
	private String smtpSenha;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public float getMarkup() {
		return markup;
	}
	public void setMarkup(float markup) {
		this.markup = markup;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public String getSmtpLogin() {
		return smtpLogin;
	}
	public void setSmtpLogin(String smtpLogin) {
		this.smtpLogin = smtpLogin;
	}
	public String getSmtpSenha() {
		return smtpSenha;
	}
	public void setSmtpSenha(String smtpSenha) {
		this.smtpSenha = smtpSenha;
	}
	public String getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}
	@Override
	public String toString() {
		return "Dados da Empresa: [nomeFantasia=" + nomeFantasia + ", razaoSocial="
				+ razaoSocial + ", cnpj=" + cnpj + ", endereco=" + endereco
				+ ", telefone=" + telefone + "]";
	}
	
	
}
