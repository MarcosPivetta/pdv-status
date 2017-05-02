package br.com.centauro.loja.pdvstatus.zabbix;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import br.com.centauro.loja.pdvstatus.model.PdvStatus;
import br.com.centauro.loja.pdvstatus.zabbix.ZabbixUtil;

public class TestZabbixUtil {

	private PdvStatus pdvStatusObj;
	
	private Properties pdvProperties;
	
	@Before
	public void prepare() {
		//pdvStatusObj = new PdvStatus(null, null);
		pdvStatusObj.setIp("192.168.171.181");
		pdvStatusObj.setHostaname("I90");
		
		pdvProperties = new Properties();
		pdvProperties.setProperty("codigoAtivacao", "123456789");
	}
	
	@Test
	public void test() {
		ZabbixUtil.saveZabbixFile(pdvStatusObj, pdvProperties);
	}

}
