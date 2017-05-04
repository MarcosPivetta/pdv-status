package br.com.centauro.loja.pdvstatus.model;

import java.util.Date;

import br.com.centauro.loja.pdvstatus.type.TipoPdvEnum;

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
	
	/**
	 * Código da Loja Ex.: CE71
	 */
	private String codLoja;
	
	/**
	 * Número do PDV Ex.: 
	 */
	private String numPdv;
	
	/**
	 * Sistema Operacional da estação Ex.: SUSE 
	 */
	private String sistemaOp;
	
	/**
	 * Hora extração dos dados  Ex.: YYMMDDHHMMSSSSS 
	 */
	private Date horaAtual;

	/**
	 * Versão do PDV Status  Ex.:  
	 */
	private String versaoPdvStatus;

	/**
	 * Tipoa PDV Tauros: SP ou ESTACAO
	 */
	private TipoPdvEnum tipoPdvTauros;
	
	/**
	 * Tipoa PDV SISPAC: PRINCIPAL ou ESTACAO
	 */
	private TipoPdvEnum tipoPdvSispac;
	
	public PdvStatus() {
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
	
	public String getCodLoja() {
		return codLoja;
	}

	public void setCodLoja(String codLoja) {
		this.codLoja = codLoja;
	}

	public String getNumPdv() {
		return numPdv;
	}

	public void setNumPdv(String numPdv) {
		this.numPdv = numPdv;
	}

	public String getSistemaOp() {
		return sistemaOp;
	}

	public void setSistemaOp(String sistemaOp) {
		this.sistemaOp = sistemaOp;
	}

	public Date getHoraAtual() {
		return horaAtual;
	}

	public void setHoraAtual(Date horaAtual) {
		this.horaAtual = horaAtual;
	}

	public String getVersaoPdvStatus() {
		return versaoPdvStatus;
	}

	public void setVersaoPdvStatus(String versaoPdvStatus) {
		this.versaoPdvStatus = versaoPdvStatus;
	}

	public TipoPdvEnum getTipoPdvTauros() {
		return tipoPdvTauros;
	}

	public void setTipoPdvTauros(TipoPdvEnum tipoPdvTauros) {
		this.tipoPdvTauros = tipoPdvTauros;
	}

	public TipoPdvEnum getTipoPdvSispac() {
		return tipoPdvSispac;
	}

	public void setTipoPdvSispac(TipoPdvEnum tipoPdvSispac) {
		this.tipoPdvSispac = tipoPdvSispac;
	}

}
