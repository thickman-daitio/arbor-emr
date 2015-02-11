package com.daitio.arboremr;

import java.net.UnknownHostException;

public class MasterController {
	
	protected MongoConnector mongo;
	
	public void startMongoSession() {
		try {
			mongo = new MongoConnector();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void logOut() {
		if (mongo != null)
			mongo.close();
	}
	
	/*
	 * Template methods for pages
	 */
	/*
	@RequestMapping(value = "/addpatient.html", method = RequestMethod.GET)
	public ModelAndView addPatientFormGet() {
		ModelAndView model = new ModelAndView("loginForm");

		return model;
	}

	@RequestMapping(value = "/addpatient.html", method = RequestMethod.POST)
	public ModelAndView addPatientFormPost() {
		ModelAndView model = new ModelAndView("loginForm");

		startMongoSession();
		
		// Mongo stuff goes here
		 
		mongo.close();

		return model;
	}
	*/
}
