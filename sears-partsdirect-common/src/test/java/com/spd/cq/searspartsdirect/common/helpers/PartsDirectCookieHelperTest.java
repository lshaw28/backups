package com.spd.cq.searspartsdirect.common.helpers;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.PartsDirectCookieHelperFixture;
import com.spd.cq.searspartsdirect.common.model.ModelCookieModel;
import com.spd.cq.searspartsdirect.common.model.PartCookieModel;

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
	public void testClarifyStringTokenizerVsSplitBehavior() {
		String fiveDelimsOnly = "|||||";
		String[] splitResult = fiveDelimsOnly.split("|");
		assertThat(splitResult.length,is(6));
		StringTokenizer splitTokenizer = new StringTokenizer(fiveDelimsOnly,"|");
		assertThat(splitTokenizer.countTokens(),is(0));
		// StringTokenizer collapses adjacent delimiters. 
		// This could cause URL to alias as Description when Description is blank
		// However, spoke with author and he is sure this will not happen.
		// nextToken also doesn't ever return null, per doc, it throws instead,
		// so the null checks on nextToken results remain unreachable. --bz
	}

	@Test
	public void testGetCookieInfo() {
		Cookie cookie = PartsDirectCookieHelper.getCookieInfo(fixture.getTestCookies(), fixture.getTestCookieName());
		assertThat(cookie,isA(Cookie.class));
		assertThat(cookie.getName(),is(fixture.getTestCookieName()));
	}

	@Test
	public void testParseRecentltyViewedModelItems() {
		List<ModelCookieModel> parseResult = PartsDirectCookieHelper.parseRecentltyViewedModelItems(fixture.getModelItemsString());
		assertThat(parseResult,hasSize(3));
	}
	
	@Test
	public void testParseEmptyRecentltyViewedModelItems() {
		List<ModelCookieModel> parseResult = PartsDirectCookieHelper.parseRecentltyViewedModelItems(Constants.EMPTY);
		assertThat(parseResult,hasSize(0));
	}
	
	@Test
	public void testParseTooShortRecentltyViewedModelItems() {
		List<ModelCookieModel> parseResult = PartsDirectCookieHelper.parseRecentltyViewedModelItems(fixture.getShortModelItemsString());
		assertThat(parseResult,hasSize(0));
	}
	
	@Test
	public void testParseLastPartialRecentltyViewedModelItems() {
		List<ModelCookieModel> parseResult = PartsDirectCookieHelper.parseRecentltyViewedModelItems(fixture.getThirteen());
		assertThat(parseResult,hasSize(3));
	}

	@Test
	public void testParseRecentltyViewedPartItems() {
		List<PartCookieModel> parseResult = PartsDirectCookieHelper.parseRecentltyViewedPartItems(fixture.getPartItemsString());
		assertThat(parseResult,hasSize(3));
	}
	
	@Test
	public void testParseEmptyRecentltyViewedPartItems() {
		List<PartCookieModel> parseResult = PartsDirectCookieHelper.parseRecentltyViewedPartItems(Constants.EMPTY);
		assertThat(parseResult,hasSize(0));
	}
	
	@Test
	public void testParseTooShortRecentltyViewedPartItems() {
		List<PartCookieModel> parseResult = PartsDirectCookieHelper.parseRecentltyViewedPartItems(fixture.getShortPartItemsString());
		assertThat(parseResult,hasSize(0));
	}
	
	@Test
	public void testParseLastPartialRecentltyViewedPartItems() {
		List<PartCookieModel> parseResult = PartsDirectCookieHelper.parseRecentltyViewedPartItems(fixture.getThirteen());
		assertThat(parseResult,hasSize(3));
	}

}
