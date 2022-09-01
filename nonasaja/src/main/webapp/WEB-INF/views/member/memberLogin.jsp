<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!-- 내용 시작 -->
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
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
	<div id="naverIdLogin_loginButton">
		<a href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=p5qDyh8lEAltU8wkoV_r&redirect_uri=http://localhost:8080/"><img src="${pageContext.request.contextPath}/images/btnG_아이콘원형.png" width="85" height="85"></a>
	</div>

</div>
<!-- 내용 끝 -->