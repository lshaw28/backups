package com.spd.cq.searspartsdirect.common.helpers;

/*
 * Wrapper class for Mockito
 */
import org.apache.sling.api.resource.Resource;

import com.day.cq.wcm.foundation.Image;

public class ImageBuilder {
	
	public Image build(Resource resource) {
		return new Image(resource);
	}
	
	public Image build(Resource resource, String path) {
		return new Image(resource, path);
	}
}
