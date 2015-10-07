package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.Peca;
import com.biavan.brend.model.PecaOrcamento;
import com.biavan.brend.model.Servico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class OrcamentoDAOImpl implements OrcamentoDAO {
	
	private static final Logger logger = LoggerFactory
			.getLogger(OrcamentoDAOImpl.class);
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(Orcamento orcamento) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(orcamento);
		logger.info("Orcamento salvo com sucesso! | " + orcamento);
		
	}
	
	@Override
	public void inserePecaOrcamento(PecaOrcamento pecaOrcamento) {
		Session session = sessionFactory.getCurrentSession();
		session.save(pecaOrcamento);
		logger.info("Peca Orcamento salvo com sucesso! | " + pecaOrcamento);
	}

	@Override
	public void atualiza(Orcamento orcamento) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(orcamento);
		logger.info("Orcamento atualizado com sucesso! |" + orcamento);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<Orcamento> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from Orcamento o where o.veiculo like :veiculo order by "
				+ "o." + param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("veiculo",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("veiculo", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<Orcamento> dados = new BootgridData<Orcamento>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<Orcamento>) queryB.list());

		return dados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<Orcamento> listaToBootgrid(String palavra, BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from Orcamento o "
				+ "where o.id = :id "
				+ "order by "
				+ "o." + param.getSortColumn() + " " + param.getSortType();

		Query queryQtde = session.createQuery(hql)
				.setLong("id", Long.parseLong(palavra));

		Query query = session.createQuery(hql)
				.setLong("id", Long.parseLong(palavra))
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<Orcamento> dados = new BootgridData<Orcamento>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryQtde.list().size());
		dados.setRows((List<Orcamento>) query.list());

		return dados;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Orcamento> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Orcamento> listaDeOrcamento = session.createQuery(
				"from Orcamento").list();
		for (Orcamento orcamento : listaDeOrcamento) {
			logger.info("Lista Or�amento :: " + orcamento);
		}
		return listaDeOrcamento;
	}

	@Override
	public Orcamento getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Orcamento orcamento = (Orcamento) session.load(Orcamento.class,
				new Long(id));
		logger.info("Orcamento carregado com sucesso! | " + orcamento);
		return orcamento;
	}

	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Orcamento orcamento = (Orcamento) session.load(Orcamento.class,
				new Long(id));
		if (null != orcamento) {
			session.delete(orcamento);
		}
		logger.info("Orcamento apagado com sucesso! | " + orcamento);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Servico> getServicos(List<Long> ids) {
		Session session = sessionFactory.getCurrentSession();

		String hql = "from Servico p where p.id in (:ids) ";

		Query query = session.createQuery(hql).setParameterList("ids", ids);

		return (List<Servico>)	query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Peca> getPecas(List<Long> ids) {
		Session session = sessionFactory.getCurrentSession();

		String hql = "from Peca p where p.id in (:ids) ";

		Query query = session.createQuery(hql).setParameterList("ids", ids);

		return (List<Peca>)	query.list();
	}
	
	@Override
	@Transactional
	public Orcamento getOrcamentoByOrdemServico(OrdemServico ordemServico) {
		Session session = sessionFactory.getCurrentSession();
		OrdemServico novaOS = (OrdemServico) session.load(OrdemServico.class, ordemServico.getId());
		return novaOS.getOrcamento();
	}

}
