package com.daitio.arboremr;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.daitio.arboremr.fitbit.FitbitAPI;
import com.daitio.arboremr.patient.Patient;

public class Functions {

	public static final String FITBIT_BASE_URL = "https://api.fitbit.com/1/user/-/";

	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	public static String getDate(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}
}
