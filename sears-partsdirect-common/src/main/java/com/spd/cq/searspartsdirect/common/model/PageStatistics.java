package com.spd.cq.searspartsdirect.common.model;

import java.util.Comparator;

public class PageStatistics implements Comparator<PageStatistics>{
	private String title;
	private String pagePath;
	private String imagePath;
	private Long views;
	
	public PageStatistics(){
		
	}
	
	public PageStatistics(String title1, String pagePath1,String imagePath1, Long views1 ){
		title = title1;
		pagePath = pagePath1;
		imagePath = imagePath1;
		views = views1;
	   }
	
	 // Overriding the compare method to sort the age 
	   public int compare(PageStatistics guide1, PageStatistics guide2){
	      return (int) (guide1.getViews() - guide2.getViews());
	   }
	
	public String getTitle() {
		return title;
	}

	public String getPagePath() {
		return pagePath;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public Long getViews() {
		return views;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}


	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public void setViews(Long views) {
		this.views = views;
	}
	
	
}
