package com.spd.cq.searspartsdirect.common.helpers;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.social.commons.Comment;
import com.adobe.cq.social.commons.CommentSystem;
import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

public class PDUtils {

	protected static final Logger log = LoggerFactory.getLogger(PDUtils.class);
	
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
	
	public static int countCommentsCorrectly(CommentSystem cs) {
		Iterator<Comment> commentsIter = cs.getComments();
		int commentsCount = 0;
		while(commentsIter.hasNext()) {
			Comment singleComment = commentsIter.next();
			if (!(singleComment.isSpam() || singleComment.isDenied())) {
				commentsCount++;
			}
		}
		return commentsCount;
	}
	
	
	/**
	 * @param pageManager
	 * @param pagePath
	 * @return true if page contains product category page as an attribute - pages, false otherwise
	 */
	public static boolean doesPageContainCategoryAsset(PageManager pageManager, String pagePath) {
        Page page = pageManager.getPage(pagePath);
        if (page != null) {
        	ValueMap pageProp = page.getProperties();
        	String[] pages = pageProp.get(Constants.ASSETS_PAGES_PATH, String[].class);
        	if (pages != null) {
	        	for (int i =0; i< pages.length; i++) {
	        		if (pages[i].contains("/"+Constants.ASSETS_PRODUCT_CATEGORY_PATH)) {
	        			return true;
	        		}
	        	}
        	}
        }
        return false;
	}
}
