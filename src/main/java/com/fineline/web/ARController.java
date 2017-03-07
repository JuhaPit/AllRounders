package com.fineline.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fineline.domain.Topten;
import com.fineline.domain.WorkDay;
import com.fineline.service.WorkDayDAO;
import com.fineline.domain.WorkDay_GoogleSheets;
import com.fineline.service.GoogleDataFetcher;


@Controller
@ResponseBody
public class ARController {
	/*
	@Inject
	WorkDayDAO workdaydao;
	*/
	@RequestMapping(value = "/test", produces = "application/json", method = RequestMethod.GET)
	public List<Topten> hello() throws IOException{
		System.out.println("ARController.hello()");
		//GoogleDataFetcher.getSheetsService();
		//GoogleDataFetcher.authorize();
		List<Topten> topten = GoogleDataFetcher.topten();
		
		return topten;
		
	}
	
	@RequestMapping(value = "/listall", produces = "application/json", method = RequestMethod.GET)
	public List<WorkDay_GoogleSheets> listEverything() throws IOException{
		
		//GoogleDataFetcher.getSheetsService();
		//GoogleDataFetcher.authorize();
		List<WorkDay_GoogleSheets> listall = GoogleDataFetcher.listAll();
		return listall;
		
	}
	
	@RequestMapping(value = "/tophel", produces = "application/json", method = RequestMethod.GET)
	public List<Topten> topHelsinki() throws IOException{
		
		List<Topten> tophel = GoogleDataFetcher.tophel();
		return tophel;
		
	}
	@RequestMapping(value = "/topvan", produces = "application/json", method = RequestMethod.GET)
	public List<Topten> topVantaa() throws IOException{
		
		List<Topten> topvan = GoogleDataFetcher.topvan();
		return topvan;
		
	}
	
	
	/*
	@RequestMapping(value = "/workdays", produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> listAllWorkDays() {

		List<WorkDay> workdays = workdaydao.listAllWorkDays();

		// Jos ei yhtään kyselyä löydy, palauta 404
		if (workdays.size() == 0) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(workdays, HttpStatus.OK);
	}
	*/
}
