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
		<table>
			<tr>
				<th>번호</th>
				<th>상품명</th>
				<th>주문수량</th>
				<th>상품금액</th>
			</tr>
			<c:forEach var="item" items="${list}">
			<tr>
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
			</tr>
			</c:forEach>
		</table>
		<div class="align-center">
			${page}
		</div>
	</c:if>
</div>
<!-- 내용 끝 -->