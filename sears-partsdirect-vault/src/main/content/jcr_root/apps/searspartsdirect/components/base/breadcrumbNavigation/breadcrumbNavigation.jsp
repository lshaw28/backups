<%@ include file="/apps/searspartsdirect/global.jsp" %>
<ul class="breadcrumb">
<%
	// @TODO: Speak to Dale
    // get starting point of trail
    long level = currentStyle.get("absParent", 2L);
    long endLevel = currentStyle.get("relParent", 1L);
    String delimStr = currentStyle.get("delim", "&nbsp;&gt;&nbsp;");
	delimStr = "<span class=\"divider\">" + delimStr + "</span>";
    String trailStr = currentStyle.get("trail", "");
    int currentLevel = currentPage.getDepth();
    String delim = "";
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
		if (level + 1 == currentLevel - endLevel) {
			linkClass = "visible-phone visible-tablet visible-desktop";
		}
%>
		<li class="<%= linkClass %>">
			<%= xssAPI.filterHTML(delim) %>
			<a href="<%= xssAPI.getValidHref(trail.getPath()+".html") %>"
             onclick="CQ_Analytics.record({event:'followBreadcrumb',values: { breadcrumbPath: '<%= xssAPI.getValidHref(trail.getPath()) %>' },collect: false,options: { obj: this },componentPath: '<%=resource.getResourceType()%>'})"><%= xssAPI.encodeForHTML(title) %></a>
		</li>
<%

        delim = delimStr;
        level++;
    }
    if (trailStr.length() > 0) {
        %><%= xssAPI.filterHTML(trailStr) %><%
    }

%>
</ul>