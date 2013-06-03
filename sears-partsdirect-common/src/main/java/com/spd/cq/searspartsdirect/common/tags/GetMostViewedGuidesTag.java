package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.jcr.NodeIterator;
import javax.jcr.PropertyIterator;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Node;

import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;

import com.spd.cq.searspartsdirect.common.model.PageStatistics;

public class GetMostViewedGuidesTag extends CQBaseTag{
	
	protected static Logger log = LoggerFactory.getLogger(GetMostViewedGuidesTag.class);
	
	@Override
	public int doStartTag() throws JspException {
		Iterator<Page> pages = null;
		List<PageStatistics> pageList = new ArrayList<PageStatistics>();
		ResourceResolver resolverPage = slingRequest.getResourceResolver();
		Resource resPage = resolverPage.getResource("/content/searspartsdirect/en/home");
		Page repairPages = resPage.adaptTo(Page.class);
		pages = repairPages.listChildren();
		while (pages.hasNext()){
			Page childPages = pages.next();
			PageStatistics pageStats = new PageStatistics();
			pageStats.setTitle(childPages.getTitle());
			pageStats.setPagePath(childPages.getPath());
			
			try {
				Resource r = resolverPage.getResource("/var/statistics/pages/content/searspartsdirect/en/home" + childPages.getName() + "/.stats");
				Node node = r.adaptTo(Node.class);
//				log.debug("Node Name:" + node.getName());
				NodeIterator childNodes = node.getNodes();
//				log.debug("NO. OF Child nodes :" + String.valueOf(childNodes.getSize()));
				while (childNodes.hasNext()){
//					log.debug("COMES HERE");
					Node chldNode = childNodes.nextNode();
//					log.debug("CHILD NODE :" + chldNode.getName());
					PropertyIterator properties = null;
					properties = chldNode.getProperties();
					while (properties.hasNext()){
						Property prop=properties.nextProperty();
						if ("views".equalsIgnoreCase(prop.getName())){
							pageStats.setViews(prop.getValue().getLong());
//							log.debug("Property: "  + String.valueOf(prop.getValue().getLong()));
						}
					}
				}
				
				//Sorts the list based in the no. of views
				Collections.sort(pageList, new PageStatistics());
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			pageList.add(pageStats);
		}
	
		return SKIP_BODY;
	}
 
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	
}
