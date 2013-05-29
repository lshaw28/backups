<%@ include file="/apps/searspartsdirect/global.jsp" %>
<form action="/content/searspartsdirect/en/comments-test/_jcr_content/parsys/comments_0.social.createcomment.html">
<%
//TODO Get rid of this scriptlet

%><%=((Page)pageContext.getAttribute("currentPage")).adaptTo(CommentSystem.class);%>
posts to /content/searspartsdirect/en/comments-test/_jcr_content/parsys/comments_0.social.createcomment.html, wonder what the parameters are.
<textarea name="jcr:description"></textarea>
<input type="text" name="userIdentifier" />
<input type="text" name="email" />
<input type="text" name="url" />
<input type="submit" name="submit" />
</form>