package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
	// We have our template provided parts, and we have our dynamically discovered parts
	//private Map<String>
	
	protected static Logger log = LoggerFactory
			.getLogger(GuideNavigationTag.class);

	@Override
	public int doStartTag() throws JspException {
		// output list containing lists [linktext,sectionlink]
		investigateBaseProvidedFields();
		investigatePageContextAttributes(pageContext);
		List<List<String>> sections = new ArrayList<List<String>>();
		// we can declare these as vars in the tld, usage here remains the same tho..
		
		String jumpToString = Constants.GUIDE_NAV_DEF_JUMPTO_TEXT;
		pageContext.setAttribute(Constants.GUIDE_NAV_JUMPTO_TEXT_PAGE_ATTR,
				jumpToString);
		
		LinkedHashMap<String,LinkGenerator> generators = new LinkedHashMap<String,LinkGenerator>();
		// check if doing parts link, check if label is different
		generators.put(Constants.PARTS_REQ_R_COMPONENT, new FixedLabelTemplateLinkGenerator(Constants.PARTS_REQ_R_COMPONENT,Constants.PARTS_REQ_DEF_GUIDE_NAV_LINK));
		generators.put(Constants.TOOLS_REQ_R_COMPONENT, new FixedLabelTemplateLinkGenerator(Constants.TOOLS_REQ_R_COMPONENT,Constants.TOOLS_REQ_DEF_GUIDE_NAV_LINK));
		// find parsys and do dynamic components
		//TODO Do that
		generators.put(Constants.COMMENTS_COMPONENT, new CommentsLinkGenerator(constructCommentsPath(),resourceResolver));
		
		for (LinkGenerator linkGen : generators.values()) {
			List<String> newList = new ArrayList<String>();
			newList.add(linkGen.generateLabel());
			newList.add(linkGen.generateLink());
			sections.add(newList);
		}
		pageContext.setAttribute(Constants.GUIDE_NAV_SECTIONS_PAGE_ATTR, sections);
		
		return EVAL_BODY_INCLUDE;
	}
	
	private String constructCommentsPath() {
		StringBuilder commentsPath = new StringBuilder(Constants.USERGEN_ROOT);
		commentsPath.append(currentPage.getPath());
		commentsPath.append(Constants.COMMENTS_PATH);
		return commentsPath.toString();
	}

	private void investigateBaseProvidedFields() {
		log.info("currentNode is " + currentNode);
		log.info("sling is " + sling);
		log.info("slingRequest is " + slingRequest);
		log.info("currentPage is " + currentPage);
		if (currentPage != null) {
			log.info("currentPage.path is "+currentPage.getPath());
		}

		log.info("slingResponse is " + slingResponse);
		log.info("bindings is " + bindings);
		log.info("resource is " + resource);
		if (resource != null) {
			log.info("resource.path is "+resource.getPath());
		}
		log.info("resourceResolver is " + resourceResolver);
		log.info("log is " + log);
		log.info("componentContext is " + componentContext);
		if (componentContext != null) {
			Component component = componentContext.getComponent();
			log.info("componentContext.component is "+component);
			if (component != null) {
				log.info("component.cellName is "+component.getCellName());
				log.info("component.superComponent is "+component.getSuperComponent());
				log.info("component.virtualComponents is "+component.getVirtualComponents());
			}
		}
		log.info("editContext is " + editContext);
		log.info("properties is " + properties);
		log.info("pageManager is " + pageManager);
		log.info("resourcePage is " + resourcePage);
		if (resourcePage != null) {
			log.info("resourcePage.path is "+resourcePage.getPath());
		}

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

	private static abstract class LinkGenerator {
		private String componentType;
		public void setComponentType(String componentType) {
			this.componentType = componentType;
		}
		public String getComponentType() {
			return componentType;
		}
		public abstract String generateLabel();
		public abstract String generateLink();
	}

	private static abstract class TemplateLinkGenerator extends LinkGenerator {
		private String componentName;
		
		/**
		 * Sets the name (usually the same as the last path-component of the type name) of
		 * the template component linked to.
		 * @return
		 */
		public void setComponentName(String componentName) {
			this.componentName = componentName;
		}
		
		/**
		 * Gets the name of the template component linked to. If no name has been set,
		 * uses the last path-component of the component type.
		 * @return
		 */
		public String getComponentName() {
			if (componentName == null) {
				String fullType = getComponentType();
				if (log.isDebugEnabled()) log.debug("setting from fullType which is "+fullType);
				componentName = fullType.substring(fullType.lastIndexOf("/") + 1);
				if (log.isDebugEnabled()) log.debug("componentName is "+componentName);
			}
			return componentName;
		}
		@Override
		public final String generateLink() {
			return "template_" + getComponentName();
		}
	}
	
	private static class FixedLabelTemplateLinkGenerator extends TemplateLinkGenerator {
		final String label;
		public FixedLabelTemplateLinkGenerator(final String componentType, final String label) {
			super();
			setComponentType(componentType);
			this.label = label;
		}
		@Override
		public final String generateLabel() {
			return label;
		}
	}
	
	private static abstract class ParsysLinkGenerator extends LinkGenerator {
		private String parsysName;

		public String getParsysName() {
			return parsysName;
		}

		public void setParsysName(String parsysName) {
			this.parsysName = parsysName;
		}
		
		private Node beingLinkedTo;

		public Node getBeingLinkedTo() {
			return beingLinkedTo;
		}

		public void setBeingLinkedTo(Node beingLinkedTo) {
			this.beingLinkedTo = beingLinkedTo;
		}

		@Override
		public String generateLink() {
			String linkTarget = null;
			try {
				linkTarget = parsysName + "_" + beingLinkedTo.getName();
			} catch (RepositoryException re) {
				log.warn("constructing link target, ",re);
			}
			return linkTarget;
		}
	}
	
	private static class SubheadLinkGenerator extends ParsysLinkGenerator {
		@Override
		public String generateLabel() {
			String label = Constants.EMPTY;
			try {
				label = getBeingLinkedTo().getProperty("textvalue")
						.getString();
			} catch (Exception e) {
				log.error("retrieving subhead label: ", e);
			}
			return label;
		}
	}

	private static class CommentsLinkGenerator extends TemplateLinkGenerator {
		private final String commentsPath;
		private final ResourceResolver rr;
		
		public CommentsLinkGenerator(final String commentsPath, final ResourceResolver rr) {
			super();
			setComponentType(Constants.COMMENTS_COMPONENT);
			this.commentsPath = commentsPath;
			this.rr = rr;
		}

		@Override // How'm I gonna find this node? It is not part of what I see.
		public String generateLabel() {
			StringBuilder label = new StringBuilder(
					Constants.COMMENTS_GUIDE_NAV_LINK_PREFIX);
			
			Resource thoseComments = rr.resolve(commentsPath);
			if (thoseComments != null) {
				CommentSystem thatCs = thoseComments
						.adaptTo(CommentSystem.class);
				if (log.isDebugEnabled()) {
					log.debug("comments path is " + commentsPath);
					log.debug("thoseComments is " + thoseComments);
					log.debug("thatCs is " + thatCs);
				}
				if (thatCs != null) {
					label.append(" (").append(thatCs.countComments()).append(")");
				} else {
					log.warn("Could not retrieve a CommentsSystem from "+thoseComments);
				}
			} else {
				log.warn("Could not resolve "+commentsPath+" to a resource");
			}
			return label.toString();
		}
	}
}
