package com.daitio.arboremr.encounter;

import java.net.UnknownHostException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.daitio.arboremr.MongoConnector;

@Controller
public class EncounterController {

	private MongoConnector mongo;

	@RequestMapping(value = "/encounter.html", method = RequestMethod.GET)
	public ModelAndView addEncounterFormGet() {
		startMongoSession();
		
		ModelAndView model1 = new ModelAndView("encounter");

		try {
			mongo = new MongoConnector();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		mongo.close();
		return model1;
	}

	@RequestMapping(value = "/encounter.html", method = RequestMethod.POST)
	public ModelAndView addEncounterFormPost(@ModelAttribute("record") Encounter record) {

		ModelAndView model = new ModelAndView("encounter");

		return model;
	}
	
	public void startMongoSession() {
		try {
			mongo = new MongoConnector();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
}
