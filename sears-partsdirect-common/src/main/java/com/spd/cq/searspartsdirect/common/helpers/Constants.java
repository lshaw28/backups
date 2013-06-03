package com.spd.cq.searspartsdirect.common.helpers;

public final class Constants {

	public static final String EMPTY = "";
	
	public static final String ASSETS_PATH = ident("/etc/spdAssets/scaffolding");
	public static final String ASSETS_TITLE_PATH = ident("jcr:title");
	public static final String ASSETS_DESCRIPTION_PATH = ident("jcr:description");
	public static final String ASSETS_LOGO_PATH = ident("/jcr:content/logo");
	public static final String ASSETS_IMAGE_PATH = ident("/jcr:content/logo");
	
	// Originally for guide nav, but generally applicable
	public static final String COMMENTS_COMPONENT = ident("searspartsdirect/components/content/comments");
	public static final String SUBHEAD_COMPONENT = ident("searspartsdirect/components/content/subhead");
	public static final String PARTS_REQ_R_COMPONENT = ident("searspartsdirect/components/content/partsRequiredRepair");
	public static final String TOOLS_REQ_R_COMPONENT = ident("searspartsdirect/components/content/toolsRequiredRepair");
	
	public static final String USERGEN_ROOT = ident("/content/usergenerated");
	
	public static final String COMMENTS_PATH = ident("/jcr:content/comments");
	
	// Constants specific to guide nav
	public static final String PARTS_REQ_DEF_GUIDE_NAV_LINK = ident("Parts required");
	public static final String TOOLS_REQ_DEF_GUIDE_NAV_LINK = ident("Tools required");
	public static final String COMMENTS_GUIDE_NAV_LINK_PREFIX = ident("Comments");
	// following MUST match attribute declaration in 
	// /apps/searspartsdirect/components/page/repairGuide/tab_page_properties.xml
	public static final String GUIDE_NAV_JUMPTO_TEXT_PAGE_ATTR = ident("jumpToString");
	public static final String GUIDE_NAV_DEF_JUMPTO_TEXT = ident("Jump to...");
	// following MUST match attribute declaration in 
	// /apps/searspartsdirect/components/page/repairGuide/tab_page_properties.xml
	public static final String GUIDE_NAV_SECTIONS_PAGE_ATTR = ident("sections");
	
	public static final String RECENTLY_VIEWED_MODEL_COOKIE = "recentlyViewedModels";
	public static final String RECENTLY_VIEWED_PART_COOKIE = "recentlyViewedParts";

	/**
	 * This method returns its argument - this is to keep what would otherwise be literals
	 * from being compiled into client code. If you want to be able to change a constant here
	 * without needing to recompile all clients, you should wrap its initialization in this
	 * call.
	 * 
	 * @param t
	 * @return t
	 */
	private final static <T> T ident(T t) {
		return t;
	}
}
