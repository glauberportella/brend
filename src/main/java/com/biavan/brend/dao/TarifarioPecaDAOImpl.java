package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.Peca;
import com.biavan.brend.model.TarifarioPeca;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class TarifarioPecaDAOImpl implements TarifarioPecaDAO {
	
	private static final Logger logger = LoggerFactory
			.getLogger(TarifarioPecaDAOImpl.class);
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(TarifarioPeca tarifarioPeca) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(tarifarioPeca);
		logger.info("TarifarioPeca salvo com sucesso! | " + tarifarioPeca);
		
	}

	@Override
	public void atualiza(TarifarioPeca tarifarioPeca) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(tarifarioPeca);
		logger.info("TarifarioPeca atualizado com sucesso! |" + tarifarioPeca);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<TarifarioPeca> listaToBootgrid(Peca peca, BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from TarifarioPeca p where p.peca = :peca and p.data like :data order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql)
				.setParameter("peca", peca)
				.setString("data", "%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setParameter("peca", peca)
				.setString("data", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<TarifarioPeca> dados = new BootgridData<TarifarioPeca>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<TarifarioPeca>) queryB.list());

		return dados;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TarifarioPeca> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<TarifarioPeca> listaDeTarifarioPeca = session.createQuery(
				"from TarifarioPeca").list();
		for (TarifarioPeca tarifarioPeca : listaDeTarifarioPeca) {
			logger.info("Lista TarifarioPeca :: " + tarifarioPeca);
		}
		return listaDeTarifarioPeca;
	}

	@Override
	public TarifarioPeca getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		TarifarioPeca tarifarioPeca = (TarifarioPeca) session.load(TarifarioPeca.class,
				new Long(id));
		logger.info("TarifarioPeca carregada com sucesso! | " + tarifarioPeca);
		return tarifarioPeca;
	}

	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		TarifarioPeca tarifarioPeca = (TarifarioPeca) session.load(TarifarioPeca.class,
				new Long(id));
		if (null != tarifarioPeca) {
			session.delete(tarifarioPeca);
		}
		logger.info("TarifarioPeca apagado com sucesso! | " + tarifarioPeca);
		
	}
	
}
