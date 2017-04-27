package br.com.centauro.loja.pdvstatus.model;

public class PdvStatus {

	/**
	 * Endereço IP da LAN Tamanho: 15 Ex.: 192.168.010.100
	 */
	private String Ip;

	/**
	 * Hostname da estação Ex.: I90
	 */
	private String hostaname;

	public PdvStatus(String Ip, String hostaname) {
		super();
		this.Ip = Ip;
		this.hostaname = hostaname;
	}

	public String getIp() {
		return Ip;
	}

	public void setIp(String ip) {
		Ip = ip;
	}

	public String getHostaname() {
		return hostaname;
	}

	public void setHostaname(String hostaname) {
		this.hostaname = hostaname;
	}
	
	@Override
	public String toString() {
		return "PdvStatus [Ip=" + Ip + ", hostaname=" + hostaname + "]";
	}
}
