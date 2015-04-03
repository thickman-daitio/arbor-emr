package com.daitio.arboremr.fitbit;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.daitio.arboremr.Functions;
import com.daitio.arboremr.patient.Patient;

public class FitbitAPIInterface {

	public static OAuthService getOAuthService() {
		OAuthService sessionService = new ServiceBuilder()
				.provider(FitbitAPI.class)
				.apiKey("1c5c4d0bc7fb4758874a751fb46a1a60")
				.apiSecret("b64533cf350147289398208d757075b8")
				.callback(
						"http://localhost:8080/daitio-arbor-health/fitbit-callback.html")
				.build();
		return sessionService;
	}
	
	public static Response getUserRequest(Patient p) {
		return getUserRequest(p.getAccessToken());
	}

	public static Response getUserRequest(Token accessToken) {
		OAuthRequest oauthRequest = new OAuthRequest(Verb.GET,
				Functions.FITBIT_BASE_URL + "profile.json");
		getOAuthService().signRequest(accessToken, oauthRequest);
		Response oauthResponse = oauthRequest.send();
		
		return oauthResponse;
	}
	
	public static Response getWeightRequest(Patient p) {
		return getWeightRequest(p.getAccessToken());
	}
	
	public static Response getWeightRequest(Token accessToken) {
		OAuthRequest weight = new OAuthRequest(Verb.GET,
				Functions.FITBIT_BASE_URL + "body/log/weight/date/"
						+ Functions.getDate() + "/1m.json");

		getOAuthService().signRequest(accessToken, weight);
		Response weightResponse = weight.send();

		return weightResponse;
	}
}
