package br.com.centauro.loja.pdvstatus.zabbix;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import br.com.centauro.loja.pdvstatus.model.PdvStatus;
import br.com.centauro.loja.pdvstatus.zabbix.ZabbixUtil;

public class TestZabbixUtil {

	private PdvStatus pdvStatusObj;
	
	@Before
	public void prepare() {
		pdvStatusObj = new PdvStatus();
		pdvStatusObj.setIdZabbixPdv("CE71-I90-SUSE");
		pdvStatusObj.setIp("192.168.171.181");
		pdvStatusObj.setHostName("I90");
	}
	
	@Test
	public void test() {
		ZabbixUtil.saveZabbixFile(pdvStatusObj);
	}

}
