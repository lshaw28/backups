package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.servlet.jsp.PageContext;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import com.spd.cq.searspartsdirect.common.tags.GetImagePathTag;

public class GetImagePathTagFixture {

	private Resource imageResource;
	private ResourceResolver resourceResolver;
	
	public GetImagePathTagFixture(PageContext pageContext, ResourceResolver resourceResolver) {
		this.resourceResolver = resourceResolver;
		
		imageResource = mock(Resource.class);
		// resource = (Resource) pageContext.findAttribute("resource");
		when(pageContext.findAttribute("resource")).thenReturn(imageResource);
	}

	public void setUpFullResponsiveImage() {
		
		// 25->96->186
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

	public void setUpNonlocalResponsiveImage() throws RepositoryException {
		setUpFullResponsiveImage();
		when(resourceResolver.getResource(getNonlocalResourcePath()+"/jcr:content")).thenReturn(imageResource);
		//Node resourceNode = resource.adaptTo(Node.class);
		Node node = mock(Node.class);
		when(imageResource.adaptTo(Node.class)).thenReturn(node);
		//pageContext.setAttribute("displayWidth", propOrEmpty(resourceNode,"displayWidth"));
		// 40	 1	
		//                         pageContext.setAttribute("displayHeight",  propOrEmpty(resourceNode,"displayHeight"));
		// 41	 1	
		//                         pageContext.setAttribute("linkAlt",  propOrEmpty(resourceNode,"linkAlt"));
		when(node.hasProperty("displayWidth")).thenReturn(true);
		Property widthProp = mock(Property.class);
		when(node.getProperty("displayWidth")).thenReturn(widthProp);
		when(widthProp.getString()).thenReturn("   ");
		when(node.hasProperty("displayHeight")).thenReturn(true);
		Property heightProp = mock(Property.class);
		when(node.getProperty("displayHeight")).thenReturn(heightProp);
		when(heightProp.getString()).thenReturn("123");
		when(node.hasProperty("linkAlt")).thenThrow(new RepositoryException());
	}

	public String getNonlocalResourcePath() {
		return "/etc/spdAssets/scaffolding/productCategory/chainsaw";
	}

}
