<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product_list.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/used.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/commuList.css">

<div class="search-wrap">
	<h2>통합검색</h2>
	<div class="result">
	<!-- 상품 검색 결과 -->
	<c:if test="${!empty productList}">
	<h3>상품 검색 결과</h3>
	<c:forEach var="product" items="${productList}">
	<div class="result-sub">
		<a href="${pageContext.request.contextPath}/product/detail.do?product_num=${product.product_num }">
			<img class="view-img1" src="${context.request.contextPath}/product/imageView.do?product_num=${product.product_num}&photo_type=1">
			<br>
			<span id="title">${product.title}</span>
			<br>
			<span id="price"><fmt:formatNumber value="${product.price2 }"/></span>원
			<br>
			<span>★ ${product.scoreAvg}/5 (리뷰:${product.reviewCount}개)</span>
			<br>
			<span id="req">${product.waitCount}/${product.req_quantity} 명 구매중</span>
			<br>
		</a>
	</div>
	</c:forEach>
	<br>
	</c:if>
	<!-- 중고거래 검색 결과 -->
	<c:if test="${!empty usedList}">
	<h3>중고거래 검색 결과</h3>
	<c:forEach var="used" items="${usedList}">
	<div class="result-sub">
		<div class="status-img-float" >
			<div class="status-img-float1">
			<c:if test="${used.status==0}"><img src="${pageContext.request.contextPath}/images/노나사자-판매중.png" width="100px" height="45px"></c:if>
			<c:if test="${used.status==1}"><img src="${pageContext.request.contextPath}/images/노나사자-판매완료.png" width="100px" height="45px"></c:if>
			</div>
			<div class="status-img-float1">
			<c:if test="${used.kind==0}"><img src="${pageContext.request.contextPath}/images/노나사자-중고거래.png" width="100px" height="45px"></c:if>
			<c:if test="${used.kind==1}"><img src="${pageContext.request.contextPath}/images/노나사자-무료나눔.png" width="100px" height="45px"></c:if>
			<c:if test="${used.kind==2}"><img src="${pageContext.request.contextPath}/images/노나사자-물물교환.png" width="100px" height="45px"></c:if>
			</div>
			<br>
			<div class="horizontal-area1">
			<a href="${pageContext.request.contextPath}/used/detail.do?used_num=${used.used_num}">
				<img id ="usedproduct_photo" src="${pageContext.request.contextPath}/used/imageView.do?used_num=${used.used_num}&board_type=2">
				<br>
				<span class="usedtitle">${used.title}</span>
				<br>
				<span class="usedtitle">
				<c:if test="${used.price!=0 && used.price!=1}">
					<b><fmt:formatNumber value="${used.price}"/>원</b>
				</c:if>
				<c:if test="${used.price==0}">
					<b>무료나눔&#x1f49b;</b>
				</c:if>
				<c:if test="${used.price==1}">
				<b>물물교환&#x1f499;</b>
				</c:if>
				</span>
				<span id="usedproduct_region" class="usedtitle">${used.region}</span>
			</a>
			</div>
		</div>
	</div>
	</c:forEach>
	<br>
	</c:if>
	<!-- 동호회 검색 결과 -->
	<c:if test="${!empty clubList}">
	<h3>동호회 검색 결과</h3>
	<c:forEach var="board" items="${clubList}">
	<div class="result-sub">
		<table class="float-clear">
		<tr>
			<th id="th-1">
			<c:if test="${board.club_recruit==0}"><img src="${pageContext.request.contextPath}/images/동호인모집중.JPG" width="80px"></c:if>
			<c:if test="${board.club_recruit==1}"><img src="${pageContext.request.contextPath}/images/동호인모집완료.JPG" width="100px"></c:if>
			</th>
			<th width="400" id="th-2"><a href="${pageContext.request.contextPath}/clubboard/detail.do?club_num=${board.club_num}">${board.club_title}</a></th>
			<th id="th_clubname"><b>${board.club_name}</b></th>
		</tr>
		<tr>
			<td id="td-1">
			<img src="${pageContext.request.contextPath}/images/icon-gender.png" width="40px">
			</td>
			<td id="td-2">
			<c:if test="${board.club_gender==0}">누구나 참여가능</c:if>
			<c:if test="${board.club_gender==1}">남자만 참여가능</c:if>
			<c:if test="${board.club_gender==2}">여자만 참여가능</c:if>
			</td>
			<td id="td_img"rowspan="5">
			<c:if test="${!empty board.club_img_name}">
			<img  src="${pageContext.request.contextPath}/clubboard/imageView.do?club_num=${board.club_num}" width="250" height="250">
			</c:if>
			<c:if test="${empty board.club_img_name}">
			<img  src="${pageContext.request.contextPath}/images/노나사자대표이미지.png" width="250" height="250">
			</c:if>
			</td>
		</tr>
		<tr>
			<td id="td-1">
			<img src="${pageContext.request.contextPath}/images/icon-age.png" width="40px">
			</td>
			<td id="td-2">
			<c:if test="${board.club_age == '10대,20대,30대,40대,50대,60대'}">
			누구나 참여가능
			</c:if>
			<c:if test="${board.club_age != '10대,20대,30대,40대,50대,60대'}">
			${board.club_age} 참여가능
			</c:if>
			</td>
		</tr>
		<tr>
			<td id="td-1">
			<img src="${pageContext.request.contextPath}/images/icon-calendar-date.png" width="40px">
			</td>
			<td id="td-2">
			${board.club_date}까지
			</td>
		</tr>
		<tr>
			<td id="td-1">
			<img src="${pageContext.request.contextPath}/images/icon-location.png" width="40px">
			</td>
			<td id="td-2">
			${board.club_region}
			</td>
		</tr>
		<tr>
			<td id="td-3">
			<img src="${pageContext.request.contextPath}/images/icon-multiple-users-silhouette.png" width="40px">
			</td>
			<td id="td-4">
			${board.club_pre}/${board.club_limit}명 참여
			</td>
		</tr>
		</table>
	</div>
	</c:forEach>
	<br>
	</c:if>
	<!-- 레시피 검색 결과 -->
	<c:if test="${!empty recipeList}">
	<h3>레시피 검색 결과</h3>
	<c:forEach var="board" items="${recipeList}">
	<div class="result-sub">
	<div class="horizontal-area1">
		<a href="${pageContext.request.contextPath}/commuRecipe/detail.do?commu_num=${board.commu_num}">
			<img id ="recipeList_food_photo" src="${pageContext.request.contextPath}/commuRecipe/imageView.do?commu_num=${board.commu_num}&board_type=2" width="230" height="230"><br>
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
	</div>
	</c:forEach>
	<br>
	</c:if>
	<!-- 커뮤니티 검색 결과 -->
	<c:if test="${!empty commuList}">
	<h3>커뮤니티 검색 결과</h3>
	<table class="table">
		<thead class="table-light">
			<tr>
				<th>카테고리</th>
				<th width="400">제목</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="board" items="${commuList}">
			<tr>
				<td>
				<c:if test="${board.commu_code==1}"><strong>자유</strong></c:if>
				<c:if test="${board.commu_code==2}"><font color="blue"><strong>지역소식</strong></font></c:if>
				<c:if test="${board.commu_code==3}"><font color="green"><strong>자취백과</strong></font></c:if>
				</td>
				<td><a href="${pageContext.request.contextPath}/commuboard/detail.do?commu_num=${board.commu_num}">${board.commu_title}</a></td>
				<td>${board.commu_date}</td>
				<td>${board.commu_hit}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	</c:if>
	<!-- 세일정보 검색 결과 -->
	<c:if test="${!empty saleList}">
	<h3>세일정보 검색 결과</h3>
	<table class="table">
		<thead class="table-light">
			<tr>
				<th>번호</th>
				<th width="400">제목</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="board" items="${saleList}">
			<tr>
				<td>${board.board_num}</td>
				<td><a href="${pageContext.request.contextPath}/sale/saleBoardDetail.do?board_num=${board.board_num}">${board.title}</a></td>
				<td>${board.reg_date}</td>
				<td>${board.hit}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>

	</div>
</div>