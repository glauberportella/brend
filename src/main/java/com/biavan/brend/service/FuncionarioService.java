package com.biavan.brend.service;

import java.util.List;
import java.util.Map;

import com.biavan.brend.model.Funcionario;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface FuncionarioService {

	public void insereFuncionario(Funcionario funcionario);

	public void atualizaFuncionario(Funcionario funcionario);

	public List<Funcionario> listaFuncionarios();
	
	public Map<Long, String> listaFuncionariosForDropDown();
	
	public Map<Long, String> listaMecanicosForDropDown();
	
	public <E> BootgridData<E> listaFuncionariosBootgrid(BootgridParam query);

	public Funcionario getFuncionarioById(long id);
	
	public Funcionario getFuncionarioByLogin(String login);

	public void removeFuncionario(long id);
	
}
