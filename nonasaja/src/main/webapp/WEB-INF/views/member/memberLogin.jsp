<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!-- 내용 시작 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
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
	<div id="naverIdLogin"></div>
	<!-- 카카오 로그인 버튼 노출 영역 -->
	<a href="https://kauth.kakao.com/oauth/authorize?client_id=a8abdc39c132bcec49dcef03bb7a10d1&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code"><img alt="" src="${pageContext.request.contextPath}/images/kakao_login_small.png"></a>
	<!-- 네아로 초기화 Script -->
	<script type="text/javascript">
	  	var naverLogin = new naver.LoginWithNaverId({
	  		clientId: "p5qDyh8lEAltU8wkoV_r",
	  		callbackUrl: "http://localhost:8080/member/naverCheck.do",
	  		isPopup: false, //회원정보 제공 화면 true:팝업, false:같은 윈도우
	  		loginButton: {color: "green", type: 3, height: 60} //로그인 버튼의 타입 지정
	  	});
	  	/* 설정정보를 초기화하고 연동을 준비 */
	  	naverLogin.init();
  	</script>
	<!-- 네아로 초기화 Script -->
	
</div>
<!-- 내용 끝 -->