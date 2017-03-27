package com.fineline.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fineline.domain.Topten;
import com.fineline.domain.WorkDay;
import com.fineline.service.GoogleUploader;
import com.fineline.service.WorkDayDAO;
import com.fineline.domain.WorkDay_GoogleSheets;
import com.fineline.service.GoogleDataFetcher;
import com.google.gdata.data.spreadsheet.Data;
import com.google.gdata.util.ServiceException;

@Controller
@ResponseBody
public class ARController {
	/*
	 * @Inject WorkDayDAO workdaydao;
	 */
	@RequestMapping(value = "/top", produces = "application/json", method = RequestMethod.GET)
	public List<Topten> topSeven() throws IOException {
		List<Topten> topSeven = GoogleDataFetcher.topten();
		return topSeven;
	}

	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insertData() throws IOException, ServiceException{
		
		GoogleUploader google = new GoogleUploader();
		List<Data> datalist = new ArrayList<Data>();
		google.writeSomething(datalist);
		
	}

	@RequestMapping(value = "/listall", produces = "application/json", method = RequestMethod.GET)
	public List<WorkDay_GoogleSheets> listEverything() throws IOException {
		List<WorkDay_GoogleSheets> listall = GoogleDataFetcher.listAll();
		return listall;
	}

	@RequestMapping(value = "/tophel", produces = "application/json", method = RequestMethod.GET)
	public List<Topten> topHelsinki() throws IOException {
		List<Topten> tophel = GoogleDataFetcher.tophel();
		return tophel;
	}

	@RequestMapping(value = "/topvan", produces = "application/json", method = RequestMethod.GET)
	public List<Topten> topVantaa() throws IOException {
		List<Topten> topvan = GoogleDataFetcher.topvan();
		System.out.println();
		return topvan;
	}

	@RequestMapping(value = "/avg/{name}", method = RequestMethod.GET)
	@ResponseBody
	public List<Topten> getAvgFromDriver(@PathVariable("name") String name)
			throws IOException {
		List<Topten> avgDriver = GoogleDataFetcher.driverAvg(name);
		return avgDriver;
	}

	/*
	 * @RequestMapping(value = "/workdays", produces = "application/json",
	 * method = RequestMethod.GET) public @ResponseBody ResponseEntity<?>
	 * listAllWorkDays() {
	 * 
	 * List<WorkDay> workdays = workdaydao.listAllWorkDays();
	 * 
	 * // Jos ei yhtään kyselyä löydy, palauta 404 if (workdays.size() == 0) {
	 * return new ResponseEntity<String>(HttpStatus.NOT_FOUND); }
	 * 
	 * return new ResponseEntity<Object>(workdays, HttpStatus.OK); }
	 */

}