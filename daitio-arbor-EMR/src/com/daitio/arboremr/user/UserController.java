package com.daitio.arboremr.user;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.daitio.arboremr.MasterController;

@Controller
public class UserController extends MasterController {

	private String userId;

	@RequestMapping(value = { "/edituser.html", "/edituser.html{?id=}" }, method = RequestMethod.GET)
	public ModelAndView editUserFormGet(
			@RequestParam(value = "id", required = false) String id) {
		ModelAndView model = new ModelAndView("editUser");

		if (id != null) {
			User u = new User();
			startMongoSession();

			MongoUserDAO uDAO = new MongoUserDAO(mongo.getInstance());
			u = uDAO.getUser(new ObjectId(id));

			model.addObject("username", u.getUsername());
			model.addObject("firstName", u.getFirstName());
			model.addObject("lastName", u.getLastName());

			mongo.close();
			userId = id;
		}

		return model;
	}

	@RequestMapping(value = "/edituser.html", method = RequestMethod.POST)
	public ModelAndView editUserFormPost(@RequestParam String action,
			@ModelAttribute("user") User user) {
		ModelAndView model = new ModelAndView("edit-user");

		if (userId != null && action.equals("Delete User")) {

			startMongoSession();
			MongoUserDAO uDAO = new MongoUserDAO(mongo.getInstance());
			uDAO.deleteUser(new ObjectId(userId));

			mongo.close();
		}

		else if (action.equals("Submit")) {
			User u = new User();
			startMongoSession();

			MongoUserDAO uDAO = new MongoUserDAO(mongo.getInstance());
			u = uDAO.getUser(new ObjectId(userId));
			u.setFirstName(user.getFirstName());
			u.setLastName(user.getLastName());

			// TODO: Password setting

			uDAO.updateUser(u);
			mongo.close();
		}

		return model;
	}

	@RequestMapping(value = "/adduser.html", method = RequestMethod.GET)
	public ModelAndView addUserFormGet() {
		ModelAndView model = new ModelAndView("add-user");

		startMongoSession();
		populateUserList(model);
		mongo.close();

		return model;
	}

	@RequestMapping(value = "/adduser.html", method = RequestMethod.POST)
	public ModelAndView addUserFormPost(@ModelAttribute("user") User user,
			@RequestParam String action) {

		startMongoSession();

		ModelAndView model = new ModelAndView("add-user");
		MongoUserDAO uDAO = new MongoUserDAO(mongo.getInstance());

		User u = new User();

		if (user.getPassword() == null) {
			model.addObject("error",
					"Password must be at least seven characters long.");
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

	public void populateUserList(ModelAndView model) {
		// Requires a mongo session to be open already
		if (mongo != null) {
			MongoUserDAO uDAO = new MongoUserDAO(mongo.getInstance());
			model.addObject("userList",
					User.getAllUsersRepeater(uDAO.getAllUsers()));
		}
	}
}
