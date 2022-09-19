<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 내용 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/kakaopay.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
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
			<li><h3><a href="${pageContext.request.contextPath}/member/myPage.do">회원정보</a></h3></li>
			<li><a href="myPageProduct.do">공동구매</a></li>
			<li><a href="myPageUsed.do">중고거래</a></li>
			<li><a href="myPageClub.do">동호회</a></li>
			<li><a href="myPageCommu.do">커뮤니티</a></li>
			<c:if test="${member.root == 0}">
			<li><a href="delete.do">회원탈퇴</a></li>
			</c:if>
		</ul>
	</div>
	<div class="mypage-right">
		<table>
			<tr>
				<th colspan="2"><h3>회원정보</h3></th>
			</tr>
			<tr>
				<td>사진</td>
				<td>
					<c:if test="${empty member.photo_name}">
					<img src="${pageContext.request.contextPath}/images/face.png" width="100" height="100" class="my-photo">
					</c:if>
					<c:if test="${!empty member.photo_name}">
					<img src="${pageContext.request.contextPath}/member/photoView.do" width="100" height="100" class="my-photo">
				</c:if>
				</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>
					<c:if test="${member.root == 0}">${member.id}</c:if>
					<c:if test="${member.root == 1}">네이버 회원</c:if>
					<c:if test="${member.root == 2}">카카오 회원</c:if>
				</td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td>${member.nickname}</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>${member.email}</td>
			</tr>
			<tr>
				<td>연락처</td>
				<td>${member.phone}</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>${member.addr1}<br>${member.addr2}</td>
			</tr>
			<tr>
				<td>관심</td>
				<td>${member.interest}</td>
			</tr>
			<!-- <tr>
				<td>사진</td>
				<td></td>
			</tr> -->
			<tr>
				<td colspan="2"><div><div class="update-btn"><a href="update.do">수정</a></div></div></td>
			</tr>
		</table>
	</div>
</div>
<div class="float-clear"></div>
<!-- 내용 끝 -->



