package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	protected static Logger log = LoggerFactory
			.getLogger(GuideNavigationTag.class);

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
	    } catch (RepositoryException re) {
	            log.error("exception getting jump to text",re);
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
		
		// We find the parsys indicated by the template
		Resource parsysResource = currentPage.getContentResource(Constants.GUIDE_TOP_PARSYS_NAME);
		if (log.isDebugEnabled()) log.debug("parsysResource is "+parsysResource);
		
		if (parsysResource != null) {
			// We iterate the parsys and see what we want to generate links for
			Iterator<Resource> parsysChildren = parsysResource.listChildren();
			ParsysLinkGeneratorFactory generatorFactory = new ParsysLinkGeneratorFactory(parsysResource);
			while (parsysChildren.hasNext()) {
				Resource parsysChild = parsysChildren.next();
				if (log.isDebugEnabled()) {
					log.debug("parsysChild is "+parsysChild);
					log.debug("parsysChild.resourceType is "+parsysChild.getResourceType());
					log.debug("parsysChild.resourceSuperType is "+parsysChild.getResourceSuperType());
				}
				String childResourceType = parsysChild.getResourceType();
				// when we find a resource type we recognize, we create a generator for a link to it
				if(typesAndLabels.containsKey(childResourceType)) {
					String label = typesAndLabels.get(childResourceType);
					ParsysLinkGenerator maybeGenerator = generatorFactory.getLinkGenerator(parsysChild,label);
					if (maybeGenerator != null) {
						generators.add(maybeGenerator);
					}
				}
			}
		} else {
			log.info("parsysResource is null for some reason");
		}
		
		// We create generators for template components thqt are after the parsys
		if (typesAndLabels.containsKey(Constants.COMMENTS_COMPONENT)) {
			generators.add(new CommentsLinkGenerator(constructCommentsPath(),resourceResolver));
		}
		
		// We iterate over our generators and create our output
		for (LinkGenerator linkGen : generators) {
			if (log.isDebugEnabled()) log.debug("generator is for "+linkGen.getComponentType());
			List<String> newList = new ArrayList<String>();
			newList.add(linkGen.generateLabel());
			newList.add(linkGen.generateLink());
			sections.add(newList);
		}
		pageContext.setAttribute(Constants.GUIDE_NAV_SECTIONS_PAGE_ATTR, sections);
		
		return SKIP_BODY;
	}
	
	private boolean labelIsBlank(String label) {
		return label == null || label.matches(" *");
	}
	
	private void maybeSetupDefaultConfig(Node pageNode) {
		if (log.isDebugEnabled()) log.debug("Maybe doing 1st-time setup");
		WCMMode wcmMode = WCMMode.fromRequest(slingRequest);
		if (wcmMode != WCMMode.READ_ONLY) {
			if (log.isDebugEnabled()) log.debug("Not read-only");
			try {
				if (pageNode == null) {
					log.warn("Could not get the node to set up");
					return;
				}
				if (log.isDebugEnabled()) log.debug("Found setup node");
				Node setupNode;
				Session jcr = pageNode.getSession();
				boolean anyChanges = false;
				if (!pageNode.hasNode(Constants.GUIDE_NAV_PATH)) {
					setupNode = pageNode.addNode(Constants.GUIDE_NAV_PATH,Constants.UNSTRUCTURED);
					anyChanges = true;
				} else {
					setupNode = pageNode.getNode(Constants.GUIDE_NAV_PATH);
				}
				if (!setupNode.hasProperty("sections") || setupNode.getProperty("sections").isNew()) {
					setupNode.setProperty(Constants.SLINGTYPE, Constants.GUIDE_NAV_COMPONENT);
					setupNode.setProperty("sections",
						new String[]{
							"{\"link\":\""+Constants.PARTS_REQ_DEF_GUIDE_NAV_LINK+"\",\"resType\":\""+Constants.PARTS_REQ_R_COMPONENT+"\"}",
							"{\"link\":\""+Constants.TOOLS_REQ_DEF_GUIDE_NAV_LINK+"\",\"resType\":\""+Constants.TOOLS_REQ_R_COMPONENT+"\"}",
							"{\"link\":\""+Constants.INSTRUCTIONS_DEF_GUIDE_NAV_LINK+"\",\"resType\":\""+Constants.INSTRUCTIONS_COMPONENT+"\"}",
							"{\"link\":\""+Constants.EMPTY+"\",\"resType\":\""+Constants.COMMENTS_COMPONENT+"\"}",
							"{\"link\":\""+Constants.EMPTY+"\",\"resType\":\""+Constants.SUBHEAD_COMPONENT+"\"}",
						}
						,PropertyType.STRING);
					if (log.isDebugEnabled()) log.debug("Performed setup");
					anyChanges = true;
				} else {
					if (log.isDebugEnabled()) log.debug("Is already set up");
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

	// Template links won't have a resource associated when they are built
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
				setComponentName(fullType.substring(fullType.lastIndexOf("/") + 1));
				if (log.isDebugEnabled()) log.debug("componentName is "+componentName);
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
			if (thoseComments != null) {
				CommentSystem thatCs = null;
				try {
					thatCs = thoseComments
							.adaptTo(CommentSystem.class);
				} catch (NullPointerException npe) {
					thatCs = null;
				}
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
	
	// Parsys links are generated based on a resource node. Some have specific logic.
	private static class ParsysLinkGeneratorFactory {
		private final static Map<String,ParsysLinkGeneratorMaker> makers = initMakers();
		private final static Map<String,ParsysLinkGeneratorMaker> initMakers() {
			Map<String,ParsysLinkGeneratorMaker> makers = new HashMap<String,ParsysLinkGeneratorMaker>();
			makers.put(Constants.SUBHEAD_COMPONENT, new SubheadLinkGeneratorMaker());
			return makers;
		}
		
		private final Resource parsysResource;
		
		public ParsysLinkGeneratorFactory(final Resource parsysResource) {
			this.parsysResource = parsysResource;
		}
		
		public ParsysLinkGenerator getLinkGenerator(Resource maybeBeingLinkedTo, String possiblyLabel) {
			ParsysLinkGeneratorMaker maker = makers.get(maybeBeingLinkedTo.getResourceType());
			ParsysLinkGenerator weMade = null;
			if (maker != null) {
				if (log.isDebugEnabled()) log.debug("Found maker for "+maybeBeingLinkedTo);
				maker.setParsysName(parsysResource.getName());
				weMade = maker.createGenerator(maybeBeingLinkedTo);
			} else {
				if (log.isDebugEnabled()) log.debug("No maker for "+maybeBeingLinkedTo);
				weMade = new FixedLabelParsysLinkGenerator(parsysResource.getName(), maybeBeingLinkedTo, possiblyLabel);
			}
			return weMade;
		}
	}
	
	// We have this so that we can explicitly declare what generator to create for a given resource type
	private static abstract class ParsysLinkGeneratorMaker {
		private String parsysName;
		
		public void setParsysName(final String parsysName) {
			this.parsysName = parsysName;
		}
		public String getParsysName() {
			return parsysName;
		}
		public abstract ParsysLinkGenerator createGenerator(Resource parsysChild);
	}
	
	private static class SubheadLinkGeneratorMaker extends ParsysLinkGeneratorMaker {
		public ParsysLinkGenerator createGenerator(Resource subhead) {
			return new SubheadLinkGenerator(getParsysName(),subhead);
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
		
		private Resource beingLinkedTo;

		public Resource getBeingLinkedTo() {
			return beingLinkedTo;
		}

		public void setBeingLinkedTo(Resource beingLinkedTo) {
			this.beingLinkedTo = beingLinkedTo;
		}

		@Override
		public String generateLink() {
			String linkTarget = null;
			linkTarget = getParsysName() + "_" + getBeingLinkedTo().getName();
			return linkTarget;
		}
	}
	
	private static class FixedLabelParsysLinkGenerator extends ParsysLinkGenerator {
		final String label;
	
		public FixedLabelParsysLinkGenerator(String parsysName, Resource beingLinkedTo, String label) {
			setParsysName(parsysName);
			setBeingLinkedTo(beingLinkedTo);
			this.label = label;
		}
		
		@Override
		public String generateLabel() {
			return label;
		}
	}
	
	private static class SubheadLinkGenerator extends ParsysLinkGenerator {
		public SubheadLinkGenerator(String parsysName, Resource subheadBeingLinkedTo) {
			setParsysName(parsysName);
			setBeingLinkedTo(subheadBeingLinkedTo);
		}
		
		@Override
		public String generateLabel() {
			String label = Constants.EMPTY;
			try {
				label = getBeingLinkedTo().adaptTo(Node.class).getProperty(Constants.GUIDE_SUBHEAD_LABEL_PROP)
						.getString();
			} catch (Exception e) {
				log.error("retrieving subhead label: ", e);
			}
			return label;
		}
	}
}
