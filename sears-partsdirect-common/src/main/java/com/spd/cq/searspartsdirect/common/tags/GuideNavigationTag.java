package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.social.commons.*;
import com.day.cq.wcm.api.WCMMode;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

@SuppressWarnings("serial")
public class GuideNavigationTag extends CQBaseTag {
	
	public final static String BEFORE_YOU_BEGIN = Constants.ident("beforeYouBegin");
	
	protected static Logger log = LoggerFactory.getLogger(GuideNavigationTag.class);

	@Override
	public int doStartTag() throws JspException {
		// output list containing lists [linktext,sectionlink]
		List<List<String>> sections = new ArrayList<List<String>>();
		
		// in a template, currentNode is not populated - so we find the page content node
		Node pageNode = currentPage.getContentResource().adaptTo(Node.class);
		
		// we may need to set up configuration
		maybeSetupDefaultConfig(pageNode);
		
		// we read our configuration into a map
		Map<String,String> typesAndLabels = readTypesAndLabelsFromConfig(pageNode);
		if (log.isDebugEnabled()) log.debug("typesAndLabels is "+typesAndLabels);
		
		// We find the jump-to text for the select and place it in context
		String jumpToString = Constants.GUIDE_NAV_DEF_JUMPTO_TEXT;
		try {
            jumpToString = pageNode.getProperty(Constants.GUIDE_NAV_JUMPTO_TEXT_PAGE_ATTR).getString();
	    } catch (Exception e) {
	            log.error("exception getting jump to text",e);
	    }
		pageContext.setAttribute(Constants.GUIDE_NAV_JUMPTO_TEXT_PAGE_ATTR,
				jumpToString);
		
		// we populate this with ways of creating links
		List<LinkGenerator> generators = new ArrayList<LinkGenerator>();
		
		// We create our template components that precede the parsys.
		// We do this explicitly to avoid needing to look at the template at publish time
		if (typesAndLabels.containsKey(Constants.TOOLS_REQ_R_COMPONENT)) {
			String labelFound = typesAndLabels.get(Constants.TOOLS_REQ_R_COMPONENT);
			if (labelIsBlank(labelFound)) {
				labelFound = Constants.TOOLS_REQ_DEF_GUIDE_NAV_LINK;
			}
			generators.add(new FixedLabelTemplateLinkGenerator(Constants.TOOLS_REQ_R_COMPONENT,labelFound));
		} 
		if (typesAndLabels.containsKey(Constants.PARTS_REQ_R_COMPONENT)) {
			String labelFound = typesAndLabels.get(Constants.PARTS_REQ_R_COMPONENT);
			if (labelIsBlank(labelFound)) {
				labelFound = Constants.PARTS_REQ_DEF_GUIDE_NAV_LINK;
			}
			generators.add(new FixedLabelTemplateLinkGenerator(Constants.PARTS_REQ_R_COMPONENT,labelFound));
		} 
		
		// We no longer have any actual dynamic content here.
		// All elements linked to are now part of the template
		
		// We only want to generate a link to Before you begin if we find a header there.
		if (typesAndLabels.containsKey(Constants.TEXT_COMPONENT)) {
			Resource beforeYouBegin = currentPage.getContentResource(BEFORE_YOU_BEGIN);
			if (resourceExists(beforeYouBegin)) {
				TextHeaderLinkGenerator beforeYouBeginGenerator = new TextHeaderLinkGenerator(beforeYouBegin);
				beforeYouBeginGenerator.setComponentName(BEFORE_YOU_BEGIN);
				generators.add(beforeYouBeginGenerator);
			}
		}
		
		if (typesAndLabels.containsKey(Constants.INSTRUCTIONS_COMPONENT)) {
			String labelFound = typesAndLabels.get(Constants.INSTRUCTIONS_COMPONENT);
			if (labelIsBlank(labelFound)) {
				labelFound = Constants.INSTRUCTIONS_DEF_GUIDE_NAV_LINK;
			}
			generators.add(new FixedLabelTemplateLinkGenerator(Constants.INSTRUCTIONS_COMPONENT,labelFound));
		}
		
		// Comments link is unconditional
		generators.add(new CommentsLinkGenerator(constructCommentsPath(),resourceResolver));
		
		// We iterate over our generators and create our output
		for (LinkGenerator linkGen : generators) {
			String label = linkGen.generateLabel();
			if (!labelIsBlank(label)) {
				List<String> newList = new ArrayList<String>();
				newList.add(linkGen.generateLabel());
				newList.add(linkGen.generateLink());
				sections.add(newList);
			}
		}
		pageContext.setAttribute(Constants.GUIDE_NAV_SECTIONS_PAGE_ATTR, sections);
		
		return SKIP_BODY;
	}
	
	public static boolean labelIsBlank(String label) {
		return label == null || label.matches(" *");
	}
	
	public static boolean resourceExists(Resource resource) {
		return resource != null && !resource.isResourceType(Resource.RESOURCE_TYPE_NON_EXISTING);
	}
	
	private void maybeSetupDefaultConfig(Node pageNode) {
		WCMMode wcmMode = WCMMode.fromRequest(slingRequest);
		if (wcmMode != WCMMode.READ_ONLY && wcmMode != WCMMode.DISABLED) {
			try {
				if (pageNode == null) {
					log.warn("Could not get the node to set up");
					return;
				}
				Node setupNode;
				Session jcr = pageNode.getSession();
				boolean anyChanges = false;
				if (!pageNode.hasNode(Constants.GUIDE_NAV_PATH)) {
					setupNode = pageNode.addNode(Constants.GUIDE_NAV_PATH,Constants.UNSTRUCTURED);
					anyChanges = true;
				} else {
					setupNode = pageNode.getNode(Constants.GUIDE_NAV_PATH);
				}
				if (!setupNode.hasProperty(Constants.GUIDE_NAV_SECTIONS_PAGE_ATTR) 
						|| setupNode.getProperty(Constants.GUIDE_NAV_SECTIONS_PAGE_ATTR).isNew()) {
					setupNode.setProperty(Constants.SLINGTYPE, Constants.GUIDE_NAV_COMPONENT);
					setupNode.setProperty(Constants.GUIDE_NAV_SECTIONS_PAGE_ATTR,
						new String[]{
							"{\"link\":\""+Constants.PARTS_REQ_DEF_GUIDE_NAV_LINK+"\",\"resType\":\""+Constants.PARTS_REQ_R_COMPONENT+"\"}",
							"{\"link\":\""+Constants.TOOLS_REQ_DEF_GUIDE_NAV_LINK+"\",\"resType\":\""+Constants.TOOLS_REQ_R_COMPONENT+"\"}",
							"{\"link\":\""+Constants.EMPTY+"\",\"resType\":\""+Constants.TEXT_COMPONENT+"\"}",
							"{\"link\":\""+Constants.INSTRUCTIONS_DEF_GUIDE_NAV_LINK+"\",\"resType\":\""+Constants.INSTRUCTIONS_COMPONENT+"\"}",
						}
						,PropertyType.STRING);
					anyChanges = true;
				} 
				if (anyChanges) {
					jcr.save();
				}
			} catch (RepositoryException re) {
				log.warn("Got node but could not set defaults, ",re);
			}
		}
	}
	
	private Map<String, String> readTypesAndLabelsFromConfig(Node pageNode) {
		Map<String, String> typesAndLabels = new HashMap<String, String>();
		try {
			Node configuredNode = pageNode.getNode(Constants.GUIDE_NAV_PATH);
			if (configuredNode.hasProperty(Constants.GUIDE_NAV_SECTIONS_PAGE_ATTR)) {
				Property sectionsProp = configuredNode.getProperty(Constants.GUIDE_NAV_SECTIONS_PAGE_ATTR);
				if (sectionsProp.getDefinition().isMultiple() == true) {
					Value[] menuItems = sectionsProp.getValues();

					for (Value menuItem : menuItems) {
						addTuplesFromJson(typesAndLabels,menuItem.getString());
					}
				} else {
					String menuItem = sectionsProp.getString();
					addTuplesFromJson(typesAndLabels,menuItem);
				}
			}
		} catch (Exception e) {
			log.error("Could not retrieve configuration", e);
		}
		return typesAndLabels; 
	}
	
	private void addTuplesFromJson(Map<String,String> toAddTo, String jsonString) {
		try {
            JSONObject menuItemConfig = new JSONObject(jsonString);
            toAddTo.put(
            		menuItemConfig.getString(Constants.GUIDE_CFG_RESTYPE),
            		menuItemConfig.getString(Constants.GUIDE_CFG_TEXT));
	    } catch (Exception e) {
	    	log.error("JSON error", e);
	    }
	}
	
	private String constructCommentsPath() {
		StringBuilder commentsPath = new StringBuilder(Constants.USERGEN_ROOT);
		commentsPath.append(currentPage.getPath());
		commentsPath.append(Constants.GUIDE_COMMENTS_PATH);
		return commentsPath.toString();
	}

	// All the ways we have of generating links, generate a label and an href
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

	// Template links won't usually have a resource associated when they are built
	private static abstract class TemplateLinkGenerator extends LinkGenerator {
		private String componentName;
		
		public void setComponentName(String componentName) {
			this.componentName = componentName;
		}
		
		public String getComponentName() {
			if (componentName == null) {
				String fullType = getComponentType();
				setComponentName(fullType.substring(fullType.lastIndexOf("/") + 1));
			}
			return componentName;
		}
		@Override
		public final String generateLink() {
			return "template_" + getComponentName();
		}
	}
	
	// Some template components always have the same label
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
	
	// But comments have a dynamically generated label
	private static class CommentsLinkGenerator extends TemplateLinkGenerator {
		private final String commentsPath;
		private final ResourceResolver rr;
		
		public CommentsLinkGenerator(final String commentsPath, final ResourceResolver rr) {
			super();
			setComponentType(Constants.COMMENTS_COMPONENT);
			this.commentsPath = commentsPath;
			this.rr = rr;
		}

		@Override
		public String generateLabel() {
			StringBuilder label = new StringBuilder(
					Constants.COMMENTS_GUIDE_NAV_LINK_PREFIX);
			Resource thoseComments = rr.resolve(commentsPath);
			if (resourceExists(thoseComments)) {
				CommentSystem thatCs = null;
				thatCs = thoseComments.adaptTo(CommentSystem.class);
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
	
	// Some template links are based on actual resources
	private static abstract class ResourceTemplateLinkGenerator extends TemplateLinkGenerator {
		private Resource beingLinkedTo;

		protected void setBeingLinkedTo(Resource beingLinkedTo) {
			this.beingLinkedTo = beingLinkedTo;
		}
		
		protected Resource getBeingLinkedTo() {
			return beingLinkedTo;
		}
		
	}
	
	private static class TextHeaderLinkGenerator extends ResourceTemplateLinkGenerator {
		
		private static final Pattern headerPattern = initHeaderPattern();
		private static final Pattern initHeaderPattern() {
			return Pattern.compile("<h([1-6])[^>]*>(.+?)</h\\1>",  Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
		}
		
		public TextHeaderLinkGenerator(Resource textResource) {
			setBeingLinkedTo(textResource);
		}

		@Override
		public String generateLabel() {
			StringBuffer hContents = new StringBuffer();
			Resource textResource = getBeingLinkedTo();
			try {
				String html = textResource.adaptTo(Node.class).getProperty(Constants.GUIDE_TEXT_LABEL_PROP).getString();
				Matcher pageMatcher = headerPattern.matcher(html);
				if (pageMatcher.find()){
				    hContents.append(pageMatcher.group(2));
				}
			} catch (Exception e) {
				log.error("retrieving text label: ", e);
			}
			return hContents.toString();
		}
	}
}
