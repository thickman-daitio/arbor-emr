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
	@RequestMapping(value = "/<html-page-url>.html", method = RequestMethod.GET)
	public ModelAndView addPatientFormGet() {
		ModelAndView model = new ModelAndView("<jsp-page>");

		return model;
	}

	@RequestMapping(value = "/<html-page-url>.html", method = RequestMethod.POST)
	public ModelAndView addPatientFormPost() {
		ModelAndView model = new ModelAndView("<jsp-page>");

		startMongoSession();
		
		// Mongo stuff goes here
		 
		mongo.close();

		return model;
	}
	*/
}
