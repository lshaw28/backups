package com.spd.cq.searspartsdirect.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.spd.cq.searspartsdirect.common.helpers.PSSettingsHelper;
import com.spd.cq.searspartsdirect.common.helpers.PartsDirectAPIHelper;
import com.spd.cq.searspartsdirect.common.model.ResponsiveTabsModel;

/**
 * Custom Tag to get model all diagram
 * 
 * @author Pankaj Rohira
 * 
 */
@SuppressWarnings("serial")
public class GetResponsiveTabsTag extends CQBaseTag {
	protected final static Logger log = LoggerFactory
			.getLogger(GetResponsiveTabsTag.class);

	private String formattedModelNumber;
	private String brandId;
	private String productCategoryId;
	private String API_ROOT;
	private PartsDirectAPIHelper apiHelper;

	@Override
	public int doStartTag() throws JspException {

		try {
			PSSettingsHelper helper = sling.getService(PSSettingsHelper.class);
			API_ROOT = helper.getPartsDirectProductAPI();
			apiHelper = new PartsDirectAPIHelper();

			String jsonDynamicTabs = getDynamicTabs();

			Gson gson = new Gson();
			ResponsiveTabsModel[] responsiveTabs = gson.fromJson(jsonDynamicTabs,
					ResponsiveTabsModel[].class);

			pageContext.setAttribute("responsiveTabs", responsiveTabs);
		} catch (IOException e) {
			log.error("IO Exception: ModelSearchResultsTag: doStartTag(): ",
					e.getMessage());
		}
		return SKIP_BODY;
	}

	private String getDynamicTabs() throws IOException {
		String API_URL = API_ROOT + "model-tabs-details?formattedModelNumber=" + formattedModelNumber
				+ "&brandId=" + brandId + "&categoryId=" + productCategoryId;
		return apiHelper.readJsonData(API_URL);
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getFormattedModelNumber() {
		return formattedModelNumber;
	}

	public void setFormattedModelNumber(String formattedModelNumber) {
		this.formattedModelNumber = formattedModelNumber;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
}