package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.ServicoOrdemServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class ServicoOrdemServicoDAOImpl implements ServicoOrdemServicoDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(ServicoOrdemServicoDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(ServicoOrdemServico servicoOrdemServico) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(servicoOrdemServico);
		logger.info("ServicoOrdemServico salvo com sucesso! | " + servicoOrdemServico);

	}

	@Override
	public void atualiza(ServicoOrdemServico servicoOrdemServico) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(servicoOrdemServico);
		logger.info("ServicoOrdemServico atualizado com sucesso! |" + servicoOrdemServico);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServicoOrdemServico> listaByOrdemServico(OrdemServico ordemServico) {
		Session session = this.sessionFactory.getCurrentSession();
		List<ServicoOrdemServico> listaByServicoOrdemServico = session.createQuery(
				"from ServicoOrdemServico pp where ordemServico = :ordemServico")
				.setParameter("ordemServico", ordemServico)
				.list();
		for (ServicoOrdemServico servicoOrdemServico : listaByServicoOrdemServico) {
			logger.info("Lista servicoOrdemServico :: " + servicoOrdemServico);
		}
		return listaByServicoOrdemServico;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<ServicoOrdemServico> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from ServicoOrdemServico f where f.nome like :busca order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("busca",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("busca", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<ServicoOrdemServico> dados = new BootgridData<ServicoOrdemServico>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<ServicoOrdemServico>) queryB.list());

		return dados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<ServicoOrdemServico> listaToBootgrid(String palavra, BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from ServicoOrdemServico f "
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

		BootgridData<ServicoOrdemServico> dados = new BootgridData<ServicoOrdemServico>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryQtde.list().size());
		dados.setRows((List<ServicoOrdemServico>) query.list());

		return dados;
	}
	
	@Override
	public ServicoOrdemServico getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		ServicoOrdemServico servicoOrdemServico = (ServicoOrdemServico) session.load(ServicoOrdemServico.class,
				new Long(id));
		logger.info("ServicoOrdemServico carregado com sucesso! | " + servicoOrdemServico);
		return servicoOrdemServico;
	}

	@Override
	public void removeByOrdemServico(OrdemServico ordemServico) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "delete from ServicoOrdemServico where ordemServico = :ordemServico";
        Query query = session.createQuery(hql)
        		.setParameter("ordemServico", ordemServico);
        
        query.executeUpdate();

		logger.info("ServicoOrdemServico apagado com sucesso!");
	}

}
