<%@page import="java.net.URLDecoder" %>
<!-- 
All pages including this files should describe below parameters
modelNumber, brandId, categoryId, brandName,  modelDescription.
 -->
<div id="headerPD">
<div class="row-fluid">
	<div class="repairHelpHomeTitle">
		<div class="pageTitleHeader">
			<h1>
				Model #
				<%=modelNumber %>
				<%=brandName %>
				<%=URLDecoder.decode(modelDescription, "UTF-8") %>
				</h1>
		</div>
	</div>
</div>
<div><a onClick="checkCookie('<%=modelNumber%>', '<%=brandId%>', '<%=categoryId%>')">I own This</a></div>
<div class="row-fluid">
	<div class="modelHeader">
		<ul id="dynamicTabs">
            <!-- Used only for styling -->
            <li class="tabNavigation current">
                <a href="javascript:void(0)">
                    <span> Shop Parts</span>
                </a>
            </li>
            <li class="tabNavigation">
                <a href="#">
                    <span>Manuals()</span>
                </a>
            </li>
            <li class="tabNavigation">
                <a href="#">
                    <span>Repair Help</span>
                </a>
            </li>
            <li class="tabNavigation">
                <a href="#">
                    <span>Expert Q&A</span>
                </a>
            </li>
        </ul>
	</div>
</div>

<!--<div class="responsiveDropdown">
    <div class="new-btn-dropdown">Width (in.)<i class="icon-chevron-sign-down">&nbsp;</i></div>
    <ul class="active">
        <!-- Used only for styling -->
        <!--<li class="tabNavigation current" id="shopParts">
            <a href="#">
                <span> Shop Parts</span>
            </a>
        </li>
        <li class="tabNavigation" id="manuals">
            <a href="#">
                <span>Manuals()</span>
            </a>
        </li>
        <li class="tabNavigation" id="repairHelp">
            <a href="#">
                <span>Repair Help</span>
            </a>
        </li>
        <li class="tabNavigation" id="expertQA">
            <a href="#">
                <span>Expert Q&A</span>
            </a>
        </li>
    </ul>
</div>-->

</div>
<script>getDynamicTabs('<%=modelNumber%>', '<%=brandId%>', '<%=categoryId%>');</script>