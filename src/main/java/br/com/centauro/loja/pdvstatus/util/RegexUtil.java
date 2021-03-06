package br.com.centauro.loja.pdvstatus.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RegexUtil {

	private static Matcher matcher;
	private static Pattern pattern_ip;
	private static Pattern pattern_enp;
	private static Pattern pattern_eth;

	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	private static final String IPADDRESS_ENP = "^enp[0-9]+s[0-9]+";
	private static final String IPADDRESS_ETH = "^eth[0-9]";

	static {
		pattern_ip = Pattern.compile(IPADDRESS_PATTERN);
		pattern_enp = Pattern.compile(IPADDRESS_ENP);
		pattern_eth = Pattern.compile(IPADDRESS_ETH);
	}

	/**
	 * Validate ip address with regular expression
	 * 
	 * @param ip
	 *            ip address for validation
	 * @return true valid ip address, false invalid ip address
	 */
	public static boolean isValidIp(final String ip) {
		matcher = pattern_ip.matcher(ip);
		return matcher.matches();
	}

	/**
	 * Validate network interface with regular expression
	 * @param interfaceName network interface name
	 * @return true valid ethernet interface, false invalid ethernet interface 
	 */
	public static boolean isEthernetInterface(final String interfaceName) {
		return (pattern_enp.matcher(interfaceName).matches() || pattern_eth.matcher(interfaceName).matches());
	}
}