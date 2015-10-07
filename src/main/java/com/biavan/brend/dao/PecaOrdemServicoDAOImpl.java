package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.PecaOrdemServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class PecaOrdemServicoDAOImpl implements PecaOrdemServicoDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(PecaOrdemServicoDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(PecaOrdemServico pecaOrdemServico) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(pecaOrdemServico);
		logger.info("PecaOrdemServico salvo com sucesso! | " + pecaOrdemServico);

	}

	@Override
	public void atualiza(PecaOrdemServico pecaOrdemServico) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(pecaOrdemServico);
		logger.info("PecaOrdemServico atualizado com sucesso! |" + pecaOrdemServico);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PecaOrdemServico> listaByOrdemServico(OrdemServico ordemServico) {
		Session session = this.sessionFactory.getCurrentSession();
		List<PecaOrdemServico> listaDePecaOrdemServico = session.createQuery(
				"from PecaOrdemServico pp where ordemServico = :ordemServico")
				.setParameter("ordemServico", ordemServico)
				.list();
		return listaDePecaOrdemServico;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<PecaOrdemServico> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from PecaOrdemServico f where f.id like :busca order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("busca",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("busca", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<PecaOrdemServico> dados = new BootgridData<PecaOrdemServico>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<PecaOrdemServico>) queryB.list());

		return dados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<PecaOrdemServico> listaToBootgrid(String palavra, BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from PecaOrdemServico f "
				+ "where f.ano like :ano "
				+ "or f.veiculo like :veiculo "
				+ "order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryQtde = session.createQuery(hql)
				.setString("ano", "%" + palavra + "%")
				.setString("veiculo", "%" + palavra + "%");
		

		Query query = session.createQuery(hql)
				.setString("ano", "%" + palavra + "%")
				.setString("veiculo", "%" + palavra + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<PecaOrdemServico> dados = new BootgridData<PecaOrdemServico>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryQtde.list().size());
		dados.setRows((List<PecaOrdemServico>) query.list());

		return dados;
	}
	
	@Override
	public PecaOrdemServico getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		PecaOrdemServico pecaOrdemServico = (PecaOrdemServico) session.load(PecaOrdemServico.class,
				new Long(id));
		logger.info("PecaOrdemServico carregado com sucesso! | " + pecaOrdemServico);
		return pecaOrdemServico;
	}

	@Override
	public void removeByOrdemServico(OrdemServico ordemServico) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "delete from PecaOrdemServico where ordemServico = :ordemServico";
        Query query = session.createQuery(hql)
        		.setParameter("ordemServico", ordemServico);
        
        query.executeUpdate();

		logger.info("PecaOrdemServico apagado com sucesso!");
	}
}
