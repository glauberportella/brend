package com.biavan.brend.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.PerfilDAO;
import com.biavan.brend.dao.UsuarioDAO;
import com.biavan.brend.model.Funcionario;
import com.biavan.brend.model.Recurso;
import com.biavan.brend.model.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	private UsuarioDAO usuarioDAO;
	
	private PerfilDAO perfilDAO;

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	public void setPerfilDAO(PerfilDAO perfilDAO) {
		this.perfilDAO = perfilDAO;
	}

	@Override
	@Transactional
	public void insereUsuario(Usuario usuario) {
		usuarioDAO.insere(encriptarSenha(usuario));

	}

	@Override
	@Transactional
	public void atualizaUsuario(Usuario usuario) {
		usuarioDAO.atualiza(usuario);
	}

	@Override
	@Transactional
	public void atualizaUsuario(Usuario usuario, String senha) {
		usuario.setSenha(senha);
		usuarioDAO.atualiza(encriptarSenha(usuario));

	}

	
	@Override
	@Transactional
	public List<Usuario> listaUsuarios() {
		return usuarioDAO.lista();
	}

	@Override
	@Transactional
	public Usuario getUsuarioByLogin(String login) {
		Usuario usuario = usuarioDAO.findByLogin(login);
		return usuario;
	}

	@Override
	@Transactional
	public void removeUsuario(String login) {
		this.usuarioDAO.remove(login);

	}

	@Override
	@Transactional
	public Usuario getUsuarioByFuncionario(Funcionario funcionario) {
		Usuario usuario = usuarioDAO.getByFuncionario(funcionario);
		return usuario;
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String login)
			throws UsernameNotFoundException {

		Usuario usuario = usuarioDAO.findByLogin(login);
		List<GrantedAuthority> authorities = buildUserAuthority(
				perfilDAO.getRecursosByPerfil(usuario.getPerfil()));

		return buildUserForAuthentication(usuario, authorities);
	}

	// Converte Usuario usuario em 
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(Usuario usuario,
			List<GrantedAuthority> authorities) {
		return new User(usuario.getLogin(), usuario.getSenha(),
				usuario.isAtivo(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<Recurso> recursos) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Constroi autorizações do usuário
		for (Recurso recurso : recursos) {
			setAuths.add(new SimpleGrantedAuthority(recurso.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
	
	private Usuario encriptarSenha(Usuario usuario) {
		String senha = usuario.getSenha();
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		usuario.setSenha(passwordEncoder.encode(senha)); 
		return usuario;
	}

}
