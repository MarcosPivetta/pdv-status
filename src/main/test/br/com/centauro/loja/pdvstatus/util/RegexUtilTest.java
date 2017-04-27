package br.com.centauro.loja.pdvstatus.util;

import org.junit.Test;

public class RegexUtilTest {

	@Test
	public void testIsValidIp() {
		String ip1 = "192.168.12.160";
		String ip2 = "010.168.1.00";
		String ip3 = "10.168.12.0";
		String ip4 = "1.168.12.000";
		String ip5 = "192.168.01.160";
		String ip6 = "192.A1.12.160";
		System.out.println(ip1 + ": " + RegexUtil.isValidIp(ip1));
		System.out.println(ip2 + ": " + RegexUtil.isValidIp(ip2));
		System.out.println(ip3 + ": " + RegexUtil.isValidIp(ip3));
		System.out.println(ip4 + ": " + RegexUtil.isValidIp(ip4));
		System.out.println(ip5 + ": " + RegexUtil.isValidIp(ip5));
		System.out.println(ip6 + ": " + RegexUtil.isValidIp(ip6));
//		fail("Not yet implemented");
	}

}
