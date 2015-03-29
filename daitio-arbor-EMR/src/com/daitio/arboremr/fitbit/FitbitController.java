package com.daitio.arboremr.fitbit;


import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.daitio.arboremr.fitbit.FitbitAPI;

/*
import edu.seua.scribe.OAuthServiceProvider;
import static edu.seua.scribe.web.SessionAttributes.*;*/

@Controller
@SessionAttributes("sessionService")
public class FitbitController {
	
	//@Autowired
	//@Qualifier("facebookServiceProvider")
	// CREATE THE OAUTH OBJECT FROM FITBIT API CLASS
	OAuthService sessionService = new ServiceBuilder()
			.provider(FitbitAPI.class)
			.apiKey("1c5c4d0bc7fb4758874a751fb46a1a60")
			.apiSecret("b64533cf350147289398208d757075b8")
			.callback("http://localhost:8080/daitio-arbor-health/fitbit-callback.html")
			.build();
	
	Token requestToken = sessionService.getRequestToken();
		
	@RequestMapping(value={"/login-fitbit"}, method = RequestMethod.GET)
	public String login(WebRequest request, HttpServletResponse res) {
		// getting request and access token from session
		//Token accessToken = (Token) request.getAttribute(ATTR_OAUTH_ACCESS_TOKEN, SCOPE_SESSION);
		
		
		if(requestToken == null) {
			// generate new request token
			OAuthService service = new ServiceBuilder()
			.provider(FitbitAPI.class)
			.apiKey("1c5c4d0bc7fb4758874a751fb46a1a60")
			.apiSecret("b64533cf350147289398208d757075b8")
			.callback("http://localhost:8080/daitio-arbor-health/api-test.html")
			.build();
			
			// GETS THE REQUEST TOKEN
			requestToken = service.getRequestToken();

			// MAKE USER VALIDATE URL
			//String authUrl = service.getAuthorizationUrl(requestToken);
			
			
			// redirect to facebook auth page
			return "redirect:" + service.getAuthorizationUrl(requestToken);
		}
		return "redirect:" + sessionService.getAuthorizationUrl(requestToken);
		
	}
	
	@RequestMapping(value={"/fitbit-callback"}, method = RequestMethod.GET)
	public ModelAndView callback(@RequestParam(value="code", required=false) String oauthVerifier, WebRequest request, HttpServletRequest req) {

		ModelAndView mav = new ModelAndView("api-test");
		
		String code = "";
		Enumeration paramEnum = req.getParameterNames();
		while (paramEnum.hasMoreElements()) {
		String name = (String) paramEnum.nextElement();
		if (name.equals("oauth_verifier")) {
		    code = req.getParameter(name);
		}
		}
		
		// getting request token
		//Token requestToken = sessionService.getRequestToken();
		
		// getting access token
		Verifier verifier = new Verifier(code);
		Token accessToken = sessionService.getAccessToken(requestToken, verifier);
		

		//getting user profile 
		OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, "https://api.fitbit.com/1/user/-/profile.json");
		sessionService.signRequest(accessToken, oauthRequest);
		Response oauthResponse = oauthRequest.send();
		//System.out.println(oauthResponse.getBody()); 

		
		try {
			mav.addObject("verifier", verifier);
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("response", e.toString());
		}		
		
		try {
			mav.addObject("requestToken", requestToken);
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("response", e.toString());
		}

		
		try {
			mav.addObject("accessToken", accessToken);
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("response", e.toString());
		}
		
		try {
			mav.addObject("responseBody", oauthResponse.getBody());
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("responseBody", e.toString());
		}
		

		return mav;
	}
}