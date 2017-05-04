package br.com.centauro.loja.pdvstatus.zabbix;

import org.junit.Before;
import org.junit.Test;

import br.com.centauro.loja.pdvstatus.model.PdvStatus;
import br.com.centauro.loja.pdvstatus.util.NetworkUtil;

public class TestZabbixUtil {

	private PdvStatus pdvStatusObj;

	@Before
	public void prepare() {
		pdvStatusObj = new PdvStatus();
		pdvStatusObj.setIdZabbixPdv("CE71-I90-SUSE");
		pdvStatusObj.setIp(NetworkUtil.getIp());
		pdvStatusObj.setHostName(NetworkUtil.getHostName());
	}

	@Test
	public void test() {
		ZabbixUtil.saveZabbixFile(pdvStatusObj);
	}

}
