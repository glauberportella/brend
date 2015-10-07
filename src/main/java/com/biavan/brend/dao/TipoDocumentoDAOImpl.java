package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.TipoDocumento;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class TipoDocumentoDAOImpl implements TipoDocumentoDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(TipoDocumentoDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(TipoDocumento tipoDocumento) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(tipoDocumento);
		logger.info("TipoDocumento salvo com sucesso! | " + tipoDocumento);

	}

	@Override
	public void atualiza(TipoDocumento tipoDocumento) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(tipoDocumento);
		logger.info("TipoDocumento atualizado com sucesso! |" + tipoDocumento);

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoDocumento> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<TipoDocumento> listaDeTipoDocumento = session.createQuery(
				"from TipoDocumento").list();
		for (TipoDocumento tipoDocumento : listaDeTipoDocumento) {
			logger.info("Lista TipoDocumento :: " + tipoDocumento);
		}
		return listaDeTipoDocumento;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<TipoDocumento> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from TipoDocumento m where m.nome like :nome order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("nome",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("nome", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<TipoDocumento> dados = new BootgridData<TipoDocumento>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<TipoDocumento>) queryB.list());

		return dados;
	}

	@Override
	public TipoDocumento getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		TipoDocumento tipoDocumento = (TipoDocumento) session.load(TipoDocumento.class,
				new Long(id));
		logger.info("TipoDocumento carregado com sucesso! | " + tipoDocumento);
		return tipoDocumento;
	}

	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		TipoDocumento tipoDocumento = (TipoDocumento) session.load(TipoDocumento.class,
				new Long(id));
		if (null != tipoDocumento) {
			session.delete(tipoDocumento);
		}
		logger.info("TipoDocumento apagado com sucesso! | " + tipoDocumento);

	}

}
