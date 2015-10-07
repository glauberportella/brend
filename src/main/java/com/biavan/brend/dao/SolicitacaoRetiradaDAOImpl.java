package com.biavan.brend.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.enums.StatusSolicitacaoRetirada;
import com.biavan.brend.model.SolicitacaoRetirada;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class SolicitacaoRetiradaDAOImpl implements SolicitacaoRetiradaDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(SolicitacaoRetiradaDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(SolicitacaoRetirada solicitacaoRetirada) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(solicitacaoRetirada);
		logger.info("SolicitacaoRetirada salva com sucesso! | " + solicitacaoRetirada);

	}

	@Override
	public void atualiza(SolicitacaoRetirada solicitacaoRetirada) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(solicitacaoRetirada);
		logger.info("SolicitacaoRetirada atualizada com sucesso! |" + solicitacaoRetirada);

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitacaoRetirada> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<SolicitacaoRetirada> listaDeSolicitacaoRetirada = session.createQuery(
				"from SolicitacaoRetirada").list();
		for (SolicitacaoRetirada solicitacaoRetirada : listaDeSolicitacaoRetirada) {
			logger.info("Lista SolicitacaoRetirada :: " + solicitacaoRetirada);
		}
		return listaDeSolicitacaoRetirada;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<SolicitacaoRetirada> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from SolicitacaoRetirada m "
				+ "left join m.solicitante solicitante "
				+ "left join m.autorizador autorizador "
				+ "where solicitante.nome like :solicitante "
				+ "or autorizador.nome like :autorizador "
				+ "order by "
				+ "m." + param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql)
				.setString("solicitante", "%" + param.getSearchPhrase() + "%")
				.setString("autorizador", "%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("solicitante", "%" + param.getSearchPhrase() + "%")
				.setString("autorizador", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<SolicitacaoRetirada> dados = new BootgridData<SolicitacaoRetirada>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		
		List<Object[]> result = queryB.list();
		List<SolicitacaoRetirada> lista = new ArrayList<SolicitacaoRetirada>();	

		for(Object[] sr : result) {
			lista.add((SolicitacaoRetirada) sr[0]);
		}
		
		dados.setRows(lista);

		return dados;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<SolicitacaoRetirada> listaPendenteToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from SolicitacaoRetirada m "
				+ "where m.status = :status "
				+ "and m.solicitante.nome like :solicitante "
				+ "order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql)
				.setString("solicitante", "%" + param.getSearchPhrase() + "%")
				.setParameter("status", StatusSolicitacaoRetirada.PENDENTE);

		Query queryB = session.createQuery(hql)
				.setString("solicitante", "%" + param.getSearchPhrase() + "%")
				.setParameter("status", StatusSolicitacaoRetirada.PENDENTE)
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<SolicitacaoRetirada> dados = new BootgridData<SolicitacaoRetirada>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<SolicitacaoRetirada>) queryB.list());

		return dados;
	
	}

	@Override
	public SolicitacaoRetirada getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		SolicitacaoRetirada solicitacaoRetirada = (SolicitacaoRetirada) session.load(SolicitacaoRetirada.class,
				new Long(id));
		logger.info("SolicitacaoRetirada carregada com sucesso! | " + solicitacaoRetirada);
		return solicitacaoRetirada;
	}

	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		SolicitacaoRetirada solicitacaoRetirada = (SolicitacaoRetirada) session.load(SolicitacaoRetirada.class,
				new Long(id));
		if (null != solicitacaoRetirada) {
			session.delete(solicitacaoRetirada);
		}
		logger.info("SolicitacaoRetirada apagada com sucesso! | " + solicitacaoRetirada);

	}

}
