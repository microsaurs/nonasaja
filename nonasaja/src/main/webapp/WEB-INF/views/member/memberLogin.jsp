<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!-- 내용 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<div class="login-wrap">
	<ul>
		<li>
			<div class="logo">
				<div class="logo-img">
					<img src="${pageContext.request.contextPath}/images/노나사자대표이미지.png" width="200" height="200">
				</div>
				<div class="logo-font">
					<span class="nona">노나</span><span class="saja">사자</span>
				</div>
			</div>
		</li>
		<li>
			<div class="inputs">
				<form:form id="login_form" action="login.do" modelAttribute="memberVO">
				<label for="id"></label>
				<form:input path="id" placeholder=" 아이디"/>
				<label for="passwd"></label>
				<form:password path="passwd" placeholder=" 비밀번호"/><br>
				<input type="checkbox"> 아이디 저장
				<div class="float-right findIdPw">
					<a href="#">아이디 찾기</a> |
					<a href="#">비밀번호 찾기</a> |
					<a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a>
				</div>
				<div class="clear"></div>
				<%-- <form:errors path="id" cssClass="error-color"/><br>--%>
				<form:errors path="passwd" cssClass="error-color"/>
				<form:errors element="div" cssClass="error-color"/>
				<div class="login-btn">
					<input type="submit" value="로그인">
				</div>
				<div class="snsLogin">
					<!-- 네이버 로그인 버튼 노출 영역 -->
					<a id="naver" href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=p5qDyh8lEAltU8wkoV_r&state=nona&redirect_uri=http://localhost:8080/auth/naver/callback"><img alt="네이버 로그인 버튼" src="${pageContext.request.contextPath}/images/btnG_축약형.png" width="150" height="50"></a>
					<!-- 카카오 로그인 버튼 노출 영역 -->
					<a id="kakao" href="https://kauth.kakao.com/oauth/authorize?client_id=a8abdc39c132bcec49dcef03bb7a10d1&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code"><img alt="카카오 로그인 버튼" src="${pageContext.request.contextPath}/images/kakao_login_large.png" width="150" height="50"></a>
				</div>
				</form:form>
			</div>
			</li>
		</ul>
</div>
<!-- 내용 끝 -->