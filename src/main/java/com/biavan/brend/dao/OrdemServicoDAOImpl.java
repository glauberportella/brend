package com.biavan.brend.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.biavan.brend.enums.StatusOS;
import com.biavan.brend.model.Funcionario;
import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public class OrdemServicoDAOImpl implements OrdemServicoDAO {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void insere(OrdemServico ordemServico) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(ordemServico);
		
	}

	@Override
	public void atualiza(OrdemServico ordemServico) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(ordemServico);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<OrdemServico> listaToBootgrid(BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		StringBuilder hql = new StringBuilder();
		
		hql.append("from OrdemServico o "
				+ "inner join o.historico historico "
				+ "where o.ordemServicoPai is null "
				+ "and o.veiculo like :veiculo "
				+ "and historico.dataFim = '3000-01-01' ");

		if (param.getExtras() != null) {
		    Set<Entry<String, String>> set = param.getExtras().entrySet();
		    Iterator<Entry<String, String>> i = set.iterator();
		    while(i.hasNext()) {
		    	@SuppressWarnings("rawtypes")
				Map.Entry me = (Map.Entry) i.next();
		    	if ("statusOS".equals(me.getKey().toString())) {
		    		hql.append("and historico." + me.getKey() + " = :" + me.getKey() + " ");
		    	}
		    	if ("mecanico".equals(me.getKey().toString())) {
		    		hql.append("and o." + me.getKey() + " = :" + me.getKey() + " ");
		    	}
		    }
		}
		
		hql.append("order by o." + param.getSortColumn() + " " + param.getSortType());

		Query queryA = session.createQuery(hql.toString())
				.setString("veiculo", "%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql.toString())
				.setString("veiculo", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		if (param.getExtras() != null) {
			Set<Entry<String, String>> set = param.getExtras().entrySet();
		    Iterator<Entry<String, String>> i = set.iterator();
		    while(i.hasNext()) {
		    	@SuppressWarnings("rawtypes")
				Map.Entry me = (Map.Entry) i.next();
		    	if ("statusOS".equals(me.getKey().toString())) {
		    		queryA.setParameter(me.getKey().toString(),
		    				StatusOS.getStatusByText(me.getValue().toString()));
		    		queryB.setParameter(me.getKey().toString(),
		    				StatusOS.getStatusByText(me.getValue().toString()));
		    	} else if ("mecanico".equals(me.getKey().toString())) {
		    		queryA.setLong(me.getKey().toString(), Long.parseLong(me.getValue().toString()));
		    		queryB.setLong(me.getKey().toString(), Long.parseLong(me.getValue().toString()));
		    	} else {
		    		queryA.setString(me.getKey().toString(), me.getValue().toString());
		    		queryB.setString(me.getKey().toString(), me.getValue().toString());
		    	}
		    }
		}
		
		BootgridData<OrdemServico> dados = new BootgridData<OrdemServico>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		
		List<Object[]> result = queryB.list();
		List<OrdemServico> lista = new ArrayList<OrdemServico>();	

		for(Object[] os : result) {
			lista.add((OrdemServico) os[0]);
		}
		
		dados.setRows(lista);

		return dados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<OrdemServico> listaAdicionalToBootgrid(OrdemServico ordemServicoPai, 
			BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from OrdemServico o "
				+ "where o.ordemServicoPai = :ordemServicoPai "
				+ "and o.veiculo like :veiculo order by "
				+ "o." + param.getSortColumn() + " " + param.getSortType();

		Query queryA = session.createQuery(hql)
				.setParameter("ordemServicoPai", ordemServicoPai)
				.setString("veiculo", "%" + param.getSearchPhrase() + "%");

		Query queryB = session.createQuery(hql)
				.setParameter("ordemServicoPai", ordemServicoPai)
				.setString("veiculo", "%" + param.getSearchPhrase() + "%")
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<OrdemServico> dados = new BootgridData<OrdemServico>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryA.list().size());
		dados.setRows((List<OrdemServico>) queryB.list());

		return dados;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public BootgridData<OrdemServico> listaToBootgrid(String palavra, BootgridParam param) {
		Session session = this.sessionFactory.getCurrentSession();

		String hql = "from OrdemServico o "
				+ "where o.ordemServicoPai is null "
				+ "and o.id = :id "
				+ "order by "
				+ "o." + param.getSortColumn() + " " + param.getSortType();

		Query queryQtde = session.createQuery(hql)
				.setLong("id", Long.parseLong(palavra));

		Query query = session.createQuery(hql)
				.setLong("id", Long.parseLong(palavra))
				.setFirstResult(param.getFirst())
				.setMaxResults(param.getRowCount());

		BootgridData<OrdemServico> dados = new BootgridData<OrdemServico>();
		dados.setCurrent(param.getCurrent());
		dados.setRowCount(param.getRowCount());
		dados.setTotal(queryQtde.list().size());
		dados.setRows((List<OrdemServico>) query.list());

		return dados;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServico> lista() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<OrdemServico> listaDeOrdemServico = session.createQuery(
				"from OrdemServico").list();
		return listaDeOrdemServico;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServico> listaByMecanico(Funcionario funcionario) {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<OrdemServico> listaDeOrdemServico = session.createQuery(
				"from OrdemServico where mecanico = :mecanico")
				.setParameter("mecanico", funcionario).list();
		return listaDeOrdemServico;
	}

	
	@Override
	public OrdemServico getById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		OrdemServico ordemServico = (OrdemServico) session.load(OrdemServico.class,
				new Long(id));
		return ordemServico;
	}

	@Override
	public void remove(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		OrdemServico ordemServico = (OrdemServico) session.load(OrdemServico.class,
				new Long(id));
		if (null != ordemServico) {
			session.delete(ordemServico);
		}
		
	}

	@Override
	public OrdemServico getOrdemServicoByOrcamento(Orcamento orcamento) {
		Session session = this.sessionFactory.getCurrentSession();
		return (OrdemServico) session.createQuery("from OrdemServico where orcamento = :orcamento")
			.setParameter("orcamento", orcamento)
			.uniqueResult();
	}

	@Override
	public Double valorTotalOrdemServicoAdicionais(OrdemServico ordemServico) {
		Session session = this.sessionFactory.getCurrentSession();
		
		return (Double) session.createQuery("select sum(valorTotal) "
				+ "from OrdemServico "
				+ "where ordemServicoPai = :ordemServicoPai ")
				.setParameter("ordemServicoPai", ordemServico)
				.uniqueResult();
		
	}
}

