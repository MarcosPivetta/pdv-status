package br.com.centauro.loja.pdvstatus.zabbix;

import org.junit.Before;
import org.junit.Test;

import br.com.centauro.loja.pdvstatus.PdvAgent;
import br.com.centauro.loja.pdvstatus.model.PdvStatus;

public class TestZabbixUtil {

	private PdvStatus pdvStatusObj;

	@Before
	public void prepare() {
		pdvStatusObj = new PdvStatus();
		pdvStatusObj.setIdZabbixPdv("CE71-I90-SUSE");
		pdvStatusObj.setIp(PdvAgent.getIp());
		pdvStatusObj.setHostName(PdvAgent.getHostName());
	}

	@Test
	public void test() {
		ZabbixUtil.saveZabbixFile(pdvStatusObj);
	}

}
