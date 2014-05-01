    <%@ include file="/apps/searspartsdirect/global.jsp" %>
        <spd:respPicPoc />
<div class="responsivePicture">
        <span data-min="0" data-max="360" data-src="${respImage}?wid=100"></span>
        <span data-min="361" data-max="720" data-src="${respImage}?wid=300" data-default="true"></span>
        <span data-min="721" data-max="13600" data-src="${respImage}?wid=600"></span>
</div>