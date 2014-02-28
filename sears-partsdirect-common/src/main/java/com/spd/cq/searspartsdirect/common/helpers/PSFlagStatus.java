package com.spd.cq.searspartsdirect.common.helpers;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.felix.scr.annotations.PropertyUnbounded;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.apache.commons.lang.StringUtils;

@Component(immediate = true, metatype = true, label = "PartsDirect Stock Availablity Message Configuration", description = "Component interfaces with partsDirect services ")
@Service(value = PSFlagStatus.class)

public class PSFlagStatus {
	Logger log = LoggerFactory.getLogger(PSFlagStatus.class);
	
	public static final String SERVICE_NAME = "com.spd.cq.searspartsdirect.common.helpers.PSFlagStatus";
	
	public static final String FLAG_MSG = "flagMessage";
	@Property(name = FLAG_MSG, label = "Flag Message", description = "format flag status|flag message", unbounded = PropertyUnbounded.ARRAY)
	
	protected ComponentContext context = null;

	protected void activate(ComponentContext context) throws Exception {
		log.info("PartsDirect SettingsHelper : activate() is called.");
		this.context = context;
	}

	protected void deactivate(ComponentContext context) {
		log.info("PartsDirect SettingsHelper : deactivate() is called.");
	}

	@SuppressWarnings("finally")
	public JSONObject getStockAvailabilityMessage() {
		JSONObject jsonObject = new JSONObject();
		Object[] flagMessageArray =  (Object[])context.getProperties().get(FLAG_MSG);
		try{
	        for(Object flagMsg : flagMessageArray)
	        {
	        	String map[] = flagMsg.toString().split("\\|");
	        	jsonObject.put(map[0], StringUtils.replaceEach(map[1], new String[]{"'"}, new String[]{"&#39;"}));
	        }
		} catch (JSONException e) {
			log.error("Error occured in PSFlagStatus:getStockAvailabilityMessage() "
					+ e.getMessage() + " Exception: ");
		} finally{
			return jsonObject;
		}
	}
}