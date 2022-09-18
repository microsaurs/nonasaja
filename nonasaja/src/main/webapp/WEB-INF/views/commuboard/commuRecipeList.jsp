<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/recipeList.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>

<div class="page-main">
	<h2>레시피</h2>
	<form action="list.do" method="get" id="search_form">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>음식명</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>제목+음식명</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>난이도</option>
				</select>
			</li>
			<li>
				<input type="search" name="keyword" id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input class="button" type="submit" value="찾기">
				<input class="button" type="button" value="목록"  onclick="location.href='list.do'">
			</li>
		</ul>
	</form>
	<c:if test="${!empty user}">
	<div class="align-right">
		<input class="button" type="button" value="글쓰기" onclick="location.href='write.do'">
	</div>
	</c:if>
	<c:if test="${count == 0}">
	<div class="result-display">
		표시할 상품이 없습니다.
	</div>
	</c:if>
	<c:if test="${count > 0}">
	<div class="item-space">
		<c:forEach var="board" items="${list}">
			<div class="horizontal-area">
				<a href="detail.do?commu_num=${board.commu_num}">
					<img id ="recipeList_food_photo" src="${pageContext.request.contextPath}/commuRecipe/imageView.do?commu_num=${board.commu_num}&board_type=2">
					<span>${board.commu_title}</span>
					<br>
					<span id="recipeList_food"><b>${board.commu_food}</b></span>
					<br>
				<div id = "level">
					<img id ="recipeList_level" src="${pageContext.request.contextPath}/images/recipeList_level2.png">
					<span>${board.commu_level}</span>
					<img id ="recipeList_time" src="${pageContext.request.contextPath}/images/recipeList_time.png">
					<span>${board.commu_time}</span>
				</div>
				</a>
			</div>
		</c:forEach>
		<div class="float-clear align-center">
			${page}
		</div>
	</div>
	</c:if>
</div>
<!-- 내용 끝 -->


