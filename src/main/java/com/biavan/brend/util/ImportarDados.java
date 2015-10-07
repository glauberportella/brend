package com.biavan.brend.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.biavan.brend.enums.TipoPessoa;
import com.biavan.brend.model.Cliente;
import com.biavan.brend.model.Fornecedor;
import com.biavan.brend.model.Funcionario;
import com.biavan.brend.model.Marca;
import com.biavan.brend.model.Peca;
import com.biavan.brend.model.Servico;
import com.biavan.brend.model.TarifarioPeca;
import com.biavan.brend.model.TarifarioServico;
import com.biavan.brend.model.TipoDocumento;
import com.biavan.brend.model.TipoFuncionario;
import com.biavan.brend.model.TipoMotor;
import com.biavan.brend.model.TipoServico;
import com.biavan.brend.model.Veiculo;

public class ImportarDados {

	public static void importar() {

		pecas();
		tipoMotor();
		tipoFuncionario();
		tipoDocumento();
		veiculos();
		funcionarios();
		clientes();
		fornecedores();

	}

	private static void veiculos() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();

		List<String> marcas = new ArrayList<String>();
		marcas.add("Chevrolet");
		marcas.add("Fiat");
		marcas.add("Ford");
		marcas.add("Honda");
		marcas.add("Hyundai");
		marcas.add("Peugeot");
		marcas.add("Renault");
		marcas.add("Toyota");
		marcas.add("Volkswagen");

		String modelo = "";
		int count = 0;
		for (String marca : marcas) {
			for (String linha : getVeiculos(marca)) {
				if (count == 0) {
					modelo = linha;
					count++;
				} else {
					if ("#".equals(linha)) {
						modelo = "";
						count = 0;
					} else {
						Veiculo veiculo = new Veiculo();
						veiculo.setMarca(marca);
						veiculo.setModelo(modelo + " - " + linha);
						session.saveOrUpdate(veiculo);
						count++;
					}
				}
			}
		}

		session.getTransaction().commit();
		System.out.println("Tipos de veículos importados!");
	}

	private static void tipoDocumento() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();

		TipoDocumento t1 = new TipoDocumento();
		t1.setNome("CPF");
		session.saveOrUpdate(t1);

		TipoDocumento t2 = new TipoDocumento();
		t2.setNome("RG");
		session.saveOrUpdate(t2);

		TipoDocumento t3 = new TipoDocumento();
		t3.setNome("CNH");
		session.saveOrUpdate(t3);

		session.getTransaction().commit();
		System.out.println("Tipos de documentos criados!");
	}

	private static void tipoFuncionario() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();

		TipoFuncionario t1 = new TipoFuncionario();
		t1.setNome("Gerente");
		session.saveOrUpdate(t1);

		TipoFuncionario t2 = new TipoFuncionario();
		t2.setNome("Mecânico");
		t2.setMecanico(true);
		session.saveOrUpdate(t2);

		TipoFuncionario t3 = new TipoFuncionario();
		t3.setNome("Atendente");
		session.saveOrUpdate(t3);

		TipoFuncionario t4 = new TipoFuncionario();
		t4.setNome("Administrador");
		session.saveOrUpdate(t4);

		TipoFuncionario t5 = new TipoFuncionario();
		t5.setNome("Almoxarife");
		session.saveOrUpdate(t5);

		session.getTransaction().commit();
		System.out.println("Tipos de funcionarios criados!");
	}

	private static void tipoMotor() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();

		TipoMotor t1 = new TipoMotor();
		t1.setNome("Gasolina");
		session.saveOrUpdate(t1);

		TipoMotor t2 = new TipoMotor();
		t2.setNome("Alcool");
		session.saveOrUpdate(t2);

		TipoMotor t3 = new TipoMotor();
		t3.setNome("Gás");
		session.saveOrUpdate(t3);

		TipoMotor t4 = new TipoMotor();
		t4.setNome("Flex");
		session.saveOrUpdate(t4);

		session.getTransaction().commit();
		System.out.println("Tipo de motores criados!");
	}

	private static void pecas() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();

		for (String linha : getPecasFromCSV()) {
			String[] item = linha.split(";");

			String hqlMarca = "from Marca m where m.nome = :nome ";
			Query queryMarca = session.createQuery(hqlMarca).setString("nome",
					item[2]);
			Marca marca = (Marca) queryMarca.uniqueResult();

			if (marca == null || marca.getId() == 0) {
				marca = new Marca();
				marca.setNome(item[2]);
				session.saveOrUpdate(marca);
			}

			Peca peca = new Peca();
			peca.setNome(item[0]);
			peca.setDescricao(item[1] + " - " + item[3]);
			peca.setQuantidade(50);
			peca.setMarca(marca);
			session.saveOrUpdate(peca);

			TarifarioPeca tarifarioPeca = new TarifarioPeca();
			tarifarioPeca.setData(new Date());
			tarifarioPeca.setValor(Double.parseDouble(item[4]));
			tarifarioPeca.setMarkup(25);
			tarifarioPeca.setValorVenda((tarifarioPeca.getValor() * (25 / 100))
					+ tarifarioPeca.getValor());
			tarifarioPeca.setPeca(peca);
			session.saveOrUpdate(tarifarioPeca);

			String hqlTipoServico = "from TipoServico m where m.nome = :nome ";
			Query queryTipoServico = session.createQuery(hqlTipoServico)
					.setString("nome", "Troca de peça");
			TipoServico tipoServico = (TipoServico) queryTipoServico
					.uniqueResult();

			if (tipoServico == null || tipoServico.getId() == 0) {
				tipoServico = new TipoServico();
				tipoServico.setNome("Troca de peça");
				session.saveOrUpdate(tipoServico);
			}

			Servico servico = new Servico();
			servico.setNome(item[5]);
			servico.setDescricao(item[5]);
			servico.setTipoServico(tipoServico);
			session.saveOrUpdate(servico);

			TarifarioServico tarifarioServico = new TarifarioServico();
			tarifarioServico.setData(new Date());
			tarifarioServico.setServico(servico);
			tarifarioServico.setValor(Double.parseDouble(item[6]));
			session.saveOrUpdate(tarifarioServico);

		}

		session.getTransaction().commit();
		System.out.println("Peças importadas!");
	}

	private static void funcionarios() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		
		for(String linha : getFuncionariosFromCSV()) {
			String[] item = linha.split(";");

			Funcionario funcionario = new Funcionario();
			funcionario.setNome(item[0]);
			funcionario.setCpf(item[1]);
			try {
				funcionario.setNascimento(Formatos.getFormatoDeData().parse(item[2]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			funcionario.setEndereco(item[3]);
			funcionario.setEmail(item[4]);
			funcionario.setTelefone(item[5]);
			
			String hql = "from TipoFuncionario m where m.nome = :nome ";
			Query query = session.createQuery(hql).setString("nome", item[6]);
			funcionario.setTipoFuncionario((TipoFuncionario) query.uniqueResult());
			
			session.saveOrUpdate(funcionario);
			
		}
		
		session.getTransaction().commit();
		System.out.println("Funcionarios importados!");
	}

	private static void clientes() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		
		for(String linha : getClientesFromCSV()) {
			String[] item = linha.split(";");

			Cliente cliente = new Cliente();
			cliente.setRazao(item[0]);
			cliente.setFantasia(item[1]);
			cliente.setNumeroDocumento(item[2]);
			try {
				cliente.setNascimento(Formatos.getFormatoDeData().parse(item[3]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cliente.setEndereco(item[4]);
			cliente.setTelefone(item[5]);
			cliente.setEmail(item[6]);
			
			if ("física".equals(item[7]))
				cliente.setTipoPessoa(TipoPessoa.Fisica);
			else
				cliente.setTipoPessoa(TipoPessoa.Juridica);
			
			String hql = "from TipoDocumento m where m.nome = :nome ";
			Query query = session.createQuery(hql).setString("nome", item[8]);
			cliente.setTipoDocumento((TipoDocumento) query.uniqueResult());
			
			session.saveOrUpdate(cliente);
			
		}
		
		session.getTransaction().commit();
		System.out.println("Funcionarios importados!");

	}

	private static void fornecedores() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		
		for(String linha : getFornecedoresFromCSV()) {
			String[] item = linha.split(";");

			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setRazao(item[0]);
			fornecedor.setFantasia(item[1]);
			fornecedor.setCnpj(item[2]);
			fornecedor.setEndereco(item[3]);
			fornecedor.setTelefone(item[4]);
			fornecedor.setEmail(item[5]);
			
			session.saveOrUpdate(fornecedor);
			
		}
		
		session.getTransaction().commit();
		System.out.println("Funcionarios importados!");
	}

	private static List<String> getPecasFromCSV() {
		return Arquivo
				.lerArquivo("src/main/resources/lista-de-pecas.csv", true);
	}

	private static List<String> getFuncionariosFromCSV() {
		return Arquivo.lerArquivo(
				"src/main/resources/lista-de-funcionarios.csv", false);
	}

	private static List<String> getFornecedoresFromCSV() {
		return Arquivo.lerArquivo(
				"src/main/resources/lista-de-fornecedores.csv", false);
	}

	private static List<String> getClientesFromCSV() {
		return Arquivo.lerArquivo("src/main/resources/lista-de-clientes.csv", false);
	}

	private static List<String> getVeiculos(String modelo) {
		return Arquivo.lerArquivo("src/main/resources/veiculos/" + modelo
				+ ".txt", false);
	}
}
