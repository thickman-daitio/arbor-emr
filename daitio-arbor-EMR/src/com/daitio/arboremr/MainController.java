package com.daitio.arboremr;

import java.net.UnknownHostException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	private MongoConnector mongo;
	private String userId;

	@RequestMapping(value = "/adduser.html", method = RequestMethod.GET)
	public ModelAndView addUserFormGet() {
		ModelAndView model = new ModelAndView("addUser");

		startMongoSession();		
		populateUserList(model);
		mongo.close();
		
		return model;
	}
	
	@RequestMapping(value = "/adduser.html", method = RequestMethod.POST)
	public ModelAndView addUserFormPost(@ModelAttribute("user") User user,
			@RequestParam String action) {

		startMongoSession();
		
		ModelAndView model = new ModelAndView("addUser");
		MongoUserDAO uDAO = new MongoUserDAO(mongo.getInstance());
		
		User u = new User();
		
		if (user.getPassword() == null) {
			model.addObject("error", "Password must be at least seven characters long.");
			populateUserList(model);
			mongo.close();
			return model;
		}
		
		if (action.equals("Submit"))
			u = uDAO.createUser(user);
		
		if (u.getUsername().equals("")) 
			model.addObject("error", "Username already in use.");
			
		populateUserList(model);
		mongo.close();
		
		return model;
	}
	
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
			model = new ModelAndView("loginForm");
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
