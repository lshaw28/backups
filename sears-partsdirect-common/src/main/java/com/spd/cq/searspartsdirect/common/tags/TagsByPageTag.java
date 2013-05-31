package com.spd.cq.searspartsdirect.common.tags;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

/**
 * Custom tag to draw out a list of Tag objects when given a Page path
 * Defaults to current page
 * @author Joseph
 *
 */
public class TagsByPageTag extends CQBaseTag {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LoggerFactory.getLogger(TagsByPageTag.class);
	protected String pagepath;
	protected String tagType;
	
	@Override
	public int doStartTag() throws JspException {
		ArrayList<Tag> tags = new ArrayList<Tag>();
		LinkedHashSet<String> parentCategoryTitles = new LinkedHashSet<String>();
		Tag brandTag = null;
		Tag parentCategoryTag = null;
		Tag productTag = null;
		Tag subCategoryTag = null;
		Tag modelNumberTag = null;
		
		TagManager tm = resourceResolver.adaptTo(TagManager.class);
		Tag[] pageTags = null;
		if (pagepath != null) {
			pageTags = pageManager.getPage(pagepath).getTags();
			
		}
		else {
			pageTags = currentPage.getTags();
		}
		
		Pattern p;
		Matcher m;
		for (Tag tag : pageTags) {
			if (tagType == null) {
				tags.add(tag);
			}
			String tagID = tag.getTagID();
			//Brand
			if (tagType == null || tagType.equals("brand")) {
				if (tag.getParent() != null &&
						tag.getParent().getTagID().equals("searspartsdirect:brands")) {
					brandTag = tag;
				}
			}
			//Parent Category - creates the parentCategoryTag tag and parentCategoryTitles LinkedHashSet
			if (tagType == null || tagType.equals("parentCategory")) {
				p = Pattern.compile("(searspartsdirect:parent_categories/[^/]+)");
				m = p.matcher(tagID);
				if (m.find()) {
					Tag t = tm.resolve(m.group());
					if (t != null) {
						parentCategoryTag = t;
						parentCategoryTitles.add(t.getTitle());
					}
				}
			}
			
			//Product (Category)
			if (tagType == null || tagType.equals("product")) {
				p = Pattern.compile("(searspartsdirect:product_categories/[^/]+/[^/]+)");
				m = p.matcher(tagID);
				if (m.find()) {
					Tag t = tm.resolve(m.group());
					if (t != null) {
						productTag = t;
					}
				}
			}
			
			//SubCategory
			if (tagType == null || tagType.equals("subCategory")) {
				p = Pattern.compile("(searspartsdirect:product_categories/[^/]+/[^/]+/[^/]+)");
				m = p.matcher(tagID);
				if (m.find()) {
					Tag t = tm.resolve(m.group());
					if (t != null) {
						subCategoryTag = t;
					}
				}
			}
			//Model
			if (tagType == null || tagType.equals("model")) {
				if (tag.getParent() != null &&
						tag.getParent().getTagID().equals("searspartsdirect:model_numbers")) {
					modelNumberTag = tag;
				}
			}
		}
		if (tagType == null) {
			pageContext.setAttribute("tags",tags);
		}
		if (tagType == null || tagType.equals("brand")) {
			pageContext.setAttribute("brandTag",brandTag);
		}
		if (tagType == null || tagType.equals("parentCategory")) {
			pageContext.setAttribute("parentCategoryTag",parentCategoryTag);
			pageContext.setAttribute("parentCategoryTitles",parentCategoryTitles);
		}
		if (tagType == null || tagType.equals("product")) {
			pageContext.setAttribute("productTag",productTag);
		}
		if (tagType == null || tagType.equals("subCategory")) {
			pageContext.setAttribute("subCategoryTag",subCategoryTag);
		}
		if (tagType == null || tagType.equals("model")) {
			pageContext.setAttribute("modelNumberTag",modelNumberTag);
		}
		
        return SKIP_BODY;
	}
	
	@Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
	
	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}
	public void setTagType(String tagType) {
		this.tagType = tagType;
	}
}
