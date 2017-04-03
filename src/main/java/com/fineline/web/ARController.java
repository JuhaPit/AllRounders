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

import com.fineline.domain.SheetsRow;
import com.fineline.domain.Topten;
import com.fineline.service.GoogleUploader;
import com.fineline.domain.WorkDay_GoogleSheets;
import com.fineline.service.GoogleDataFetcher;
import com.google.gdata.util.ServiceException;

@CrossOrigin
@Controller
public class ARController {
	/*
	 * @Inject WorkDayDAO workdaydao;
	 */

	private static String secret = "B55AF471FFE583FEB96EF0788EBF9FCBA678592F70CB8508F5D43ED64A1C0E90B598C627389A913776EEE1AFEFEDE85AB82CB8AD46DF7AE3CE24072196738A9B";

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

	@RequestMapping(value = "/insert", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> insertData(@RequestBody SheetsRow row)
			throws IOException, ServiceException {

		GoogleUploader.insert(row);

		return new ResponseEntity<Object>(HttpStatus.OK);
	}

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

	@RequestMapping("/restapi")
	public String rest() {
		return "index";
	}

	@RequestMapping("/")
	public String restapi() {
		return "index";
	}

}
