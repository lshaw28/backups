package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;

/**
 * Custom tag to draw out a list of Page objects when given a Tag path or id
 * @author Joseph
 *
 */
public class PagesByTagTag extends CQBaseTag {
	
	protected static Logger log = LoggerFactory.getLogger(PagesByTagTag.class);
	protected String tag;
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<Page> pages = new ArrayList<Page>();
		TagManager tm = resourceResolver.adaptTo(TagManager.class);
		
		Tag finderTag = tm.resolve(tag);
		//Tag.find() implementation
		if (finderTag != null) {
			Iterator<Resource> resourceIter = finderTag.find();
			while (resourceIter.hasNext()) {
				Resource r = resourceIter.next();
				if (r != null) {
					Page p = r.getParent().adaptTo(Page.class);
					if (p != null) {
						pages.add(p);
					}
				}
			}
		//QueryBuilder implementation
		/*QueryBuilder qb = resourceResolver.adaptTo(QueryBuilder.class);
		HashMap<String, String> props = new HashMap<String, String>();
        props.put("type", "cq:Page");
        props.put("path", "/content/searspartsdirect");
        props.put("property", "jcr:content/cq:tags");
        props.put("property.value", tag);
        List<Hit> hits = qb.createQuery(PredicateGroup.create(props),resourceResolver.adaptTo(Session.class)).getResult().getHits();
		try {
	        for (Hit hit: hits) {
				pages.add(hit.getPath());
			}
		}
		catch (Exception e) {
			log.error("Error querying pages by tag: " + e.toString());
		}*/
        
		pageContext.setAttribute("taggedPages",pages);
		}
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
	
	public void setTag(String tag) {
		this.tag = tag;
	}
}
