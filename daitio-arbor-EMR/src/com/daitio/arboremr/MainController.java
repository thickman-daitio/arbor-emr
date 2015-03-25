package com.daitio.arboremr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.daitio.arboremr.fitbit.FitbitAPIInterface;
import com.daitio.arboremr.patient.MongoPatientDAO;
import com.daitio.arboremr.patient.Patient;
import com.daitio.arboremr.patient.Patient.Status;
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
	
	@RequestMapping(value = "api-test.html", method = RequestMethod.POST)
	public ModelAndView apiTest(@RequestParam String action) {
		ModelAndView model = new ModelAndView();
		
		model = new ModelAndView("api-test");

		if (action.equals("Do API Call")) {
			populatePatientList(model);
						
			FitbitAPIInterface api = new FitbitAPIInterface();
			
			try {
				model.addObject("result", api.execute());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return model;
	}
	
	@RequestMapping(value = "api-test.html", method = RequestMethod.GET)
	public ModelAndView apiTest() {
		ModelAndView model = new ModelAndView();
		
		model = new ModelAndView("api-test");
				
		return model;
	}

	@RequestMapping(value = "/home.html", method = RequestMethod.POST)
	public ModelAndView loginFormPost(@ModelAttribute("user") User user,
			@RequestParam String action, 
			HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView();

		startMongoSession();
		MongoUserDAO uDAO = new MongoUserDAO(mongo.getInstance());
		User compare = uDAO.getUserByUsername(user.getUsername());

		if (compare.getPassword().equals(User.hashPassword(user.getPassword()))) {
			user.setFirstName(compare.getFirstName());
			user.setLastName(compare.getLastName());

			model = new ModelAndView("doctor-home");
			populatePatientList(model);
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
				
		populatePatientList(model);
		
		return model;
	}
	
	private void populatePatientList(ModelAndView model) {
		startMongoSession();
		MongoPatientDAO pDAO = new MongoPatientDAO(mongo.getInstance());
		
		int iGoodCount = 0;
		int iNeutralCount = 0;
		int iBadCount = 0;
		
		List<Patient> pList = pDAO.getAllPatients();
		for (int i = 0; i < pList.size(); i++) {
			Patient p = pList.get(i);
			if (p.getStatus().equals(Status.STATUS_GOOD.getStatusCode()))
				iGoodCount++;
			else if (p.getStatus().equals(Status.STATUS_NEUTRAL.getStatusCode()))
				iNeutralCount++;
			else if (p.getStatus().equals(Status.STATUS_BAD.getStatusCode()))
				iBadCount++;
		}
		model.addObject("patientList", pList);		
		model.addObject("bad", iBadCount);
		model.addObject("neutral", iNeutralCount);
		model.addObject("good", iGoodCount);
		
		mongo.close();
	}
}