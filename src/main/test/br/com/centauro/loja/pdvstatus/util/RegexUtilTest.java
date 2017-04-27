package br.com.centauro.loja.pdvstatus.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexUtilTest {

	
	@Test
	public void testIsValidIp() {
		String ip1 = "192.168.12.160";		// true
		String ip2 = "010.168.1.00";		// true
		String ip3 = "10.168.12.0";			// true
		String ip4 = "1.168.12.000";		// true
		String ip5 = "192.168.01.160";		// true
		String ip6 = "192.A1.12.160";		// false
		
		/** 
		 * Validando a mascara de IP
		 * */
		assertTrue(RegexUtil.isValidIp(ip1));
		assertTrue(RegexUtil.isValidIp(ip2));
		assertTrue(RegexUtil.isValidIp(ip3));
		assertTrue(RegexUtil.isValidIp(ip4));
		assertTrue(RegexUtil.isValidIp(ip5));
		assertTrue(RegexUtil.isValidIp(ip6));
		
//		System.out.println(ip1 + ": " + RegexUtil.isValidIp(ip1));
//		System.out.println(ip2 + ": " + RegexUtil.isValidIp(ip2));
//		System.out.println(ip3 + ": " + RegexUtil.isValidIp(ip3));
//		System.out.println(ip4 + ": " + RegexUtil.isValidIp(ip4));
//		System.out.println(ip5 + ": " + RegexUtil.isValidIp(ip5));
//		System.out.println(ip6 + ": " + RegexUtil.isValidIp(ip6));		
		
	}

	@Test
	public void testIsEthernetInterface() {
		String et1 = "enp3s0";
		String et2 = "enpXs4654";
		String et3 = "enp000s000";
		String et4 = "enp12s45";
		String et5 = "Aenp3s0";
		String et6 = "0enp3s0";
		String et7 = "enps0";
		String et8 = "enp3AsA0";
		String et9 = "enpp3s0s";
		String et10 = "enp30s10";
		System.out.println(et1 + ": " + RegexUtil.isEthernetInterface(et1));
		System.out.println(et2 + ": " + RegexUtil.isEthernetInterface(et2));
		System.out.println(et3 + ": " + RegexUtil.isEthernetInterface(et3));
		System.out.println(et4 + ": " + RegexUtil.isEthernetInterface(et4));
		System.out.println(et5 + ": " + RegexUtil.isEthernetInterface(et5));
		System.out.println(et6 + ": " + RegexUtil.isEthernetInterface(et6));
		System.out.println(et7 + ": " + RegexUtil.isEthernetInterface(et7));
		System.out.println(et8 + ": " + RegexUtil.isEthernetInterface(et8));
		System.out.println(et9 + ": " + RegexUtil.isEthernetInterface(et9));
		System.out.println(et10 + ": " + RegexUtil.isEthernetInterface(et10));
	}
}
