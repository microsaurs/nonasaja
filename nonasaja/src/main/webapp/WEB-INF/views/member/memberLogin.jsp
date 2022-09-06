<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/otherLogin.js"></script> --%>
<div class="page-main">
	<h2>회원 로그인</h2>
	<form:form id="login_form" action="login.do"
	                    modelAttribute="memberVO">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="id">아이디</label>
				<form:input path="id"/>
				<form:errors path="id" cssClass="error-color"/>
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<form:password path="passwd"/>
				<form:errors path="passwd" cssClass="error-color"/>
			</li>
		</ul>  
		<div class="align-center">
			<input type="submit" value="로그인">
			<input type="button" value="홈으로"
			 onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>                  
	</form:form>
	<!-- 네이버 로그인 버튼 노출 영역 -->
	<a id="naver" href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=p5qDyh8lEAltU8wkoV_r&state=nona&redirect_uri=http://localhost:8080/auth/naver/callback"><img alt="네이버 로그인 버튼" src="${pageContext.request.contextPath}/images/naver_login_smallG.png" width="100" height="40"></a>
	<!-- 카카오 로그인 버튼 노출 영역 -->
	<a id="kakao" href="https://kauth.kakao.com/oauth/authorize?client_id=a8abdc39c132bcec49dcef03bb7a10d1&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code"><img alt="카카오 로그인 버튼" src="${pageContext.request.contextPath}/images/kakao_login_large.png" width="100" height="40"></a>
	<%-- <a id="kakao" href=""><img alt="카카오 로그인 버튼" src="${pageContext.request.contextPath}/images/kakao_login_large.png" width="100" height="40"></a> --%>
	
</div>
<!-- 내용 끝 -->