package com.spd.cq.searspartsdirect.common.helpers;

import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;

/**
 * Compares pages based on their all-time total views.
 * @author bzethmayr
 *
 */
public class PageImpressionsComparator implements Comparator<Page> {

	private final static Logger log = LoggerFactory.getLogger(PageImpressionsComparator.class);
	
	private final Map<Page,Long> memoImpressions = new IdentityHashMap<Page,Long>();
	private final ResourceResolver resourceResolver;
	
	/**
	 * Creates a new comparator
	 * @param resourceResolver Needed in order to look up page views.
	 */
	public PageImpressionsComparator(final ResourceResolver resourceResolver) {
		this.resourceResolver = resourceResolver;
	}
	
	/**
	 * Compares pages based on views. This meets the standard compare contract, so for
	 * most viewed first, use like: 
	 * <code>Collections.sort(yourList,Collections.reverseOrder(new PageImpressionsComparator(resourceResolver)))<code>
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Page pageA, Page pageB) {
		long impressionsA, impressionsB;
		if (!memoImpressions.containsKey(pageA)) {
			impressionsA = getAllImpressions(pageA);
			memoImpressions.put(pageA, impressionsA);
		} else {
			impressionsA = memoImpressions.get(pageA);
		}
		if (!memoImpressions.containsKey(pageB)) {
			impressionsB = getAllImpressions(pageB);
			memoImpressions.put(pageB, impressionsB);
		} else {
			impressionsB = memoImpressions.get(pageB);
		}
		return Long.signum(impressionsA - impressionsB);
	}
	
	/*
	 * This is relatively expensive so we memoize the results.
	 */
	private long getAllImpressions(Page tempChild) {
        long numberOfImpressions = 0;
        try {
			String statsPath = Constants.STATS_PAGE_PREFIX + tempChild.getPath() + Constants.STATS_PAGE_SUFFIX;
			Resource allYearsResource = resourceResolver.getResource(statsPath);
			if (allYearsResource == null) {
				return numberOfImpressions;
			}
			Node allYearsNode = allYearsResource.adaptTo(Node.class);
			NodeIterator allYears = allYearsNode.getNodes();
			while (allYears.hasNext()) {
				numberOfImpressions += allYears.nextNode().getProperty("views").getLong();
			}
        } catch (Exception ex) {
        	log.error("Cannot get the number of views for "+tempChild, ex);
        }
        return numberOfImpressions;
	}

}
