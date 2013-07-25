package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.jsp.JspException;

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
import com.spd.cq.searspartsdirect.common.model.SymptomDetailsModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.JobCodeModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.PartTypeModel;

public class GetSymptomDetailTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	private Session session;
	private QueryBuilder builder;
	private Query query;
	SymptomDetailsModel symptomDetailsModel;
	List<JobCodeModel> jobCodeModels;
	public static final Logger log = LoggerFactory.getLogger(GetSymptomDetailTag.class);
	private static final String PART_TYPE = "partType";
	private static final String GUIDES = "guides";
	private String id;

	@Override
	public int doStartTag() throws JspException {
		session = slingRequest.getResourceResolver().adaptTo(Session.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("path", Constants.ASSETS_PATH + "/symptom");
		map.put("type", Constants.CQ_PAGE);
		map.put("property", Constants.ASSETS_ID_REL_PATH);
		map.put("property.value", id);

		builder = resourceResolver.adaptTo(QueryBuilder.class);
		query = builder.createQuery(PredicateGroup.create(map), session);
		SearchResult result = query.getResult();
		symptomDetailsModel = new SymptomDetailsModel();

		for (Hit hit : result.getHits()) {
		try {
				ValueMap props = hit.getProperties();
				if (props != null) {
					symptomDetailsModel.setTitle(props.get(Constants.ASSETS_TITLE_PATH, String.class));
					symptomDetailsModel.setDescription(props.get(Constants.ASSETS_DESCRIPTION_PATH, String.class));

					// now get the jobcodes, part types and guides info
					String[] pages = props.get("pages", String[].class);
					if (pages != null) {
						jobCodeModels = new ArrayList<JobCodeModel>();
						for(int i = 0; i< pages.length; i++) {
							log.debug("**pages[" + i + "]: " + pages[i]);
							if (pages[i].indexOf(Constants.ASSETS_PATH.concat("/jobCode")) > -1) {
								Page jobCodePage = pageManager.getPage(pages[i]);
								if (jobCodePage != null) {
									ValueMap jobCodeProps = jobCodePage.getProperties();
									if (jobCodeProps != null) {
										String id = (String) jobCodeProps.get(Constants.ASSETS_ID);
										JobCodeModel jobCodeModel = new JobCodeModel(id, jobCodePage.getPath(), jobCodePage.getTitle(), jobCodePage.getDescription());

										String partType = (String) jobCodeProps.get(PART_TYPE);
										Page partTypePage = pageManager.getPage(partType);

										if (partTypePage != null) {
											PartTypeModel partTypeModel = new PartTypeModel(partTypePage.getPath(),
													partTypePage.getTitle(), partTypePage.getDescription(),
													partTypePage.getPath() + Constants.ASSETS_IMAGE_PATH, null);
											jobCodeModel.setPartTypeModel(partTypeModel);
										} else {
											log.warn("Could not resolve "+partType+" to a part type page.");
										}
										jobCodeModels.add(jobCodeModel);

										//getting guides
										String[] guides = jobCodeProps.get(GUIDES, String[].class);
										if (guides != null) {
											List<GuideModel> guideList = new ArrayList<GuideModel>();
											for (int j = 0; j<guides.length; j++) {
												Page guidePage = pageManager.getPage(guides[j]);
												if (guidePage != null) {
													GuideModel guide = new GuideModel(guidePage.getPath(), null, guidePage.getTitle());
													guideList.add(guide);
												} else {
													log.warn("Could not resolve "+guides[j]+" to a guide page.");
												}
											}
											jobCodeModel.setGuides(guideList);
										} else {
											log.debug("no guides for this jobcode");
										}
									} else {
										log.debug("no properties for jobcode");
									}
								} else {
									log.warn("Could not resolve "+pages[i]+" to a job code.");
								}
							}
						}
					} else {
						log.debug("no pages found for the symptom");
					}
					symptomDetailsModel.setJobCodeModels(jobCodeModels);
				}
			} catch (RepositoryException e) {
				log.error("Failure to get the symptom details ",e.fillInStackTrace());
			}
		}

		pageContext.setAttribute("symptom", symptomDetailsModel);
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


}
