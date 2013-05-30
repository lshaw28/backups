package com.spd.cq.searspartsdirect.common.tags;

import java.util.Iterator;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.WCMMode;
import com.day.cq.wcm.core.stats.PageViewStatistics;
import com.day.cq.wcm.core.stats.PageViewReport;
import com.day.cq.statistics.StatisticsService;

public class GetMostViewedGuidesTag extends CQBaseTag{
	
	protected static Logger log = LoggerFactory.getLogger(GetMostViewedGuidesTag.class);
	
	@Override
	public int doStartTag() throws JspException {
		Iterator<Page> pages = null;
		StatisticsService statService = sling.getService(StatisticsService.class);
		ResourceResolver resolverPage = slingRequest.getResourceResolver();
		Resource resPage = resolverPage.getResource("/content/geometrixx");
		
		Page geoPages = resPage.adaptTo(Page.class);
		pages = geoPages.listChildren();
		log.debug("CHILD PAGES : ");
		while (pages.hasNext()){
			PageViewReport report = new PageViewReport(statService.getPath() + "/pages", pages.next(), WCMMode.EDIT);
			report.setPeriod(30);
			log.debug(Integer.toString(report.getPeriod()));
			Iterator stats = null;
			int totalPageViews = 0;
			try {
				stats = statService.runReport(report);
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			while (stats.hasNext()) {
				Object[] res = (Object[]) stats.next();
				totalPageViews = totalPageViews + Integer.parseInt(res[1].toString());
			}
			log.debug(pages.next().getTitle() + totalPageViews);
		}

		return SKIP_BODY;
	}
 
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	
}
