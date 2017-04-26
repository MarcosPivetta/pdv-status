package br.com.centauro.loja.satstatus.zabbix;

import java.util.Calendar;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import br.com.centauro.loja.pdvstatus.model.PdvStatus;
import br.com.centauro.loja.pdvstatus.zabbix.ZabbixUtil;

public class TestZabbixUtil {

	private PdvStatus satStatusObj;
	
	private Properties satProperties;
	
	@Before
	public void prepare() {
		satStatusObj = new PdvStatus();
		satStatusObj.setDataHoraStatus(Calendar.getInstance());
		satStatusObj.setCodigoEeeee("10000");
		satStatusObj.setMensagem("Resposta com Sucesso");
		satStatusObj.setCod("");
		satStatusObj.setMensagemSefaz("");
		satStatusObj.setNumeroSerie("000048901");
		satStatusObj.setCodAtivacao("123456789");
		satStatusObj.setTipoLan("IPFIX");
		satStatusObj.setLanIp("192.168.171.181");
		satStatusObj.setLanMac("b8:24:1a:00:1a:f2");
		satStatusObj.setLanMask("255.255.255.000");
		satStatusObj.setLanGw("192.168.171.001");
		satStatusObj.setLanDns1("192.168.008.195");
		satStatusObj.setLanDns2("192.168.015.253");
		satStatusObj.setStatusLan("CONECTADO");
		satStatusObj.setNivelBateria("ALTO");
		satStatusObj.setMemoriaTotal("1048576 Kbytes");
		satStatusObj.setMemoriaUsada("253420 Kbytes");
		satStatusObj.setDataHoraAtual(Calendar.getInstance());
		satStatusObj.setVersaoSoftBasico("02.00.00");
		satStatusObj.setVersaoLayout("0.06");
		satStatusObj.setUltimoCfeSat("35160606347409002885590000489010001623518377");
		satStatusObj.setListaInicial("00000000000000000000000000000000000000000000");
		satStatusObj.setListaFinal("00000000000000000000000000000000000000000000");
		satStatusObj.setDataHoraCfe(Calendar.getInstance());
		satStatusObj.setDataHoraUltima(Calendar.getInstance());
		satStatusObj.setDataEmissaoCertificado(Calendar.getInstance());
		satStatusObj.setDataVencimentoCertificado(Calendar.getInstance());
		satStatusObj.setEstadoOperacao("0");
		
		satProperties = new Properties();
		satProperties.setProperty("codigoAtivacao", "123456789");
	}
	
	@Test
	public void test() {
		ZabbixUtil.saveZabbixFile(satStatusObj, satProperties);
	}

}
