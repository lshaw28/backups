package com.spd.cq.searspartsdirect.common.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PartsDirectAPIHelper {

	protected static Logger log = LoggerFactory.getLogger(PartsDirectAPIHelper.class);

	public String readJsonData(String apiUrl) throws IOException {
		return readJsonData(apiUrl, 5000);
	}

	public String readJsonData(String apiUrl, int timeoutValue) throws IOException {
		log.debug("PD API url String is2="+apiUrl);
		String jsonText = null;
		//apiUrl="http://partsapivip.qa.ch3.s.com/pd-services/v1/userservice/retrive?username=&profileid=c4ccbcf4-3b71-4071-83dc-d88c75aded8c&cartid=8a6bc7483f5ba81b013f5dd11c360013"
		URL url = new URL(apiUrl);
		URLConnection urlConnection = url.openConnection();
		urlConnection.setConnectTimeout(timeoutValue);
		urlConnection.setReadTimeout(timeoutValue);
		InputStream is = urlConnection.getInputStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			jsonText = readAll(rd);

		} finally {
			is.close();
		}

		//jsonText= "{'cart':{'cartLines':[{'part':{'description':'REFRIGERATOR WATER FILTER  9010','partNumber':'9010','productGroupId':'0046','supplierId':'046'},'quantity':'1'},{'part':{'description':'REFRIGERATOR WATER FILTER 9030','partNumber':'9030','productGroupId':'0046','supplierId':'046'},'quantity':'1'}]},'firstName':'Vivek','id':'28061','lastName':'Sharma','ownedModels':{'profileNumber':'0'},'password':'e38ad214943daad1d64c102faec29de4afe9da3d','roles':['ROLE_USER','ROLE_SUPERADMIN'],'username':'viveks_19@yahoo.com'}";
		log.debug("json string is "+jsonText);
		return jsonText;
	}

	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

}
