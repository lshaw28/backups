package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;
import com.spd.cq.searspartsdirect.common.model.GuideModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.PartTypeModel;

public class GetCommonPartsTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetCommonPartsTag.class);

	private Session session;
	private QueryBuilder builder;
	private Query query;
	private String categoryPath;
	List<PartTypeModel> partTypes;
	List<String> anchors;

	@Override
	public int doStartTag() throws JspException {
		if (!StringUtils.isEmpty(categoryPath)) {
			partTypes = new ArrayList<PartTypeModel>();
			anchors = new ArrayList<String>();

			session = slingRequest.getResourceResolver().adaptTo(Session.class);
			Map<String, String> map = new HashMap<String, String>();
			map.put("path", Constants.ASSETS_PATH + "/partType");
			map.put("type", Constants.CQ_PAGE);
			map.put("property", Constants.ASSETS_PAGES_REL_PATH);
			map.put("property.value", categoryPath);
			map.put("orderby", "@"+ Constants.ASSETS_TITLE_REL_PATH);
			map.put("orderby.index","true");
			map.put("orderby.sort", "asc");

			builder = resourceResolver.adaptTo(QueryBuilder.class);
			query = builder.createQuery(PredicateGroup.create(map), session);
			SearchResult result = query.getResult();

			for (Hit hit : result.getHits()) {
				try {
					ValueMap props = hit.getProperties();
					Page partTypePage = pageManager.getPage(hit.getPath());
					if (props != null) {
						PartTypeModel partType = new PartTypeModel(partTypePage.getPath(), 
								props.get(Constants.ASSETS_TITLE_PATH, String.class), 
								props.get(Constants.ASSETS_DESCRIPTION_PATH, String.class), 
								partTypePage.getPath() + Constants.ASSETS_IMAGE_PATH,  
								props.get(Constants.ASSETS_TITLE_PLURAL, String.class));
						
						if (!StringUtils.isEmpty(partType.getTitle()) && !anchors.contains(partType.getTitle().substring(0, 1))) {
							partType.setAnchor(partType.getTitle().substring(0, 1));
							anchors.add(partType.getTitle().substring(0, 1));
						}
						
						ValueMap partTypesProps = partTypePage.getProperties();
						String[] guides = partTypesProps.get(Constants.ASSETS_GUIDES, String[].class);

						if (guides != null) {
							List<GuideModel> guideList = new ArrayList<GuideModel>();
							for (int i = 0; i < guides.length; i++) {
								Page guidePage = pageManager.getPage(guides[i]);
								if (guidePage != null) {
									GuideModel guide = new GuideModel(guidePage.getPath(), null, guidePage.getTitle());
									guideList.add(guide);
								} else {
									log.warn("Could not resolve "+guides[i]+" to a repair guide");
								}
							}
							partType.setGuides(guideList);
						} else {
							log.info("no guides for the part type at "+partType.getPath());
						}
						partTypes.add(partType);
					}
				} catch(Exception e) {
					log.error("Retrieving common parts, ", e);
				}
			}
			pageContext.setAttribute("commonParts", partTypes);
		} else {
			log.debug("CategoryPath is null");
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
