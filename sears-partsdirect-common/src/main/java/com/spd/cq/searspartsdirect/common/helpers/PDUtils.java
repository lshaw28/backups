package com.spd.cq.searspartsdirect.common.helpers;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;

public class PDUtils {

	protected static final Logger log = LoggerFactory.getLogger(PDUtils.class);
	
	private PDUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static String getSubcategoryFromPage(Page page) {
		String subcategoryID = null;
		Tag[] tagsArr = page.getTags();
		if (tagsArr != null){
    		for(int i=0; i<=tagsArr.length-1 ; i++){
    			if (StringUtils.contains(tagsArr[i].getTagID(), Constants.SUBCATEGORY_TAG)){
    				subcategoryID = tagsArr[i].getTagID();
    				break;
    			}
    		}
		}
		return subcategoryID;
	}

}
