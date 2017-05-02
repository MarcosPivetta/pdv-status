package br.com.centauro.loja.pdvstatus.model;

public class PdvStatus {

	/**
	 * Identificador único do Zabbix
	 */
	private String idZabbixPdv;

	/**
	 * Endereço IP da LAN Tamanho: 15 Ex.: 192.168.010.100
	 */
	private String ip;

	/**
	 * Hostname da estação Ex.: I90
	 */
	private String hostName;

	public PdvStatus() {
		
	}
	
	public PdvStatus(String zabbixId, String ip, String hostName) {
		super();
		this.idZabbixPdv = zabbixId;
		this.ip = ip;
		this.hostName = hostName;
	}

	public String getIdZabbixPdv() {
		return idZabbixPdv;
	}

	public void setIdZabbixPdv(String idZabbixPdv) {
		this.idZabbixPdv = idZabbixPdv;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
}
