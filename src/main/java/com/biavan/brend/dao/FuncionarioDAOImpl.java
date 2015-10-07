package com.biavan.brend.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biavan.brend.model.Funcionario;
import com.biavan.brend.model.Usuario;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class FuncionarioDAOImpl implements FuncionarioDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(FuncionarioDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(Funcionario funcionario) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(funcionario);
		logger.info("Funcionario salvo com sucesso! | " + funcionario);

	}

	@Override
	public void atualiza(Funcionario funcionario) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(funcionario);
		logger.info("Funcionario atualizado com sucesso! |" + funcionario);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Funcionario> listaDeFuncionario = session.createQuery(
				"from Funcionario where email <> 'admin@omservicos.com.br' ").list();
		return listaDeFuncionario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> listaMecanicos() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Object[]> result = session.createQuery(
				"from Funcionario f "
				+ "inner join f.tipoFuncionario tipoFuncionario "
				+ "where tipoFuncionario.mecanico = true ").list();
		
		List<Funcionario> listaDeFuncionario = new ArrayList<Funcionario>();	

		for(Object[] os : result) {
			listaDeFuncionario.add((Funcionario) os[0]);
		}
		
		return listaDeFuncionario;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<Funcionario> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from Funcionario f where email <> 'admin@omservicos.com.br' "
				+ "and f.nome like :nome order by "
				+ param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql).setString("nome",
				"%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setString("nome", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<Funcionario> dados = new BootgridData<Funcionario>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<Funcionario>) queryB.list());

		return dados;
	}

	@Override
	public Funcionario getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Funcionario funcionario = (Funcionario) session.load(Funcionario.class,
				new Long(id));
		logger.info("Funcionario carregado com sucesso! | " + funcionario);
		return funcionario;
	}

	@Override
	public Funcionario getByLogin(String login) {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
 
		usuarios = session.createQuery("from Usuario where login = :login")
			.setString("login", login).list();
 
		if (usuarios.size() <= 0)
			return null;
		
		return usuarios.get(0).getFuncionario();
	}
	
	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Funcionario funcionario = (Funcionario) session.load(Funcionario.class,
				new Long(id));
		if (null != funcionario) {
			session.delete(funcionario);
		}
		logger.info("Funcionario apagado com sucesso! | " + funcionario);

	}

}
