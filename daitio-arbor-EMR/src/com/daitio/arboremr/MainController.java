package com.daitio.arboremr;

import java.net.UnknownHostException;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

@Controller
public class MainController {

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public ModelAndView getAdmissionForm() {

		ModelAndView model1 = new ModelAndView("loginForm");

		return model1;
	}

	@RequestMapping(value = "/myPage.html", method = RequestMethod.POST)
	public ModelAndView submitAdmissionForm(@ModelAttribute("user1") User user,
			BindingResult result) {

		if (result.hasErrors()) {

			ModelAndView model1 = new ModelAndView("loginForm");
			return model1;
		}

		try {
			MongoConnector mdbConn = new MongoConnector();
			mdbConn.addUser();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		ModelAndView model1 = new ModelAndView("myPageSuccess");
		return model1;
	}

}
