package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.TipoServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class TipoServicoDAOImpl implements TipoServicoDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(TipoServicoDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(TipoServico tipoServico) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(tipoServico);
		logger.info("TipoServico salva com sucesso! | " + tipoServico);

	}

	@Override
	public void atualiza(TipoServico tipoServico) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(tipoServico);
		logger.info("TipoServico atualizada com sucesso! |" + tipoServico);

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoServico> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<TipoServico> listaDeTipoServico = session.createQuery(
				"from TipoServico").list();
		for (TipoServico tipoServico : listaDeTipoServico) {
			logger.info("Lista TipoServico :: " + tipoServico);
		}
		return listaDeTipoServico;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<TipoServico> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from TipoServico m where m.nome like :nome order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("nome",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("nome", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<TipoServico> dados = new BootgridData<TipoServico>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<TipoServico>) queryB.list());

		return dados;
	}

	@Override
	public TipoServico getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		TipoServico tipoServico = (TipoServico) session.load(TipoServico.class,
				new Long(id));
		logger.info("TipoServico carregada com sucesso! | " + tipoServico);
		return tipoServico;
	}

	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		TipoServico tipoServico = (TipoServico) session.load(TipoServico.class,
				new Long(id));
		if (null != tipoServico) {
			session.delete(tipoServico);
		}
		logger.info("TipoServico apagada com sucesso! | " + tipoServico);

	}

}
