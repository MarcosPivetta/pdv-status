package br.com.centauro.loja.pdvstatus.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexUtilTest {

	@Test
	public void testIsValidIp() {
		String ip1 = "192.168.12.160"; // true
		String ip2 = "010.168.1.00"; // true
		String ip3 = "10.168.12.0"; // true
		String ip4 = "1.168.12.000"; // true
		String ip5 = "192.168.01.160"; // true
		String ip6 = "192.A1.12.160"; // false

		/**
		 * Validando a mascara de IP
		 */
		assertTrue(RegexUtil.isValidIp(ip1));
		assertTrue(RegexUtil.isValidIp(ip2));
		assertTrue(RegexUtil.isValidIp(ip3));
		assertTrue(RegexUtil.isValidIp(ip4));
		assertTrue(RegexUtil.isValidIp(ip5));
		assertTrue(RegexUtil.isValidIp(ip6));

	}

}
