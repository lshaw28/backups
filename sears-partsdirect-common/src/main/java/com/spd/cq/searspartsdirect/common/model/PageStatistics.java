package com.spd.cq.searspartsdirect.common.model;

import java.util.Comparator;

public class PageStatistics implements Comparator<PageStatistics>{
	private String title;
	private String pagePath;
	private Long views;

	public PageStatistics(){
	   }

	PageStatistics(String title1, String pagePath1,Long views1 ){
		title = title1;
		pagePath = pagePath1;
		views = views1;
	   }
	
	 // Overriding the compare method to sort the age 
	   public int compare(PageStatistics guide1, PageStatistics guide2){
	      return (int) (guide1.getViews() - guide2.getViews());
	   }
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPagePath() {
		return pagePath;
	}
	
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}
	
	public Long getViews() {
		return views;
	}
	
	public void setViews(Long views) {
		this.views = views;
	}

}
