package br.com.centauro.loja.pdvstatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.centauro.loja.pdvstatus.model.PdvStatus;
import br.com.centauro.loja.pdvstatus.util.RegexUtil;
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

	private static final SimpleDateFormat SDF_YYMMDDHHMMSSSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	public static final Properties APPLICATION;
	
	private static final String ZABBIX_FILE = "/etc/zabbix/zabbix-agentd.conf";
	private static final String ZABBIX_HOSTNAME = "Hostname=";
	
	private static final String PATH_TAUROS_SP = "/p2ksp";
	private static final String PATH_TAUROS_ESTACAO = "/p2k";

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
		
		// Obter as informações do PDV
		String idZabbixPdv = getIdZabbixPdv();
		String ip = getIp();// IP
		String hostName = getHostName(); // Hostname

		// Hora da obtenção do status
		Date horaAtual = new Date(System.currentTimeMillis());
		String strDataHora = SDF_YYMMDDHHMMSSSSS.format(horaAtual);
		
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
		
		// Cidade
		// Estado

		// Verificar se é Tauros
				
		// Verificar se é principal, ou se é estação (Tauros SP ou pdv normal)

		// Se for Tauros (Tauros), obter a versão e a data de atualização
		/*
		 * if(isTauros()) { obter versão obter data de atualização } else { //É
		 * sispac // nem sei o que colocar }
		 */
		// Versão do PDV Status
System.out.println(isTaurosSP());
		// Popular o objeto PdvStatus com as informações para enviar ao Zabbix
		PdvStatus pdvStatus = new PdvStatus();
		pdvStatus.setIdZabbixPdv(idZabbixPdv);// Adicionando o Prefixo PDV ao ID
		pdvStatus.setIp(ip);
		pdvStatus.setHostName(hostName);
		pdvStatus.setCodLoja(codLoja);
		pdvStatus.setNumPdv(numPdv);
		pdvStatus.setSistemaOp(sistemaOp);
		pdvStatus.setVersaoPdvStatus(APPLICATION.getProperty("version"));
		//Tipo PDV Tauros (SP ou ESTACAO)
		//Versão do Tauros
		//Tipo PDV Sispac (PRINCIPAL ou ESTACAO)
		//Versão do Sispac
		pdvStatus.setHoraAtual(horaAtual);
		
		// Enviar o status do pdv para o zabbix (usar classe ZabbixUtil
		ZabbixUtil.saveZabbixFile(pdvStatus);
	}

	/**
	 * @deprecated Remover esse método
	 */
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

	/**
	 * Obtém o idZabbixPdv do arquivo de configuração do Zabbix 
	 * @return o id obtido, ou null em caso de erro ao abrir o arquivo
	 */
	public static String getIdZabbixPdv() {
		String idZabbixPdv = null;
		
		try {
			File fileZabbix = new File(ZABBIX_FILE);

			if(fileZabbix.exists()){
				LOGGER.debug("O arquivo " + ZABBIX_FILE + " existe!");
				// Verificar se consegue ler
				if(fileZabbix.canRead()) {

					Scanner scanner = new Scanner(fileZabbix);
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						if(line.startsWith(ZABBIX_HOSTNAME)) {
							// Achou a linha que começa com Hostname=
							String[] lineSplit = line.split("=");
							idZabbixPdv = lineSplit[1];
							break;
						}
					}
					scanner.close();
				} else {
					LOGGER.error("Não é possível ler o arquivo: " + ZABBIX_FILE);
				}
//				FileInputStream fis = new FileInputStream(fileZabbix);
//				fis.
			} else {
				LOGGER.error("O arquivo " + ZABBIX_FILE + " NÃO EXISTE!");
			}
			
			
			// Se abriu, procurar pela chave "Hostname"
		} catch(Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		LOGGER.debug("idZabbixPdv: " + idZabbixPdv);
		return idZabbixPdv;
	}
	
	/**
	 * Obtém o hostName da máquina
	 * @return hostName da máquina, ou null em caso de falha
	 */
	public static String getHostName() {
		String hostName = "";
		try {
			hostName = InetAddress.getLocalHost().getHostName();
			LOGGER.info("HostName: " + hostName);
		} catch (UnknownHostException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return hostName;
	}

	/**
	 * Obtém o IP da interface de rede ethernet
	 * @return o IP obtido, ou null em caso de falha
	 */
	public static String getIp() {
		String ip = null;

		try {
			Enumeration<?> nis = NetworkInterface.getNetworkInterfaces();

			while (nis.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) nis.nextElement();

				boolean achouIpValido = false;
				
				// Valida se a interface é ethernet
				LOGGER.debug("Validando interface: " + ni.getName());
				if (RegexUtil.isEthernetInterface(ni.getName())) {
					LOGGER.debug("Interface " + ni.getName() + " válida!");
					Enumeration<?> ias = ni.getInetAddresses();
					while (ias.hasMoreElements()) {
						InetAddress ia = (InetAddress) ias.nextElement();
						
						// Verifica se o IP obtido é válido
						LOGGER.debug("Validando ip: " + ia.getHostAddress());
						if (RegexUtil.isValidIp(ia.getHostAddress())) {
							// É válido, armazena da variável ip e para o loop
							ip = ia.getHostAddress();
							LOGGER.info("IP válido: " + ip);
							achouIpValido = true;
							break;
						}
					}
				}
				
				// Verifica se achou ip válido
				if(achouIpValido) {
					// Se achou, para o while de networkInterfaces
					break;
				}
			}
		} catch (SocketException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return ip;
	}
	
	public static boolean isTaurosSP(){
		boolean isTaurosSP = false;		
		
		try {
			File taurosSp = new File(PATH_TAUROS_SP);
			if (taurosSp.isDirectory()) {	
				LOGGER.debug("O diretório " + PATH_TAUROS_SP + " existe!");
				isTaurosSP = true;
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		LOGGER.info("isTaurosSP: " + isTaurosSP);
		return isTaurosSP;	
	}
	
}
