package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.RegistroContato;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class RegistroContatoDAOImpl implements RegistroContatoDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(RegistroContatoDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(RegistroContato registroContato) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(registroContato);
		logger.info("RegistroContato salva com sucesso! | " + registroContato);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroContato> listaByOrdemServico(OrdemServico ordemServico) {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<RegistroContato> listaDeRegistroContato = session.createQuery(
				"from RegistroContato where ordemServico = :ordemServico ")
				.setParameter("ordemServico", ordemServico)
				.list();

		return listaDeRegistroContato;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<RegistroContato> listaToBootgridByOrdemServico(
			OrdemServico ordemServico, BootgridParam param) {
		
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from RegistroContato m "
				+ "where ordemServico = :ordemServico "
				+ "and m.descricao like :descricao order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql)
				.setParameter("ordemServico", ordemServico)
				.setString("descricao", "%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setParameter("ordemServico", ordemServico)
				.setString("descricao", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<RegistroContato> dados = new BootgridData<RegistroContato>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<RegistroContato>) queryB.list());

		return dados;
	}

	@Override
	public RegistroContato getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		RegistroContato registroContato = (RegistroContato) session.load(RegistroContato.class,
				new Long(id));
		logger.info("RegistroContato carregada com sucesso! | " + registroContato);
		return registroContato;
	}

}
