package com.fineline.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fineline.domain.WorkDay;
import com.fineline.service.WorkDayDAO;


@Controller
@ResponseBody
public class ARController {
	
	@Inject
	WorkDayDAO workdaydao;
	
	@RequestMapping("*")
	public String hello(){
		
		return "Demo";
		
	}
	
	@RequestMapping(value = "/workdays", produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> listAllWorkDays() {

		List<WorkDay> workdays = workdaydao.listAllWorkDays();

		// Jos ei yhtään kyselyä löydy, palauta 404
		if (workdays.size() == 0) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(workdays, HttpStatus.OK);
	}

}
