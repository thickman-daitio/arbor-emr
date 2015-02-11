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

	
	@RequestMapping(value = { "/edituser.html", "/edituser.html{?id=}" }, method = RequestMethod.GET) 
	public ModelAndView editUserFormGet(@RequestParam(value = "id", required = false) String id) {
		ModelAndView model = new ModelAndView("editUser");
		
		if (id != null) {
			User u = new User();
			startMongoSession();
			MongoUserDAO uDAO = new MongoUserDAO(mongo.getInstance());
			u = uDAO.getUserByUsername(id);
			model.addObject("username", u.getUsername());
			model.addObject("firstName", u.getFirstName());
			model.addObject("lastName", u.getLastName());
			mongo.close();
			userId = id;
		}

		return model;	
	}
	
	@RequestMapping(value = "/edituser.html", method = RequestMethod.POST)
	public ModelAndView editUserFormPost(@RequestParam String action, @ModelAttribute("user") User user) {
		ModelAndView model = new ModelAndView("editUser");
		
		if (userId != null && action.equals("Delete User")) {
			User u = new User();
			startMongoSession();
			MongoUserDAO uDAO = new MongoUserDAO(mongo.getInstance());
			u = uDAO.getUserByUsername(userId);
			uDAO.deleteUser(u);
			mongo.close();
		}
		
		else if (action.equals("Submit")) {
			User u = new User();
			startMongoSession();
			MongoUserDAO uDAO = new MongoUserDAO(mongo.getInstance());
			u = uDAO.getUserByUsername(userId);
			u.setFirstName(user.getFirstName());
			u.setLastName(user.getLastName());
					
			// TODO: Password setting
			
			uDAO.updateUser(u);
			mongo.close();
		}
		
		return model;
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public ModelAndView loginFormGet() {
		ModelAndView model = new ModelAndView("loginForm");
		ModelAndView model = new ModelAndView("loginDash");
		logOut();
		return model;
	}
@@ -127,7 +40,7 @@ public class MainController {
			user.setLastName(compare.getLastName());
			model = new ModelAndView("home");
		} else {
			model = new ModelAndView("loginForm");
			model = new ModelAndView("loginDash");
			model.addObject("error", "Invalid username or password.");
		}
		mongo.close();
@@ -141,24 +54,4 @@ public class MainController {

		return model;
	}
	
	
	public void populateUserList(ModelAndView model) {
		// Requires a mongo session to be open already
		MongoUserDAO uDAO = new MongoUserDAO(mongo.getInstance());
		model.addObject("userList", User.getAllUsersRepeater(uDAO.getAllUsers()));
	}
	
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
}