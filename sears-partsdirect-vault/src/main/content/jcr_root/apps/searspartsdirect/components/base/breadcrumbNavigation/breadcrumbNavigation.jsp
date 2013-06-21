<%@ include file="/apps/searspartsdirect/global.jsp" %>
<ul class="breadcrumb">
	<li class="hidden-phone visible-tablet visible-desktop"><a href="http://www.searspartsdirect.com/">Home</a></li>
<%
    // get starting point of trail
    long level = currentStyle.get("absParent", 3L); // 2nd arg here is absolute depth to start at
    long endLevel = currentStyle.get("relParent", 0L); // 2nd arg here is relative height to end at
    String delimStr = currentStyle.get("delim", "&nbsp;&gt;&nbsp;");
	delimStr = "<span class=\"divider\">" + delimStr + "</span>";
    String trailStr = currentStyle.get("trail", "");
    int currentLevel = currentPage.getDepth();
    String delim = ">";
	String linkClass = "hidden-phone visible-tablet visible-desktop";
    while (level < currentLevel - endLevel) {
        Page trail = currentPage.getAbsoluteParent((int) level);
        if (trail == null) {
            break;
        }
        String title = trail.getNavigationTitle();
        if (title == null || title.equals("")) {
            title = trail.getNavigationTitle();
        }
        if (title == null || title.equals("")) {
            title = trail.getTitle();
        }
        if (title == null || title.equals("")) {
            title = trail.getName();
        }
		if (level + 2 == currentLevel - endLevel) { // immediate parent and current page always visible
			linkClass = "visible-phone visible-tablet visible-desktop";
		}
		if (level + 1 != currentLevel - endLevel) { // a node above the current page
%>
		<li class="<%= linkClass %>">
			<%= xssAPI.filterHTML(delim) %>
			<a href="<%= xssAPI.getValidHref(trail.getPath()+".html") %>"
             onclick="CQ_Analytics.record({event:'followBreadcrumb',values: { breadcrumbPath: '<%= xssAPI.getValidHref(trail.getPath()) %>' },collect: false,options: { obj: this },componentPath: '<%=resource.getResourceType()%>'})"><%= xssAPI.encodeForHTML(title) %></a>
		</li>
<%
		} else { // leaf (current page), no link
%>
		<li class="<%= linkClass %>">
			<%= xssAPI.filterHTML(delim) %>
			<%= xssAPI.encodeForHTML(title) %>
		</li>
<%
		}

        delim = delimStr;
        level++;
    }
    if (trailStr.length() > 0) {
        %><%= xssAPI.filterHTML(trailStr) %><%
    }

%>
</ul>