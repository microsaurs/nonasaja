<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
<script type="text/javascript">
	var naverLogin = new naver.LoginWithNaverId({
		clientId: "p5qDyh8lEAltU8wkoV_r",
  		callbackUrl: "http://localhost:8080/member/naverCheck.do",
  		isPopup: false, //회원정보 제공 화면 true:팝업, false:같은 윈도우
  		callbackHandle: true
  		/* callback 페이지가 분리되었을 경우에 callback 페이지에서는 callback처리를 해줄수 있도록 설정 */
	});
	
	//로그아웃 처리 : 세션에 저장한 정보 비우기
	function Logout(){
		sessionStorage.clear();
	}
	
	/* 네아로 로그인 정보를 초기화하기 위하여 init을 호출 */
	naverLogin.init();
	
	/* callback의 처리, 정상적으로 callback 처리가 완료될 경우 main page로 redirect 또는 popup close */
	window.addEventListener('load', function(){
		naverLogin.getLoginStatus(function(status){
			if(status){
				/* 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
				var accessToken = sessionStorage.getItem("accessToken");
				var email = naverLogin.user.getEmail();
				var name = naverLogin.user.getName();
				var nickname = naverLogin.user.getNickName();
				var mobile = naverLogin.user.getMobile();
				var id = naverLogin.user.getId();
				if(email == undefined || email == null){
					alert('이메일은 필수정보입니다. 정보제공을 동의해주세요.');
					/* 사용자 정보 재동의를 위하여 다시 네이로 동의페이지로 이동 */
					naverLogin.reprompt();
					return;
				}
				
				//세션에 로그인 정보 저장
				//console.log(naverLogin.accessToken);
				//console.log(naverLogin.user);
				
				sessionStorage.setItem("accessToken", naverLogin.accessToken);
				sessionStorage.setItem("id",id);
				sessionStorage.setItem("email",email);
				sessionStorage.setItem("name",name);
				sessionStorage.setItem("nickname",nickname);
				sessionStorage.setItem("mobile",mobile);
				window.location.replace("http://localhost:8080/member/naverLogin.do?id="+id);

			}else{
				console.log("callback 처리에 실패하였습니다.");
			}
		});
	});
</script>