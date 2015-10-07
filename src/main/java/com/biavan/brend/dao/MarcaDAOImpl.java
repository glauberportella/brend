package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.Marca;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class MarcaDAOImpl implements MarcaDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(MarcaDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(Marca marca) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(marca);
		logger.info("Marca salva com sucesso! | " + marca);

	}

	@Override
	public void atualiza(Marca marca) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(marca);
		logger.info("Marca atualizada com sucesso! |" + marca);

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Marca> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Marca> listaDeMarca = session.createQuery(
				"from Marca").list();
		for (Marca marca : listaDeMarca) {
			logger.info("Lista Marca :: " + marca);
		}
		return listaDeMarca;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<Marca> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from Marca m where m.nome like :nome order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("nome",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("nome", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<Marca> dados = new BootgridData<Marca>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<Marca>) queryB.list());

		return dados;
	}

	@Override
	public Marca getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Marca marca = (Marca) session.load(Marca.class,
				new Long(id));
		logger.info("Marca carregada com sucesso! | " + marca);
		return marca;
	}

	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Marca marca = (Marca) session.load(Marca.class,
				new Long(id));
		if (null != marca) {
			session.delete(marca);
		}
		logger.info("Marca apagada com sucesso! | " + marca);

	}

}
