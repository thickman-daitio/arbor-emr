package com.daitio.arboremr;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.daitio.arboremr.patient.MongoPatientDAO;
import com.daitio.arboremr.patient.Patient;
import com.daitio.arboremr.user.MongoUserDAO;
import com.daitio.arboremr.user.User;

@Controller
@SessionAttributes("sessionUser")
public class MainController extends MasterController {

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public ModelAndView loginFormGet() {
		ModelAndView model = new ModelAndView("login");
		logOut();
		return model;
	}

	@RequestMapping(value = "/home.html", method = RequestMethod.POST)
	public ModelAndView loginFormPost(@ModelAttribute("user") User user,
			HttpServletRequest request) {

		startMongoSession();

		ModelAndView model = new ModelAndView();

		MongoUserDAO uDAO = new MongoUserDAO(mongo.getInstance());
		User compare = uDAO.getUserByUsername(user.getUsername());

		if (compare.getPassword().equals(User.hashPassword(user.getPassword()))) {
			user.setFirstName(compare.getFirstName());
			user.setLastName(compare.getLastName());

			model = new ModelAndView("doctor-home");
		} else {
			model = new ModelAndView("login");
			model.addObject("error", "Invalid username or password.");
		}
		mongo.close();

		model.addObject("sessionUser", user);

		return model;
	}

	@RequestMapping(value = "/home.html", method = RequestMethod.GET)
	public ModelAndView homeFormGet() {
		ModelAndView model = new ModelAndView("doctor-home");
		
		startMongoSession();
		populatePatientList(model);
		mongo.close();
		
		return model;
	}
	
	public void populatePatientList(ModelAndView model) {
		// Requires a mongo session to be open already
		if (mongo != null) {
			MongoPatientDAO pDAO = new MongoPatientDAO(mongo.getInstance());
			model.addObject("patientList", Patient.getAllPatientsRepeater(pDAO.getAllPatients()));
		}
	}
}