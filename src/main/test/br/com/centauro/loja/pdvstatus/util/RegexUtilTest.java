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
		assertFalse(RegexUtil.isValidIp(ip6));

	}

	@Test
	public void testIsEthernetInterface() {
		String et1 = "enp3s0"; // true
		String et2 = "enpXs4654"; // false
		String et3 = "enp000s000"; // true
		String et4 = "enp12s45"; // true
		String et5 = "Aenp3s0"; // false
		String et6 = "0enp3s0"; // false
		String et7 = "enps0"; // false
		String et8 = "enp3AsA0"; // false
		String et9 = "enpp3s0s"; // false
		String et10 = "enp30s10"; // true

		/**
		 * Validando a mascara de Interface Ethernet
		 */

		assertTrue(RegexUtil.isEthernetInterface(et1));
		assertFalse(RegexUtil.isEthernetInterface(et2));
		assertTrue(RegexUtil.isEthernetInterface(et3));
		assertTrue(RegexUtil.isEthernetInterface(et4));
		assertFalse(RegexUtil.isEthernetInterface(et5));
		assertFalse(RegexUtil.isEthernetInterface(et6));
		assertFalse(RegexUtil.isEthernetInterface(et7));
		assertFalse(RegexUtil.isEthernetInterface(et8));
		assertFalse(RegexUtil.isEthernetInterface(et9));
		assertTrue(RegexUtil.isEthernetInterface(et10));

	}
}
