package com.spd.cq.searspartsdirect.common.helpers;

public final class Constants {

	public static final String EMPTY = "";
	
	//CQ constants
	public static final String CQ_PAGE = ident("cq:Page");
	public static final String CQ_TAG = ident("cq:Tag");
	
	//Asset Constants
	public static final String ASSETS_PATH = ident("/etc/spdAssets/scaffolding");
	public static final String ASSETS_TITLE_PATH = ident("jcr:title");
	public static final String ASSETS_TITLE_REL_PATH = ident("jcr:content/jcr:title");
	public static final String ASSETS_DESCRIPTION_PATH = ident("jcr:description");
	public static final String ASSETS_LOGO_PATH = ident("/jcr:content/logo");
	public static final String ASSETS_IMAGE_PATH = ident("/jcr:content/image");
	public static final String ASSETS_IMAGE_REL_PATH = ident("jcr:content/image");
	
	//Model Search Constants
	public static final String MODEL_NOT_FOUND = ident("Not Found");
	
	// Originally for guide nav, but generally applicable
	public static final String GUIDE_NAV_COMPONENT = ident("searspartsdirect/components/content/guideNavigation");
	public static final String COMMENTS_COMPONENT = ident("searspartsdirect/components/content/comments");
	public static final String SUBHEAD_COMPONENT = ident("searspartsdirect/components/content/subhead");
	public static final String PARTS_REQ_R_COMPONENT = ident("searspartsdirect/components/content/partsRequiredRepair");
	public static final String TOOLS_REQ_R_COMPONENT = ident("searspartsdirect/components/content/toolsRequiredRepair");
	public static final String INSTRUCTIONS_COMPONENT = ident("searspartsdirect/components/content/repairInstructions");
	
	public static final String USERGEN_ROOT = ident("/content/usergenerated");
	
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
	
	public static final String GUIDE_CFG_RESTYPE = ident("resType");
	public static final String GUIDE_CFG_TEXT = ident("link");
	
	public static final String JCR_CONTENT_ROOT = ident("jcr:content/");
	
	public static final String RECENTLY_VIEWED_MODEL_COOKIE = "recentlyViewedModels";
	public static final String RECENTLY_VIEWED_PART_COOKIE = "recentlyViewedParts";
	
	public static final String SSO_CAS_LOGIN_URL = "https://sso.shld.net/shccas/shcLogin";
	public static final String GATEWAY_FIELD = "gateway";
	public static final String SERVICE_FIELD = "service";
	public static final String SSO_SERVICE_FIELD = "https://localhost:4502/partsdirect/j_acegi_cas_security_check";

	 public static final String CAS_VISITED_PARAM = "casVisited";
	 public static final String CAS_AUTOLOGIN_STARTED_PARAM = "casAutologinStarted";
	 public static final String CAS_NOFAIL_PARAMETER = "ssonofail";
	 public static final String AUTHENTICATION_SUCCESS_URL = "AUTHENTICATION_SUCCESS_URL";
	 public static final String AUTHENTICATION_FAILURE_URL = "AUTHENTICATION_FAILURE_URL";
	 public static final String CAS_ERROR_CODE_FIELD = "casErrorCode";
	 public static final String ERROR_CODE_FIELD = "errorCode";
	 public static final String LOGIN_ID_FIELD = "loginId";
	 public static final String LOGON_PASSWORD_FIELD = "logonPassword";
	 public static final String ERROR_COUNT_FIELD = "errorCount";
	 
	 public static final String FIRST_TIME_LOGON_RESET_PASSWORD_STATE_CODE = "500.112";


	    
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
