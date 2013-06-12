package com.spd.cq.searspartsdirect.common.helpers;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;

public class ExternalLinks {
	
	private final ResourceResolver resourceResolver;
	private final SlingHttpServletRequest slingRequest;
	private final String addedPrefix;
	private final String addedSuffix;
	
	public ExternalLinks(final SlingHttpServletRequest slingRequest) {
		this.slingRequest = slingRequest;
		this.resourceResolver = slingRequest.getResourceResolver();
		addedPrefix = readConfiguredAddedPrefix();
		addedSuffix = readConfiguredAddedSuffix();
	}
	
	
	public String getExternalUrlForPage(final String pagePath) {
		// Removed "removed prefixes" notion in favor of resourceResolver map method
		StringBuilder sb = new StringBuilder();
		String mapped = resourceResolver.map(slingRequest,pagePath);
		if (!mapped.startsWith(addedPrefix)) {
			sb.append(addedPrefix);
		}
		sb.append(mapped);
		if (!mapped.endsWith(addedSuffix)) {
			sb.append(addedSuffix);
		}
		return sb.toString();
	}
	
	String readConfiguredAddedPrefix() {
		return EnvironmentSettings.getExternalAddedPrefix();
	}
	
	String readConfiguredAddedSuffix() {
		return EnvironmentSettings.getExternalAddedSuffix();
	}
}
