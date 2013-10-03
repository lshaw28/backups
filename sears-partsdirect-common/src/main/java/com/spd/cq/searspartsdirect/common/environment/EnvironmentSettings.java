package com.spd.cq.searspartsdirect.common.environment;


import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

// We must make use of the raw type for 'props' to meet OSGi's requirements
@SuppressWarnings("rawtypes")
@Component(label="Sears PartsDirect Environment Settings",description="Environment specific variables",immediate=true,metatype=true)
@Service(value=com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings.class)

public class EnvironmentSettings {

	private static Dictionary<?, ?> props;

	static {
		props = new Hashtable();
	}

	@Property(label = "404 Handler Redirect URL",
			description = "Page that will be redirected to when a page is not found",
			value = "/content/searspartsdirect/en/404.html"
		)
	public static final String HANDLE_404_URL = "searspartsdirect.error.404.url";

	public static String get404HandlerURL() {
		return getProperty(HANDLE_404_URL, Constants.EMPTY);
	}

	@Property(label = "Sitemap Start Paths",
			description = "Paths to start generating the sitemap from",
			value = "/content/searspartsdirect/en"
		)
	public static final String SITEMAP_START_PATHS = "searspartsdirect.sitemap.paths.start";

	public static String getSitemapStartPaths() {
		return getProperty(SITEMAP_START_PATHS,Constants.EMPTY);
	}

	@Property(label = "Sitemap Stop Paths",
			description = "Paths to stop generating the sitemap at",
			value = "")
	public static final String SITEMAP_STOP_PATHS = "searspartsdirect.sitemap.paths.stop";

	public static String getSitemapStopPaths() {
		return getProperty(SITEMAP_STOP_PATHS,Constants.EMPTY);
	}

	@Property(label = "External Url Added Prefix",
			description = "Prefix to prepend to external page URLs",
			value = "http://www.searspartsdirect.com")
	public static final String EXTERNAL_ADDED_PREFIX = "searspartsdirect.external.added.prefix";

	public static String getExternalAddedPrefix() {
		return getProperty(EXTERNAL_ADDED_PREFIX,Constants.EMPTY);
	}

	@Property(label = "External Url Added Suffix",
			description = "Suffix to append to external page URLs",
			value = ".html")
	public static final String EXTERNAL_ADDED_SUFFIX = "searspartsdirect.external.added.suffix";

	public static String getExternalAddedSuffix() {
		return getProperty(EXTERNAL_ADDED_SUFFIX,Constants.EMPTY);
	}

	@Property(label = "PartsDirect User Data API",
			description = "API will get PD user shopping cart, profile models and logged in state",
			value = "http://partsapivip.qa.ch3.s.com/pd-services/v1/userservice/retrive?username=")
	public static final String PD_USERDATA_API = "searspartsdirect.userdata.api";

	public static String getPDUserDataApiUrl() {
		return getProperty(PD_USERDATA_API,Constants.EMPTY);
	}

	@Property(label = "PartsDirect URL",
			description = "Configurable PartsDirect Url for QA, Stress and production env",
			value = "partsbetavip.qa.ch3.s.com")
	public static final String PD_URL = "searspartsdirect.url";
	public static String getPDUrl() {
		return getProperty(PD_URL,Constants.EMPTY);
	}

	@Property(label = "PartsDirect Top Parts API",
			description = "API will get top parts for a model, category, and brand",
			value = "http://partsapivip.qa.ch3.s.com/pd-services/v1/modelTop/topParts")
	public static final String PD_TOPPARTS_API = "searspartsdirect.topparts.api";

	public static String getPDTopPartsApiUrl() {
		return getProperty(PD_TOPPARTS_API,Constants.EMPTY);
	}

	@Property(label = "PartsDirect Model Subcomponents API",
			description = "API will get API description, symptom frequencies, and header tabs for a model",
			value = "http://partsapivip.qa.ch3.s.com/pd-services/v1/commonSymptoms/modelInfo")
	public static final String PD_MODELSUB_API = "searspartsdirect.modelsub.api";

	public static String getPDModelSubApiUrl() {
		return getProperty(PD_MODELSUB_API,Constants.EMPTY);
	}

	@Property(label = "PartsDirect JobCode Parts API",
			description = "API will get the parts associated for each jobCode",
			value = "http://partsapivip.qa.ch3.s.com/pd-services/v1/commonSymptoms/jobcodelist/partlist")
	public static final String PD_JOBCODE_PARTS_API = "searspartsdirect.jobcode.parts.api";

	public static String getPDJobCodePartsApiUrl() {
		return getProperty(PD_JOBCODE_PARTS_API, Constants.EMPTY);
	}

	@Property(label = "PartsDirect API Root",
			description = "PartsDirect API Root",
			value = "http://partsapivip.qa.ch3.s.com/pd-services/v1/")
	public static final String PD_API_ROOT = "searspartsdirect.api.root";
	public static String getPDApiRoot() {
		return getProperty(PD_API_ROOT, Constants.EMPTY);
	}

	@Property(label = "PartsDirect API Root Secure",
			description = "PartsDirect API Root Secure",
			value = "http://partsapivip.qa.ch3.s.com/pd-services/v1/")
	public static final String PD_API_ROOT_SECURE = "searspartsdirect.api.root.secure";
	public static String getPDApiRootSecure() {
		return getProperty(PD_API_ROOT_SECURE, Constants.EMPTY);
	}

	@Property(label = "HTTP local host name and port",
			description = "HTTP local host name and port",
			value = "localhost:4502")
	public static final String LOCAL_HTTP_N_PORT = "searspartsdirect.local.http";
	public static String getLocalHttpAndPort() {
		return getProperty(LOCAL_HTTP_N_PORT, Constants.EMPTY);
	}

	@Property(label = "HTTPS local host name and port",
			description = "HTTPS local host name and port",
			value = "localhost:5433")
	public static final String LOCAL_HTTPS_N_PORT = "searspartsdirect.local.https";
	public static String getLocalHttpsAndPort() {
		return getProperty(LOCAL_HTTPS_N_PORT, Constants.EMPTY);
	}

	@Property(label = "Commercial site Url",
			description = "Commercial site url",
			value = "partscommercialvip.qa.ch3.s.com")
	public static final String PD_COMMERCIAL_URL = "searspartsdirect.commercial.url";
	public static String getCommercialSiteUrl() {
		return getProperty(PD_COMMERCIAL_URL, Constants.EMPTY);
	}

	@Property(label = "SSO Server URL",
			description = "SSO server url",
			value = "https://web301p.qa.ch3.s.com:5743/shccas/shcLogin"
		)
	public static final String SSO_SERVER_URL = "searspartsdirect.sso.server";

	public static String getSSOServerURL() {
		return getProperty(SSO_SERVER_URL, Constants.EMPTY);
	}

	@Property(label = "PD Security Path",
			description = "PD Security Path",
			value = "/partsdirect/j_spring_cas_security_check"
		)
	public static final String PD_SECURITY_PATH = "searspartsdirect.pd.security.path";

	public static String getPDSecurityPath() {
		return getProperty(PD_SECURITY_PATH, Constants.EMPTY);
	}
	
	@Property(label = "PD Live Person flag",
			description = "PD Live PErson flag",
			value = "y"
		)
	public static final String PD_LIVEPERSON_FLAG = "searspartsdirect.liveperson.flag";

	public static String getPDLivePersonFlag() {
		return getProperty(PD_LIVEPERSON_FLAG, Constants.EMPTY);
	}
	
	
	// ---- for testing ----
	public void externalActivateForTesting(final ComponentContext context) throws Exception {
		activate(context);
	}

	// ----< SCR Integration >--------------------------------------------------
	protected void activate( final ComponentContext context ) throws Exception {
		// retrieve config values
		props = context.getProperties();
	}

	protected void deactivate( ComponentContext context ) {
	}

	// A note - we do depend here on the properties being the correct types, which contract
	// we maintain elsewhere. In all cases so far, these are Strings.
	@SuppressWarnings("unchecked")
	private static <T> T getProperty(String prop, T defaultValue) {
		if (props.get(prop) == null) {
			return defaultValue;
		} else {
			return (T)props.get(prop);
		}
	}
}