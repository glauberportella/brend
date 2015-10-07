package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.TipoMotor;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class TipoMotorDAOImpl implements TipoMotorDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(TipoMotorDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(TipoMotor tipoMotor) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(tipoMotor);
		logger.info("TipoMotor salvo com sucesso! | " + tipoMotor);

	}

	@Override
	public void atualiza(TipoMotor tipoMotor) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(tipoMotor);
		logger.info("TipoMotor atualizado com sucesso! |" + tipoMotor);

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoMotor> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<TipoMotor> listaDeTipoMotor = session.createQuery(
				"from TipoMotor").list();
		return listaDeTipoMotor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<TipoMotor> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from TipoMotor m where m.nome like :nome order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("nome",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("nome", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<TipoMotor> dados = new BootgridData<TipoMotor>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<TipoMotor>) queryB.list());

		return dados;
	}

	@Override
	public TipoMotor getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		TipoMotor tipoMotor = (TipoMotor) session.load(TipoMotor.class,
				new Long(id));
		logger.info("TipoMotor carregado com sucesso! | " + tipoMotor);
		return tipoMotor;
	}

	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		TipoMotor tipoMotor = (TipoMotor) session.load(TipoMotor.class,
				new Long(id));
		if (null != tipoMotor) {
			session.delete(tipoMotor);
		}
		logger.info("TipoMotor apagado com sucesso! | " + tipoMotor);

	}

}
