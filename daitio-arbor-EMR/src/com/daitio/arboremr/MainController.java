package com.daitio.arboremr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/* FOR FITBIT */
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;
import java.util.Scanner;

/*************/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

//import com.daitio.arboremr.fitbit.FitbitAPIInterface;
import com.daitio.arboremr.fitbit.FitbitAPI;
import com.daitio.arboremr.patient.MongoPatientDAO;
import com.daitio.arboremr.patient.Patient;
import com.daitio.arboremr.patient.Patient.Status;
import com.daitio.arboremr.user.MongoUserDAO;
import com.daitio.arboremr.user.User;

@Controller
@SessionAttributes("sessionUser")
public class MainController extends MasterController {
	private static final String PROTECTED_RESOURCE_URL = "https://api.fitbit.com/1/user/-/profile.json";
	
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public ModelAndView loginFormGet() {
		ModelAndView model = new ModelAndView("login");
		logOut();
		return model;
	}

/*	@RequestMapping(value = "api-test.html", method = RequestMethod.POST)
	public ModelAndView apiTest(@RequestParam String action, HttpServletRequest getQuery) {
		ModelAndView model = new ModelAndView();
		model = new ModelAndView("api-test");

		if (action.equals("Do API Call")) {
			populatePatientList(model);

			// CREATE THE OAUTH OBJECT FROM FITBIT API CLASS
			OAuthService service = new ServiceBuilder()
					.provider(FitbitAPI.class)
					.apiKey("1c5c4d0bc7fb4758874a751fb46a1a60")
					.apiSecret("b64533cf350147289398208d757075b8")
					.callback("http://localhost:8080/daitio-arbor-health/api-test.html")
					.build();

			// GETS THE REQUEST TOKEN
			Token requestToken = service.getRequestToken();

			// MAKE USER VALIDATE URL
			String authUrl = service.getAuthorizationUrl(requestToken);
			
			
			//if (action.equals("Get Account Info")) {

			Verifier v = new Verifier("ENTER VERIFIER VARIABLE FROM QUERY STRING");
			Token accessToken = service.getAccessToken(requestToken, v); // the
																			// requestToken
																			// you
																			// had
																			// from
																			// step
																			// 2

			OAuthRequest request = new OAuthRequest(Verb.GET,
					"https://api.fitbit.com");
			service.signRequest(accessToken, request); // the access token
														// from step 4
			Response response = request.send();
			System.out.println(response.getBody()); 

			try {
				model.addObject("response", requestToken.toString());
			} catch (Exception e) {
				e.printStackTrace();
				model.addObject("response", e.toString());
			}
			//}

			try {
				model.addObject("result", authUrl);
			} catch (Exception e) {
				e.printStackTrace();
				model.addObject("result", e.toString());
			}
		}
		
		
		return model;
	}

	@RequestMapping(value = "api-test.html", method = RequestMethod.GET)
	public ModelAndView apiTest() {
		ModelAndView model = new ModelAndView();

		model = new ModelAndView("api-test");

		return model;
	} */

	@RequestMapping(value = "/home.html", method = RequestMethod.POST)
	public ModelAndView loginFormPost(@ModelAttribute("user") User user,
			@RequestParam String action, HttpServletRequest request) {

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
			else if (p.getStatus()
					.equals(Status.STATUS_NEUTRAL.getStatusCode()))
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