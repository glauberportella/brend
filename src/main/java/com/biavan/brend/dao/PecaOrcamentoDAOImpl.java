package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.PecaOrcamento;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class PecaOrcamentoDAOImpl implements PecaOrcamentoDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(PecaOrcamentoDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(PecaOrcamento pecaOrcamento) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(pecaOrcamento);
		logger.info("PecaOrcamento salvo com sucesso! | " + pecaOrcamento);

	}

	@Override
	public void atualiza(PecaOrcamento pecaOrcamento) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(pecaOrcamento);
		logger.info("PecaOrcamento atualizado com sucesso! |" + pecaOrcamento);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PecaOrcamento> listaByOrcamento(Orcamento orcamento) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery(
				"from PecaOrcamento pp where orcamento = :orcamento")
				.setParameter("orcamento", orcamento)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<PecaOrcamento> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from PecaOrcamento f where f.modelo like :busca order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("busca",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("busca", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<PecaOrcamento> dados = new BootgridData<PecaOrcamento>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<PecaOrcamento>) queryB.list());

		return dados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<PecaOrcamento> listaToBootgrid(String palavra, BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from PecaOrcamento f "
				+ "where f.modelo like :modelo "
				+ "or f.ano like :ano "
				+ "or f.veiculo like :veiculo "
				+ "order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryQtde = session.createQuery(hql)
				.setString("modelo", "%" + palavra + "%")
				.setString("ano", "%" + palavra + "%")
				.setString("veiculo", "%" + palavra + "%");
		

		Query query = session.createQuery(hql)
				.setString("modelo", "%" + palavra + "%")
				.setString("ano", "%" + palavra + "%")
				.setString("veiculo", "%" + palavra + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<PecaOrcamento> dados = new BootgridData<PecaOrcamento>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryQtde.list().size());
		dados.setRows((List<PecaOrcamento>) query.list());

		return dados;
	}
	
	@Override
	public PecaOrcamento getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		PecaOrcamento pecaOrcamento = (PecaOrcamento) session.load(PecaOrcamento.class,
				new Long(id));
		logger.info("PecaOrcamento carregado com sucesso! | " + pecaOrcamento);
		return pecaOrcamento;
	}

	@Override
	public void removeByOrcamento(Orcamento orcamento) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "delete from PecaOrcamento where orcamento = :orcamento";
        Query query = session.createQuery(hql)
        		.setParameter("orcamento", orcamento);
        
        query.executeUpdate();

		logger.info("PecaOrcamento apagado com sucesso!");
	}
}
