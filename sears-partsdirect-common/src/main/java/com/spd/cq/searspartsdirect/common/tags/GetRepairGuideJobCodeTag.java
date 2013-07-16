package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.model.spdasset.JobCodeModel;

public class GetRepairGuideJobCodeTag extends CQBaseTag {

	private static final long serialVersionUID = 1L;
	protected static final Logger log = LoggerFactory.getLogger(GetRepairGuideJobCodeTag.class);

	@Override
	public int doStartTag() throws JspException {

		List<JobCodeModel> repairGuideJobCodes = new ArrayList<JobCodeModel>();
		try {

			// Get the jobCode associated with the current repair guide
			Page repairGuidePage = pageManager.getPage(currentPage.getPath());

			if (repairGuidePage != null && repairGuidePage.getProperties() != null) {
				if (repairGuidePage.getProperties().containsKey("pages")) {
					String[] jobCodePagePaths = repairGuidePage.getProperties().get("pages", String[].class);

					for (String jobCodePagePath : jobCodePagePaths) {
						Resource jobCodeResource = resourceResolver.getResource(jobCodePagePath);
						if (jobCodeResource != null) {
							Page jobCodeResourcePage = jobCodeResource.adaptTo(Page.class);
							if (jobCodeResourcePage != null) {
								repairGuideJobCodes.add(new JobCodeModel(jobCodeResourcePage.getProperties().get("id", String.class), null, null, null));
							}
						}
					}
				}
			}

			pageContext.setAttribute("repairGuideJobCodes", repairGuideJobCodes);

		} catch (Exception e) {
			log.error("Error fetching jobCode for: " + currentPage.getPath());
		}

		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
