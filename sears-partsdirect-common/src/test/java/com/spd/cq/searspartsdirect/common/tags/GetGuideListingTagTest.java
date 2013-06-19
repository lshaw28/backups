package com.spd.cq.searspartsdirect.common.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.spd.cq.searspartsdirect.common.fixture.GetGuideListingTagFixture;
import com.spd.cq.searspartsdirect.common.model.RelatedArticleModel;

public class GetGuideListingTagTest extends MocksTag{
	
	GetGuideListingTag tag;
	GetGuideListingTagFixture fixture;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		fixture = new GetGuideListingTagFixture(pageContext,resourceResolver,pageManager);
		tag = new GetGuideListingTag();
		tag.setPageContext(pageContext);
		
	}

	@Test
	public void testDoStartTag() {
		try {
			tag.setCategoryPath("/category");
			tag.doStartTag();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		HashMap<String, List<RelatedArticleModel>> guides = (HashMap<String, List<RelatedArticleModel>>)pageContext.getAttribute("guides");
		assertThat(guides,is(instanceOf(HashMap.class)));
		//assertThat(guides,hasSize(3));
		Set keys = guides.keySet();
		int count = 0;
		for (Iterator i = keys.iterator(); i.hasNext();)
		{
	       String key = (String) i.next();
	       List<RelatedArticleModel> relatedArticles = guides.get(key);
	       if (count == 0){ //check the first entry
			   RelatedArticleModel first = relatedArticles.get(0);
			   assertThat(first.getUrl(),is("/baz.html"));
		   }
		   if (count == 2){ //check the last entry
			   RelatedArticleModel last =  relatedArticles.get(2);
			   assertThat(last.getUrl(),is("/foo.html"));
		   }
		   count ++;
		}

	}
	

}
