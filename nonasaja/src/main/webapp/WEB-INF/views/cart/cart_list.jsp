<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--내용 시작-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/cart.js"></script>
<div class="page-main">
	<h2>장바구니</h2>
	<form id="search_form" action="cart_list.do" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="3" <c:if test="${param.keyfield==1}">selected</c:if> >전체</option>
					<option value="1" <c:if test="${param.keyfield==1}">selected</c:if> >장바구니</option>
					<option value="2" <c:if test="${param.keyfield==2}">selected</c:if> >구매대기</option>
				</select>
			</li>
			<li>
				<input type="search" size="16" name="keyword" id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="검색">
			</li>
		</ul>
	</form>
	<c:if test="${count == 0 }">
		<div class="result-display">표시할 상품이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0 }">
	<form action="cart_order.do" id="cartOrder">
		<table>
		
			<tr>
				<th>주문하기</th>			
				<th>번호</th>
				<th>상품명</th>
				<th>주문수량</th>
				<th>상품금액</th>
				<th>소분 수량</th>
				<th>장바구니 삭제</th>
			</tr>
			<c:forEach var="cart" items="${list}">
				<input type="hidden" name="product_num" value="${cart.product_num}">
			<tr>
				<td>
					<input type="checkbox" name="cart_num" value="${cart.cart_num}">
				</td>
				<td>${cart.product_num }</td>
				<td>
				<c:if test="${cart.productVO.status == 1 }">
					<del>${cart.productVO.name}</del>
				</c:if>
				<c:if test="${cart.productVO.status == 2 }">
					<a href="${pageContext.request.contextPath}/product/detail.do?product_num=${cart.product_num }">${cart.productVO.name}</a>
				</c:if>
				</td>
				<td>
				<input type="number" name="quantity" min="1" max="99999" value="${cart.quantity}" class="quantity-width">
				<br>
				<input type="button" value="변경" class="cart-modify" data-cartnum="${cart.cart_num}" data-productnum="${cart.product_num}">
				</td>
				<td><fmt:formatNumber value="${cart.productVO.price2 }"/></td>
				<td>${cart.productVO.req_quantity }</td>
				<td><input type="button" class="cart-del" value="삭제" data-cartnum=${cart.cart_num }></td>
			</tr>
			</c:forEach>
			</table>
			<input type="submit" value="주문하기">
		</form>
		
		<div class="align-center">
			${page}
		</div>
	</c:if>
</div>
<!-- 내용 끝 -->