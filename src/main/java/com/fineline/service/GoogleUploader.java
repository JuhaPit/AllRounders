package com.fineline.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fineline.domain.SheetsRow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.gdata.util.ServiceException;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class GoogleUploader {

	private static final String APPLICATION_NAME = "REAL DATA FEEDER";
	private static final String CLIENT_GOD_MODE = "4/yoGolvyeAkIfVqspGBOHuGbPlGo49bUx0YK9p6M1m8s#";
	private static final java.io.File DATA_STORE_DIR = new java.io.File(
			System.getProperty("user.home"),
			".credentials/sheets.googleapis.com-java-quickstart.json");
	private static FileDataStoreFactory DATA_STORE_FACTORY;
	private static final JsonFactory JSON_FACTORY = JacksonFactory
			.getDefaultInstance();
	private static HttpTransport HTTP_TRANSPORT;
	private static final List<String> SCOPES = Arrays.asList(
			SheetsScopes.SPREADSHEETS, SheetsScopes.DRIVE);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	static final Logger LOG = LoggerFactory.getLogger(GoogleUploader.class);
	/**
	 * Creates an authorized Credential object.
	 *
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	public static Credential authorize() throws IOException {
		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				HTTP_TRANSPORT, JSON_FACTORY, GoogleUploader.getClientSecret(),
				SCOPES).setDataStoreFactory(DATA_STORE_FACTORY)
				.setAccessType("offline").build();
		flow.newTokenRequest(CLIENT_GOD_MODE);
		Credential credential = new AuthorizationCodeInstalledApp(flow,
				new LocalServerReceiver()).authorize("user");
		LOG.info("Credential authorize - Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}

	private static GoogleClientSecrets getClientSecret() throws IOException {
		// Load client secrets.
		InputStream in = GoogleUploader.class
				.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
				JSON_FACTORY, new InputStreamReader(in));
		return clientSecrets;
	}

	/**
	 * Build and return an authorized Sheets API client service.
	 *
	 * @return an authorized Sheets API client service
	 * @throws IOException
	 */
	public static Sheets getSheetsService() throws IOException {
		Credential credential = authorize();
		return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}

	
	@SuppressWarnings("unused")
	public static void insert(SheetsRow row) throws IOException, ServiceException, ParseException {
		// Build a new authorized API client service.
		Sheets service = getSheetsService();

		// Prints the names and majors of students in a sample spreadsheet:
		String spreadsheetId = "1zxu-b-HG0Kp8aQwIisFWO786s65LkfF2Wo-uSZ1xp6I";

		List<List<Object>> arrData = getData(row);

		ValueRange oRange = new ValueRange();
		int lastRow = findLastRow();
		String range = "Form Responses 1!A" + (lastRow+1) + ":T";
		oRange.setRange(range); // I NEED THE NUMBER OF THE LAST ROW
		oRange.setValues(arrData);

		List<ValueRange> oList = new ArrayList<>();
		oList.add(oRange);

		BatchUpdateValuesRequest oRequest = new BatchUpdateValuesRequest();
		oRequest.setValueInputOption("RAW");
		oRequest.setData(oList);

		BatchUpdateValuesResponse oResp1 = service.spreadsheets().values()
				.batchUpdate(spreadsheetId, oRequest).execute();
	}

	public static List<List<Object>> getData(SheetsRow row) throws ParseException {

		List<Object> data1 = new ArrayList<Object>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyy HH:mm:ss");
		
		String pvv = row.getTimeStamp();
		Date date = formatter.parse(pvv);
		double epochDate = getEpochDate(date);
		
		String startTime = row.getAloitusAika();
		double aloitusAika = convertTime(startTime);
		
		String endTime = row.getLopetusAika();
		double lopetusAika = convertTime(endTime);
		
		String breaks = row.getTauot();
		double tauot = convertTime(breaks);
		
		int pYhteensa = row.getpJaot() + row.getpNoudot() + row.getpUnknown();
		int bYhteensa = row.getbJaot() + row.getbNoudot() + row.getbDhl();
		
		data1.add(epochDate);
		data1.add(row.getKuljettaja());
		data1.add(row.getAuto());
		data1.add(row.getAloitusKm());
		data1.add(aloitusAika);
		data1.add(lopetusAika);
		data1.add(tauot);
		data1.add(row.getLopetusKm());
		data1.add(row.getReitti());
		data1.add(row.getpJaot());
		data1.add(row.getpNoudot());
		data1.add(row.getpUnknown());
		data1.add(pYhteensa);
		data1.add(row.getbJaot());
		data1.add(row.getbNoudot());
		data1.add(row.getbDhl());
		data1.add(bYhteensa);
		data1.add(row.getiKollit());
		data1.add(row.getiStopit());
		data1.add(row.getLisatiedot());
		
		List<List<Object>> data = new ArrayList<List<Object>>();
		data.add(data1);

		return data;
	}
	
	public static int findLastRow() throws IOException{
		
		Sheets service = getSheetsService();

		String spreadsheetId = "1zxu-b-HG0Kp8aQwIisFWO786s65LkfF2Wo-uSZ1xp6I";
		String range = "Form Responses 1!A2:T";
		ValueRange response = service.spreadsheets().values()
				.get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		int size = values.size();
		return size+1;
		
	}
	
	// Precomputed difference between the Unix epoch and the Sheets epoch.
	private static final long SHEETS_EPOCH_DIFFERENCE = -2209161600000L;

	public static double getEpochDate(Date date)
	{
	    long millisSinceUnixEpoch = date.getTime();
	    long millisSinceSheetsEpoch = millisSinceUnixEpoch - SHEETS_EPOCH_DIFFERENCE;
	    return millisSinceSheetsEpoch / (double) TimeUnit.DAYS.toMillis(1);
	}
	
	public static double convertTime(String time){
		
		String[] parts = time.split(":");
		
		double hours = Double.parseDouble(parts[0]);
		double minutes = Double.parseDouble(parts[1]);
		
		double hoursInMinutes = hours * 60;
		
		double total = hoursInMinutes + minutes;
		
		double hoursAsDecimal = total / 1440;
		
		return hoursAsDecimal;
	}
	
	
	
}