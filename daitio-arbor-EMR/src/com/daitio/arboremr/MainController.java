package com.daitio.arboremr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.daitio.arboremr.patient.MongoPatientDAO;
import com.daitio.arboremr.patient.Patient;
import com.daitio.arboremr.user.MongoUserDAO;
import com.daitio.arboremr.user.User;

@Controller
public class MainController extends MasterController {

	private MongoConnector mongo;
	private String userId;
	
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public ModelAndView loginFormGet() {
		ModelAndView model = new ModelAndView("loginDash");
		logOut();
		return model;
	}

	@RequestMapping(value = "/home.html", method = RequestMethod.POST)
	public ModelAndView loginFormPost(@ModelAttribute("user") User user) {

		startMongoSession();
		
		ModelAndView model = new ModelAndView();

		MongoUserDAO uDAO = new MongoUserDAO(mongo.getInstance());
		User compare = uDAO.getUserByUsername(user.getUsername());

		if (compare.getPassword().equals(User.hashPassword(user.getPassword()))) {
			user.setFirstName(compare.getFirstName());
			user.setLastName(compare.getLastName());
			model = new ModelAndView("home");
		} else {
			model = new ModelAndView("loginDash");
			model.addObject("error", "Invalid username or password.");
		}
		mongo.close();
		
		return model;
	}

	@RequestMapping(value = "/home.html", method = RequestMethod.GET)
	public ModelAndView homeFormGet() {
		ModelAndView model = new ModelAndView();

		return model;
	}
}
