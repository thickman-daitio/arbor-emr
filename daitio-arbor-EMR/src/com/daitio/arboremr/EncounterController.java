package com.daitio.arboremr;

import java.net.UnknownHostException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EncounterController {

	private MongoConnector mongo;

	@RequestMapping(value = "/encounter.html", method = RequestMethod.GET)
	public ModelAndView addUserFormGet() {
		ModelAndView model1 = new ModelAndView("encounter");

		try {
			mongo = new MongoConnector();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return model1;
	}

	@RequestMapping(value = "/encounter.html", method = RequestMethod.POST)
	public ModelAndView addUserFormPost(@ModelAttribute("record") Encounter record) {

		ModelAndView model = new ModelAndView("encounter");

		MongoEncounterDAO uDAO = new MongoEncounterDAO(mongo.getInstance());
		uDAO.createEncounter(record);

		return model;
	}
}
