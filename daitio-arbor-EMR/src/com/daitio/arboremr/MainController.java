package com.daitio.arboremr;

import java.net.UnknownHostException;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	MongoConnector mongo;
	
	@RequestMapping(value = "/adduser.html", method = RequestMethod.GET)
	public ModelAndView addUserForm() {
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
		
		ModelAndView model1 = new ModelAndView("addUser");
		
		MongoUserDAO uDAO = new MongoUserDAO(mongo.getInstance());
		uDAO.createUser(user);
				
		return model1;
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public ModelAndView getAdmissionForm() {
		ModelAndView model1 = new ModelAndView("loginForm");
		
		try {
			mongo = new MongoConnector();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return model1;
	}

	@RequestMapping(value = "/myPage.html", method = RequestMethod.POST)
	public ModelAndView submitAdmissionForm(@ModelAttribute("user") User user,
			BindingResult result) {

		if (result.hasErrors()) {
			ModelAndView model1 = new ModelAndView("loginForm");
			return model1;
		}
		
		MongoUserDAO uDAO = new MongoUserDAO(mongo.getInstance());
		User compare = uDAO.getUserByUsername(user.getUsername());
		
		if (compare.getPassword().equals(User.hashPassword(user.getPassword()))) {
			ModelAndView model1 = new ModelAndView("myPageSuccess");
			return model1;
		} else {
			ModelAndView model1 = new ModelAndView("loginForm");
			return model1;
		}
		
	}

}
