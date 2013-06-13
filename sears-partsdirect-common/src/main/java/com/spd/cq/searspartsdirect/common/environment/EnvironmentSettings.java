package com.spd.cq.searspartsdirect.common.environment;


import java.util.Dictionary;
import java.util.Hashtable;
import org.apache.felix.scr.annotations.*;
import org.osgi.service.component.ComponentContext;

@Component(label="Sears PartsDirect Environment Settings",description="Environment specific variables",immediate=true,metatype=true)
@Service(value=com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings.class)
@Properties({	
	@Property(name = org.osgi.framework.Constants.SERVICE_DESCRIPTION, value = "Environment specific variables"),
    @Property(name = org.osgi.framework.Constants.SERVICE_VENDOR, value = "Siteworx")
})
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
    	return getProperty(HANDLE_404_URL,"");
    }
    
    @Property(label = "Sitemap Start Paths", 
    		description = "Paths to start generating the sitemap from",
    		value = "/content/searspartsdirect/en"
    	)
    public static final String SITEMAP_START_PATHS = "searspartsdirect.sitemap.paths.start";
    
    public static String getSitemapStartPaths() {
    	return getProperty(SITEMAP_START_PATHS,"");
    }
    
    @Property(label = "Sitemap Stop Paths", 
    		description = "Paths to stop generating the sitemap at",
    		value = "")
    public static final String SITEMAP_STOP_PATHS = "searspartsdirect.sitemap.paths.stop";
    
    public static String getSitemapStopPaths() {
    	return getProperty(SITEMAP_STOP_PATHS,"");
    }
    
    @Property(label = "External Url Added Prefix", 
    		description = "Prefix to prepend to external page URLs",
    		value = "http://www.searspartsdirect.com")
    public static final String EXTERNAL_ADDED_PREFIX = "searspartsdirect.external.added.prefix";
    
    public static String getExternalAddedPrefix() {
    	return getProperty(EXTERNAL_ADDED_PREFIX,"");
    }
    
    @Property(label = "External Url Added Suffix", 
    		description = "Suffix to append to external page URLs",
    		value = ".html")
    public static final String EXTERNAL_ADDED_SUFFIX = "searspartsdirect.external.added.suffix";
    
    public static String getExternalAddedSuffix() {
    	return getProperty(EXTERNAL_ADDED_SUFFIX,"");
    }
        
    // ----< SCR Integration >--------------------------------------------------
    protected void activate( final ComponentContext context ) throws Exception {
        // retrieve config values
        props = context.getProperties();
    }
    
    protected void deactivate( ComponentContext context ) {    	
    }
    
    private static <T> T getProperty(String prop, T defaultValue) {
        if (props.get(prop) == null) {
            return defaultValue;
        } else {
            return (T)props.get(prop);
        }
    }
}