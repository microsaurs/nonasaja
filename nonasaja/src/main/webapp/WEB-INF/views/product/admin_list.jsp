<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/product_search.js"></script>
<div class="page-main">
	<h2>상품 목록</h2>
	<form id="search_form" action="admin_list.do" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="1" <c:if test="${param.keyfield==1}">selected</c:if> >상품명</option>
					<option value="2" <c:if test="${param.keyfield==2}">selected</c:if> >내용</option>
					<option value="3" <c:if test="${param.keyfield==3}">selected</c:if> >상품명+내용</option>
				
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
	<div class="align-right">
		<input type="button" value="상품 등록" onclick="location.href='register.do'">
		<input type="button" value="목록" onclick="location.href='admin_list.do'">
		<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	</div>
	<c:if test="${count == 0 }">
		<div class="result-display">표시할 상품이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0 }">
		<table>
			<tr>
				<th>번호</th>
				<th>상품명</th>
				<th>판매가격</th>
				<th>재고</th>
				<th>등록일</th>
				<th>상태</th>
			</tr>
			<c:forEach var="item" items="${list}">
			<tr>
				<td>${item.product_num }</td>
				<td><a href="admin_modify.do?product_num=${item.product_num }">${item.name }</a></td>
				<td><fmt:formatNumber value="${item.price2 }"/></td>
				<td><fmt:formatNumber value="${item.quantity }"/></td>
				<td>${item.reg_date }</td>
				<td>
					<c:if test="${item.status ==1 }">판매중</c:if>
					<c:if test="${item.status ==2 }">판매중지</c:if>
				</td>
			</tr>
			</c:forEach>
		</table>
		<div class="align-center">
			${page}
		</div>
	</c:if>
</div>
<!-- 내용 끝 -->



















