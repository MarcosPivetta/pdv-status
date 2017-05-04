package br.com.centauro.loja.pdvstatus;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.centauro.loja.pdvstatus.model.PdvStatus;
import br.com.centauro.loja.pdvstatus.type.TipoPdvEnum;
import br.com.centauro.loja.pdvstatus.util.NetworkUtil;
import br.com.centauro.loja.pdvstatus.util.SispacUtil;
import br.com.centauro.loja.pdvstatus.util.TaurosUtil;
import br.com.centauro.loja.pdvstatus.zabbix.ZabbixUtil;

/**
 * Classe principal, com responsabilidade de consultar as informações do PDV e
 * enviar para o Zabbix
 * 
 * @author juliano.freitas
 *
 */
public class PdvAgent {
	private static Logger LOGGER = LoggerFactory.getLogger(PdvAgent.class);

	public static final Properties APPLICATION;

	static {
		APPLICATION = new Properties();

		try {
			InputStream is = new PdvAgent().getClass()
					.getResourceAsStream("/META-INF/maven/br.com.centauro.loja/pdv-status/pom.properties");

			if (is != null) {
				APPLICATION.load(is);
			}

			is.close();
		} catch (FileNotFoundException e) {
			LOGGER.error("Arquivo nao encontrado [application.properties]", e);
			APPLICATION.clear();
		} catch (Exception e) {
			LOGGER.error("Exception", e);
			APPLICATION.clear();
		}
	}

	/**
	 * Metodo main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LOGGER.info("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
		LOGGER.info("Inicializando...");

		LOGGER.info("groupId: " + APPLICATION.getProperty("groupId"));
		LOGGER.info("artifactId: " + APPLICATION.getProperty("artifactId")+"\tversion: " + APPLICATION.getProperty("version"));
		
		// Obter as informações do PDV
		String idZabbixPdv = ZabbixUtil.getIdZabbixPdv();
		if(idZabbixPdv == null) {
			LOGGER.error("Falha ao obter o Hostname do arquivo do Zabbix!");
			LOGGER.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.exit(1);
		}
		
		String ip = NetworkUtil.getIp();// IP
		String hostName = NetworkUtil.getHostName(); // HostName
		TipoPdvEnum tipoPdvEnum = TaurosUtil.getTipoTauros();
		
		// Código da Loja
		String codLoja = null;
		// Numero da loja
		String numPdv = null;
		// Sistema Operacional
		String sistemaOp = null;
		if(idZabbixPdv != null && !"".equals(idZabbixPdv)) {
			String[] id = idZabbixPdv.split("-");
			codLoja = id[0];
			numPdv = id[1];
			sistemaOp = id[2];
		}
		
		// Popular o objeto PdvStatus com as informações para enviar ao Zabbix
		PdvStatus pdvStatus = new PdvStatus();
		pdvStatus.setIdZabbixPdv(idZabbixPdv);
		pdvStatus.setIp(ip);
		pdvStatus.setHostName(hostName);
		pdvStatus.setCodLoja(codLoja);
		pdvStatus.setNumPdv(numPdv);
		pdvStatus.setSistemaOp(sistemaOp);

		// Versão do PDV Status
		pdvStatus.setVersaoPdvStatus(APPLICATION.getProperty("version"));

		// Versão do Tauros
		pdvStatus.setVersaoTauros(TaurosUtil.getVersaoTauros());

		// Versão do Sispac
		pdvStatus.setVersaoSispac(SispacUtil.getVersaoSispac());

		// Tipo PDV Tauros (SP ou ESTACAO)
		pdvStatus.setTipoPdvTauros(tipoPdvEnum);

		// Tipo PDV Sispac (PRINCIPAL ou ESTACAO)
		if(SispacUtil.isSispacPrincipal()) {
			pdvStatus.setTipoPdvSispac(TipoPdvEnum.PRINCIPAL);
		} else if(SispacUtil.isSispacEstacao()) {
			pdvStatus.setTipoPdvSispac(TipoPdvEnum.ESTACAO);
		}
		
		// Hora da obtenção do status
		Date horaAtual = new Date(System.currentTimeMillis());
		pdvStatus.setHoraAtual(horaAtual);
		
		// Enviar o status do pdv para o zabbix (usar classe ZabbixUtil
		ZabbixUtil.saveZabbixFile(pdvStatus);
		
		LOGGER.info("Fim!");
		LOGGER.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
	}
}
