package br.com.centauro.loja.pdvstatus.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe utilitária do Tauros, con métodos para verificar o tipo de PDV, versão
 * instalada.
 * 
 * @author juliano
 *
 */
public class TaurosUtil {
	private static Logger LOGGER = LoggerFactory.getLogger(TaurosUtil.class);

	private static final String PATH_TAUROS_SP = "/p2ksp";
	private static final String PATH_TAUROS_ESTACAO = "/p2k";

	/**
	 * Verifica se o PDV é um TAUROS SP
	 * 
	 * @return true se for SP, false caso contrário
	 */
	public static boolean isTaurosSP() {
		boolean isTaurosSP = false;

		try {
			File taurosSp = new File(PATH_TAUROS_SP);
			if (taurosSp.isDirectory()) {
				LOGGER.debug("O diretório " + PATH_TAUROS_SP + " existe!");
				isTaurosSP = true;
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		LOGGER.info("isTaurosSP: " + isTaurosSP);
		return isTaurosSP;
	}

	/**
	 * Verifica se o PDV é um TAUROS ESTACAO
	 * 
	 * @return true se for estacao, false caso contrário
	 */
	public static boolean isTaurosEstacao() {
		boolean isTaurosEstacao = false;

		try {
			File taurosDir = new File(PATH_TAUROS_ESTACAO);
			if (taurosDir.isDirectory()) {
				LOGGER.debug("O diretório " + PATH_TAUROS_ESTACAO + " existe!");
				isTaurosEstacao = true;
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		LOGGER.info("isTaurosEstacao: " + isTaurosEstacao);
		return isTaurosEstacao;
	}

	/**
	 * Obtém a versão do PDV Tauros.
	 * @return String com a versão recuperada, ou null caso não encontre o arquivo
	 */
	public static String getVersaoTauros() {
		String versao = null;

		return versao;
	}
}
