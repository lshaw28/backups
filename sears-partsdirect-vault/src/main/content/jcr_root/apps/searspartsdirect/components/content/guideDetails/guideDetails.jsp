<%@ include file="/apps/searspartsdirect/global.jsp" %>
<c:set var="difficultyLevel"><cq:text property="difficultyLevel" placeholder="" /></c:set>
<c:if test="${empty difficultyLevel}"><c:set var="difficultyLevel" value="level-one" /></c:if>

<div class="row-fluid">
	<div class="new-span-quarter">
		<spd:displayImage path="${currentPage.path}${Constants.ASSETS_IMAGE_PATH}" decorated="false" />
	</div>
	<div class="new-span-three-quarters">
		<p><cq:text property="longDescription" /></p>
	</div>
	<div class="span9 tablet-snap">
		<div class="new-span-third">
			<h5>Repair difficulty:</h5>
			<div class="difficulty-rating ${difficultyLevel}">
				<i class="icon-wrench rating-one">&nbsp;</i><i class="icon-wrench rating-two">&nbsp;</i><i class="icon-wrench rating-three">&nbsp;</i><i class="icon-wrench rating-four">&nbsp;</i><i class="icon-wrench rating-five">&nbsp;</i>
			</div>
		</div>
		<div class="new-span-third">
			<h5>Time required:</h5>
			<p><cq:text property="timeRequired" /></p>
		</div>
	</div>
</div>