package br.com.centauro.loja.pdvstatus.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RegexUtil {

	private static Pattern pattern;
	private static Matcher matcher;

	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	static {
		pattern = Pattern.compile(IPADDRESS_PATTERN);
	}

	/**
	 * Validate ip address with regular expression
	 * 
	 * @param ip
	 *            ip address for validation
	 * @return true valid ip address, false invalid ip address
	 */
	public static boolean isValidIp(final String ip) {
		matcher = pattern.matcher(ip);
		return matcher.matches();
	}

	public static void main(String[] args) {
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
	}
}