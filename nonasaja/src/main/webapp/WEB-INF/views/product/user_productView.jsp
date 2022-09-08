<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/product.detail.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.number.min.js"></script>
<div class="page-main">
	<!-- 판매 중지 상품 -->
	<c:if test="${product.status==1}">
		<div class="result-display">
			<div class="align-center">
				본 상품은 판매 중지되었습니다.<br>
				<input type="button" value="판매상품 보기" onclick="location.href='list.do'">
			</div>
		</div>
	</c:if>
	
	<c:if test="${product.status==2 }">
		<h2 class="align-center">${product.name }</h2>
		<div class="product-image">
			<img src="imageView.do?product_num=${product.product_num}&photo_type=1" width="400" height="400">
			<!-- 사진이 2,3개 있으면 슬라이드로 만들기 -->
		</div>
		<div class="product-detail">
			<form id="product_cart" method="post">
				<input type="hidden" name="product_num" value="${product.product_num}" id="product_num">
				<input type="hidden" name="product_price2" value="${product.price2}" id="product_price2">
				<input type="hidden" name="product_quantity" value="${product.quantity}" id="product_quantity">
				<ul>
					<li>가격 : <b><fmt:formatNumber value="${product.price2 }"/>원</b></li>
					<li>수량 : <span><fmt:formatNumber value="${product.quantity}"/>개</span></li>
					<c:if test="${product.quantity>0 }">
					<li>
						<label for="order_quantity">구매 수량</label>
						<input type="number" name="order_quantity" min="1" max=${product.quantity} 
								id="order_quantity" class="quantity-width">	
					</li>
					<li>
						<span id="product_total_txt">총 주문 금액 : 0원</span>
					</li>
					<li>
						<input type="submit" value="장바구니에 담기">
					</li>
					</c:if>
					<c:if test="${product.quantity <= 0 }">
					<li class="align-center">
						<span class="sold-out">품절</span>
					</li>
					</c:if>
				</ul>
			</form>
		</div>
		<hr size="1" noshade="noshade" width="100%">
		<p>
			${product.detail}
		</p>
	</c:if>
</div>
























