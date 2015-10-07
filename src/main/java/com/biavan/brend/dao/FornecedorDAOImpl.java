package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.Fornecedor;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class FornecedorDAOImpl implements FornecedorDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(FornecedorDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(Fornecedor fornecedor) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(fornecedor);
		logger.info("Fornecedor salvo com sucesso! | " + fornecedor);

	}

	@Override
	public void atualiza(Fornecedor fornecedor) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(fornecedor);
		logger.info("Fornecedor atualizado com sucesso! |" + fornecedor);

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Fornecedor> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Fornecedor> listaDeFornecedor = session.createQuery(
				"from Fornecedor").list();
		for (Fornecedor fornecedor : listaDeFornecedor) {
			logger.info("Lista Fornecedor :: " + fornecedor);
		}
		return listaDeFornecedor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<Fornecedor> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from Fornecedor f where f.razao like :razao order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("razao",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("razao", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<Fornecedor> dados = new BootgridData<Fornecedor>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<Fornecedor>) queryB.list());

		return dados;
	}

	@Override
	public Fornecedor getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Fornecedor fornecedor = (Fornecedor) session.load(Fornecedor.class,
				new Long(id));
		logger.info("Fornecedor carregado com sucesso! | " + fornecedor);
		return fornecedor;
	}

	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Fornecedor fornecedor = (Fornecedor) session.load(Fornecedor.class,
				new Long(id));
		if (null != fornecedor) {
			session.delete(fornecedor);
		}
		logger.info("Fornecedor apagado com sucesso! | " + fornecedor);

	}

}
