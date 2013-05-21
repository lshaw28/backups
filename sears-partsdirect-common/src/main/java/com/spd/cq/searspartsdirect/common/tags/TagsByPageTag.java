package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;

/**
 * Custom tag to draw out a list of Tag objects when given a Page path
 * Defaults to current page
 * @author Joseph
 *
 */
public class TagsByPageTag extends CQBaseTag {

	protected static Logger log = LoggerFactory.getLogger(TagsByPageTag.class);
	protected String pagepath;
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<Tag> tags = new ArrayList<Tag>();
		ArrayList<Tag> brandTags = new ArrayList<Tag>();
		ArrayList<Tag> parentCategoryTags = new ArrayList<Tag>();
		ArrayList<Tag> productTags = new ArrayList<Tag>();
		ArrayList<Tag> subCategoryTags = new ArrayList<Tag>();
		ArrayList<Tag> modelNumberTags = new ArrayList<Tag>();
		
		Tag[] pageTags = null;
		if (pagepath != null) {
			pageTags = pageManager.getPage(pagepath).getTags();
		}
		else {
			pageTags = currentPage.getTags();
		}
		for (Tag tag : pageTags) {
			tags.add(tag);
			if (tag.getParent() != null &&
					tag.getParent().getTagID().equals("searspartsdirect:brands")) {
				brandTags.add(tag);
			}
			if (tag.getParent() != null &&
					tag.getParent().getTagID().equals("searspartsdirect:product_categories")) {
				parentCategoryTags.add(tag);
			}
			if (Pattern.matches("searspartsdirect:product_categories/[^/]+?/[^/]+?$",tag.getTagID())) {
				productTags.add(tag);
			}
			if (Pattern.matches("searspartsdirect:product_categories/[^/]+?/[^/]+?/[^/]+?$",tag.getTagID()) &&
					!tag.getTagID().endsWith("type")) {
				subCategoryTags.add(tag);
			}
			if (tag.getParent() != null &&
					tag.getParent().getTagID().equals("searspartsdirect:model_numbers")) {
				modelNumberTags.add(tag);
			}
		}
		pageContext.setAttribute("tags",tags);
		pageContext.setAttribute("brandTags",brandTags);
		pageContext.setAttribute("parentCategoryTags",parentCategoryTags);
		pageContext.setAttribute("productTags",productTags);
		pageContext.setAttribute("subCategoryTags",subCategoryTags);
		pageContext.setAttribute("modelNumberTags",modelNumberTags);
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
	
	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}
}
