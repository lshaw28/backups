package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.ValueMap;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.spd.cq.searspartsdirect.common.helpers.AssetType;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class GetRelationTagFixture {
	
	private final static String[] empty = new String[0];
	private PageManager pageManager;
	
//	Page workingPage = pagepath != null ? pageManager.getPage(pagepath) : currentPage;
//	String[] relations = workingPage.getProperties().get("pages", empty);
	public GetRelationTagFixture(PageManager pageManager, Page currentPage) {
		this.pageManager = pageManager;
		Page thatPage = mock(Page.class);
		when(pageManager.getPage(getThatPagepath())).thenReturn(thatPage);
		when(pageManager.getPage(getThisPagepath())).thenReturn(currentPage);
		setUpProperties(currentPage);
		setUpProperties(thatPage);
	}
	
	ValueMap setUpProperties(Page aPage) {
		ValueMap pagesProperties = mock(ValueMap.class);
		when(aPage.getProperties()).thenReturn(pagesProperties);
		AssetType[] allAssetTypes = AssetType.values();
		List<String> relatedPaths = new ArrayList<String>();
		String[] testRelations = {"first","second","third"};
		for (int i = 0; i < allAssetTypes.length; i++) {
			AssetType aType = allAssetTypes[i];
			String mixedCaseName = aType.getMixedCaseName();
			for (String s : testRelations) {
				String relPath = Constants.ASSETS_PATH + "/" + mixedCaseName + "/" + s;
				relatedPaths.add(relPath);
				Page relPage = mock(Page.class);
				when(pageManager.getPage(relPath)).thenReturn(relPage);
				ValueMap relPageProperties = mock(ValueMap.class);
				//ValueMap properties = p.getProperties();
				when(relPage.getProperties()).thenReturn(relPageProperties);
				//String title = properties.get(Constants.ASSETS_TITLE_PATH,"");
				//String description = properties.get(Constants.ASSETS_DESCRIPTION_PATH,"");
				when(relPageProperties.get(Constants.ASSETS_TITLE_PATH,"")).thenReturn(s + " title");
				when(relPageProperties.get(Constants.ASSETS_DESCRIPTION_PATH,"")).thenReturn(s + " description");
			}
			relatedPaths.add(Constants.ASSETS_PATH + "/" + mixedCaseName + "/clinker");
		}
		when(pagesProperties.get("pages",empty)).thenReturn(relatedPaths.toArray(empty));
		return pagesProperties;
	}
	
	public String getThatPagepath() {
		return "/content/searspartsdirect/en/that";
	}
	
	public String getThisPagepath() {
		return "/content/searspartsdirect/en/this";
	}

	public void breakThisPageRelations() {
		Page currentPage = pageManager.getPage(getThisPagepath());
		ValueMap properties = currentPage.getProperties();
		when(properties.get("pages",empty)).thenReturn(new String[0]);
	}
	
//ASSETS_PATH = ident("/etc/spdAssets/scaffolding");
//if (Pattern.matches(Constants.ASSETS_PATH + "/" + assetType + "/[^/]+", relations[i])) {

}
