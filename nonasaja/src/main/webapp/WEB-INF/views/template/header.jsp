<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 상단 시작 -->
<h2 class="align-center">SpringPage</h2>
<div class="align-right">
	<a href="${pageContext.request.contextPath}/board/list.do">홈</a>
	<a href="${pageContext.request.contextPath}/board/list.do">공동구매</a>
	<a href="${pageContext.request.contextPath}/board/list.do">세일정보</a>
	<a href="${pageContext.request.contextPath}/board/list.do">중고거래</a>
	<a href="${pageContext.request.contextPath}/clubboard/list.do">동호회</a>
	<a href="${pageContext.request.contextPath}/commuboard/list.do">커뮤니티</a>  
	<c:if test="${!empty user && !empty user.photo}">
		<img src="${pageContext.request.contextPath}/member/photoView.do" width="25" height="25" class="my-photo">
	</c:if>
	<c:if test="${!empty user && empty user.photo}">
		<img src="${pageContext.request.contextPath}/images/face.png" width="25" height="25" class="my-photo">
	</c:if>
	<c:if test="${!empty user && !empty user.nickname}">
		[<span class="user_name">${user.nickname}</span>]
	</c:if>
	<c:if test="${!empty user && empty user.nickname}">
		[<span class="user_name">${user.id}</span>]
	</c:if>
	<c:if test="${empty user}">
		<a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a>
		<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>
	</c:if>
	<c:if test="${!empty user}">
		<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
	</c:if>
	<c:if test="${!empty user && user.auth == 2}">
		<a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a>
	</c:if>
	<a href="${pageContext.request.contextPath}/main/main.do">홈으로</a>
</div>
<!-- 상단 끝 -->



