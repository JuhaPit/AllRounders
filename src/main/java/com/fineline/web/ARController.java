package com.fineline.web;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fineline.domain.Deliveries;
import com.fineline.domain.SheetsRow;
import com.fineline.domain.Topten;
import com.fineline.service.GoogleUploader;
import com.fineline.domain.WorkDay_GoogleSheets;
import com.fineline.service.GoogleDataFetcher;
import com.google.gdata.util.ServiceException;

@CrossOrigin
@Controller
public class ARController {

	// Key:Value pair for very simple authentication. SHA512 encrypted word.
	private static String secret = "B55AF471FFE583FEB96EF0788EBF9FCBA678592F70CB8508F5D43ED64A1C0E90B598C627389A913776EEE1AFEFEDE85AB82CB8AD46DF7AE3CE24072196738A9B";

	/*
	 * Accepts GET request at endpoint /top, requires header with Secret word
	 * 
	 * Returns JSON list of top 7 drivers
	 */
	@RequestMapping(value = "/top", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> topSeven(
			@RequestHeader(value = "Secret") String secret_word)
			throws IOException {

		if (secret.equals(secret_word)) {
			List<Topten> topSeven = GoogleDataFetcher.topten();
			return new ResponseEntity<Object>(topSeven, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}

	/*
	 * Accepts POST request at endpoint /insert, requires header with Secret
	 * word and requires following info on body: 
	 * String timeStamp, String kuljettaja, String auto, int aloitusKm, String aloitusAika, 
	 * String lopetusAika, String tauot, int lopetusKm, String reitti, int pJaot, int pNoudot, 
	 * int pUnknown, int pYhteensa, int bJaot, int bNoudot, int bDhl,
	 * int bYhteensa, int iKollit, int iStopit, String lisatiedot
	 * 
	 * Returns HTTP Status 200 OK, if everything is ok!
	 */

	@RequestMapping(value = "/insert", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> insertData(
			@RequestBody SheetsRow row,
			@RequestHeader(value = "Secret") String secret_word)
			throws IOException, ServiceException {
		if (secret.equals(secret_word)) {
			GoogleUploader.insert(row);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}

	/*
	 * Accepts GET request at endpoint /listall, requires header with Secret
	 * word
	 * 
	 * Returns JSON list of all Google Sheets data
	 */

	@RequestMapping(value = "/listall", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> listEverything(
			@RequestHeader(value = "Secret") String secret_word)
			throws IOException {

		if (secret.equals(secret_word)) {
			List<WorkDay_GoogleSheets> listall = GoogleDataFetcher.listAll();
			return new ResponseEntity<Object>(listall, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}

	/*
	 * Accepts GET request at endpoint /tophel, requires header with Secret word
	 * 
	 * Returns JSON list of all best 7 drivers in Helsinki
	 */

	@RequestMapping(value = "/tophel", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> topHelsinki(
			@RequestHeader(value = "Secret") String secret_word)
			throws IOException {

		if (secret.equals(secret_word)) {
			List<Topten> tophel = GoogleDataFetcher.tophel();
			return new ResponseEntity<Object>(tophel, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}

	/*
	 * Accepts GET request at endpoint /topvan, requires header with Secret word
	 * 
	 * Returns JSON list of all best 7 drivers in Vantaa
	 */

	@RequestMapping(value = "/topvan", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> topVantaa(
			@RequestHeader(value = "Secret") String secret_word)
			throws IOException {

		if (secret.equals(secret_word)) {
			List<Topten> topvan = GoogleDataFetcher.topvan();
			return new ResponseEntity<Object>(topvan, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}

	/*
	 * Accepts GET request at endpoint /avg/{name}, requires header with Secret
	 * word Requires Driver {name} on url, example /avg/Joonas Returns JSON
	 * Object with driver efficiency past month
	 */

	@RequestMapping(value = "/avg/{name}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getAvgFromDriver(
			@RequestHeader(value = "Secret") String secret_word,
			@PathVariable("name") String name) throws IOException {

		if (secret.equals(secret_word)) {
			List<Topten> avgDriver = GoogleDataFetcher.driverAvg(name);
			return new ResponseEntity<Object>(avgDriver, HttpStatus.OK);
		} else {

			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}

	/*
	 * Accepts GET request at endpoint /deliveries, requires header with Secret
	 * word
	 * 
	 * Returns JSON Object of all deliveries
	 */

	@RequestMapping(value = "/deliveries", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getAllDeliveries(
			@RequestHeader(value = "Secret") String secret_word)
			throws IOException {

		if (secret.equals(secret_word)) {
			Deliveries d = GoogleDataFetcher.getAllDeliverycount();
			return new ResponseEntity<Object>(d, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping("/restapi")
	public String rest() {
		return "index";
	}

	@RequestMapping("/")
	public String restapi() {
		return "index";
	}

}
