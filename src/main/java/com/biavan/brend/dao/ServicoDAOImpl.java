package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.Servico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class ServicoDAOImpl implements ServicoDAO {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ServicoDAOImpl.class);
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(Servico servico) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(servico);
		logger.info("Servico salvo com sucesso! | " + servico);
		
	}

	@Override
	public void atualiza(Servico servico) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(servico);
		logger.info("Servico atualizado com sucesso! |" + servico);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<Servico> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from Servico p where p.nome like :nome order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("nome",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("nome", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<Servico> dados = new BootgridData<Servico>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<Servico>) queryB.list());

		return dados;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<Servico> listaToBootgrid(String palavra, BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from Servico p "
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

		BootgridData<Servico> dados = new BootgridData<Servico>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryQtde.list().size());
		dados.setRows((List<Servico>) query.list());

		return dados;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Servico> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Servico> listaDeServico = session.createQuery(
				"from Servico").list();
		return listaDeServico;
	}

	@Override
	public Servico getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Servico servico = (Servico) session.load(Servico.class,
				new Long(id));
		logger.info("Servico carregada com sucesso! | " + servico);
		return servico;
	}

	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Servico servico = (Servico) session.load(Servico.class,
				new Long(id));
		if (null != servico) {
			session.delete(servico);
		}
		logger.info("Servico apagado com sucesso! | " + servico);
		
	}

}
