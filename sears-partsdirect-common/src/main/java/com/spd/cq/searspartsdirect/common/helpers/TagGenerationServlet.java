package com.spd.cq.searspartsdirect.common.helpers;

import java.io.IOException;
import java.util.Iterator;

import javax.jcr.security.AccessControlException;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrUtil;
import com.day.cq.tagging.InvalidTagFormatException;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.text.csv.Csv;

/**
 * Convenience service to create Tags from a .csv format
 * @author Joseph
 *
 */

@SlingServlet(paths="/bin/searspartsdirect/taggeneration", methods = "POST", metatype=true)

public class TagGenerationServlet extends SlingAllMethodsServlet {
	private static final Logger log = LoggerFactory.getLogger(TagGenerationServlet.class);
    private static final String ENCODING = "UTF-8";
    
	@Reference
    private SlingRepository repository;
 
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException
    {
    	ResourceResolver resourceResolver = request.getResourceResolver();
        Csv csv = new Csv();
        Iterator<String[]> rows = csv.read(request.getRequestParameter("file").getInputStream(), ENCODING);
        
		TagManager tm = resourceResolver.adaptTo(TagManager.class);
		Tag namespaceCheck = tm.resolve("searspartsdirect:");
		if (namespaceCheck == null) {
			try {
				tm.createTag("searspartsdirect:", "Sears Parts Direct", "Namespace for Sears Parts Direct");
			} catch (Exception e) {
				log.error(e.toString());
				return;
			}
		}
        
        while (rows.hasNext()) {
            String [] row = rows.next();
            if (row.length > 0) {
        		generateTags(resourceResolver, row);
            }
        }
        
        csv.close();
    }

	private void generateTags(ResourceResolver resourceResolver, String[] row) {
		TagManager tm = resourceResolver.adaptTo(TagManager.class);
		String tagPath = "searspartsdirect:";
		for (String tagTitle : row) {
			if (tagTitle.equals("n/a")) {
				break;
			}
			String tagName = JcrUtil.createValidName(tagTitle);
			Tag check = tm.resolve(tagPath + tagName);
			if (check == null)
	        {
	            try {
	                check = tm.createTag(tagPath + tagName, tagTitle, "", true);
	                tagPath = check.getTagID() + "/";
                	log.info("Created tag with ID [" + check.getTagID() + "], title [" + tagTitle + "]");
	            } catch (Exception e) {
	            	log.error(e.toString());
	            }
	        }
			else {
				tagPath = check.getTagID() + "/";
			}
		}
	}
}
