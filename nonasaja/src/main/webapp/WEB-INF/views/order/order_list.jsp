<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--내용 시작-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<!-- 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/order.js"></script>
 -->
<div class="page-main">
	<h2>주문목록</h2>
	<form id="search_form" action="order_list.do" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="3" <c:if test="${param.keyfield==1}">selected</c:if> >전체</option>
					<option value="1" <c:if test="${param.keyfield==1}">selected</c:if> >주문번호</option>
					<option value="2" <c:if test="${param.keyfield==2}">selected</c:if> >상품명</option>
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
		<div class="result-display">표시할 내역이 없습니다.</div>
	</c:if>
	
	<c:if test="${count > 0 }">
		<c:forEach var="order" items="${list }">
		<div id="order_box">
			<h4>${order.order_num }</h4>
			<p>${order.product_name}</p>
		</div>
		</c:forEach>
	</c:if>
</div>
<!-- 내용 끝 -->











