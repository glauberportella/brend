package com.biavan.brend.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.ConfigEmpresa;

public class ConfigEmpresaDAOImpl implements ConfigEmpresaDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(ConfigEmpresaDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(ConfigEmpresa configEmpresa) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(configEmpresa);
		logger.info("Dados da emporesa salvo com sucesso! | " + configEmpresa);
	}
	
	@Override
	public void atualiza(ConfigEmpresa configEmpresa) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(configEmpresa);
		logger.info("Dados da Empresa atualizados com sucesso! |" + configEmpresa);

	}

	@Override
	public ConfigEmpresa getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		ConfigEmpresa configEmpresa = (ConfigEmpresa) session.load(ConfigEmpresa.class,
				new Long(id));
		logger.info("Dados da Empresa carregado com sucesso! | " + configEmpresa);
		return configEmpresa;
	}
}
