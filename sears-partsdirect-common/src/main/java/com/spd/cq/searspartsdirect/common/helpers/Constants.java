package com.spd.cq.searspartsdirect.common.helpers;

public final class Constants {

	public static final String EMPTY = "";
	
	public static final String ASSETS_PATH = ident("/etc/spdAssets/scaffolding");
	public static final String ASSETS_TITLE_PATH = ident("jcr:title");
	public static final String ASSETS_DESCRIPTION_PATH = ident("jcr:description");
	public static final String ASSETS_LOGO_PATH = ident("/jcr:content/logo");
	public static final String ASSETS_IMAGE_PATH = ident("/jcr:content/logo");
	
	public static final String COMMENTS_COMPONENT = ident("searspartsdirect/components/content/comments");
	public static final String SUBHEAD_COMPONENT = ident("searspartsdirect/components/content/subhead");
	public static final String PARTS_REQ_R_COMPONENT = ident("searspartsdirect/components/content/partsRequiredRepair");
	public static final String TOOLS_REQ_R_COMPONENT = ident("searspartsdirect/components/content/toolsRequiredRepair");
	
	public static final String PARTS_REQ_DEF_STICKY_LINK = ident("Parts required");
	public static final String TOOLS_REQ_DEF_STICKY_LINK = ident("Tools required");
	public static final String COMMENTS_STICKY_LINK_PREFIX = ident("Comments");
	
	public static final String RECENTLY_VIEWED_MODEL_COOKIE = "recentlyViewedModels";
	public static final String RECENTLY_VIEWED_PART_COOKIE = "recentlyViewedParts";

	private final static <T> T ident(T t) {
		return t;
	}
}
