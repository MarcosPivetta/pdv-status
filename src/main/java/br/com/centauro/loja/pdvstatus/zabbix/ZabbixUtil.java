package br.com.centauro.loja.pdvstatus.zabbix;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.centauro.loja.pdvstatus.model.PdvStatus;

/**
 * Classe utilitária utilizada para enviar informações pelo zabbix_sender
 * @author juliano
 */
public abstract class ZabbixUtil {

	private static Logger LOGGER = LoggerFactory.getLogger(ZabbixUtil.class);

	private static final String PDV = "PDV";
	private static final String ASPAS = "\"";
	private static final String LF = "\n";
	private static final SimpleDateFormat SDF_DATE_TIME = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	private static final String ZABBIX_PATH = "log/";
	private static final String ZABBIX_FILE = "pdv-zabbix.log";

	private static final String PROPERTY_FILE_PATH = "./";
	private static final String PROPERTY_FILE_NAME = "ZABBIX.properties";

    private static Properties zabbixProperties;

    /** Constantes do arquivo de properties **/
    private static final String ZABBIX_SENDER = "ZABBIX_SENDER";
    private static final String ZABBIX_URL = "ZABBIX_URL";
    private static final String ZABBIX_PORT = "ZABBIX_PORT";
    
    static {
		zabbixProperties = new Properties();

		try {
			FileInputStream fis = new FileInputStream(PROPERTY_FILE_PATH + PROPERTY_FILE_NAME);
			zabbixProperties.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			LOGGER.error("Arquivo nao encontrado [" + PROPERTY_FILE_PATH + PROPERTY_FILE_NAME + "]", e);
			zabbixProperties.clear();
		} catch (Exception e) {
			LOGGER.error("Exception", e);
			zabbixProperties.clear();
		}
    }
	
    /**
     * Grava o conteudo do pdvStatus em arquivo para envio ao zabbix
     * @param pdvStatus Objeto PdvStatus, contendo as informações obtidas do PDV
     */
    public static void saveZabbixFile(final PdvStatus pdvStatus) {
		LOGGER.info("Gerando arquivo...");
		try {
			final String PRFX = PDV+pdvStatus.getIdZabbixPdv();
			
    		StringBuilder content = new StringBuilder()
    				.append(PRFX).append(" ").append("Ip ").append(pdvStatus.getIp()).append(LF)
    				.append(PRFX).append(" ").append("HostName ").append(pdvStatus.getHostName()).append(LF)
    				;
    
    		String absolutePath = writeFile(content);
    		
    		if(null == absolutePath) {
    			LOGGER.error("Falha ao gerar arquivo do Zabbix.");
    			return;
    		}
    		
    		callZabbixAgent(absolutePath);
		} catch(Exception e) {
			LOGGER.error("Falha ao gerar arquivo para o Zabbix: " + e, e.getMessage());
		}
	}

	/**
	 * Gera arquivo para envio ao zabbix
	 * @param content
	 */
	private static String writeFile(final StringBuilder content) {
		String absolutePath = null;
		FileOutputStream foStream = null;
		
		try {
			File zabbixFile = new File(ZABBIX_PATH + ZABBIX_FILE);
			
			// Tenta apagar o arquivo, caso ele exista
			try {
				if(zabbixFile.exists()) {
					zabbixFile.delete();
				}
			} catch(Exception e) {
				LOGGER.error("Falha ao remover arquivo");
			}
			
			// Criar novo
			zabbixFile.createNewFile();
			
			foStream = new FileOutputStream(zabbixFile);
			foStream.write(content.toString().getBytes());

			absolutePath = zabbixFile.getAbsolutePath();
		} catch(Exception e) {
			LOGGER.error("Falha ao gerar arquivo para o Zabbix: " + e, e.getMessage());
		} finally {
			secureClose(foStream);
		}

		return absolutePath;
	}
	
	/**
	 * Faz a chamada ao agente do Zabbix, passando o caminho do arquivo
	 */
	private static void callZabbixAgent(final String zabbixFilePath) {
		final ArrayList<String> commands = new ArrayList<String>();
		commands.add("/bin/bash");
		commands.add("-c");
		String zabbix_sender = zabbixProperties.getProperty(ZABBIX_SENDER) + " -z " + zabbixProperties.getProperty(ZABBIX_URL) + " -p " + zabbixProperties.getProperty(ZABBIX_PORT) + " -i " + zabbixFilePath;
		commands.add(zabbix_sender);
		
		LOGGER.info(zabbix_sender);

		BufferedReader reader = null;
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(zabbix_sender);
			proc.waitFor();
			StringBuffer output = new StringBuffer();
			reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			LOGGER.info("Retorno do comando = " + output);
		} catch (Throwable t) {
			LOGGER.error("Erro ao executar comando shell" + t);
		} finally {
			secureClose(reader);
		}
	}

	/**
	 * Fecha resource
	 * @param resource
	 */
	private static void secureClose(final Closeable resource) {
		try {
			if (resource != null) {
				resource.close();
			}
		} catch (IOException ex) {
			LOGGER.error("Erro = " + ex.getMessage());
		}
	}

}
