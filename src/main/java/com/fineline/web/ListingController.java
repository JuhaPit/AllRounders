package com.fineline.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
public class ListingController {
	@RequestMapping("/listing")
	public String listing() {
		return "listing";
	}
	
	@RequestMapping("/mainpage")
	public String mainpage() {
		return "mainpage";
	}	
	
}

