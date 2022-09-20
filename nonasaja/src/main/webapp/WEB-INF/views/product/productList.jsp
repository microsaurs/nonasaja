<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 내용시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
<div class="page-main">
	<input type="button" id="register_btn" onclick="location.href='register.do'" value="상품 등록">
	<input type="button" id="adminList_btn" onclick="location.href='admin_list.do'" value="관리자용 목록">
	<input type="button" id="cart_btn" onclick="location.href='${pageContext.request.contextPath}/cart/cart_list.do'" value="고객용 장바구니">
	<input type="button" id="orderList_btn" onclick="location.href='${pageContext.request.contextPath}/order/order_list.do'" value="고객용 구매목록">
	<!-- 헤드 이미지 -->
	<img src="${pageContext.request.contextPath}/images/listMain2.png" width="100%">
	<!--  카테고리 박스 -->
	<hr class="hr-color" size="1" width="100%">
	<div id="category_box">
		<img class ="category-img" src="${pageContext.request.contextPath}/images/category_living.png">
		<img class ="category-img" src="${pageContext.request.contextPath}/images/category_food.png">
		<img class ="category-txt" src="${pageContext.request.contextPath}/images/category_txt.png">
	</div>
	<div id="search_form_box">
		<form action="list.do" method="get" id="search_form">
			<ul class="search">
				<li>
					<select name="keyfield">
						<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>상품명</option>
						<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>내용</option>
						<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>상품명+내용</option> 
					</select>
				</li>
				<li>
					<input type="search" size="16" name="keyword" id="keyword" value="${param.keyword }"> 
				</li>
				<li>
					<input type="submit" value="찾기">
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
			<div class="product-space">
			<c:forEach var="product" items="${list }">
				<div class="horizontal-area">
					<a href="detail.do?product_num=${product.product_num }">
						<img class="view-img" src="${context.request.contextPath}/product/imageView.do?product_num=${product.product_num}&photo_type=1">
						<br>
						<span>
							${product.title}</span>
						<br>
						<b><fmt:formatNumber value="${product.price2 }"/>원</b>
						<br>
						★${product.scoreAvg}/5 (리뷰:${product.reviewCount}개)
						<br>
						<span>${product.waitCount}/${product.req_quantity} 명 구매중</span>
						<br>
					</a>
				</div>
			</c:forEach>
			<div class="float-clear align-center">
				${page}
			</div>
			</div>
		</c:if>
</div>

<!--내용 끝--> 
