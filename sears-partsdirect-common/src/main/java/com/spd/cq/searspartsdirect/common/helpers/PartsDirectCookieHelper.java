package com.spd.cq.searspartsdirect.common.helpers;

import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.model.CookieModelItem;
import com.spd.cq.searspartsdirect.common.model.CookiePartItem;

public class PartsDirectCookieHelper {

	protected static Logger log = LoggerFactory.getLogger(PartsDirectCookieHelper.class);

	public static Cookie getCookieInfo(Cookie[] cookies, String cookieName) {
		Cookie cookie = null;

		for (int i = 0; i < cookies.length; i++) {
			log.error(cookies[i].getName());
			if (cookies[i].getName().equals(cookieName)) {
				cookie = cookies[i];
				break;
			}
		}
		return cookie;
	}
	
	
	public static LinkedList<CookieModelItem> parseRecentltyViewedModelItems( String list ) {
		LinkedList<CookieModelItem> modelList = new LinkedList<CookieModelItem>();
		StringTokenizer tokenizer = new StringTokenizer ( list, "|" );
		while ( tokenizer.hasMoreTokens() && tokenizer.countTokens() >= 3 ) {
			String modelName = tokenizer.nextToken();
			String modelDescription = tokenizer.nextToken();
			String modelURL = tokenizer.nextToken();
			if (modelName!= null && modelURL!= null) {
				CookieModelItem item = new CookieModelItem();
				item.setItemName( modelName );
				item.setItemDescription( modelDescription );
				item.setItemURL( modelURL );
				if ( modelList.size() < 3) 
					modelList.addLast(item);
			}			
		}
		return modelList;
	}
	
	public static LinkedList<CookiePartItem> parseRecentltyViewedPartItems( String list ) {
		LinkedList<CookiePartItem> partList = new LinkedList<CookiePartItem>();
		StringTokenizer tokenizer = new StringTokenizer ( list, "|" );
		while ( tokenizer.hasMoreTokens() && tokenizer.countTokens() >= 4 ) {
			String partName = tokenizer.nextToken();
			String partDescription = tokenizer.nextToken();
			String partURL = tokenizer.nextToken();
			String partImageURL = tokenizer.nextToken();
			if (partName != null && partURL != null) {
				CookiePartItem item = new CookiePartItem();
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
