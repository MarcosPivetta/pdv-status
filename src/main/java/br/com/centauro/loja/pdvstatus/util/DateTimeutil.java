package br.com.centauro.loja.pdvstatus.util;

import java.util.Date;

/**
 * Classe utilitária para lidar com Data e Hora
 * @author juliano
 *
 */
public abstract class DateTimeutil {
	
	/**
	 * Retorna a UnixTimestamp corrente
	 * @return long UnixTimestamp
	 */
	public static long getUnixTimestamp() {
		long unixTime = System.currentTimeMillis() / 1000L;
		
		return unixTime;
	}
	
	/**
	 * Converte uma data em UnixTimestamp
	 * @param data a data a ser convertida em UnixTimestamp
	 * @return
	 */
	public static long getUnixTimestamp(Date data) {
		if(data == null) {
			return 0L;
		}
		
		return (data.getTime() / 1000L);
	}
}
