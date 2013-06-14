package com.spd.cq.searspartsdirect.common.helpers;


import java.util.List;

import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.PartsDirectCookieHelperFixture;
import com.spd.cq.searspartsdirect.common.model.ModelCookieModel;
import com.spd.cq.searspartsdirect.common.model.PartCookieModel;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PartsDirectCookieHelperTest extends TestCase {

	private PartsDirectCookieHelperFixture fixture;
	private PartsDirectCookieHelper cookieHelper;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new PartsDirectCookieHelperFixture();
		cookieHelper = new PartsDirectCookieHelper();
	}

	@Test
	public void testGetCookieInfo() {
		Cookie cookie = cookieHelper.getCookieInfo(fixture.getTestCookies(), fixture.getTestCookieName());
		assertThat(cookie,isA(Cookie.class));
		assertThat(cookie.getName(),is(fixture.getTestCookieName()));
	}

	@Test
	public void testParseRecentltyViewedModelItems() {
		List<ModelCookieModel> parseResult = cookieHelper.parseRecentltyViewedModelItems(fixture.getModelItemsString());
		assertThat(parseResult,hasSize(3));
	}

	@Test
	public void testParseRecentltyViewedPartItems() {
		List<PartCookieModel> parseResult = cookieHelper.parseRecentltyViewedPartItems(fixture.getPartItemsString());
		assertThat(parseResult,hasSize(3));
	}

}
