package com.daitio.arboremr;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Functions {

	public static final String FITBIT_BASE_URL = "https://api.fitbit.com/1/user/-/";

	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	public static String getDate(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}
}
