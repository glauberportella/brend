package com.biavan.brend.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.Funcionario;
import com.biavan.brend.model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(UsuarioDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(Usuario usuario) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(usuario);
	}

	@Override
	public void atualiza(Usuario usuario) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(usuario);
		logger.info("Usuario atualizado com sucesso!");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Usuario> listaDeUsuario = session.createQuery(
				"from Usuario").list();
		return listaDeUsuario;
	}

	@Override
	public void remove(String login) {
		Session session = this.sessionFactory.getCurrentSession();
		Usuario usuario = findByLogin(login);

		if (null != usuario) {
			session.delete(usuario);
		}
		logger.info("Usuario apagado com sucesso!");
	}
	
	@Override
	public Usuario getByFuncionario(Funcionario funcionario) {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
 
		usuarios = session.createQuery("from Usuario where funcionario = :funcionario")
			.setParameter("funcionario", funcionario).list();
 
		if (usuarios.size() > 0) {
			return usuarios.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public Usuario findByLogin(String login) {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
 
		usuarios = session.createQuery("from Usuario where login = :login")
			.setString("login", login).list();
 
		if (usuarios.size() > 0) {
			return usuarios.get(0);
		} else {
			return null;
		}
	}

}
