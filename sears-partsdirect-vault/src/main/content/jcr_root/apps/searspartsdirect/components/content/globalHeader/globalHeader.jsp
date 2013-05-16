<%@ include file="/apps/searspartsdirect/global.jsp" %>

<!--  global logo -->
<!-- <cq:include path="/content/searspartsdirect/en/jcr:content/global_logo" resourceType="foundation/components/image" /> -->


<!--   <cq:include path="/content/searspartsdirect/en/jcr:content/global_logo" resourceType="foundation/components/text" /> -->

 <!--  <cq:include path="commonSymptoms" resourceType="/apps/searspartsdirect/components/content/commonSymptoms" /> -->
 
  <!--  <cq:include path="errorCodesTable" resourceType="/apps/searspartsdirect/components/content/errorCodesTable" />  -->
 

   <li class="first login_link">
		<a class="mainSignInModal" href="javascript:void(0)" title="Login"><spd:getLoginStatus/></a>
    </li>
    
    <li class="register">
		<a class="mainRegisterModal" title="Register" href="javascript:void(0)"><spd:getRegisterStatus/></a>
	</li>

     <p><spd:getRecentlyViewed/></p>
     
     <p><spd:getMyModels/></p>
     
     <p><spd:getShoppingCart/></p>
  
     <p>include dynamic tags libs along with static html</p>