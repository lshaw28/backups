package com.spd.cq.searspartsdirect.common.helpers;

public final class Constants {

	public static final String EMPTY = "";
	
	//CQ constants
	public static final String CQ_PAGE = ident("cq:Page");
	public static final String CQ_TAG = ident("cq:Tag");
	public static final String CQ_DEFAULT_ERROR_PAGE = ident("/libs/sling/servlet/errorhandler/default.jsp");
	public static final String CQ_WCMMODE_REQ_ATTR = ident("com.day.cq.wcm.api.WCMMode");
	
	//Asset Constants
	public static final String ASSETS_PATH = ident("/etc/spdAssets/scaffolding");
	public static final String ASSETS_TITLE_PATH = ident("jcr:title");
	public static final String ASSETS_TITLE_REL_PATH = ident("jcr:content/jcr:title");
	public static final String ASSETS_PAGES_REL_PATH = ident("jcr:content/pages");
	public static final String ASSETS_DESCRIPTION_PATH = ident("jcr:description");
	public static final String ASSETS_LOGO_PATH = ident("/jcr:content/logo");
	public static final String ASSETS_IMAGE_PATH = ident("/jcr:content/image");
	public static final String ASSETS_IMAGE_REL_PATH = ident("jcr:content/image");
	
	//Model Search Constants
	public static final String MODEL_NOT_FOUND = ident("Not Found");
	
	//DAM Sub-directory Constants
	public static final String DAM_APPROVED_PATH = ident("/content/dam/searspartsdirect");
	public static final String DAM_DENIED_PATH = ident("/content/dam/assetsdenied");
	public static final String DAM_PENDING_APPROVAL_PATH = ident("/content/dam/assetspendingapproval");
	
	// Originally for guide nav, but generally applicable
	public static final String GUIDE_NAV_COMPONENT = ident("searspartsdirect/components/content/guideNavigation");
	public static final String COMMENTS_COMPONENT = ident("searspartsdirect/components/content/comments");
	public static final String SUBHEAD_COMPONENT = ident("searspartsdirect/components/content/subhead");
	public static final String PARTS_REQ_R_COMPONENT = ident("searspartsdirect/components/content/partsRequiredRepair");
	public static final String TOOLS_REQ_R_COMPONENT = ident("searspartsdirect/components/content/toolsRequiredRepair");
	public static final String INSTRUCTIONS_COMPONENT = ident("searspartsdirect/components/content/repairInstructions");
	public static final String TEXT_COMPONENT = ident("searspartsdirect/components/content/text");
	
	public static final String USERGEN_ROOT = ident("/content/usergenerated");
	public static final String ARTICLES_ROOT = ident("/content/searspartsdirect/en/articles");
	
	public static final String GUIDE_COMMENTS_PATH = ident("/jcr:content/comments");
	public static final String GUIDE_NAV_PATH = ident("guideNavigation");
	public static final String SLINGTYPE = ident("sling:resourceType");
	public static final String UNSTRUCTURED = ident("nt:unstructured");
	
	// Constants specific to guide nav
	public static final String PARTS_REQ_DEF_GUIDE_NAV_LINK = ident("Parts required");
	public static final String TOOLS_REQ_DEF_GUIDE_NAV_LINK = ident("Tools needed");
	public static final String INSTRUCTIONS_DEF_GUIDE_NAV_LINK = ident("Instructions");
	public static final String COMMENTS_GUIDE_NAV_LINK_PREFIX = ident("Comments");
	// following MUST match attribute declaration in 
	// /apps/searspartsdirect/components/page/repairGuide/tab_page_properties.xml
	public static final String GUIDE_NAV_JUMPTO_TEXT_PAGE_ATTR = ident("jumpToString");
	public static final String GUIDE_NAV_DEF_JUMPTO_TEXT = ident("Jump to...");
	// following MUST match attribute declaration in 
	// /apps/searspartsdirect/components/content/guideNavigation/dialog.xml
	public static final String GUIDE_NAV_SECTIONS_PAGE_ATTR = ident("sections");
	public static final String GUIDE_TOP_PARSYS_NAME = ident("parsys");
	public static final String GUIDE_SUBHEAD_LABEL_PROP = ident("textvalue");
	public static final String GUIDE_TEXT_LABEL_PROP = ident("text");
	
	public static final String GUIDE_CFG_RESTYPE = ident("resType");
	public static final String GUIDE_CFG_TEXT = ident("link");
	
	public static final String JCR_CONTENT_ROOT = ident("jcr:content/");
	
	public static final String STATS_PAGE_PREFIX = ident("/var/statistics/pages");
    public static final String STATS_PAGE_SUFFIX = ident("/.stats");
	
	public static final String RECENTLY_VIEWED_MODEL_COOKIE = "recentlyViewedModels";
	public static final String RECENTLY_VIEWED_PART_COOKIE = "recentlyViewedParts";
	public static final String USER_NAME_COOKIE = "username";
	public static final String MY_MODEL_COOKIE = "myProfileModels";
	public static final String SHOPPING_CART_COOKIE = "cid";

	public static final String XML_DECLARATION = ident("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	public static final String SITEMAP_OPEN_URLSET = ident("<urlset ");
	public static final String XML_XSD_NS = ident(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
	public static final String SITEMAP_XML_XSD = ident(" xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\" ");
	public static final String SITEMAP_XML_NS = ident(" xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\" ");
	public static final String XML_CLOSE_TAG = ident(">");
	public static final String SITEMAP_CLOSE_URLSET = ident("</urlset>");
	public static final String SITEMAP_OPEN_URL = ident("<url>");
	public static final String SITEMAP_CLOSE_URL = ident("</url>");
	public static final String SITEMAP_OPEN_LOC = ident("<loc>");
	public static final String SITEMAP_CLOSE_LOC = ident("</loc>");
	public static final String SITEMAP_OPEN_LM = ident("<lastmod>");
	public static final String SITEMAP_CLOSE_LM = ident("</lastmod>");
	public static final String SITEMAP_OPEN_PRIORITY = ident("<priority>");
	public static final String SITEMAP_CLOSE_PRIORITY = ident("</priority>");
	
	// Constants relating login
    public static final String LOGIN_SERVICE = ident("");
    public static final String LOGOUT_SERVICE = ident("");
    public static final String REGISTER = ident("");
	
	public static final String SUBCATEGORY_TAG = "searspartsdirect:subcategories";

	
	/**
	 * This method returns its argument - this is to keep what would otherwise be literals
	 * from being compiled into client code. If you want to be able to change a constant here
	 * without needing to recompile all clients, you should wrap its initialization in this
	 * call.
	 * 
	 * @param t
	 * @return t
	 */
	public final static <T> T ident(T t) {
		return t;
	}
}
