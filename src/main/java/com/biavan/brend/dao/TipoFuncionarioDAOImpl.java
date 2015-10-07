package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.TipoFuncionario;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class TipoFuncionarioDAOImpl implements TipoFuncionarioDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(TipoFuncionarioDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(TipoFuncionario tipoFuncionario) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(tipoFuncionario);
		logger.info("TipoFuncionario salvo com sucesso! | " + tipoFuncionario);

	}

	@Override
	public void atualiza(TipoFuncionario tipoFuncionario) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(tipoFuncionario);
		logger.info("TipoFuncionario atualizado com sucesso! |" + tipoFuncionario);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoFuncionario> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		List<TipoFuncionario> listaDeTipoFuncionario = session.createQuery(
				"from TipoFuncionario").list();
		for (TipoFuncionario tipoFuncionario : listaDeTipoFuncionario) {
			logger.info("Lista tipoFuncionario :: " + tipoFuncionario);
		}
		return listaDeTipoFuncionario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<TipoFuncionario> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from TipoFuncionario f where f.nome like :nome order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("nome",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("nome", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<TipoFuncionario> dados = new BootgridData<TipoFuncionario>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<TipoFuncionario>) queryB.list());

		return dados;
	}

	@Override
	public TipoFuncionario getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		TipoFuncionario tipoFuncionario = (TipoFuncionario) session.load(TipoFuncionario.class,
				new Long(id));
		logger.info("TipoFuncionario carregado com sucesso! | " + tipoFuncionario);
		return tipoFuncionario;
	}

	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		TipoFuncionario tipoFuncionario = (TipoFuncionario) session.load(TipoFuncionario.class,
				new Long(id));
		if (null != tipoFuncionario) {
			session.delete(tipoFuncionario);
		}
		logger.info("TipoFuncionario apagado com sucesso! | " + tipoFuncionario);

	}

}
