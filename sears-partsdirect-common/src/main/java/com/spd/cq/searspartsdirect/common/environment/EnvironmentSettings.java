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
    private static final String HANDLE_404_URL = "searspartsdirect.error.404.url";
    
    public static String get404HandlerURL() {
    	return getProperty(HANDLE_404_URL,"");
    }
    
    @Property(label = "Sitemap Start Paths", 
    		description = "Paths to start generating the sitemap from",
    		value = "/content/searspartsdirect/en"
    	)
    private static final String SITEMAP_START_PATHS = "searspartsdirect.sitemap.paths.start";
    
    public static String getSitemapStartPaths() {
    	return getProperty(SITEMAP_START_PATHS,"");
    }
    
    @Property(label = "Sitemap Stop Paths", 
    		description = "Paths to stop generating the sitemap at",
    		value = "")
    private static final String SITEMAP_STOP_PATHS = "searspartsdirect.sitemap.paths.stop";
    
    public static String getSitemapStopPaths() {
    	return getProperty(SITEMAP_STOP_PATHS,"");
    }
    
    @Property(label = "Sitemap Removed Prefixes", 
    		description = "Path prefixes to remove from sitemap urls",
    		value = "/content/searspartsdirect/")
    private static final String SITEMAP_REMOVED_PREFIXES = "searspartsdirect.sitemap.removed.prefixes";
    
    public static String getSitemapRemovedPrefixes() {
    	return getProperty(SITEMAP_REMOVED_PREFIXES,"");
    }
    
    @Property(label = "Sitemap Added Prefix", 
    		description = "URL prefix to prepend to sitemap urls",
    		value = "http://www.searspartsdirect.com/")
    private static final String SITEMAP_ADDED_PREFIX = "searspartsdirect.sitemap.added.prefix";
    
    public static String getSitemapAddedPrefix() {
    	return getProperty(SITEMAP_ADDED_PREFIX,"");
    }
    
    @Property(label = "Sitemap Added Suffix", 
    		description = "URL suffix to append to sitemap urls",
    		value = ".html")
    private static final String SITEMAP_ADDED_SUFFIX = "searspartsdirect.sitemap.added.suffix";
    
    public static String getSitemapAddedSuffix() {
    	return getProperty(SITEMAP_ADDED_SUFFIX,"");
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