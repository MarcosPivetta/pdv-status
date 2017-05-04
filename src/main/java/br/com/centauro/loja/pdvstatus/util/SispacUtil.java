package br.com.centauro.loja.pdvstatus.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.centauro.loja.pdvstatus.type.TipoPdvEnum;

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
	// private static final String PATH_TAUROS_ESTACAO = "/p2k";

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

		// TODO No tratamento da exceção retornar "ERRO"
		LOGGER.info("versão: " + versao);
		return versao;
	}
	
	/**
	 * Método para obter tipo de Sispac.
	 * 
	 * @return TipoPdvEnum com o tipo recuperado, sendo PRINCIPAL ou ESTACAO, ou null caso não seja Sispac.
	 */
	public static TipoPdvEnum getTipoSispac() {
		TipoPdvEnum tipoSispac = null;

		try {
			if (SispacUtil.isSispacPrincipal()) {
				tipoSispac = TipoPdvEnum.PRINCIPAL;
			} else if (SispacUtil.isSispacEstacao()) {
				tipoSispac = TipoPdvEnum.ESTACAO;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return tipoSispac;
	}

}
