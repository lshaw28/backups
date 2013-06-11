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
    
    @Property(label = "404 Handler Redirect URL", description = "Default is /404.html, set to change this")
    private static final String HANDLE_404_URL = "searspartsdirect.error.404.url";
    
    public static String get404HandlerURL() {
    	return getProperty(HANDLE_404_URL,"/content/searspartsdirect/en/404.html");
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