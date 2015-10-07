package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.Funcionario;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface FuncionarioDAO {
	
	public void insere(Funcionario funcionario);

	public void atualiza(Funcionario funcionario);

	public BootgridData<Funcionario> listaToBootgrid(BootgridParam param);
	
	public List<Funcionario> lista();
	
	public List<Funcionario> listaMecanicos();

	public Funcionario getById(long id);
	
	public Funcionario getByLogin(String login);

	public void remove(long id);
}
