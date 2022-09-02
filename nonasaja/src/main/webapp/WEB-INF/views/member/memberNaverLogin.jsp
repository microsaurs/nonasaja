<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.naver.login.js"></script>
<script type="text/javascript">
	var naverLogin = new naver.LoginWithNaverId({
		clientId: "p5qDyh8lEAltU8wkoV_r",
  		callbackUrl: "http://localhost:8080/member/naverLogin.do",
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
				//var accessToken = naverLogin.accessToken;
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
				
				
				//alert('이메일 :'+email+'\n이름 :'+name+'\n별명 :'+nickname+'\n전화번호 :'+mobile);
				
				//세션에 로그인 정보 저장
				//console.log(naverLogin.accessToken);
				//console.log(naverLogin.user);
				
				//sessionStorage.setItem("accessToken", naverLogin.accessToken);
				//sessionStorage.setItem("user", naverLogin.user);
				//sessionStorage.setItem("id",id);
				//sessionStorage.setItem("email",email);
				//sessionStorage.setItem("name",name);
				//sessionStorage.setItem("nickname",nickname);
				//sessionStorage.setItem("mobile",mobile);
				//sessionStorage.setItem("profileImage", naverLogin.profileImage);
				
				$('#nickname').val(nickname);
				$('#name').val(name);
				$('#phone').val(mobile);
				$('#email').val(email);
				$('#id').val(id);
			}else{
				console.log("callback 처리에 실패하였습니다.");
			}
		});
	});
</script>
<div class="page-main">
	<h2>회원가입</h2>
	<form:form modelAttribute="memberVO" id="register_form" action="/member/naverLogin.do" method="post">
		<form:hidden path="id"/>
		<ul>
			<li>
				<label for="name">이름</label>
				<form:input path="name" readonly="true"/>
			</li>
			<li>
				<label for="nickname">별명</label>
				<form:input path="nickname" readonly="true"/>
				<form:errors path="nickname" cssClass="error-color"/>
			</li>
			<li>
				<label for="phone">전화번호</label>
				<form:input path="phone" readonly="true"/>
				<form:errors path="phone" cssClass="error-color"/>
			</li>
			<li>
				<label for="email">이메일</label>
				<form:input path="email" readonly="true"/>
				<form:errors path="email" cssClass="error-color"/>
			</li>
			<li>
				<label for="zipcode">우편번호</label>
				<form:input path="zipcode"/>
				<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기">
				<form:errors path="zipcode" cssClass="error-color"/>
			</li>
			<li>
				<label for="addr1">주소</label>
				<form:input path="addr1"/>
				<form:errors path="addr1" cssClass="error-color"/>
			</li>
			<li>
				<label for="add2">상세 주소</label>
				<form:input path="addr2"/>
				<form:errors path="addr2" cssClass="error-color"/>
			</li>
			<li>
				<label for="interest">관심사</label>
				<form:checkbox path="interest" value="운동"/>운동
				<form:checkbox path="interest" value="오락"/>오락
				<form:checkbox path="interest" value="맛집"/>맛집
				<form:checkbox path="interest" value="노래"/>노래
				<form:checkbox path="interest" value="여행"/>여행
				<form:checkbox path="interest" value="스터디"/>스터디
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>
<!-- 우편번호 검색 시작 -->
<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
</div>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    // 우편번호 찾기 화면을 넣을 element
    var element_layer = document.getElementById('layer');

    function closeDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_layer.style.display = 'none';
    }

    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.address; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('addr1').value = fullAddr;
                //document.getElementById('sample2_addressEnglish').value = data.addressEnglish;

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_layer.style.display = 'none';
            },
            width : '100%',
            height : '100%',
            maxSuggestItems : 5
        }).embed(element_layer);

        // iframe을 넣은 element를 보이게 한다.
        element_layer.style.display = 'block';

        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
        initLayerPosition();
    }

    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
    function initLayerPosition(){
        var width = 300; //우편번호서비스가 들어갈 element의 width
        var height = 400; //우편번호서비스가 들어갈 element의 height
        var borderWidth = 5; //샘플에서 사용하는 border의 두께

        // 위에서 선언한 값들을 실제 element에 넣는다.
        element_layer.style.width = width + 'px';
        element_layer.style.height = height + 'px';
        element_layer.style.border = borderWidth + 'px solid';
        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
    }
</script>
<!-- 우편번호 검색 끝 -->
