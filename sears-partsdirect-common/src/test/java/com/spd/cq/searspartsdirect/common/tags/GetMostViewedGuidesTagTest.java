package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.junit.Test;
import org.mockito.Mockito;

import com.spd.cq.searspartsdirect.common.model.PageStatistics;
import com.spd.cq.searspartsdirect.common.model.RelatedGuideModel;


import junit.framework.TestCase;

public class GetMostViewedGuidesTagTest extends TestCase{
	
	GetMostViewedGuidesTag tag;
	ArrayList<PageStatistics> guides;
	PageContext pageContext;
	
	@Override
	protected void setUp() {
		pageContext = Mockito.mock(PageContext.class);
		tag = new GetMostViewedGuidesTag();
		guides = new ArrayList<PageStatistics>();
	
		PageStatistics guide1 = new PageStatistics("Title1","url1.html", "imagePath1", 4L);
		PageStatistics guide2 = new PageStatistics("title2","url2.html", "imagePath2", 5L);
		PageStatistics guide3 = new PageStatistics("title3","url3.html", "imagePath3", 3L);
		PageStatistics guide4 = new PageStatistics("title4","url4.html", "imagePath4", 2L);

		guides.add(guide1);
		guides.add(guide2);				
		guides.add(guide3);			
		guides.add(guide4); 
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testMostViewedGuides() throws JspException {
		ArrayList<PageStatistics> guidesCheck;
		Mockito.when(pageContext.getAttribute("guides")).thenReturn(guides);
		tag.doStartTag();
		guidesCheck = (ArrayList<PageStatistics>) pageContext.getAttribute("guides");
		for (int i=0; i < guidesCheck.size(); i++) {
			assertEquals(guidesCheck.get(i).getPagePath(), guides.get(i).getPagePath());
			assertEquals(guidesCheck.get(i).getImagePath(), guides.get(i).getImagePath());
			assertEquals(guidesCheck.get(i).getTitle(), guides.get(i).getTitle());
		}
	}

}
