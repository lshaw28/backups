package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
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
import com.spd.cq.searspartsdirect.common.model.ErrorCodeListModel;
import com.spd.cq.searspartsdirect.common.model.spdasset.BrandModel;

public class GetErrorCodesListTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(GetErrorCodesListTag.class);
	private String categoryPath;
	private String categoryName;
	Session session;
	QueryBuilder builder;
	Query query;

	@Override
	public int doStartTag() throws JspException {
		if (StringUtils.isNotBlank(categoryPath)) {
			Map<BrandModel, List<ErrorCodeListModel>> tempErrorCodeList = new LinkedHashMap<BrandModel, List<ErrorCodeListModel>>();
			Map<BrandModel, List<ErrorCodeListModel>> errorCodeList = new LinkedHashMap<BrandModel, List<ErrorCodeListModel>>();

			session = slingRequest.getResourceResolver().adaptTo(Session.class);

			Map<String, String> map = new HashMap<String, String>();
			map.put("path", Constants.CATEGORIES_ROOT + "/" + categoryName + "-repair/error-codes");
			map.put("type", Constants.CQ_PAGE);
			map.put("1_property", Constants.ASSETS_PAGES_REL_PATH);
			map.put("1_property.value", categoryPath);
			map.put("2_property", Constants.TEMPLATE_REL_PATH);
			map.put("2_property.value", Constants.ERROR_CODE_TEMPLATE);

			builder = resourceResolver.adaptTo(QueryBuilder.class);
			query = builder.createQuery(PredicateGroup.create(map), session);

			SearchResult result = query.getResult();

			for (Hit hit : result.getHits()) {
				try {
					ValueMap props = hit.getProperties();

					Page p = pageManager.getPage(hit.getPath());

					if (props != null) {
						ErrorCodeListModel model = new ErrorCodeListModel(props.get("jcr:title", String.class), p.getPath());
						String[] pages = props.get("pages", String[].class);

						if (pages != null) {
							log.debug("pages.length " + pages.length);
							for (int i = 0; i< pages.length; i++) {
								if (pages[i].contains("/brand")) {
									List<ErrorCodeListModel> errorCodeModels = new ArrayList<ErrorCodeListModel>();
									Page page = pageManager.getPage(pages[i]);
									BrandModel brandModel = new BrandModel(page.getName(),"", page.getTitle(), page.getDescription(), page.getPath() + Constants.ASSETS_LOGO_PATH);
									if (!tempErrorCodeList.containsKey(brandModel)) {
										errorCodeModels.add(model);
										tempErrorCodeList.put(brandModel, errorCodeModels);
									} else {
										List<ErrorCodeListModel> newModel = tempErrorCodeList.get(brandModel);
										newModel.add(model);
										tempErrorCodeList.remove(brandModel);
										tempErrorCodeList.put(brandModel, newModel);
									}
								}
							}
						} else {
							log.debug("pags is null");
						}
					} else {
						log.debug("props is null");
					}
				} catch (RepositoryException e) {
					log.error("Error while getting the error code list data, ", e.fillInStackTrace());
				}
			}

			//need to sort the collection by brand name
			List<BrandModel> brandKeys = new ArrayList<BrandModel>();
			brandKeys.addAll(tempErrorCodeList.keySet());

			Collections.sort(brandKeys, new Comparator<BrandModel>() {
				public int compare(BrandModel o1, BrandModel o2) {
					return o1.getTitle().compareToIgnoreCase(o2.getTitle());
				}
			});

			for (BrandModel brandModel : brandKeys) {
				errorCodeList.put(brandModel, tempErrorCodeList.get(brandModel));
			}

			pageContext.setAttribute("errorCodeList", errorCodeList);
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getCategoryPath() {
		return categoryPath;
	}

	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}