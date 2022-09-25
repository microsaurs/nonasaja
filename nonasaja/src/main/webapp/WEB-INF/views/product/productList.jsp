<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 내용시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/product_search.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product_list.css">
<div class="page-main">
	<!-- 헤드 이미지 -->
	<img src="${pageContext.request.contextPath}/images/listMain2.png" width="100%">
	<!--  카테고리 박스 -->
	<hr class="hr-color" size="1" width="100%">
	<div id="category_box">
		<img class ="category-txt" src="${pageContext.request.contextPath}/images/category_txt.png">
		<div class ="category-float">
		<img class ="category-img" id="food_img" src="${pageContext.request.contextPath}/images/공동구매카테고리-식품.png">
		<img class ="category-img" id="living_img" src="${pageContext.request.contextPath}/images/공동구매카테고리-생활용품png.png">
		</div>
	</div>
	<div id="search_form_box">
		<form action="list.do" id="search_form1" method="get">
			<ul class="search-align">
				<li>
					<select name="keyfield" id="keyfield">
						<option value="" <c:if test="${param.keyfield==0}">selected</c:if>>전체</option>
						<option value="0" <c:if test="${param.keyfield==0}">selected</c:if>>식품</option> 
						<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>생활용품</option>
					</select>
				</li>
				<li>
					
					<input type="search" name="keyword" id="searchbar" value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="찾기" class="button5">
					<input type="button" value="전체목록" onclick="location.href='list.do'" class="button4">
				</li>
			</ul>
		</form>
	</div>
		<c:if test="${count==0 }">
			<div class="result-display">
				표시할 상품이 없습니다.
			</div>
		</c:if>
		<c:if test="${count>0 }">
			<c:forEach var="product" items="${list }">
				<div class="horizontal-area">
					<a href="detail.do?product_num=${product.product_num }">
						<img class="view-img" src="${context.request.contextPath}/product/imageView.do?product_num=${product.product_num}&photo_type=1">
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
	
		 <div class="float-clear align-center">
			<!-- ${page}-->
		</div>
		</c:if>
</div>

<!--내용 끝--> 
