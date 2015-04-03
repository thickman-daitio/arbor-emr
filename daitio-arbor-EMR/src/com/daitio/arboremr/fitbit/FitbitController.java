package com.daitio.arboremr.fitbit;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.daitio.arboremr.MasterController;
import com.daitio.arboremr.patient.MongoPatientDAO;
import com.daitio.arboremr.patient.Patient;

@Controller
@SessionAttributes("sessionService")
public class FitbitController extends MasterController {

	OAuthService sessionService = FitbitAPIInterface.getOAuthService();
	Token requestToken = sessionService.getRequestToken();

	@RequestMapping(value = { "/login-fitbit" }, method = RequestMethod.GET)
	public String login(WebRequest request, HttpServletResponse res) {
		return "redirect:" + sessionService.getAuthorizationUrl(requestToken);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = { "/fitbit-callback" }, method = RequestMethod.GET)
	public ModelAndView callback(
			@RequestParam(value = "code", required = false) String oauthVerifier,
			WebRequest request, HttpServletRequest req) {

		ModelAndView mav = new ModelAndView("api-test");

		// Get access token
		String code = "";
		Enumeration paramEnum = req.getParameterNames();
		while (paramEnum.hasMoreElements()) {
			String name = (String) paramEnum.nextElement();
			if (name.equals("oauth_verifier")) {
				code = req.getParameter(name);
			}
		}
		Verifier verifier = new Verifier(code);
		Token accessToken = sessionService.getAccessToken(requestToken, verifier);
		
		// Get API requests
		Response user = FitbitAPIInterface.getUserRequest(accessToken);
		Response weight = FitbitAPIInterface.getWeightRequest(accessToken);
		
		// Display elements on page
		mav.addObject("verifier", verifier);
		mav.addObject("requestToken", requestToken);
		mav.addObject("accessToken", accessToken);
		mav.addObject("responseBody", user.getBody());
		mav.addObject("weight", weight.getBody());
		
		// Set patient data from Fitbit
		Patient p = new Patient();
		p.parseFitbitProfile(user.getBody());
		p.setWeightList(weight.getBody());

		// Save patient in MongoDB
		startMongoSession();
		MongoPatientDAO pDAO = new MongoPatientDAO(mongo.getInstance());
		p.setAccessToken(accessToken);
		pDAO.createPatient(p);
		mongo.close();

		return mav;
	}
}