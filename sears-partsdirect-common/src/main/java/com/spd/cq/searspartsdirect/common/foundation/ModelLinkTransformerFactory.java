package com.spd.cq.searspartsdirect.common.foundation;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.cocoon.xml.sax.AttributesImpl;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.rewriter.ProcessingComponentConfiguration;
import org.apache.sling.rewriter.ProcessingContext;
import org.apache.sling.rewriter.Transformer;
import org.apache.sling.rewriter.TransformerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.day.cq.wcm.api.PageManager;

@Component(enabled=true)
@Service
@Properties({
    @Property(name="pipeline.mode", value="global"),
    @Property(name="service.ranking", intValue=999)})

public class ModelLinkTransformerFactory implements TransformerFactory {
	
	public Transformer createTransformer() {
        return new RestrictedLinkTransformer();  //To change body of implemented methods use File | Settings | File Templates.
    }
    public class RestrictedLinkTransformer extends org.apache.cocoon.xml.sax.AbstractSAXPipe implements Transformer {

        private final Logger log = LoggerFactory.getLogger(getClass());

        private PageManager pm;
        private Matcher m;
        private boolean found;

        public void init(ProcessingContext processingContext, ProcessingComponentConfiguration processingComponentConfiguration) throws IOException {
            pm = processingContext.getRequest().getResourceResolver().adaptTo(PageManager.class);
            String requestURI = processingContext.getRequest().getRequestURI();
            Pattern p = Pattern.compile("/(.*)/(.*)/model-(.*)-repair(.*)");
            m = p.matcher(requestURI);
            found = m.find();
        }

        public void dispose() {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
            AttributesImpl attributes = new AttributesImpl(atts);
            if (found && localName.equalsIgnoreCase("a")){
                try{
                    String href = attributes.getValue("href");
                    if (href.startsWith("/") && href.indexOf(".html") >0) {
                    	StringBuilder sb = new StringBuilder();
                    	sb.append("/").append(m.group(1)).append("/").append(m.group(2));
                    	sb.append("/").append("model-" + m.group(3) + "-repair");
                    	sb.append(href);
                    	href = sb.toString();
                    	for (int i = 0; i<attributes.getLength(); i++) {
                            if (attributes.getQName(i).equalsIgnoreCase("href")) {
                                attributes.setValue(i, href);
                            }
                    	}
                    }
                } catch (Exception e){
                    log.error("Exception in RestrictedLinkTransformer: " + e.getMessage());
                }
        }

        super.startElement(uri, localName, qName, attributes);

    }

}
}
