package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.ServicoOrcamento;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class ServicoOrcamentoDAOImpl implements ServicoOrcamentoDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(ServicoOrcamentoDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(ServicoOrcamento servicoOrcamento) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(servicoOrcamento);
		logger.info("ServicoOrcamento salvo com sucesso! | " + servicoOrcamento);

	}

	@Override
	public void atualiza(ServicoOrcamento servicoOrcamento) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(servicoOrcamento);
		logger.info("ServicoOrcamento atualizado com sucesso! |" + servicoOrcamento);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServicoOrcamento> listaByOrcamento(Orcamento orcamento) {
		Session session = this.sessionFactory.getCurrentSession();
		List<ServicoOrcamento> listaDeServicoOrcamento = session.createQuery(
				"from ServicoOrcamento pp where orcamento = :orcamento")
				.setParameter("orcamento", orcamento)
				.list();
		for (ServicoOrcamento servicoOrcamento : listaDeServicoOrcamento) {
			logger.info("Lista ServicoOrcamento :: " + servicoOrcamento);
		}
		return listaDeServicoOrcamento;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<ServicoOrcamento> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from ServicoOrcamento f where f.nome like :busca order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("busca",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("busca", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<ServicoOrcamento> dados = new BootgridData<ServicoOrcamento>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<ServicoOrcamento>) queryB.list());

		return dados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<ServicoOrcamento> listaToBootgrid(String palavra, BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from ServicoOrcamento f "
				+ "where f.nome like :nome "
				+ "or f.descricao like :descricao "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryQtde = session.createQuery(hql)
				.setString("nome", "%" + palavra + "%")
				.setString("descricao", "%" + palavra + "%");
		

		Query query = session.createQuery(hql)
				.setString("nome", "%" + palavra + "%")
				.setString("descricao", "%" + palavra + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<ServicoOrcamento> dados = new BootgridData<ServicoOrcamento>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryQtde.list().size());
		dados.setRows((List<ServicoOrcamento>) query.list());

		return dados;
	}
	
	@Override
	public ServicoOrcamento getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		ServicoOrcamento servicoOrcamento = (ServicoOrcamento) session.load(ServicoOrcamento.class,
				new Long(id));
		logger.info("ServicoOrcamento carregado com sucesso! | " + servicoOrcamento);
		return servicoOrcamento;
	}

	@Override
	public void removeByOrcamento(Orcamento orcamento) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "delete from ServicoOrcamento where orcamento = :orcamento";
        Query query = session.createQuery(hql)
        		.setParameter("orcamento", orcamento);
        
        query.executeUpdate();

		logger.info("ServicoOrcamento apagado com sucesso!");
	}

}
