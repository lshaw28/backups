package com.spd.cq.searspartsdirect.common.helpers;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;

public class NavigablePageFilter extends PageFilter {
	@Override
	public boolean includes(Page page) {
		if (super.includes(page)) {
			return !page.isHideInNav();
		} else {
			return false;
		}
	}
}
