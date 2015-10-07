package com.biavan.brend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.Cliente;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class ClienteDAOImpl implements ClienteDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(ClienteDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(Cliente cliente) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(cliente);
		logger.info("Cliente salvo com sucesso! | " + cliente);

	}

	@Override
	public void atualiza(Cliente cliente) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(cliente);
		logger.info("Cliente atualizado com sucesso! |" + cliente);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Cliente> listaDeCliente = session.createQuery(
				"from Cliente").list();
		return listaDeCliente;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<Cliente> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from Cliente f where f.razao like :busca order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("busca",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("busca", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<Cliente> dados = new BootgridData<Cliente>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<Cliente>) queryB.list());

		return dados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<Cliente> listaToBootgrid(String palavra, BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from Cliente f "
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

		BootgridData<Cliente> dados = new BootgridData<Cliente>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryQtde.list().size());
		dados.setRows((List<Cliente>) query.list());

		return dados;
	}
	
	@Override
	public Cliente getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Cliente cliente = (Cliente) session.load(Cliente.class,
				new Long(id));
		logger.info("Cliente carregado com sucesso! | " + cliente);
		return cliente;
	}

	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Cliente cliente = (Cliente) session.load(Cliente.class,
				new Long(id));
		if (null != cliente) {
			session.delete(cliente);
		}
		logger.info("Cliente apagado com sucesso! | " + cliente);

	}

}
