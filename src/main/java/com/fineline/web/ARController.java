package com.fineline.web;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

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

	@RequestMapping(value = "/motd", produces = "application/json", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Object> updateMotd(
			@RequestHeader(value = "Secret") String secret_word,
			@RequestBody String message) throws IOException {

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
			throws IOException {

		if (secret.equals(secret_word)) {
			Sakko sssss = new Sakko();
			Path path;
			try {
				path = Paths.get(getClass().getClassLoader()
						.getResource("sakko.txt").toURI());
				StringBuilder data = new StringBuilder();
				Stream<String> lines = Files.lines(path);
				lines.forEach(line -> data.append(line).append(""));
				lines.close();
				System.out.println("Sakko count: " + data);

				sssss.setSakko_count(data.toString());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			LOG.info("/sakko - [GET] Fetched sakot");
			return new ResponseEntity<Object>(sssss, HttpStatus.OK);
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
			Sakko sss = new Sakko();
			Path path;
			try {

				path = Paths.get(getClass().getClassLoader()
						.getResource("sakko.txt").toURI());
				StringBuilder data = new StringBuilder();
				Stream<String> lines = Files.lines(path);
				lines.forEach(line -> data.append(line));
				lines.close();
				System.out.println(path.toString());

				System.out.println("Sakko count: " + data);

				String myString = data.toString();

				int new_sakko_count = Integer.parseInt(myString);
				new_sakko_count++;
				System.out.println(new_sakko_count);

				try {
					FileWriter writer = new FileWriter(path.toString());
					writer.write("" + new_sakko_count);
					writer.close();
					sss.setSakko_count("" + new_sakko_count);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			LOG.info("/sakko - [PUT]Updated sakot");
			return new ResponseEntity<Object>(sss, HttpStatus.OK);
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

			Path path;
			Sakko ssss = new Sakko();
			try {

				path = Paths.get(getClass().getClassLoader()
						.getResource("sakko.txt").toURI());
				StringBuilder data = new StringBuilder();
				Stream<String> lines = Files.lines(path);
				lines.forEach(line -> data.append(line));
				lines.close();
				System.out.println(path.toString());

				String myString = data.toString();

				int new_sakko_count = Integer.parseInt(myString);
				new_sakko_count++;
				System.out.println(new_sakko_count);

				try {
					FileWriter writer = new FileWriter(path.toString());
					writer.write("" + 0);
					writer.close();
					ssss.setSakko_count("" + 0);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			LOG.info("/sakko - [DELETE] Set Sakko count to 0");
			return new ResponseEntity<Object>(ssss, HttpStatus.OK);
		} else {
			LOG.debug("/sakko - [DELETE]Error while fetching sakot");
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping("/restapi")
	public String rest() {
		LOG.info("/restapi - Rest API");
		return "index";
	}

	@RequestMapping("/")
	public String restapi() {
		LOG.info("/ - Index");
		return "index";
	}

}
