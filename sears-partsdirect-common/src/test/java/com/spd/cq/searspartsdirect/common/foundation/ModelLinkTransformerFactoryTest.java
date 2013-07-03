package com.spd.cq.searspartsdirect.common.foundation;


import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import junit.framework.TestCase;

import org.apache.sling.rewriter.Transformer;
import org.apache.sling.rewriter.TransformerFactory;

import com.spd.cq.searspartsdirect.common.fixture.ModelLinkTransformerFactoryFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ModelLinkTransformerFactoryTest extends TestCase {

	private TransformerFactory factory;
	private ModelLinkTransformerFactoryFixture fixture;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new ModelLinkTransformerFactoryFixture();
		factory = new ModelLinkTransformerFactory();
		
	}

	@Test
	public void testCreateTransformer() {
		Transformer transformer = factory.createTransformer();
		assertThat(transformer,is(not(nullValue())));
	}

	@Test
	public void testTransformTransformable() throws IOException, SAXException {
		Transformer transformer = factory.createTransformer();
		// "/(.*)/(.*)/model-(.*)-repair(.*)"
		fixture.setRequestUri("/one/two/model-three-repairfour");
		transformer.init(fixture.getProcessingContext(), fixture.getProcessingComponentConfiguration());
		fixture.setHrefValue("/foo.html");
		transformer.startElement(fixture.getElUri(), fixture.getElLocalName(), fixture.getElQName(), fixture.getElAttributes());
		transformer.startElement(fixture.getElUri(), "foo", "foo", fixture.getElAttributes());
		transformer.dispose();
	}
	
	@Test
	public void testTransformTransformableNullHref() throws IOException, SAXException {
		Transformer transformer = factory.createTransformer();
		fixture.setRequestUri("/one/two/model-three-repairfour");
		transformer.init(fixture.getProcessingContext(), fixture.getProcessingComponentConfiguration());
		transformer.startElement(fixture.getElUri(), fixture.getElLocalName(), fixture.getElQName(), fixture.getElAttributes());
		transformer.dispose();
	}
	
	@Test
	public void testTransformTransformableRelativeHtmlHref() throws IOException, SAXException {
		Transformer transformer = factory.createTransformer();
		fixture.setRequestUri("/one/two/model-three-repairfour");
		fixture.setHrefValue("foo.html");
		transformer.init(fixture.getProcessingContext(), fixture.getProcessingComponentConfiguration());
		transformer.startElement(fixture.getElUri(), fixture.getElLocalName(), fixture.getElQName(), fixture.getElAttributes());
		transformer.dispose();
	}
	
	@Test
	public void testTransformTransformableNonHtmlHref() throws IOException, SAXException {
		Transformer transformer = factory.createTransformer();
		fixture.setRequestUri("/one/two/model-three-repairfour");
		fixture.setHrefValue("/foo.foo");
		transformer.init(fixture.getProcessingContext(), fixture.getProcessingComponentConfiguration());
		transformer.startElement(fixture.getElUri(), fixture.getElLocalName(), fixture.getElQName(), fixture.getElAttributes());
		transformer.dispose();
	}
	
	@Test
	public void testTransformNontransformable() throws IOException, SAXException {
		Transformer transformer = factory.createTransformer();
		// "/(.*)/(.*)/model-(.*)-repair(.*)"
		fixture.setRequestUri("/one/two/mooodel-three-repairfour");
		transformer.init(fixture.getProcessingContext(), fixture.getProcessingComponentConfiguration());
		transformer.startElement(fixture.getElUri(), fixture.getElLocalName(), fixture.getElQName(), fixture.getElAttributes());
		transformer.dispose();
	}

}
