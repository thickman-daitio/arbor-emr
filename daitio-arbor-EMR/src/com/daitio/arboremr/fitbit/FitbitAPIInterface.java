/*package com.daitio.arboremr.fitbit;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.tomcat.util.codec.binary.Base64;

public class FitbitAPIInterface {
	
	// https://api.fitbit.com/oauth/request_token
	
	private static final String FITBIT_API		= "https://api.fitbit.com/";
	private static final String CLIENT_KEY 		= "1c5c4d0bc7fb4758874a751fb46a1a60";   
	private static final String CLIENT_SECRET 	= "b64533cf350147289398208d757075b8";  
	
	private static final String PARAM_CONSUMER_KEY		= "oauth_consumer_key";
	private static final String PARAM_NONCE				= "oauth_nonce";
	private static final String PARAM_SIGNATURE_METHOD 	= "oauth_signature_method";
	private static final String PARAM_TIMESTAMP			= "oauth_timestamp";
	private static final String PARAM_VERSION			= "oauth_version";
	private static final String PARAM_SIGNATURE			= "oauth_signature";
	
	private static final String OAUTH_NONCE 			= "daitio";
	private static final String OAUTH_SIGNATURE_METHOD 	= "HMAC-SHA1";
	private static final String OAUTH_VERSION 			= "1.0";
	
	private static byte[] buff = new byte[1024];
	
	public FitbitAPIInterface() {
	
	}
	
	public String execute() throws Exception {
		
		List<String> params = new ArrayList<String>();
		List<String> vals   = new ArrayList<String>();
		
		params.add(PARAM_CONSUMER_KEY);
		params.add(PARAM_NONCE);
		params.add(PARAM_SIGNATURE_METHOD);
		params.add(PARAM_TIMESTAMP);
		params.add(PARAM_VERSION);
		
		vals.add(CLIENT_KEY);
		vals.add(OAUTH_NONCE);
		vals.add(OAUTH_SIGNATURE_METHOD);
		vals.add(getTimestamp());
		vals.add(OAUTH_VERSION);
		
		String base = getSignatureBaseUrl(params, vals);
		
		params.add(PARAM_SIGNATURE);
		vals.add(generateSignature(base, CLIENT_SECRET, null));
			
		return doHttpPost(FITBIT_API, params, vals);
	}

	public String getSignatureBaseUrl(List<String> params, List<String> vals) {
		// Verified, creates base string based on Fitbit's documentation
		StringBuilder builder = new StringBuilder();
		
		try {
			builder.append("POST&" + URLEncoder.encode(FITBIT_API, "UTF-8") + "&");
			
			for (int i = 0; i < params.size(); i++) {
				builder.append(params.get(i));
				builder.append("%3D");
				builder.append(URLEncoder.encode(vals.get(i), "UTF-8"));
				if (i < params.size() - 1)
					builder.append("%26");
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
		return builder.toString();
	}
	
	public String getTimestamp() {
		Date d = new Date();
		return String.valueOf(d.getTime());
	}
		
	private String generateSignature(String baseStr,
			String oAuthConsumerSecret, String oAuthTokenSecret) {
		byte[] byteHMAC = null;
		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec spec;
			if (null == oAuthTokenSecret) {
				String signingKey = encode(oAuthConsumerSecret) + '&';
				spec = new SecretKeySpec(signingKey.getBytes(), "HmacSHA1");
			} else {
				String signingKey = encode(oAuthConsumerSecret) + '&'
						+ encode(oAuthTokenSecret);
				spec = new SecretKeySpec(signingKey.getBytes(), "HmacSHA1");
			}
			mac.init(spec);
			byteHMAC = mac.doFinal(baseStr.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Base64().encodeAsString(byteHMAC);
	}
	
	private String encode(String value) {
		String encoded = "";
		try {
			encoded = URLEncoder.encode(value, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder(encoded.length());
		char focus;
		for (int i = 0; i < encoded.length(); i++) {
			focus = encoded.charAt(i);
			if (focus == '*') {
				sb.append("%2A");
			} else if (focus == '+') {
				sb.append("%20");
			} else if (focus == '%' && i + 1 < encoded.length()
					&& encoded.charAt(i + 1) == '7'
					&& encoded.charAt(i + 2) == 'E') {
				sb.append('~');
				i += 2;
			} else {
				sb.append(focus);
			}
		}
		return sb.toString();
	}
	
	@SuppressWarnings("deprecation")
	public String doHttpPost(String urlStr, List<String> paramName, List<String> paramVal) throws Exception {

		@SuppressWarnings("resource")
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(urlStr);

		StringBuilder builder = new StringBuilder();
		builder.append("OAuth ");
		for (int i = 0; i < paramName.size(); i++) {
			builder.append(paramName.get(i));
			builder.append("=");
			builder.append(paramVal.get(i)); //, "UTF-8"));
			if (i < paramName.size() - 1)
				builder.append(", ");
		}

		HttpResponse response = null;
		httpPost.setHeader("Host", "api.fitbit.com");
		httpPost.setHeader("Authorization", builder.toString());
		
		for (int i = 0; i < httpPost.getAllHeaders().length; i++)
			System.out.println(httpPost.getAllHeaders()[i]);	
		
		response = client.execute(httpPost);

		StatusLine status = response.getStatusLine();
		if (status.getStatusCode() != 200) {
			throw new Exception("Invalid response. " + status.toString());
		}

		HttpEntity entity = response.getEntity();
		InputStream is = entity.getContent();
		ByteArrayOutputStream content = new ByteArrayOutputStream();
		
		int readCount = 0;
		while ((readCount = is.read(buff)) != -1) {
			content.write(buff, 0, readCount);
		}
				
		return new String(content.toByteArray());
	}
	
	/*
	public static String getSignature(String data, String key)
			throws SignatureException, NoSuchAlgorithmException,
			InvalidKeyException {
		
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signingKey);
		
		return toHexString(mac.doFinal(data.getBytes()));
	}

	private static String toHexString(byte[] bytes) {
		Formatter formatter = new Formatter();
		
		for (byte b : bytes) {
			formatter.format("%02x", b);
		}
 
		return formatter.toString();
	}
	*/
	
	/*
	public static String httpPost(String urlStr, String[] paramName,
			String[] paramVal) throws Exception {
		
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setAllowUserInteraction(false);
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		
		// Create the form content
		OutputStream out = conn.getOutputStream();
		Writer writer = new OutputStreamWriter(out, "UTF-8");
		for (int i = 0; i < paramName.length; i++) {
			writer.write(paramName[i]);
			writer.write("=");
			writer.write(URLEncoder.encode(paramVal[i], "UTF-8"));
			writer.write("&");
		}
		writer.close();
		out.close();
		
		if (conn.getResponseCode() != 200) {
			throw new IOException(conn.getResponseMessage());
		}

		// Buffer the result into a string
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();

		conn.disconnect();
		
		return sb.toString();
	}
	
}*/