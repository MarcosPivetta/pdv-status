package br.com.centauro.loja.pdvstatus.util;

import java.io.File;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.centauro.loja.pdvstatus.type.TipoPdvEnum;

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
	private static final String FILE_TAUROS_SP = "versaoSP.dat";
	private static final String PATH_TAUROS_ESTACAO = "/p2k";
	private static final String FILE_TAUROS_ESTACAO = "versaoPDV.dat";

	private static Scanner scanner;

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
	 * 
	 * @return String com a versão recuperada, ou null caso não encontre o
	 *         arquivo
	 */
	public static String getVersaoTauros() {
		String versao = null;

		try {
			if (isTaurosEstacao()) {
				File fileEstacao = new File(PATH_TAUROS_ESTACAO + "/" + FILE_TAUROS_ESTACAO);
				if (fileEstacao.exists()) {
					LOGGER.debug("O arquivo " + FILE_TAUROS_ESTACAO + " existe!");

					scanner = new Scanner(fileEstacao);
					versao = scanner.toString();
				}
			}

			if (isTaurosSP()) {
				File fileSP = new File(PATH_TAUROS_SP + "/" + FILE_TAUROS_SP);
				if (fileSP.exists()) {
					LOGGER.debug("O arquivo " + FILE_TAUROS_SP + " existe!");

					scanner = new Scanner(fileSP);
					String line = scanner.nextLine();
					if (line.startsWith("VERSAO_SP =")) {
						String[] linesplit = line.split("=");
						versao = linesplit[1].trim();
					}
				}
			}
		} catch (Exception e) {
			versao = "ERRO";
			LOGGER.error(e.getMessage(), e);
		}
		
		return versao;
	}
	
	/**
	 * Método para obter tipo de Tauros.
	 *
	 * @return TipoPdvEnum com o tipo recuperado, sendo SP ou ESTACAO, ou null caso não seja Tauros.
	 */
	public static TipoPdvEnum getTipoTauros() {
		TipoPdvEnum tipoTauros = null;

		try {
			if (isTaurosSP()) {
				tipoTauros = TipoPdvEnum.SP;
			} else if (isTaurosEstacao()) {
				tipoTauros = TipoPdvEnum.ESTACAO;
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return tipoTauros;
	}
	
}
