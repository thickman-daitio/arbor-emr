package com.daitio.arboremr;

import java.net.UnknownHostException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	private MongoConnector mongo;

	@RequestMapping(value = "/adduser.html", method = RequestMethod.GET)
	public ModelAndView addUserFormGet() {
		ModelAndView model1 = new ModelAndView("addUser");

		try {
			mongo = new MongoConnector();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return model1;
	}

	@RequestMapping(value = "/adduser.html", method = RequestMethod.POST)
	public ModelAndView addUserFormPost(@ModelAttribute("user") User user) {

		ModelAndView model = new ModelAndView("addUser");

		MongoUserDAO uDAO = new MongoUserDAO(mongo.getInstance());
		uDAO.createUser(user);

		return model;
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public ModelAndView loginFormGet() {
		ModelAndView model = new ModelAndView("loginForm");

		try {
			mongo = new MongoConnector();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/home.html", method = RequestMethod.POST)
	public ModelAndView loginFormPost(@ModelAttribute("user") User user) {

		ModelAndView model = new ModelAndView();

		MongoUserDAO uDAO = new MongoUserDAO(mongo.getInstance());
		User compare = uDAO.getUserByUsername(user.getUsername());

		if (compare.getPassword().equals(User.hashPassword(user.getPassword()))) {
			user.setFirstName(compare.getFirstName());
			user.setLastName(compare.getLastName());
			model = new ModelAndView("home");
		} else
			model = new ModelAndView("loginForm");

		return model;
	}


	@RequestMapping(value = "/home.html", method = RequestMethod.GET)
	public ModelAndView homeFormGet() {
		ModelAndView model = new ModelAndView();
		
		return model;
	}
}
