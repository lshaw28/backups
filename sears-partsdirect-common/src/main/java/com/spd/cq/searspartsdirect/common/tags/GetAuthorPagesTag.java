package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;

import javax.jcr.Node;
import javax.jcr.Value;
import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.spdasset.AuthorModel;

public class GetAuthorPagesTag  extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetAuthorPagesTag.class);
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<Page> pages = new ArrayList<Page>();
		ArrayList<AuthorModel> authors = new ArrayList<AuthorModel>();

		try {
			
			Value[] values = currentPage.getProperties().get("authors",new Value[0]);

			for(Value path: values){
				Page p = pageManager.getPage(path.getString());
				pages.add(p);
			}
			
			String description = "";
			for (Page page: pages){
				// We need to resolve the image path, and hand out a blank for image if the image does not exist
        		String imagePath = page.getPath() + Constants.ASSETS_IMAGE_PATH;
        		Resource imageResource = resourceResolver.getResource(imagePath);
        		if (imageResource == null) {
        			// If we cannot resolve to an image, we return a blank string
        			imagePath = Constants.EMPTY;
        		} else {
        			Node imageNode = imageResource.adaptTo(Node.class);
        			if (!(imageNode.hasProperty("fileReference") || imageNode.hasNode("file"))) {
        				// If the image is not set up one way or another, we return a blank string
        				imagePath = Constants.EMPTY;
        			}
        		}
				
        		if(page.getProperties().containsKey("abstracttext")){
	        		description = page.getProperties().get("abstracttext").toString();
	        	} else {
	        		description = "";
	        	}
        		
        		AuthorModel author = new AuthorModel(
						page.getPath(),
						page.getTitle(),
						description,
						imagePath);
				authors.add(author);
			}
			
			
		} catch (Exception e) {
			log.error("failed to get author pages", e);
		} 
		pageContext.setAttribute("authors", authors);
		
		return SKIP_BODY;
	}

	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
