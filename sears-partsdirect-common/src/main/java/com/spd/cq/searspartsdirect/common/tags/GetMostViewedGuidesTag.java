package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.jcr.NodeIterator;
import javax.jcr.PropertyIterator;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Node;
import javax.jcr.Session;

import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.wcm.api.Page;

import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.PageStatistics;
import com.spd.cq.searspartsdirect.common.model.RelatedGuideModel;

public class GetMostViewedGuidesTag extends CQBaseTag{

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetMostViewedGuidesTag.class);
	protected String categoryPath;
	
	@Override
	public int doStartTag() throws JspException {
		try {
			ArrayList<Page> result = new ArrayList<Page>();
			//Get the guides based on the category
			QueryBuilder qb = resourceResolver.adaptTo(QueryBuilder.class);
			HashMap<String, String> props = new HashMap<String, String>();
	        props.put("type", "cq:Page");
	        props.put("path", "/content/searspartsdirect/en/guides");
	        props.put("property", "jcr:content/pages");
	        props.put("property.value", categoryPath);
	        
	        List<Hit> hits = qb.createQuery(PredicateGroup.create(props),resourceResolver.adaptTo(Session.class)).getResult().getHits();
	        log.debug("MOST VIEWED GUIDES");
	        for (Hit hit: hits) {
	        	result.add(pageManager.getPage(hit.getPath()));
	        }
	        
	        //Now loop though the list of category guides and get the no. of views for each guide.
	        ResourceResolver resolverPage = slingRequest.getResourceResolver();
	        List<PageStatistics> pageList = new ArrayList<PageStatistics>();
	        for(Page page: result){
	        	try {
					Resource r = resolverPage.getResource("/var/statistics/pages/content/searspartsdirect/en/guides" + page.getName() + "/.stats");
					Node node = r.adaptTo(Node.class);
					NodeIterator childNodes = node.getNodes();
					while (childNodes.hasNext()){
						Node chldNode = childNodes.nextNode();
						PropertyIterator properties = null;
						properties = chldNode.getProperties();
						while (properties.hasNext()){
							Property prop=properties.nextProperty();
							if ("views".equalsIgnoreCase(prop.getName())){
								pageList.add(new PageStatistics(page.getTitle(),page.getPath() + ".html", page.getPath() + Constants.ASSETS_IMAGE_PATH ,prop.getValue().getLong()));							
							}
						}
					}

					} catch (RepositoryException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        }
	        // dummy data
		/*	PageStatistics guide1 = new PageStatistics("Title1","url1.html", "imagePath1", 4L);
			PageStatistics guide2 = new PageStatistics("title2","url2.html", "imagePath2", 5L);
			PageStatistics guide3 = new PageStatistics("title3","url3.html", "imagePath3", 3L);
			PageStatistics guide4 = new PageStatistics("title4","url4.html", "imagePath4", 2L);

			pageList.add(guide1);
			pageList.add(guide2);				
			pageList.add(guide3);			
			pageList.add(guide4); */
	        
	      //Sorts the list based in the no. of views
		Collections.sort(pageList, new PageStatistics());
		pageContext.setAttribute("guides", pageList);
	        
		}
		catch (Exception e) {
			
		}
		return SKIP_BODY;
	}
 
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	
	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}
	
}
