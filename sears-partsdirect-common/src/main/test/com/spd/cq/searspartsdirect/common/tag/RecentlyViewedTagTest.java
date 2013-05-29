package com.spd.cq.searspartsdirect.common.tag;

import java.util.LinkedList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.spd.cq.searspartsdirect.common.model.ModelCookieModel;
import com.spd.cq.searspartsdirect.common.model.PartCookieModel;
import com.spd.cq.searspartsdirect.common.tags.RecentlyViewedTag;

//@RunWith(PowerMockRunner.class)
public class RecentlyViewedTagTest {
	
	 /*private Mockery context = new JUnit4Mockery() {
         {
             setImposteriser(ClassImposteriser.INSTANCE);
         }
	 };*/
	
	/*@Test
	public void testJMockTest() throws JspException {
		final PageContext pageContext = context.mock(MockPageContext.class);
		final HttpServletRequest request = context.mock(HttpServletRequest.class);
		final CQBaseTag cqBaseTag = context.mock(CQBaseTag.class);
		
		RecentlyViewedTag recentlyViewedTag = new RecentlyViewedTag();
		recentlyViewedTag.setPageContext(pageContext);
		
		cqBaseTag.setPageContext(pageContext);
		
		context.checking(new Expectations() {
              {
            	 ignoring(cqBaseTag).setPageContext(pageContext);
            	 oneOf(request).getCookies().will(returnValue(getCookie()));
                 //cqBaseTag.setPageContext(pageContext);
              }
		  });
		
		recentlyViewedTag.doStartTag();
	}*/
	

	@Test
	public void testWithNoCookie() throws JspException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		PageContext pageContext = Mockito.mock(PageContext.class);
		
		Mockito.when(pageContext.getRequest()).thenReturn(request);
		Mockito.when(request.getCookies()).thenReturn(getCookie());

		RecentlyViewedTag recentlyViewedTag = new RecentlyViewedTag();
		recentlyViewedTag.setPageContext(pageContext);
		recentlyViewedTag.doStartTag();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testWithCookies() throws JspException {
		LinkedList<ModelCookieModel> modelList = getModelList();
		LinkedList<PartCookieModel> partList = getPartList();
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		PageContext pageContext = Mockito.mock(PageContext.class);

		Mockito.when(pageContext.getRequest()).thenReturn(request);
		Mockito.when(request.getCookies()).thenReturn(getPartAndModelCookie());
		Mockito.when(pageContext.getAttribute("rvModelList")).thenReturn(modelList);
		Mockito.when(pageContext.getAttribute("recentlyViewedParts")).thenReturn(partList);

		RecentlyViewedTag recentlyViewedTag = new RecentlyViewedTag();
		recentlyViewedTag.setPageContext(pageContext);
		recentlyViewedTag.doStartTag();
		
		LinkedList<ModelCookieModel> actualModelList = (LinkedList<ModelCookieModel>) pageContext.getAttribute("rvModelList");
		Assert.assertNotNull(actualModelList);
		Assert.assertTrue(actualModelList.size() > 0);
		Assert.assertEquals("3333", actualModelList.get(0).getItemName());
		Assert.assertEquals("Some description", actualModelList.get(0).getItemDescription());
		Assert.assertEquals("http://www.SPD.com/ModelUrl", actualModelList.get(0).getItemURL());
		
		
		LinkedList<PartCookieModel> actualPartList = (LinkedList<PartCookieModel>) pageContext.getAttribute("recentlyViewedParts");
		Assert.assertNotNull(actualPartList);
		Assert.assertTrue(actualPartList.size() > 0);
		Assert.assertEquals("9010P", actualPartList.get(0).getItemName());
		Assert.assertEquals("water filter", actualPartList.get(0).getItemDescription());
		Assert.assertEquals("itemImageURL", actualPartList.get(0).getItemImageURL());
		Assert.assertEquals("partUrl", actualPartList.get(0).getItemURL());
		
	}
	
	/*@Test
	public void testEasyAndPowerMockTest() throws JspException{
		PageContext pageContext = createMock(PageContext.class);
		HttpServletRequest request = createMock(HttpServletRequest.class);
		CQBaseTag cqBaseTag = createMock(CQBaseTag.class);
		
		EasyMock.expect(request.getCookies()).andReturn(getCookie());
		
		cqBaseTag.setPageContext(pageContext);
		//EasyMock.expectLastCall().once();
		RecentlyViewedTag recentlyViewedTag = new RecentlyViewedTag();
		recentlyViewedTag.setPageContext(pageContext);
		EasyMock.expectLastCall().once();
		
		cqBaseTag.setPageContext(pageContext);
		EasyMock.expectLastCall().once();
		
		recentlyViewedTag.doStartTag();
		
		PowerMock.verify(PageContext.class);
		PowerMock.verify(HttpServletRequest.class);
	}*/

	
	private Cookie[] getCookie() {
		Cookie cookie = new Cookie( "testCookie", "1234" );
        cookie.setMaxAge(12000);
        cookie.setPath("/partsdirect/");
        cookie.setDomain("localhost");
        return new Cookie[] {cookie};
	}
	
	private Cookie[] getPartAndModelCookie() {
		Cookie modelCookie = new Cookie( "recentlyViewedModels", "" );
		modelCookie.setValue("3333|Some description|http://www.SPD.com/ModelUrl");
        modelCookie.setMaxAge(12000);
        modelCookie.setPath("/partsdirect/");
        modelCookie.setDomain("localhost");
        
        Cookie partCookie = new Cookie( "recentlyViewedParts", "" );
        partCookie.setValue("9010P|water filter|partUrl|itemImageURL");
        partCookie.setMaxAge(12000);
        partCookie.setPath("/partsdirect/");
        partCookie.setDomain("localhost");
        return new Cookie[] {modelCookie, partCookie};
	}
	
	private LinkedList<ModelCookieModel> getModelList() {
		LinkedList<ModelCookieModel> modelList = new LinkedList<ModelCookieModel>();
		ModelCookieModel modelCookieModel = new ModelCookieModel();
		modelCookieModel.setItemName("3333");
		modelCookieModel.setItemDescription("Some description");
		modelCookieModel.setItemURL("http://www.SPD.com/ModelUrl");
		modelList.add(modelCookieModel);
		return modelList;
	}
	
	private LinkedList<PartCookieModel> getPartList() {
		LinkedList<PartCookieModel> partModel = new LinkedList<PartCookieModel>();
		PartCookieModel partCookieModel = new PartCookieModel();
		partCookieModel.setItemName("9010P");
		partCookieModel.setItemDescription("water filter");
		partCookieModel.setItemImageURL("itemImageURL");
		partCookieModel.setItemURL("partUrl");
		partModel.add(partCookieModel);
		return partModel;
	}
}
