package com.biavan.brend.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.FuncionarioDAO;
import com.biavan.brend.dao.UsuarioDAO;
import com.biavan.brend.model.Funcionario;
import com.biavan.brend.model.Usuario;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	private FuncionarioDAO funcionarioDAO;
	private UsuarioDAO usuarioDAO;
	
	public void setFuncionarioDAO(FuncionarioDAO funcionarioDAO) {
		this.funcionarioDAO = funcionarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	@Override
	@Transactional
	public void insereFuncionario(Funcionario funcionario) {
		funcionarioDAO.insere(funcionario);

	}

	@Override
	@Transactional
	public void atualizaFuncionario(Funcionario funcionario) {
		funcionarioDAO.atualiza(funcionario);

	}

	@Override
	@Transactional
	public List<Funcionario> listaFuncionarios() {
		return funcionarioDAO.lista();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<Funcionario> listaFuncionariosBootgrid(BootgridParam param) {
		BootgridData<Funcionario> bootgridData = funcionarioDAO.listaToBootgrid(param);
		
		for(Funcionario f : bootgridData.getRows()) {
			f.getTipoFuncionario();
		}
		
		return bootgridData;
	}
	
	
	@Override
	@Transactional
	public Funcionario getFuncionarioById(long id) {
		return funcionarioDAO.getById(id);
	}
	
	@Override
	@Transactional
	public Funcionario getFuncionarioByLogin(String login) {
		Funcionario funcionario = funcionarioDAO.getByLogin(login);
		if (funcionario != null)
			funcionario.getTipoFuncionario();
		return funcionario;
	}
	

	@Override
	@Transactional
	public void removeFuncionario(long id) {
		Usuario usuario = usuarioDAO.getByFuncionario(funcionarioDAO.getById(id));
		
		if (usuario != null) {
			usuarioDAO.remove(usuario.getLogin());
		}
		funcionarioDAO.remove(id);
	}
	
	@Override
	@Transactional
	public Map<Long, String> listaFuncionariosForDropDown() {
		Map<Long, String> listaFuncionarios = new LinkedHashMap<Long, String>();
		
		for (Funcionario f : this.funcionarioDAO.lista()) {
			listaFuncionarios.put(f.getId(), f.getNome());
		}
		return listaFuncionarios;
	}

	
	@Override
	@Transactional
	public Map<Long, String> listaMecanicosForDropDown() {
		Map<Long, String> listaFuncionarios = new LinkedHashMap<Long, String>();
		
		for (Funcionario f : this.funcionarioDAO.listaMecanicos()) {
			listaFuncionarios.put(f.getId(), f.getNome());
		}
		return listaFuncionarios;		
	}

}
