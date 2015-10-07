package com.biavan.brend.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.ConfigEmpresaDAO;
import com.biavan.brend.dao.RegistroContatoDAO;
import com.biavan.brend.dao.StatusOrdemServicoDAO;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.RegistroContato;
import com.biavan.brend.model.StatusOrdemServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;
import com.biavan.brend.plugin.mail.Mailable;
import com.biavan.brend.plugin.mail.MessageProperty;
import com.biavan.brend.plugin.mail.SendMailSSL;

@Service
public class RegistroContatoServiceImpl implements RegistroContatoService {

	private static final Logger logger = LoggerFactory
			.getLogger(RegistroContatoServiceImpl.class);

	
	private RegistroContatoDAO registroContatoDAO;
	private StatusOrdemServicoDAO statusOrdemServicoDAO;
	private ConfigEmpresaDAO configEmpresaDAO;

	public void setRegistroContatoDAO(RegistroContatoDAO registroContatoDAO) {
		this.registroContatoDAO = registroContatoDAO;
	}

	public void setStatusOrdemServicoDAO(StatusOrdemServicoDAO statusOrdemServicoDAO) {
		this.statusOrdemServicoDAO = statusOrdemServicoDAO;
	}
	
	public void setConfigEmpresaDAO(ConfigEmpresaDAO configEmpresaDAO) {
		this.configEmpresaDAO = configEmpresaDAO;
	}
	
	@Override
	@Transactional
	public String insereRegistroContato(RegistroContato registroContato) {
		registroContatoDAO.insere(registroContato);
		
		try {
			MessageProperty messageProperty = new MessageProperty();
			messageProperty.setFrom(configEmpresaDAO.getById(1).getEmail());
			messageProperty.setSubject("Follow-up da OS #" + registroContato.getOrdemServico().getId());
			messageProperty.setMessage("Novas interações na OS #"
					+ registroContato.getOrdemServico().getId()
					+ " apresentando a mensagem a seguir: \n"
					+ registroContato.getDescricao()
					+ "\n\n"
					+ registroContato.getVistoriaFormatada());
			messageProperty.addRecipient(configEmpresaDAO.getById(1).getEmail());
			messageProperty.addRecipient(registroContato.getOrdemServico().getCliente().getEmail());
			
			SendMailSSL.send(messageProperty, configEmpresaDAO.getById(1));
			return "E-mail enviado!";
		} catch(Exception e) {
			logger.info("Erro ao enviar e-mail.");
			e.printStackTrace();
		}
		
		return "Erro ao envio e-mail.";
	}

	@Override
	@Transactional
	public String insereRegistroContato(RegistroContato registroContato, StatusOrdemServico statusOrdemServico) {
		registroContatoDAO.insere(registroContato);

		StatusOrdemServico statusOrdemServicoAtual = Collections
				.max(statusOrdemServicoDAO
						.listaByOrdemServico(statusOrdemServico
								.getOrdemServico()));
		
		statusOrdemServicoAtual.setDataFim(new Date());
		statusOrdemServicoDAO.atualiza(statusOrdemServicoAtual);
		
		statusOrdemServicoDAO.insere(statusOrdemServico);
		
		try {
			MessageProperty messageProperty = new MessageProperty();
			messageProperty.setFrom(configEmpresaDAO.getById(1).getEmail());
			messageProperty.setSubject("Follow-up da OS #" + registroContato.getOrdemServico().getId());
			messageProperty.setMessage("O status da OS #"
					+ registroContato.getOrdemServico().getId()
					+ " foi atualizado com a mensagem a seguir: \n"
					+ registroContato.getDescricao()
					+ "\n\n"
					+ registroContato.getVistoriaFormatada());
			messageProperty.addRecipient(configEmpresaDAO.getById(1).getEmail());
			messageProperty.addRecipient(registroContato.getOrdemServico().getCliente().getEmail());
		
			SendMailSSL.send(messageProperty, configEmpresaDAO.getById(1));
			return "E-mail enviado!";
		} catch(Exception e) {
			logger.info("Erro ao enviar e-mail.");
			e.printStackTrace();
		}
		
		return "Erro ao enviar e-mail.";
		
	}
	
	@Override
	@Transactional
	public List<RegistroContato> listaRegistroContatosByOrdemServico(
			OrdemServico ordemServico) {
		return registroContatoDAO.listaByOrdemServico(ordemServico);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<RegistroContato> listaRegistroContatosBootgridByOrdemServico(
			OrdemServico ordemServico, BootgridParam param) {
		
	    param.setSortColumn(param.getSortColumn().replace("dataFormatada", "data"));
		
		BootgridData<RegistroContato> bootgridData = registroContatoDAO
				.listaToBootgridByOrdemServico(ordemServico, param);
		
		for(RegistroContato contato : bootgridData.getRows()) {
			contato.setOrdemServico(null);
		}
		
		return bootgridData;
	}

	@Override
	@Transactional
	public RegistroContato getRegistroContatoById(long id) {
		return registroContatoDAO.getById(id);
	}

}
