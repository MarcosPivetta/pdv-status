package br.com.centauro.loja.pdvstatus.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LinuxUtil {
	private static Logger LOGGER = LoggerFactory.getLogger(LinuxUtil.class);

	public static String getSistemaOperacional() {
		return findLinuxDistro("PRETTY_NAME");
	}
	
	private static String findLinuxDistro(String key) {
		String value = "ERRO";
		
		if(key != null && !"".equals(key)) {
			// lists all the files ending with -release in the etc folder
			File dir = new File("/etc/");
			File fileList[] = new File[0];
			if (dir.exists()) {
				fileList = dir.listFiles(new FilenameFilter() {
					public boolean accept(File dir, String filename) {
						return filename.endsWith("-release");
					}
				});
			}
			// looks for the version file (not all linux distros)
			File fileVersion = new File("/proc/version");
			if (fileVersion.exists()) {
				fileList = Arrays.copyOf(fileList, fileList.length + 1);
				fileList[fileList.length - 1] = fileVersion;
			}
			// prints all the version-related files
			boolean valueFound = false;
			for (File f : fileList) {
				if(valueFound) {
					break;
				}
				try {
					BufferedReader myReader = new BufferedReader(new FileReader(f));
					String strLine = null;
					while ((strLine = myReader.readLine()) != null) {
						String[] split = strLine.split("=");
						if(split != null && split.length == 2) {
							if(split[0].equals(key)) {
								value = split[1];
								valueFound = true;
								break;
							}
						}
//						System.out.println(strLine);
					}
					myReader.close();
				} catch (Exception e) {
					LOGGER.error("Error: " + e.getMessage());
				}
			}
		}
		
		LOGGER.info("Sistema Operacional: " + value);
		return value;
	}
}
