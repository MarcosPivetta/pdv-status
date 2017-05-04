package br.com.centauro.loja.pdvstatus.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkUtil {
	private static Logger LOGGER = LoggerFactory.getLogger(NetworkUtil.class);

	/**
	 * Obtém o hostName da máquina
	 * 
	 * @return hostName da máquina, ou null em caso de falha
	 */
	public static String getHostName() {
		String hostName = null;
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			hostName = "ERRO";
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("HostName: " + hostName);
		return hostName;
	}

	/**
	 * Obtém o IP da interface de rede ethernet
	 * 
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
							LOGGER.debug("IP válido: " + ip);
							achouIpValido = true;
							break;
						}
					}
				}

				// Verifica se achou ip válido
				if (achouIpValido) {
					// Se achou, para o while de networkInterfaces
					break;
				}
			}
		} catch (SocketException e) {
			ip = "ERRO";
			LOGGER.error(e.getMessage(), e);
		}

		LOGGER.info("IP: " + ip);
		return ip;
	}
}
