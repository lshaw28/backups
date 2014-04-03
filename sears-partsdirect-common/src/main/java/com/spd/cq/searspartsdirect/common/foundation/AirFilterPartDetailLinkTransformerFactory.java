package com.spd.cq.searspartsdirect.common.foundation;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.cocoon.xml.sax.AttributesImpl;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.rewriter.ProcessingComponentConfiguration;
import org.apache.sling.rewriter.ProcessingContext;
import org.apache.sling.rewriter.Transformer;
import org.apache.sling.rewriter.TransformerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

@Component(enabled=true)
@Service
@Properties({
	@Property(name="pipeline.mode", value="global"),
	@Property(name="service.ranking", intValue=999)})

public class AirFilterPartDetailLinkTransformerFactory implements TransformerFactory {

	public Transformer createTransformer() {
		return new RestrictedLinkTransformer();
	}
	public static class RestrictedLinkTransformer extends org.apache.cocoon.xml.sax.AbstractSAXPipe implements Transformer {

		private final Logger log = LoggerFactory.getLogger(getClass());
		private final static Pattern airFilterPartDetailsPattern = Pattern
				.compile(Constants.AIRFILTER_PART_DETAILS_PFX + "/([^/]*)/([^/]*)/([^/]*)/" + "(.*)");
		private Matcher airFilterPartDetailsMatcher;
		private ResourceResolver resourceResolver;

		private boolean found;

		public void init(ProcessingContext processingContext, ProcessingComponentConfiguration processingComponentConfiguration) throws IOException {
			String requestURI = processingContext.getRequest().getRequestURI();
			resourceResolver = processingContext.getRequest().getResourceResolver();

			airFilterPartDetailsMatcher = airFilterPartDetailsPattern.matcher(requestURI);
			found = airFilterPartDetailsMatcher.find();
		}

		public void dispose() {
			// currently NOOP
		}

		public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
			AttributesImpl attributes = new AttributesImpl(atts);
			if (localName.equalsIgnoreCase("a")){
				try{
					String href = attributes.getValue("href");
					if (href != null && (href.length() > 0 && href.charAt(0) == '/') && href.indexOf(".html") >0) {

						href = resourceResolver.map(href);
						Matcher linkMatch = airFilterPartDetailsPattern.matcher(href);
						if (found && !linkMatch.find()) {
							StringBuilder sb = new StringBuilder();
							sb.append('/').append(airFilterPartDetailsMatcher.group(1));
							sb.append('/').append(airFilterPartDetailsMatcher.group(2));
							sb.append('/').append(airFilterPartDetailsMatcher.group(3));
							sb.append(href);
							href = sb.toString();
						}
						for (int i = 0; i<attributes.getLength(); i++) {
							if (attributes.getQName(i).equalsIgnoreCase("href")) {
								attributes.setValue(i, href);
							}
						}
					}
				} catch (Exception e){
					log.error("Exception in RestrictedLinkTransformer, ", e);
				}
			}
			super.startElement(uri, localName, qName, attributes);
		}
	}
}
