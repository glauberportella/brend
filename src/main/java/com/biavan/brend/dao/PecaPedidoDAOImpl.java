package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.PecaPedido;
import com.biavan.brend.model.Pedido;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class PecaPedidoDAOImpl implements PecaPedidoDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(PecaPedidoDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(PecaPedido pecaPedido) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(pecaPedido);
		logger.info("PecaPedido salvo com sucesso! | " + pecaPedido);

	}

	@Override
	public void atualiza(PecaPedido pecaPedido) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(pecaPedido);
		logger.info("PecaPedido atualizado com sucesso! |" + pecaPedido);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PecaPedido> listaByPedido(Pedido pedido) {
		Session session = this.sessionFactory.getCurrentSession();
		List<PecaPedido> listaDePecaPedido = session.createQuery(
				"from PecaPedido pp where pedido = :pedido")
				.setParameter("pedido", pedido)
				.list();
		for (PecaPedido pecaPedido : listaDePecaPedido) {
			logger.info("Lista pecaPedido :: " + pecaPedido);
		}
		return listaDePecaPedido;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<PecaPedido> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from PecaPedido f where f.razao like :busca order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("busca",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("busca", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<PecaPedido> dados = new BootgridData<PecaPedido>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<PecaPedido>) queryB.list());

		return dados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<PecaPedido> listaToBootgrid(String palavra, BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from PecaPedido f "
				+ "where f.email like :email "
				+ "or f.endereco like :endereco "
				+ "or f.fantasia like :fantasia "
				+ "or f.numeroDocumento like :numeroDocumento "
				+ "or f.razao like :razao "
				+ "or f.telefone like :telefone "
				+ "order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryQtde = session.createQuery(hql)
				.setString("email", "%" + palavra + "%")
				.setString("endereco", "%" + palavra + "%")
				.setString("fantasia", "%" + palavra + "%")
				.setString("numeroDocumento", "%" + palavra + "%")
				.setString("razao", "%" + palavra + "%")
				.setString("telefone", "%" + palavra + "%");
		

		Query query = session.createQuery(hql)
				.setString("email", "%" + palavra + "%")
				.setString("endereco", "%" + palavra + "%")
				.setString("fantasia", "%" + palavra + "%")
				.setString("numeroDocumento", "%" + palavra + "%")
				.setString("razao", "%" + palavra + "%")
				.setString("telefone", "%" + palavra + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<PecaPedido> dados = new BootgridData<PecaPedido>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryQtde.list().size());
		dados.setRows((List<PecaPedido>) query.list());

		return dados;
	}
	
	@Override
	public PecaPedido getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		PecaPedido pecaPedido = (PecaPedido) session.load(PecaPedido.class,
				new Long(id));
		logger.info("PecaPedido carregado com sucesso! | " + pecaPedido);
		return pecaPedido;
	}

	@Override
	public void removeByPedido(Pedido pedido) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "delete from PecaPedido where pedido = :pedido";
        Query query = session.createQuery(hql)
        		.setParameter("pedido", pedido);
        
        query.executeUpdate();

		logger.info("PecaPedido apagado com sucesso!");
	}

}
