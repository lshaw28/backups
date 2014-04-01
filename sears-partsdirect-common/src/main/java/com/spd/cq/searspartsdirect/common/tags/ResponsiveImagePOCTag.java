package com.spd.cq.searspartsdirect.common.tags;

import javax.jcr.Node;
import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

@SuppressWarnings("serial")
public class ResponsiveImagePOCTag extends CQBaseTag {
	protected final static Logger log = LoggerFactory.getLogger(ResponsiveImagePOCTag.class);

	@Override
	public int doStartTag() throws JspException {
		
		String imagePath = currentPage.getPath() + Constants.ASSETS_IMAGE_PATH;
		Resource imageResource = resourceResolver.getResource(imagePath);
		String respImageNamePath = null;
		
		//List<String> imageUrls = new ArrayList<String>();
		
		String imageUrl = null;
		final String imageUrlPrefix = "http://s.sears.com/is/image/Sears";
		
		try {
			Node imageNode = imageResource.adaptTo(Node.class);
			if (imageNode.hasProperty("fileReference")) {
				respImageNamePath = imageNode.getProperty("fileReference").getValue().getString();
				String respImageName = respImageNamePath.substring(respImageNamePath.lastIndexOf("/"), respImageNamePath.lastIndexOf("."));
				log.debug("%%IMAGE%% "+respImageName);
				/*imageUrls.add(imageUrlPrefix + respImageName + "?hei=100&wid=100"); //thumbnail
				imageUrls.add(imageUrlPrefix + respImageName + "?hei=200&wid=200"); //mobile
				imageUrls.add(imageUrlPrefix + respImageName + "?hei=300&wid=300"); //tablet
				imageUrls.add(imageUrlPrefix + respImageName + "?hei=400&wid=400"); //desktop*/
				imageUrl = imageUrlPrefix + respImageName;
		   }
		} catch (Exception e) {
			log.error("exception in image, ",e);
		}
		
		pageContext.setAttribute("respImage", imageUrl);
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
