package com.spd.cq.searspartsdirect.common.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PartsDirectAPIHelper {
	
	protected static Logger log = LoggerFactory.getLogger(PartsDirectAPIHelper.class);
	
	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		log.debug("PD API url String is="+url);
		//urlStr="http://partsapivip.qa.ch3.s.com/pd-services/v1/userservice/retrive?username=&profileid=c4ccbcf4-3b71-4071-83dc-d88c75aded8c&cartid=8a6bc7483f5ba81b013f5dd11c360013"
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

}
