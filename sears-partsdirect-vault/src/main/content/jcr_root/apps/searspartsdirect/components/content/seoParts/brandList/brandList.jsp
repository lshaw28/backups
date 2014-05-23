<%@ include file="/apps/searspartsdirect/global.jsp" %>
<%@ page import="javax.jcr.Property, javax.jcr.Value, com.day.cq.wcm.api.WCMMode, com.spd.cq.searspartsdirect.common.environment.EnvironmentSettings"%>

<c:set var="title"><cq:text property="title"/></c:set>

<H3>${title}</H3>
<%
String cssClassPrefix = "svg-icon-";
String cssClass = "";
StringBuffer brandJsonObject = new StringBuffer();
String externalPrefix = "";

if(currentNode.hasProperty("brands")){
	
	EnvironmentSettings envSettings = sling.getService(EnvironmentSettings.class);
	externalPrefix = envSettings.getExternalAddedPrefix();

	Property prop = currentNode.getProperty("brands");
	
	if(prop.isMultiple()){
		Value[] brandArray = prop.getValues();
		for(Value brand : brandArray) {
			if(brandJsonObject.length() != 0){
				brandJsonObject.append(", ");
			}
			cssClass = cssClassPrefix + brand.getString().toLowerCase();
			brandJsonObject.append("{\"name\" : \""+brand.getString().toLowerCase()+"\", \"cssName\" : \""+cssClass+"\"}");
		}
	}else{
		String brand = prop.getString();
		cssClass = cssClassPrefix + brand.toLowerCase();
		brandJsonObject.append("{\"name\" : \""+brand.toLowerCase()+"\", \"cssName\" : \""+cssClass+"\"}");
	}
	brandJsonObject.insert(0, "[");
	brandJsonObject.insert(brandJsonObject.length(), "]");
  %>
    <div id="slider-caro-applianceV2" class="brand-container">
      <div class="brand-wraper"></div>
    </div>
    <div class="iconSelectView hidden-phone" id="stopShowStart">
      <div class="iconArrow fsShow"><i class="icon-chevron-down"></i></div>
      <div class="iconArrow fsHide"><i class="icon-chevron-up"></i></div>
    </div>
    <div class="swipeViewGuide hidden-desktop">
      <div class="iconHand"><i class="icon-hand-up"></i> Swipe to see more</div>
    </div>
  <%
} else if(WCMMode.fromRequest(request) != WCMMode.DISABLED){
    %>
   
  <div class="row-fluid">
    <div class="span2"><img alt='Placeholder' src='/libs/cq/ui/widgets/themes/default/placeholders/list.png'/>brand1</div>
    <div class="span2"><img alt='Placeholder' src='/libs/cq/ui/widgets/themes/default/placeholders/list.png'/>brand2</div>
    <div class="span2"><img alt='Placeholder' src='/libs/cq/ui/widgets/themes/default/placeholders/list.png'/>brand3</div>
    <div class="span2"><img alt='Placeholder' src='/libs/cq/ui/widgets/themes/default/placeholders/list.png'/>brand4</div>
    <div class="span2"><img alt='Placeholder' src='/libs/cq/ui/widgets/themes/default/placeholders/list.png'/>brand5</div>
    <div class="span2"><img alt='Placeholder' src='/libs/cq/ui/widgets/themes/default/placeholders/list.png'/>brand6</div>
</div>  

    <%
} // end else
%>

<script id="data" type="application/json"><%=brandJsonObject.toString() %></script>

<script>
  $(document).ready(function(){
    var brandJSON = JSON.parse($("#data").html());
    brandList(brandJSON, '<%=externalPrefix %>');
  });              
</script>