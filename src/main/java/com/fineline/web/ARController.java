package com.fineline.web;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.fineline.domain.Motd;
import com.fineline.domain.Sakko;
import com.fineline.domain.SheetsRow;
import com.fineline.domain.Topten;
import com.fineline.service.GoogleUploader;
import com.fineline.service.SakkoService;
import com.fineline.domain.WorkDay_GoogleSheets;
import com.fineline.service.GoogleDataFetcher;
import com.google.gdata.util.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@Controller
public class ARController {
	static final Logger LOG = LoggerFactory.getLogger(ARController.class);

	// Key:Value pair for very simple authentication. SHA512 encrypted word.
	private static String secret = "B55AF471FFE583FEB96EF0788EBF9FCBA678592F70CB8508F5D43ED64A1C0E90B598C627389A913776EEE1AFEFEDE85AB82CB8AD46DF7AE3CE24072196738A9B";

	// Bean to hold info on message of the day
	Motd msg = new Motd();

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
			LOG.info("/top - [GET] Fetching topSeven");
			return new ResponseEntity<Object>(topSeven, HttpStatus.OK);
		} else {
			LOG.debug("/top - [GET] Error fetching topSeven");
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}

	/*
	 * Accepts POST request at endpoint /insert, requires header with Secret
	 * word and requires following info on body: String timeStamp, String
	 * kuljettaja, String auto, int aloitusKm, String aloitusAika, String
	 * lopetusAika, String tauot, int lopetusKm, String reitti, int pJaot, int
	 * pNoudot, int pUnknown, int pYhteensa, int bJaot, int bNoudot, int bDhl,
	 * int bYhteensa, int iKollit, int iStopit, String lisatiedot
	 * 
	 * Returns HTTP Status 200 OK, if everything is ok!
	 */

	@RequestMapping(value = "/insert", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> insertData(
			@RequestBody SheetsRow row,
			@RequestHeader(value = "Secret") String secret_word)
			throws IOException, ServiceException, ParseException {
		if (secret.equals(secret_word)) {
			GoogleUploader.insert(row);
			LOG.info("/insert - [POST] Inserting row to sheet");
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			LOG.debug("/insert - [POST] Error inserting row to sheet");
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
			LOG.info("/listall - [GET] Fetching list of all workdays");
			return new ResponseEntity<Object>(listall, HttpStatus.OK);
		} else {
			LOG.debug("/listall - [GET] Error fetching list of all workdays");
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
			LOG.info("/tophel - [GET] Fetching top drivers for helsinki");
			return new ResponseEntity<Object>(tophel, HttpStatus.OK);
		} else {
			LOG.debug("/tophel - [GET] Error fetching top drivers for helsinki");
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
			LOG.info("/topvan - [GET] Fetching top drivers for vantaa");
			return new ResponseEntity<Object>(topvan, HttpStatus.OK);
		} else {
			LOG.debug("/topvan - [GET] Error fetching top drivers for vantaa");
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
			LOG.info("/avg/{name} - [GET] Fetching driver specific avarage statistic for "
					+ name);
			return new ResponseEntity<Object>(avgDriver, HttpStatus.OK);
		} else {
			LOG.debug("/avg/{name} - [GET] Error fetching driver specific avarage statistic  for "
					+ name);
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
			LOG.info("/deliveries - [GET] Fetching all deliveries");
			return new ResponseEntity<Object>(d, HttpStatus.OK);
		} else {
			LOG.debug("/deliveries - [GET] Error fetching all deliveries");
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/motd/{message}", produces = "application/json", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Object> updateMotd(
			@RequestHeader(value = "Secret") String secret_word,
			@PathVariable("message") String message) throws IOException {

		if (secret.equals(secret_word)) {

			msg.setMessage(message);
			DateTimeFormatter dtf = DateTimeFormatter
					.ofPattern("M/d/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			msg.setLast_updated(dtf.format(now));

			System.setProperty("info.motd.message", message);
			System.setProperty("info.motd.updated", dtf.format(now));

			LOG.info("/motd - [PUT] Updated message of the day");
			return new ResponseEntity<Object>(msg, HttpStatus.OK);
		} else {
			LOG.debug("/motd -[PUT]  Error setting new message of the day");
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/motd", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getMotd(
			@RequestHeader(value = "Secret") String secret_word)
			throws IOException {

		if (secret.equals(secret_word)) {

			LOG.info("/motd - [GET] Fetched message of the day");
			return new ResponseEntity<Object>(msg, HttpStatus.OK);
		} else {
			LOG.debug("/motd - [GET] Error fetching new message of the day");
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}

	/*
	 * Returns Sakko Count
	 */
	
	@RequestMapping(value = "/sakko", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getSakot(
			@RequestHeader(value = "Secret") String secret_word)
			throws Exception {

		if (secret.equals(secret_word)) {

			SakkoService s = new SakkoService();
			Sakko sakko = s.readSakko();

			LOG.info("/sakko - [GET] Fetched sakot, current sakko count: " + sakko.getSakko_count());
			return new ResponseEntity<Object>(sakko, HttpStatus.OK);
		} else {
			LOG.debug("/sakko - [GET] Error while fetching sakot");
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	/*
	 * Updates Sakko count +1 and returns updated sakko count
	 */

	@RequestMapping(value = "/sakko", produces = "application/json", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Object> putSakot(
			@RequestHeader(value = "Secret") String secret_word)
			throws Exception {

		if (secret.equals(secret_word)) {
		
			SakkoService s = new SakkoService();
			Sakko sakko = s.writeSakko();

			LOG.info("/sakko - [PUT]Updated sakot, new sakko count: " + sakko.getSakko_count());
			return new ResponseEntity<Object>(sakko, HttpStatus.OK);
		} else {
			LOG.debug("/sakko - [PUT]Error while fetching sakot");
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	/*
	 * Resets Sakko count to Zero
	 */

	@RequestMapping(value = "/sakko", produces = "application/json", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Object> nollaaSakot(
			@RequestHeader(value = "Secret") String secret_word)
			throws Exception {

		if (secret.equals(secret_word)) {
			SakkoService s = new SakkoService();
			Sakko sakko = s.resetSakko();
			LOG.info("/sakko - [DELETE] Set Sakko count to 0");
			return new ResponseEntity<Object>(sakko, HttpStatus.OK);
		} else {
			LOG.debug("/sakko - [DELETE]Error while fetching sakot");
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}

	
	@RequestMapping(value = "/sakkothreshold/{value}", produces = "application/json", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Object> setSakkoThreshold(
			@RequestHeader(value = "Secret") String secret_word, @PathVariable("value") String sakko_threshold)
			throws IOException {

		if (secret.equals(secret_word)) {			
			System.setProperty("info.sakko.threshold", sakko_threshold);
			LOG.info("/sakkothreshold - [PUT] set new sakko threshold");
			return new ResponseEntity<Object>(sakko_threshold, HttpStatus.OK);
		} else {
			LOG.debug("/sakkothreshold - [PUT] Error setting new sakko threshold");
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	/*
	 * Redirects /info GET to debug/info
	 */
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String redirectToInfo() {
		LOG.info("/info - [GET] Redirecting to Rest API debug/info");
		return "redirect:debug/info";
	}
	
	@RequestMapping(value = "/restapi", method = RequestMethod.GET)
	public String rest() {
		LOG.info("/restapi - [GET] Rest API");
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String restapi() {
		LOG.info("/ - Index [GET]");
		return "index";
	}

}
