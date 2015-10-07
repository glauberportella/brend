package com.biavan.brend.util;

import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.biavan.brend.model.ConfigEmpresa;
import com.biavan.brend.model.Perfil;
import com.biavan.brend.model.Recurso;
import com.biavan.brend.model.Usuario;

public class GerarBanco {

	public static void main(String[] args) {
		
		HibernateUtil.gerarBanco();
		
		criarEmpresa();
		System.out.println("Registro de configuração criado!");
		
		String login = "Admin";
		String senha = "123456";
		String perfil = "Tecnico";
		
		criarUsuario(login, senha, perfil);
		System.out.println("Criado o usuário: ");
		System.out.println("Login: " + login);
		System.out.println("Senha: " + senha);
		System.out.println("Perfil: " + perfil);

	}
	
	private static void criarEmpresa() {
		ConfigEmpresa config = new ConfigEmpresa();
		config.setId(1);
		config.setSmtpHost("smtp.gmail.com");
		config.setSmtpLogin("safectmtccsenac@gmail.com");
		config.setSmtpSenha("******"); 

		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.saveOrUpdate(config);
		session.getTransaction().commit();
	}

	private static void criarUsuario(String login, String senha, String papel) {

		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		Recurso recurso = new Recurso();
		recurso.setNome("Tudo");
		recurso.setDescricao("Acesso a todos os recursos");
		recurso.setRole("ROLE_TEC");
		session.saveOrUpdate(recurso);
		
		Perfil perfil = new Perfil();
		perfil.setNome("Técnico");
		perfil.addRecurso(recurso);
		session.saveOrUpdate(perfil);
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setLogin(login);
		usuario.setSenha(passwordEncoder.encode(senha));
		usuario.setPerfil(perfil);
		session.saveOrUpdate(usuario);
		
		session.getTransaction().commit();
		
	}
	
}
