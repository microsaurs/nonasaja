<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--내용 시작-->
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/js/cart.js"></script>  -->
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
			</tr>
			<c:forEach var="item" items="${list}">
				<input type="hidden" name="product_num" value="${item.product_num}">
			<tr>
				<td>
					<input type="checkbox" name="cart_num" value="${item.cart_num}">
				</td>
				<td>${item.product_num }</td>
				<td>
				<c:if test="${item.productVO.status == 1 }">
					<del>${item.productVO.name}</del>
				</c:if>
				<c:if test="${item.productVO.status == 2 }">
					<a href="${pageContext.request.contextPath}/product/detail.do?product_num=${item.product_num }">${item.productVO.name}</a>
				</c:if>
				</td>
				<td><fmt:formatNumber value="${item.quantity }"/></td>
				<td><fmt:formatNumber value="${item.productVO.price2 }"/></td>
				<td>${item.productVO.req_quantity }</td>
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