package com.spd.cq.searspartsdirect.common.helpers;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, metatype = true, label = "PartsDirect API ROOT Configuration", description = "Component interfaces with partsDirect services ")
@Service(value = PSSettingsHelper.class)

public class PSSettingsHelper {
	Logger log = LoggerFactory.getLogger(PSSettingsHelper.class);
	
	public static final String SERVICE_NAME = "com.spd.cq.searspartsdirect.common.helpers.PSsettingsHelper";
	
	public static final String API_URL = "apiUrl";
	@Property(name = API_URL, label = "Product API URL", description = "PartsDirect Product API URL")
	
	protected ComponentContext context = null;

	protected void activate(ComponentContext context) throws Exception {
		log.info("PartsDirect SettingsHelper : activate() is called.");
		this.context = context;
	}

	protected void deactivate(ComponentContext context) {
		log.info("PartsDirect SettingsHelper : deactivate() is called.");
	}

	public String getPartsDirectProductAPI() {
		Object apiKey = (Object) context.getProperties().get("apiUrl");
		if (apiKey == null) {
			return null;
		}
		return apiKey.toString();
	}
}