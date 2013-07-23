package com.spd.cq.searspartsdirect.common.helpers;

import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.model.ModelCookieModel;
import com.spd.cq.searspartsdirect.common.model.PartCookieModel;

public class PartsDirectCookieHelper {

	protected static Logger log = LoggerFactory.getLogger(PartsDirectCookieHelper.class);

	public static Cookie getCookieInfo(Cookie[] cookies, String cookieName) {
		Cookie cookie = null;

		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(cookieName)) {
				cookie = cookies[i];
				break;
			}
		}
		return cookie;
	}
	
	
	public static LinkedList<ModelCookieModel> parseRecentltyViewedModelItems( String list ) {
		LinkedList<ModelCookieModel> modelList = new LinkedList<ModelCookieModel>();
		StringTokenizer tokenizer = new StringTokenizer ( list, "|" );
		while ( tokenizer.hasMoreTokens() && tokenizer.countTokens() >= 3 ) {
			String modelName = tokenizer.nextToken();
			String modelDescription = tokenizer.nextToken();
			String modelURL = tokenizer.nextToken();
			if (modelName!= null && modelURL!= null) {
				ModelCookieModel item = new ModelCookieModel();
				item.setItemName( modelName );
				item.setItemDescription( modelDescription );
				item.setItemURL( modelURL );
				if ( modelList.size() < 3) 
					modelList.addLast(item);
			}			
		}
		return modelList;
	}
	
	public static LinkedList<PartCookieModel> parseRecentltyViewedPartItems( String list ) {
		LinkedList<PartCookieModel> partList = new LinkedList<PartCookieModel>();
		StringTokenizer tokenizer = new StringTokenizer ( list, "|" );
		while ( tokenizer.hasMoreTokens() && tokenizer.countTokens() >= 4 ) {
			String partName = tokenizer.nextToken();
			String partDescription = tokenizer.nextToken();
			String partURL = tokenizer.nextToken();
			String partImageURL = tokenizer.nextToken();
			if (partName != null && partURL != null) {
				PartCookieModel item = new PartCookieModel();
				item.setItemName( partName );
				item.setItemDescription( partDescription );
				item.setItemURL( partURL );
				item.setItemImageURL( partImageURL );
				if (partList.size() < 3) 
					partList.addLast(item);
			}			
		}
		return partList;
	}
}
