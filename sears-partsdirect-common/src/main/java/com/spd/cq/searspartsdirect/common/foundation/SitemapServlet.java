/**
 * Generates the sitemap.xml
 */
package com.spd.cq.searspartsdirect.common.foundation;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.Servlet;

import org.apache.felix.scr.annotations.*;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.helpers.ExternalLinks;
import com.spd.cq.searspartsdirect.common.helpers.NavigablePageFilter;
import com.spd.cq.searspartsdirect.common.helpers.PathStringUtils;

/**
 * @author bzethmayr
 *
 */

@SuppressWarnings("serial")
@Component(immediate = true, metatype = false)
@Service(Servlet.class)
@Properties({@Property(name = "service.description", value = "sitemap.xml renderer"),
        @Property(name = "service.vendor", value = "Siteworx LLC"),
        @Property(name = "sling.servlet.resourceTypes", value = {"searspartsdirect/servlet/sitemap"}),
        @Property(name = "sling.servlet.extensions", value = {"xml"}),
        @Property(name = "sling.servlet.methods", value = {"GET"})
})

public class SitemapServlet extends SlingSafeMethodsServlet {
	
	protected static final Logger log = LoggerFactory.getLogger(SitemapServlet.class);
	// The following two classes are stateless, so we can safely keep them in this scope.
	protected static final PathStringUtils psu = new PathStringUtils();
	protected static final NavigablePageFilter npf = new NavigablePageFilter();
	
	@Override
	public void doGet(SlingHttpServletRequest slingRequest, SlingHttpServletResponse slingResponse) {
		if (log.isDebugEnabled()) log.debug("servlet called");
		slingResponse.setContentType("application/xml; charset=UTF-8");
		PrintWriter out = null;
		Rules rules = new Rules(
				readConfiguredStartPaths(),
				readConfiguredStopPaths(),
				slingRequest
			);
		try {
			out = slingResponse.getWriter();
			writeXmlDeclaration(out);
			writeStartUrlset(out);
			writeAllUrls(rules,out);
			writeEndUrlset(out);
		} catch (Exception gettingWriter) {
			log.error("Getting writer, ",gettingWriter);
		} finally {
			out.flush();
			out.close();
		}
		if (log.isDebugEnabled()) log.debug("servlet ended");
	}
	
	void writeAllUrls(final Rules rules, final PrintWriter out) {
		Set<String> startPaths = rules.getStartPaths();
		for (String startPath : startPaths) {
			Page aPage = rules.getResourceResolver().getResource(startPath).adaptTo(Page.class);
			if (aPage != null) {
				writeUrlsUnder(rules,aPage,out);
			} else {
				log.warn("Could not resolve the path "+startPath+" to a page");
			}
		}
	}
	
	void writeUrlsUnder(final Rules rules, final Page startPage, final PrintWriter out) {
		if (psu.hasAnyParentIn(rules.getStopPaths(),startPage.getPath())) {
			if (log.isDebugEnabled()) log.debug("hit a stop");
			return;
		}
		writeUrlForPage(rules,startPage,out);
		Iterator<Page> children = startPage.listChildren(npf);
		while (children.hasNext()) {
			writeUrlsUnder(rules,children.next(),out);
		}
	}
	
	void writeUrlForPage(final Rules rules, final Page aPage, final PrintWriter out) {
		out.println(Constants.SITEMAP_OPEN_URL);
		out.print(Constants.SITEMAP_OPEN_LOC);
		out.print(getExternalUrl(rules,aPage)); 
		out.println(Constants.SITEMAP_CLOSE_LOC);
		out.print(Constants.SITEMAP_OPEN_LM);
		out.print(getFormattedLastModified(rules,aPage));
		out.println(Constants.SITEMAP_CLOSE_LM);
		out.println(Constants.SITEMAP_CLOSE_URL);
	}
	
	String getExternalUrl(final Rules rules, final Page aPage) {
		String pagePath = aPage.getPath();
		// Removed "removed prefixes" notion in favor of resourceResolver map method
		return rules.getExternalLinks().getExternalUrlForPage(pagePath);
	}
	
	String getFormattedLastModified(Rules rules, final Page aPage) {
		return rules.getDateFormatter().format(aPage.getLastModified().getTime());
	}
	
	Set<String> readConfiguredStartPaths() {
		return psu.pathsSetFromCsv(EnvironmentSettings.getSitemapStartPaths());
	}
	
	Set<String> readConfiguredStopPaths() {
		return psu.pathsSetFromCsv(EnvironmentSettings.getSitemapStopPaths());
	}
	
	void writeXmlDeclaration(final PrintWriter out) {
		out.println(Constants.XML_DECLARATION);
	}
	
	void writeStartUrlset(final PrintWriter out) {
		out.print(Constants.SITEMAP_OPEN_URLSET);
		out.println(Constants.XML_XSD_NS);
		out.println(Constants.SITEMAP_XML_XSD);
		out.print(Constants.SITEMAP_XML_NS);
		out.println(Constants.XML_CLOSE_TAG);
	}
	
	void writeEndUrlset(final PrintWriter out) {
		out.println(Constants.SITEMAP_CLOSE_URLSET);
	}
	
	private static final class Rules {
		private final Set<String> startPaths;
		private final Set<String> stopPaths;
		private final ExternalLinks externalLinks;
		private final ResourceResolver resourceResolver;
		private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		
		public Rules(final Set<String> startPaths, 
				final Set<String> stopPaths, 
				final SlingHttpServletRequest currentRequest) {
			this.startPaths = startPaths;
			this.stopPaths = stopPaths;
			this.resourceResolver = currentRequest.getResourceResolver();
			this.externalLinks = new ExternalLinks(currentRequest);
		}
		
		public Set<String> getStartPaths() {
			return startPaths;
		}
		
		public Set<String> getStopPaths() {
			return stopPaths;
		}
		
		public ExternalLinks getExternalLinks() {
			return externalLinks;
		}
		
		public ResourceResolver getResourceResolver() {
			return resourceResolver;
		}
		
		public DateFormat getDateFormatter() {
			return dateFormatter;
		}
	}
} 
