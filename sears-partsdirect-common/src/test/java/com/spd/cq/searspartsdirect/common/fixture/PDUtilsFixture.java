package com.spd.cq.searspartsdirect.common.fixture;

import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

import static org.mockito.Mockito.*;

public class PDUtilsFixture {

	public PDUtilsFixture() {
		
	}

	public Page getPageWithSubcategory() {
		Page page = getPageWithoutTags();
		Tag subCat = mock(Tag.class);
		when(subCat.getTagID()).thenReturn(Constants.SUBCATEGORY_TAG);
		Tag[] tags = new Tag[]{subCat};
		when(page.getTags()).thenReturn(tags);
		return page;
	}

	public Page getPageWithoutTags() {
		Page page = mock(Page.class);
		return page;
	}

	public Page getPageWithEmptyTags() {
		//Tag[] tagsArr = page.getTags();
		Tag[] tagsArr = new Tag[0];
		Page page = getPageWithoutTags();
		when(page.getTags()).thenReturn(tagsArr);
		return page;
	}

	public Page getPageWithOtherTag() {
		Page page = getPageWithoutTags();
		Tag notSubCat = mock(Tag.class);
		when(notSubCat.getTagID()).thenReturn("inrlyehde");
		Tag[] tags = new Tag[]{notSubCat};
		when(page.getTags()).thenReturn(tags);
		return page;
	}

}
