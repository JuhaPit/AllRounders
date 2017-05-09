package com.fineline.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.services.sheets.v4.Sheets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fineline.domain.Deliveries;
import com.fineline.domain.Topten;
import com.fineline.domain.WorkDay_GoogleSheets;

public class GoogleDataFetcher {

	/* Google Sheets stuff */
	private static final String APPLICATION_NAME = "REAL DATA FEEDER";
	private static final String SPREADSHEET_ID = "1YmlQACbcTwP6vTX0qAd45iL-Nw8hqv7rCTDLGQWtEX0";
	private static final String SPREADSHEET_SHEET_AND_RANGE = "Näkymä 1!A3:AE";
	private static final String CLIENT_GOD_MODE = "4/jVHvLP_1Y0TkzTKWOnm0u6anfm9u2GJEsfxL63wRXRo#";

	/* Google Sheets Column indices */
	private static final int COLUMN_DATE = 0;
	private static final int COLUMN_NAME = 1;
	private static final int COLUMN_CAR_NUMBER = 2;
	private static final int COLUMN_START_KM = 3;
	private static final int COLUMN_START_TIME = 4;
	private static final int COLUMN_END_TIME = 5;
	private static final int COLUMN_BREAKS = 6;
	private static final int COLUMN_END_KM = 7;
	private static final int COLUMN_EVENING_HOURS = 8;
	private static final int COLUMN_NIGHT_HOURS = 9;
	private static final int COLUMN_HOURS_TOTAL = 10;
	private static final int COLUMN_EVENING_HOURS_DECIMAL = 11;
	private static final int COLUMN_NIGHT_HOURS_DECIMAL = 12;
	private static final int COLUMN_HOURS_TOTAL_DECIMAL = 13;
	private static final int COLUMN_KM_TOTAL = 14;
	private static final int COLUMN_BASIC_HOURS = 15;
	private static final int COLUMN_ROUTE = 16;
	private static final int COLUMN_POSTNORD_DELIVERIES = 17;
	private static final int COLUMN_POSTNORD_PICKUPS = 18;
	private static final int COLUMN_POSTNORD_UNKNOWN = 19;
	private static final int COLUMN_POSTNORD_TOTAL = 20;
	private static final int COLUMN_BRING_DELIVERIES = 21;
	private static final int COLUMN_BRING_PICKUPS = 22;
	private static final int COLUMN_DHL_RETURNS = 23;
	private static final int COLUMN_BRING_TOTAL = 24;
	private static final int COLUMN_INNIGHT_DELIVERIES = 25;
	private static final int COLUMN_INNIGHT_STOPS = 26;
	private static final int COLUMN_EXTRA_INFO = 27;
	private static final int COLUMN_TOTAL_DELIVERIES = 28;
	private static final int COLUMN_TOTAL_PICKUPS = 29;
	private static final int COLUMN_EFFICIENCY = 30;

	/** Directory to store user credentials for this application. */

	private static final java.io.File DATA_STORE_DIR = new java.io.File(
			System.getProperty("user.home"),
			".credentials/sheets.googleapis.com-java-quickstart");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory
			.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/**
	 * Global instance of the scopes required by this quickstart.
	 * 
	 * If modifying these scopes, delete your previously saved credentials at
	 * ~/.credentials/sheets.googleapis.com-java-quickstart
	 */
	private static final List<String> SCOPES = Arrays
			.asList(SheetsScopes.SPREADSHEETS_READONLY);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}
	
	static final Logger LOG = LoggerFactory.getLogger(GoogleDataFetcher.class);

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	public static Credential authorize() throws IOException {

		// Load client secrets.
		InputStream in = GoogleDataFetcher.class
				.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
				JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(DATA_STORE_FACTORY)
				.setAccessType("offline").setApprovalPrompt("auto").build();

		flow.newTokenRequest(CLIENT_GOD_MODE);

		// GoogleAuthorizationCodeTokenRequest newTokenRequest(String
		// authorizationCode)

		Credential credential = new AuthorizationCodeInstalledApp(flow,
				new LocalServerReceiver()).authorize("user");

		LOG.info("Credential authorize - credential: " + credential);
		LOG.info("Credential authorize - Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}

	/**
	 * Build and return an authorized Sheets API client service.
	 * 
	 * @return an authorized Sheets API client service
	 * @throws IOException
	 */

	public static Sheets getSheetsService() throws IOException {
		Credential credential = authorize();

		System.out.println(credential.getAccessToken());
		return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}

	public static List<Topten> topten() throws IOException {

		Sheets service = getSheetsService();

		String spreadsheetId = SPREADSHEET_ID;
		String range = SPREADSHEET_SHEET_AND_RANGE;
		ValueRange response = service.spreadsheets().values()
				.get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		Date date1 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		date = cal.getTime();

		List<WorkDay_GoogleSheets> workdays = new ArrayList<WorkDay_GoogleSheets>();

		if (values == null || values.size() == 0) {
			LOG.info("GoogleDataFetcher - topten - No data found");
		} else {
			for (List<?> row : values) {

				WorkDay_GoogleSheets w = new WorkDay_GoogleSheets();
				if (row.get(COLUMN_DATE).toString().trim().length() == 0
						|| row.get(COLUMN_EVENING_HOURS).toString().length() == 0) {
					break;
				}
				w.setDate(row.get(COLUMN_DATE).toString());
				w.setName(row.get(COLUMN_NAME).toString());
				w.setRoute(row.get(COLUMN_ROUTE).toString());
				w.setPostnord_total(row.get(COLUMN_POSTNORD_TOTAL).toString());
				w.setBring_total(row.get(COLUMN_BRING_TOTAL).toString());

				try {

					w.setEffiency(Double.parseDouble(row.get(COLUMN_EFFICIENCY)
							.toString()));
				} catch (Exception e) {
					LOG.debug("GoogleDataFetcher - topten - Exploded, tried to get COLUMN_EFFIENCY");
					break;
				}

				try {
					date1 = dateFormat.parse(w.getDate());
					if (date1.after(date)) {
						workdays.add(w);
					}
				} catch (Exception e) {

				}

			}
		}

		workdays.sort(Comparator.comparing(WorkDay_GoogleSheets::getEffiency)
				.reversed());

		List<String> names = new ArrayList<String>();

		String new_name;

		List<Topten> topten = new ArrayList<Topten>();
		for (int i = 0; i < workdays.size(); i++) {

			new_name = workdays.get(i).getName().toLowerCase();

			if (!names.contains(new_name)) {
				
				List<Topten> test = new ArrayList<Topten>();
				
				test = driverAvgTop(new_name, workdays);

				names.add(new_name);
				Topten entry = new Topten(workdays.get(i).getName(), workdays
						.get(i).getEffiency(), workdays.get(i).getDate(), workdays.get(i).getRoute(), test.get(0).getWork_days() , test.get(0).getAvg_eff());
				topten.add(entry);
			}
		}

		topten = topten.subList(0, 7);
		LOG.info("GoogleDataFetcher - topten - Topten size: " + topten.size());
		return topten;
	}

	public static List<WorkDay_GoogleSheets> listAll() throws IOException {

		Sheets service = getSheetsService();

		String spreadsheetId = SPREADSHEET_ID;
		String range = SPREADSHEET_SHEET_AND_RANGE;
		ValueRange response = service.spreadsheets().values()
				.get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();

		List<WorkDay_GoogleSheets> workdays = new ArrayList<WorkDay_GoogleSheets>();

		if (values == null || values.size() == 0) {
			LOG.info("GoogleDataFetcher - listAll - No data found");
		} else {
			for (List<?> row : values) {
				// Print columns A and AE, which correspond to indices 0 and 30.
				WorkDay_GoogleSheets w = new WorkDay_GoogleSheets();
				if (row.get(COLUMN_DATE).toString().trim().length() == 0 || row.get(COLUMN_NAME).toString().length() == 0
						|| row.get(COLUMN_EVENING_HOURS).toString().length() == 0 || row.get(COLUMN_POSTNORD_TOTAL).toString().length() == 0) {

					break;
				}
				w.setDate(row.get(COLUMN_DATE).toString());
				w.setName(row.get(COLUMN_NAME).toString());
				w.setCar_number(row.get(COLUMN_CAR_NUMBER).toString());
				w.setStart_km(row.get(COLUMN_START_KM).toString());
				w.setStart_time(row.get(COLUMN_START_TIME).toString());
				w.setEnd_time(row.get(COLUMN_END_TIME).toString());
				w.setBreaks(row.get(COLUMN_BREAKS).toString());
				w.setEnd_km(row.get(COLUMN_END_KM).toString());
				w.setEvening_hours(row.get(COLUMN_EVENING_HOURS).toString());
				w.setNight_hours(row.get(COLUMN_NIGHT_HOURS).toString());
				w.setHours_total(row.get(COLUMN_HOURS_TOTAL).toString());
				w.setKm_total(row.get(COLUMN_KM_TOTAL).toString());
				w.setBasic_hours(row.get(COLUMN_BASIC_HOURS).toString());
				w.setRoute(row.get(COLUMN_ROUTE).toString());
				w.setPostnord_deliveries(row.get(COLUMN_POSTNORD_DELIVERIES)
						.toString());
				w.setPostnord_pickups(row.get(COLUMN_POSTNORD_PICKUPS)
						.toString());
				w.setPostnord_unknown(row.get(COLUMN_POSTNORD_UNKNOWN)
						.toString());
				w.setPostnord_total(row.get(COLUMN_POSTNORD_TOTAL).toString());
				w.setBring_deliveries(row.get(COLUMN_BRING_DELIVERIES)
						.toString());
				w.setBring_pickups(row.get(COLUMN_BRING_PICKUPS).toString());
				w.setBring_dhl_returns(row.get(COLUMN_DHL_RETURNS).toString());
				w.setBring_total(row.get(COLUMN_BRING_TOTAL).toString());
				w.setInnight_deliveries(row.get(COLUMN_INNIGHT_DELIVERIES)
						.toString());
				w.setInnight_stops(row.get(COLUMN_INNIGHT_STOPS).toString());
				w.setExtra_info(row.get(COLUMN_EXTRA_INFO).toString());
				w.setTotal_deliveries(row.get(COLUMN_TOTAL_DELIVERIES)
						.toString());
				w.setTotal_pickups(row.get(COLUMN_TOTAL_PICKUPS).toString());

				try {
					w.setEvening_hours_decimal(Double.parseDouble(row.get(
							COLUMN_EVENING_HOURS_DECIMAL).toString()));
					w.setNight_hours_decimal(Double.parseDouble(row.get(
							COLUMN_NIGHT_HOURS_DECIMAL).toString()));
					w.setHours_total_decimal(Double.parseDouble(row.get(
							COLUMN_HOURS_TOTAL_DECIMAL).toString()));
					w.setEffiency(Double.parseDouble(row.get(COLUMN_EFFICIENCY)
							.toString()));
				} catch (Exception e) {
					LOG.debug("GoogleDataFetcher - topten - Exploded, tried to get COLUMN_EVENING_HOURS_DECIMAL, COLUMN_NIGHT_HOURS_DECIMAL or COLUMN_HOURS_TOTAL_DECIMAL");
					break;
				}

				workdays.add(w);
			}
		}

		return workdays;
	}

	public static List<Topten> tophel() throws IOException {

		Sheets service = getSheetsService();

		String spreadsheetId = SPREADSHEET_ID;
		String range = SPREADSHEET_SHEET_AND_RANGE;
		ValueRange response = service.spreadsheets().values()
				.get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();

		List<WorkDay_GoogleSheets> workdays = new ArrayList<WorkDay_GoogleSheets>();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		Date date1 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		date = cal.getTime();
		LOG.info("GoogleDataFetcher - tophel - Formatted date: " + dateFormat.format(date));

		if (values == null || values.size() == 0) {
			LOG.info("GoogleDataFetcher - tophel - No data found");
		} else {
			for (List<?> row : values) {
				// Print columns A and AE, which correspond to indices 0 and 30.
				WorkDay_GoogleSheets w = new WorkDay_GoogleSheets();
				if (row.get(COLUMN_DATE).toString().trim().length() == 0
						|| row.get(COLUMN_EVENING_HOURS).toString().length() == 0 || row.get(COLUMN_EFFICIENCY).toString().isEmpty()) {
					break;
				}
				w.setDate(row.get(COLUMN_DATE).toString());
				w.setName(row.get(COLUMN_NAME).toString());
				w.setRoute(row.get(COLUMN_ROUTE).toString());
				w.setPostnord_total(row.get(COLUMN_POSTNORD_TOTAL).toString());
				w.setBring_total(row.get(COLUMN_BRING_TOTAL).toString());
				try {
					w.setEffiency(Double.parseDouble(row.get(COLUMN_EFFICIENCY)
							.toString()));
				} catch (Exception e) {
					LOG.debug("GoogleDataFetcher - tophel - Exploded, tried to get COLUMN_EFFICIENCY");
					break;
				}
				if (w.getRoute().contains("HEL")) {

					try {
						date1 = dateFormat.parse(w.getDate());
					} catch (ParseException e) {
						e.printStackTrace();
					}

					if (date1.after(date)) {

						workdays.add(w);
					}
				}
			}
		}

		workdays.sort(Comparator.comparing(WorkDay_GoogleSheets::getEffiency)
				.reversed());

		List<String> names = new ArrayList<String>();

		String new_name;
		

		List<Topten> tophel = new ArrayList<Topten>();
		for (int i = 0; i < workdays.size(); i++) {

			new_name = workdays.get(i).getName().toLowerCase();

			if (!names.contains(new_name)) {

				names.add(new_name);
				
				List<Topten> test = new ArrayList<Topten>();
				
				test = driverAvgTop(new_name, workdays);
				
				Topten entry = new Topten(workdays.get(i).getName(), workdays
						.get(i).getEffiency(), workdays.get(i).getDate(), workdays.get(i).getRoute(), test.get(0).getWork_days() , test.get(0).getAvg_eff());
				tophel.add(entry);
			}
		}
		

		
		

		if (tophel.size() > 6) {
			tophel = tophel.subList(0, 7);
		}

		return tophel;
	}

	public static List<Topten> topvan() throws IOException {

		Sheets service = getSheetsService();

		String spreadsheetId = SPREADSHEET_ID;
		String range = SPREADSHEET_SHEET_AND_RANGE;
		ValueRange response = service.spreadsheets().values()
				.get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();

		List<WorkDay_GoogleSheets> workdays = new ArrayList<WorkDay_GoogleSheets>();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		Date date1 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		date = cal.getTime();

		if (values == null || values.size() == 0) {
			LOG.info("GoogleDataFetcher - topvan - No data found");
		} else {
			for (List<?> row : values) {


				WorkDay_GoogleSheets w = new WorkDay_GoogleSheets();
				if (row.get(COLUMN_DATE).toString().trim().length() == 0 || row.get(COLUMN_NAME).toString().length() == 0
						|| row.get(COLUMN_EVENING_HOURS).toString().length() == 0) {

					break;
				}
				w.setDate(row.get(COLUMN_DATE).toString());
				w.setName(row.get(COLUMN_NAME).toString());
				w.setRoute(row.get(COLUMN_ROUTE).toString());
				w.setPostnord_total(row.get(COLUMN_POSTNORD_TOTAL).toString());
				w.setBring_total(row.get(COLUMN_BRING_TOTAL).toString());

				try {
					w.setEffiency(Double.parseDouble(row.get(COLUMN_EFFICIENCY)
							.toString()));
				} catch (Exception e) {
					LOG.debug("GoogleDataFetcher - topvan - Exploded, tried to get COLUMN_EFFICIENCY");
					break;
				}
				if (w.getRoute().contains("VAN") || w.getRoute().contains("FISP")) {

					try {
						date1 = dateFormat.parse(w.getDate());
					} catch (ParseException e) {
						e.printStackTrace();
					}

					if (date1.after(date)) {
						workdays.add(w);

					}
				}
			}
		}

		workdays.sort(Comparator.comparing(WorkDay_GoogleSheets::getEffiency)
				.reversed());

		List<String> names = new ArrayList<String>();

		String new_name;
		System.out.println("test");
		List<Topten> topvan = new ArrayList<Topten>();
		for (int i = 0; i < workdays.size(); i++) {

			new_name = workdays.get(i).getName().toLowerCase();

			if (!names.contains(new_name)) {

				names.add(new_name);
				
				List<Topten> test = new ArrayList<Topten>();
				
				test = driverAvgTop(new_name, workdays);
				
				Topten entry = new Topten(workdays.get(i).getName(), workdays
						.get(i).getEffiency(), workdays.get(i).getDate(), workdays.get(i).getRoute(), test.get(0).getWork_days() , test.get(0).getAvg_eff());
				topvan.add(entry);
			}
		}

		if (topvan.size() > 6) {
			topvan = topvan.subList(0, 7);
		}

		return topvan;
	}

	public static List<Topten> driverAvg(String name) throws IOException {

		Sheets service = getSheetsService();

		String spreadsheetId = SPREADSHEET_ID;
		String range = SPREADSHEET_SHEET_AND_RANGE;
		ValueRange response = service.spreadsheets().values()
				.get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();

		List<WorkDay_GoogleSheets> workdays = new ArrayList<WorkDay_GoogleSheets>();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		Date date1 = new Date();
		Date date2 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		date = cal.getTime();
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.YEAR, Calendar.MONTH+1 , 1);
		date2 = cal2.getTime();
				
		if (values == null || values.size() == 0) {
			LOG.info("GoogleDataFetcher - driverAvg - No data found");
		} else {
			for (List<?> row : values) {
				WorkDay_GoogleSheets w = new WorkDay_GoogleSheets();
				if (row.get(COLUMN_DATE).toString().trim().length() == 0 || row.get(COLUMN_NAME).toString().length() == 0
						|| row.get(COLUMN_EVENING_HOURS).toString().length() == 0 || row.get(COLUMN_POSTNORD_TOTAL).toString().length() == 0) {

					break;
				}
				w.setDate(row.get(COLUMN_DATE).toString());
				w.setName(row.get(COLUMN_NAME).toString());
				w.setPostnord_total(row.get(COLUMN_POSTNORD_TOTAL).toString());
				w.setBring_total(row.get(COLUMN_BRING_TOTAL).toString());

				try {
					w.setEffiency(Double.parseDouble(row.get(COLUMN_EFFICIENCY)
							.toString()));
				} catch (Exception e) {
					LOG.debug("GoogleDataFetcher - driverAvg - Exploded, tried to get COLUMN_EFFICIENCY");
					break;
				}

				try {
					date1 = dateFormat.parse(w.getDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}

				if (date1.after(date2)) {
					workdays.add(w);

				}

			}
		}

		int postnord = 0;
		int bring = 0;
		double avg = 0;
		int count = 0;

		List<Topten> driverAvg = new ArrayList<Topten>();

		Topten driver = new Topten(null, 0, null, null, 0, 0);

		for (int i = 0; i < workdays.size(); i++) {
			if (name.equalsIgnoreCase(workdays.get(i).getName().toString())) {
				count++;
				postnord = postnord
						+ Integer.parseInt(workdays.get(i).getPostnord_total());
				bring = bring
						+ Integer.parseInt(workdays.get(i).getBring_total());
				avg = avg + workdays.get(i).getEffiency();

			}

		}

		if (count != 0) {
			driver.setName(name);
		}

		avg = (postnord + bring) / avg;
		driver.setEff(avg);
		driver.setWork_days(count);
		driver.setAvg_eff(avg);
		driverAvg.add(driver);
		return driverAvg;
	}

	public static Deliveries getAllDeliverycount() throws IOException {

		Sheets service = getSheetsService();

		String spreadsheetId = SPREADSHEET_ID;
		String range = SPREADSHEET_SHEET_AND_RANGE;
		ValueRange response = service.spreadsheets().values()
				.get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();

		int postnord_deliveries = 0, postnord_pickups = 0, postnord_unknown = 0, postnord_total = 0;
		int bring_deliveries = 0, bring_pickups = 0, dhl_returns = 0, bring_total = 0;
		int innight_deliveries = 0, innight_stops = 0;
		int total_deliveries = 0, total_pickups = 0;

		if (values == null || values.size() == 0) {
			LOG.info("GoogleDataFetcher - getAllDeliverycount - No data found");
		} else {
			System.out.println("");
			for (List<?> row : values) {
				// Print columns A and AE, which correspond to indices 0 and 30.
				
				if (row.get(COLUMN_DATE).toString().length() == 0
						|| row.get(COLUMN_EVENING_HOURS).toString().length() == 0) {
					break;
				}

				try {

					
					postnord_deliveries = postnord_deliveries
							+ Integer.parseInt((String) row
									.get(COLUMN_POSTNORD_DELIVERIES));
					postnord_pickups = postnord_pickups
							+ Integer.parseInt((String) row
									.get(COLUMN_POSTNORD_PICKUPS));
					postnord_unknown = postnord_unknown
							+ Integer.parseInt((String) row
									.get(COLUMN_POSTNORD_UNKNOWN));
					postnord_total = postnord_total
							+ Integer.parseInt((String) row
									.get(COLUMN_POSTNORD_TOTAL));

					bring_deliveries = bring_deliveries
							+ Integer.parseInt((String) row
									.get(COLUMN_BRING_DELIVERIES));
					bring_pickups = bring_pickups
							+ Integer.parseInt((String) row
									.get(COLUMN_BRING_PICKUPS));
					dhl_returns = dhl_returns
							+ Integer.parseInt((String) row
									.get(COLUMN_DHL_RETURNS));
					bring_total = bring_total
							+ Integer.parseInt((String) row
									.get(COLUMN_BRING_TOTAL));

					innight_deliveries = innight_deliveries
							+ Integer.parseInt((String) row
									.get(COLUMN_INNIGHT_DELIVERIES));
					innight_stops = innight_stops
							+ Integer.parseInt((String) row
									.get(COLUMN_INNIGHT_STOPS));

				} catch (Exception e) {
					LOG.debug("GoogleDataFetcher - driverAvg - Exploded, tried to get COLUMN_POSTNORD_DELIVERIES, COLUMN_POSTNORD_PICKUPS, COLUMN_POSTNORD_UNKNOWN, COLUMN_POSTNORD_TOTAL, COLUMN_BRING_DELIVERIES, COLUMN_BRING_PICKUPS, COLUMN_DHL_RETURNS, COLUMN_BRING_TOTAL, COLUMN_INNIGHT_DELIVERIES or COLUMN_INNIGHT_STOPS");
					break;
				}

			}
		}

		total_deliveries = postnord_total + bring_total + innight_deliveries;
		total_pickups = postnord_pickups + bring_pickups;

		Deliveries d = new Deliveries(postnord_total, postnord_pickups,
				postnord_deliveries, postnord_unknown, bring_deliveries,
				bring_total, bring_pickups, dhl_returns, innight_deliveries,
				innight_stops, total_deliveries, total_pickups);

		return d;
	}
	
	public static List<Topten> driverAvgTop(String name, List<WorkDay_GoogleSheets> workdays) throws IOException {

		int postnord = 0;
		int bring = 0;
		double avg = 0;
		int count = 0;

		List<Topten> driverAvg = new ArrayList<Topten>();

		Topten driver = new Topten(null, 0, null, null, 0, 0);

		for (int i = 0; i < workdays.size(); i++) {
			if (name.equalsIgnoreCase(workdays.get(i).getName().toString())) {
				
				System.out.println(workdays.get(i).toString());
				
				try {
					count++;									
					postnord = postnord + Integer.parseInt(workdays.get(i).getPostnord_total());
					bring = bring + Integer.parseInt(workdays.get(i).getBring_total());
					avg = avg + workdays.get(i).getEffiency();
					
				} catch (Exception e) {
					LOG.info("GoogleDataFetcher - driverAvgTop parse error, problem with sheet data?");
				}
				

			}

		}

		if (count != 0) {
			driver.setName(name);
		}
		
		avg = (postnord + bring) / avg;
		driver.setEff(avg);
		driver.setWork_days(count);
		driver.setAvg_eff(avg);
		driverAvg.add(driver);
		
		System.out.println(driverAvg);
		return driverAvg;
	}

}
