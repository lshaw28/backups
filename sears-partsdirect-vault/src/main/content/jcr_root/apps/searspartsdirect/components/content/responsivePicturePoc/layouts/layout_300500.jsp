    <%@ include file="/apps/searspartsdirect/global.jsp" %>
        <spd:respPicPoc />
    <div class="responsivePictureInner">
        <span data-min="0" data-max="300" data-src="${respImage}?wid=100"></span>
        <span data-min="301" data-max="500" data-src="${respImage}?wid=300" data-default="true"></span>
        <span data-min="501" data-max="13600" data-src="${respImage}?wid=600"></span>
    </div>