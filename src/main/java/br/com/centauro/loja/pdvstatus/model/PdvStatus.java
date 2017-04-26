package br.com.centauro.loja.pdvstatus.model;

import java.util.Calendar;

import br.com.centauro.loja.pdvstatus.type.SatStatusEnum;

public class PdvStatus {

	private Long id;

	/**
	 * Data e hora do status
	 */
	private Calendar dataHoraStatus;

	/**
	 * O código EEEEE é substituído por um dentre os possíveis códigos de
	 * retorno Tamanho: 5 Ex: '10000', '10001',' '10098', ' '10099'
	 */
	private String codigoEeeee;

	/**
	 * São as descrições dos códigos EEEEE Em caso de rejeição nos dados de
	 * cancelamento (EEEEE = 07007) este campo retornará a descrição da mensagem
	 * Tamanho: 60 Ex: "Código ativaçãoo inválido"
	 */
	private String mensagem;

	/**
	 * "cod" É o código de referência de cada mensagemSEFAZ Tamanho: 3 Ex:
	 * "002", "999"
	 */
	private String cod;

	/**
	 * Mensagem de texto enviada pela SEFAZ referente ao "Envio de avisos ao
	 * usuário" Tamanho: 100 Ex: "Existem atualizações para o SAT, porém o prazo
	 * para instalação é menor que 30 dias"
	 */
	private String mensagemSefaz;

	/**
	 * Número de série do SAT Tamanho: 9 Ex.: 320008889
	 */
	private String numeroSerie;

	/**
	 * Código de ativação do SAT Tamanho: 20 Ex.: 123456789
	 */
	private String codAtivacao;

	/**
	 * Tipo de Lan Tamanho: 8 Ex.: DHCP, PPPoE, IPFIX
	 */
	private String tipoLan;

	/**
	 * Endereço IP da LAN Tamanho: 15 Ex.: 192.168.010.100
	 */
	private String lanIp;

	/**
	 * Endereço MAC Tamanho: 17 Ex.: 00:0C:41:82:25:E8
	 */
	private String lanMac;

	/**
	 * Máscara de sub-rede Tamanho: 15 Ex.: 255.255.255.000
	 */
	private String lanMask;

	/**
	 * Endereço gateway Tamanho: 15 Ex.: 192.168.010.001
	 */
	private String lanGw;

	/**
	 * Endereço DNS1 Tamanho: 15 Ex.: 192.168.010.001
	 */
	private String lanDns1;

	/**
	 * Endereço DNS2 Tamanho: 15 Ex.: 192.168.010.001
	 */
	private String lanDns2;

	/**
	 * Status da rede Tamanho: 16 Ex.: CONECTADO, NÃO CONECTADO
	 */
	private String statusLan;

	/**
	 * Nível da Bateria Tamanho: 8 Ex.: ALTO, MÉDIO, BAIXO
	 */
	private String nivelBateria;

	/**
	 * Memória de Trabalho Total Tamanho: 50 Ex.: 1 Gbyte
	 */
	private String memoriaTotal;

	/**
	 * Memória de Trabalho Usada Tamanho: 50 Ex.: 35 bytes
	 */
	private String memoriaUsada;

	/**
	 * Data e hora atual
	 */
	private Calendar dataHoraAtual;

	/**
	 * Versão do Software Básico Tamanho: 16 Ex.: 000001
	 */
	private String versaoSoftBasico;

	/**
	 * Versão do Leiaute da tabela de informações Tamanho: 16 Ex.: 1.01
	 */
	private String versaoLayout;

	/**
	 * Número sequencial do último CF-e-SAT Emitido Tamanho: 44 Ex.:
	 * 32008889000000089
	 */
	private String ultimoCfeSat;

	/**
	 * Número sequencial do primeiro CF-e-SAT armazenado na memória de trabalho
	 * Tamanho: 44 Ex.: 32008889000000075
	 */
	private String listaInicial;

	/**
	 * Número sequencial do último CF-e-SAT armazenado na memória de trabalho
	 * Tamanho: 44 Ex.: 32008889000000089
	 */
	private String listaFinal;

	/**
	 * Data e hora da última transmissão de CF-e-SAT para SEFAZ
	 */
	private Calendar dataHoraCfe;

	/**
	 * Data e hora da última comunicação com a SEFAZ
	 */
	private Calendar dataHoraUltima;

	/**
	 * Data de emissão do certificado instalado
	 */
	private Calendar dataEmissaoCertificado;

	/**
	 * Data de vencimento do certificado instalado
	 */
	private Calendar dataVencimentoCertificado;

	/**
	 * Estado de Operação do SAT 0=DESBLOQUEADO 1=BLOQUEIO SEFAZ 2=BLOQUEIO
	 * CONTRIBUINTE 3=BLOQUEIO AUTÔNOMO 4=BLOQUEIO PARA DESATIVAÇÂO Tamanho: 1
	 * Ex.: "0" ,"1", "2", "3" ou "4"
	 */
	private String estadoOperacao;

	public PdvStatus() {
	}

	public PdvStatus(Long id, Calendar dataHoraStatus, String codigoEeeee, String mensagem, String cod,
			String mensagemSefaz, String numeroSerie, String codAtivacao, String tipoLan, String lanIp, String lanMac,
			String lanMask, String lanGw, String lanDns1, String lanDns2, String statusLan, String nivelBateria,
			String memoriaTotal, String memoriaUsada, Calendar dataHoraAtual, String versaoSoftBasico,
			String versaoLayout, String ultimoCfeSat, String listaInicial, String listaFinal, Calendar dataHoraCfe,
			Calendar dataHoraUltima, Calendar dataEmissaoCertificado, Calendar dataVencimentoCertificado,
			String estadoOperacao) {
		super();
		this.id = id;
		this.dataHoraStatus = dataHoraStatus;
		this.codigoEeeee = codigoEeeee;
		this.mensagem = mensagem;
		this.cod = cod;
		this.mensagemSefaz = mensagemSefaz;
		this.numeroSerie = numeroSerie;
		this.codAtivacao = codAtivacao;
		this.tipoLan = tipoLan;
		this.lanIp = lanIp;
		this.lanMac = lanMac;
		this.lanMask = lanMask;
		this.lanGw = lanGw;
		this.lanDns1 = lanDns1;
		this.lanDns2 = lanDns2;
		this.statusLan = statusLan;
		this.nivelBateria = nivelBateria;
		this.memoriaTotal = memoriaTotal;
		this.memoriaUsada = memoriaUsada;
		this.dataHoraAtual = dataHoraAtual;
		this.versaoSoftBasico = versaoSoftBasico;
		this.versaoLayout = versaoLayout;
		this.ultimoCfeSat = ultimoCfeSat;
		this.listaInicial = listaInicial;
		this.listaFinal = listaFinal;
		this.dataHoraCfe = dataHoraCfe;
		this.dataHoraUltima = dataHoraUltima;
		this.dataEmissaoCertificado = dataEmissaoCertificado;
		this.dataVencimentoCertificado = dataVencimentoCertificado;
		this.estadoOperacao = estadoOperacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDataHoraStatus() {
		return dataHoraStatus;
	}

	public void setDataHoraStatus(Calendar dataHoraStatus) {
		this.dataHoraStatus = dataHoraStatus;
	}

	public String getCodigoEeeee() {
		return codigoEeeee;
	}

	public void setCodigoEeeee(String codigoEeeee) {
		this.codigoEeeee = codigoEeeee;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getMensagemSefaz() {
		return mensagemSefaz;
	}

	public void setMensagemSefaz(String mensagemSefaz) {
		this.mensagemSefaz = mensagemSefaz;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getCodAtivacao() {
		return codAtivacao;
	}

	public void setCodAtivacao(String codAtivacao) {
		this.codAtivacao = codAtivacao;
	}

	public String getTipoLan() {
		return tipoLan;
	}

	public void setTipoLan(String tipoLan) {
		this.tipoLan = tipoLan;
	}

	public String getLanIp() {
		return lanIp;
	}

	public void setLanIp(String lanIp) {
		this.lanIp = lanIp;
	}

	public String getLanMac() {
		return lanMac;
	}

	public void setLanMac(String lanMac) {
		this.lanMac = lanMac;
	}

	public String getLanMask() {
		return lanMask;
	}

	public void setLanMask(String lanMask) {
		this.lanMask = lanMask;
	}

	public String getLanGw() {
		return lanGw;
	}

	public void setLanGw(String lanGw) {
		this.lanGw = lanGw;
	}

	public String getLanDns1() {
		return lanDns1;
	}

	public void setLanDns1(String lanDns1) {
		this.lanDns1 = lanDns1;
	}

	public String getLanDns2() {
		return lanDns2;
	}

	public void setLanDns2(String lanDns2) {
		this.lanDns2 = lanDns2;
	}

	public String getStatusLan() {
		return statusLan;
	}

	public void setStatusLan(String statusLan) {
		this.statusLan = statusLan;
	}

	public String getNivelBateria() {
		return nivelBateria;
	}

	public void setNivelBateria(String nivelBateria) {
		this.nivelBateria = nivelBateria;
	}

	public String getMemoriaTotal() {
		return memoriaTotal;
	}

	public void setMemoriaTotal(String memoriaTotal) {
		this.memoriaTotal = memoriaTotal;
	}

	public String getMemoriaUsada() {
		return memoriaUsada;
	}

	public void setMemoriaUsada(String memoriaUsada) {
		this.memoriaUsada = memoriaUsada;
	}

	public Calendar getDataHoraAtual() {
		return dataHoraAtual;
	}

	public void setDataHoraAtual(Calendar dataHoraAtual) {
		this.dataHoraAtual = dataHoraAtual;
	}

	public String getVersaoSoftBasico() {
		return versaoSoftBasico;
	}

	public void setVersaoSoftBasico(String versaoSoftBasico) {
		this.versaoSoftBasico = versaoSoftBasico;
	}

	public String getVersaoLayout() {
		return versaoLayout;
	}

	public void setVersaoLayout(String versaoLayout) {
		this.versaoLayout = versaoLayout;
	}

	public String getUltimoCfeSat() {
		return ultimoCfeSat;
	}

	public void setUltimoCfeSat(String ultimoCfeSat) {
		this.ultimoCfeSat = ultimoCfeSat;
	}

	public String getListaInicial() {
		return listaInicial;
	}

	public void setListaInicial(String listaInicial) {
		this.listaInicial = listaInicial;
	}

	public String getListaFinal() {
		return listaFinal;
	}

	public void setListaFinal(String listaFinal) {
		this.listaFinal = listaFinal;
	}

	public Calendar getDataHoraCfe() {
		return dataHoraCfe;
	}

	public void setDataHoraCfe(Calendar dataHoraCfe) {
		this.dataHoraCfe = dataHoraCfe;
	}

	public Calendar getDataHoraUltima() {
		return dataHoraUltima;
	}

	public void setDataHoraUltima(Calendar dataHoraUltima) {
		this.dataHoraUltima = dataHoraUltima;
	}

	public Calendar getDataEmissaoCertificado() {
		return dataEmissaoCertificado;
	}

	public void setDataEmissaoCertificado(Calendar dataEmissaoCertificado) {
		this.dataEmissaoCertificado = dataEmissaoCertificado;
	}

	public Calendar getDataVencimentoCertificado() {
		return dataVencimentoCertificado;
	}

	public void setDataVencimentoCertificado(Calendar dataVencimentoCertificado) {
		this.dataVencimentoCertificado = dataVencimentoCertificado;
	}

	public String getEstadoOperacao() {
		return estadoOperacao;
	}

	public void setEstadoOperacao(String estadoOperacao) {
		this.estadoOperacao = estadoOperacao;
	}

	@Override
	public String toString() {
		return "PdvStatus [id=" + id + ", dataHoraStatus=" + dataHoraStatus + ", codigoEeeee=" + codigoEeeee
				+ ", mensagem=" + mensagem + ", cod=" + cod + ", mensagemSefaz=" + mensagemSefaz + ", numeroSerie="
				+ numeroSerie + ", tipoLan=" + tipoLan + ", lanIp=" + lanIp + ", lanMac=" + lanMac + ", lanMask="
				+ lanMask + ", lanGw=" + lanGw + ", lanDns1=" + lanDns1 + ", lanDns2=" + lanDns2 + ", statusLan="
				+ statusLan + ", nivelBateria=" + nivelBateria + ", memoriaTotal=" + memoriaTotal + ", memoriaUsada="
				+ memoriaUsada + ", dataHoraAtual=" + dataHoraAtual + ", versaoSoftBasico=" + versaoSoftBasico
				+ ", versaoLayout=" + versaoLayout + ", ultimoCfeSat=" + ultimoCfeSat + ", listaInicial=" + listaInicial
				+ ", listaFinal=" + listaFinal + ", dataHoraCfe=" + dataHoraCfe + ", dataHoraUltima=" + dataHoraUltima
				+ ", dataEmissaoCertificado=" + dataEmissaoCertificado + ", dataVencimentoCertificado="
				+ dataVencimentoCertificado + ", estadoOperacao=" + estadoOperacao + "]";
	}

	public String getSatStatusStr() {
		return SatStatusEnum.fromInt(Integer.parseInt(this.getEstadoOperacao())).toString();
	}

}
