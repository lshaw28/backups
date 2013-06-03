package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.social.commons.*;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.components.Component;
import com.day.cq.wcm.api.components.ComponentContext;
import com.day.cq.wcm.api.components.EditContext;
import com.day.cq.wcm.api.designer.Design;
import com.day.cq.wcm.api.designer.Designer;
import com.day.cq.wcm.api.designer.Style;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

// This component is being reworked, please hold any comments until rework is completed.
public class GuideNavigationTag extends CQBaseTag {
	protected static Logger log = LoggerFactory
			.getLogger(GuideNavigationTag.class);

	@Override
	public int doStartTag() throws JspException {
		// output list containing lists [linktext,sectionlink]
		investigateBaseProvidedFields();
		investigatePageContextAttributes(pageContext);
		List<List<String>> sections = new ArrayList<List<String>>();
		pageContext.setAttribute("sections", sections);
		String jumpToString = Constants.GUIDE_NAV_DEF_JUMPTO_TEXT;
		pageContext.setAttribute(Constants.GUIDE_NAV_JUMPTO_TEXT_PAGE_ATTR,
				jumpToString);
		return EVAL_BODY_INCLUDE;
	}

	private void investigateBaseProvidedFields() {
		log.info("currentNode is " + currentNode);
		log.info("sling is " + sling);
		log.info("slingRequest is " + slingRequest);
		log.info("currentPage is " + currentPage);

		log.info("slingResponse is " + slingResponse);
		log.info("bindings is " + bindings);
		log.info("resource is " + resource);
		log.info("resourceResolver is " + resourceResolver);
		log.info("log is " + log);
		log.info("componentContext is " + componentContext);
		log.info("editContext is " + editContext);
		log.info("properties is " + properties);
		log.info("pageManager is " + pageManager);
		log.info("resourcePage is " + resourcePage);

		// TODO: figure this out....if we ever need it
		// log.info("pageProperties is " + pageProperties);
		log.info("component is " + component);
		log.info("designer is " + designer);
		log.info("currentDesign is " + currentDesign);
		log.info("resourceDesign is " + resourceDesign);
		log.info("currentStyle is " + currentStyle);
		log.info("request is " + request);
		log.info("contextPath is " + contextPath);
	}

	private void investigatePageContextAttributes(PageContext pageContext) {
		log.info("in page scope");
		try {
			dumpEnumToInfo(pageContext
					.getAttributeNamesInScope(PageContext.PAGE_SCOPE));
		} catch (Exception e) {
			log.info("Something wrong with page scope");
		}
		log.info("in request scope");
		try {
			dumpEnumToInfo(pageContext
					.getAttributeNamesInScope(PageContext.REQUEST_SCOPE));
		} catch (Exception e) {
			log.info("Something wrong with request scope");
		}
		log.info("in session scope");
		try {
			dumpEnumToInfo(pageContext
					.getAttributeNamesInScope(PageContext.SESSION_SCOPE));
		} catch (Exception e) {
			log.info("Something wrong with session scope");
		}
		log.info("in application scope");
		try {
			dumpEnumToInfo(pageContext
					.getAttributeNamesInScope(PageContext.APPLICATION_SCOPE));
		} catch (Exception e) {
			log.info("Something wrong with application scope");
		}
	}

	private void dumpEnumToInfo(Enumeration en) {
		StringBuilder sb = new StringBuilder();
		while (en.hasMoreElements()) {
			sb.append(en.nextElement()).append(", ");
		}
		sb.setLength(sb.length() - 2);
		log.info(sb.toString());
	}

	private static abstract class LabelGenerator {
		public abstract String generateLabel(Node beingLabelled,
				ResourceResolver rr);
	}

	private static class SubheadLabelGenerator extends LabelGenerator {
		public String generateLabel(Node subheadBeingLabelled,
				ResourceResolver rr) {
			String label = Constants.EMPTY;
			try {
				label = subheadBeingLabelled.getProperty("textvalue")
						.getString();
			} catch (Exception e) {
				log.error("retrieving subhead label: ", e);
			}
			return label;
		}
	}

	private static class CommentsLabelGenerator extends LabelGenerator {
		public String generateLabel(Node commentsBeingLabelled,
				ResourceResolver rr) {
			StringBuilder label = new StringBuilder(
					Constants.COMMENTS_GUIDE_NAV_LINK_PREFIX);
			try {
				Resource thoseComments = rr.resolve(commentsBeingLabelled
						.getPath());
				CommentSystem thatCs = thoseComments
						.adaptTo(CommentSystem.class);
				if (log.isDebugEnabled()) {
					log.debug("comments node is " + commentsBeingLabelled);
					log.debug("thoseComments is " + thoseComments);
					log.debug("thatCs is " + thatCs);
				}
				label.append(" (").append(thatCs.countComments()).append(")");
			} catch (RepositoryException re) {
				log.warn("finding out about comments, ", re);
			}
			return label.toString();
		}
	}

	private final static Map<String, LabelGenerator> specialLabelGenerators = initSpecialLabelGenerators();

	private final static Map<String, LabelGenerator> initSpecialLabelGenerators() {
		Map<String, LabelGenerator> generators = new HashMap<String, LabelGenerator>();
		generators
				.put(Constants.SUBHEAD_COMPONENT, new SubheadLabelGenerator());
		generators.put(Constants.COMMENTS_COMPONENT,
				new CommentsLabelGenerator());
		return generators;
	}
}
