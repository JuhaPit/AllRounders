package com.fineline.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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



public class GoogleUploader {

	private static final String APPLICATION_NAME = "REAL DATA FEEDER";
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
		Credential credential = new AuthorizationCodeInstalledApp(flow,
				new LocalServerReceiver()).authorize("user");
		System.out.println("Credentials saved to "
				+ DATA_STORE_DIR.getAbsolutePath());
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

	public static void perse() throws IOException, ServiceException {
		// Build a new authorized API client service.
		Sheets service = getSheetsService();

		// Prints the names and majors of students in a sample spreadsheet:
		String spreadsheetId = "1zxu-b-HG0Kp8aQwIisFWO786s65LkfF2Wo-uSZ1xp6I";
		String range = "Form Responses 1!A2:T";

		List<List<Object>> arrData = getData();

		ValueRange oRange = new ValueRange();
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

	public static List<List<Object>> getData() {

		List<Object> data1 = new ArrayList<Object>();
		data1.add("jagadeesh");
		data1.add("jagadeesh");
		data1.add("jagadeesh");

		List<Object> data2 = new ArrayList<Object>();
		data2.add("jagadeesh");
		data2.add("jagadeesh");
		data2.add("jagadeesh");

		List<List<Object>> data = new ArrayList<List<Object>>();
		data.add(data1);
		data.add(data2);

		return data;
	}

}