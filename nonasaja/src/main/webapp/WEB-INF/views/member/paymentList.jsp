<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/kakaopay.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
<!-- 내용 시작 -->
<div class="mypage-wrap">
	<div class="mypage-left">
		<ul>
			<li>
				<c:if test="${empty member.photo_name}">
				<img src="${pageContext.request.contextPath}/images/face.png" width="200" height="200" class="my-photo">
				</c:if>
				<c:if test="${!empty member.photo_name}">
				<img src="${pageContext.request.contextPath}/member/photoView.do" width="200" height="200" class="my-photo">
				</c:if>
			</li>
			<li id="nick"><span>${member.nickname}</span></li>
			<li id="money">사자머니 &nbsp;&nbsp;&nbsp;<a href="paymentList.do">충전 ></a></li>
			<li><span><fmt:formatNumber value="${member.cash}"/>원</span></li>
		</ul>
		<br>
		<ul>
			<li><a href="#">회원정보</a></li>
			<li><a href="#">공동구매</a></li>
			<li><a href="#">중고거래</a></li>
			<li><a href="#">동호회</a></li>
			<li><a href="#">커뮤니티</a></li>
			<c:if test="${member.root == 0}">
			<li><a href="#">회원탈퇴</a></li>
			</c:if>
		</ul>
	</div>
	<div class="mypage-right">
		<table>
			<tr>
				<th colspan="5"><b>사자머니</b></th>
			</tr>
			<tr>
				<td colspan="5">현재 보유중인 사자머니</td>
			</tr>
			<tr>
				<td><fmt:formatNumber value="${member.cash}"/>  원</td>
			</tr>
			<tr>
				<td colspan="4">
					<form>
						<input type="radio" name="money" value="5000">5천원
						<input type="radio" name="money" value="10000">1만원
						<input type="radio" name="money" value="30000">3만원
						<input type="radio" name="money" value="50000">5만원
		    		</form>
				</td>
				<td>
					<button id="kpay_btn">충전</button>
				</td>
			</tr>
			<tr class="label">
				<td>번호</td>
				<td>결제금액</td>
				<td>결제 전 잔액</td>
				<td>결제 후 잔액</td>
				<td style="text-align:center;">결제일</td>
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
</div>
<!-- 내용 끝 -->