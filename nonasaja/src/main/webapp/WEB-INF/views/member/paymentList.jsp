<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 내용 시작 -->
<div class="page-main">
	<table>
		<tr>
			<th>번호</th>
			<th>결제금액</th>
			<th>결제 전 잔액</th>
			<th>결제 후 잔액</th>
			<th>결제일</th>
		</tr>
		<c:forEach var="point" items="${list}" varStatus="status">
		<tr>
			<td>${fn:length(list) - status.index}</td>
			<td><b><fmt:formatNumber value="${point.lionpoint}"/>원</b></td>
			<td><fmt:formatNumber value="${point.cash}"/>원</td>
			<td><fmt:formatNumber value="${point.remain}"/>원</td>
			<td>${point.reg_date}</td>
		</tr>
		</c:forEach>
	</table>
</div>
<!-- 내용 끝 -->