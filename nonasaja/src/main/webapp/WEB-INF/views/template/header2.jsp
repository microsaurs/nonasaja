<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/header.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header2.css">
<!-- 상단 시작 -->
<div >
	<div id="header-line"><br>
		<div id="logo">
			<a href="${pageContext.request.contextPath}/main/main.do"><img src="${pageContext.request.contextPath}/images/logo.png" width="250" height="65"></a>
		</div>
		
	</div>	
	<div class="marginforcart">
		<%-- 로그인 안된 경우 --%>
		<div class="size">
			<c:if test="${empty user}">
				<a href="${pageContext.request.contextPath}/member/login.do" class="text">로그인</a>
				<a href="${pageContext.request.contextPath}/member/registerUser.do" class="text">회원가입</a>
			</c:if>
		</div>
		<%-- 로그인 된 경우 --%>
		<div class="size">
		<c:if test="${!empty user}">
			<a href="${pageContext.request.contextPath}/member/logout.do" class="text">로그아웃</a>
			<c:if test="${!empty user && user.auth == 2}">
			<a href="${pageContext.request.contextPath}/member/myPage.do" class="text">MY페이지</a>
			</c:if>
			<c:if test="${!empty user && user.auth == 3}">
				<a href="${pageContext.request.contextPath}/member/admin_main.do" class="text">관리페이지</a>
			</c:if>
		</c:if>	
		</div>
	</div>
</div>
	

	<div>
		<div class="location"> 
			<a href="${pageContext.request.contextPath}/main/main.do" class="category-margin" id="header_main">홈</a>
			<a href="${pageContext.request.contextPath}/product/list.do" class="category-margin" id="header_product">공동구매</a>
			<a href="${pageContext.request.contextPath}/sale/saleBoardList.do" class="category-margin" id="header_sale">세일정보</a>
			<a href="${pageContext.request.contextPath}/used/list.do" class="category-margin" id="header_used">중고거래</a>
			<a href="${pageContext.request.contextPath}/clubboard/list.do" class="category-margin" id="header_clubboard">동호회</a>
			<a href="${pageContext.request.contextPath}/commuboard/list.do" class="category-margin" id="header_commuboard">커뮤니티</a>  
			<a href="${pageContext.request.contextPath}/commuRecipe/list.do" class="category-margin"  id="header_commuRecipe">레시피</a>   
		</div>
		<div class="float-right">
			<c:if test="${!empty user && !empty user.photo}">
				<img src="${pageContext.request.contextPath}/member/photoView.do" width="35" height="35" class="my-photo">
			</c:if>
			<c:if test="${!empty user && empty user.photo}">
				<img src="${pageContext.request.contextPath}/images/face.png" width="35" height="35" class="my-photo">
			</c:if>
			<c:if test="${!empty user && !empty user.nickname}">
				<p class="nickname"> <b>${user.nickname}</b> 님</p>
			</c:if>
			<c:if test="${!empty user && empty user.nickname}">
				<p class="nickname"><b>${user.id}</b> 님</p>
			</c:if>
		</div>
	</div>
	
	<hr size="1" width="100%">
<!-- 상단 끝 -->



