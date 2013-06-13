package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mockito;

import com.spd.cq.searspartsdirect.common.model.RelatedGuideModel;

public class GetRelatedGuidesTagTest extends TestCase {
	
	GetRelatedGuidesTag tag;
	ArrayList<RelatedGuideModel> guides;
	PageContext pageContext;
	
	@Override
	protected void setUp() {
		pageContext = Mockito.mock(PageContext.class);
		tag = new GetRelatedGuidesTag();
		guides = new ArrayList<RelatedGuideModel>();

	    // dummy data
		RelatedGuideModel guide1 = new RelatedGuideModel("url1.html", "imagePath1", "title1");
		RelatedGuideModel guide2 = new RelatedGuideModel("url2.html", "imagePath2", "title2");
		RelatedGuideModel guide3 = new RelatedGuideModel("url3.html", "imagePath3", "title3");
		RelatedGuideModel guide4 = new RelatedGuideModel("url4.html", "imagePath4", "title4");

		guides.add(guide1);
		guides.add(guide2);				
		guides.add(guide3);			
		guides.add(guide4);
		
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRelatedGuides() throws JspException {
		ArrayList<RelatedGuideModel> guidesCheck;
		Mockito.when(pageContext.getAttribute("guides")).thenReturn(guides);
		tag.doStartTag();
		guidesCheck = (ArrayList<RelatedGuideModel>) pageContext.getAttribute("guides");
		for (int i=0; i < guidesCheck.size(); i++) {
			assertEquals(guidesCheck.get(i).getUrl(), guides.get(i).getUrl());
			assertEquals(guidesCheck.get(i).getImagePath(), guides.get(i).getImagePath());
			assertEquals(guidesCheck.get(i).getTitle(), guides.get(i).getTitle());
		}
	}
}
