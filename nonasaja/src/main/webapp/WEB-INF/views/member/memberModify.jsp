<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!-- 내용 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member-modify.js"></script>
<div class="mypage-wrap">
	<div class="mypage-left">
		<ul>
			<li>
				<c:if test="${empty memberVO.photo_name}">
				<img src="${pageContext.request.contextPath}/images/face.png" width="200" height="200" class="my-photo">
				</c:if>
				<c:if test="${!empty memberVO.photo_name}">
				<img src="${pageContext.request.contextPath}/member/photoView.do" width="200" height="200" class="my-photo">
				</c:if>
			</li>
			<li id="nick"><span>${memberVO.nickname}</span></li>
			<li id="money">사자머니 &nbsp;&nbsp;&nbsp;<a href="paymentList.do">충전 ></a></li>
			<li><span><fmt:formatNumber value="${memberVO.cash}"/></span></li>
		</ul>
		<br>
		<ul>
			<li><a href="${pageContext.request.contextPath}/member/myPage.do">회원정보</a></li>
			<li><a href="#">공동구매</a></li>
			<li><a href="#">중고거래</a></li>
			<li><a href="#">동호회</a></li>
			<li><a href="#">커뮤니티</a></li>
			<c:if test="${memberVO.root == 0}">
			<li><a href="#">회원탈퇴</a></li>
			</c:if>
		</ul>
	</div>
	<div class="mypage-right">
		<form:form id="modify_form" action="update.do" method="post" modelAttribute="memberVO">
			<table>
				<tr>
					<th colspan="2"><h3>회원정보</h3></th>
				</tr>
				<tr>
					<td>사진</td>
					<td>
						<c:if test="${empty memberVO.photo_name}">
						<img src="${pageContext.request.contextPath}/images/face.png" width="100" height="100" class="my-photo">
						</c:if>
						<c:if test="${!empty memberVO.photo_name}">
						<img src="${pageContext.request.contextPath}/member/photoView.do" width="100" height="100" class="my-photo">
					</c:if>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<div class="align-center">
							<input type="button" value="프로필 사진 변경" id="photo_btn">
							<div id="photo_choice" style="display:none;">
								<input type="file" id="upload" accept="image/gif,image/png,image/jpeg" value="${memberVO.photo_name}"><br>
								<input type="button" value="확인" id="photo_submit">  
								<input type="button" value="취소" id="photo_reset"> 
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td>
						<form:hidden path="id"/>
						<c:if test="${memberVO.root == 0}">
						${memberVO.id}
						</c:if>
						<c:if test="${memberVO.root == 1}">
						네이버 회원
						</c:if>
						<c:if test="${memberVO.root == 2}">
						카카오 회원
						</c:if>
					</td>
				</tr>
				<tr>
					<td>닉네임</td>
					<td><form:input path="nickname"/></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><form:input path="email"/></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td><form:input path="phone"/></td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td>
						<form:input path="zipcode"/>
						<input type="button" id="zipcode_btn" onclick="execDaumPostcode()" value="우편번호 찾기">
					</td>
				</tr>
				<tr>
					<td>주소</td>
					<td>
						<form:input path="addr1"/>
					</td>
				</tr>
				<tr>
					<td>상세주소</td>
					<td>
						<form:input path="addr2"/>
					</td>
				</tr>
				<tr>
					<td>관심</td>
					<td>
						<form:checkbox path="f_interest" value="운동"/><label for="interest1"></label> 운동
						<form:checkbox path="f_interest" value="오락"/><label for="interest2"></label> 오락
						<form:checkbox path="f_interest" value="맛집"/><label for="interest3"></label> 맛집<br>
						<form:checkbox path="f_interest" value="노래"/><label for="interest4"></label> 노래
						<form:checkbox path="f_interest" value="여행"/><label for="interest5"></label> 여행
						<form:checkbox path="f_interest" value="스터디"/><label for="interest6"></label> 스터디
					</td>
				</tr>
				<!-- <tr>
					<td>사진</td>
					<td></td>
				</tr> -->
				<tr>
					<td colspan="2">
						<div class="align-center">
							<form:button id="submit_btn">확인</form:button>
							<input id="back_btn"type="button" value="마이페이지" onclick="location.href='myPage.do'">
						</div>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>
<div class="float-clear"></div>
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
<!-- 내용 끝 -->