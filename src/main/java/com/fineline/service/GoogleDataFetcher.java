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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fineline.domain.Topten;
import com.fineline.domain.WorkDay_GoogleSheets;

public class GoogleDataFetcher {

	private static final String APPLICATION_NAME = "REAL DATA FEEDER";

	/** Directory to store user credentials for this application. */

	private static final java.io.File DATA_STORE_DIR = new java.io.File(
			System.getProperty("user.dir"),
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
				.setAccessType("offline").build();
		Credential credential = new AuthorizationCodeInstalledApp(flow,
				new LocalServerReceiver()).authorize("user");
		System.out.println("Credentials saved to "
				+ DATA_STORE_DIR.getAbsolutePath());
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
		return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}
	
	public static List<Topten> topten() throws IOException {
		
		Sheets service = getSheetsService();

		WorkDay_GoogleSheets d = new WorkDay_GoogleSheets();

		// Prints the names and majors of students in a sample spreadsheet:
		// https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
		String spreadsheetId = "1YmlQACbcTwP6vTX0qAd45iL-Nw8hqv7rCTDLGQWtEX0";
		String range = "Näkymä 1!A3:AE";
		ValueRange response = service.spreadsheets().values()
				.get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();

		List<WorkDay_GoogleSheets> workdays = new ArrayList<WorkDay_GoogleSheets>();

		if (values == null || values.size() == 0) {
			System.out.println("No data found.");
		} else {
			System.out.println("");
			for (List row : values) {
				// Print columns A and AE, which correspond to indices 0 and 30.
				WorkDay_GoogleSheets w = new WorkDay_GoogleSheets();
				if (row.get(0).toString().length() == 0 || row.get(8).toString().length() == 0) {
					break;
				}
				w.setDate(row.get(0).toString());
				w.setName(row.get(1).toString());
				w.setCar_number(row.get(2).toString());
				w.setStart_km(row.get(3).toString());
				w.setStart_time(row.get(4).toString());
				w.setEnd_time(row.get(5).toString());
				w.setBreaks(row.get(6).toString());
				w.setEnd_km(row.get(7).toString());
				w.setEvening_hours(row.get(8).toString());
				w.setNight_hours(row.get(9).toString());
				w.setHours_total(row.get(10).toString());

				
				w.setKm_total(row.get(14).toString());
				w.setBasic_hours(row.get(15).toString());
				w.setRoute(row.get(16).toString());
				w.setPostnord_deliveries(row.get(17).toString());
				w.setPostnord_pickups(row.get(18).toString());
				w.setPostnord_unknown(row.get(19).toString());
				w.setPostnord_total(row.get(20).toString());
				w.setBring_deliveries(row.get(21).toString());
				w.setBring_pickups(row.get(22).toString());
				w.setBring_dhl_returns(row.get(23).toString());
				w.setBring_total(row.get(24).toString());
				w.setInnight_deliveries(row.get(25).toString());
				w.setInnight_stops(row.get(26).toString());
				w.setExtra_info(row.get(27).toString());
				w.setTotal_deliveries(row.get(28).toString());
				w.setTotal_pickups(row.get(29).toString());
				
				try {
					w.setEvening_hours_decimal(Double.parseDouble(row.get(11)
							.toString()));
					w.setNight_hours_decimal(Double.parseDouble(row.get(12)
							.toString()));
					w.setHours_total_decimal(Double.parseDouble(row.get(13)
							.toString()));
					w.setEffiency(Double.parseDouble(row.get(30).toString()));
				} catch (Exception e) {
					System.out.println("Räjähti");
					break;
				}
				
				workdays.add(w);
			}
		}

		
		workdays.sort(Comparator.comparing(WorkDay_GoogleSheets::getEffiency).reversed());
		
		List<String> names = new ArrayList<String>();
		
		String new_name;
		
		List<Topten> topten = new ArrayList<Topten>();
		for (int i = 0; i < workdays.size(); i++) {
			
			new_name = workdays.get(i).getName().toLowerCase();
			
			if (!names.contains(new_name)){
								
				names.add(new_name);
				Topten entry = new Topten(workdays.get(i).getName(), workdays.get(i).getEffiency(), workdays.get(i).getDate());
				topten.add(entry);
			}
		}


		topten = topten.subList(0, 10);
		System.out.println(topten.size());
		return topten;
	}
	
	public static List<WorkDay_GoogleSheets> listAll() throws IOException {
		
		Sheets service = getSheetsService();

		WorkDay_GoogleSheets d = new WorkDay_GoogleSheets();

		// Prints the names and majors of students in a sample spreadsheet:
		// https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
		String spreadsheetId = "1YmlQACbcTwP6vTX0qAd45iL-Nw8hqv7rCTDLGQWtEX0";
		String range = "Näkymä 1!A3:AE";
		ValueRange response = service.spreadsheets().values()
				.get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();

		List<WorkDay_GoogleSheets> workdays = new ArrayList<WorkDay_GoogleSheets>();

		if (values == null || values.size() == 0) {
			System.out.println("No data found.");
		} else {
			System.out.println("");
			for (List row : values) {
				// Print columns A and AE, which correspond to indices 0 and 30.
				WorkDay_GoogleSheets w = new WorkDay_GoogleSheets();
				if (row.get(0).toString().length() == 0 || row.get(8).toString().length() == 0) {
					break;
				}
				w.setDate(row.get(0).toString());
				w.setName(row.get(1).toString());
				w.setCar_number(row.get(2).toString());
				w.setStart_km(row.get(3).toString());
				w.setStart_time(row.get(4).toString());
				w.setEnd_time(row.get(5).toString());
				w.setBreaks(row.get(6).toString());
				w.setEnd_km(row.get(7).toString());
				w.setEvening_hours(row.get(8).toString());
				w.setNight_hours(row.get(9).toString());
				w.setHours_total(row.get(10).toString());

				
				w.setKm_total(row.get(14).toString());
				w.setBasic_hours(row.get(15).toString());
				w.setRoute(row.get(16).toString());
				w.setPostnord_deliveries(row.get(17).toString());
				w.setPostnord_pickups(row.get(18).toString());
				w.setPostnord_unknown(row.get(19).toString());
				w.setPostnord_total(row.get(20).toString());
				w.setBring_deliveries(row.get(21).toString());
				w.setBring_pickups(row.get(22).toString());
				w.setBring_dhl_returns(row.get(23).toString());
				w.setBring_total(row.get(24).toString());
				w.setInnight_deliveries(row.get(25).toString());
				w.setInnight_stops(row.get(26).toString());
				w.setExtra_info(row.get(27).toString());
				w.setTotal_deliveries(row.get(28).toString());
				w.setTotal_pickups(row.get(29).toString());
				
				try {
					w.setEvening_hours_decimal(Double.parseDouble(row.get(11)
							.toString()));
					w.setNight_hours_decimal(Double.parseDouble(row.get(12)
							.toString()));
					w.setHours_total_decimal(Double.parseDouble(row.get(13)
							.toString()));
					w.setEffiency(Double.parseDouble(row.get(30).toString()));
				} catch (Exception e) {
					System.out.println("Räjähti");
					break;
				}
				
				workdays.add(w);
			}
		}

		return workdays;
	}
	
	public static List<Topten> tophel() throws IOException {
		
		Sheets service = getSheetsService();

		WorkDay_GoogleSheets d = new WorkDay_GoogleSheets();

		// Prints the names and majors of students in a sample spreadsheet:
		// https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
		String spreadsheetId = "1YmlQACbcTwP6vTX0qAd45iL-Nw8hqv7rCTDLGQWtEX0";
		String range = "Näkymä 1!A3:AE";
		ValueRange response = service.spreadsheets().values()
				.get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();

		List<WorkDay_GoogleSheets> workdays = new ArrayList<WorkDay_GoogleSheets>();

		if (values == null || values.size() == 0) {
			System.out.println("No data found.");
		} else {
			System.out.println("");
			for (List row : values) {
				// Print columns A and AE, which correspond to indices 0 and 30.
				WorkDay_GoogleSheets w = new WorkDay_GoogleSheets();
				if (row.get(0).toString().length() == 0 || row.get(8).toString().length() == 0) {
					break;
				}
				w.setDate(row.get(0).toString());
				w.setName(row.get(1).toString());
				w.setCar_number(row.get(2).toString());
				w.setStart_km(row.get(3).toString());
				w.setStart_time(row.get(4).toString());
				w.setEnd_time(row.get(5).toString());
				w.setBreaks(row.get(6).toString());
				w.setEnd_km(row.get(7).toString());
				w.setEvening_hours(row.get(8).toString());
				w.setNight_hours(row.get(9).toString());
				w.setHours_total(row.get(10).toString());

				
				w.setKm_total(row.get(14).toString());
				w.setBasic_hours(row.get(15).toString());
				w.setRoute(row.get(16).toString());
				w.setPostnord_deliveries(row.get(17).toString());
				w.setPostnord_pickups(row.get(18).toString());
				w.setPostnord_unknown(row.get(19).toString());
				w.setPostnord_total(row.get(20).toString());
				w.setBring_deliveries(row.get(21).toString());
				w.setBring_pickups(row.get(22).toString());
				w.setBring_dhl_returns(row.get(23).toString());
				w.setBring_total(row.get(24).toString());
				w.setInnight_deliveries(row.get(25).toString());
				w.setInnight_stops(row.get(26).toString());
				w.setExtra_info(row.get(27).toString());
				w.setTotal_deliveries(row.get(28).toString());
				w.setTotal_pickups(row.get(29).toString());
				
				try {
					w.setEvening_hours_decimal(Double.parseDouble(row.get(11)
							.toString()));
					w.setNight_hours_decimal(Double.parseDouble(row.get(12)
							.toString()));
					w.setHours_total_decimal(Double.parseDouble(row.get(13)
							.toString()));
					w.setEffiency(Double.parseDouble(row.get(30).toString()));
				} catch (Exception e) {
					System.out.println("Räjähti");
					break;
				}
				if (w.getRoute().contains("HEL")){
				workdays.add(w);
				}
			}
		}

		
		workdays.sort(Comparator.comparing(WorkDay_GoogleSheets::getEffiency).reversed());
		
		List<String> names = new ArrayList<String>();
		
		String new_name;
		
		List<Topten> tophel = new ArrayList<Topten>();
		for (int i = 0; i < workdays.size(); i++) {
			
			new_name = workdays.get(i).getName().toLowerCase();
			
			if (!names.contains(new_name)){
								
				names.add(new_name);
				Topten entry = new Topten(workdays.get(i).getName(), workdays.get(i).getEffiency(), workdays.get(i).getDate());
				tophel.add(entry);
			}
		}


		tophel = tophel.subList(0, 15);
		System.out.println(tophel.size());
		return tophel;
	}
	
	
	public static List<Topten> topvan() throws IOException {
		
		Sheets service = getSheetsService();

		WorkDay_GoogleSheets d = new WorkDay_GoogleSheets();

		// Prints the names and majors of students in a sample spreadsheet:
		// https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
		String spreadsheetId = "1YmlQACbcTwP6vTX0qAd45iL-Nw8hqv7rCTDLGQWtEX0";
		String range = "Näkymä 1!A3:AE";
		ValueRange response = service.spreadsheets().values()
				.get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();

		List<WorkDay_GoogleSheets> workdays = new ArrayList<WorkDay_GoogleSheets>();

		if (values == null || values.size() == 0) {
			System.out.println("No data found.");
		} else {
			System.out.println("");
			for (List row : values) {
				// Print columns A and AE, which correspond to indices 0 and 30.
				WorkDay_GoogleSheets w = new WorkDay_GoogleSheets();
				if (row.get(0).toString().length() == 0 || row.get(8).toString().length() == 0) {
					break;
				}
				w.setDate(row.get(0).toString());
				w.setName(row.get(1).toString());
				w.setCar_number(row.get(2).toString());
				w.setStart_km(row.get(3).toString());
				w.setStart_time(row.get(4).toString());
				w.setEnd_time(row.get(5).toString());
				w.setBreaks(row.get(6).toString());
				w.setEnd_km(row.get(7).toString());
				w.setEvening_hours(row.get(8).toString());
				w.setNight_hours(row.get(9).toString());
				w.setHours_total(row.get(10).toString());

				
				w.setKm_total(row.get(14).toString());
				w.setBasic_hours(row.get(15).toString());
				w.setRoute(row.get(16).toString());
				w.setPostnord_deliveries(row.get(17).toString());
				w.setPostnord_pickups(row.get(18).toString());
				w.setPostnord_unknown(row.get(19).toString());
				w.setPostnord_total(row.get(20).toString());
				w.setBring_deliveries(row.get(21).toString());
				w.setBring_pickups(row.get(22).toString());
				w.setBring_dhl_returns(row.get(23).toString());
				w.setBring_total(row.get(24).toString());
				w.setInnight_deliveries(row.get(25).toString());
				w.setInnight_stops(row.get(26).toString());
				w.setExtra_info(row.get(27).toString());
				w.setTotal_deliveries(row.get(28).toString());
				w.setTotal_pickups(row.get(29).toString());
				
				try {
					w.setEvening_hours_decimal(Double.parseDouble(row.get(11)
							.toString()));
					w.setNight_hours_decimal(Double.parseDouble(row.get(12)
							.toString()));
					w.setHours_total_decimal(Double.parseDouble(row.get(13)
							.toString()));
					w.setEffiency(Double.parseDouble(row.get(30).toString()));
				} catch (Exception e) {
					System.out.println("Räjähti");
					break;
				}
				if (w.getRoute().contains("VAN")){
				workdays.add(w);
				}
			}
		}

		
		workdays.sort(Comparator.comparing(WorkDay_GoogleSheets::getEffiency).reversed());
		
		List<String> names = new ArrayList<String>();
		
		String new_name;
		
		List<Topten> topvan = new ArrayList<Topten>();
		for (int i = 0; i < workdays.size(); i++) {
			
			new_name = workdays.get(i).getName().toLowerCase();
			
			if (!names.contains(new_name)){
								
				names.add(new_name);
				Topten entry = new Topten(workdays.get(i).getName(), workdays.get(i).getEffiency(), workdays.get(i).getDate());
				topvan.add(entry);
			}
		}


		topvan = topvan.subList(0, 5);
		System.out.println(topvan.size());
		return topvan;
	}
}
