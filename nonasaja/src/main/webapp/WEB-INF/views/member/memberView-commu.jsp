<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 내용 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
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
			<li><a href="${pageContext.request.contextPath}/member/myPage.do">회원정보</a></li>
			<li><a href="myPageProduct.do">공동구매</a></li>
			<li><a href="myPageUsed.do">중고거래</a></li>
			<li><a href="myPageClub.do">동호회</a></li>
			<li><h3><a href="myPageCommu.do">커뮤니티</a></h3></li>
			<c:if test="${member.root == 0}">
			<li><a href="delete.do">회원탈퇴</a></li>
			</c:if>
		</ul>
	</div>
	<div class="mypage-right">
		<table>
			<tr>
				<th colspan="2"><h3>커뮤니티</h3></th>
			</tr>
			<tr>
				<td>
					<c:if test="${type == 1}">
					<b><a href="myPageCommu.do?type=1">내가 작성한 글</a></b> |
					<a href="myPageCommu.do?type=2">내가 작성한 댓글</a>
					</c:if>
					<c:if test="${type == 2}">
					<a href="myPageCommu.do?type=1">내가 작성한 글</a> |
					<b><a href="myPageCommu.do?type=1">내가 작성한 댓글</a></b>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>
					<select id="code">
						<option value="1">커뮤니티
						<option value="2">레시피
					</select>
				<td>
			</tr>
			<tr>
				<!-- 목록 -->
			</tr>
		</table>
	</div>
</div>
<!-- 내용 끝 -->