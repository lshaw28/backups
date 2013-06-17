package com.spd.cq.searspartsdirect.common.tags;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.jsp.JspException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.junit.Test;
import org.mockito.Mockito;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class GetErrorCodesListTagTest extends MocksTag {
	
	GetErrorCodesListTag tag;
	Resource resource;
	Session session;
	QueryBuilder builder;
	Query query;
	SearchResult result;
	Map<String, String> map;
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		resource = Mockito.mock(Resource.class);
		session = Mockito.mock(Session.class);
		builder = Mockito.mock(QueryBuilder.class);
		query = Mockito.mock(Query.class);
		result = Mockito.mock(SearchResult.class);
		tag = new GetErrorCodesListTag();
	}
	
	@Test
	public void testGetErrorCodeDataWithBrands() throws JspException, RepositoryException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("path", "/content/searspartsdirect/en/error-tables");
		map.put("type", Constants.CQ_PAGE);
		map.put("1_property", "jcr:content/pages");
		map.put("1_property.value", "dummyCategory");
		
		when(pageContext.getRequest()).thenReturn(request);
		when(resourceResolver.getResource("/etc/spdAssets/scaffolding/errorCode")).thenReturn(resource);
		when(pageContext.findAttribute("resourceResolver")).thenReturn(resourceResolver);
		when(pageContext.findAttribute("slingRequest")).thenReturn(slingRequest);
		when(slingRequest.getResourceResolver()).thenReturn(resourceResolver);
		when(resourceResolver.adaptTo(QueryBuilder.class)).thenReturn(builder);
		when(slingRequest.getResourceResolver().adaptTo(Session.class)).thenReturn(session);
		when(builder.createQuery(any(PredicateGroup.class),any(Session.class))).thenReturn(query);
		when(query.getResult()).thenReturn(result);
		
		List<Hit> hits = new ArrayList<Hit>();
		for (int i = 0; i < 1; i++) {
			hits.add(getHitPage(i));
		}
		when(result.getHits()).thenReturn(hits);
		tag.setPageContext(pageContext);
		tag.setCategoryPath("dummyCategory");
		tag.doStartTag();
		assertNotNull(pageContext.getAttribute("errorCodeList"));
	}
	
	private Hit getHitPage(int i) throws RepositoryException {
		Hit hit = mock(Hit.class);
		Page page = mock(Page.class);
		String hitPath = "/hit"+i;
		when(hit.getPath()).thenReturn(hitPath);
		when(page.getPath()).thenReturn(hitPath);
		when(pageManager.getPage(hitPath)).thenReturn(page);
		
		ValueMap properties = mock(ValueMap.class);
		when(page.getProperties()).thenReturn(properties);
		when(hit.getProperties()).thenReturn(properties);
		when(properties.get(Constants.ASSETS_TITLE_PATH, String.class)).thenReturn(hitPath+" title");
		when(pageManager.getPage(hitPath)).thenReturn(page);
		
		when(properties.get("pages", String[].class)).thenReturn(new String[] {"/etc/spdAssets/scaffolding/brand/testBrand"});
		when(pageManager.getPage("/etc/spdAssets/scaffolding/brand/testBrand")).thenReturn(page);
		return hit;
	}
}
