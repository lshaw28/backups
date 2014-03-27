<%@ include file="/apps/searspartsdirect/global.jsp"%>
<%@ page import="java.net.URLEncoder"%>

<%
	String address = properties.get("parAddress/address/address1","") +" "+ properties.get("parAddress/address/address2","")+" " +properties.get("parAddress/address/city","")+" " + properties.get("parAddress/address/state","")+" " + properties.get("parAddress/address/zipcode","");
	address = URLEncoder.encode(address, "utf-8");
	String fullAddress = "https://www.google.com/maps/place/" + address;
	
	String productsServicesText = properties.get("parProductsServices/text/text","");
	String ourTeamText = properties.get("parOurTeam/text/text","");
%>

<article id="content" data-templatename="partsRepairCenter">
    <div class="articleFix">
        <div class="row-fluid">
            <div class="span10 desktop-offset1">

                <div id="partsRepairCenter" class="partsRepairCenter">
                    <div class="row-fluid">
                        <div class="span12 prcTitle">
                            <h1><%=properties.get("jcr:title", "")%></h1>
                        </div>
                    </div>

                    <div class="row-fluid">
                        <div class="span10">
                            <cq:include path="parCarousel" resourceType="foundation/components/parsys" />
                            <h3 class="prcSubTitle">About This Store</h3>
                            <cq:include path="parAbout" resourceType="foundation/components/parsys" />
                        </div>
                        <div class="adSpace span2 visible-desktop hidden-tablet hidden-phone">
                            <cq:include path="parAdSpace" resourceType="foundation/components/parsys" />
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span10">
                            <div class="row-fluid">
                                <div class="new-span-responsive">
                                    <cq:include path="parAddress" resourceType="foundation/components/parsys" />
                                    <a href="<%=fullAddress%>" target="_blank"><input type="button" class="new-btn viewMapAndDirectionsButton" value="View Map & Directions"/></a>
                                </div>
                                <div class="new-span-responsive">
                                    <cq:include path="parHoursOfOperation" resourceType="foundation/components/parsys" />
                                </div>
                            </div>

                            <div class="row-fluid">
                            	<% if(productsServicesText.length() > 0) {
                            		%>
                            		<h3 class=prcSubTitle><%=properties.get("jcr:title", "")%> Products & Services</h3>
                            		<%
                            	}
                            	%>
                                <cq:include path="parProductsServices" resourceType="foundation/components/parsys" />
                            </div>

                            <div class="row-fluid">
                                <div class="new-span-responsive">
                                    <cq:include path="parTopCategories" resourceType="foundation/components/parsys" />
                                </div>
                                <div class="new-span-responsive">
                                    <cq:include path="parRepairServices" resourceType="foundation/components/parsys" />
                                </div>
                            </div>
                            <div class="row-fluid">
                            	<% if(ourTeamText.length() > 0) {
                            		%>
                            		<h3 class="prcSubTitle">Our Team</h3>
                            		<%
                            	}
                            	%>
                                <cq:include path="parOurTeam" resourceType="foundation/components/parsys" />
                            </div>
                            <div class="row-fluid">
                                <div class="new-span-responsive">
                                    <cq:include path="parTeamAssociate1" resourceType="foundation/components/parsys" />
                                </div>
                                <div class="new-span-responsive">
                                    <cq:include path="parTeamAssociate2" resourceType="foundation/components/parsys" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!-- /partsRepairCenter -->

            </div><!-- /span10 desktop-offset1 -->
        </div><!-- /row-fluid -->
    </div><!-- /articleFix -->
</article>

