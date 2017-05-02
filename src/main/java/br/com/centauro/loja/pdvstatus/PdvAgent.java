package br.com.centauro.loja.pdvstatus;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe principal, com responsabilidade de consultar as informações do PDV e
 * enviar para o Zabbix
 * 
 * @author juliano.freitas
 *
 */
public class PdvAgent {

	private static Logger LOGGER = LoggerFactory.getLogger(PdvAgent.class);

	private static final SimpleDateFormat SDF_YYMMDDHHMMSSSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
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

		LOGGER.info("Inicializando...");

		LOGGER.info("groupId: " + APPLICATION.getProperty("groupId"));
		LOGGER.info("artifactId: " + APPLICATION.getProperty("artifactId"));
		LOGGER.info("version: " + APPLICATION.getProperty("version"));

		testNetwork();
		hostname();
		// Obter as informações do PDV
		// IP
		// Hostname
		// Código da Loja
		// Cidade
		// Estado
		// Hora da obtenção do status

		// Verificar se é principal, ou se é estação (Tauros SP ou pdv normal)

		// Se for P2K (Taurus), obter a versão e a data de atualização
		/*
		 * if(isTaurus()) { obter versão obter data de atualização } else { //É
		 * sispac // nem sei o que colocar }
		 */

		// Popular o objeto PdvStatus com as informações para enviar ao Zabbix

		// Enviar o status do pdv para o zabbix (usar classe ZabbixUtil
	}
	
	public static void testNetwork() {
		try {
			System.out.println("Your Host addr: " + InetAddress.getLocalHost().getHostAddress());

			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			for (; networkInterfaces.hasMoreElements();) {
				NetworkInterface e = networkInterfaces.nextElement();

				Enumeration<InetAddress> inetAddresses = e.getInetAddresses();
				for (; inetAddresses.hasMoreElements();) {
					InetAddress addr = inetAddresses.nextElement();
					System.out.println(e.getDisplayName() + "\t" + addr.getHostAddress());
				}
			}

			String hostname = "";
			try {
				hostname = InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e) {
				// failed; try alternate means.
			}

			// try environment properties.
			//
			String host = System.getenv("COMPUTERNAME");
			if (host != null) {
				hostname = host;
			}
			host = System.getenv("HOSTNAME");
			if (host != null) {
				hostname = host;
			}

			// undetermined.
			System.out.println(hostname);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static String hostname() {
			String hostname = "";
			try {
				hostname = InetAddress.getLocalHost().getHostName();
				System.out.println(hostname);
			} catch (UnknownHostException e) {
				// failed; try alternate means.
			}
			return hostname;
	}
}
