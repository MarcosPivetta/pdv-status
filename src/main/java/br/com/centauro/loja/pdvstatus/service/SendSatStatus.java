package br.com.centauro.loja.pdvstatus.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.centauro.loja.pdvstatus.model.PdvStatus;
import br.com.centauro.loja.pdvstatus.util.CalendarSerializer;

public class SendSatStatus {
	private static Logger LOGGER = LoggerFactory.getLogger(SendSatStatus.class);

	private static String satStatusToJson(PdvStatus satStatusObj) {

		GsonBuilder gsonB = new GsonBuilder();
		gsonB.registerTypeAdapter(Calendar.class, new CalendarSerializer());
		gsonB.registerTypeAdapter(GregorianCalendar.class, new CalendarSerializer());
		Gson gson = gsonB.create();

		String strSatStatus = gson.toJson(satStatusObj);
		LOGGER.debug("satStatusToJson:" + strSatStatus);

		return strSatStatus;

	}

	public static int send(PdvStatus satStatusObj, Properties satProperties) {

		String satStatusJson = satStatusToJson(satStatusObj);
		String satStatusURL = satProperties.getProperty("satStatusURL", "");
		try {
			URL url = new URL(satStatusURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String input = satStatusJson;

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			// if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			// throw new RuntimeException("Failed : HTTP error code : " +
			// conn.getResponseCode());
			// }

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			LOGGER.debug("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				LOGGER.debug(output);
			}

			conn.disconnect();

			return conn.getResponseCode();

		} catch (MalformedURLException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return -1;
	}
}
