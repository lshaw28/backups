package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.*;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.rewriter.ProcessingComponentConfiguration;
import org.apache.sling.rewriter.ProcessingContext;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.xml.sax.Attributes;

import com.day.cq.wcm.api.PageManager;

public class ModelLinkTransformerFactoryFixture {

	private ProcessingContext processingContext;
	private ProcessingComponentConfiguration processingComponentConfiguration;

	private String requestUri;
	private Attributes attributes;
	private String hrefValue;
	
	public ModelLinkTransformerFactoryFixture() {
		//ProcessingContext processingContext, ProcessingComponentConfiguration processingComponentConfiguration
		processingContext = mock(ProcessingContext.class);
		processingComponentConfiguration = mock(ProcessingComponentConfiguration.class);
		//PageManager pm = processingContext.getRequest().getResourceResolver().adaptTo(PageManager.class);
		SlingHttpServletRequest slingRequest = mock(SlingHttpServletRequest.class);
		when(processingContext.getRequest()).thenReturn(slingRequest);
		ResourceResolver resourceResolver = mock(ResourceResolver.class);
		when(slingRequest.getResourceResolver()).thenReturn(resourceResolver);
		PageManager pageManager = mock(PageManager.class);
		when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageManager);
        //String requestURI = processingContext.getRequest().getRequestURI();
		when(slingRequest.getRequestURI()).thenAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				return requestUri;
			}
		});
		attributes = mock(Attributes.class);
		when(attributes.getLength()).thenReturn(2);
		when(attributes.getLocalName(0)).thenReturn("href");
		when(attributes.getQName(0)).thenReturn("href");
		when(attributes.getType(0)).thenReturn("CDATA");
		when(attributes.getValue(0)).thenAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				return hrefValue;
			}
		});
		when(attributes.getLocalName(1)).thenReturn("target");
		when(attributes.getQName(1)).thenReturn("target");
		when(attributes.getType(1)).thenReturn("CDATA");
		when(attributes.getValue(1)).thenReturn("_blank");
	}

	public ProcessingContext getProcessingContext() {
		return processingContext;
	}

	public ProcessingComponentConfiguration getProcessingComponentConfiguration() {
		return processingComponentConfiguration;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	
	public void setHrefValue(String hrefValue) {
		this.hrefValue = hrefValue;
	}

	public String getElUri() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getElLocalName() {
		// TODO Auto-generated method stub
		return "a";
	}

	public String getElQName() {
		// TODO Auto-generated method stub
		return "a";
	}

	public Attributes getElAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

}
