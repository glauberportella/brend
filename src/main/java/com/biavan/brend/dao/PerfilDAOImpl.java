package com.biavan.brend.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.Perfil;
import com.biavan.brend.model.Recurso;

public class PerfilDAOImpl implements PerfilDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(PerfilDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(Perfil perfil) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(perfil);
	}

	@Override
	public void atualiza(Perfil perfil) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(perfil);
		logger.info("Perfil atualizado com sucesso!");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Perfil> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Perfil> listaDePerfil = session.createQuery(
				"from Perfil").list();
		return listaDePerfil;
	}
	
	@Override
	public Perfil getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Perfil perfil = (Perfil) session.load(Perfil.class,	new Long(id));
		return perfil;
	}

	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Perfil perfil = (Perfil) session.load(Perfil.class,	new Long(id));
		if (null != perfil) {
			session.delete(perfil);
		}
		logger.info("Perfil apagado com sucesso!");
	}

	@Override
	public List<Recurso> getRecursosByPerfil(Perfil perfil) {
		Session session = this.sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		List<Object> result = session.createQuery(
				"from Recurso r join r.perfis p where p.id = :id")
				.setParameter("id", perfil.getId())
				.list();
		
		List<Recurso> recursos = new ArrayList<Recurso>();
		Iterator<Object> itr = result.iterator();
		while(itr.hasNext()) {
			Object[] obj = (Object[]) itr.next();
			recursos.add((Recurso) obj[0]);
		}
		
		return recursos;
	}
}
