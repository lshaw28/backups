package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.jsp.PageContext;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import com.spd.cq.searspartsdirect.common.tags.GetImagePathTag;

public class GetImagePathTagFixture {

	private Resource imageResource;
	
	public GetImagePathTagFixture(PageContext pageContext) {
		imageResource = mock(Resource.class);
		// resource = (Resource) pageContext.findAttribute("resource");
		when(pageContext.findAttribute("resource")).thenReturn(imageResource);
	}

	public void setUpFullResponsiveImage() {
		
		// 25->96->186
		ResourceResolver resourceResolver = mock(ResourceResolver.class);
		when(imageResource.getResourceResolver()).thenReturn(resourceResolver);
		// +3pc
		Resource imageSubResource = mock(Resource.class);
		when(resourceResolver.getResource(imageResource,GetImagePathTag.DESKTOP_IMAGE)).thenReturn(imageSubResource);
		when(resourceResolver.getResource(imageResource,GetImagePathTag.TABLET_IMAGE)).thenReturn(imageSubResource);
		when(resourceResolver.getResource(imageResource,GetImagePathTag.MOBILE_IMAGE)).thenReturn(imageSubResource);
	}

	public String getTargetName() {
		return "pondering";
	}
	
	public String getPngOriginalPath() {
		return "/poi/dog/pondering/file.png";
	}

	public String getGifOriginalPath() {
		return "/bronze/statue/pondering/file.gif";
	}

	public String getJpgOriginalPath() {
		return "/bread/duck/pondering/file.jpg";
	}

}
