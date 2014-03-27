package com.spd.cq.searspartsdirect.common.foundation;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestPathInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

@Component
@Service
@Properties({
		@Property(name = org.osgi.framework.Constants.SERVICE_DESCRIPTION, value = "Loads guides as sub-pages of a model if the path exists below the model."),
		@Property(name = org.osgi.framework.Constants.SERVICE_VENDOR, value = "Siteworx"),
		@Property(name = "process.label", value = "Model Sub Page Filter"),
		@Property(name = "sling.filter.scope", value = "request"),
		@Property(name = "service.ranking", value = "500")})
public class ModelSubPageFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(ModelSubPageFilter.class);

	private final static Pattern authorModeRoot = Pattern.compile(Constants.SPD_ROOT + "[^\\./]*");
	private final static Pattern brandCatModelRest = Pattern
			.compile("^/([^/]*)/([^/]*)/" + Constants.MODELNO_PFX + "([^/]*)"
					+ Constants.MODELNO_SFX + "(.*\\"+Constants.MARKUP_EXT+")$");
	private final static Pattern airFilterPartDetailsPattern = Pattern
							.compile(Constants.AIRFILTER_PART_DETAILS_PFX + "/([^/]*)/([^/]*)/([^/]*)"
									+ "(.*\\"+Constants.MARKUP_EXT+")$");
	private final static Pattern repairGuide = Pattern.compile("^"
			+ Constants.REPAIR_GUIDES_ROOT);
	private final static Pattern categoryRepair = Pattern.compile("^/[^/]*"
			+ Constants.MODELNO_SFX + "[\\./]");
	private final static Pattern symptomatic = Pattern
			.compile(Constants.SYMPTOM_ROOT + "([^\\./]+)");
	
	private final static String CMS_URL_PREFIX = "/content/searspartsdirect/en";
	
	private final static Pattern PRCPattern = Pattern
			.compile(Constants.PRC_PFX + "/([^/]*)"
					+ "(.*\\"+Constants.MARKUP_EXT+")$");

	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain fc) throws IOException, ServletException {

		SlingHttpServletRequest request = (SlingHttpServletRequest)servletRequest;
		SlingHttpServletResponse response = (SlingHttpServletResponse)servletResponse;

		RequestPathInfo rpi = request.getRequestPathInfo();

		String resPath = rpi.getResourcePath();
		List<String> selectors = new LinkedList<String>();
		boolean mustForward = false;
		String authorPrepend = "";

		if (resPath.endsWith(Constants.MARKUP_EXT)) {
			// We'd rather strip content/searspartsdirect/en/ immediately to get cleaner matches.
			// We need to remember whether we did so and prepend.
			// resPath = resPath.replace("/content/searspartsdirect/en","");
			Matcher authorFix = authorModeRoot.matcher(resPath);
			if (authorFix.find()) {
				authorPrepend = authorFix.group(0);
				resPath = resPath.substring(0,authorFix.start())+resPath.substring(authorFix.end());
			}

			// We just strip this.. we restore it as needed further along
			resPath = resPath.replace("/categories","");

			Matcher mBcm = brandCatModelRest.matcher(resPath);
			if (mBcm.find()) {
				//String forwardUrl = m.group(2) + "?model=" + m.group(1);
				//Resource Resolver gets overzealous here
				selectors.add(mBcm.group(1));
				String catName = mBcm.group(2);
				selectors.add(catName);
				selectors.add(mBcm.group(3));
				resPath = mBcm.group(4);
				mustForward = true;
				if (resPath.equals(".html")) {
					resPath = Constants.MODEL_REPAIR_PAGE_NO_EXT + resPath;
				} else if (!hasRepairGuide(resPath)) {
					String catRepair = "/" + catName + Constants.MODELNO_SFX;
					if (!resPath.startsWith(catRepair)) {
						resPath = catRepair + resPath;
					}
				}
			}

			// Look for Air Filter Part Details page pattern
			Matcher airFilterPartDetailsMatcher = airFilterPartDetailsPattern.matcher(resPath);
			if (airFilterPartDetailsMatcher.find()) {
				selectors.add(airFilterPartDetailsMatcher.group(1));
				selectors.add(airFilterPartDetailsMatcher.group(2));
				selectors.add(airFilterPartDetailsMatcher.group(3));
				resPath = resPath.substring(0, airFilterPartDetailsMatcher.start(1) - 1) + Constants.MARKUP_EXT;
				resPath = resPath.replace("part-number", "part-details");
				mustForward = true;
			}

			Matcher symptom = symptomatic.matcher(resPath);
			if (symptom.find()) {
				// start of symptom-id group, - 1 to remove the slash or .
				resPath = resPath.substring(0,symptom.start(1) - 1)+Constants.MARKUP_EXT;
				selectors.add(symptom.group(1));
				mustForward = true;
			}

			if (hasCategoryRepair(resPath)) {
				resPath = Constants.CATEGORIES_PFX + resPath;
				mustForward = true;
			}
			
			/* Check for PRC Pattern */
			Matcher prc = PRCPattern.matcher(resPath);
			if(prc.find()){
				redirectPRCURL(request, response, resPath);
			}
		}

		if (mustForward) {
			String forwardUrl = addSelectors(authorPrepend + resPath,Constants.MARKUP_EXT,selectors);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(forwardUrl);

			//if (requestDispatcher == null) throw new RuntimeException("No dispatcher for "+forwardUrl);
			log.debug("Forwarding to " + forwardUrl);
			requestDispatcher.forward(request, response);

		} else {
			fc.doFilter(request, response);
		}
		return;

	}
	
	private static void redirectPRCURL(SlingHttpServletRequest request, SlingHttpServletResponse response, String pagePath) throws ServletException, IOException{
		log.info("PRCPageFilter: doFilter(): for url " + pagePath);
		
		String cmsPageUrl = mapURLToCMSPagePath(pagePath);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(cmsPageUrl);

		if (requestDispatcher == null) throw new RuntimeException("PRCPageFilter: doFilter(): No dispatcher for " + cmsPageUrl);
		
		log.info("PRCPageFilter: doFilter(): forwarding request to " + cmsPageUrl);
		requestDispatcher.forward(request, response);
	}
	
	private static String mapURLToCMSPagePath(String pagePath) {
		
		StringBuffer fullPagePath = new StringBuffer();
		fullPagePath.append(CMS_URL_PREFIX);
		log.info("Step 1: "+pagePath);
		//int lastIndex = pagePath.lastIndexOf("/");
		//fullPagePath.append(pagePath.substring(0,lastIndex)+'-'+pagePath.substring(lastIndex+1));
		fullPagePath.append(pagePath);
		log.info("Step 2: "+fullPagePath.toString());
		return fullPagePath.toString();
	}

	boolean hasRepairGuide(String path) {
		return repairGuide.matcher(path).find();
	}

	boolean hasCategoryRepair(String path) {
		return !path.startsWith(Constants.MODEL_REPAIR_PAGE_NO_EXT) && categoryRepair.matcher(path).find();
	}

	String addSelectors(String inUrl, String extension, List<String> selectors) {
		StringBuilder outUrl = new StringBuilder(inUrl.substring(0,inUrl.length()-extension.length()));
		for (String selector : selectors) {
			outUrl.append('.').append(selector);
		}
		//outUrl.setLength(outUrl.length() - 1);
		outUrl.append(extension);
		return outUrl.toString();
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void destroy() {
	}

}
