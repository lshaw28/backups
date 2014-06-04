    <%@ page import="com.day.cq.commons.jcr.JcrConstants,
                   com.day.cq.wcm.api.components.Component" %><%
        %><%@include file="/libs/foundation/global.jsp"%><%

    final String regex = "^layout\\_(.*)\\.jsp$";
    response.setContentType("text/plain");

%>[<%

    try {

        NodeIterator scripts = slingRequest.getResourceResolver().getResource(
            component.getPath()+"/layouts").adaptTo(Node.class).getNodes();
        String delim = "";
        while (scripts.hasNext()) {
            Node script = scripts.nextNode();
            String name = script.getName();
            if (name.matches(regex)) {
                String value = name.replaceFirst(regex, "$1");
                String text = value;
                try {
                    if (script.hasProperty(JcrConstants.JCR_TITLE)) {
                        text = script.getProperty("jcr:title").getString();
                    } else {
                        final Component sComp = component.getSuperComponent();
                        if (null != sComp) {
                            final Resource scriptResource = sComp.getLocalResource(script.getName());
                            if (null != scriptResource) {
                                text = scriptResource.adaptTo(ValueMap.class).get(JcrConstants.JCR_TITLE, text);
                            }
                        }
                    }

                } catch (Exception e) {
                    log.debug("displayoptions [{}]: ", script.getPath(), e);
                }
                %><%= delim %><%
        %>{<%
        %>"text":"<%= text %>",<%
        %>"value":"<%= value %>"<%
        %>}<%
                if ("".equals(delim)) delim = ",";
            }
        }

    } catch (RepositoryException re) {}

%>]