package com.spd.cq.searspartsdirect.common.foundation;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import com.spd.cq.searspartsdirect.common.fixture.ModelSubPageFilterFixture;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

import junit.framework.TestCase;

import static org.mockito.Mockito.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ModelSubPageFilterTest extends TestCase {

	private ModelSubPageFilterFixture fixture;
	private ModelSubPageFilter filter;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new ModelSubPageFilterFixture();
		filter = new ModelSubPageFilter();
	}

	@Test
	public void testFilterWrongPath() throws ServletException, IOException {
		fixture.setUpPath("/res/path");
		runFilter();
		shouldDoFilter();
	}
	
	@Test
	public void test3oClockAuthorMisbehavior() throws ServletException, IOException {
		fixture.setUpPath(Constants.CATEGORIES_ROOT+"/chainsaw"+Constants.MODELNO_SFX+"/repair-articles.html");
		fixture.setUpDispatcher(Constants.CATEGORIES_ROOT+"/chainsaw"+Constants.MODELNO_SFX+"/repair-articles.html");
		runFilter();
		shouldForward();
	}
	
	@Test
	public void testNonHtmlPath() throws ServletException, IOException {
		fixture.setUpPath(Constants.CATEGORIES_ROOT+"/chainsaw"+Constants.MODELNO_SFX+"/repair-articles");
		runFilter();
		shouldDoFilter();
	}

	// Start tests from taxonomy worksheet
	
	@Test
	public void testFilterRepairHelp() throws IOException, ServletException {
		fixture.setUpPath("/repair-help.html");
		// fixture.setUpDispatcher("/repair-help.html");
		runFilter();
		shouldDoFilter();
	}

	@Test
	public void testCategoryRepairHelp() throws ServletException, IOException {
		fixture.setUpPath("/category"+Constants.MODELNO_SFX+".html");
		fixture.setUpDispatcher(Constants.CATEGORIES_PFX+"/category"+Constants.MODELNO_SFX+".html");
		runFilter();
		shouldForward();
	}
	
	// Dup with more specific category..
	@Test
	public void testLawnmowerRepairHelp() throws ServletException, IOException {
		fixture.setUpPath("/lawnmower"+Constants.MODELNO_SFX+".html");
		fixture.setUpDispatcher(Constants.CATEGORIES_PFX+"/lawnmower"+Constants.MODELNO_SFX+".html");
		runFilter();
		shouldForward();
	}

	@Test
	public void testCategoryArticles() throws IOException, ServletException {
		fixture.setUpPath("/dishwasher"+Constants.MODELNO_SFX+"/repair-articles.html");
		fixture.setUpDispatcher(Constants.CATEGORIES_PFX+"/dishwasher"+Constants.MODELNO_SFX+"/repair-articles.html");
		runFilter();
		shouldForward();
	}

	@Test
	public void testArticlePage() throws IOException, ServletException {
		fixture.setUpPath("/article/article.html");
		runFilter();
		shouldDoFilter();
	}
	
	@Test
	public void testAuthorModeArticlePage() throws IOException, ServletException {
		fixture.setUpPath(Constants.ARTICLES_ROOT+"/article.html");
		runFilter();
		shouldDoFilter();
	}
	
	@Test
	public void testCategorySymptomHelp() throws IOException, ServletException {
		fixture.setUpPath("/category"+Constants.MODELNO_SFX+"/symptom/symptom-id.html");
		fixture.setUpDispatcher(Constants.CATEGORIES_PFX+"/category"+Constants.MODELNO_SFX+"/symptom.symptom-id.html");
		runFilter();
		shouldForward();
	}	
	
	@Test
	public void testSpecificRepairGuide() throws IOException, ServletException {
		fixture.setUpPath(Constants.REPAIR_GUIDES_ROOT+"a-specific-guide.html");
		runFilter();
		shouldDoFilter();
	}	
	
	// Category Guide List
	// www.searspartsdirect.com/<category>-repair/repair-guides =, but see above
	@Test
	public void testCategoryGuideList() throws IOException, ServletException {
		fixture.setUpPath("/range"+Constants.MODELNO_SFX+"/repair-guides.html");
		fixture.setUpDispatcher(Constants.CATEGORIES_PFX+"/range"+Constants.MODELNO_SFX+"/repair-guides.html");
		runFilter();
		shouldForward();
	}

	@Test
	public void testCategoryErrorCodes() throws IOException, ServletException {
		fixture.setUpPath("/dishwasher"+Constants.MODELNO_SFX+"/error-codes.html");
		fixture.setUpDispatcher(Constants.CATEGORIES_PFX+"/dishwasher"+Constants.MODELNO_SFX+"/error-codes.html");
		runFilter();
		shouldForward();
	}
	
	@Test
	public void testAuthorModeCategoryErrorCodes() throws IOException, ServletException {
		fixture.setUpPath(Constants.SPD_ROOT+"fr/dishwasher"+Constants.MODELNO_SFX+"/error-codes.html");
		fixture.setUpDispatcher(Constants.SPD_ROOT+"fr"+Constants.CATEGORIES_PFX+"/dishwasher"+Constants.MODELNO_SFX+"/error-codes.html");
		runFilter();
		shouldForward();
	}

	@Test
	public void testCategoryBrandErrorCodes() throws ServletException,
			IOException {
		fixture.setUpPath("/dishwasher"+Constants.MODELNO_SFX+"/error-codes/kenmore-error-codes.html");
		fixture.setUpDispatcher(Constants.CATEGORIES_PFX+"/dishwasher"+Constants.MODELNO_SFX+"/error-codes/kenmore-error-codes.html");
		runFilter();
		shouldForward();
	}
	
	@Test
	public void testAuthorPage() throws IOException, ServletException {
		fixture.setUpPath("/author/ben-zethmayr.html");
		runFilter();
		shouldDoFilter();
	}

	@Test
	public void testModelHelpRepairRoot() throws IOException, ServletException {
		fixture.setUpPath("/brand/category/"+Constants.MODELNO_PFX+"model"+Constants.MODELNO_SFX+".html");
		fixture.setUpDispatcher(Constants.MODEL_REPAIR_PAGE_NO_EXT+".brand.category.model.html");
		runFilter();
		shouldForward();
	}
	
	// Dup with more specific data
	@Test
	public void testKenmoreElite3523HelpRepairRoot() throws IOException, ServletException {
		fixture.setUpPath("/kenmore/diswasher/"+Constants.MODELNO_PFX+"Elite3523"+Constants.MODELNO_SFX+".html");
		fixture.setUpDispatcher(Constants.MODEL_REPAIR_PAGE_NO_EXT+".kenmore.diswasher.Elite3523.html");
		runFilter();
		shouldForward();
	}

	@Test
	public void testModelHelpRepairGuides() throws IOException,
			ServletException {
		fixture.setUpPath("/brand/category/"+Constants.MODELNO_PFX+"model"+Constants.MODELNO_SFX+"/repair-guides.html");
		fixture.setUpDispatcher(Constants.CATEGORIES_PFX+"/category"+Constants.MODELNO_SFX+"/repair-guides.brand.category.model.html");
		runFilter();
		shouldForward();
	}
	
	// Dup with more specific data
	@Test
	public void testHusqvarnaChainsawHelpRepairGuides() throws IOException,
			ServletException {
		fixture.setUpPath("/husqvarna/chainsaw/"+Constants.MODELNO_PFX+"999"+Constants.MODELNO_SFX+"/repair-guides.html");
		fixture.setUpDispatcher(Constants.CATEGORIES_PFX+"/chainsaw"+Constants.MODELNO_SFX+"/repair-guides.husqvarna.chainsaw.999.html");
		runFilter();
		shouldForward();
	}

	@Test
	public void testModelHelpErrorCodes() throws IOException, ServletException {
		fixture.setUpPath("/brand/category/"+Constants.MODELNO_PFX+"model"+Constants.MODELNO_SFX+"/error-codes.html");
		fixture.setUpDispatcher(Constants.CATEGORIES_PFX+"/category"+Constants.MODELNO_SFX+"/error-codes.brand.category.model.html");
		runFilter();
		shouldForward();
	}

	@Test
	public void testModelParticularSymptom() throws IOException,
			ServletException {
		fixture.setUpPath("/brand/category/"+Constants.MODELNO_PFX+"model"+Constants.MODELNO_SFX+"/symptom/symptom-id.html");
		fixture.setUpDispatcher(Constants.CATEGORIES_PFX+"/category"+Constants.MODELNO_SFX+"/symptom.brand.category.model.symptom-id.html");
		runFilter();
		shouldForward();
	}
	
	@Test
	public void testAuthorModeModelParticularSymptom() throws IOException,
			ServletException {
		fixture.setUpPath(Constants.EN_ROOT+"/brand/category/"+Constants.MODELNO_PFX+"model"+Constants.MODELNO_SFX+"/symptom/symptom-id.html");
		fixture.setUpDispatcher(Constants.CATEGORIES_ROOT+"/category"+Constants.MODELNO_SFX+"/symptom.brand.category.model.symptom-id.html");
		runFilter();
		shouldForward();
	}
	
	@Test
	public void testPageNooneEverSees() throws IOException, ServletException {
		fixture.setUpPath(Constants.EN_ROOT+Constants.MARKUP_EXT);
		runFilter();
		shouldDoFilter();
	}

	@Test
	public void testModelParticularRepairGuide() throws IOException,
			ServletException {
		fixture.setUpPath("/brand/category/"+Constants.MODELNO_PFX+"model"+Constants.MODELNO_SFX+Constants.REPAIR_GUIDES_ROOT+"repair-guide-title.html");
		fixture.setUpDispatcher(Constants.REPAIR_GUIDES_ROOT+"repair-guide-title.brand.category.model.html");
		runFilter();
		shouldForward();
	}
	
	// Dup with more specific data
	@Test
	public void testAcmePortableHoleParticularRepairGuide() throws IOException,
			ServletException {
		fixture.setUpPath("/acme/portable-hole/"+Constants.MODELNO_PFX+"0"+Constants.MODELNO_SFX+Constants.REPAIR_GUIDES_ROOT+"donotfold.html");
		fixture.setUpDispatcher(Constants.REPAIR_GUIDES_ROOT+"donotfold.acme.portable-hole.0.html");
		runFilter();
		shouldForward();
	}

	// End tests from taxonomy worksheet

	@Test
	public void testAddSelectors() {
		String identity = "/random.html";
		List<String> selectors = new LinkedList<String>();
		String extension = ".html";
		assertThat(filter.addSelectors(identity, extension, selectors),
				is(identity));
		selectors.add("foo");
		assertThat(filter.addSelectors(identity, extension, selectors),
				is("/random.foo.html"));
		selectors.add("foo");
		assertThat(filter.addSelectors(identity, extension, selectors),
				is("/random.foo.foo.html"));
	}

	@Test
	public void testHasRepairGuide() {
		assertThat(filter.hasRepairGuide("/repair-guide/foo.html"), is(true));
		assertThat(filter.hasRepairGuide("/foo.html"), is(false));
		assertThat(filter.hasRepairGuide("/repair-guides.html"), is(false));
	}
	
	// End helper method tests

	private void runFilter() throws IOException, ServletException {
		filter.init(fixture.getFilterConfig());
		filter.doFilter(fixture.getRequest(), fixture.getResponse(),
				fixture.getFilterChain());
		filter.destroy();
	}

	private void shouldForward() throws ServletException, IOException {
		verify(fixture.getDispatcher()).forward(fixture.getRequest(),
				fixture.getResponse());
	}

	private void shouldDoFilter() throws IOException, ServletException {
		verify(fixture.getFilterChain()).doFilter(fixture.getRequest(),
				fixture.getResponse());
	}
}
