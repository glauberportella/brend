package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.biavan.brend.model.Peca;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class PecaDAOImpl implements PecaDAO {
	
	private static final Logger logger = LoggerFactory
			.getLogger(PecaDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(Peca peca) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(peca);
		logger.info("Peca salvo com sucesso! | " + peca);
		
	}

	@Override
	public void atualiza(Peca peca) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(peca);
		logger.info("Peca atualizado com sucesso! |" + peca);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<Peca> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from Peca p where p.nome like :nome order by "
				+ "p." + param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("nome",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("nome", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<Peca> dados = new BootgridData<Peca>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<Peca>) queryB.list());

		return dados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<Peca> listaToBootgrid(String palavra, BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from Peca p "
				+ "where p.nome like :nome "
				+ "or p.descricao like :descricao "
				+ "order by "
				+ "p." + param.getSortColumn() + " " + param.getSortType();

		Query queryQtde = session.createQuery(hql)
				.setString("nome", "%" + palavra + "%")
				.setString("descricao", "%" + palavra + "%");

		Query query = session.createQuery(hql)
				.setString("nome", "%" + palavra + "%")
				.setString("descricao", "%" + palavra + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<Peca> dados = new BootgridData<Peca>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryQtde.list().size());
		dados.setRows((List<Peca>) query.list());

		return dados;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Peca> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Peca> listaDePeca = session.createQuery(
				"from Peca").list();
		return listaDePeca;
	}

	@Override
	public Peca getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Peca peca = (Peca) session.load(Peca.class,
				new Long(id));
		logger.info("Peca carregada com sucesso! | " + peca);
		return peca;
	}

	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Peca peca = (Peca) session.load(Peca.class,
				new Long(id));
		if (null != peca) {
			session.delete(peca);
		}
		logger.info("Peca apagado com sucesso! | " + peca);
		
	}

}
