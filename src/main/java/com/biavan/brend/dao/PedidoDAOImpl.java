package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.Peca;
import com.biavan.brend.model.PecaPedido;
import com.biavan.brend.model.Pedido;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class PedidoDAOImpl implements PedidoDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(PedidoDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public long insere(Pedido pedido) {
		Session session = sessionFactory.getCurrentSession();
		session.save(pedido);
		logger.info("Pedido salvo com sucesso! | " + pedido);
		return pedido.getId();
	}
	
	@Override
	public void inserePecaPedido(PecaPedido pecaPedido) {
		Session session = sessionFactory.getCurrentSession();
		session.save(pecaPedido);
		logger.info("Peca Pedido salvo com sucesso! | " + pecaPedido);
	}

	@Override
	public long atualiza(Pedido pedido) {
		Session session = sessionFactory.getCurrentSession();
		session.update(pedido);
		logger.info("Pedido atualizado com sucesso! |" + pedido);
		return pedido.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> lista() {
		Session session = sessionFactory.getCurrentSession();
		List<Pedido> listaDePedido = session.createQuery(
				"from Pedido").list();
		for (Pedido pedido : listaDePedido) {
			logger.info("Lista pedido :: " + pedido);
		}
		return listaDePedido;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<Pedido> listaToBootgrid(BootgridParam param) {
		Session session = sessionFactory.getCurrentSession();

		String hql = "from Pedido p where p.data like :busca order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("busca",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("busca", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<Pedido> dados = new BootgridData<Pedido>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<Pedido>) queryB.list());

		return dados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<Pedido> listaToBootgrid(String palavra, BootgridParam param) {
		Session session = sessionFactory.getCurrentSession();

		String hql = "from Pedido f "
				+ "where f.data like :data "
				+ "order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryQtde = session.createQuery(hql)
				.setString("data", "%" + palavra + "%");
		

		Query query = session.createQuery(hql)
				.setString("data", "%" + palavra + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<Pedido> dados = new BootgridData<Pedido>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryQtde.list().size());
		dados.setRows((List<Pedido>) query.list());

		return dados;
	}
	
	@Override
	public Pedido getById(long id) {
		Session session = sessionFactory.getCurrentSession();
		Pedido pedido = (Pedido) session.load(Pedido.class,
				new Long(id));
		logger.info("Pedido carregado com sucesso! | " + pedido);
		return pedido;
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		Pedido pedido = (Pedido) session.load(Pedido.class,
				new Long(id));
		if (null != pedido) {
			session.delete(pedido);
		}
		logger.info("Pedido apagado com sucesso! | " + pedido);

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Peca> getPecas(List<Long> ids) {
		Session session = sessionFactory.getCurrentSession();

		String hql = "from Peca p where p.id in (:ids) ";

		Query query = session.createQuery(hql).setParameterList("ids", ids);

		return (List<Peca>)	query.list();
	}
	
}
