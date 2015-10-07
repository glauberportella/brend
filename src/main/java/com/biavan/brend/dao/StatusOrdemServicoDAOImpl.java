package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.enums.StatusOS;
import com.biavan.brend.model.InformacaoStatusOrdemServico;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.StatusOrdemServico;

public class StatusOrdemServicoDAOImpl implements StatusOrdemServicoDAO {
	
	private static final Logger logger = LoggerFactory
			.getLogger(StatusOrdemServicoDAOImpl.class);
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(StatusOrdemServico statusOrdemServico) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(statusOrdemServico);
		logger.info("StatusOrdemServico salvo com sucesso! | " + statusOrdemServico);
		
	}

	@Override
	public void atualiza(StatusOrdemServico statusOrdemServico) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(statusOrdemServico);
		logger.info("StatusOrdemServico atualizado com sucesso! |" + statusOrdemServico);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StatusOrdemServico> listaByOrdemServico(OrdemServico ordemServico) {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<StatusOrdemServico> listaDeStatusOrdemServico = session.createQuery(
				"from StatusOrdemServico s "
				+ "where ordemServico = :ordemServico "
				+ "order by dataInicio desc ")
				.setParameter("ordemServico", ordemServico)
				.list();
		for (StatusOrdemServico statusOrdemServico : listaDeStatusOrdemServico) {
			logger.info("Lista StatusOrdemServico :: " + statusOrdemServico);
		}
		return listaDeStatusOrdemServico;
	}
	
	@Override
	public StatusOrdemServico getStatusAtualByOrdemServico(OrdemServico ordemServico) {
		Session session = this.sessionFactory.getCurrentSession();
		
		return (StatusOrdemServico) session.createQuery(
				"from StatusOrdemServico s "
				+ "where ordemServico = :ordemServico "
				+ "and dataFim = '3000-01-01' ")
				.setParameter("ordemServico", ordemServico)
				.uniqueResult();

	}

	@Override
	public StatusOrdemServico getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		StatusOrdemServico statusOrdemServico = (StatusOrdemServico) session.load(StatusOrdemServico.class,
				new Long(id));
		logger.info("StatusOrdemServico carregada com sucesso! | " + statusOrdemServico);
		return statusOrdemServico;
	}

	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		StatusOrdemServico statusOrdemServico = (StatusOrdemServico) session.load(StatusOrdemServico.class,
				new Long(id));
		if (null != statusOrdemServico) {
			session.delete(statusOrdemServico);
		}
		logger.info("StatusOrdemServico apagado com sucesso! | " + statusOrdemServico);
		
	}
	
	@Override
	public InformacaoStatusOrdemServico percentualByStatus(StatusOS statusOS) {
		Session session = this.sessionFactory.getCurrentSession();

		InformacaoStatusOrdemServico info = new InformacaoStatusOrdemServico();
		
		info.setStatusOS(statusOS);
		
		info.setTotalOrdemServicos((Float) Float.parseFloat(session.createQuery(
				"select count(distinct s.ordemServico) "
				+ "from StatusOrdemServico s "
				+ "inner join s.ordemServico ordemServico "
				+ "where s.dataFim = '3000-01-01' "
				+ "and ordemServico.ordemServicoPai is null ")
				.uniqueResult().toString()));
		
		info.setQtdeByStatus((Float) Float.parseFloat(session.createQuery(
				"select count(distinct s.ordemServico) "
				+ "from StatusOrdemServico s "
				+ "inner join s.ordemServico ordemServico "
				+ "where s.dataFim = '3000-01-01' "
				+ "and ordemServico.ordemServicoPai is null "
				+ "and s.statusOS = :statusOS")
				.setParameter("statusOS", statusOS)
				.uniqueResult().toString()));

		return info;
	}
	
}
