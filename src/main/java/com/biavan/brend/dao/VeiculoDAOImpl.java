package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.Veiculo;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class VeiculoDAOImpl implements VeiculoDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(VeiculoDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(Veiculo veiculo) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(veiculo);
		logger.info("Veiculo salvo com sucesso! | " + veiculo);

	}

	@Override
	public void atualiza(Veiculo veiculo) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(veiculo);
		logger.info("Veiculo atualizado com sucesso! |" + veiculo);

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Veiculo> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Veiculo> listaDeVeiculo = session.createQuery(
				"from Veiculo").list();
		return listaDeVeiculo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<Veiculo> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from Veiculo m "
				+ "where m.modelo like :search "
				+ "or m.marca like :search "
				+ "order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("search",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("search", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<Veiculo> dados = new BootgridData<Veiculo>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<Veiculo>) queryB.list());

		return dados;
	}

	@Override
	public Veiculo getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Veiculo veiculo = (Veiculo) session.load(Veiculo.class,
				new Long(id));
		logger.info("Veiculo carregado com sucesso! | " + veiculo);
		return veiculo;
	}

	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Veiculo veiculo = (Veiculo) session.load(Veiculo.class,
				new Long(id));
		if (null != veiculo) {
			session.delete(veiculo);
		}
		logger.info("Veiculo apagado com sucesso! | " + veiculo);

	}

}
