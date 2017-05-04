package br.com.centauro.loja.pdvstatus.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe utilitária do SISPAC, con métodos para verificar o tipo de PDV, versão
 * instalada.
 * 
 * @author juliano
 *
 */
public class SispacUtil {
	private static Logger LOGGER = LoggerFactory.getLogger(SispacUtil.class);

	private static final String PATH_SISPAC = "/XXXXX";
//	private static final String PATH_TAUROS_ESTACAO = "/p2k";

	
	public static boolean isSispacPrincipal() {
		boolean is = false;
		
		LOGGER.info("isSispacPrincipal: " + is);
		return is;
	}
	
	public static boolean isSispacEstacao() {
		boolean is = false;
		
		LOGGER.info("isSispacEstacao: " + is);
		return is;
	}
	
	public static String getVersaoSispac() {
		String versao = null;
		
		LOGGER.info("versão: " + versao);
		return versao;
	}
}
