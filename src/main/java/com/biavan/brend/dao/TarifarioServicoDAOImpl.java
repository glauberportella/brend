package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.Servico;
import com.biavan.brend.model.TarifarioServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class TarifarioServicoDAOImpl implements TarifarioServicoDAO {
	
	private static final Logger logger = LoggerFactory
			.getLogger(TarifarioServicoDAOImpl.class);
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(TarifarioServico tarifarioServico) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(tarifarioServico);
		logger.info("TarifarioServico salvo com sucesso! | " + tarifarioServico);
		
	}

	@Override
	public void atualiza(TarifarioServico tarifarioServico) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(tarifarioServico);
		logger.info("TarifarioServico atualizado com sucesso! |" + tarifarioServico);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<TarifarioServico> listaToBootgrid(Servico servico, BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from TarifarioServico p where p.servico = :servico and p.data like :data order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql)
				.setParameter("servico", servico)
				.setString("data", "%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setParameter("servico", servico)
				.setString("data", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<TarifarioServico> dados = new BootgridData<TarifarioServico>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<TarifarioServico>) queryB.list());

		return dados;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TarifarioServico> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<TarifarioServico> listaDeTarifarioServico = session.createQuery(
				"from TarifarioServico").list();
		for (TarifarioServico tarifarioServico : listaDeTarifarioServico) {
			logger.info("Lista TarifarioServico :: " + tarifarioServico);
		}
		return listaDeTarifarioServico;
	}

	@Override
	public TarifarioServico getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		TarifarioServico tarifarioServico = (TarifarioServico) session.load(TarifarioServico.class,
				new Long(id));
		logger.info("TarifarioServico carregada com sucesso! | " + tarifarioServico);
		return tarifarioServico;
	}

	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		TarifarioServico tarifarioServico = (TarifarioServico) session.load(TarifarioServico.class,
				new Long(id));
		if (null != tarifarioServico) {
			session.delete(tarifarioServico);
		}
		logger.info("TarifarioServico apagado com sucesso! | " + tarifarioServico);
		
	}

}
