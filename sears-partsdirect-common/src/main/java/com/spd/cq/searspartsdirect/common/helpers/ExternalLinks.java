package com.spd.cq.searspartsdirect.common.helpers;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;

import com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings;

public class ExternalLinks {
	private final ResourceResolver resourceResolver;
	private final SlingHttpServletRequest slingRequest;
	private String addedPrefix;
	private String addedSuffix;

	public ExternalLinks(final SlingHttpServletRequest slingRequest) {
		this.slingRequest = slingRequest;
		this.resourceResolver = slingRequest.getResourceResolver();
		setAddedPrefix(readConfiguredAddedPrefix());
		setAddedSuffix(readConfiguredAddedSuffix());
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

	public String getExternalUrlForAsset(final String pagePath) {
		// Removed "removed prefixes" notion in favor of resourceResolver map method
		StringBuilder sb = new StringBuilder();
		String mapped = resourceResolver.map(slingRequest,pagePath);
		if (!mapped.startsWith(addedPrefix)) {
			sb.append(addedPrefix);
		}
		sb.append(mapped);
		return sb.toString();
	}

	private String readConfiguredAddedPrefix() {
		return EnvironmentSettings.getExternalAddedPrefix();
	}

	private String readConfiguredAddedSuffix() {
		return EnvironmentSettings.getExternalAddedSuffix();
	}


	public final String getAddedPrefix() {
		return addedPrefix;
	}


	public final void setAddedPrefix(String addedPrefix) {
		this.addedPrefix = addedPrefix;
	}


	public final String getAddedSuffix() {
		return addedSuffix;
	}


	public final void setAddedSuffix(String addedSuffix) {
		this.addedSuffix = addedSuffix;
	}
}
